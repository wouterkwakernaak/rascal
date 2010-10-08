package org.rascalmpl.semantics.dynamic;

public abstract class Test extends org.rascalmpl.ast.Test {

	static public class Labeled extends org.rascalmpl.ast.Test.Labeled {

		public Labeled(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Tags __param2,
				org.rascalmpl.ast.Expression __param3,
				org.rascalmpl.ast.StringLiteral __param4) {
			super(__param1, __param2, __param3, __param4);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IBool> __evaluate(
				org.rascalmpl.interpreter.TestEvaluator.Visitor __eval) {

			org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IValue> result = org.rascalmpl.interpreter.result.ResultFactory
					.bool(true, __eval.__getEval());
			// System.err.println("visitTestLabeled: " + this);

			try {
				result = this.getExpression().__evaluate(__eval.__getEval());
			} catch (org.rascalmpl.interpreter.control_exceptions.Throw e) {
				__eval.__getTestResultListener().report(false, this.toString(),
						this.getLocation(), e);
			} catch (java.lang.Throwable e) {
				__eval.__getTestResultListener().report(false, this.toString(),
						this.getLocation(), e);
			}

			__eval.__getTestResultListener().report(result.isTrue(),
					this.toString(), this.getLocation());

			return org.rascalmpl.interpreter.result.ResultFactory.bool(result
					.isTrue(), __eval.__getEval());

		}

		@Override
		public org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IValue> __evaluate(
				org.rascalmpl.interpreter.Evaluator __eval) {

			__eval.getCurrentModuleEnvironment().addTest(this);
			return org.rascalmpl.interpreter.result.ResultFactory.nothing();

		}

	}

	static public class Ambiguity extends org.rascalmpl.ast.Test.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.Test> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Unlabeled extends org.rascalmpl.ast.Test.Unlabeled {

		public Unlabeled(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Tags __param2,
				org.rascalmpl.ast.Expression __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IBool> __evaluate(
				org.rascalmpl.interpreter.TestEvaluator.Visitor __eval) {

			org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IValue> result = org.rascalmpl.interpreter.result.ResultFactory
					.bool(true, __eval.__getEval());
			// System.err.println("visitTestUnlabeled: " + this);

			try {
				result = this.getExpression().__evaluate(__eval.__getEval());
			} catch (org.rascalmpl.interpreter.staticErrors.StaticError e) {
				__eval.__getTestResultListener().report(false, this.toString(),
						this.getLocation(), e);
			} catch (org.rascalmpl.interpreter.control_exceptions.Throw e) {
				__eval.__getTestResultListener().report(false, this.toString(),
						this.getLocation(), e);
			} catch (java.lang.Throwable e) {
				__eval.__getTestResultListener().report(false, this.toString(),
						this.getLocation(), e);
			}

			__eval.__getTestResultListener().report(result.isTrue(),
					this.toString(), this.getLocation());

			return org.rascalmpl.interpreter.result.ResultFactory.bool(result
					.isTrue(), __eval.__getEval());

		}

		@Override
		public org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IValue> __evaluate(
				org.rascalmpl.interpreter.Evaluator __eval) {

			__eval.getCurrentModuleEnvironment().addTest(this);
			return org.rascalmpl.interpreter.result.ResultFactory.nothing();

		}

	}
}