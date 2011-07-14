package org.rascalmpl.test.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.io.StandardTextReader;
import org.rascalmpl.parser.gtd.SGTDBF;
import org.rascalmpl.parser.gtd.stack.AbstractStackNode;
import org.rascalmpl.parser.gtd.stack.LiteralStackNode;
import org.rascalmpl.parser.gtd.stack.NonTerminalStackNode;
import org.rascalmpl.parser.gtd.stack.SequenceStackNode;
import org.rascalmpl.parser.uptr.NodeToUPTR;
import org.rascalmpl.values.ValueFactoryFactory;
import org.rascalmpl.values.uptr.Factory;

/*
S ::= (A B)
A ::= a
B ::= b
*/
public class Sequence2 extends SGTDBF implements IParserTest{
	private final static IConstructor SYMBOL_START_S = VF.constructor(Factory.Symbol_Sort, VF.string("S"));
	private final static IConstructor SYMBOL_A = VF.constructor(Factory.Symbol_Sort, VF.string("A"));
	private final static IConstructor SYMBOL_B = VF.constructor(Factory.Symbol_Sort, VF.string("B"));
	private final static IConstructor SYMBOL_SEQ_AB = VF.constructor(Factory.Symbol_Seq, Factory.Symbols.make(VF, SYMBOL_A, SYMBOL_B));
	private final static IConstructor SYMBOL_a = VF.constructor(Factory.Symbol_Lit, VF.string("a"));
	private final static IConstructor SYMBOL_char_a = VF.constructor(Factory.Symbol_CharClass, VF.list(VF.constructor(Factory.CharRange_Single, VF.integer(97))));
	private final static IConstructor SYMBOL_b = VF.constructor(Factory.Symbol_Lit, VF.string("b"));
	private final static IConstructor SYMBOL_char_b = VF.constructor(Factory.Symbol_CharClass, VF.list(VF.constructor(Factory.CharRange_Single, VF.integer(98))));
	
	private final static IConstructor PROD_S_SEQ_AB = VF.constructor(Factory.Production_Default,  SYMBOL_START_S, VF.list(SYMBOL_SEQ_AB), VF.set());
	private final static IConstructor PROD_SEQUENCE_AB = VF.constructor(Factory.Production_Default,  SYMBOL_SEQ_AB, VF.list(SYMBOL_A, SYMBOL_B), VF.set());
	private final static IConstructor PROD_A_a = VF.constructor(Factory.Production_Default,  SYMBOL_A, VF.list(SYMBOL_a), VF.set());
	private final static IConstructor PROD_a_a = VF.constructor(Factory.Production_Default,  SYMBOL_a, VF.list(SYMBOL_char_a), VF.set());
	private final static IConstructor PROD_B_b = VF.constructor(Factory.Production_Default,  SYMBOL_B, VF.list(SYMBOL_b), VF.set());
	private final static IConstructor PROD_b_b = VF.constructor(Factory.Production_Default,  SYMBOL_b, VF.list(SYMBOL_char_b), VF.set());
	
	private final static AbstractStackNode NONTERMINAL_START_S = new NonTerminalStackNode(AbstractStackNode.START_SYMBOL_ID, 0, "S");
	private final static AbstractStackNode NONTERMINAL_A1 = new NonTerminalStackNode(1, 0, "A");
	private final static AbstractStackNode NONTERMINAL_B2 = new NonTerminalStackNode(2, 1, "B");
	private final static AbstractStackNode SEQUENCE3 = new SequenceStackNode(3, 0, PROD_SEQUENCE_AB, new AbstractStackNode[]{NONTERMINAL_A1, NONTERMINAL_B2});
	private final static AbstractStackNode LITERAL_a4 = new LiteralStackNode(4, 0, PROD_a_a, new char[]{'a'});
	private final static AbstractStackNode LITERAL_b5 = new LiteralStackNode(5, 0, PROD_b_b, new char[]{'b'});
	
	
	public Sequence2(){
		super();
	}
	
	public void S(){
		expect(PROD_S_SEQ_AB, SEQUENCE3);
	}
	
	public void A(){
		expect(PROD_A_a, LITERAL_a4);
	}
	
	public void B(){
		expect(PROD_B_b, LITERAL_b5);
	}
	
	public IConstructor executeParser(){
		return parse(NONTERMINAL_START_S, null, "ab".toCharArray(), new NodeToUPTR());
	}
	
	public IValue getExpectedResult() throws IOException{
		String expectedInput = "appl(prod(sort(\"S\"),[seq([sort(\"A\"),sort(\"B\")])],{}),[appl(prod(seq([sort(\"A\"),sort(\"B\")]),[sort(\"A\"),sort(\"B\")],{}),[appl(prod(sort(\"A\"),[lit(\"a\")],{}),[appl(prod(lit(\"a\"),[\\char-class([single(97)])],{}),[char(97)])]),appl(prod(sort(\"B\"),[lit(\"b\")],{}),[appl(prod(lit(\"b\"),[\\char-class([single(98)])],{}),[char(98)])])])])";
		return new StandardTextReader().read(ValueFactoryFactory.getValueFactory(), Factory.uptr, Factory.Tree, new ByteArrayInputStream(expectedInput.getBytes()));
	}

	public static void main(String[] args){
		Sequence2 s2 = new Sequence2();
		IConstructor result = s2.parse(NONTERMINAL_START_S, null, "ab".toCharArray(), new NodeToUPTR());
		System.out.println(result);
		
		System.out.println("S((A(a),B(b))) <- good");
	}
}