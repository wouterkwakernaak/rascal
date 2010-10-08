package org.rascalmpl.semantics.dynamic;

public abstract class MidStringChars extends org.rascalmpl.ast.MidStringChars {

	static public class Ambiguity extends
			org.rascalmpl.ast.MidStringChars.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.MidStringChars> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Lexical extends
			org.rascalmpl.ast.MidStringChars.Lexical {

		public Lexical(org.eclipse.imp.pdb.facts.INode __param1,
				java.lang.String __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

		@Override
		public org.rascalmpl.ast.Statement __evaluate(
				org.rascalmpl.interpreter.StringTemplateConverter.Visitor __eval) {

			return __eval
					.makeAppend(org.rascalmpl.interpreter.StringTemplateConverter.Visitor
							.makeLit(this.getTree(), this.getString()));

		}

	}
}