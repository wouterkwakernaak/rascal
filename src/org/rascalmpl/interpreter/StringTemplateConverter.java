package org.rascalmpl.interpreter;

import org.rascalmpl.ast.ASTFactory;
import org.rascalmpl.ast.ASTFactoryFactory;

public class StringTemplateConverter {
	private static int labelCounter = 0;
	
	private static org.rascalmpl.ast.Statement surroundWithSingleIterForLoop(org.eclipse.imp.pdb.facts.INode src, org.rascalmpl.ast.Name label, org.rascalmpl.ast.Statement body) {
		ASTFactory factory = ASTFactoryFactory.getASTFactory();
		org.rascalmpl.ast.Name dummy = factory.makeNameLexical(src, "_");
		org.rascalmpl.ast.Expression var = factory.makeExpressionQualifiedName(src, new org.rascalmpl.ast.QualifiedName.Default(src, java.util.Arrays.asList(dummy)));
		org.rascalmpl.ast.Expression truth = factory.makeExpressionLiteral(src, new org.rascalmpl.ast.Literal.Boolean(src, new org.rascalmpl.ast.BooleanLiteral.Lexical(src, "true")));
		org.rascalmpl.ast.Expression list = factory.makeExpressionList(src, java.util.Arrays.asList(truth));
		org.rascalmpl.ast.Expression enumerator = factory.makeExpressionEnumerator(src, var, list);
		org.rascalmpl.ast.Statement stat = factory.makeStatementFor(src, new org.rascalmpl.ast.Label.Default(src, label), java.util.Arrays.asList(enumerator), body);
		return stat;
	}


	public static org.rascalmpl.ast.Statement convert(org.rascalmpl.ast.StringLiteral str) {
		ASTFactory factory = ASTFactoryFactory.getASTFactory();
		final org.rascalmpl.ast.Name label= factory.makeNameLexical(null, "#" + StringTemplateConverter.labelCounter);
		StringTemplateConverter.labelCounter++;
//		return org.rascalmpl.interpreter.StringTemplateConverter.surroundWithSingleIterForLoop(str.getTree(), label, str.accept(new org.rascalmpl.interpreter.StringTemplateConverter.Visitor(label)));
		return org.rascalmpl.interpreter.StringTemplateConverter.surroundWithSingleIterForLoop(str.getTree(), label, str.__evaluate(new org.rascalmpl.interpreter.StringTemplateConverter.Visitor(label)));
	}
	
	public static class Visitor extends org.rascalmpl.ast.NullASTVisitor<org.rascalmpl.ast.Statement> {
		
		private final org.rascalmpl.ast.Name label;

		public Visitor(org.rascalmpl.ast.Name label) {
			this.label = label;
		}

		public static org.rascalmpl.ast.Statement makeBlock(org.eclipse.imp.pdb.facts.INode src, org.rascalmpl.ast.Statement ...stats) {
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor.makeBlock(src, java.util.Arrays.asList(stats));
		}
		
		private static org.rascalmpl.ast.Statement makeBlock(org.eclipse.imp.pdb.facts.INode src, java.util.List<org.rascalmpl.ast.Statement> stats) {
			ASTFactory factory = ASTFactoryFactory.getASTFactory();
			return factory.makeStatementNonEmptyBlock(src, new org.rascalmpl.ast.Label.Empty(src),
					stats);
		}

		
		public org.rascalmpl.ast.Statement makeAppend(org.rascalmpl.ast.Expression exp) {
			ASTFactory factory = ASTFactoryFactory.getASTFactory();
			return factory.makeStatementAppend(exp.getTree(), factory.makeDataTargetLabeled(null, this.label),
					factory.makeStatementExpression(exp.getTree(), exp)); 
		}
		
		public static org.rascalmpl.ast.Statement combinePreBodyPost(org.eclipse.imp.pdb.facts.INode src, java.util.List<org.rascalmpl.ast.Statement> pre, org.rascalmpl.ast.Statement body, java.util.List<org.rascalmpl.ast.Statement> post) {
			java.util.List<org.rascalmpl.ast.Statement> stats = new java.util.ArrayList<org.rascalmpl.ast.Statement>();
			stats.addAll(pre);
			stats.add(body);
			stats.addAll(post);
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor.makeBlock(src, stats);
		}
		
		
		public static org.rascalmpl.ast.Expression makeLit(org.eclipse.imp.pdb.facts.INode src, java.lang.String str) {
			ASTFactory factory = ASTFactoryFactory.getASTFactory();
			// Note: we don't unescape here this happens
			// in the main evaluator; also, we pretend 
			// "...< etc. to be "..." stringliterals...
			return factory.makeExpressionLiteral(src, 
					factory.makeLiteralString(src, 
							factory.makeStringLiteralNonInterpolated(src, 
									factory.makeStringConstantLexical(src, str))));
		}
		
	
	}
}
