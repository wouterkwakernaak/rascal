package org.rascalmpl.semantics.dynamic;

public abstract class StringTail extends org.rascalmpl.ast.StringTail {

	static public class Post extends org.rascalmpl.ast.StringTail.Post {

		public Post(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.PostStringChars __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			return this.getPost().__evaluate(__eval);

		}

	}

	static public class MidTemplate extends
			org.rascalmpl.ast.StringTail.MidTemplate {

		public MidTemplate(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.MidStringChars __param2,
				org.rascalmpl.ast.StringTemplate __param3,
				org.rascalmpl.ast.StringTail __param4) {
			super(__param1, __param2, __param3, __param4);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			org.rascalmpl.ast.Statement mid = this.getMid().__evaluate(__eval);
			org.rascalmpl.ast.Statement template = this.getTemplate().__evaluate(
					__eval);
			org.rascalmpl.ast.Statement tail = this.getTail().__evaluate(__eval);
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor
					.makeBlock(this.getTree(), mid, template, tail);

		}

	}

	static public class MidInterpolated extends
			org.rascalmpl.ast.StringTail.MidInterpolated {

		public MidInterpolated(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.MidStringChars __param2,
				org.rascalmpl.ast.Expression __param3,
				org.rascalmpl.ast.StringTail __param4) {
			super(__param1, __param2, __param3, __param4);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			org.rascalmpl.ast.Statement mid = this.getMid().__evaluate(__eval);
			org.rascalmpl.ast.Statement exp = __eval.makeAppend(this
					.getExpression());
			org.rascalmpl.ast.Statement tail = this.getTail().__evaluate(__eval);
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor
					.makeBlock(this.getTree(), mid, exp, tail);

		}

	}

	static public class Ambiguity extends
			org.rascalmpl.ast.StringTail.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.StringTail> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}