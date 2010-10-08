package org.rascalmpl.interpreter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.exceptions.FactTypeDeclarationException;
import org.eclipse.imp.pdb.facts.exceptions.FactTypeRedeclaredException;
import org.eclipse.imp.pdb.facts.exceptions.RedeclaredFieldNameException;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.rascalmpl.ast.Declaration;
import org.rascalmpl.ast.Import;
import org.rascalmpl.ast.Nonterminal;
import org.rascalmpl.ast.NullASTVisitor;
import org.rascalmpl.ast.QualifiedName;
import org.rascalmpl.ast.Sym;
import org.rascalmpl.ast.Toplevel;
import org.rascalmpl.ast.TypeArg;
import org.rascalmpl.ast.TypeVar;
import org.rascalmpl.ast.UserType;
import org.rascalmpl.ast.Variant;
import org.rascalmpl.ast.Declaration.Alias;
import org.rascalmpl.ast.Declaration.Data;
import org.rascalmpl.ast.Declaration.DataAbstract;
import org.rascalmpl.ast.Toplevel.GivenVisibility;
import org.rascalmpl.interpreter.asserts.ImplementationError;
import org.rascalmpl.interpreter.env.Environment;
import org.rascalmpl.interpreter.result.ConstructorFunction;
import org.rascalmpl.interpreter.staticErrors.IllegalQualifiedDeclaration;
import org.rascalmpl.interpreter.staticErrors.RedeclaredFieldError;
import org.rascalmpl.interpreter.staticErrors.RedeclaredTypeError;
import org.rascalmpl.interpreter.staticErrors.SyntaxError;
import org.rascalmpl.interpreter.staticErrors.UndeclaredTypeError;
import org.rascalmpl.interpreter.utils.Names;
import org.rascalmpl.values.uptr.Factory;


public class TypeDeclarationEvaluator {
	private org.rascalmpl.interpreter.Evaluator eval;

	public TypeDeclarationEvaluator(org.rascalmpl.interpreter.Evaluator eval) {
		this.eval = eval;
	}

	private org.rascalmpl.interpreter.env.Environment env;

	public void evaluateDeclarations(java.util.List<org.rascalmpl.ast.Toplevel> decls, org.rascalmpl.interpreter.env.Environment env) {
		this.env = env;
		java.util.Set<org.rascalmpl.ast.UserType> abstractDataTypes = new java.util.HashSet<org.rascalmpl.ast.UserType>();
		java.util.Set<org.rascalmpl.ast.Declaration.Data> constructorDecls = new java.util.HashSet<org.rascalmpl.ast.Declaration.Data>();
		java.util.Set<org.rascalmpl.ast.Declaration.Alias> aliasDecls = new java.util.HashSet<org.rascalmpl.ast.Declaration.Alias>();

		// this code is very much order dependent
		this.collectDeclarations(decls, abstractDataTypes, constructorDecls,
				aliasDecls);
		this.declareAbstractDataTypes(abstractDataTypes);
		this.declareAliases(aliasDecls);
		this.declareConstructors(constructorDecls);
	}
	
	public void evaluateSyntaxDefinitions(java.util.List<org.rascalmpl.ast.Import> imports,
			org.rascalmpl.interpreter.env.Environment env) {
		for (org.rascalmpl.ast.Import i : imports) {
			if (i.isSyntax()) {
				// TODO: declare all the embedded regular symbols as well
				this.declareSyntaxType(i.getSyntax().getDefined(), env);
			}
		}
	}

	public void declareSyntaxType(org.rascalmpl.ast.Sym type, org.rascalmpl.interpreter.env.Environment env) {
		org.eclipse.imp.pdb.facts.IValueFactory vf = this.eval.getValueFactory();
		
		if (type.isNonterminal()) {
			java.lang.String nt = ((org.rascalmpl.ast.Nonterminal.Lexical) type.getNonterminal()).getString();
		
			// TODO: at some point the cf wrapper needs to be dropped here...
			env.concreteSyntaxType(nt, (org.eclipse.imp.pdb.facts.IConstructor) Factory.Symbol_Cf.make(vf, Factory.Symbol_Sort.make(vf, vf.string(nt))));
		}
		// do nothing
	}

	private void declareConstructors(java.util.Set<org.rascalmpl.ast.Declaration.Data> constructorDecls) {
		for (org.rascalmpl.ast.Declaration.Data data : constructorDecls) {
			this.declareConstructor(data, this.env);
		}
	}

	public void declareConstructor(org.rascalmpl.ast.Declaration.Data x, org.rascalmpl.interpreter.env.Environment env) {
		org.eclipse.imp.pdb.facts.type.TypeFactory tf = org.eclipse.imp.pdb.facts.type.TypeFactory.getInstance();

		// needs to be done just in case the declaration came
		// from a shell instead of from a module
		org.eclipse.imp.pdb.facts.type.Type adt = this.declareAbstractDataType(x.getUser(), env);

		for (org.rascalmpl.ast.Variant var : x.getVariants()) {
			java.lang.String altName = org.rascalmpl.interpreter.utils.Names.name(var.getName());

			if (var.isNAryConstructor()) {
				java.util.List<org.rascalmpl.ast.TypeArg> args = var.getArguments();
				org.eclipse.imp.pdb.facts.type.Type[] fields = new org.eclipse.imp.pdb.facts.type.Type[args.size()];
				java.lang.String[] labels = new java.lang.String[args.size()];

				for (int i = 0; i < args.size(); i++) {
					org.rascalmpl.ast.TypeArg arg = args.get(i);
					fields[i] = new org.rascalmpl.interpreter.TypeEvaluator(env, null).eval(arg.getType());

					if (fields[i] == null) {
						throw new org.rascalmpl.interpreter.staticErrors.UndeclaredTypeError(arg.getType()
								.toString(), arg);
					}

					if (arg.hasName()) {
						labels[i] = org.rascalmpl.interpreter.utils.Names.name(arg.getName());
					} else {
						labels[i] = "arg" + java.lang.Integer.toString(i);
					}
				}

				org.eclipse.imp.pdb.facts.type.Type children = tf.tupleType(fields, labels);
				try {
					org.rascalmpl.interpreter.result.ConstructorFunction cons = env.constructorFromTuple(var, this.eval, adt, altName, children);
					cons.setPublic(true); // TODO: implement declared visibility
				} catch (org.eclipse.imp.pdb.facts.exceptions.RedeclaredConstructorException e) {
					throw new org.rascalmpl.interpreter.staticErrors.RedeclaredTypeError(altName, var);
				} catch (org.eclipse.imp.pdb.facts.exceptions.RedeclaredFieldNameException e) {
					throw new org.rascalmpl.interpreter.staticErrors.RedeclaredFieldError(e.getMessage(), var);
				}
			} 
		}
	}

	public void declareAbstractADT(org.rascalmpl.ast.Declaration.DataAbstract x, org.rascalmpl.interpreter.env.Environment env) {
		org.eclipse.imp.pdb.facts.type.TypeFactory.getInstance();
		this.declareAbstractDataType(x.getUser(), env);
	}
	
	private void declareAliases(java.util.Set<org.rascalmpl.ast.Declaration.Alias> aliasDecls) {
		java.util.List<org.rascalmpl.ast.Declaration.Alias> todo = new java.util.LinkedList<org.rascalmpl.ast.Declaration.Alias>();
		todo.addAll(aliasDecls);
		
		int len = todo.size();
		int i = 0;
		while (!todo.isEmpty()) {
			org.rascalmpl.ast.Declaration.Alias trial = todo.remove(0);
			try {
				this.declareAlias(trial, this.env);
				i--;
			}
			catch (org.rascalmpl.interpreter.staticErrors.UndeclaredTypeError e) {
				if (i >= len) {
					// Cycle
					throw e;
				}
				// Put at end of queue
				todo.add(trial);
			}
			i++;
		}
	}
	
	public void declareAlias(org.rascalmpl.ast.Declaration.Alias x, org.rascalmpl.interpreter.env.Environment env) {
		try {
			org.eclipse.imp.pdb.facts.type.Type base = new org.rascalmpl.interpreter.TypeEvaluator(env, this.eval.getHeap()).eval(x.getBase());

			if (base == null) {
				throw new org.rascalmpl.interpreter.staticErrors.UndeclaredTypeError(x.getBase().toString(), x
						.getBase());
			}
			
			org.rascalmpl.ast.QualifiedName name = x.getUser().getName();
			if (org.rascalmpl.interpreter.utils.Names.isQualified(name)) {
				throw new org.rascalmpl.interpreter.staticErrors.IllegalQualifiedDeclaration(name);
			}

			env.aliasType(org.rascalmpl.interpreter.utils.Names.typeName(name), base,
					this.computeTypeParameters(x.getUser(), env));
		} 
		catch (org.eclipse.imp.pdb.facts.exceptions.FactTypeRedeclaredException e) {
			throw new org.rascalmpl.interpreter.staticErrors.RedeclaredTypeError(e.getName(), x);
		}
		catch (org.eclipse.imp.pdb.facts.exceptions.FactTypeDeclarationException e) {
			throw new org.rascalmpl.interpreter.asserts.ImplementationError("Unknown FactTypeDeclarationException: " + e.getMessage());
		}
	}

	private void declareAbstractDataTypes(java.util.Set<org.rascalmpl.ast.UserType> abstractDataTypes) {
		for (org.rascalmpl.ast.UserType decl : abstractDataTypes) {
			this.declareAbstractDataType(decl, this.env);
		}
	}

	public org.eclipse.imp.pdb.facts.type.Type declareAbstractDataType(org.rascalmpl.ast.UserType decl, org.rascalmpl.interpreter.env.Environment env) {
		org.rascalmpl.ast.QualifiedName name = decl.getName();
		if (org.rascalmpl.interpreter.utils.Names.isQualified(name)) {
			throw new org.rascalmpl.interpreter.staticErrors.IllegalQualifiedDeclaration(name);
		}
		return env.abstractDataType(org.rascalmpl.interpreter.utils.Names.typeName(name), this.computeTypeParameters(decl, env));
	}

	private org.eclipse.imp.pdb.facts.type.Type[] computeTypeParameters(org.rascalmpl.ast.UserType decl, org.rascalmpl.interpreter.env.Environment env) {
		org.eclipse.imp.pdb.facts.type.TypeFactory tf = org.eclipse.imp.pdb.facts.type.TypeFactory.getInstance();

		org.eclipse.imp.pdb.facts.type.Type[] params;
		if (decl.isParametric()) {
			java.util.List<org.rascalmpl.ast.Type> formals = decl
					.getParameters();
			params = new org.eclipse.imp.pdb.facts.type.Type[formals.size()];
			int i = 0;
			for (org.rascalmpl.ast.Type formal : formals) {
				if (!formal.isVariable()) {
					throw new org.rascalmpl.interpreter.staticErrors.SyntaxError(
							"Declaration of parameterized type with type instance "
									+ formal + " is not allowed", formal.getLocation());
				}
				org.rascalmpl.ast.TypeVar var = formal.getTypeVar();	
				org.eclipse.imp.pdb.facts.type.Type bound = var.hasBound() ? new org.rascalmpl.interpreter.TypeEvaluator(env, null).eval(var.getBound()) : tf
						.valueType();
				params[i++] = tf
						.parameterType(org.rascalmpl.interpreter.utils.Names.name(var.getName()), bound);
			}
		} else {
			params = new org.eclipse.imp.pdb.facts.type.Type[0];
		}
		return params;
	}

	private void collectDeclarations(java.util.List<org.rascalmpl.ast.Toplevel> decls,
			java.util.Set<org.rascalmpl.ast.UserType> abstractDataTypes, java.util.Set<org.rascalmpl.ast.Declaration.Data> constructorDecls,
			java.util.Set<org.rascalmpl.ast.Declaration.Alias> aliasDecls) {
		org.rascalmpl.interpreter.TypeDeclarationEvaluator.DeclarationCollector collector = new org.rascalmpl.interpreter.TypeDeclarationEvaluator.DeclarationCollector(
				abstractDataTypes, constructorDecls, aliasDecls);

		for (org.rascalmpl.ast.Toplevel t : decls) {
//			t.accept(collector);
			t.__evaluate(collector);
		}
	}

	public class DeclarationCollector extends org.rascalmpl.ast.NullASTVisitor<org.rascalmpl.ast.Declaration> {
		private java.util.Set<org.rascalmpl.ast.UserType> abstractDataTypes;
		private java.util.Set<org.rascalmpl.ast.Declaration.Data> constructorDecls;
		private java.util.Set<org.rascalmpl.ast.Declaration.Alias> aliasDecls;

		public DeclarationCollector(java.util.Set<org.rascalmpl.ast.UserType> abstractDataTypes,
				java.util.Set<org.rascalmpl.ast.Declaration.Data> constructorDecls, java.util.Set<org.rascalmpl.ast.Declaration.Alias> aliasDecls) {
			this.__setAbstractDataTypes(abstractDataTypes);
			this.__setConstructorDecls(constructorDecls);
			this.__setAliasDecls(aliasDecls);
		}

		public void __setAliasDecls(java.util.Set<org.rascalmpl.ast.Declaration.Alias> aliasDecls) {
			this.aliasDecls = aliasDecls;
		}

		public java.util.Set<org.rascalmpl.ast.Declaration.Alias> __getAliasDecls() {
			return aliasDecls;
		}

		public void __setAbstractDataTypes(java.util.Set<org.rascalmpl.ast.UserType> abstractDataTypes) {
			this.abstractDataTypes = abstractDataTypes;
		}

		public java.util.Set<org.rascalmpl.ast.UserType> __getAbstractDataTypes() {
			return abstractDataTypes;
		}

		public void __setConstructorDecls(java.util.Set<org.rascalmpl.ast.Declaration.Data> constructorDecls) {
			this.constructorDecls = constructorDecls;
		}

		public java.util.Set<org.rascalmpl.ast.Declaration.Data> __getConstructorDecls() {
			return constructorDecls;
		}
	}

	

}
