package org.rascalmpl.semantics.dynamic;

public abstract class StringMiddle extends org.rascalmpl.ast.StringMiddle {

	static public class Ambiguity extends
			org.rascalmpl.ast.StringMiddle.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.StringMiddle> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Template extends
			org.rascalmpl.ast.StringMiddle.Template {

		public Template(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.MidStringChars __param2,
				org.rascalmpl.ast.StringTemplate __param3,
				org.rascalmpl.ast.StringMiddle __param4) {
			super(__param1, __param2, __param3, __param4);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			org.rascalmpl.ast.Statement mid = this.getMid().accept(__eval);
			org.rascalmpl.ast.Statement tmp = this.getTemplate().accept(__eval);
			org.rascalmpl.ast.Statement tail = this.getTail().accept(__eval);
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor
					.makeBlock(this.getTree(), mid, tmp, tail);

		}

	}

	static public class Mid extends org.rascalmpl.ast.StringMiddle.Mid {

		public Mid(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.MidStringChars __param2) {
			super(__param1, __param2);
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			return this.getMid().accept(__eval);

		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Interpolated extends
			org.rascalmpl.ast.StringMiddle.Interpolated {

		public Interpolated(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.MidStringChars __param2,
				org.rascalmpl.ast.Expression __param3,
				org.rascalmpl.ast.StringMiddle __param4) {
			super(__param1, __param2, __param3, __param4);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			org.rascalmpl.ast.Statement mid = this.getMid().accept(__eval);
			org.rascalmpl.ast.Statement exp = __eval.makeAppend(this
					.getExpression());
			org.rascalmpl.ast.Statement tail = this.getTail().accept(__eval);
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor
					.makeBlock(this.getTree(), mid, exp, tail);

		}

	}
}