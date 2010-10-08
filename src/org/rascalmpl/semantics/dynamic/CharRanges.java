package org.rascalmpl.semantics.dynamic;

public abstract class CharRanges extends org.rascalmpl.ast.CharRanges {

	static public class Bracket extends org.rascalmpl.ast.CharRanges.Bracket {

		public Bracket(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharRanges __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Range extends org.rascalmpl.ast.CharRanges.Range {

		public Range(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharRange __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Ambiguity extends
			org.rascalmpl.ast.CharRanges.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.CharRanges> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Concatenate extends
			org.rascalmpl.ast.CharRanges.Concatenate {

		public Concatenate(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharRanges __param2,
				org.rascalmpl.ast.CharRanges __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}