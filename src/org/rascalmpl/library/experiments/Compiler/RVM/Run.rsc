module experiments::Compiler::RVM::Run

import experiments::Compiler::RVM::AST;
import experiments::Compiler::RVM::Syntax;
import experiments::Compiler::RVM::Implode;

import ParseTree;
import util::IDE;

import IO;

@javaClass{org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.Execute}
@reflect{Executes RVM programs}
public java tuple[value,int] executeProgram(RVMProgram program,
											map[str,Symbol] imported_types,
                                            list[Declaration] imported_functions,
                                            lrel[str,list[str],list[str]] imported_overloaded_functions,
                                            map[str,int] imported_overloading_resolvers, 
                                            map[str, map[Symbol, Production]] imported_grammars,
										    list[value] arguments, 
										    bool debug, 
										    bool testsuite);

public void execute(experiments::Compiler::RVM::Syntax::RascalVM tree, loc selection) {
	ast = implode(#experiments::Compiler::RVM::AST::RascalVM, tree);
	out = executeProgram(ast.directives);
	println(out);	
}

set[Contribution] contributions = 
	{ menu(menu("muRascalVM", [ action("Run", execute) ])) };

@doc{Registers the muRascalVM language, .rvm}
public void registerLanguage() {
	registerLanguage("muRascalVM", "rvm", experiments::Compiler::RVM::Syntax::RascalVM (str src, loc l) { return parse(#experiments::Compiler::RVM::Syntax::RascalVM, src, l); });
	registerContributions("muRascalVM", contributions);
}
