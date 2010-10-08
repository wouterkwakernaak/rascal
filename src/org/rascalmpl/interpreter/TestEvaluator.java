package org.rascalmpl.interpreter;

import java.util.List;

import org.eclipse.imp.pdb.facts.IBool;
import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.ast.NullASTVisitor;
import org.rascalmpl.ast.Test;
import org.rascalmpl.ast.Test.Labeled;
import org.rascalmpl.ast.Test.Unlabeled;
import org.rascalmpl.interpreter.control_exceptions.Throw;
import org.rascalmpl.interpreter.env.Environment;
import org.rascalmpl.interpreter.env.ModuleEnvironment;
import org.rascalmpl.interpreter.result.Result;
import org.rascalmpl.interpreter.result.ResultFactory;
import org.rascalmpl.interpreter.staticErrors.StaticError;

public class TestEvaluator {
	private final org.rascalmpl.interpreter.Evaluator eval;
	private final org.rascalmpl.interpreter.ITestResultListener testResultListener;
	
	public TestEvaluator(org.rascalmpl.interpreter.Evaluator eval, org.rascalmpl.interpreter.ITestResultListener testResultListener){
		super();
		
		this.eval = eval;
		this.testResultListener = testResultListener;
	}

	public org.rascalmpl.interpreter.Evaluator __getEval() {
		return eval;
	}

	public org.rascalmpl.interpreter.ITestResultListener __getTestResultListener() {
		return testResultListener;
	}

	public void test(java.lang.String moduleName) {
		org.rascalmpl.interpreter.env.Environment old = this.__getEval().getCurrentEnvt();
		
		org.rascalmpl.interpreter.env.ModuleEnvironment module = this.__getEval().getHeap().getModule(moduleName);
		if (module == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		try {
			this.__getEval().setCurrentEnvt(module);
			this.__getEval().pushEnv();
			this.test();
		}
		finally {
			this.__getEval().unwind(module);
			if (old != null) {
				this.__getEval().setCurrentEnvt(old);
			}
		}
	}
	
	public void test() {
		org.rascalmpl.interpreter.env.ModuleEnvironment topModule = (org.rascalmpl.interpreter.env.ModuleEnvironment) this.__getEval().getCurrentEnvt().getRoot();
		
		this.runTests(topModule, topModule.getTests());
		
		for (java.lang.String i : topModule.getImports()) {
			org.rascalmpl.interpreter.env.ModuleEnvironment mod = topModule.getImport(i);
			this.runTests(mod, mod.getTests());
		}
	}
	
	private void runTests(org.rascalmpl.interpreter.env.ModuleEnvironment env, java.util.List<org.rascalmpl.ast.Test> tests){
		org.rascalmpl.interpreter.env.Environment old = this.__getEval().getCurrentEnvt();
		try {
			this.__getEval().setCurrentEnvt(env);
			
			org.rascalmpl.interpreter.TestEvaluator.Visitor visitor = new org.rascalmpl.interpreter.TestEvaluator.Visitor();
			this.__getTestResultListener().start(tests.size());
			for(int i = tests.size() - 1; i >= 0; i--){
				try {
					this.__getEval().pushEnv();
					//tests.get(i).accept(visitor);
					tests.get(i).__evaluate(visitor);
				}
				finally {
					this.__getEval().unwind(env);
				}
			}
		}
		finally {
			if (old != null) {
				this.__getEval().setCurrentEnvt(old);
			}
		}
		
		this.__getTestResultListener().done();
	}
	
	public class Visitor extends org.rascalmpl.ast.NullASTVisitor<org.rascalmpl.interpreter.result.Result<org.eclipse.imp.pdb.facts.IBool>>{
		
		public Visitor(){
			super();
		}
		
		public org.rascalmpl.interpreter.Evaluator __getEval() {
			return eval;
		}

		public org.rascalmpl.interpreter.ITestResultListener __getTestResultListener() {
			return testResultListener;
		}
	}
}
