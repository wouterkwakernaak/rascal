package org.rascalmpl.semantics.concrete;

import org.eclipse.imp.pdb.facts.INode;
import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.ast.IASTVisitor;
import org.rascalmpl.interpreter.IEvaluatorContext;
import org.rascalmpl.interpreter.matching.IBooleanResult;
import org.rascalmpl.interpreter.matching.IMatchingResult;
import org.rascalmpl.interpreter.result.Result;
import org.rascalmpl.interpreter.staticErrors.UnexpectedTypeError;
import org.rascalmpl.interpreter.staticErrors.UnsupportedPatternError;

public class Expression extends org.rascalmpl.ast.Expression {
	static public class Addition extends org.rascalmpl.ast.Expression.Addition {

		public Addition(INode node, Expression lhs, Expression rhs) {
			super(node, lhs, rhs);
		}

		@Override
		public Result<IValue> eval(IEvaluatorContext ctx) {
			Result<IValue> left = getLhs().eval(ctx);
			Result<IValue> right = getRhs().eval(ctx);
			return left.add(right);			
		}
		
		@Override
		public Result<IValue> debugEval(IEvaluatorContext ctx) {
			// NOTE: suspend is a private method of the debug evaluator, will need to make it public
			//suspend(this);
			//return eval(ctx);
			return super.debugEval(ctx);
		}
		
		@Override
		public IBooleanResult booleanEval(IEvaluatorContext ctx) {
			// NOTE: Here we also have private fields from the evaluator: tf
//			throw new UnexpectedTypeError(tf.boolType(), x.accept(
//					ctx.getEvaluator()).getType(), x);
			return super.booleanEval(ctx);

		}

		@Override
		public IMatchingResult patternEval(IEvaluatorContext ctx) {
			throw new UnsupportedPatternError(this.toString(), this);
		}

		@Override
		public <T> T debugDecorator(IEvaluatorContext ctx) {
			// TODO: The following two lines are from DebuggingDecorator, but depend on private information
//			suspend(x);
//			return evaluator.visitExpressionAddition(x);
			// TODO Auto-generated method stub
			return super.debugDecorator(ctx);
		}
	}

	@Override
	public <T> T accept(IASTVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
