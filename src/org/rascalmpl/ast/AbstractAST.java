package org.rascalmpl.ast;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.INode;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.Type;
import org.rascalmpl.interpreter.BooleanEvaluator;
import org.rascalmpl.interpreter.Evaluator;
import org.rascalmpl.interpreter.PatternEvaluator;
import org.rascalmpl.interpreter.asserts.ImplementationError;
import org.rascalmpl.interpreter.debug.DebuggableEvaluator;
import org.rascalmpl.interpreter.debug.DebuggingDecorator;
import org.rascalmpl.interpreter.matching.IBooleanResult;
import org.rascalmpl.interpreter.matching.IMatchingResult;
import org.rascalmpl.interpreter.result.Result;
import org.rascalmpl.values.uptr.TreeAdapter;

public abstract class AbstractAST implements IVisitable {
	protected INode node;
	protected ASTStatistics stats = new ASTStatistics();
	protected Type _type = null;
	
	public Type _getType() {
	  return _type;
	}
	
	public void _setType(Type nonterminalType) {
	  if (_type != null) {
	    throw new ImplementationError("why set a type twice?");
	  }
	  this._type = nonterminalType;
	}
	
	abstract public <T> T accept(IASTVisitor<T> v);

	public ISourceLocation getLocation() {
		return TreeAdapter.getLocation((IConstructor) node);
	}

	public ASTStatistics getStats() {
		return stats;
	}
	
	public void setStats(ASTStatistics stats) {
		this.stats = stats;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (getClass() == obj.getClass()) {
			if (obj == this) {
				return true;
			}

			AbstractAST other = (AbstractAST) obj;

			if (other.node == node) {
				return true;
			}

			if (other.node.equals(node)) {
				return other.node.getAnnotation("loc").isEqual(
						node.getAnnotation("loc"));
			}
		}
		return false;
	}

	public INode getTree() {
		return node;
	}

	@Override
	public int hashCode() {
		return node.hashCode();
	}

	@Override
	public String toString() {
		return TreeAdapter.yield((IConstructor) node);
	}
	
	// NOTE: Should be abstract, but this breaks everything, so, for
	// now, provide a default implementation, but...
	// TODO: Make this abstract again!
//	public abstract Result<IValue> eval(Evaluator eval);
	public Result<IValue> evaluator(Evaluator eval) { return null; }
	
	// TODO: Same for this
//	public abstract Result<IValue> debugEval(DebuggableEvaluator eval);
	public Result<IValue> evaluator(DebuggableEvaluator eval) { return null; }
	
	// TODO: Same for this
//	public abstract <T> T debugDecorator(DebuggingDecorator<T> ddec);
	public <T> T evaluator(DebuggingDecorator<T> ddec) { return null; }
	
	// TODO: Same for this
//	public abstract IBooleanResult booleanEval(BooleanEvaluator eval);
	public IBooleanResult evaluator(BooleanEvaluator eval) { return null; }
	
	// TODO: Same for this
//	public abstract IMatchingResult patternEval(PatternEvaluator eval);
	public IMatchingResult evaluator(PatternEvaluator eval) { return null; }
	
}
