package org.rascalmpl.semantics.dynamic;

public abstract class CharRange extends org.rascalmpl.ast.CharRange {

	static public class Ambiguity extends org.rascalmpl.ast.CharRange.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.CharRange> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Range extends org.rascalmpl.ast.CharRange.Range {

		public Range(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Character __param2,
				org.rascalmpl.ast.Character __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Character extends org.rascalmpl.ast.CharRange.Character {

		public Character(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Character __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}