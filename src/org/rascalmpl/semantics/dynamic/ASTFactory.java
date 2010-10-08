package org.rascalmpl.semantics.dynamic;

import org.eclipse.imp.pdb.facts.INode;

public class ASTFactory extends org.rascalmpl.ast.ASTFactory {
	public org.rascalmpl.ast.Name.Ambiguity makeNameAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Name> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Name.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Name.Lexical makeNameLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Name.Lexical(node, string);
	}

	public org.rascalmpl.ast.EscapedName.Ambiguity makeEscapedNameAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.EscapedName> alternatives) {
		return new org.rascalmpl.semantics.dynamic.EscapedName.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.EscapedName.Lexical makeEscapedNameLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.EscapedName.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.QualifiedName.Ambiguity makeQualifiedNameAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.QualifiedName> alternatives) {
		return new org.rascalmpl.semantics.dynamic.QualifiedName.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.QualifiedName.Default makeQualifiedNameDefault(
			INode node, java.util.List<org.rascalmpl.ast.Name> names) {
		return new org.rascalmpl.semantics.dynamic.QualifiedName.Default(node,
				names);
	}

	public org.rascalmpl.ast.RegExpLiteral.Ambiguity makeRegExpLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.RegExpLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.RegExpLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.RegExpLiteral.Lexical makeRegExpLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.RegExpLiteral.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.RegExpModifier.Ambiguity makeRegExpModifierAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.RegExpModifier> alternatives) {
		return new org.rascalmpl.semantics.dynamic.RegExpModifier.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.RegExpModifier.Lexical makeRegExpModifierLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.RegExpModifier.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.Backslash.Ambiguity makeBackslashAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Backslash> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Backslash.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Backslash.Lexical makeBackslashLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Backslash.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.RegExp.Ambiguity makeRegExpAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.RegExp> alternatives) {
		return new org.rascalmpl.semantics.dynamic.RegExp.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.RegExp.Lexical makeRegExpLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.RegExp.Lexical(node, string);
	}

	public org.rascalmpl.ast.NamedRegExp.Ambiguity makeNamedRegExpAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.NamedRegExp> alternatives) {
		return new org.rascalmpl.semantics.dynamic.NamedRegExp.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.NamedRegExp.Lexical makeNamedRegExpLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.NamedRegExp.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.NamedBackslash.Ambiguity makeNamedBackslashAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.NamedBackslash> alternatives) {
		return new org.rascalmpl.semantics.dynamic.NamedBackslash.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.NamedBackslash.Lexical makeNamedBackslashLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.NamedBackslash.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.Expression.Map makeExpressionMap(INode node,
			java.util.List<org.rascalmpl.ast.Mapping> mappings) {
		return new org.rascalmpl.semantics.dynamic.Expression.Map(node,
				mappings);
	}

	public org.rascalmpl.ast.Expression.Tuple makeExpressionTuple(INode node,
			java.util.List<org.rascalmpl.ast.Expression> elements) {
		return new org.rascalmpl.semantics.dynamic.Expression.Tuple(node,
				elements);
	}

	public org.rascalmpl.ast.Expression.Set makeExpressionSet(INode node,
			java.util.List<org.rascalmpl.ast.Expression> elements) {
		return new org.rascalmpl.semantics.dynamic.Expression.Set(node,
				elements);
	}

	public org.rascalmpl.ast.Expression.List makeExpressionList(INode node,
			java.util.List<org.rascalmpl.ast.Expression> elements) {
		return new org.rascalmpl.semantics.dynamic.Expression.List(node,
				elements);
	}

	public org.rascalmpl.ast.Expression.QualifiedName makeExpressionQualifiedName(
			INode node, org.rascalmpl.ast.QualifiedName qualifiedName) {
		return new org.rascalmpl.semantics.dynamic.Expression.QualifiedName(
				node, qualifiedName);
	}

	public org.rascalmpl.ast.Expression.Literal makeExpressionLiteral(
			INode node, org.rascalmpl.ast.Literal literal) {
		return new org.rascalmpl.semantics.dynamic.Expression.Literal(node,
				literal);
	}

	public org.rascalmpl.ast.Expression.Lexical makeExpressionLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.Expression.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.Expression.Or makeExpressionOr(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Or(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.And makeExpressionAnd(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.And(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.Expression.Equivalence makeExpressionEquivalence(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Equivalence(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.Implication makeExpressionImplication(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Implication(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.NonEquals makeExpressionNonEquals(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.NonEquals(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.GreaterThanOrEq makeExpressionGreaterThanOrEq(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.GreaterThanOrEq(
				node, lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.GreaterThan makeExpressionGreaterThan(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.GreaterThan(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.LessThanOrEq makeExpressionLessThanOrEq(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.LessThanOrEq(
				node, lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.LessThan makeExpressionLessThan(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.LessThan(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.In makeExpressionIn(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.In(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.NotIn makeExpressionNotIn(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.NotIn(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.Expression.Subtraction makeExpressionSubtraction(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Subtraction(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.Addition makeExpressionAddition(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Addition(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.Intersection makeExpressionIntersection(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Intersection(
				node, lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.Modulo makeExpressionModulo(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Modulo(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.Expression.Division makeExpressionDivision(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Division(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.Join makeExpressionJoin(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Join(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.Expression.Product makeExpressionProduct(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Product(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.Composition makeExpressionComposition(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Composition(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.SetAnnotation makeExpressionSetAnnotation(
			INode node, org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.Name name, org.rascalmpl.ast.Expression value) {
		return new org.rascalmpl.semantics.dynamic.Expression.SetAnnotation(
				node, expression, name, value);
	}

	public org.rascalmpl.ast.Expression.GetAnnotation makeExpressionGetAnnotation(
			INode node, org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Expression.GetAnnotation(
				node, expression, name);
	}

	public org.rascalmpl.ast.Expression.TransitiveClosure makeExpressionTransitiveClosure(
			INode node, org.rascalmpl.ast.Expression argument) {
		return new org.rascalmpl.semantics.dynamic.Expression.TransitiveClosure(
				node, argument);
	}

	public org.rascalmpl.ast.Expression.TransitiveReflexiveClosure makeExpressionTransitiveReflexiveClosure(
			INode node, org.rascalmpl.ast.Expression argument) {
		return new org.rascalmpl.semantics.dynamic.Expression.TransitiveReflexiveClosure(
				node, argument);
	}

	public org.rascalmpl.ast.Expression.Negative makeExpressionNegative(
			INode node, org.rascalmpl.ast.Expression argument) {
		return new org.rascalmpl.semantics.dynamic.Expression.Negative(node,
				argument);
	}

	public org.rascalmpl.ast.Expression.Negation makeExpressionNegation(
			INode node, org.rascalmpl.ast.Expression argument) {
		return new org.rascalmpl.semantics.dynamic.Expression.Negation(node,
				argument);
	}

	public org.rascalmpl.ast.Expression.IsDefined makeExpressionIsDefined(
			INode node, org.rascalmpl.ast.Expression argument) {
		return new org.rascalmpl.semantics.dynamic.Expression.IsDefined(node,
				argument);
	}

	public org.rascalmpl.ast.Expression.Subscript makeExpressionSubscript(
			INode node, org.rascalmpl.ast.Expression expression,
			java.util.List<org.rascalmpl.ast.Expression> subscripts) {
		return new org.rascalmpl.semantics.dynamic.Expression.Subscript(node,
				expression, subscripts);
	}

	public org.rascalmpl.ast.Expression.FieldProject makeExpressionFieldProject(
			INode node, org.rascalmpl.ast.Expression expression,
			java.util.List<org.rascalmpl.ast.Field> fields) {
		return new org.rascalmpl.semantics.dynamic.Expression.FieldProject(
				node, expression, fields);
	}

	public org.rascalmpl.ast.Expression.FieldAccess makeExpressionFieldAccess(
			INode node, org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.Name field) {
		return new org.rascalmpl.semantics.dynamic.Expression.FieldAccess(node,
				expression, field);
	}

	public org.rascalmpl.ast.Expression.FieldUpdate makeExpressionFieldUpdate(
			INode node, org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.Name key, org.rascalmpl.ast.Expression replacement) {
		return new org.rascalmpl.semantics.dynamic.Expression.FieldUpdate(node,
				expression, key, replacement);
	}

	public org.rascalmpl.ast.Expression.ReifiedType makeExpressionReifiedType(
			INode node, org.rascalmpl.ast.BasicType basicType,
			java.util.List<org.rascalmpl.ast.Expression> arguments) {
		return new org.rascalmpl.semantics.dynamic.Expression.ReifiedType(node,
				basicType, arguments);
	}

	public org.rascalmpl.ast.Expression.ReifyType makeExpressionReifyType(
			INode node, org.rascalmpl.ast.Type type) {
		return new org.rascalmpl.semantics.dynamic.Expression.ReifyType(node,
				type);
	}

	public org.rascalmpl.ast.Expression.StepRange makeExpressionStepRange(
			INode node, org.rascalmpl.ast.Expression first,
			org.rascalmpl.ast.Expression second,
			org.rascalmpl.ast.Expression last) {
		return new org.rascalmpl.semantics.dynamic.Expression.StepRange(node,
				first, second, last);
	}

	public org.rascalmpl.ast.Expression.Range makeExpressionRange(INode node,
			org.rascalmpl.ast.Expression first,
			org.rascalmpl.ast.Expression last) {
		return new org.rascalmpl.semantics.dynamic.Expression.Range(node,
				first, last);
	}

	public org.rascalmpl.ast.Expression.Bracket makeExpressionBracket(
			INode node, org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Expression.Bracket(node,
				expression);
	}

	public org.rascalmpl.ast.Expression.VoidClosure makeExpressionVoidClosure(
			INode node, org.rascalmpl.ast.Parameters parameters,
			java.util.List<org.rascalmpl.ast.Statement> statements) {
		return new org.rascalmpl.semantics.dynamic.Expression.VoidClosure(node,
				parameters, statements);
	}

	public org.rascalmpl.ast.Expression.Closure makeExpressionClosure(
			INode node, org.rascalmpl.ast.Type type,
			org.rascalmpl.ast.Parameters parameters,
			java.util.List<org.rascalmpl.ast.Statement> statements) {
		return new org.rascalmpl.semantics.dynamic.Expression.Closure(node,
				type, parameters, statements);
	}

	public org.rascalmpl.ast.Expression.Visit makeExpressionVisit(INode node,
			org.rascalmpl.ast.Label label, org.rascalmpl.ast.Visit visit) {
		return new org.rascalmpl.semantics.dynamic.Expression.Visit(node,
				label, visit);
	}

	public org.rascalmpl.ast.Expression.Any makeExpressionAny(INode node,
			java.util.List<org.rascalmpl.ast.Expression> generators) {
		return new org.rascalmpl.semantics.dynamic.Expression.Any(node,
				generators);
	}

	public org.rascalmpl.ast.Expression.All makeExpressionAll(INode node,
			java.util.List<org.rascalmpl.ast.Expression> generators) {
		return new org.rascalmpl.semantics.dynamic.Expression.All(node,
				generators);
	}

	public org.rascalmpl.ast.Expression.It makeExpressionIt(INode node) {
		return new org.rascalmpl.semantics.dynamic.Expression.It(node);
	}

	public org.rascalmpl.ast.Expression.Reducer makeExpressionReducer(
			INode node, org.rascalmpl.ast.Expression init,
			org.rascalmpl.ast.Expression result,
			java.util.List<org.rascalmpl.ast.Expression> generators) {
		return new org.rascalmpl.semantics.dynamic.Expression.Reducer(node,
				init, result, generators);
	}

	public org.rascalmpl.ast.Expression.Comprehension makeExpressionComprehension(
			INode node, org.rascalmpl.ast.Comprehension comprehension) {
		return new org.rascalmpl.semantics.dynamic.Expression.Comprehension(
				node, comprehension);
	}

	public org.rascalmpl.ast.Expression.Equals makeExpressionEquals(INode node,
			org.rascalmpl.ast.Expression lhs, org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.Equals(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.Expression.Enumerator makeExpressionEnumerator(
			INode node, org.rascalmpl.ast.Expression pattern,
			org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Expression.Enumerator(node,
				pattern, expression);
	}

	public org.rascalmpl.ast.Expression.NoMatch makeExpressionNoMatch(
			INode node, org.rascalmpl.ast.Expression pattern,
			org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Expression.NoMatch(node,
				pattern, expression);
	}

	public org.rascalmpl.ast.Expression.Match makeExpressionMatch(INode node,
			org.rascalmpl.ast.Expression pattern,
			org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Expression.Match(node,
				pattern, expression);
	}

	public org.rascalmpl.ast.Expression.IfDefinedOtherwise makeExpressionIfDefinedOtherwise(
			INode node, org.rascalmpl.ast.Expression lhs,
			org.rascalmpl.ast.Expression rhs) {
		return new org.rascalmpl.semantics.dynamic.Expression.IfDefinedOtherwise(
				node, lhs, rhs);
	}

	public org.rascalmpl.ast.Expression.IfThenElse makeExpressionIfThenElse(
			INode node, org.rascalmpl.ast.Expression condition,
			org.rascalmpl.ast.Expression thenExp,
			org.rascalmpl.ast.Expression elseExp) {
		return new org.rascalmpl.semantics.dynamic.Expression.IfThenElse(node,
				condition, thenExp, elseExp);
	}

	public org.rascalmpl.ast.Expression.NonEmptyBlock makeExpressionNonEmptyBlock(
			INode node, java.util.List<org.rascalmpl.ast.Statement> statements) {
		return new org.rascalmpl.semantics.dynamic.Expression.NonEmptyBlock(
				node, statements);
	}

	public org.rascalmpl.ast.Expression.Anti makeExpressionAnti(INode node,
			org.rascalmpl.ast.Expression pattern) {
		return new org.rascalmpl.semantics.dynamic.Expression.Anti(node,
				pattern);
	}

	public org.rascalmpl.ast.Expression.Guarded makeExpressionGuarded(
			INode node, org.rascalmpl.ast.Type type,
			org.rascalmpl.ast.Expression pattern) {
		return new org.rascalmpl.semantics.dynamic.Expression.Guarded(node,
				type, pattern);
	}

	public org.rascalmpl.ast.Expression.TypedVariableBecomes makeExpressionTypedVariableBecomes(
			INode node, org.rascalmpl.ast.Type type,
			org.rascalmpl.ast.Name name, org.rascalmpl.ast.Expression pattern) {
		return new org.rascalmpl.semantics.dynamic.Expression.TypedVariableBecomes(
				node, type, name, pattern);
	}

	public org.rascalmpl.ast.Expression.VariableBecomes makeExpressionVariableBecomes(
			INode node, org.rascalmpl.ast.Name name,
			org.rascalmpl.ast.Expression pattern) {
		return new org.rascalmpl.semantics.dynamic.Expression.VariableBecomes(
				node, name, pattern);
	}

	public org.rascalmpl.ast.Expression.Descendant makeExpressionDescendant(
			INode node, org.rascalmpl.ast.Expression pattern) {
		return new org.rascalmpl.semantics.dynamic.Expression.Descendant(node,
				pattern);
	}

	public org.rascalmpl.ast.Expression.CallOrTree makeExpressionCallOrTree(
			INode node, org.rascalmpl.ast.Expression expression,
			java.util.List<org.rascalmpl.ast.Expression> arguments) {
		return new org.rascalmpl.semantics.dynamic.Expression.CallOrTree(node,
				expression, arguments);
	}

	public org.rascalmpl.ast.Expression.MultiVariable makeExpressionMultiVariable(
			INode node, org.rascalmpl.ast.QualifiedName qualifiedName) {
		return new org.rascalmpl.semantics.dynamic.Expression.MultiVariable(
				node, qualifiedName);
	}

	public org.rascalmpl.ast.Expression.Ambiguity makeExpressionAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Expression> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Expression.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Expression.TypedVariable makeExpressionTypedVariable(
			INode node, org.rascalmpl.ast.Type type, org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Expression.TypedVariable(
				node, type, name);
	}

	public org.rascalmpl.ast.Nonterminal.Ambiguity makeNonterminalAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Nonterminal> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Nonterminal.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Nonterminal.Lexical makeNonterminalLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.Nonterminal.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.NonterminalLabel.Ambiguity makeNonterminalLabelAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.NonterminalLabel> alternatives) {
		return new org.rascalmpl.semantics.dynamic.NonterminalLabel.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.NonterminalLabel.Lexical makeNonterminalLabelLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.NonterminalLabel.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.ParameterizedNonterminal.Ambiguity makeParameterizedNonterminalAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ParameterizedNonterminal> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ParameterizedNonterminal.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.ParameterizedNonterminal.Lexical makeParameterizedNonterminalLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.ParameterizedNonterminal.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.Sym.CaseInsensitiveLiteral makeSymCaseInsensitiveLiteral(
			INode node, org.rascalmpl.ast.CaseInsensitiveStringConstant cistring) {
		return new org.rascalmpl.semantics.dynamic.Sym.CaseInsensitiveLiteral(
				node, cistring);
	}

	public org.rascalmpl.ast.Sym.Literal makeSymLiteral(INode node,
			org.rascalmpl.ast.StringConstant string) {
		return new org.rascalmpl.semantics.dynamic.Sym.Literal(node, string);
	}

	public org.rascalmpl.ast.Sym.CharacterClass makeSymCharacterClass(
			INode node, org.rascalmpl.ast.Class charClass) {
		return new org.rascalmpl.semantics.dynamic.Sym.CharacterClass(node,
				charClass);
	}

	public org.rascalmpl.ast.Sym.Column makeSymColumn(INode node,
			org.rascalmpl.ast.IntegerLiteral column) {
		return new org.rascalmpl.semantics.dynamic.Sym.Column(node, column);
	}

	public org.rascalmpl.ast.Sym.EndOfLine makeSymEndOfLine(INode node) {
		return new org.rascalmpl.semantics.dynamic.Sym.EndOfLine(node);
	}

	public org.rascalmpl.ast.Sym.StartOfLine makeSymStartOfLine(INode node) {
		return new org.rascalmpl.semantics.dynamic.Sym.StartOfLine(node);
	}

	public org.rascalmpl.ast.Sym.NonEagerIterStarSep makeSymNonEagerIterStarSep(
			INode node, org.rascalmpl.ast.Sym symbol,
			org.rascalmpl.ast.StringConstant sep) {
		return new org.rascalmpl.semantics.dynamic.Sym.NonEagerIterStarSep(
				node, symbol, sep);
	}

	public org.rascalmpl.ast.Sym.NonEagerIterSep makeSymNonEagerIterSep(
			INode node, org.rascalmpl.ast.Sym symbol,
			org.rascalmpl.ast.StringConstant sep) {
		return new org.rascalmpl.semantics.dynamic.Sym.NonEagerIterSep(node,
				symbol, sep);
	}

	public org.rascalmpl.ast.Sym.IterStarSep makeSymIterStarSep(INode node,
			org.rascalmpl.ast.Sym symbol, org.rascalmpl.ast.StringConstant sep) {
		return new org.rascalmpl.semantics.dynamic.Sym.IterStarSep(node,
				symbol, sep);
	}

	public org.rascalmpl.ast.Sym.IterSep makeSymIterSep(INode node,
			org.rascalmpl.ast.Sym symbol, org.rascalmpl.ast.StringConstant sep) {
		return new org.rascalmpl.semantics.dynamic.Sym.IterSep(node, symbol,
				sep);
	}

	public org.rascalmpl.ast.Sym.IterStar makeSymIterStar(INode node,
			org.rascalmpl.ast.Sym symbol) {
		return new org.rascalmpl.semantics.dynamic.Sym.IterStar(node, symbol);
	}

	public org.rascalmpl.ast.Sym.Iter makeSymIter(INode node,
			org.rascalmpl.ast.Sym symbol) {
		return new org.rascalmpl.semantics.dynamic.Sym.Iter(node, symbol);
	}

	public org.rascalmpl.ast.Sym.Optional makeSymOptional(INode node,
			org.rascalmpl.ast.Sym symbol) {
		return new org.rascalmpl.semantics.dynamic.Sym.Optional(node, symbol);
	}

	public org.rascalmpl.ast.Sym.Labeled makeSymLabeled(INode node,
			org.rascalmpl.ast.Sym symbol,
			org.rascalmpl.ast.NonterminalLabel label) {
		return new org.rascalmpl.semantics.dynamic.Sym.Labeled(node, symbol,
				label);
	}

	public org.rascalmpl.ast.Sym.Nonterminal makeSymNonterminal(INode node,
			org.rascalmpl.ast.Nonterminal nonterminal) {
		return new org.rascalmpl.semantics.dynamic.Sym.Nonterminal(node,
				nonterminal);
	}

	public org.rascalmpl.ast.Sym.Parameter makeSymParameter(INode node,
			org.rascalmpl.ast.Nonterminal nonTerminal) {
		return new org.rascalmpl.semantics.dynamic.Sym.Parameter(node,
				nonTerminal);
	}

	public org.rascalmpl.ast.Sym.Ambiguity makeSymAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Sym> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Sym.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Sym.Parametrized makeSymParametrized(INode node,
			org.rascalmpl.ast.ParameterizedNonterminal pnonterminal,
			java.util.List<org.rascalmpl.ast.Sym> parameters) {
		return new org.rascalmpl.semantics.dynamic.Sym.Parametrized(node,
				pnonterminal, parameters);
	}

	public org.rascalmpl.ast.Range.FromTo makeRangeFromTo(INode node,
			org.rascalmpl.ast.Char start, org.rascalmpl.ast.Char end) {
		return new org.rascalmpl.semantics.dynamic.Range.FromTo(node, start,
				end);
	}

	public org.rascalmpl.ast.Range.Ambiguity makeRangeAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Range> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Range.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Range.Character makeRangeCharacter(INode node,
			org.rascalmpl.ast.Char character) {
		return new org.rascalmpl.semantics.dynamic.Range.Character(node,
				character);
	}

	public org.rascalmpl.ast.Class.Union makeClassUnion(INode node,
			org.rascalmpl.ast.Class lhs, org.rascalmpl.ast.Class rhs) {
		return new org.rascalmpl.semantics.dynamic.Class.Union(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Class.Intersection makeClassIntersection(
			INode node, org.rascalmpl.ast.Class lhs, org.rascalmpl.ast.Class rhs) {
		return new org.rascalmpl.semantics.dynamic.Class.Intersection(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Class.Difference makeClassDifference(INode node,
			org.rascalmpl.ast.Class lhs, org.rascalmpl.ast.Class rhs) {
		return new org.rascalmpl.semantics.dynamic.Class.Difference(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.Class.Complement makeClassComplement(INode node,
			org.rascalmpl.ast.Class charClass) {
		return new org.rascalmpl.semantics.dynamic.Class.Complement(node,
				charClass);
	}

	public org.rascalmpl.ast.Class.Bracket makeClassBracket(INode node,
			org.rascalmpl.ast.Class charclass) {
		return new org.rascalmpl.semantics.dynamic.Class.Bracket(node,
				charclass);
	}

	public org.rascalmpl.ast.Class.Ambiguity makeClassAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Class> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Class.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Class.SimpleCharclass makeClassSimpleCharclass(
			INode node, java.util.List<org.rascalmpl.ast.Range> ranges) {
		return new org.rascalmpl.semantics.dynamic.Class.SimpleCharclass(node,
				ranges);
	}

	public org.rascalmpl.ast.Char.Ambiguity makeCharAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Char> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Char.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Char.Lexical makeCharLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Char.Lexical(node, string);
	}

	public org.rascalmpl.ast.Command.Import makeCommandImport(INode node,
			org.rascalmpl.ast.Import imported) {
		return new org.rascalmpl.semantics.dynamic.Command.Import(node,
				imported);
	}

	public org.rascalmpl.ast.Command.Declaration makeCommandDeclaration(
			INode node, org.rascalmpl.ast.Declaration declaration) {
		return new org.rascalmpl.semantics.dynamic.Command.Declaration(node,
				declaration);
	}

	public org.rascalmpl.ast.Command.Expression makeCommandExpression(
			INode node, org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Command.Expression(node,
				expression);
	}

	public org.rascalmpl.ast.Command.Statement makeCommandStatement(INode node,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Command.Statement(node,
				statement);
	}

	public org.rascalmpl.ast.Command.Ambiguity makeCommandAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Command> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Command.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Command.Shell makeCommandShell(INode node,
			org.rascalmpl.ast.ShellCommand command) {
		return new org.rascalmpl.semantics.dynamic.Command.Shell(node, command);
	}

	public org.rascalmpl.ast.ShellCommand.SetOption makeShellCommandSetOption(
			INode node, org.rascalmpl.ast.QualifiedName name,
			org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.SetOption(node,
				name, expression);
	}

	public org.rascalmpl.ast.ShellCommand.History makeShellCommandHistory(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.History(node);
	}

	public org.rascalmpl.ast.ShellCommand.Undeclare makeShellCommandUndeclare(
			INode node, org.rascalmpl.ast.QualifiedName name) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Undeclare(node,
				name);
	}

	public org.rascalmpl.ast.ShellCommand.Unimport makeShellCommandUnimport(
			INode node, org.rascalmpl.ast.QualifiedName name) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Unimport(node,
				name);
	}

	public org.rascalmpl.ast.ShellCommand.Test makeShellCommandTest(INode node) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Test(node);
	}

	public org.rascalmpl.ast.ShellCommand.ListDeclarations makeShellCommandListDeclarations(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.ListDeclarations(
				node);
	}

	public org.rascalmpl.ast.ShellCommand.ListModules makeShellCommandListModules(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.ListModules(
				node);
	}

	public org.rascalmpl.ast.ShellCommand.Edit makeShellCommandEdit(INode node,
			org.rascalmpl.ast.QualifiedName name) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Edit(node, name);
	}

	public org.rascalmpl.ast.ShellCommand.Quit makeShellCommandQuit(INode node) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Quit(node);
	}

	public org.rascalmpl.ast.ShellCommand.Ambiguity makeShellCommandAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ShellCommand> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.ShellCommand.Help makeShellCommandHelp(INode node) {
		return new org.rascalmpl.semantics.dynamic.ShellCommand.Help(node);
	}

	public org.rascalmpl.ast.RascalReservedKeywords.Ambiguity makeRascalReservedKeywordsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.RascalReservedKeywords> alternatives) {
		return new org.rascalmpl.semantics.dynamic.RascalReservedKeywords.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.RascalReservedKeywords.Lexical makeRascalReservedKeywordsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.RascalReservedKeywords.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.StrChar.Lexical makeStrCharLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.StrChar.Lexical(node, string);
	}

	public org.rascalmpl.ast.StrChar.Ambiguity makeStrCharAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.StrChar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StrChar.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.StrChar.newline makeStrCharnewline(INode node) {
		return new org.rascalmpl.semantics.dynamic.StrChar.newline(node);
	}

	public org.rascalmpl.ast.StrCon.Ambiguity makeStrConAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.StrCon> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StrCon.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.StrCon.Lexical makeStrConLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.StrCon.Lexical(node, string);
	}

	public org.rascalmpl.ast.SingleQuotedStrChar.Ambiguity makeSingleQuotedStrCharAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.SingleQuotedStrChar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.SingleQuotedStrChar.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.SingleQuotedStrChar.Lexical makeSingleQuotedStrCharLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.SingleQuotedStrChar.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.SingleQuotedStrCon.Ambiguity makeSingleQuotedStrConAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.SingleQuotedStrCon> alternatives) {
		return new org.rascalmpl.semantics.dynamic.SingleQuotedStrCon.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.SingleQuotedStrCon.Lexical makeSingleQuotedStrConLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.SingleQuotedStrCon.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.Symbol.Sort makeSymbolSort(INode node,
			org.rascalmpl.ast.QualifiedName name) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Sort(node, name);
	}

	public org.rascalmpl.ast.Symbol.CaseInsensitiveLiteral makeSymbolCaseInsensitiveLiteral(
			INode node, org.rascalmpl.ast.SingleQuotedStrCon singelQuotedString) {
		return new org.rascalmpl.semantics.dynamic.Symbol.CaseInsensitiveLiteral(
				node, singelQuotedString);
	}

	public org.rascalmpl.ast.Symbol.Literal makeSymbolLiteral(INode node,
			org.rascalmpl.ast.StrCon string) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Literal(node, string);
	}

	public org.rascalmpl.ast.Symbol.CharacterClass makeSymbolCharacterClass(
			INode node, org.rascalmpl.ast.CharClass charClass) {
		return new org.rascalmpl.semantics.dynamic.Symbol.CharacterClass(node,
				charClass);
	}

	public org.rascalmpl.ast.Symbol.Alternative makeSymbolAlternative(
			INode node, org.rascalmpl.ast.Symbol lhs,
			org.rascalmpl.ast.Symbol rhs) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Alternative(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.Symbol.IterStarSep makeSymbolIterStarSep(
			INode node, org.rascalmpl.ast.Symbol symbol,
			org.rascalmpl.ast.StrCon sep) {
		return new org.rascalmpl.semantics.dynamic.Symbol.IterStarSep(node,
				symbol, sep);
	}

	public org.rascalmpl.ast.Symbol.IterSep makeSymbolIterSep(INode node,
			org.rascalmpl.ast.Symbol symbol, org.rascalmpl.ast.StrCon sep) {
		return new org.rascalmpl.semantics.dynamic.Symbol.IterSep(node, symbol,
				sep);
	}

	public org.rascalmpl.ast.Symbol.IterStar makeSymbolIterStar(INode node,
			org.rascalmpl.ast.Symbol symbol) {
		return new org.rascalmpl.semantics.dynamic.Symbol.IterStar(node, symbol);
	}

	public org.rascalmpl.ast.Symbol.Iter makeSymbolIter(INode node,
			org.rascalmpl.ast.Symbol symbol) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Iter(node, symbol);
	}

	public org.rascalmpl.ast.Symbol.Optional makeSymbolOptional(INode node,
			org.rascalmpl.ast.Symbol symbol) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Optional(node, symbol);
	}

	public org.rascalmpl.ast.Symbol.Sequence makeSymbolSequence(INode node,
			org.rascalmpl.ast.Symbol head,
			java.util.List<org.rascalmpl.ast.Symbol> tail) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Sequence(node, head,
				tail);
	}

	public org.rascalmpl.ast.Symbol.Ambiguity makeSymbolAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Symbol> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Symbol.Empty makeSymbolEmpty(INode node) {
		return new org.rascalmpl.semantics.dynamic.Symbol.Empty(node);
	}

	public org.rascalmpl.ast.Type.Bracket makeTypeBracket(INode node,
			org.rascalmpl.ast.Type type) {
		return new org.rascalmpl.semantics.dynamic.Type.Bracket(node, type);
	}

	public org.rascalmpl.ast.Type.Selector makeTypeSelector(INode node,
			org.rascalmpl.ast.DataTypeSelector selector) {
		return new org.rascalmpl.semantics.dynamic.Type.Selector(node, selector);
	}

	public org.rascalmpl.ast.Type.User makeTypeUser(INode node,
			org.rascalmpl.ast.UserType user) {
		return new org.rascalmpl.semantics.dynamic.Type.User(node, user);
	}

	public org.rascalmpl.ast.Type.Variable makeTypeVariable(INode node,
			org.rascalmpl.ast.TypeVar typeVar) {
		return new org.rascalmpl.semantics.dynamic.Type.Variable(node, typeVar);
	}

	public org.rascalmpl.ast.Type.Function makeTypeFunction(INode node,
			org.rascalmpl.ast.FunctionType function) {
		return new org.rascalmpl.semantics.dynamic.Type.Function(node, function);
	}

	public org.rascalmpl.ast.Type.Structured makeTypeStructured(INode node,
			org.rascalmpl.ast.StructuredType structured) {
		return new org.rascalmpl.semantics.dynamic.Type.Structured(node,
				structured);
	}

	public org.rascalmpl.ast.Type.Basic makeTypeBasic(INode node,
			org.rascalmpl.ast.BasicType basic) {
		return new org.rascalmpl.semantics.dynamic.Type.Basic(node, basic);
	}

	public org.rascalmpl.ast.Type.Ambiguity makeTypeAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Type> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Type.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Type.Symbol makeTypeSymbol(INode node,
			org.rascalmpl.ast.Symbol symbol) {
		return new org.rascalmpl.semantics.dynamic.Type.Symbol(node, symbol);
	}

	public org.rascalmpl.ast.CharRange.Range makeCharRangeRange(INode node,
			org.rascalmpl.ast.Character start, org.rascalmpl.ast.Character end) {
		return new org.rascalmpl.semantics.dynamic.CharRange.Range(node, start,
				end);
	}

	public org.rascalmpl.ast.CharRange.Ambiguity makeCharRangeAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.CharRange> alternatives) {
		return new org.rascalmpl.semantics.dynamic.CharRange.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.CharRange.Character makeCharRangeCharacter(
			INode node, org.rascalmpl.ast.Character character) {
		return new org.rascalmpl.semantics.dynamic.CharRange.Character(node,
				character);
	}

	public org.rascalmpl.ast.CharRanges.Bracket makeCharRangesBracket(
			INode node, org.rascalmpl.ast.CharRanges ranges) {
		return new org.rascalmpl.semantics.dynamic.CharRanges.Bracket(node,
				ranges);
	}

	public org.rascalmpl.ast.CharRanges.Concatenate makeCharRangesConcatenate(
			INode node, org.rascalmpl.ast.CharRanges lhs,
			org.rascalmpl.ast.CharRanges rhs) {
		return new org.rascalmpl.semantics.dynamic.CharRanges.Concatenate(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.CharRanges.Ambiguity makeCharRangesAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.CharRanges> alternatives) {
		return new org.rascalmpl.semantics.dynamic.CharRanges.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.CharRanges.Range makeCharRangesRange(INode node,
			org.rascalmpl.ast.CharRange range) {
		return new org.rascalmpl.semantics.dynamic.CharRanges.Range(node, range);
	}

	public org.rascalmpl.ast.OptCharRanges.Present makeOptCharRangesPresent(
			INode node, org.rascalmpl.ast.CharRanges ranges) {
		return new org.rascalmpl.semantics.dynamic.OptCharRanges.Present(node,
				ranges);
	}

	public org.rascalmpl.ast.OptCharRanges.Ambiguity makeOptCharRangesAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.OptCharRanges> alternatives) {
		return new org.rascalmpl.semantics.dynamic.OptCharRanges.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.OptCharRanges.Absent makeOptCharRangesAbsent(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.OptCharRanges.Absent(node);
	}

	public org.rascalmpl.ast.CharClass.Union makeCharClassUnion(INode node,
			org.rascalmpl.ast.CharClass lhs, org.rascalmpl.ast.CharClass rhs) {
		return new org.rascalmpl.semantics.dynamic.CharClass.Union(node, lhs,
				rhs);
	}

	public org.rascalmpl.ast.CharClass.Intersection makeCharClassIntersection(
			INode node, org.rascalmpl.ast.CharClass lhs,
			org.rascalmpl.ast.CharClass rhs) {
		return new org.rascalmpl.semantics.dynamic.CharClass.Intersection(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.CharClass.Difference makeCharClassDifference(
			INode node, org.rascalmpl.ast.CharClass lhs,
			org.rascalmpl.ast.CharClass rhs) {
		return new org.rascalmpl.semantics.dynamic.CharClass.Difference(node,
				lhs, rhs);
	}

	public org.rascalmpl.ast.CharClass.Complement makeCharClassComplement(
			INode node, org.rascalmpl.ast.CharClass charClass) {
		return new org.rascalmpl.semantics.dynamic.CharClass.Complement(node,
				charClass);
	}

	public org.rascalmpl.ast.CharClass.Bracket makeCharClassBracket(INode node,
			org.rascalmpl.ast.CharClass charClass) {
		return new org.rascalmpl.semantics.dynamic.CharClass.Bracket(node,
				charClass);
	}

	public org.rascalmpl.ast.CharClass.Ambiguity makeCharClassAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.CharClass> alternatives) {
		return new org.rascalmpl.semantics.dynamic.CharClass.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.CharClass.SimpleCharclass makeCharClassSimpleCharclass(
			INode node, org.rascalmpl.ast.OptCharRanges optionalCharRanges) {
		return new org.rascalmpl.semantics.dynamic.CharClass.SimpleCharclass(
				node, optionalCharRanges);
	}

	public org.rascalmpl.ast.NumChar.Ambiguity makeNumCharAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.NumChar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.NumChar.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.NumChar.Lexical makeNumCharLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.NumChar.Lexical(node, string);
	}

	public org.rascalmpl.ast.ShortChar.Ambiguity makeShortCharAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.ShortChar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ShortChar.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.ShortChar.Lexical makeShortCharLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.ShortChar.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.Character.Bottom makeCharacterBottom(INode node) {
		return new org.rascalmpl.semantics.dynamic.Character.Bottom(node);
	}

	public org.rascalmpl.ast.Character.EOF makeCharacterEOF(INode node) {
		return new org.rascalmpl.semantics.dynamic.Character.EOF(node);
	}

	public org.rascalmpl.ast.Character.Top makeCharacterTop(INode node) {
		return new org.rascalmpl.semantics.dynamic.Character.Top(node);
	}

	public org.rascalmpl.ast.Character.Short makeCharacterShort(INode node,
			org.rascalmpl.ast.ShortChar shortChar) {
		return new org.rascalmpl.semantics.dynamic.Character.Short(node,
				shortChar);
	}

	public org.rascalmpl.ast.Character.Ambiguity makeCharacterAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Character> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Character.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Character.Numeric makeCharacterNumeric(INode node,
			org.rascalmpl.ast.NumChar numChar) {
		return new org.rascalmpl.semantics.dynamic.Character.Numeric(node,
				numChar);
	}

	public org.rascalmpl.ast.Body.Anything makeBodyAnything(INode node,
			org.rascalmpl.ast.Marker marker, org.rascalmpl.ast.Rest rest) {
		return new org.rascalmpl.semantics.dynamic.Body.Anything(node, marker,
				rest);
	}

	public org.rascalmpl.ast.Body.Ambiguity makeBodyAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Body> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Body.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Body.Toplevels makeBodyToplevels(INode node,
			java.util.List<org.rascalmpl.ast.Toplevel> toplevels) {
		return new org.rascalmpl.semantics.dynamic.Body.Toplevels(node,
				toplevels);
	}

	public org.rascalmpl.ast.Mapping.Ambiguity makeMappingAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Mapping> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Mapping.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Mapping.Default makeMappingDefault(INode node,
			org.rascalmpl.ast.Expression from, org.rascalmpl.ast.Expression to) {
		return new org.rascalmpl.semantics.dynamic.Mapping.Default(node, from,
				to);
	}

	public org.rascalmpl.ast.Strategy.Innermost makeStrategyInnermost(INode node) {
		return new org.rascalmpl.semantics.dynamic.Strategy.Innermost(node);
	}

	public org.rascalmpl.ast.Strategy.Outermost makeStrategyOutermost(INode node) {
		return new org.rascalmpl.semantics.dynamic.Strategy.Outermost(node);
	}

	public org.rascalmpl.ast.Strategy.BottomUpBreak makeStrategyBottomUpBreak(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Strategy.BottomUpBreak(node);
	}

	public org.rascalmpl.ast.Strategy.BottomUp makeStrategyBottomUp(INode node) {
		return new org.rascalmpl.semantics.dynamic.Strategy.BottomUp(node);
	}

	public org.rascalmpl.ast.Strategy.TopDownBreak makeStrategyTopDownBreak(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Strategy.TopDownBreak(node);
	}

	public org.rascalmpl.ast.Strategy.Ambiguity makeStrategyAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Strategy> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Strategy.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Strategy.TopDown makeStrategyTopDown(INode node) {
		return new org.rascalmpl.semantics.dynamic.Strategy.TopDown(node);
	}

	public org.rascalmpl.ast.Comprehension.Map makeComprehensionMap(INode node,
			org.rascalmpl.ast.Expression from, org.rascalmpl.ast.Expression to,
			java.util.List<org.rascalmpl.ast.Expression> generators) {
		return new org.rascalmpl.semantics.dynamic.Comprehension.Map(node,
				from, to, generators);
	}

	public org.rascalmpl.ast.Comprehension.List makeComprehensionList(
			INode node, java.util.List<org.rascalmpl.ast.Expression> results,
			java.util.List<org.rascalmpl.ast.Expression> generators) {
		return new org.rascalmpl.semantics.dynamic.Comprehension.List(node,
				results, generators);
	}

	public org.rascalmpl.ast.Comprehension.Ambiguity makeComprehensionAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Comprehension> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Comprehension.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.Comprehension.Set makeComprehensionSet(INode node,
			java.util.List<org.rascalmpl.ast.Expression> results,
			java.util.List<org.rascalmpl.ast.Expression> generators) {
		return new org.rascalmpl.semantics.dynamic.Comprehension.Set(node,
				results, generators);
	}

	public org.rascalmpl.ast.Replacement.Conditional makeReplacementConditional(
			INode node, org.rascalmpl.ast.Expression replacementExpression,
			java.util.List<org.rascalmpl.ast.Expression> conditions) {
		return new org.rascalmpl.semantics.dynamic.Replacement.Conditional(
				node, replacementExpression, conditions);
	}

	public org.rascalmpl.ast.Replacement.Ambiguity makeReplacementAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Replacement> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Replacement.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Replacement.Unconditional makeReplacementUnconditional(
			INode node, org.rascalmpl.ast.Expression replacementExpression) {
		return new org.rascalmpl.semantics.dynamic.Replacement.Unconditional(
				node, replacementExpression);
	}

	public org.rascalmpl.ast.PatternWithAction.Arbitrary makePatternWithActionArbitrary(
			INode node, org.rascalmpl.ast.Expression pattern,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.PatternWithAction.Arbitrary(
				node, pattern, statement);
	}

	public org.rascalmpl.ast.PatternWithAction.Ambiguity makePatternWithActionAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PatternWithAction> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PatternWithAction.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.PatternWithAction.Replacing makePatternWithActionReplacing(
			INode node, org.rascalmpl.ast.Expression pattern,
			org.rascalmpl.ast.Replacement replacement) {
		return new org.rascalmpl.semantics.dynamic.PatternWithAction.Replacing(
				node, pattern, replacement);
	}

	public org.rascalmpl.ast.Case.Default makeCaseDefault(INode node,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Case.Default(node, statement);
	}

	public org.rascalmpl.ast.Case.Ambiguity makeCaseAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Case> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Case.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Case.PatternWithAction makeCasePatternWithAction(
			INode node, org.rascalmpl.ast.PatternWithAction patternWithAction) {
		return new org.rascalmpl.semantics.dynamic.Case.PatternWithAction(node,
				patternWithAction);
	}

	public org.rascalmpl.ast.Visit.GivenStrategy makeVisitGivenStrategy(
			INode node, org.rascalmpl.ast.Strategy strategy,
			org.rascalmpl.ast.Expression subject,
			java.util.List<org.rascalmpl.ast.Case> cases) {
		return new org.rascalmpl.semantics.dynamic.Visit.GivenStrategy(node,
				strategy, subject, cases);
	}

	public org.rascalmpl.ast.Visit.Ambiguity makeVisitAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Visit> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Visit.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Visit.DefaultStrategy makeVisitDefaultStrategy(
			INode node, org.rascalmpl.ast.Expression subject,
			java.util.List<org.rascalmpl.ast.Case> cases) {
		return new org.rascalmpl.semantics.dynamic.Visit.DefaultStrategy(node,
				subject, cases);
	}

	public org.rascalmpl.ast.Visibility.Default makeVisibilityDefault(INode node) {
		return new org.rascalmpl.semantics.dynamic.Visibility.Default(node);
	}

	public org.rascalmpl.ast.Visibility.Private makeVisibilityPrivate(INode node) {
		return new org.rascalmpl.semantics.dynamic.Visibility.Private(node);
	}

	public org.rascalmpl.ast.Visibility.Ambiguity makeVisibilityAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Visibility> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Visibility.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Visibility.Public makeVisibilityPublic(INode node) {
		return new org.rascalmpl.semantics.dynamic.Visibility.Public(node);
	}

	public org.rascalmpl.ast.Toplevel.Ambiguity makeToplevelAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Toplevel> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Toplevel.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Toplevel.GivenVisibility makeToplevelGivenVisibility(
			INode node, org.rascalmpl.ast.Declaration declaration) {
		return new org.rascalmpl.semantics.dynamic.Toplevel.GivenVisibility(
				node, declaration);
	}

	public org.rascalmpl.ast.Declaration.Tag makeDeclarationTag(INode node,
			org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.Kind kind, org.rascalmpl.ast.Name name,
			java.util.List<org.rascalmpl.ast.Type> types) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Tag(node, tags,
				visibility, kind, name, types);
	}

	public org.rascalmpl.ast.Declaration.Annotation makeDeclarationAnnotation(
			INode node, org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.Type annoType, org.rascalmpl.ast.Type onType,
			org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Annotation(node,
				tags, visibility, annoType, onType, name);
	}

	public org.rascalmpl.ast.Declaration.Rule makeDeclarationRule(INode node,
			org.rascalmpl.ast.Tags tags, org.rascalmpl.ast.Name name,
			org.rascalmpl.ast.PatternWithAction patternAction) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Rule(node, tags,
				name, patternAction);
	}

	public org.rascalmpl.ast.Declaration.Variable makeDeclarationVariable(
			INode node, org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.Type type,
			java.util.List<org.rascalmpl.ast.Variable> variables) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Variable(node,
				tags, visibility, type, variables);
	}

	public org.rascalmpl.ast.Declaration.Function makeDeclarationFunction(
			INode node,
			org.rascalmpl.ast.FunctionDeclaration functionDeclaration) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Function(node,
				functionDeclaration);
	}

	public org.rascalmpl.ast.Declaration.Test makeDeclarationTest(INode node,
			org.rascalmpl.ast.Test test) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Test(node, test);
	}

	public org.rascalmpl.ast.Declaration.DataAbstract makeDeclarationDataAbstract(
			INode node, org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.UserType user) {
		return new org.rascalmpl.semantics.dynamic.Declaration.DataAbstract(
				node, tags, visibility, user);
	}

	public org.rascalmpl.ast.Declaration.Data makeDeclarationData(INode node,
			org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.UserType user,
			java.util.List<org.rascalmpl.ast.Variant> variants) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Data(node, tags,
				visibility, user, variants);
	}

	public org.rascalmpl.ast.Declaration.Alias makeDeclarationAlias(INode node,
			org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.UserType user, org.rascalmpl.ast.Type base) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Alias(node,
				tags, visibility, user, base);
	}

	public org.rascalmpl.ast.Declaration.Ambiguity makeDeclarationAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Declaration> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Declaration.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Declaration.View makeDeclarationView(INode node,
			org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.Name view, org.rascalmpl.ast.Name superType,
			java.util.List<org.rascalmpl.ast.Alternative> alts) {
		return new org.rascalmpl.semantics.dynamic.Declaration.View(node, tags,
				visibility, view, superType, alts);
	}

	public org.rascalmpl.ast.Alternative.Ambiguity makeAlternativeAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Alternative> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Alternative.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Alternative.NamedType makeAlternativeNamedType(
			INode node, org.rascalmpl.ast.Name name, org.rascalmpl.ast.Type type) {
		return new org.rascalmpl.semantics.dynamic.Alternative.NamedType(node,
				name, type);
	}

	public org.rascalmpl.ast.Variant.Ambiguity makeVariantAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Variant> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Variant.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Variant.NAryConstructor makeVariantNAryConstructor(
			INode node, org.rascalmpl.ast.Name name,
			java.util.List<org.rascalmpl.ast.TypeArg> arguments) {
		return new org.rascalmpl.semantics.dynamic.Variant.NAryConstructor(
				node, name, arguments);
	}

	public org.rascalmpl.ast.Test.Labeled makeTestLabeled(INode node,
			org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.StringLiteral labeled) {
		return new org.rascalmpl.semantics.dynamic.Test.Labeled(node, tags,
				expression, labeled);
	}

	public org.rascalmpl.ast.Test.Ambiguity makeTestAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Test> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Test.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Test.Unlabeled makeTestUnlabeled(INode node,
			org.rascalmpl.ast.Tags tags, org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Test.Unlabeled(node, tags,
				expression);
	}

	public org.rascalmpl.ast.FunctionModifier.Ambiguity makeFunctionModifierAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.FunctionModifier> alternatives) {
		return new org.rascalmpl.semantics.dynamic.FunctionModifier.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.FunctionModifier.Java makeFunctionModifierJava(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.FunctionModifier.Java(node);
	}

	public org.rascalmpl.ast.FunctionModifiers.Ambiguity makeFunctionModifiersAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.FunctionModifiers> alternatives) {
		return new org.rascalmpl.semantics.dynamic.FunctionModifiers.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.FunctionModifiers.List makeFunctionModifiersList(
			INode node,
			java.util.List<org.rascalmpl.ast.FunctionModifier> modifiers) {
		return new org.rascalmpl.semantics.dynamic.FunctionModifiers.List(node,
				modifiers);
	}

	public org.rascalmpl.ast.Signature.WithThrows makeSignatureWithThrows(
			INode node, org.rascalmpl.ast.Type type,
			org.rascalmpl.ast.FunctionModifiers modifiers,
			org.rascalmpl.ast.Name name,
			org.rascalmpl.ast.Parameters parameters,
			java.util.List<org.rascalmpl.ast.Type> exceptions) {
		return new org.rascalmpl.semantics.dynamic.Signature.WithThrows(node,
				type, modifiers, name, parameters, exceptions);
	}

	public org.rascalmpl.ast.Signature.Ambiguity makeSignatureAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Signature> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Signature.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Signature.NoThrows makeSignatureNoThrows(
			INode node, org.rascalmpl.ast.Type type,
			org.rascalmpl.ast.FunctionModifiers modifiers,
			org.rascalmpl.ast.Name name, org.rascalmpl.ast.Parameters parameters) {
		return new org.rascalmpl.semantics.dynamic.Signature.NoThrows(node,
				type, modifiers, name, parameters);
	}

	public org.rascalmpl.ast.FunctionDeclaration.Abstract makeFunctionDeclarationAbstract(
			INode node, org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.Signature signature) {
		return new org.rascalmpl.semantics.dynamic.FunctionDeclaration.Abstract(
				node, tags, visibility, signature);
	}

	public org.rascalmpl.ast.FunctionDeclaration.Ambiguity makeFunctionDeclarationAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.FunctionDeclaration> alternatives) {
		return new org.rascalmpl.semantics.dynamic.FunctionDeclaration.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.FunctionDeclaration.Default makeFunctionDeclarationDefault(
			INode node, org.rascalmpl.ast.Tags tags,
			org.rascalmpl.ast.Visibility visibility,
			org.rascalmpl.ast.Signature signature,
			org.rascalmpl.ast.FunctionBody body) {
		return new org.rascalmpl.semantics.dynamic.FunctionDeclaration.Default(
				node, tags, visibility, signature, body);
	}

	public org.rascalmpl.ast.FunctionBody.Ambiguity makeFunctionBodyAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.FunctionBody> alternatives) {
		return new org.rascalmpl.semantics.dynamic.FunctionBody.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.FunctionBody.Default makeFunctionBodyDefault(
			INode node, java.util.List<org.rascalmpl.ast.Statement> statements) {
		return new org.rascalmpl.semantics.dynamic.FunctionBody.Default(node,
				statements);
	}

	public org.rascalmpl.ast.Variable.Initialized makeVariableInitialized(
			INode node, org.rascalmpl.ast.Name name,
			org.rascalmpl.ast.Expression initial) {
		return new org.rascalmpl.semantics.dynamic.Variable.Initialized(node,
				name, initial);
	}

	public org.rascalmpl.ast.Variable.Ambiguity makeVariableAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Variable> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Variable.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Variable.UnInitialized makeVariableUnInitialized(
			INode node, org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Variable.UnInitialized(node,
				name);
	}

	public org.rascalmpl.ast.Kind.All makeKindAll(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.All(node);
	}

	public org.rascalmpl.ast.Kind.Tag makeKindTag(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Tag(node);
	}

	public org.rascalmpl.ast.Kind.Anno makeKindAnno(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Anno(node);
	}

	public org.rascalmpl.ast.Kind.Alias makeKindAlias(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Alias(node);
	}

	public org.rascalmpl.ast.Kind.View makeKindView(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.View(node);
	}

	public org.rascalmpl.ast.Kind.Data makeKindData(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Data(node);
	}

	public org.rascalmpl.ast.Kind.Variable makeKindVariable(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Variable(node);
	}

	public org.rascalmpl.ast.Kind.Rule makeKindRule(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Rule(node);
	}

	public org.rascalmpl.ast.Kind.Function makeKindFunction(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Function(node);
	}

	public org.rascalmpl.ast.Kind.Ambiguity makeKindAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Kind> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Kind.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Kind.Module makeKindModule(INode node) {
		return new org.rascalmpl.semantics.dynamic.Kind.Module(node);
	}

	public org.rascalmpl.ast.TagString.Ambiguity makeTagStringAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.TagString> alternatives) {
		return new org.rascalmpl.semantics.dynamic.TagString.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.TagString.Lexical makeTagStringLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.TagString.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.TagChar.Ambiguity makeTagCharAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.TagChar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.TagChar.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.TagChar.Lexical makeTagCharLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.TagChar.Lexical(node, string);
	}

	public org.rascalmpl.ast.Tag.Empty makeTagEmpty(INode node,
			org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Tag.Empty(node, name);
	}

	public org.rascalmpl.ast.Tag.Expression makeTagExpression(INode node,
			org.rascalmpl.ast.Name name, org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Tag.Expression(node, name,
				expression);
	}

	public org.rascalmpl.ast.Tag.Ambiguity makeTagAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Tag> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Tag.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Tag.Default makeTagDefault(INode node,
			org.rascalmpl.ast.Name name, org.rascalmpl.ast.TagString contents) {
		return new org.rascalmpl.semantics.dynamic.Tag.Default(node, name,
				contents);
	}

	public org.rascalmpl.ast.Tags.Ambiguity makeTagsAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Tags> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Tags.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Tags.Default makeTagsDefault(INode node,
			java.util.List<org.rascalmpl.ast.Tag> tags) {
		return new org.rascalmpl.semantics.dynamic.Tags.Default(node, tags);
	}

	public org.rascalmpl.ast.Marker.Ambiguity makeMarkerAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Marker> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Marker.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Marker.Lexical makeMarkerLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Marker.Lexical(node, string);
	}

	public org.rascalmpl.ast.Rest.Ambiguity makeRestAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Rest> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Rest.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Rest.Lexical makeRestLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Rest.Lexical(node, string);
	}

	public org.rascalmpl.ast.Literal.DateTime makeLiteralDateTime(INode node,
			org.rascalmpl.ast.DateTimeLiteral dateTimeLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.DateTime(node,
				dateTimeLiteral);
	}

	public org.rascalmpl.ast.Literal.Location makeLiteralLocation(INode node,
			org.rascalmpl.ast.LocationLiteral locationLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.Location(node,
				locationLiteral);
	}

	public org.rascalmpl.ast.Literal.String makeLiteralString(INode node,
			org.rascalmpl.ast.StringLiteral stringLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.String(node,
				stringLiteral);
	}

	public org.rascalmpl.ast.Literal.Real makeLiteralReal(INode node,
			org.rascalmpl.ast.RealLiteral realLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.Real(node,
				realLiteral);
	}

	public org.rascalmpl.ast.Literal.Integer makeLiteralInteger(INode node,
			org.rascalmpl.ast.IntegerLiteral integerLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.Integer(node,
				integerLiteral);
	}

	public org.rascalmpl.ast.Literal.Boolean makeLiteralBoolean(INode node,
			org.rascalmpl.ast.BooleanLiteral booleanLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.Boolean(node,
				booleanLiteral);
	}

	public org.rascalmpl.ast.Literal.Ambiguity makeLiteralAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Literal> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Literal.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Literal.RegExp makeLiteralRegExp(INode node,
			org.rascalmpl.ast.RegExpLiteral regExpLiteral) {
		return new org.rascalmpl.semantics.dynamic.Literal.RegExp(node,
				regExpLiteral);
	}

	public org.rascalmpl.ast.Bound.Default makeBoundDefault(INode node,
			org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Bound.Default(node,
				expression);
	}

	public org.rascalmpl.ast.Bound.Ambiguity makeBoundAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Bound> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Bound.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Bound.Empty makeBoundEmpty(INode node) {
		return new org.rascalmpl.semantics.dynamic.Bound.Empty(node);
	}

	public org.rascalmpl.ast.Statement.GlobalDirective makeStatementGlobalDirective(
			INode node, org.rascalmpl.ast.Type type,
			java.util.List<org.rascalmpl.ast.QualifiedName> names) {
		return new org.rascalmpl.semantics.dynamic.Statement.GlobalDirective(
				node, type, names);
	}

	public org.rascalmpl.ast.Statement.NonEmptyBlock makeStatementNonEmptyBlock(
			INode node, org.rascalmpl.ast.Label label,
			java.util.List<org.rascalmpl.ast.Statement> statements) {
		return new org.rascalmpl.semantics.dynamic.Statement.NonEmptyBlock(
				node, label, statements);
	}

	public org.rascalmpl.ast.Statement.TryFinally makeStatementTryFinally(
			INode node, org.rascalmpl.ast.Statement body,
			java.util.List<org.rascalmpl.ast.Catch> handlers,
			org.rascalmpl.ast.Statement finallyBody) {
		return new org.rascalmpl.semantics.dynamic.Statement.TryFinally(node,
				body, handlers, finallyBody);
	}

	public org.rascalmpl.ast.Statement.Try makeStatementTry(INode node,
			org.rascalmpl.ast.Statement body,
			java.util.List<org.rascalmpl.ast.Catch> handlers) {
		return new org.rascalmpl.semantics.dynamic.Statement.Try(node, body,
				handlers);
	}

	public org.rascalmpl.ast.Statement.Continue makeStatementContinue(
			INode node, org.rascalmpl.ast.Target target) {
		return new org.rascalmpl.semantics.dynamic.Statement.Continue(node,
				target);
	}

	public org.rascalmpl.ast.Statement.Fail makeStatementFail(INode node,
			org.rascalmpl.ast.Target target) {
		return new org.rascalmpl.semantics.dynamic.Statement.Fail(node, target);
	}

	public org.rascalmpl.ast.Statement.Break makeStatementBreak(INode node,
			org.rascalmpl.ast.Target target) {
		return new org.rascalmpl.semantics.dynamic.Statement.Break(node, target);
	}

	public org.rascalmpl.ast.Statement.VariableDeclaration makeStatementVariableDeclaration(
			INode node, org.rascalmpl.ast.LocalVariableDeclaration declaration) {
		return new org.rascalmpl.semantics.dynamic.Statement.VariableDeclaration(
				node, declaration);
	}

	public org.rascalmpl.ast.Statement.FunctionDeclaration makeStatementFunctionDeclaration(
			INode node,
			org.rascalmpl.ast.FunctionDeclaration functionDeclaration) {
		return new org.rascalmpl.semantics.dynamic.Statement.FunctionDeclaration(
				node, functionDeclaration);
	}

	public org.rascalmpl.ast.Statement.Append makeStatementAppend(INode node,
			org.rascalmpl.ast.DataTarget dataTarget,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Statement.Append(node,
				dataTarget, statement);
	}

	public org.rascalmpl.ast.Statement.Insert makeStatementInsert(INode node,
			org.rascalmpl.ast.DataTarget dataTarget,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Statement.Insert(node,
				dataTarget, statement);
	}

	public org.rascalmpl.ast.Statement.Throw makeStatementThrow(INode node,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Statement.Throw(node,
				statement);
	}

	public org.rascalmpl.ast.Statement.Return makeStatementReturn(INode node,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Statement.Return(node,
				statement);
	}

	public org.rascalmpl.ast.Statement.AssertWithMessage makeStatementAssertWithMessage(
			INode node, org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.Expression message) {
		return new org.rascalmpl.semantics.dynamic.Statement.AssertWithMessage(
				node, expression, message);
	}

	public org.rascalmpl.ast.Statement.Assert makeStatementAssert(INode node,
			org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Statement.Assert(node,
				expression);
	}

	public org.rascalmpl.ast.Statement.Assignment makeStatementAssignment(
			INode node, org.rascalmpl.ast.Assignable assignable,
			org.rascalmpl.ast.Assignment operator,
			org.rascalmpl.ast.Statement statement) {
		return new org.rascalmpl.semantics.dynamic.Statement.Assignment(node,
				assignable, operator, statement);
	}

	public org.rascalmpl.ast.Statement.Expression makeStatementExpression(
			INode node, org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.Statement.Expression(node,
				expression);
	}

	public org.rascalmpl.ast.Statement.EmptyStatement makeStatementEmptyStatement(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Statement.EmptyStatement(
				node);
	}

	public org.rascalmpl.ast.Statement.Visit makeStatementVisit(INode node,
			org.rascalmpl.ast.Label label, org.rascalmpl.ast.Visit visit) {
		return new org.rascalmpl.semantics.dynamic.Statement.Visit(node, label,
				visit);
	}

	public org.rascalmpl.ast.Statement.Switch makeStatementSwitch(INode node,
			org.rascalmpl.ast.Label label,
			org.rascalmpl.ast.Expression expression,
			java.util.List<org.rascalmpl.ast.Case> cases) {
		return new org.rascalmpl.semantics.dynamic.Statement.Switch(node,
				label, expression, cases);
	}

	public org.rascalmpl.ast.Statement.IfThen makeStatementIfThen(INode node,
			org.rascalmpl.ast.Label label,
			java.util.List<org.rascalmpl.ast.Expression> conditions,
			org.rascalmpl.ast.Statement thenStatement,
			org.rascalmpl.ast.NoElseMayFollow noElseMayFollow) {
		return new org.rascalmpl.semantics.dynamic.Statement.IfThen(node,
				label, conditions, thenStatement, noElseMayFollow);
	}

	public org.rascalmpl.ast.Statement.IfThenElse makeStatementIfThenElse(
			INode node, org.rascalmpl.ast.Label label,
			java.util.List<org.rascalmpl.ast.Expression> conditions,
			org.rascalmpl.ast.Statement thenStatement,
			org.rascalmpl.ast.Statement elseStatement) {
		return new org.rascalmpl.semantics.dynamic.Statement.IfThenElse(node,
				label, conditions, thenStatement, elseStatement);
	}

	public org.rascalmpl.ast.Statement.DoWhile makeStatementDoWhile(INode node,
			org.rascalmpl.ast.Label label, org.rascalmpl.ast.Statement body,
			org.rascalmpl.ast.Expression condition) {
		return new org.rascalmpl.semantics.dynamic.Statement.DoWhile(node,
				label, body, condition);
	}

	public org.rascalmpl.ast.Statement.While makeStatementWhile(INode node,
			org.rascalmpl.ast.Label label,
			java.util.List<org.rascalmpl.ast.Expression> conditions,
			org.rascalmpl.ast.Statement body) {
		return new org.rascalmpl.semantics.dynamic.Statement.While(node, label,
				conditions, body);
	}

	public org.rascalmpl.ast.Statement.For makeStatementFor(INode node,
			org.rascalmpl.ast.Label label,
			java.util.List<org.rascalmpl.ast.Expression> generators,
			org.rascalmpl.ast.Statement body) {
		return new org.rascalmpl.semantics.dynamic.Statement.For(node, label,
				generators, body);
	}

	public org.rascalmpl.ast.Statement.Ambiguity makeStatementAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Statement> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Statement.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Statement.Solve makeStatementSolve(INode node,
			java.util.List<org.rascalmpl.ast.QualifiedName> variables,
			org.rascalmpl.ast.Bound bound, org.rascalmpl.ast.Statement body) {
		return new org.rascalmpl.semantics.dynamic.Statement.Solve(node,
				variables, bound, body);
	}

	public org.rascalmpl.ast.NoElseMayFollow.Ambiguity makeNoElseMayFollowAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.NoElseMayFollow> alternatives) {
		return new org.rascalmpl.semantics.dynamic.NoElseMayFollow.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.NoElseMayFollow.Default makeNoElseMayFollowDefault(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.NoElseMayFollow.Default(node);
	}

	public org.rascalmpl.ast.Assignable.Constructor makeAssignableConstructor(
			INode node, org.rascalmpl.ast.Name name,
			java.util.List<org.rascalmpl.ast.Assignable> arguments) {
		return new org.rascalmpl.semantics.dynamic.Assignable.Constructor(node,
				name, arguments);
	}

	public org.rascalmpl.ast.Assignable.Tuple makeAssignableTuple(INode node,
			java.util.List<org.rascalmpl.ast.Assignable> elements) {
		return new org.rascalmpl.semantics.dynamic.Assignable.Tuple(node,
				elements);
	}

	public org.rascalmpl.ast.Assignable.Annotation makeAssignableAnnotation(
			INode node, org.rascalmpl.ast.Assignable receiver,
			org.rascalmpl.ast.Name annotation) {
		return new org.rascalmpl.semantics.dynamic.Assignable.Annotation(node,
				receiver, annotation);
	}

	public org.rascalmpl.ast.Assignable.IfDefinedOrDefault makeAssignableIfDefinedOrDefault(
			INode node, org.rascalmpl.ast.Assignable receiver,
			org.rascalmpl.ast.Expression defaultExpression) {
		return new org.rascalmpl.semantics.dynamic.Assignable.IfDefinedOrDefault(
				node, receiver, defaultExpression);
	}

	public org.rascalmpl.ast.Assignable.FieldAccess makeAssignableFieldAccess(
			INode node, org.rascalmpl.ast.Assignable receiver,
			org.rascalmpl.ast.Name field) {
		return new org.rascalmpl.semantics.dynamic.Assignable.FieldAccess(node,
				receiver, field);
	}

	public org.rascalmpl.ast.Assignable.Subscript makeAssignableSubscript(
			INode node, org.rascalmpl.ast.Assignable receiver,
			org.rascalmpl.ast.Expression subscript) {
		return new org.rascalmpl.semantics.dynamic.Assignable.Subscript(node,
				receiver, subscript);
	}

	public org.rascalmpl.ast.Assignable.Ambiguity makeAssignableAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Assignable> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Assignable.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Assignable.Variable makeAssignableVariable(
			INode node, org.rascalmpl.ast.QualifiedName qualifiedName) {
		return new org.rascalmpl.semantics.dynamic.Assignable.Variable(node,
				qualifiedName);
	}

	public org.rascalmpl.ast.Assignment.IfDefined makeAssignmentIfDefined(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.IfDefined(node);
	}

	public org.rascalmpl.ast.Assignment.Intersection makeAssignmentIntersection(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Intersection(node);
	}

	public org.rascalmpl.ast.Assignment.Division makeAssignmentDivision(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Division(node);
	}

	public org.rascalmpl.ast.Assignment.Product makeAssignmentProduct(INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Product(node);
	}

	public org.rascalmpl.ast.Assignment.Subtraction makeAssignmentSubtraction(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Subtraction(node);
	}

	public org.rascalmpl.ast.Assignment.Addition makeAssignmentAddition(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Addition(node);
	}

	public org.rascalmpl.ast.Assignment.Ambiguity makeAssignmentAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Assignment> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Assignment.Default makeAssignmentDefault(INode node) {
		return new org.rascalmpl.semantics.dynamic.Assignment.Default(node);
	}

	public org.rascalmpl.ast.Label.Default makeLabelDefault(INode node,
			org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Label.Default(node, name);
	}

	public org.rascalmpl.ast.Label.Ambiguity makeLabelAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Label> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Label.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Label.Empty makeLabelEmpty(INode node) {
		return new org.rascalmpl.semantics.dynamic.Label.Empty(node);
	}

	public org.rascalmpl.ast.DataTarget.Labeled makeDataTargetLabeled(
			INode node, org.rascalmpl.ast.Name label) {
		return new org.rascalmpl.semantics.dynamic.DataTarget.Labeled(node,
				label);
	}

	public org.rascalmpl.ast.DataTarget.Ambiguity makeDataTargetAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.DataTarget> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DataTarget.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.DataTarget.Empty makeDataTargetEmpty(INode node) {
		return new org.rascalmpl.semantics.dynamic.DataTarget.Empty(node);
	}

	public org.rascalmpl.ast.Target.Labeled makeTargetLabeled(INode node,
			org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Target.Labeled(node, name);
	}

	public org.rascalmpl.ast.Target.Ambiguity makeTargetAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Target> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Target.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Target.Empty makeTargetEmpty(INode node) {
		return new org.rascalmpl.semantics.dynamic.Target.Empty(node);
	}

	public org.rascalmpl.ast.Catch.Binding makeCatchBinding(INode node,
			org.rascalmpl.ast.Expression pattern,
			org.rascalmpl.ast.Statement body) {
		return new org.rascalmpl.semantics.dynamic.Catch.Binding(node, pattern,
				body);
	}

	public org.rascalmpl.ast.Catch.Ambiguity makeCatchAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Catch> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Catch.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Catch.Default makeCatchDefault(INode node,
			org.rascalmpl.ast.Statement body) {
		return new org.rascalmpl.semantics.dynamic.Catch.Default(node, body);
	}

	public org.rascalmpl.ast.Declarator.Ambiguity makeDeclaratorAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Declarator> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Declarator.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Declarator.Default makeDeclaratorDefault(
			INode node, org.rascalmpl.ast.Type type,
			java.util.List<org.rascalmpl.ast.Variable> variables) {
		return new org.rascalmpl.semantics.dynamic.Declarator.Default(node,
				type, variables);
	}

	public org.rascalmpl.ast.LocalVariableDeclaration.Dynamic makeLocalVariableDeclarationDynamic(
			INode node, org.rascalmpl.ast.Declarator declarator) {
		return new org.rascalmpl.semantics.dynamic.LocalVariableDeclaration.Dynamic(
				node, declarator);
	}

	public org.rascalmpl.ast.LocalVariableDeclaration.Ambiguity makeLocalVariableDeclarationAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.LocalVariableDeclaration> alternatives) {
		return new org.rascalmpl.semantics.dynamic.LocalVariableDeclaration.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.LocalVariableDeclaration.Default makeLocalVariableDeclarationDefault(
			INode node, org.rascalmpl.ast.Declarator declarator) {
		return new org.rascalmpl.semantics.dynamic.LocalVariableDeclaration.Default(
				node, declarator);
	}

	public org.rascalmpl.ast.SyntaxDefinition.Layout makeSyntaxDefinitionLayout(
			INode node, org.rascalmpl.ast.Sym defined,
			org.rascalmpl.ast.Prod production) {
		return new org.rascalmpl.semantics.dynamic.SyntaxDefinition.Layout(
				node, defined, production);
	}

	public org.rascalmpl.ast.SyntaxDefinition.Ambiguity makeSyntaxDefinitionAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.SyntaxDefinition> alternatives) {
		return new org.rascalmpl.semantics.dynamic.SyntaxDefinition.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.SyntaxDefinition.Language makeSyntaxDefinitionLanguage(
			INode node, org.rascalmpl.ast.Start start,
			org.rascalmpl.ast.Sym defined, org.rascalmpl.ast.Prod production) {
		return new org.rascalmpl.semantics.dynamic.SyntaxDefinition.Language(
				node, start, defined, production);
	}

	public org.rascalmpl.ast.Start.Present makeStartPresent(INode node) {
		return new org.rascalmpl.semantics.dynamic.Start.Present(node);
	}

	public org.rascalmpl.ast.Start.Ambiguity makeStartAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Start> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Start.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Start.Absent makeStartAbsent(INode node) {
		return new org.rascalmpl.semantics.dynamic.Start.Absent(node);
	}

	public org.rascalmpl.ast.Prod.AssociativityGroup makeProdAssociativityGroup(
			INode node, org.rascalmpl.ast.Assoc associativity,
			org.rascalmpl.ast.Prod group) {
		return new org.rascalmpl.semantics.dynamic.Prod.AssociativityGroup(
				node, associativity, group);
	}

	public org.rascalmpl.ast.Prod.All makeProdAll(INode node,
			org.rascalmpl.ast.Prod lhs, org.rascalmpl.ast.Prod rhs) {
		return new org.rascalmpl.semantics.dynamic.Prod.All(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Prod.First makeProdFirst(INode node,
			org.rascalmpl.ast.Prod lhs, org.rascalmpl.ast.Prod rhs) {
		return new org.rascalmpl.semantics.dynamic.Prod.First(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Prod.Follow makeProdFollow(INode node,
			org.rascalmpl.ast.Prod lhs, org.rascalmpl.ast.Prod rhs) {
		return new org.rascalmpl.semantics.dynamic.Prod.Follow(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Prod.Reject makeProdReject(INode node,
			org.rascalmpl.ast.Prod lhs, org.rascalmpl.ast.Prod rhs) {
		return new org.rascalmpl.semantics.dynamic.Prod.Reject(node, lhs, rhs);
	}

	public org.rascalmpl.ast.Prod.Action makeProdAction(INode node,
			org.rascalmpl.ast.Prod prod, org.rascalmpl.ast.LanguageAction action) {
		return new org.rascalmpl.semantics.dynamic.Prod.Action(node, prod,
				action);
	}

	public org.rascalmpl.ast.Prod.Unlabeled makeProdUnlabeled(INode node,
			java.util.List<org.rascalmpl.ast.ProdModifier> modifiers,
			java.util.List<org.rascalmpl.ast.Sym> args) {
		return new org.rascalmpl.semantics.dynamic.Prod.Unlabeled(node,
				modifiers, args);
	}

	public org.rascalmpl.ast.Prod.Labeled makeProdLabeled(INode node,
			java.util.List<org.rascalmpl.ast.ProdModifier> modifiers,
			org.rascalmpl.ast.Name name,
			java.util.List<org.rascalmpl.ast.Sym> args) {
		return new org.rascalmpl.semantics.dynamic.Prod.Labeled(node,
				modifiers, name, args);
	}

	public org.rascalmpl.ast.Prod.Reference makeProdReference(INode node,
			org.rascalmpl.ast.Name referenced) {
		return new org.rascalmpl.semantics.dynamic.Prod.Reference(node,
				referenced);
	}

	public org.rascalmpl.ast.Prod.Ambiguity makeProdAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Prod> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Prod.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Prod.Others makeProdOthers(INode node) {
		return new org.rascalmpl.semantics.dynamic.Prod.Others(node);
	}

	public org.rascalmpl.ast.LanguageAction.Action makeLanguageActionAction(
			INode node, java.util.List<org.rascalmpl.ast.Statement> statements) {
		return new org.rascalmpl.semantics.dynamic.LanguageAction.Action(node,
				statements);
	}

	public org.rascalmpl.ast.LanguageAction.Ambiguity makeLanguageActionAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.LanguageAction> alternatives) {
		return new org.rascalmpl.semantics.dynamic.LanguageAction.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.LanguageAction.Build makeLanguageActionBuild(
			INode node, org.rascalmpl.ast.Expression expression) {
		return new org.rascalmpl.semantics.dynamic.LanguageAction.Build(node,
				expression);
	}

	public org.rascalmpl.ast.Assoc.Associative makeAssocAssociative(INode node) {
		return new org.rascalmpl.semantics.dynamic.Assoc.Associative(node);
	}

	public org.rascalmpl.ast.Assoc.NonAssociative makeAssocNonAssociative(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.Assoc.NonAssociative(node);
	}

	public org.rascalmpl.ast.Assoc.Right makeAssocRight(INode node) {
		return new org.rascalmpl.semantics.dynamic.Assoc.Right(node);
	}

	public org.rascalmpl.ast.Assoc.Ambiguity makeAssocAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Assoc> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Assoc.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Assoc.Left makeAssocLeft(INode node) {
		return new org.rascalmpl.semantics.dynamic.Assoc.Left(node);
	}

	public org.rascalmpl.ast.ProdModifier.Lexical makeProdModifierLexical(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.ProdModifier.Lexical(node);
	}

	public org.rascalmpl.ast.ProdModifier.Bracket makeProdModifierBracket(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.ProdModifier.Bracket(node);
	}

	public org.rascalmpl.ast.ProdModifier.Ambiguity makeProdModifierAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ProdModifier> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ProdModifier.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.ProdModifier.Associativity makeProdModifierAssociativity(
			INode node, org.rascalmpl.ast.Assoc associativity) {
		return new org.rascalmpl.semantics.dynamic.ProdModifier.Associativity(
				node, associativity);
	}

	public org.rascalmpl.ast.Comment.Ambiguity makeCommentAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Comment> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Comment.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Comment.Lexical makeCommentLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Comment.Lexical(node, string);
	}

	public org.rascalmpl.ast.CommentChar.Ambiguity makeCommentCharAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.CommentChar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.CommentChar.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.CommentChar.Lexical makeCommentCharLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.CommentChar.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.Asterisk.Ambiguity makeAsteriskAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Asterisk> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Asterisk.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Asterisk.Lexical makeAsteriskLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.Asterisk.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.UnicodeEscape.Ambiguity makeUnicodeEscapeAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.UnicodeEscape> alternatives) {
		return new org.rascalmpl.semantics.dynamic.UnicodeEscape.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.UnicodeEscape.Lexical makeUnicodeEscapeLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.UnicodeEscape.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.DecimalIntegerLiteral.Ambiguity makeDecimalIntegerLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.DecimalIntegerLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DecimalIntegerLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.DecimalIntegerLiteral.Lexical makeDecimalIntegerLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.DecimalIntegerLiteral.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.HexIntegerLiteral.Ambiguity makeHexIntegerLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.HexIntegerLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.HexIntegerLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.HexIntegerLiteral.Lexical makeHexIntegerLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.HexIntegerLiteral.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.OctalIntegerLiteral.Ambiguity makeOctalIntegerLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.OctalIntegerLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.OctalIntegerLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.OctalIntegerLiteral.Lexical makeOctalIntegerLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.OctalIntegerLiteral.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.DecimalLongLiteral.Ambiguity makeDecimalLongLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.DecimalLongLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DecimalLongLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.DecimalLongLiteral.Lexical makeDecimalLongLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.DecimalLongLiteral.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.HexLongLiteral.Ambiguity makeHexLongLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.HexLongLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.HexLongLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.HexLongLiteral.Lexical makeHexLongLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.HexLongLiteral.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.OctalLongLiteral.Ambiguity makeOctalLongLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.OctalLongLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.OctalLongLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.OctalLongLiteral.Lexical makeOctalLongLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.OctalLongLiteral.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.RealLiteral.Ambiguity makeRealLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.RealLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.RealLiteral.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.RealLiteral.Lexical makeRealLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.RealLiteral.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.BooleanLiteral.Ambiguity makeBooleanLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.BooleanLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.BooleanLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.BooleanLiteral.Lexical makeBooleanLiteralLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.BooleanLiteral.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.OctalEscapeSequence.Ambiguity makeOctalEscapeSequenceAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.OctalEscapeSequence> alternatives) {
		return new org.rascalmpl.semantics.dynamic.OctalEscapeSequence.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.OctalEscapeSequence.Lexical makeOctalEscapeSequenceLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.OctalEscapeSequence.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.StringCharacter.Ambiguity makeStringCharacterAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StringCharacter> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StringCharacter.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.StringCharacter.Lexical makeStringCharacterLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.StringCharacter.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.StringConstant.Ambiguity makeStringConstantAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StringConstant> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StringConstant.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.StringConstant.Lexical makeStringConstantLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.StringConstant.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.CaseInsensitiveStringConstant.Ambiguity makeCaseInsensitiveStringConstantAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.CaseInsensitiveStringConstant> alternatives) {
		return new org.rascalmpl.semantics.dynamic.CaseInsensitiveStringConstant.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.CaseInsensitiveStringConstant.Lexical makeCaseInsensitiveStringConstantLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.CaseInsensitiveStringConstant.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.StringLiteral.Template makeStringLiteralTemplate(
			INode node, org.rascalmpl.ast.PreStringChars pre,
			org.rascalmpl.ast.StringTemplate template,
			org.rascalmpl.ast.StringTail tail) {
		return new org.rascalmpl.semantics.dynamic.StringLiteral.Template(node,
				pre, template, tail);
	}

	public org.rascalmpl.ast.StringLiteral.Interpolated makeStringLiteralInterpolated(
			INode node, org.rascalmpl.ast.PreStringChars pre,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.StringTail tail) {
		return new org.rascalmpl.semantics.dynamic.StringLiteral.Interpolated(
				node, pre, expression, tail);
	}

	public org.rascalmpl.ast.StringLiteral.Ambiguity makeStringLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StringLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StringLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.StringLiteral.NonInterpolated makeStringLiteralNonInterpolated(
			INode node, org.rascalmpl.ast.StringConstant constant) {
		return new org.rascalmpl.semantics.dynamic.StringLiteral.NonInterpolated(
				node, constant);
	}

	public org.rascalmpl.ast.PreStringChars.Ambiguity makePreStringCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PreStringChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PreStringChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.PreStringChars.Lexical makePreStringCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.PreStringChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.MidStringChars.Ambiguity makeMidStringCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.MidStringChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.MidStringChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.MidStringChars.Lexical makeMidStringCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.MidStringChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.PostStringChars.Ambiguity makePostStringCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PostStringChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PostStringChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.PostStringChars.Lexical makePostStringCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.PostStringChars.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.ProtocolChars.Ambiguity makeProtocolCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ProtocolChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ProtocolChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.ProtocolChars.Lexical makeProtocolCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.ProtocolChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.PreProtocolChars.Ambiguity makePreProtocolCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PreProtocolChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PreProtocolChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.PreProtocolChars.Lexical makePreProtocolCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.PreProtocolChars.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.MidProtocolChars.Ambiguity makeMidProtocolCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.MidProtocolChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.MidProtocolChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.MidProtocolChars.Lexical makeMidProtocolCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.MidProtocolChars.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.PostProtocolChars.Ambiguity makePostProtocolCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PostProtocolChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PostProtocolChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.PostProtocolChars.Lexical makePostProtocolCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.PostProtocolChars.Lexical(
				node, string);
	}

	public org.rascalmpl.ast.PrePathChars.Ambiguity makePrePathCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PrePathChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PrePathChars.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.PrePathChars.Lexical makePrePathCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.PrePathChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.MidPathChars.Ambiguity makeMidPathCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.MidPathChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.MidPathChars.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.MidPathChars.Lexical makeMidPathCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.MidPathChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.PostPathChars.Ambiguity makePostPathCharsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.PostPathChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PostPathChars.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.PostPathChars.Lexical makePostPathCharsLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.PostPathChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.PathChars.Ambiguity makePathCharsAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.PathChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PathChars.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.PathChars.Lexical makePathCharsLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.PathChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.ProtocolPart.Interpolated makeProtocolPartInterpolated(
			INode node, org.rascalmpl.ast.PreProtocolChars pre,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.ProtocolTail tail) {
		return new org.rascalmpl.semantics.dynamic.ProtocolPart.Interpolated(
				node, pre, expression, tail);
	}

	public org.rascalmpl.ast.ProtocolPart.Ambiguity makeProtocolPartAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ProtocolPart> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ProtocolPart.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.ProtocolPart.NonInterpolated makeProtocolPartNonInterpolated(
			INode node, org.rascalmpl.ast.ProtocolChars protocolChars) {
		return new org.rascalmpl.semantics.dynamic.ProtocolPart.NonInterpolated(
				node, protocolChars);
	}

	public org.rascalmpl.ast.PathPart.Interpolated makePathPartInterpolated(
			INode node, org.rascalmpl.ast.PrePathChars pre,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.PathTail tail) {
		return new org.rascalmpl.semantics.dynamic.PathPart.Interpolated(node,
				pre, expression, tail);
	}

	public org.rascalmpl.ast.PathPart.Ambiguity makePathPartAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.PathPart> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PathPart.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.PathPart.NonInterpolated makePathPartNonInterpolated(
			INode node, org.rascalmpl.ast.PathChars pathChars) {
		return new org.rascalmpl.semantics.dynamic.PathPart.NonInterpolated(
				node, pathChars);
	}

	public org.rascalmpl.ast.LocationLiteral.Ambiguity makeLocationLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.LocationLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.LocationLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.LocationLiteral.Default makeLocationLiteralDefault(
			INode node, org.rascalmpl.ast.ProtocolPart protocolPart,
			org.rascalmpl.ast.PathPart pathPart) {
		return new org.rascalmpl.semantics.dynamic.LocationLiteral.Default(
				node, protocolPart, pathPart);
	}

	public org.rascalmpl.ast.URLChars.Ambiguity makeURLCharsAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.URLChars> alternatives) {
		return new org.rascalmpl.semantics.dynamic.URLChars.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.URLChars.Lexical makeURLCharsLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.URLChars.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.DatePart.Ambiguity makeDatePartAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.DatePart> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DatePart.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.DatePart.Lexical makeDatePartLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.DatePart.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.JustDate.Ambiguity makeJustDateAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.JustDate> alternatives) {
		return new org.rascalmpl.semantics.dynamic.JustDate.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.JustDate.Lexical makeJustDateLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.JustDate.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.TimePartNoTZ.Ambiguity makeTimePartNoTZAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.TimePartNoTZ> alternatives) {
		return new org.rascalmpl.semantics.dynamic.TimePartNoTZ.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.TimePartNoTZ.Lexical makeTimePartNoTZLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.TimePartNoTZ.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.TimeZonePart.Ambiguity makeTimeZonePartAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.TimeZonePart> alternatives) {
		return new org.rascalmpl.semantics.dynamic.TimeZonePart.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.TimeZonePart.Lexical makeTimeZonePartLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.TimeZonePart.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.JustTime.Ambiguity makeJustTimeAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.JustTime> alternatives) {
		return new org.rascalmpl.semantics.dynamic.JustTime.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.JustTime.Lexical makeJustTimeLexical(INode node,
			String string) {
		return new org.rascalmpl.semantics.dynamic.JustTime.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.DateAndTime.Ambiguity makeDateAndTimeAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.DateAndTime> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DateAndTime.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.DateAndTime.Lexical makeDateAndTimeLexical(
			INode node, String string) {
		return new org.rascalmpl.semantics.dynamic.DateAndTime.Lexical(node,
				string);
	}

	public org.rascalmpl.ast.DateTimeLiteral.DateAndTimeLiteral makeDateTimeLiteralDateAndTimeLiteral(
			INode node, org.rascalmpl.ast.DateAndTime dateAndTime) {
		return new org.rascalmpl.semantics.dynamic.DateTimeLiteral.DateAndTimeLiteral(
				node, dateAndTime);
	}

	public org.rascalmpl.ast.DateTimeLiteral.TimeLiteral makeDateTimeLiteralTimeLiteral(
			INode node, org.rascalmpl.ast.JustTime time) {
		return new org.rascalmpl.semantics.dynamic.DateTimeLiteral.TimeLiteral(
				node, time);
	}

	public org.rascalmpl.ast.DateTimeLiteral.Ambiguity makeDateTimeLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.DateTimeLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DateTimeLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.DateTimeLiteral.DateLiteral makeDateTimeLiteralDateLiteral(
			INode node, org.rascalmpl.ast.JustDate date) {
		return new org.rascalmpl.semantics.dynamic.DateTimeLiteral.DateLiteral(
				node, date);
	}

	public org.rascalmpl.ast.IntegerLiteral.OctalIntegerLiteral makeIntegerLiteralOctalIntegerLiteral(
			INode node, org.rascalmpl.ast.OctalIntegerLiteral octal) {
		return new org.rascalmpl.semantics.dynamic.IntegerLiteral.OctalIntegerLiteral(
				node, octal);
	}

	public org.rascalmpl.ast.IntegerLiteral.HexIntegerLiteral makeIntegerLiteralHexIntegerLiteral(
			INode node, org.rascalmpl.ast.HexIntegerLiteral hex) {
		return new org.rascalmpl.semantics.dynamic.IntegerLiteral.HexIntegerLiteral(
				node, hex);
	}

	public org.rascalmpl.ast.IntegerLiteral.Ambiguity makeIntegerLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.IntegerLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.IntegerLiteral.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.IntegerLiteral.DecimalIntegerLiteral makeIntegerLiteralDecimalIntegerLiteral(
			INode node, org.rascalmpl.ast.DecimalIntegerLiteral decimal) {
		return new org.rascalmpl.semantics.dynamic.IntegerLiteral.DecimalIntegerLiteral(
				node, decimal);
	}

	public org.rascalmpl.ast.LongLiteral.OctalLongLiteral makeLongLiteralOctalLongLiteral(
			INode node, org.rascalmpl.ast.OctalLongLiteral octalLong) {
		return new org.rascalmpl.semantics.dynamic.LongLiteral.OctalLongLiteral(
				node, octalLong);
	}

	public org.rascalmpl.ast.LongLiteral.HexLongLiteral makeLongLiteralHexLongLiteral(
			INode node, org.rascalmpl.ast.HexLongLiteral hexLong) {
		return new org.rascalmpl.semantics.dynamic.LongLiteral.HexLongLiteral(
				node, hexLong);
	}

	public org.rascalmpl.ast.LongLiteral.Ambiguity makeLongLiteralAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.LongLiteral> alternatives) {
		return new org.rascalmpl.semantics.dynamic.LongLiteral.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.LongLiteral.DecimalLongLiteral makeLongLiteralDecimalLongLiteral(
			INode node, org.rascalmpl.ast.DecimalLongLiteral decimalLong) {
		return new org.rascalmpl.semantics.dynamic.LongLiteral.DecimalLongLiteral(
				node, decimalLong);
	}

	public org.rascalmpl.ast.Formal.Ambiguity makeFormalAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Formal> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Formal.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Formal.TypeName makeFormalTypeName(INode node,
			org.rascalmpl.ast.Type type, org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.Formal.TypeName(node, type,
				name);
	}

	public org.rascalmpl.ast.Formals.Ambiguity makeFormalsAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Formals> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Formals.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Formals.Default makeFormalsDefault(INode node,
			java.util.List<org.rascalmpl.ast.Formal> formals) {
		return new org.rascalmpl.semantics.dynamic.Formals.Default(node,
				formals);
	}

	public org.rascalmpl.ast.Parameters.VarArgs makeParametersVarArgs(
			INode node, org.rascalmpl.ast.Formals formals) {
		return new org.rascalmpl.semantics.dynamic.Parameters.VarArgs(node,
				formals);
	}

	public org.rascalmpl.ast.Parameters.Ambiguity makeParametersAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.Parameters> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Parameters.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Parameters.Default makeParametersDefault(
			INode node, org.rascalmpl.ast.Formals formals) {
		return new org.rascalmpl.semantics.dynamic.Parameters.Default(node,
				formals);
	}

	public org.rascalmpl.ast.ProtocolTail.Post makeProtocolTailPost(INode node,
			org.rascalmpl.ast.PostProtocolChars post) {
		return new org.rascalmpl.semantics.dynamic.ProtocolTail.Post(node, post);
	}

	public org.rascalmpl.ast.ProtocolTail.Ambiguity makeProtocolTailAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ProtocolTail> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ProtocolTail.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.ProtocolTail.Mid makeProtocolTailMid(INode node,
			org.rascalmpl.ast.MidProtocolChars mid,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.ProtocolTail tail) {
		return new org.rascalmpl.semantics.dynamic.ProtocolTail.Mid(node, mid,
				expression, tail);
	}

	public org.rascalmpl.ast.PathTail.Post makePathTailPost(INode node,
			org.rascalmpl.ast.PostPathChars post) {
		return new org.rascalmpl.semantics.dynamic.PathTail.Post(node, post);
	}

	public org.rascalmpl.ast.PathTail.Ambiguity makePathTailAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.PathTail> alternatives) {
		return new org.rascalmpl.semantics.dynamic.PathTail.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.PathTail.Mid makePathTailMid(INode node,
			org.rascalmpl.ast.MidPathChars mid,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.PathTail tail) {
		return new org.rascalmpl.semantics.dynamic.PathTail.Mid(node, mid,
				expression, tail);
	}

	public org.rascalmpl.ast.StringTail.Post makeStringTailPost(INode node,
			org.rascalmpl.ast.PostStringChars post) {
		return new org.rascalmpl.semantics.dynamic.StringTail.Post(node, post);
	}

	public org.rascalmpl.ast.StringTail.MidTemplate makeStringTailMidTemplate(
			INode node, org.rascalmpl.ast.MidStringChars mid,
			org.rascalmpl.ast.StringTemplate template,
			org.rascalmpl.ast.StringTail tail) {
		return new org.rascalmpl.semantics.dynamic.StringTail.MidTemplate(node,
				mid, template, tail);
	}

	public org.rascalmpl.ast.StringTail.Ambiguity makeStringTailAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StringTail> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StringTail.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.StringTail.MidInterpolated makeStringTailMidInterpolated(
			INode node, org.rascalmpl.ast.MidStringChars mid,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.StringTail tail) {
		return new org.rascalmpl.semantics.dynamic.StringTail.MidInterpolated(
				node, mid, expression, tail);
	}

	public org.rascalmpl.ast.StringMiddle.Template makeStringMiddleTemplate(
			INode node, org.rascalmpl.ast.MidStringChars mid,
			org.rascalmpl.ast.StringTemplate template,
			org.rascalmpl.ast.StringMiddle tail) {
		return new org.rascalmpl.semantics.dynamic.StringMiddle.Template(node,
				mid, template, tail);
	}

	public org.rascalmpl.ast.StringMiddle.Interpolated makeStringMiddleInterpolated(
			INode node, org.rascalmpl.ast.MidStringChars mid,
			org.rascalmpl.ast.Expression expression,
			org.rascalmpl.ast.StringMiddle tail) {
		return new org.rascalmpl.semantics.dynamic.StringMiddle.Interpolated(
				node, mid, expression, tail);
	}

	public org.rascalmpl.ast.StringMiddle.Ambiguity makeStringMiddleAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StringMiddle> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StringMiddle.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.StringMiddle.Mid makeStringMiddleMid(INode node,
			org.rascalmpl.ast.MidStringChars mid) {
		return new org.rascalmpl.semantics.dynamic.StringMiddle.Mid(node, mid);
	}

	public org.rascalmpl.ast.StringTemplate.DoWhile makeStringTemplateDoWhile(
			INode node, java.util.List<org.rascalmpl.ast.Statement> preStats,
			org.rascalmpl.ast.StringMiddle body,
			java.util.List<org.rascalmpl.ast.Statement> postStats,
			org.rascalmpl.ast.Expression condition) {
		return new org.rascalmpl.semantics.dynamic.StringTemplate.DoWhile(node,
				preStats, body, postStats, condition);
	}

	public org.rascalmpl.ast.StringTemplate.While makeStringTemplateWhile(
			INode node, org.rascalmpl.ast.Expression condition,
			java.util.List<org.rascalmpl.ast.Statement> preStats,
			org.rascalmpl.ast.StringMiddle body,
			java.util.List<org.rascalmpl.ast.Statement> postStats) {
		return new org.rascalmpl.semantics.dynamic.StringTemplate.While(node,
				condition, preStats, body, postStats);
	}

	public org.rascalmpl.ast.StringTemplate.IfThenElse makeStringTemplateIfThenElse(
			INode node,
			java.util.List<org.rascalmpl.ast.Expression> conditions,
			java.util.List<org.rascalmpl.ast.Statement> preStatsThen,
			org.rascalmpl.ast.StringMiddle thenString,
			java.util.List<org.rascalmpl.ast.Statement> postStatsThen,
			java.util.List<org.rascalmpl.ast.Statement> preStatsElse,
			org.rascalmpl.ast.StringMiddle elseString,
			java.util.List<org.rascalmpl.ast.Statement> postStatsElse) {
		return new org.rascalmpl.semantics.dynamic.StringTemplate.IfThenElse(
				node, conditions, preStatsThen, thenString, postStatsThen,
				preStatsElse, elseString, postStatsElse);
	}

	public org.rascalmpl.ast.StringTemplate.IfThen makeStringTemplateIfThen(
			INode node,
			java.util.List<org.rascalmpl.ast.Expression> conditions,
			java.util.List<org.rascalmpl.ast.Statement> preStats,
			org.rascalmpl.ast.StringMiddle body,
			java.util.List<org.rascalmpl.ast.Statement> postStats) {
		return new org.rascalmpl.semantics.dynamic.StringTemplate.IfThen(node,
				conditions, preStats, body, postStats);
	}

	public org.rascalmpl.ast.StringTemplate.Ambiguity makeStringTemplateAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StringTemplate> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StringTemplate.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.StringTemplate.For makeStringTemplateFor(
			INode node,
			java.util.List<org.rascalmpl.ast.Expression> generators,
			java.util.List<org.rascalmpl.ast.Statement> preStats,
			org.rascalmpl.ast.StringMiddle body,
			java.util.List<org.rascalmpl.ast.Statement> postStats) {
		return new org.rascalmpl.semantics.dynamic.StringTemplate.For(node,
				generators, preStats, body, postStats);
	}

	public org.rascalmpl.ast.Field.Index makeFieldIndex(INode node,
			org.rascalmpl.ast.IntegerLiteral fieldIndex) {
		return new org.rascalmpl.semantics.dynamic.Field.Index(node, fieldIndex);
	}

	public org.rascalmpl.ast.Field.Ambiguity makeFieldAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Field> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Field.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Field.Name makeFieldName(INode node,
			org.rascalmpl.ast.Name fieldName) {
		return new org.rascalmpl.semantics.dynamic.Field.Name(node, fieldName);
	}

	public org.rascalmpl.ast.Module.Ambiguity makeModuleAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Module> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Module.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Module.Default makeModuleDefault(INode node,
			org.rascalmpl.ast.Header header, org.rascalmpl.ast.Body body) {
		return new org.rascalmpl.semantics.dynamic.Module.Default(node, header,
				body);
	}

	public org.rascalmpl.ast.ModuleActuals.Ambiguity makeModuleActualsAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ModuleActuals> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ModuleActuals.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.ModuleActuals.Default makeModuleActualsDefault(
			INode node, java.util.List<org.rascalmpl.ast.Type> types) {
		return new org.rascalmpl.semantics.dynamic.ModuleActuals.Default(node,
				types);
	}

	public org.rascalmpl.ast.ImportedModule.Default makeImportedModuleDefault(
			INode node, org.rascalmpl.ast.QualifiedName name) {
		return new org.rascalmpl.semantics.dynamic.ImportedModule.Default(node,
				name);
	}

	public org.rascalmpl.ast.ImportedModule.Renamings makeImportedModuleRenamings(
			INode node, org.rascalmpl.ast.QualifiedName name,
			org.rascalmpl.ast.Renamings renamings) {
		return new org.rascalmpl.semantics.dynamic.ImportedModule.Renamings(
				node, name, renamings);
	}

	public org.rascalmpl.ast.ImportedModule.Actuals makeImportedModuleActuals(
			INode node, org.rascalmpl.ast.QualifiedName name,
			org.rascalmpl.ast.ModuleActuals actuals) {
		return new org.rascalmpl.semantics.dynamic.ImportedModule.Actuals(node,
				name, actuals);
	}

	public org.rascalmpl.ast.ImportedModule.Ambiguity makeImportedModuleAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ImportedModule> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ImportedModule.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.ImportedModule.ActualsRenaming makeImportedModuleActualsRenaming(
			INode node, org.rascalmpl.ast.QualifiedName name,
			org.rascalmpl.ast.ModuleActuals actuals,
			org.rascalmpl.ast.Renamings renamings) {
		return new org.rascalmpl.semantics.dynamic.ImportedModule.ActualsRenaming(
				node, name, actuals, renamings);
	}

	public org.rascalmpl.ast.Renaming.Ambiguity makeRenamingAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Renaming> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Renaming.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Renaming.Default makeRenamingDefault(INode node,
			org.rascalmpl.ast.Name from, org.rascalmpl.ast.Name to) {
		return new org.rascalmpl.semantics.dynamic.Renaming.Default(node, from,
				to);
	}

	public org.rascalmpl.ast.Renamings.Ambiguity makeRenamingsAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.Renamings> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Renamings.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Renamings.Default makeRenamingsDefault(INode node,
			java.util.List<org.rascalmpl.ast.Renaming> renamings) {
		return new org.rascalmpl.semantics.dynamic.Renamings.Default(node,
				renamings);
	}

	public org.rascalmpl.ast.Import.Syntax makeImportSyntax(INode node,
			org.rascalmpl.ast.SyntaxDefinition syntax) {
		return new org.rascalmpl.semantics.dynamic.Import.Syntax(node, syntax);
	}

	public org.rascalmpl.ast.Import.Extend makeImportExtend(INode node,
			org.rascalmpl.ast.ImportedModule module) {
		return new org.rascalmpl.semantics.dynamic.Import.Extend(node, module);
	}

	public org.rascalmpl.ast.Import.Ambiguity makeImportAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Import> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Import.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Import.Default makeImportDefault(INode node,
			org.rascalmpl.ast.ImportedModule module) {
		return new org.rascalmpl.semantics.dynamic.Import.Default(node, module);
	}

	public org.rascalmpl.ast.ModuleParameters.Ambiguity makeModuleParametersAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.ModuleParameters> alternatives) {
		return new org.rascalmpl.semantics.dynamic.ModuleParameters.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.ModuleParameters.Default makeModuleParametersDefault(
			INode node, java.util.List<org.rascalmpl.ast.TypeVar> parameters) {
		return new org.rascalmpl.semantics.dynamic.ModuleParameters.Default(
				node, parameters);
	}

	public org.rascalmpl.ast.Header.Parameters makeHeaderParameters(INode node,
			org.rascalmpl.ast.Tags tags, org.rascalmpl.ast.QualifiedName name,
			org.rascalmpl.ast.ModuleParameters params,
			java.util.List<org.rascalmpl.ast.Import> imports) {
		return new org.rascalmpl.semantics.dynamic.Header.Parameters(node,
				tags, name, params, imports);
	}

	public org.rascalmpl.ast.Header.Ambiguity makeHeaderAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.Header> alternatives) {
		return new org.rascalmpl.semantics.dynamic.Header.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.Header.Default makeHeaderDefault(INode node,
			org.rascalmpl.ast.Tags tags, org.rascalmpl.ast.QualifiedName name,
			java.util.List<org.rascalmpl.ast.Import> imports) {
		return new org.rascalmpl.semantics.dynamic.Header.Default(node, tags,
				name, imports);
	}

	public org.rascalmpl.ast.BasicType.DateTime makeBasicTypeDateTime(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.DateTime(node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedReifiedType makeBasicTypeReifiedReifiedType(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedReifiedType(
				node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedNonTerminal makeBasicTypeReifiedNonTerminal(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedNonTerminal(
				node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedFunction makeBasicTypeReifiedFunction(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedFunction(
				node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedConstructor makeBasicTypeReifiedConstructor(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedConstructor(
				node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedTypeParameter makeBasicTypeReifiedTypeParameter(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedTypeParameter(
				node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedAdt makeBasicTypeReifiedAdt(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedAdt(node);
	}

	public org.rascalmpl.ast.BasicType.ReifiedType makeBasicTypeReifiedType(
			INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.ReifiedType(node);
	}

	public org.rascalmpl.ast.BasicType.Lex makeBasicTypeLex(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Lex(node);
	}

	public org.rascalmpl.ast.BasicType.Tuple makeBasicTypeTuple(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Tuple(node);
	}

	public org.rascalmpl.ast.BasicType.Relation makeBasicTypeRelation(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Relation(node);
	}

	public org.rascalmpl.ast.BasicType.Map makeBasicTypeMap(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Map(node);
	}

	public org.rascalmpl.ast.BasicType.Bag makeBasicTypeBag(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Bag(node);
	}

	public org.rascalmpl.ast.BasicType.Set makeBasicTypeSet(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Set(node);
	}

	public org.rascalmpl.ast.BasicType.List makeBasicTypeList(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.List(node);
	}

	public org.rascalmpl.ast.BasicType.Loc makeBasicTypeLoc(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Loc(node);
	}

	public org.rascalmpl.ast.BasicType.Void makeBasicTypeVoid(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Void(node);
	}

	public org.rascalmpl.ast.BasicType.Node makeBasicTypeNode(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Node(node);
	}

	public org.rascalmpl.ast.BasicType.Value makeBasicTypeValue(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Value(node);
	}

	public org.rascalmpl.ast.BasicType.String makeBasicTypeString(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.String(node);
	}

	public org.rascalmpl.ast.BasicType.Num makeBasicTypeNum(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Num(node);
	}

	public org.rascalmpl.ast.BasicType.Real makeBasicTypeReal(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Real(node);
	}

	public org.rascalmpl.ast.BasicType.Int makeBasicTypeInt(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Int(node);
	}

	public org.rascalmpl.ast.BasicType.Ambiguity makeBasicTypeAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.BasicType> alternatives) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.BasicType.Bool makeBasicTypeBool(INode node) {
		return new org.rascalmpl.semantics.dynamic.BasicType.Bool(node);
	}

	public org.rascalmpl.ast.TypeArg.Named makeTypeArgNamed(INode node,
			org.rascalmpl.ast.Type type, org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.TypeArg.Named(node, type,
				name);
	}

	public org.rascalmpl.ast.TypeArg.Ambiguity makeTypeArgAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.TypeArg> alternatives) {
		return new org.rascalmpl.semantics.dynamic.TypeArg.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.TypeArg.Default makeTypeArgDefault(INode node,
			org.rascalmpl.ast.Type type) {
		return new org.rascalmpl.semantics.dynamic.TypeArg.Default(node, type);
	}

	public org.rascalmpl.ast.StructuredType.Ambiguity makeStructuredTypeAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.StructuredType> alternatives) {
		return new org.rascalmpl.semantics.dynamic.StructuredType.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.StructuredType.Default makeStructuredTypeDefault(
			INode node, org.rascalmpl.ast.BasicType basicType,
			java.util.List<org.rascalmpl.ast.TypeArg> arguments) {
		return new org.rascalmpl.semantics.dynamic.StructuredType.Default(node,
				basicType, arguments);
	}

	public org.rascalmpl.ast.FunctionType.Ambiguity makeFunctionTypeAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.FunctionType> alternatives) {
		return new org.rascalmpl.semantics.dynamic.FunctionType.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.FunctionType.TypeArguments makeFunctionTypeTypeArguments(
			INode node, org.rascalmpl.ast.Type type,
			java.util.List<org.rascalmpl.ast.TypeArg> arguments) {
		return new org.rascalmpl.semantics.dynamic.FunctionType.TypeArguments(
				node, type, arguments);
	}

	public org.rascalmpl.ast.TypeVar.Bounded makeTypeVarBounded(INode node,
			org.rascalmpl.ast.Name name, org.rascalmpl.ast.Type bound) {
		return new org.rascalmpl.semantics.dynamic.TypeVar.Bounded(node, name,
				bound);
	}

	public org.rascalmpl.ast.TypeVar.Ambiguity makeTypeVarAmbiguity(INode node,
			java.util.List<org.rascalmpl.ast.TypeVar> alternatives) {
		return new org.rascalmpl.semantics.dynamic.TypeVar.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.TypeVar.Free makeTypeVarFree(INode node,
			org.rascalmpl.ast.Name name) {
		return new org.rascalmpl.semantics.dynamic.TypeVar.Free(node, name);
	}

	public org.rascalmpl.ast.UserType.Parametric makeUserTypeParametric(
			INode node, org.rascalmpl.ast.QualifiedName name,
			java.util.List<org.rascalmpl.ast.Type> parameters) {
		return new org.rascalmpl.semantics.dynamic.UserType.Parametric(node,
				name, parameters);
	}

	public org.rascalmpl.ast.UserType.Ambiguity makeUserTypeAmbiguity(
			INode node, java.util.List<org.rascalmpl.ast.UserType> alternatives) {
		return new org.rascalmpl.semantics.dynamic.UserType.Ambiguity(node,
				alternatives);
	}

	public org.rascalmpl.ast.UserType.Name makeUserTypeName(INode node,
			org.rascalmpl.ast.QualifiedName name) {
		return new org.rascalmpl.semantics.dynamic.UserType.Name(node, name);
	}

	public org.rascalmpl.ast.DataTypeSelector.Ambiguity makeDataTypeSelectorAmbiguity(
			INode node,
			java.util.List<org.rascalmpl.ast.DataTypeSelector> alternatives) {
		return new org.rascalmpl.semantics.dynamic.DataTypeSelector.Ambiguity(
				node, alternatives);
	}

	public org.rascalmpl.ast.DataTypeSelector.Selector makeDataTypeSelectorSelector(
			INode node, org.rascalmpl.ast.QualifiedName sort,
			org.rascalmpl.ast.Name production) {
		return new org.rascalmpl.semantics.dynamic.DataTypeSelector.Selector(
				node, sort, production);
	}
}