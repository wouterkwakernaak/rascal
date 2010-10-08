package org.rascalmpl.semantics.dynamic;

public abstract class CharClass extends org.rascalmpl.ast.CharClass {

	static public class Complement extends
			org.rascalmpl.ast.CharClass.Complement {

		public Complement(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharClass __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class SimpleCharclass extends
			org.rascalmpl.ast.CharClass.SimpleCharclass {

		public SimpleCharclass(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.OptCharRanges __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Union extends org.rascalmpl.ast.CharClass.Union {

		public Union(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharClass __param2,
				org.rascalmpl.ast.CharClass __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Ambiguity extends org.rascalmpl.ast.CharClass.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.CharClass> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Difference extends
			org.rascalmpl.ast.CharClass.Difference {

		public Difference(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharClass __param2,
				org.rascalmpl.ast.CharClass __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Intersection extends
			org.rascalmpl.ast.CharClass.Intersection {

		public Intersection(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharClass __param2,
				org.rascalmpl.ast.CharClass __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Bracket extends org.rascalmpl.ast.CharClass.Bracket {

		public Bracket(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharClass __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}