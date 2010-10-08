package org.rascalmpl.semantics.dynamic;

public abstract class Symbol extends org.rascalmpl.ast.Symbol {

	static public class Sequence extends org.rascalmpl.ast.Symbol.Sequence {

		public Sequence(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2,
				java.util.List<org.rascalmpl.ast.Symbol> __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Optional extends org.rascalmpl.ast.Symbol.Optional {

		public Optional(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class IterSep extends org.rascalmpl.ast.Symbol.IterSep {

		public IterSep(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2,
				org.rascalmpl.ast.StrCon __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class IterStarSep extends
			org.rascalmpl.ast.Symbol.IterStarSep {

		public IterStarSep(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2,
				org.rascalmpl.ast.StrCon __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class IterStar extends org.rascalmpl.ast.Symbol.IterStar {

		public IterStar(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Iter extends org.rascalmpl.ast.Symbol.Iter {

		public Iter(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class CharacterClass extends
			org.rascalmpl.ast.Symbol.CharacterClass {

		public CharacterClass(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.CharClass __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Literal extends org.rascalmpl.ast.Symbol.Literal {

		public Literal(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.StrCon __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Sort extends org.rascalmpl.ast.Symbol.Sort {

		public Sort(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.QualifiedName __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class CaseInsensitiveLiteral extends
			org.rascalmpl.ast.Symbol.CaseInsensitiveLiteral {

		public CaseInsensitiveLiteral(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.SingleQuotedStrCon __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Ambiguity extends org.rascalmpl.ast.Symbol.Ambiguity {

		public Ambiguity(org.eclipse.imp.pdb.facts.INode __param1,
				java.util.List<org.rascalmpl.ast.Symbol> __param2) {
			super(__param1, __param2);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Empty extends org.rascalmpl.ast.Symbol.Empty {

		public Empty(org.eclipse.imp.pdb.facts.INode __param1) {
			super(__param1);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}

	static public class Alternative extends
			org.rascalmpl.ast.Symbol.Alternative {

		public Alternative(org.eclipse.imp.pdb.facts.INode __param1,
				org.rascalmpl.ast.Symbol __param2,
				org.rascalmpl.ast.Symbol __param3) {
			super(__param1, __param2, __param3);
		}

		@Override
		public <T> T __evaluate(org.rascalmpl.ast.NullASTVisitor<T> __eval) {
			return null;
		}

	}
}