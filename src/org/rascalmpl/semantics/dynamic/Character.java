package org.rascalmpl.semantics.dynamic;

public abstract class Character extends org.rascalmpl.ast.Character {

	static public class Top extends org.rascalmpl.ast.Character.Top {

		public Top(org.eclipse.imp.pdb.facts.INode __param1) {
			super(__param1);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Bottom extends org.rascalmpl.ast.Character.Bottom {

		public Bottom(org.eclipse.imp.pdb.facts.INode __param1) {
			super(__param1);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Ambiguity extends org.rascalmpl.ast.Character.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.Character> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Numeric extends org.rascalmpl.ast.Character.Numeric {

		public Numeric(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.NumChar __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Short extends org.rascalmpl.ast.Character.Short {

		public Short(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.ShortChar __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class EOF extends org.rascalmpl.ast.Character.EOF {

		public EOF(org.eclipse.imp.pdb.facts.INode __param1) {
			super(__param1);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}