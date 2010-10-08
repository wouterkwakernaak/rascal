package org.rascalmpl.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.imp.pdb.facts.INode;
import org.rascalmpl.ast.BooleanLiteral;
import org.rascalmpl.ast.DataTarget;
import org.rascalmpl.ast.Expression;
import org.rascalmpl.ast.Label;
import org.rascalmpl.ast.Name;
import org.rascalmpl.ast.NullASTVisitor;
import org.rascalmpl.ast.QualifiedName;
import org.rascalmpl.ast.Statement;
import org.rascalmpl.ast.StringConstant;
import org.rascalmpl.ast.StringLiteral;
import org.rascalmpl.ast.MidStringChars.Lexical;
import org.rascalmpl.ast.StringLiteral.NonInterpolated;
import org.rascalmpl.ast.StringMiddle.Interpolated;
import org.rascalmpl.ast.StringMiddle.Mid;
import org.rascalmpl.ast.StringMiddle.Template;
import org.rascalmpl.ast.StringTail.Post;
import org.rascalmpl.ast.StringTemplate.DoWhile;
import org.rascalmpl.ast.StringTemplate.For;
import org.rascalmpl.ast.StringTemplate.IfThen;
import org.rascalmpl.ast.StringTemplate.IfThenElse;
import org.rascalmpl.ast.StringTemplate.While;

public class StringTemplateConverter {
	private static int labelCounter = 0;
	
	private static org.rascalmpl.ast.Statement surroundWithSingleIterForLoop(org.eclipse.imp.pdb.facts.INode src, org.rascalmpl.ast.Name label, org.rascalmpl.ast.Statement body) {
		org.rascalmpl.ast.Name dummy = new org.rascalmpl.ast.Name.Lexical(src, "_");
		org.rascalmpl.ast.Expression var = new org.rascalmpl.ast.Expression.QualifiedName(src, new org.rascalmpl.ast.QualifiedName.Default(src, java.util.Arrays.asList(dummy)));
		org.rascalmpl.ast.Expression truth = new org.rascalmpl.ast.Expression.Literal(src, new org.rascalmpl.ast.Literal.Boolean(src, new org.rascalmpl.ast.BooleanLiteral.Lexical(src, "true")));
		org.rascalmpl.ast.Expression list = new org.rascalmpl.ast.Expression.List(src, java.util.Arrays.asList(truth));
		org.rascalmpl.ast.Expression enumerator = new org.rascalmpl.ast.Expression.Enumerator(src, var, list);
		org.rascalmpl.ast.Statement stat = new org.rascalmpl.ast.Statement.For(src, new org.rascalmpl.ast.Label.Default(src, label), java.util.Arrays.asList(enumerator), body);
		return stat;
	}


	public static org.rascalmpl.ast.Statement convert(org.rascalmpl.ast.StringLiteral str) {
		final org.rascalmpl.ast.Name label= new org.rascalmpl.ast.Name.Lexical(null, "#" + StringTemplateConverter.labelCounter);
		StringTemplateConverter.labelCounter++;
		return org.rascalmpl.interpreter.StringTemplateConverter.surroundWithSingleIterForLoop(str.getTree(), label, str.accept(new org.rascalmpl.interpreter.StringTemplateConverter.Visitor(label)));
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
			return new org.rascalmpl.ast.Statement.NonEmptyBlock(src, new org.rascalmpl.ast.Label.Empty(src),
					stats);
		}

		
		public org.rascalmpl.ast.Statement makeAppend(org.rascalmpl.ast.Expression exp) {
			return new org.rascalmpl.ast.Statement.Append(exp.getTree(), new org.rascalmpl.ast.DataTarget.Labeled(null, this.label),
					new org.rascalmpl.ast.Statement.Expression(exp.getTree(), exp)); 
		}
		
		public static org.rascalmpl.ast.Statement combinePreBodyPost(org.eclipse.imp.pdb.facts.INode src, java.util.List<org.rascalmpl.ast.Statement> pre, org.rascalmpl.ast.Statement body, java.util.List<org.rascalmpl.ast.Statement> post) {
			java.util.List<org.rascalmpl.ast.Statement> stats = new java.util.ArrayList<org.rascalmpl.ast.Statement>();
			stats.addAll(pre);
			stats.add(body);
			stats.addAll(post);
			return org.rascalmpl.interpreter.StringTemplateConverter.Visitor.makeBlock(src, stats);
		}
		
		
		public static org.rascalmpl.ast.Expression makeLit(org.eclipse.imp.pdb.facts.INode src, java.lang.String str) {
			// Note: we don't unescape here this happens
			// in the main evaluator; also, we pretend 
			// "...< etc. to be "..." stringliterals...
			return new org.rascalmpl.ast.Expression.Literal(src, 
					new org.rascalmpl.ast.Literal.String(src, 
							new org.rascalmpl.ast.StringLiteral.NonInterpolated(src, 
									new org.rascalmpl.ast.StringConstant.Lexical(src, str))));
		}
		
	
	}
}
