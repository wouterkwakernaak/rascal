package org.rascalmpl.values.uptr;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IString;
import org.rascalmpl.interpreter.asserts.ImplementationError;

public class SymbolAdapter {
	
	private SymbolAdapter() {
		super();
	}

	public static boolean isCf(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Cf;
	}
	
	public static boolean isLabel(IConstructor sym) {
		return sym.getConstructorType() == Factory.Symbol_Label;
	}

	public static boolean isLex(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Lex;
	}
	
	public static boolean isSort(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Sort;
	}
	
	public static boolean isStartSort(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Start_Sort;
	}  
	
	public static boolean isStart(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_START;
	}
	  
	public static IConstructor getStart(IConstructor tree) {
		if (isStartSort(tree)) {
			return (IConstructor) tree.get("start");
		}
		throw new ImplementationError("Symbol does not have a child named start: " + tree);
	}

	public static IConstructor getSymbol(IConstructor tree) {
		if (isLabel(tree) || isLex(tree) || isCf(tree) || isOpt(tree) || isIterPlus(tree) || isIterStar(tree)  || isIterPlusSeps(tree) || isIterStarSeps(tree)) {
			return ((IConstructor) tree.get("symbol"));
		}
		
		throw new ImplementationError("Symbol does not have a child named symbol: " + tree);
	}
	
	public static String getName(IConstructor tree) {
		if (isSort(tree)) {
			return ((IString) tree.get("string")).getValue();
		}
		else if (isParameterizedSort(tree)) {
			return ((IString) tree.get("sort")).getValue();
		}
		else if (isLabel(tree)) {
			return ((IString) tree.get("name")).getValue();
		}
		else {
			throw new ImplementationError("Symbol does not have a child named \"name\": " + tree);
		}
	}
	
	public static String getLabel(IConstructor tree) {
		if (isLabel(tree)) {
			return ((IString) tree.get("label")).getValue();
		}
		
		throw new ImplementationError("Symbol does not have a child named \"label\" : " + tree);
	}

	public static boolean isParameterizedSort(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_ParameterizedSort;
	}
	
	public static boolean isLiteral(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Lit;
	}

	public static boolean isCILiteral(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_CiLit;
	}

	public static boolean isIterStar(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_IterStar;
	}
	
	public static boolean isIterPlus(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_IterPlus;
	}
	
	public static boolean isLayout(IConstructor tree) {
		if(isProductionList(tree)){
			return isLayout((IConstructor) tree.get("rhs"));
		}
		if (isCf(tree) || isLex(tree) || isOpt(tree) || isIterPlus(tree) || isIterStar(tree)){
			IConstructor t = (IConstructor) tree.get("symbol");
			if(t.getConstructorType() == Factory.Symbol_Layout){
				return true;
			}
			return isLayout(t);
		}
		
		return (tree.getConstructorType() == Factory.Symbol_Layout) || tree.getConstructorType() == Factory.Symbol_LayoutX;
	}
	
	public static boolean isLayouts(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_LayoutX;
	}
	
	private static boolean isProductionList(IConstructor tree){
		return (tree.getConstructorType() == Factory.Production_List);
	}
	
	public static boolean isStarList(IConstructor tree) {
		return isIterStar(tree) || isIterStarSeps(tree) ;
	}
	
	public static boolean isPlusList(IConstructor tree) {
		return isIterPlus(tree) || isIterPlusSeps(tree);
	}
	
	public static boolean isSepList(IConstructor tree){
		return isIterPlusSeps(tree) || isIterStarSeps(tree);
	}
	
	public static boolean isAnyList(IConstructor tree) {
		return isStarList(tree) || isPlusList(tree);
	}
	
	public static boolean isCfOptLayout(IConstructor tree) {
		return false;
	}
	private static boolean isOpt(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Opt;
	}

	public static String toString(IConstructor symbol) {
		return symbol.toString();
	}



	public static IConstructor getRhs(IConstructor symbol) {
		return (IConstructor) symbol.get("rhs");
	}


	public static boolean isIterStarSeps(IConstructor rhs) {
		return rhs.getConstructorType() == Factory.Symbol_IterStarSepX;
	}
	
	public static boolean isIterPlusSeps(IConstructor rhs) {
		return rhs.getConstructorType() == Factory.Symbol_IterSepX;
	}

	public static IList getSeparators(IConstructor rhs) {
		return (IList) rhs.get("separators");
	}

	/** 
	 * TODO: remove it and its use after bootstrapping
	 * This method facilitates bootstrapping by allowing old symbols to be equal to new symbols
	 */
	public static boolean isEqual(IConstructor fst, IConstructor snd) {
		if (fst.isEqual(snd)) {
			return true;
		}
		
		if (isSort(fst) && isSort(snd)) {
			return fst.isEqual(snd);
		}
		
		if ((isIterPlus(fst) && isIterPlusSeps(snd))
				|| (isIterStar(fst) && isIterStarSeps(snd))) {
			return isEqual(snd, fst);
		}
		
		if ((isIterPlusSeps(fst) && isIterPlus(snd))
				|| (isIterStarSeps(fst) && isIterStar(snd))) {
			IList seps = getSeparators(fst);
			if (!isEqual(getSymbol(fst),getSymbol(snd))) {
				return false;
			}
			
			switch (seps.length()) {
			case 1: 
				if (isLayouts((IConstructor) seps.get(0))) {
					return true; // would have been an Cf Iter without seps  
				}
				// is a lexical iter
				return false;
			case 3:
				return false;
			}
		}
		
		if (isCfOptLayout(fst) && isLayouts(snd)) {
			return true;
		}
		
		if (isCfOptLayout(snd) && isLayouts(fst)) {
			return true;
		}
		
		return fst.isEqual(snd);
	}

	public static boolean isEqual(IList s1, IList s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		for (int i = 0; i < s1.length(); i++) {
			if (!SymbolAdapter.isEqual((IConstructor) s1.get(i), (IConstructor) s2.get(i))) {
				return false;
			}
		}
		return true;
	}
}
