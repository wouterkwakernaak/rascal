package org.rascalmpl.semantics.dynamic;

public abstract class OptCharRanges extends org.rascalmpl.ast.OptCharRanges {

	static public class Ambiguity extends
			org.rascalmpl.ast.OptCharRanges.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.OptCharRanges> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Absent extends org.rascalmpl.ast.OptCharRanges.Absent {

		public Absent(org.eclipse.imp.pdb.facts.INode __param1) {
			super(__param1);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Present extends org.rascalmpl.ast.OptCharRanges.Present {

		public Present(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharRanges __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}