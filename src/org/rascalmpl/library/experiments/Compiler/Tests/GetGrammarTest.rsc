module experiments::Compiler::Tests::GetGrammarTest

import experiments::Compiler::Rascal2muRascal::TypeReifier;
import lang::rascal::types::CheckTypes;
import lang::rascal::\syntax::Rascal;

import experiments::Compiler::muRascal2RVM::RascalReifiedTypes;

import ParseTree;

public test bool tst() {
	configuration = newConfiguration();
	configuration = checkModule(parse(#start[Module],|rascal:///lang/rascal/syntax/Rascal.rsc|).top,configuration);
	return getGrammar(configuration) == getModuleType().definitions;
}
