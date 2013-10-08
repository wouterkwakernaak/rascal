@bootstrapParser
module experiments::Compiler::Rascal2muRascal::RascalExpression

import Prelude;

import lang::rascal::\syntax::Rascal;

import lang::rascal::types::TestChecker;
import lang::rascal::types::CheckTypes;
import lang::rascal::types::AbstractName;
import lang::rascal::types::AbstractType;

import experiments::Compiler::Rascal2muRascal::TmpAndLabel;
import experiments::Compiler::Rascal2muRascal::RascalModule;
import experiments::Compiler::Rascal2muRascal::RascalPattern;
import experiments::Compiler::Rascal2muRascal::RascalStatement;
import experiments::Compiler::Rascal2muRascal::RascalType;
import experiments::Compiler::Rascal2muRascal::TypeReifier;

import experiments::Compiler::muRascal::AST;

import experiments::Compiler::Rascal2muRascal::TypeUtils;


int size_exps({Expression ","}* es) = size([e | e <- es]);		// TODO: should become library function
int size_assignables({Assignable ","}+ es) = size([e | e <- es]);	// TODO: should become library function

// Create (and flatten) a muAll

MuExp makeMuAll([*exps1, muAll(list[MuExp] exps2), exps3]) = makeMuAll(exps1 + exps2 + exps3);
default MuExp makeMuAll(list[MuExp] exps) = muAll(exps);

// Create (and flatten) a muOne

MuExp makeMuOne([*exps1, muOne(list[MuExp] exps2), exps3]) = makeMuOne(exps1 + exps2 + exps3);
default MuExp makeMuOne(exp) = muOne(exp);

// Generate code for completely type-resolved operators

bool isContainerType(str t) = t in {"list", "map", "set", "rel", "lrel"};

MuExp infix(str op, Expression e){
  lot = getOuterType(e.lhs);
  rot = getOuterType(e.rhs);
  if(lot == "value" || rot == "value"){
     return muCallPrim("<op>", [*translate(e.lhs), *translate(e.rhs)]);
  }
  if(isContainerType(lot))
     if(isContainerType(rot))
       return muCallPrim("<lot>_<op>_<rot>", [*translate(e.lhs), *translate(e.rhs)]);
     else
       return muCallPrim("<lot>_<op>_elm", [*translate(e.lhs), *translate(e.rhs)]);
  else
    if(isContainerType(rot))
       return muCallPrim("elm_<op>_<rot>", [*translate(e.lhs), *translate(e.rhs)]);
     else
       return muCallPrim("<lot>_<op>_<rot>", [*translate(e.lhs), *translate(e.rhs)]);
}

MuExp infix_elm_left(str op, Expression e){
   rot = getOuterType(e.rhs);
   return muCallPrim("elm_<op>_<rot>", [*translate(e.lhs), *translate(e.rhs)]);
}

MuExp infix_rel_lrel(str op, Expression e){
  lot = getOuterType(e.lhs);
  if(lot == "set") lot = "rel"; else if (lot == "list") lot = "lrel";
  rot = getOuterType(e.rhs);
  if(rot == "set") rot = "rel"; else if (rot == "list") rot = "lrel";
  return muCallPrim("<lot>_<op>_<rot>", [*translate(e.lhs), *translate(e.rhs)]);
}
 
MuExp prefix(str op, Expression arg) = muCallPrim("<op>_<getOuterType(arg)>", [translate(arg)]);

MuExp postfix(str op, Expression arg) = muCallPrim("<getOuterType(arg)>_<op>", [translate(arg)]);

MuExp postfix_rel_lrel(str op, Expression arg) {
  ot = getOuterType(arg);
  if(ot == "set" ) ot = "rel"; else if(ot == "list") ot = "lrel";
  return muCallPrim("<ot>_<op>", [translate(arg)]);
}

set[str] numeric = {"int", "real", "rat", "num"};

MuExp comparison(str op, Expression e) {
  lot = getOuterType(e.lhs);
  rot = getOuterType(e.rhs);
  if(lot == "value" || rot == "value"){
     lot = ""; rot = "";
  } else {
    if(lot in numeric) lot += "_"; else lot = "";
 
     if(rot in numeric) rot = "_" + rot; else rot = "";
  }
  
  return muCallPrim("<lot><op><rot>", [*translate(e.lhs), *translate(e.rhs)]);
}

/*********************************************************************/
/*                  Expressions                                       */
/*********************************************************************/

// literals

MuExp translate((Literal) `<BooleanLiteral b>`) = "<b>" == "true" ? muCon(true) : muCon(false);
 
MuExp translate((Literal) `<IntegerLiteral n>`) = muCon(toInt("<n>"));

MuExp translate((Literal) `<RegExpLiteral r>`) { throw "RexExpLiteral cannot occur in expression"; }

MuExp translate((Literal) `<StringLiteral n>`) = translateStringLiteral(n);

MuExp translate((Literal) `<LocationLiteral src>`) = translateLocationLiteral(src);

default MuExp translate((Literal) `<Literal s>`) =  muCon(readTextValueString("<s>"));

MuExp translate(e:(Expression)  `<Literal s>`) = translate(s);

// Other expressions

// Concrete
MuExp translate(e:(Expression) `<Concrete concret>`) { throw("Concrete"); }

// Block
MuExp translate(e:(Expression) `{ <Statement+ statements> }`) = muBlock([translate(stat) | stat <- statements]);

// Parenthesized expression
MuExp translate(e:(Expression) `(<Expression expression>)`)   = translate(expression);

// Closure
MuExp translate (e:(Expression) `<Type \type> <Parameters parameters> { <Statement+ statements> }`) = translateClosure(e, parameters, statements);

MuExp translate (e:(Expression) `<Parameters parameters> { <Statement* statements> }`) = translateClosure(e, parameters, statements);

// Enumerator with range

MuExp translate (e:(Expression) `<Pattern pat> \<- [ <Expression first> .. <Expression last> ]`) =
    muMulti(muCreate(mkCallToLibFun("Library", "RANGE", 3), [ translatePat(pat), translate(first), translate(last)]));
    
MuExp translate (e:(Expression) `<Pattern pat> \<- [ <Expression first> , <Expression second> .. <Expression last> ]`) =
     muMulti(muCreate(mkCallToLibFun("Library", "RANGE_STEP", 4), [ translatePat(pat), translate(first), translate(second), translate(last)]));

// Range

MuExp translate (e:(Expression) `[ <Expression first> .. <Expression last> ]`) {
   kind = (getOuterType(first) == "int" && getOuterType(last) == "int") ? "int" : "real";
   return muCallPrim("range_create_<kind>", [translate(first), translate(last)]);
}

MuExp translate (e:(Expression) `[ <Expression first> , <Expression second> .. <Expression last> ]`) {
   kind = (getOuterType(first) == "int" && getOuterType(second) == "int" && getOuterType(last) == "int") ? "int" : "real";
   return muCallPrim("range_step_create_<kind>", [translate(first),  translate(second), translate(last)]);
}

// Visit
MuExp translate (e:(Expression) `<Label label> <Visit \visit>`) = translateVisit(label, \visit);

// Reducer
MuExp translate (e:(Expression) `( <Expression init> | <Expression result> | <{Expression ","}+ generators> )`) = translateReducer(init, result, generators);

// Reified type
MuExp translate (e:(Expression) `type ( <Expression symbol> , <Expression definitions >)`) { throw("reifiedType"); }
//  muCon(symbolToValue(symbol, config));

// Call
MuExp translate(e:(Expression) `<Expression expression> ( <{Expression ","}* arguments> <KeywordArguments keywordArguments>)`){
   // ignore kw arguments for the moment
   MuExp receiver = translate(expression);
   list[MuExp] args = [ translate(a) | a <- arguments ];
   if(getOuterType(expression) == "str") {
       return muCallPrim("node_create", [receiver, *args]);
   }
   
   if(getOuterType(expression) == "loc"){
       return muCallPrim("loc_with_offset_create", [receiver, *args]);
   }
   
   if(muFun(str _) := receiver || muFun(str _, str _) := receiver || muConstr(str _) := receiver) {
       return muCall(receiver, args);
   }
   
   // Now overloading resolution...
   ftype = getType(expression@\loc); // Get the type of a receiver
   if(isOverloadedFunction(receiver) && receiver.fuid in overloadingResolver) {
       // Get the types of arguments
       list[Symbol] targs = [ getType(arg@\loc) | arg <- arguments ];
       // Generate a unique name for an overloaded function resolved for this specific use 
       str ofqname = receiver.fuid + "(<for(targ<-targs){><targ>;<}>)";
       // Resolve alternatives for this specific call
       int i = overloadingResolver[receiver.fuid];
       tuple[str scopeIn,set[int] alts] of = overloadedFunctions[i];
       set[int] resolved = {};
       
       bool matches(Symbol t) {
           if(isFunctionType(ftype) || isConstructorType(ftype)) { 
               return t == ftype;
           }           
           if(isOverloadedType(ftype)) {
               return t in (getNonDefaultOverloadOptions(ftype) + getDefaultOverloadOptions(ftype));
           }
           throw "Ups, unexpected type of the call receiver expression!";
       }
       
       for(int alt <- of.alts) {
           t = fuid2type[alt];
           if(matches(t)) {
               resolved += alt;
           }
       }
       
       bool exists = <of.scopeIn,resolved> in overloadedFunctions;
       if(!exists) {
           i = size(overloadedFunctions);
           overloadedFunctions += <of.scopeIn,resolved>;
       } else {
           i = indexOf(overloadedFunctions, <of.scopeIn,resolved>);
       }
       
       overloadingResolver[ofqname] = i;
       return muOCall(muOFun(ofqname), args);
   }
   if(isOverloadedFunction(receiver) && receiver.fuid notin overloadingResolver) {
      throw "The use of a function has to be managed via overloading resolver!";
   }
   // Push down additional information if the overloading resolution needs to be done at runtime
   return muOCall(receiver, 
   				  isFunctionType(ftype) ? Symbol::\tuple([ ftype ]) : Symbol::\tuple([ t | Symbol t <- getNonDefaultOverloadOptions(ftype) + getDefaultOverloadOptions(ftype) ]), 
   				  args);
}

// Any
MuExp translate (e:(Expression) `any ( <{Expression ","}+ generators> )`) = makeMuOne([translate(g) | g <- generators ]);

// All
MuExp translate (e:(Expression) `all ( <{Expression ","}+ generators> )`) = makeMuAll([translate(g) | g <- generators ]);

// Comprehension
MuExp translate (e:(Expression) `<Comprehension comprehension>`) = translateComprehension(comprehension);

// Set
MuExp translate(Expression e:(Expression)`{ <{Expression ","}* es> }`) = translateSetOrList(es, "set");

// List
MuExp translate(Expression e:(Expression)`[ <{Expression ","}* es> ]`)  = translateSetOrList(es, "list");

// Reified type
MuExp translate (e:(Expression) `# <Type tp>`) = muCon(symbolToValue(translateType(tp),config));

// Tuple
MuExp translate (e:(Expression) `\< <{Expression ","}+ elements> \>`) =
    muCallPrim("tuple_create", [ translate(elem) | elem <- elements ]);

// Map
MuExp translate (e:(Expression) `( <{Mapping[Expression] ","}* mappings> )`) =
   muCallPrim("map_create", [ translate(m.from), translate(m.to) | m <- mappings ]);

// It in reducer
MuExp translate (e:(Expression) `it`) = muTmp(topIt());
 
 // Qualified name
MuExp translate(q:(QualifiedName) `<QualifiedName v>`) = mkVar("<v>", v@\loc);

MuExp translate((Expression) `<QualifiedName v>`) = translate(v);

// For the benefit of names in regular expressions

MuExp translate((Name) `<Name name>`) = mkVar("<name>", name@\loc);

// Subscript
MuExp translate(Expression e:(Expression) `<Expression exp> [ <{Expression ","}+ subscripts> ]`){
    ot = getOuterType(exp);
    op = "<ot>_subscript";
    if(ot notin {"map"}) {
    	op = "<getOuterType(exp)>_subscript_<intercalate("-", [getOuterType(s) | s <- subscripts])>";
    }
    return muCallPrim(op, translate(exp) + [translate(s) | s <- subscripts]);
}

// Slice
MuExp translate (e:(Expression) `<Expression expression> [ <OptionalExpression optFirst> .. <OptionalExpression optLast> ]`) =
	translateSlice(expression, optFirst, optLast);

MuExp translate (e:(Expression) `<Expression expression> [ <OptionalExpression optFirst> , <Expression second> .. <OptionalExpression optLast> ]`) =
	translateSlice(expression, optFirst, second, optLast);

// Field access
MuExp translate (e:(Expression) `<Expression expression> . <Name field>`) =
    muCallPrim("<getOuterType(expression)>_field_access", [ translate(expression), muCon("<field>") ]);

// Field update
MuExp translate (e:(Expression) `<Expression expression> [ <Name key> = <Expression replacement> ]`) =
    muCallPrim("<getOuterType(expression)>_field_update", [ translate(expression), muCon("<key>"), translate(replacement) ]);

// Field project
MuExp translate (e:(Expression) `<Expression expression> \< <{Field ","}+ fields> \>`) {
    tp = getType(expression@\loc);   
    list[str] fieldNames = [];
    if(isRelType(tp)){
       tp = getSetElementType(tp);
    } else if(isListType(tp)){
       tp = getListElementType(tp);
    } else if(isMapType(tp)){
       tp = getMapFieldsAsTuple(tp);
    }
    if(tupleHasFieldNames(tp)){
    	fieldNames = getTupleFieldNames(tp);
    }	
    fcode = [(f is index) ? muCon(toInt("<f>")) : muCon(indexOf(fieldNames, "<f>")) | f <- fields];
    //fcode = [(f is index) ? muCon(toInt("<f>")) : muCon("<f>") | f <- fields];
    return muCallPrim("<getOuterType(expression)>_field_project", [ translate(expression), *fcode]);
}

// setAnnotation
MuExp translate (e:(Expression) `<Expression expression> [ @ <Name name> = <Expression val> ]`) =
    muCallPrim("annotation_set", [translate(expression), muCon("<name>"), translate(val)]);

// getAnnotation
MuExp translate (e:(Expression) `<Expression expression> @ <Name name>`) {
println("getAnnotation: <e>");
    return muCallPrim("annotation_get", [translate(expression), muCon("<name>")]);
    }

// Is
MuExp translate (e:(Expression) `<Expression expression> is <Name name>`) =
    muCallPrim("is", [translate(expression), muCon("<name>")]);

// Has
MuExp translate (e:(Expression) `<Expression expression> has <Name name>`) = 
    muCon(hasField(getType(expression@\loc), "<name>"));   

// Transitive closure
MuExp translate(e:(Expression) `<Expression argument> +`)   = postfix_rel_lrel("transitive_closure", argument);

// Transitive reflexive closure
MuExp translate(e:(Expression) `<Expression argument> *`)   = postfix_rel_lrel("transitive_reflexive_closure", argument);

// isDefined?
MuExp translate(e:(Expression) `<Expression argument> ?`)  = generateIfDefinedOtherwise(muBlock([ translate(argument), muCon(true) ]),  muCon(false));

// IfDefinedOtherwise
MuExp translate(e:(Expression) `<Expression lhs> ? <Expression rhs>`)  = generateIfDefinedOtherwise(translate(lhs), translate(rhs));

MuExp generateIfDefinedOtherwise(MuExp muLHS, MuExp muRHS) {

    str varname = asTmp(nextLabel());
	// Check if evaluation of the expression throws a 'NoSuchKey' or 'NoSuchAnnotation' exception;
	// do this by checking equality of the value constructor names
	cond1 = muCallMuPrim("equal", [ muCon("UninitializedVariable"),
									muCallMuPrim("subscript_array_mint", [ muCallMuPrim("get_name_and_children", [ muTmp(asUnwrapedThrown(varname)) ]), muInt(0) ] ) ]);
	cond3 = muCallMuPrim("equal", [ muCon("NoSuchKey"),
									muCallMuPrim("subscript_array_mint", [ muCallMuPrim("get_name_and_children", [ muTmp(asUnwrapedThrown(varname)) ]), muInt(0) ] ) ]);
	cond2 = muCallMuPrim("equal", [ muCon("NoSuchAnnotation"),
									muCallMuPrim("subscript_array_mint", [ muCallMuPrim("get_name_and_children", [ muTmp(asUnwrapedThrown(varname)) ]), muInt(0) ] ) ]);
	
	elsePart3 = muIfelse(nextLabel(), muAll([cond3]), [ muRHS ], [ muThrow(muTmp(varname)) ]);
	elsePart2 = muIfelse(nextLabel(), muAll([cond2]), [ muRHS ], [ elsePart3 ]);
	catchBody = muIfelse(nextLabel(), muAll([cond1]), [ muRHS ], [ elsePart2 ]);
	return muTry(muLHS, muCatch(varname, Symbol::\adt("RuntimeException",[]), catchBody), 
			  		 	muBlock([]));
}

// Not
MuExp translate(e:(Expression) `!<Expression argument>`)    = translateBool(e);

// Negate
MuExp translate(e:(Expression) `-<Expression argument>`)    = prefix("negative", argument);

// Splice
MuExp translate(e:(Expression) `*<Expression argument>`) {
    throw "Splice cannot occur outside set or list";
}

// AsType
MuExp translate(e:(Expression) `[ <Type \type> ] <Expression argument>`)  { throw("asType"); }

// Composition
MuExp translate(e:(Expression) `<Expression lhs> o <Expression rhs>`)   = infix_rel_lrel("compose", e);

// Product
MuExp translate(e:(Expression) `<Expression lhs> * <Expression rhs>`)   = infix("product", e);

// Join
MuExp translate(e:(Expression) `<Expression lhs> join <Expression rhs>`)   = infix("join", e);

// Remainder
MuExp translate(e:(Expression) `<Expression lhs> % <Expression rhs>`)   = infix("remainder", e);

// Division
MuExp translate(e:(Expression) `<Expression lhs> / <Expression rhs>`)   = infix("divide", e);

// Intersection
MuExp translate(e:(Expression) `<Expression lhs> & <Expression rhs>`)   = infix("intersect", e);

//Addition
MuExp translate(e:(Expression) `<Expression lhs> + <Expression rhs>`)   = infix("add", e);

// Subtraction
MuExp translate(e:(Expression) `<Expression lhs> - <Expression rhs>`)   = infix("subtract", e);

// Insert Before
MuExp translate(e:(Expression) `<Expression lhs> \>\> <Expression rhs>`)   = infix("add", e);

// Append After
MuExp translate(e:(Expression) `<Expression lhs> \<\< <Expression rhs>`)   = infix("add", e);

// Modulo
MuExp translate(e:(Expression) `<Expression lhs> mod <Expression rhs>`)   = infix("mod", e);

// Notin
MuExp translate(e:(Expression) `<Expression lhs> notin <Expression rhs>`)   = infix_elm_left("notin", e);

// In
MuExp translate(e:(Expression) `<Expression lhs> in <Expression rhs>`)   = infix_elm_left("in", e);

// Greater Equal
MuExp translate(e:(Expression) `<Expression lhs> \>= <Expression rhs>`) = infix("greaterequal", e);

// Less Equal
MuExp translate(e:(Expression) `<Expression lhs> \<= <Expression rhs>`) = infix("lessequal", e);

// Less
MuExp translate(e:(Expression) `<Expression lhs> \< <Expression rhs>`)  = infix("less", e);

// Greater
MuExp translate(e:(Expression) `<Expression lhs> \> <Expression rhs>`)  = infix("greater", e);

// Equal
MuExp translate(e:(Expression) `<Expression lhs> == <Expression rhs>`)  = comparison("equal", e);

// NotEqual
MuExp translate(e:(Expression) `<Expression lhs> != <Expression rhs>`)  = comparison("notequal", e);



// NoMatch
MuExp translate(e:(Expression) `<Pattern pat> !:= <Expression rhs>`)  = translateMatch(e);

// Match
MuExp translate(e:(Expression) `<Pattern pat> := <Expression exp>`)     = translateMatch(e);

// Enumerate

MuExp translate(e:(Expression) `<QualifiedName name> \<- <Expression exp>`) {
    <fuid, pos> = getVariableScope("<name>", name@\loc);
    return muMulti(muCreate(mkCallToLibFun("Library", "ENUMERATE_AND_ASSIGN", 2), [muVarRef("<name>", fuid, pos), translate(exp)]));
}

MuExp translate(e:(Expression) `<Type tp> <Name name> \<- <Expression exp>`) {
    <fuid, pos> = getVariableScope("<name>", name@\loc);
    return muMulti(muCreate(mkCallToLibFun("Library", "ENUMERATE_CHECK_AND_ASSIGN", 3), [muTypeCon(translateType(tp)), muVarRef("<name>", fuid, pos), translate(exp)]));
}

MuExp translate(e:(Expression) `<Pattern pat> \<- <Expression exp>`) =
    muMulti(muCreate(mkCallToLibFun("Library", "ENUMERATE_AND_MATCH", 2), [translatePat(pat), translate(exp)]));

// Implies
MuExp translate(e:(Expression) `<Expression lhs> ==\> <Expression rhs>`)  = translateBool(e);

// Equivalent
MuExp translate(e:(Expression) `<Expression lhs> \<==\> <Expression rhs>`)  = translateBool(e);

// And
MuExp translate(e:(Expression) `<Expression lhs> && <Expression rhs>`)  = translateBool(e);

// Or
MuExp translate(e:(Expression) `<Expression lhs> || <Expression rhs>`)  = translateBool(e);
 
// Conditional Expression
MuExp translate(e:(Expression) `<Expression condition> ? <Expression thenExp> : <Expression elseExp>`) = 
	// Label (used to backtrack) here is not important as it is not allowed to have 'fail' in conditional expressions 
    muIfelse(nextLabel(),makeMuAll([translate(condition)]), [translate(thenExp)],  [translate(elseExp)]); 

// Default: should not happen
default MuExp translate(Expression e) {
	throw "MISSING CASE FOR EXPRESSION: <e>";
}

/*********************************************************************/
/*                  End of Ordinary Expessions                       */
/*********************************************************************/

/*********************************************************************/
/*                  BooleanExpessions                                */
/*********************************************************************/
 
// Is an expression free of backtracking? 

bool backtrackFree(Expression e){
    visit(e){
    case (Expression) `<Pattern pat> \<- <Expression exp>`: 
    	return false;
    case (Expression) `<Pattern pat> \<- [ <Expression first> .. <Expression last> ]`: 
    	return false;
    case (Expression) `<Pattern pat> \<- [ <Expression first> , <Expression second> .. <Expression last> ]`: 
    	return false;
    }
    return true;
}

// Boolean expressions

MuExp translateBool((Expression) `<Expression lhs> && <Expression rhs>`) = translateBoolBinaryOp("and", lhs, rhs);

MuExp translateBool((Expression) `<Expression lhs> || <Expression rhs>`) = translateBoolBinaryOp("or", lhs, rhs);

MuExp translateBool((Expression) `<Expression lhs> ==\> <Expression rhs>`) = translateBoolBinaryOp("implies", lhs, rhs);

MuExp translateBool((Expression) `<Expression lhs> \<==\> <Expression rhs>`) = translateBoolBinaryOp("equivalent", lhs, rhs);

MuExp translateBool((Expression) `! <Expression lhs>`) = translateBoolNot(lhs);
 
 MuExp translateBool(e: (Expression) `<Pattern pat> := <Expression exp>`)  = translateMatch(e);
//   muMulti(muCreate(mkCallToLibFun("Library","MATCH",2), [translatePat(pat), translate(exp)]));
   
MuExp translateBool(e: (Expression) `<Pattern pat> !:= <Expression exp>`) = translateMatch(e);
//    muCallMuPrim("not_mbool", [makeMuAll([muMulti(muCreate(mkCallToLibFun("Library","MATCH",2), [translatePat(pat), translate(exp)]))]) ]);

// All other expressions are translated as ordinary expression

default MuExp translateBool(Expression e) {
   println("translateBool, default: <e>");
   return translate(e);
}
   
// Translate Boolean operators

MuExp translateBoolBinaryOp(str fun, Expression lhs, Expression rhs){
  if(backtrackFree(lhs) && backtrackFree(rhs)) {
     return muCallMuPrim("<fun>_mbool_mbool", [translateBool(lhs), translateBool(rhs)]);
  } else {
    switch(fun){
    	case "and": return makeMuAll([translate(lhs), translate(rhs)]);
    	case "or":  // a or b == !(!a and !b)
    				return muCallMuPrim("not_mbool", [makeMuAll([muCallMuPrim("not_mbool", [translate(lhs)]),  muCallMuPrim("not_mbool", [translate(lhs)])])]);
    	case "implies":
    				// a ==> b
    	            return makeMuAll([muCallMuPrim("implies_mbool_mbool", [makeMuAll([translate(lhs)]), makeMuAll([translate(rhs)])])]);
    	case "equivalent":
    				// a <==> b
    				return makeMuAll([muCallMuPrim("equivalent_mbool_mbool", [makeMuAll([translate(lhs)]), makeMuAll([translate(rhs)])])]);
    	default:
    		throw "translateBoolBinary: unknown operator <fun>";
    }
  }
}

MuExp translateBoolNot(Expression lhs){
  if(backtrackFree(lhs)){
  	  return muCallMuPrim("not_mbool", [translateBool(lhs)]);
  	} else {
  	  return muCallMuPrim("not_mbool", [ makeMuAll([translate(lhs)]) ]);
  	}
}

/*********************************************************************/
/*      Auxiliary functions for translating various constructs       */
/*********************************************************************


// Translate a string literals and string templates

/*
syntax StringLiteral
	= template: PreStringChars pre StringTemplate template StringTail tail 
	| interpolated: PreStringChars pre Expression expression StringTail tail 
	| nonInterpolated: StringConstant constant ;
	
lexical PreStringChars
	= [\"] StringCharacter* [\<] ;
	
lexical MidStringChars
	=  [\>] StringCharacter* [\<] ;
	
lexical PostStringChars
	= @category="Constant" [\>] StringCharacter* [\"] ;
*/	

MuExp translateStringLiteral(s: (StringLiteral) `<PreStringChars pre> <StringTemplate template> <StringTail tail>`) {
	preResult = nextTmp();
	return muBlock( [ muAssignTmp(preResult, translatePre(pre)),
                      muCallPrim("template_addunindented", [ translateTemplate(template, preResult), *translateTail(tail)])
                    ]);
}
    
MuExp translateStringLiteral((StringLiteral) `<PreStringChars pre> <Expression expression> <StringTail tail>`) {
    preResult = nextTmp();
    return muBlock( [ muAssignTmp(preResult, translatePre(pre)),
					  muCallPrim("template_addunindented", [ translateTemplate(expression, preResult), *translateTail(tail)])
					]   );
}
                    
MuExp translateStringLiteral((StringLiteral)`<StringConstant constant>`) = muCon(readTextValueString("<constant>"));

MuExp translatePre(PreStringChars pre) {
  content = "<pre>"[1..-1];
  return muCon(content);  //[muCallPrim("str_remove_margins", [muCon(content)])];
}

/*
syntax StringTemplate
	= ifThen    : "if"    "(" {Expression ","}+ conditions ")" "{" Statement* preStats StringMiddle body Statement* postStats "}" 
	| ifThenElse: "if"    "(" {Expression ","}+ conditions ")" "{" Statement* preStatsThen StringMiddle thenString Statement* postStatsThen "}" "else" "{" Statement* preStatsElse StringMiddle elseString Statement* postStatsElse "}" 
	| \for       : "for"   "(" {Expression ","}+ generators ")" "{" Statement* preStats StringMiddle body Statement* postStats "}" 
	| doWhile   : "do"    "{" Statement* preStats StringMiddle body Statement* postStats "}" "while" "(" Expression condition ")" 
	| \while     : "while" "(" Expression condition ")" "{" Statement* preStats StringMiddle body Statement* postStats "}" ;
*/
	

/*
  syntax StringMiddle
	= mid: MidStringChars mid 
	| template: MidStringChars mid StringTemplate template StringMiddle tail 
	| interpolated: MidStringChars mid Expression expression StringMiddle tail ;
*/

MuExp translateMiddle((StringMiddle) `<MidStringChars mid>`)  =  muCon("<mid>"[1..-1]); // muCallPrim("str_remove_margins", [muCon("<mid>"[1..-1])]);

MuExp translateMiddle((StringMiddle) `<MidStringChars mid> <StringTemplate template> <StringMiddle tail>`) {
    midResult = nextTmp();
    return muBlock( [ muAssignTmp(midResult, translateMid(mid)),
   			          muCallPrim("template_addunindented", [ translateTemplate(template, midResult), translateMiddle(tail) ])
   			        ]);
   	}

MuExp translateMiddle((StringMiddle) `<MidStringChars mid> <Expression expression> <StringMiddle tail>`) {
    midResult = nextTmp();
    return muBlock( [ muAssignTmp(midResult, translateMid(mid)),
                      muCallPrim("template_addunindented", [ translateTemplate(expression, midResult), translateMiddle(tail) ])
                    ]);
}

MuExp translateMid(MidStringChars mid) {
  content = "<mid>"[1..-1];
  return muCon(content);
}    
/*
syntax StringTail
	= midInterpolated: MidStringChars mid Expression expression StringTail tail 
	| post: PostStringChars post 
	| midTemplate: MidStringChars mid StringTemplate template StringTail tail ;
*/

list[MuExp] translateTail((StringTail) `<MidStringChars mid> <Expression expression> <StringTail tail>`) {
    midResult = nextTmp();
    return [ muBlock( [ muAssignTmp(midResult, translateMid(mid)),
                      muCallPrim("template_addunindented", [ translateTemplate(expression, midResult), *translateTail(tail)])
                    ])
           ];
}
	
list[MuExp] translateTail((StringTail) `<PostStringChars post>`) {
  content = "<post>"[1..-1];
  return size(content) == 0 ? [] : [muCon(content)]; //[muCallPrim("str_remove_margins", [muCon(content)])];
}

list[MuExp] translateTail((StringTail) `<MidStringChars mid> <StringTemplate template> <StringTail tail>`) {
    midResult = nextTmp();
    return [ muBlock( [ muAssignTmp(midResult, translateMid(mid)),
                        muCallPrim("template_addunindented", [ translateTemplate(template, midResult), *translateTail(tail) ])
                    ])
           ];
 }  
 
 MuExp translateTemplate(Expression e, str pre){
    result = nextTmp();
    return muBlock([ muAssignTmp(result, muCallPrim("template_open", [muTmp(pre)])),
    				 muAssignTmp(result, muCallPrim("template_add", [ muTmp(result), muCallPrim("value_to_string", [translate(e)]) ])),
                     muCallPrim("template_close", [muTmp(result)])
                   ]);
 }
 
 // Translate location templates
 
 /*
 syntax LocationLiteral
	= \default: ProtocolPart protocolPart PathPart pathPart ;
 */
 
 MuExp translateLocationLiteral((LocationLiteral) `<ProtocolPart protocolPart> <PathPart pathPart>`) =
     muCallPrim("loc_create", [muCallPrim("str_add_str", [translateProtocolPart(protocolPart), translatePathPart(pathPart)])]);
 
 /*
 syntax ProtocolPart
	= nonInterpolated: ProtocolChars protocolChars 
	| interpolated: PreProtocolChars pre Expression expression ProtocolTail tail ;
	
lexical PreProtocolChars
	= "|" URLChars "\<" ;
	
 lexical MidProtocolChars
	= "\>" URLChars "\<" ;
	
lexical ProtocolChars
	= [|] URLChars "://" !>> [\t-\n \r \ \u00A0 \u1680 \u2000-\u200A \u202F \u205F \u3000];

syntax ProtocolTail
	= mid: MidProtocolChars mid Expression expression ProtocolTail tail 
	| post: PostProtocolChars post ;

lexical PostProtocolChars
	= "\>" URLChars "://" ;	
*/

 MuExp translateProtocolPart((ProtocolPart) `<ProtocolChars protocolChars>`) = muCon("<protocolChars>"[1..]);
 
 MuExp translateProtocolPart((ProtocolPart) `<PreProtocolChars pre> <Expression expression> <ProtocolTail tail>`) =
    muCallPrim("str_add_str", [muCon("<pre>"[1..-1]), translate(expression), translateProtocolTail(tail)]);
 
 // ProtocolTail
 MuExp  translateProtocolTail((ProtocolTail) `<MidProtocolChars mid> <Expression expression> <ProtocolTail tail>`) =
   muCallPrim("str_add_str", [muCon("<mid>"[1..-1]), translate(expression), translateProtocolTail(tail)]);
   
MuExp translateProtocolTail((ProtocolTail) `<PostProtocolChars post>`) = muCon("<post>"[1 ..]);

/*
syntax PathPart
	= nonInterpolated: PathChars pathChars 
	| interpolated: PrePathChars pre Expression expression PathTail tail ;

lexical PathChars
	= URLChars [|] ;
		
 syntax PathTail
	= mid: MidPathChars mid Expression expression PathTail tail 
	| post: PostPathChars post ;

lexical PrePathChars
	= URLChars "\<" ;

lexical MidPathChars
	= "\>" URLChars "\<" ;
	
lexical PostPathChars
	=  "\>" URLChars "|" ;
*/

MuExp translatePathPart((PathPart) `<PathChars pathChars>`) = muCon("<pathChars>"[..-1]);
MuExp translatePathPart((PathPart) `<PrePathChars pre> <Expression expression> <PathTail tail>`) =
   muCallPrim("str_add_str", [ muCon("<pre>"[..-1]), translate(expression), translatePathTail(tail)]);

// PathTail
MuExp translatePathTail((PathTail) `<MidPathChars mid> <Expression expression> <PathTail tail>`) =
   muCallPrim("str_add_str", [ muCon("<mid>"[1..-1]), translate(expression), translatePathTail(tail)]);
   
MuExp translatePathTail((PathTail) `<PostPathChars post>`) = muCon("<post>"[1..-1]);

 
// Translate a closure   
 
 MuExp translateClosure(Expression e, Parameters parameters, Statement+ statements) {
 	uid = loc2uid[e@\loc];
	fuid = uid2str(uid);
	
	enterFunctionScope(fuid);
	
    ftype = getClosureType(e@\loc);
	nformals = size(ftype.parameters);
	nlocals = getScopeSize(fuid);
	bool isVarArgs = (varArgs(_,_) := parameters);
  	// TODO: keyword parameters
    
    MuExp body = translateFunction(parameters.formals.formals, statements, []);
    tuple[str fuid,int pos] addr = uid2addr[uid];
    functions_in_module += muFunction(fuid, ftype, (addr.fuid in moduleNames) ? "" : addr.fuid, 
  									  nformals, nlocals, e@\loc, [], (), body);
  	
  	leaveFunctionScope();								  
  	
	return (addr.fuid == uid2str(0)) ? muFun(fuid) : muFun(fuid, addr.fuid); // closures are not overloaded
}

// Translate comprehensions

MuExp translateGenerators({Expression ","}+ generators){
   if(all(gen <- generators, backtrackFree(gen))){
      return makeMuAll([translate(g) | g <-generators]);
   } else {
     return makeMuAll([muCallPrim("rbool", [translate(g)]) | g <-generators]);
   }
}

MuExp translateComprehension(c: (Comprehension) `[ <{Expression ","}+ results> | <{Expression ","}+ generators> ]`) {
    loopname = nextLabel(); 
    tmp = asTmp(loopname);
    return
    muBlock(
    [ muAssignTmp(tmp, muCallPrim("listwriter_open", [])),
      muWhile(loopname, makeMuAll([translate(g) | g <-generators]), [muCallPrim("listwriter_add", [muTmp(tmp)] + [ translate(r) | r <- results])]), 
      muCallPrim("listwriter_close", [muTmp(tmp)]) 
    ]);
}

MuExp translateComprehension(c: (Comprehension) `{ <{Expression ","}+ results> | <{Expression ","}+ generators> }`) {
    loopname = nextLabel(); 
    tmp = asTmp(loopname); 
    return
    muBlock(
    [ muAssignTmp(tmp, muCallPrim("setwriter_open", [])),
      muWhile(loopname, makeMuAll([translate(g) | g <-generators]), [muCallPrim("setwriter_add", [muTmp(tmp)] + [ translate(r) | r <- results])]), 
      muCallPrim("setwriter_close", [muTmp(tmp)]) 
    ]);
}

MuExp translateComprehension(c: (Comprehension) `(<Expression from> : <Expression to> | <{Expression ","}+ generators> )`) {
    loopname = nextLabel(); 
    tmp = asTmp(loopname); 
    return
    muBlock(
    [ muAssignTmp(tmp, muCallPrim("mapwriter_open", [])),
      muWhile(loopname, makeMuAll([*translate(g) | g <-generators]), [muCallPrim("mapwriter_add", [muTmp(tmp)] + [ translate(from), translate(to)])]), 
      muCallPrim("mapwriter_close", [muTmp(tmp)]) 
    ]);
}

// Translate Reducer

MuExp translateReducer(init, result, generators){
    loopname = nextLabel(); 
    tmp = asTmp(loopname); 
    pushIt(tmp);
    code = [ muAssignTmp(tmp, translate(init)), muWhile(loopname, makeMuAll([translate(g) | g <-generators]), [muAssignTmp(tmp, translate(result))]), muTmp(tmp)];
    popIt();
    return muBlock(code);
}

// Translate SetOrList including spliced elements

private bool containSplices(es) = any(e <- es, e is splice);

MuExp translateSetOrList(es, str kind){
 if(containSplices(es)){
       writer = nextTmp();
       enterWriter(writer);
       code = [ muAssignTmp(writer, muCallPrim("<kind>writer_open", [])) ];
       for(elem <- es){
           if(elem is splice){
              code += muCallPrim("<kind>writer_splice", [muTmp(writer), translate(elem.argument)]);
            } else {
              code += muCallPrim("<kind>writer_add", [muTmp(writer), translate(elem)]);
           }
       }
       code += [ muCallPrim("<kind>writer_close", [ muTmp(writer) ]) ];
       leaveWriter();
       return muBlock(code);
    } else {
      return muCallPrim("<kind>_create", [ translate(elem) | elem <- es ]);
    }
}

// Translate Slice

MuExp translateSlice(Expression expression, OptionalExpression optFirst, OptionalExpression optLast) =
    muCallPrim("<getOuterType(expression)>_slice", [ translate(expression), translateOpt(optFirst), muCon("false"), translateOpt(optLast) ]);

MuExp translateOpt(OptionalExpression optExp) =
    optExp is noExpression ? muCon("false") : translate(optExp.expression);

MuExp translateSlice(Expression expression, OptionalExpression optFirst, Expression second, OptionalExpression optLast) =
    muCallPrim("<getOuterType(expression)>_slice", [  translate(expression), translateOpt(optFirst), translate(second), translateOpt(optLast) ]);

// Translate Visit

MuExp translateVisit(label, \visit) {
	
	int i = nextVisit();
	
	strategy = 0;
	if(\visit is givenStrategy) {
		switch(\visit.strategy) {
			case (Strategy) `bottom-up`      : strategy = 0;
			case (Strategy) `top-down`       : strategy = 1;
			case (Strategy) `bottom-up-break`: strategy = 2;
			case (Strategy) `top-down-break` : strategy = 3;
			case (Strategy) `innermost`      : strategy = 4;
			case (Strategy) `outermost`      : strategy = 5;
		}	
	}
	
	subject = \visit.subject;
	cases = \visit.cases;
	
	list[MuExp] exps = [];
	bool rebuild = false;
	Symbol ftype = Symbol::func(Symbol::\void(), [Symbol::\value()]);
	if(Case c <- cases, ( c is patternWithAction || /\insert(_,_) := c ) ) {
		rebuild = true;
		ftype = Symbol::func(Symbol::\value(), [Symbol::\value()]);
	}
	
	tuple[str fuid,str scopeId] fun = bla; // TODO
	
	str varname = asTmp(nextLabel());
	exps += muAssignTmp(varname, translate(subject));
	
	if(strategy == 0) {
		if(rebuild) {
			exps += muAssignTmp(varname, visitChildren(varname, fun));
		}
		exps += visitChildren(varname, <bla>);
	}
	
	exps += translateVisitCases(varname, [ c | Case c <- cases ], rebuild);
	
	if(strategy == 1) {
		if(rebuild) {
			exps += muAssignTmp(varname, visitChildren(varname, fun));
		}
		exps += visitChildren(varname, <bla>);
	}

}

@doc{Applies a function to all the children of a value, if any}
MuExp visitChildren(str varname, tuple[str fuid, str scopeId] fun) {
	str name_and_children = asTmp(nextLabel());
	muAssignTmp(name_and_children, muCallMuPrim("get_name_and_children", [ muTmp(varname) ]));
	
	str writer   = asTmp(nextLabel()); 
	str child    = asTmp(nextLabel());
	
	str loopname = nextLabel();
	exp_list = muBlock([
					muAssignTmp(writer, muCallPrim("listwriter_open", [])),	
					muWhile(loopname, makeMuAll([ muMulti(muCreate(mkCallToLibFun("Library", "ENUMERATE_AND_ASSIGN", 2), [ muTmpRef(child), muTmp(varname) ])) ]), 
					 			  	  [ muCallPrim("listwriter_add", [ muTmp(writer), muCall(muFun(fun.fuid, fun.scopeId), [ muTmp(child) ]) ]) ]),
					muCallPrim("listwriter_close", [ muTmp(writer) ])
					]);
	loopname = nextLabel();
	exp_set = muBlock([
					muAssignTmp(writer, muCallPrim("setwriter_open", [])),	
					muWhile(loopname, makeMuAll([ muMulti(muCreate(mkCallToLibFun("Library", "ENUMERATE_AND_ASSIGN", 2), [ muTmpRef(child), muTmp(varname) ])) ]), 
					 			  	  [ muCallPrim("setwriter_add", [ muTmp(writer), muCall(muFun(fun.fuid, fun.scopeId), [ muTmp(child) ]) ]) ]),
					muCallPrim("setwriter_close", [ muTmp(writer) ])
					]);
	loopname = nextLabel();
	exp_map = muBlock([
					muAssignTmp(writer, muCallPrim("mapwriter_open", [])),	
					muWhile(loopname, makeMuAll([ muMulti(muCreate(mkCallToLibFun("Library", "ENUMERATE_AND_ASSIGN", 2), [ muTmpRef(child), muTmp(varname) ])) ]), 
					 			  	  [ muCallPrim("mapwriter_add", [ muTmp(writer), muCall(muFun(fun.fuid, fun.scopeId), [ muTmp(child) ]), muCall(muFun(fun.fuid, fun.scopeId), [ muCallPrim("map_subscript", [ muTmp(varname), muTmp(child) ]) ]) ]) ]),
					muCallPrim("mapwriter_close", [ muTmp(writer) ])
					]);
	return muTypeSwitch( muTmp(varname), 
				 		 [ 
				   		  muTypeCase("list", exp_list), 
				   		  muTypeCase("set", exp_set),
				   		  muTypeCase("map", exp_map),
				   		  muTypeCase("tuple", muTmp(varname)),      // TODO:
				   		  muTypeCase("node", muTmp(varname)),       // TODO:
				   		  muTypeCase("constructor", muTmp(varname)) // TODO:
				 		 ], 
				 		 muTmp(varname) );
}

MuExp translateVisitCases(str varname, list[Case] cases, bool rebuild) {
	// TODO: conditional
	if(size(cases) == 0) {
		return rebuild ? muTmp(varname) : muBlock([]);
	}
	
	c = head(cases);
	
	if(c is patternWithAction) {
			pattern = c.patternWithAction;
			ifname = nextLabel();
			enterBacktrackingScope(ifname);
			if(c.patternWithAction is replacing) {
				expression = c.patternWithAction.replacement.replacementExpression;
				replacement = translate(expression);
        		cond = muMulti(muCreate(mkCallToLibFun("Library","MATCH",2), [translatePat(pattern), muTmp(varname)]));
        		exp = muIfelse(ifname, muAll([cond]), [translate(replacement)], [translateVisitCases(varname, tail(cases))]);
        		leaveBacktrackingScope();
        		return exp;
			} else {
				// Arbitrary
				statement = c.patternWithAction.statement;
				list[MuExp] exps = [];
				if(statement is nonEmptyBlock) {
					bool hasInsert = false;
					for(Statement stat <- statement.statements) {
						if(stat is \insert) {
							hasInsert = true;
							break;
						}
						exps += translate(stat);
					} 
				} else {
					if(statement is \insert) {
						hasInsert = true;
					}
					exp += translate(statement);
				}
				cond = muMulti(muCreate(mkCallToLibFun("Library","MATCH",2), [translatePat(pattern), muTmp(varname)]));
        		exp = muIfelse(ifname, muAll([cond]), (rebuild && !hasInsert) ? [ *exps, muTmp(varname) ] : exps, [translateVisitCases(varname, tail(cases))]);
        		leaveBacktrackingScope();
				return exp;
			}
		} else {
			// Default
			statement = c.statement;
			list[MuExp] exps = [];
			if(statement is nonEmptyBlock) {
				bool hasInsert = false;
				for(Statement stat <- statement.statements) {
					if(stat is \insert) {
						hasInsert = true;
						break;
					}
					exps += translate(stat);
				} 
			} else {
				if(statement is \insert) {
					hasInsert = true;
				}
				exps += translate(statement);
			}
			return muBlock( (rebuild && !hasInsert) ? [ *exps, muTmp(varname) ] : exps);
		}
	
}

