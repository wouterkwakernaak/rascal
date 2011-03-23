package org.rascalmpl.parser.gtd.result;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.rascalmpl.parser.gtd.result.AbstractNode.CycleMark;
import org.rascalmpl.parser.gtd.result.ListContainerNode.CycleNode;
import org.rascalmpl.parser.gtd.result.action.IActionExecutor;
import org.rascalmpl.parser.gtd.result.struct.Link;
import org.rascalmpl.parser.gtd.util.ArrayList;
import org.rascalmpl.parser.gtd.util.DoubleArrayList;
import org.rascalmpl.parser.gtd.util.HashMap;
import org.rascalmpl.parser.gtd.util.IndexedStack;
import org.rascalmpl.parser.gtd.util.specific.PositionStore;
import org.rascalmpl.values.uptr.Factory;
import org.rascalmpl.values.uptr.ProductionAdapter;

public class ErrorListBuilder{
	private final static IValueFactory vf = AbstractNode.vf;
	
	private ErrorListBuilder(){
		super();
	}
	
	private static IConstructor[] constructPostFix(AbstractNode[] postFix, IConstructor production, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, PositionStore positionStore, IActionExecutor actionExecutor){
		int postFixLength = postFix.length;
		int offset = 0;
		IConstructor[] constructedPostFix = new IConstructor[postFixLength];
		for(int i = 0; i < postFixLength; ++i){
			AbstractNode node = postFix[i];
			if(!(node instanceof CycleNode)){
				IConstructor constructedNode = postFix[i].toErrorTree(stack, depth, cycleMark, positionStore, actionExecutor);
				if(constructedNode == null) return null;
				constructedPostFix[offset + i] = constructedNode;
			}else{
				CycleNode cycleNode = (CycleNode) node;
				IConstructor[] convertedCycle = convertCycle(cycleNode, stack, depth, cycleMark, positionStore, actionExecutor);
				if(convertedCycle == null) return null;
				
				IConstructor[] constructedCycle = constructCycle(convertedCycle, production, actionExecutor);
				if(constructedCycle == null) return null;
				
				int constructedCycleLength = constructedCycle.length;
				if(constructedCycleLength == 1){
					constructedPostFix[offset + i] = constructedCycle[0];
				}else{
					offset += constructedCycleLength;
					int currentLength = constructedPostFix.length;
					IConstructor[] newConstructedPostFix = new IConstructor[currentLength + constructedCycleLength];
					System.arraycopy(constructedPostFix, 0, newConstructedPostFix, 0, i);
					System.arraycopy(constructedCycle, 0, newConstructedPostFix, i, constructedCycleLength);
				}
			}
		}
		
		return constructedPostFix;
	}
	
	private static IConstructor[] convertCycle(CycleNode cycleNode, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, PositionStore positionStore, IActionExecutor actionExecutor){
		AbstractNode[] cycleElements = cycleNode.cycle;
		
		int nrOfCycleElements = cycleElements.length;
		IConstructor[] convertedCycle;
		if(nrOfCycleElements == 1){
			convertedCycle = new IConstructor[1];
			IConstructor element = cycleElements[0].toErrorTree(stack, depth, cycleMark, positionStore, actionExecutor);
			if(element == null) return null;
			convertedCycle[0] = element;
		}else{
			convertedCycle = new IConstructor[nrOfCycleElements + 1];
			for(int i = 0; i < nrOfCycleElements; ++i){
				IConstructor element = cycleElements[i + 1].toErrorTree(stack, depth, cycleMark, positionStore, actionExecutor);
				if(element == null) return null;
				convertedCycle[i] = element;
			}
			convertedCycle[0] = convertedCycle[nrOfCycleElements];
		}
		
		return convertedCycle;
	}
	
	private static IConstructor[] constructCycle(IConstructor[] convertedCycle, IConstructor production, IActionExecutor actionExecutor){
		IConstructor cycle = vf.constructor(Factory.Tree_Cycle, ProductionAdapter.getRhs(production), vf.integer(1));
		cycle = actionExecutor.filterCycle(cycle);
		if(cycle == null){
			return convertedCycle;
		}
		
		IConstructor elements = vf.constructor(Factory.Tree_Appl, production, vf.list(convertedCycle));
		elements = actionExecutor.filterProduction(elements);
		if(elements == null){
			return null;
		}
		
		IConstructor constructedCycle = vf.constructor(Factory.Tree_Amb, vf.set(elements, cycle));
		constructedCycle = actionExecutor.filterAmbiguity(constructedCycle);
		if(constructedCycle == null) return null;
		
		return new IConstructor[]{constructedCycle};
	}
	
	protected static void gatherAlternatives(Link child, DoubleArrayList<IConstructor[], IConstructor> gatheredAlternatives, IConstructor production, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, HashMap<ArrayList<Link>, IConstructor[]> sharedPrefixCache, PositionStore positionStore, IActionExecutor actionExecutor){
		AbstractNode childNode = child.node;
		
		if(!(childNode.isEpsilon() && child.prefixes == null)){
			ArrayList<AbstractNode> blackList = new ArrayList<AbstractNode>();
			if(childNode.isEmpty()){
				CycleNode cycle = gatherCycle(child, new AbstractNode[]{childNode}, blackList);
				if(cycle != null){
					if(cycle.cycle.length == 1){
						gatherProduction(child, new AbstractNode[]{cycle}, gatheredAlternatives, production, stack, depth, cycleMark, sharedPrefixCache, positionStore, blackList, actionExecutor);
					}else{
						gatherProduction(child, new AbstractNode[]{childNode, cycle}, gatheredAlternatives, production, stack, depth, cycleMark, sharedPrefixCache, positionStore, blackList, actionExecutor);
					}
					return;
				}
			}
			gatherProduction(child, new AbstractNode[]{childNode}, gatheredAlternatives, production, stack, depth, cycleMark, sharedPrefixCache, positionStore, blackList, actionExecutor);
		}else{
			gatheredAlternatives.add(new IConstructor[]{}, production);
		}
	}
	
	private static void gatherProduction(Link child, AbstractNode[] postFix, DoubleArrayList<IConstructor[], IConstructor> gatheredAlternatives, IConstructor production, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, HashMap<ArrayList<Link>, IConstructor[]> sharedPrefixCache, PositionStore positionStore, ArrayList<AbstractNode> blackList, IActionExecutor actionExecutor){
		do{
			ArrayList<Link> prefixes = child.prefixes;
			if(prefixes == null){
				IConstructor[] constructedPostFix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
				if(constructedPostFix == null) return;
				
				gatheredAlternatives.add(constructedPostFix, production);
				return;
			}
			
			if(prefixes.size() == 1){
				Link prefix = prefixes.get(0);
				
				if(prefix == null){
					IConstructor[] constructedPostFix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
					if(constructedPostFix == null) return;
					
					gatheredAlternatives.add(constructedPostFix, production);
					return;
				}
				
				AbstractNode prefixNode = prefix.node;
				if(blackList.contains(prefixNode)){
					return;
				}
				
				if(prefixNode.isEmpty() && !prefixNode.isSeparator()){ // Possibly a cycle.
					CycleNode cycle = gatherCycle(prefix, new AbstractNode[]{prefixNode}, blackList);
					if(cycle != null){
						prefixNode = cycle;
					}
				}
				
				int length = postFix.length;
				AbstractNode[] newPostFix = new AbstractNode[length + 1];
				System.arraycopy(postFix, 0, newPostFix, 1, length);
				newPostFix[0] = prefixNode;
				
				child = prefix;
				postFix = newPostFix;
				continue;
			}
			
			gatherAmbiguousProduction(prefixes, postFix, gatheredAlternatives, production, stack, depth, cycleMark, sharedPrefixCache, positionStore, blackList, actionExecutor);
			
			break;
		}while(true);
	}
	
	private static void gatherAmbiguousProduction(ArrayList<Link> prefixes, AbstractNode[] postFix, DoubleArrayList<IConstructor[], IConstructor> gatheredAlternatives, IConstructor production, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, HashMap<ArrayList<Link>, IConstructor[]> sharedPrefixCache, PositionStore positionStore, ArrayList<AbstractNode> blackList, IActionExecutor actionExecutor){
		IConstructor[] cachedPrefixResult = sharedPrefixCache.get(prefixes);
		if(cachedPrefixResult != null){
			int prefixResultLength = cachedPrefixResult.length;
			IConstructor[] constructedPostFix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
			int length = constructedPostFix.length;
			IConstructor[] newPostFix;
			if(prefixResultLength == 1){
				newPostFix = new IConstructor[length + 1];
				System.arraycopy(constructedPostFix, 0, newPostFix, 1, length);
				newPostFix[0] = cachedPrefixResult[0];
			}else{
				newPostFix = new IConstructor[prefixResultLength + length];
				System.arraycopy(cachedPrefixResult, 0, newPostFix, 0, prefixResultLength);
				System.arraycopy(constructedPostFix, 0, newPostFix, prefixResultLength, length);
			}
			
			gatheredAlternatives.add(newPostFix, production);
			return;
		}
		
		DoubleArrayList<IConstructor[], IConstructor> gatheredPrefixes = new DoubleArrayList<IConstructor[], IConstructor>();
		
		for(int i = prefixes.size() - 1; i >= 0; --i){
			Link prefix = prefixes.get(i);
			
			if(prefix == null){
				IConstructor[] constructedPostFix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
				if(constructedPostFix == null) return;
				
				gatheredAlternatives.add(constructedPostFix, production);
			}else{
				AbstractNode prefixNode = prefix.node;
				if(blackList.contains(prefixNode)){
					continue;
				}
				
				if(prefixNode.isEmpty() && !prefixNode.isSeparator()){ // Possibly a cycle.
					CycleNode cycle = gatherCycle(prefix, new AbstractNode[]{prefixNode}, blackList);
					if(cycle != null){
						gatherProduction(prefix, new AbstractNode[]{cycle}, gatheredPrefixes, production, stack, depth, cycleMark, sharedPrefixCache, positionStore, blackList, actionExecutor);
						continue;
					}
				}
				
				gatherProduction(prefix, new AbstractNode[]{prefixNode}, gatheredPrefixes, production, stack, depth, cycleMark, sharedPrefixCache, positionStore, blackList, actionExecutor);
			}
		}
		
		int nrOfGatheredPrefixes = gatheredPrefixes.size();
		if(nrOfGatheredPrefixes ==  0) return;
		
		if(nrOfGatheredPrefixes == 1){
			IConstructor[] prefixAlternative = gatheredPrefixes.getFirst(0);
			
			IConstructor[] constructedPrefix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
			if(constructedPrefix == null) return;
			
			int length = constructedPrefix.length;
			int prefixLength = prefixAlternative.length;
			IConstructor[] newPostFix = new IConstructor[length + prefixLength];
			System.arraycopy(constructedPrefix, 0, newPostFix, prefixLength, length);
			System.arraycopy(prefixAlternative, 0, newPostFix, 0, prefixLength);
			
			gatheredAlternatives.add(newPostFix, production);
		}else{
			ISetWriter ambSublist = vf.setWriter(Factory.Tree);
			IConstructor lastAlternativeSubList = null;
			
			for(int i = nrOfGatheredPrefixes - 1; i >= 0; --i){
				IConstructor alternativeSubList = vf.constructor(Factory.Tree_Appl, production, vf.list(gatheredPrefixes.getFirst(i)));
				alternativeSubList = actionExecutor.filterProduction(alternativeSubList);
				if(alternativeSubList != null){
					lastAlternativeSubList = alternativeSubList;
					ambSublist.insert(alternativeSubList);
				}
			}
			
			int nrOfAmbSubLists = ambSublist.size();
			if(nrOfAmbSubLists == 1){ // Filtered and no longer ambiguous; flatten it.
				IList childrenList = (IList) lastAlternativeSubList.get(1);
				int nrOfChildren = childrenList.length();
				IConstructor[] children = new IConstructor[nrOfChildren];
				for(int i = nrOfChildren - 1; i >= 0; --i){
					children[i] = (IConstructor) childrenList.get(i);
				}
				
				IConstructor[] constructedPrefix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
				if(constructedPrefix == null) return;
				
				int length = constructedPrefix.length;
				IConstructor[] newPostFix = new IConstructor[nrOfChildren + length];
				System.arraycopy(children, 0, newPostFix, 0, nrOfChildren);
				System.arraycopy(constructedPrefix, 0, newPostFix, nrOfChildren, length);
				gatheredAlternatives.add(newPostFix, production);
				
				sharedPrefixCache.put(prefixes, children);
			}else if(nrOfAmbSubLists > 1){ // Ambiguous after filtering.
				IConstructor prefixResult = vf.constructor(Factory.Tree_Amb, ambSublist.done());
				prefixResult = actionExecutor.filterAmbiguity(prefixResult);
				if(prefixResult == null) return;
				
				IConstructor[] constructedPrefix = constructPostFix(postFix, production, stack, depth, cycleMark, positionStore, actionExecutor);
				if(constructedPrefix == null) return;
				
				int length = constructedPrefix.length;
				IConstructor[] newPostFix = new IConstructor[length + 1];
				System.arraycopy(constructedPrefix, 0, newPostFix, 1, length);
				newPostFix[0] = prefixResult;
				
				gatheredAlternatives.add(newPostFix, production);
				
				sharedPrefixCache.put(prefixes, new IConstructor[]{prefixResult});
			}
			// Filtering caused it to be discarded.
		}
	}
	
	private static CycleNode gatherCycle(Link child, AbstractNode[] postFix, ArrayList<AbstractNode> blackList){
		AbstractNode originNode = child.node;
		
		blackList.add(originNode);
		
		OUTER : do{
			ArrayList<Link> prefixes = child.prefixes;
			if(prefixes == null){
				return null;
			}
			
			int nrOfPrefixes = prefixes.size();
			
			for(int i = nrOfPrefixes - 1; i >= 0; --i){
				Link prefix = prefixes.get(i);
				if(prefix == null) continue;
				AbstractNode prefixNode = prefix.node;
				
				if(prefixNode == originNode){
					return new CycleNode(postFix);
				}
				
				if(prefixNode.isEmpty()){
					int length = postFix.length;
					AbstractNode[] newPostFix = new AbstractNode[length + 1];
					System.arraycopy(postFix, 0, newPostFix, 1, length);
					newPostFix[0] = prefixNode;
					
					child = prefix;
					postFix = newPostFix;
					continue OUTER;
				}
			}
			break;
		}while(true);
		
		return null;
	}
	
	private static IConstructor buildAlternative(IConstructor production, IValue[] children, boolean error){
		IListWriter childrenListWriter = vf.listWriter(Factory.Tree);
		for(int i = children.length - 1; i >= 0; --i){
			childrenListWriter.insert(children[i]);
		}
		
		if(error){
			return vf.constructor(Factory.Tree_Appl, production, childrenListWriter.done());
		}
		return vf.constructor(Factory.Tree_Error, production, childrenListWriter.done(), AbstractContainerNode.EMPTY_LIST);
	}
	
	public static IConstructor toErrorListTree(ListContainerNode node, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, PositionStore positionStore, IActionExecutor actionExecutor){
		if(depth == cycleMark.depth){
			cycleMark.reset();
		}
		
		ISourceLocation sourceLocation = null;
		if(!(node.isLayout || node.input == null)){
			int beginLine = positionStore.findLine(node.offset);
			int endLine = positionStore.findLine(node.endOffset);
			sourceLocation = vf.sourceLocation(node.input, node.offset, node.endOffset - node.offset, beginLine + 1, endLine + 1, positionStore.getColumn(node.offset, beginLine), positionStore.getColumn(node.endOffset, endLine));
		}
		
		int index = stack.contains(node);
		if(index != -1){ // Cycle found.
			IConstructor cycle = vf.constructor(Factory.Tree_Cycle, ProductionAdapter.getRhs(node.firstProduction), vf.integer(depth - index));
			cycle = actionExecutor.filterCycle(cycle);
			if(cycle == null){
				cycle = vf.constructor(Factory.Tree_Error_Cycle, ProductionAdapter.getRhs(node.firstProduction), vf.integer(depth - index));
			}
			
			if(sourceLocation != null) cycle = cycle.setAnnotation(Factory.Location, sourceLocation);
			
			cycleMark.setMark(index);
			
			return cycle;
		}
		
		int childDepth = depth + 1;
		
		stack.push(node, depth); // Push.
		
		// Gather
		HashMap<ArrayList<Link>, IConstructor[]> sharedPrefixCache = new HashMap<ArrayList<Link>, IConstructor[]>();
		DoubleArrayList<IConstructor[], IConstructor> gatheredAlternatives = new DoubleArrayList<IConstructor[], IConstructor>();
		gatherAlternatives(node.firstAlternative, gatheredAlternatives, node.firstProduction, stack, childDepth, cycleMark, sharedPrefixCache, positionStore, actionExecutor);
		if(node.alternatives != null){
			for(int i = node.alternatives.size() - 1; i >= 0; --i){
				gatherAlternatives(node.alternatives.get(i), gatheredAlternatives, node.productions.get(i), stack, childDepth, cycleMark, sharedPrefixCache, positionStore, actionExecutor);
			}
		}
		
		// Output.
		IConstructor result = null;
		
		int nrOfAlternatives = gatheredAlternatives.size();
		if(nrOfAlternatives == 1){ // Not ambiguous.
			IConstructor production = gatheredAlternatives.getSecond(0);
			IValue[] alternative = gatheredAlternatives.getFirst(0);
			result = buildAlternative(production, alternative, node.rejected);
			result = actionExecutor.filterProduction(result);
			if(result == null){
				// Build error alternative.
				result = buildAlternative(production, alternative, true);
			}
			if(sourceLocation != null) result = result.setAnnotation(Factory.Location, sourceLocation);
		}else if(nrOfAlternatives > 0){ // Ambiguous.
			ISetWriter ambSetWriter = vf.setWriter(Factory.Tree);
			IConstructor lastAlternative = null;
			
			for(int i = nrOfAlternatives - 1; i >= 0; --i){
				IConstructor production = gatheredAlternatives.getSecond(i);
				IValue[] alternative = gatheredAlternatives.getFirst(i);
				
				IConstructor alt = buildAlternative(production, alternative, node.rejected);
				alt = actionExecutor.filterProduction(alt);
				if(alt != null){
					if(sourceLocation != null) alt = alt.setAnnotation(Factory.Location, sourceLocation);
					lastAlternative = alt;
					ambSetWriter.insert(alt);
				}
			}
			
			if(ambSetWriter.size() == 1){
				result = lastAlternative;
			}else if(ambSetWriter.size() == 0){
				// Build error trees for the alternatives.
				for(int i = nrOfAlternatives - 1; i >= 0; --i){
					IConstructor production = gatheredAlternatives.getSecond(i);
					IValue[] alternative = gatheredAlternatives.getFirst(i);
					
					IConstructor alt = buildAlternative(production, alternative, true);
					if(sourceLocation != null) alt = alt.setAnnotation(Factory.Location, sourceLocation);
					ambSetWriter.insert(alt);
				}
				
				result = vf.constructor(Factory.Tree_Error_Amb, ambSetWriter.done());
				// Don't filter error ambs.
			}else{
				result = vf.constructor(Factory.Tree_Amb, ambSetWriter.done());
				result = actionExecutor.filterAmbiguity(result);
				if(result == null){
					// Build error amb.
					result = vf.constructor(Factory.Tree_Error_Amb, ambSetWriter.done());
				}
				
				if(sourceLocation != null) result = result.setAnnotation(Factory.Location, sourceLocation);
			}
		}
		
		stack.dirtyPurge(); // Pop.
		
		return result;
	}
	
	// Temp.
	/*public static IConstructor toErrorListTree(ListContainerNode node, IndexedStack<AbstractNode> stack, int depth, CycleMark cycleMark, PositionStore positionStore, IActionExecutor actionExecutor){
		return node.toTree(stack, depth, cycleMark, positionStore, new FilteringTracker(), actionExecutor);
	}*/
}