package org.rascalmpl.values.uptr;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IInteger;
import org.rascalmpl.values.errors.SummaryAdapter;

public class ParsetreeAdapter {
	
	private ParsetreeAdapter() {
		super();
	}
	
	public static boolean isErrorSummary(IConstructor parseTree) {
		return parseTree.getConstructorType() == Factory.ParseTree_Summary;
	}
	
	public static boolean isParseTree(IConstructor parseTree) {
		return parseTree.getConstructorType() == Factory.ParseTree_Top;
	}
	
	public static IConstructor getTop(IConstructor parseTree) {
		return (IConstructor) parseTree.get("top");
	}
	
	public static SummaryAdapter getSummary(IConstructor parseTree) {
		return new SummaryAdapter(parseTree);
	}
	
	public static boolean hasAmbiguities(IConstructor parseTree) {
		return ((IInteger) parseTree.get("amb_cnt")).intValue() != 0;
	}
}
