/*******************************************************************************
 * Copyright (c) 2009-2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:

 *   * 
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Tijs van der Storm - Tijs.van.der.Storm@cwi.nl
 *   * Emilie Balland - (CWI)
 *   * Anya Helene Bagge - anya@ii.uib.no (Univ. Bergen)
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Mark Hills - Mark.Hills@cwi.nl (CWI)
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
 *   * Michael Steindorfer - Michael.Steindorfer@cwi.nl - CWI 
*******************************************************************************/
package org.rascalmpl.interpreter;

import static org.rascalmpl.semantics.dynamic.Import.parseFragments;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.IMap;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.exceptions.FactTypeUseException;
import org.eclipse.imp.pdb.facts.io.StandardTextReader;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.rascalmpl.ast.AbstractAST;
import org.rascalmpl.ast.Command;
import org.rascalmpl.ast.Commands;
import org.rascalmpl.ast.Declaration;
import org.rascalmpl.ast.EvalCommand;
import org.rascalmpl.ast.Name;
import org.rascalmpl.ast.QualifiedName;
import org.rascalmpl.ast.Statement;
import org.rascalmpl.interpreter.asserts.ImplementationError;
import org.rascalmpl.interpreter.asserts.NotYetImplemented;
import org.rascalmpl.interpreter.callbacks.IConstructorDeclared;
import org.rascalmpl.interpreter.control_exceptions.Failure;
import org.rascalmpl.interpreter.control_exceptions.Insert;
import org.rascalmpl.interpreter.control_exceptions.Return;
import org.rascalmpl.interpreter.control_exceptions.Throw;
import org.rascalmpl.interpreter.debug.IRascalSuspendTrigger;
import org.rascalmpl.interpreter.debug.IRascalSuspendTriggerListener;
import org.rascalmpl.interpreter.env.Environment;
import org.rascalmpl.interpreter.env.GlobalEnvironment;
import org.rascalmpl.interpreter.env.KeywordParameter;
import org.rascalmpl.interpreter.env.ModuleEnvironment;
import org.rascalmpl.interpreter.load.IRascalSearchPathContributor;
import org.rascalmpl.interpreter.load.RascalURIResolver;
import org.rascalmpl.interpreter.load.URIContributor;
import org.rascalmpl.interpreter.result.AbstractFunction;
import org.rascalmpl.interpreter.result.ICallableValue;
import org.rascalmpl.interpreter.result.OverloadedFunction;
import org.rascalmpl.interpreter.result.Result;
import org.rascalmpl.interpreter.result.ResultFactory;
import org.rascalmpl.interpreter.staticErrors.CommandlineError;
import org.rascalmpl.interpreter.staticErrors.StaticError;
import org.rascalmpl.interpreter.staticErrors.UndeclaredFunction;
import org.rascalmpl.interpreter.staticErrors.UndeclaredVariable;
import org.rascalmpl.interpreter.staticErrors.UnguardedFail;
import org.rascalmpl.interpreter.staticErrors.UnguardedInsert;
import org.rascalmpl.interpreter.staticErrors.UnguardedReturn;
import org.rascalmpl.interpreter.types.RascalTypeFactory;
import org.rascalmpl.interpreter.utils.JavaBridge;
import org.rascalmpl.interpreter.utils.Names;
import org.rascalmpl.interpreter.utils.Profiler;
import org.rascalmpl.interpreter.utils.RuntimeExceptionFactory;
import org.rascalmpl.library.lang.rascal.syntax.RascalParser;
import org.rascalmpl.parser.ASTBuilder;
import org.rascalmpl.parser.Parser;
import org.rascalmpl.parser.ParserGenerator;
import org.rascalmpl.parser.gtd.IGTD;
import org.rascalmpl.parser.gtd.io.InputConverter;
import org.rascalmpl.parser.gtd.result.action.IActionExecutor;
import org.rascalmpl.parser.gtd.result.out.DefaultNodeFlattener;
import org.rascalmpl.parser.uptr.UPTRNodeFactory;
import org.rascalmpl.parser.uptr.action.NoActionExecutor;
import org.rascalmpl.parser.uptr.action.RascalFunctionActionExecutor;
import org.rascalmpl.parser.uptr.recovery.Recoverer;
import org.rascalmpl.uri.CWDURIResolver;
import org.rascalmpl.uri.ClassResourceInput;
import org.rascalmpl.uri.FileURIResolver;
import org.rascalmpl.uri.HomeURIResolver;
import org.rascalmpl.uri.HttpURIResolver;
import org.rascalmpl.uri.JarURIResolver;
import org.rascalmpl.uri.TempURIResolver;
import org.rascalmpl.uri.URIResolverRegistry;
import org.rascalmpl.uri.URIUtil;
import org.rascalmpl.values.uptr.Factory;
import org.rascalmpl.values.uptr.SymbolAdapter;

public class Evaluator implements IEvaluator<Result<IValue>>, IRascalSuspendTrigger {
	private final IValueFactory vf; // sharable
	private static final TypeFactory tf = TypeFactory.getInstance(); // always shared
	protected Environment currentEnvt; // not sharable
 
	private final GlobalEnvironment heap; // shareable if frozen
	private final Configuration config = new Configuration();
	/**
	 * True if an interrupt has been signalled and we should abort execution
	 */
	private boolean interrupt = false;

	private final JavaBridge javaBridge; // TODO: sharable if synchronized

	/**
	 * Used in runtime error messages
	 */
	private AbstractAST currentAST;

	/**
	 * True if we're doing profiling
	 */
	private static boolean doProfiling = false;
	
	/**
	 * This flag helps preventing non-terminating bootstrapping cycles. If 
	 * it is set we do not allow loading of another nested Parser Generator.
	 */
	private boolean isBootstrapper = false;

	/**
	 * The current profiler; private to this evaluator
	 */
	private Profiler profiler;

	private final TypeDeclarationEvaluator typeDeclarator; // not sharable

	private final List<ClassLoader> classLoaders; // sharable if frozen
	private final ModuleEnvironment rootScope; // sharable if frozen

	private final PrintWriter defStderr;
	private final PrintWriter defStdout;
	private PrintWriter curStderr = null;
	private PrintWriter curStdout = null;

	/**
	 * Probably not sharable
	 */
	private ITestResultListener testReporter;
	/**
	 * To avoid null pointer exceptions, avoid passing this directly to other classes, use
	 * the result of getMonitor() instead. 
	 */
	private IRascalMonitor monitor;
	
	private AbstractInterpreterEventTrigger eventTrigger; // TODO: can this be shared?	

	private final List<IRascalSuspendTriggerListener> suspendTriggerListeners;	 // TODO: can this be shared?
	
	private Stack<Accumulator> accumulators = new Stack<Accumulator>(); // not sharable
	private final Stack<String> indentStack = new Stack<String>(); // not sharable
	private final RascalURIResolver rascalPathResolver; // sharable if frozen

	private final URIResolverRegistry resolverRegistry; // sharable

	private final Map<IConstructorDeclared,Object> constructorDeclaredListeners; // TODO: can this be shared?
	private static final Object dummy = new Object();	
	
	public Evaluator(IValueFactory f, PrintWriter stderr, PrintWriter stdout, ModuleEnvironment scope, GlobalEnvironment heap) {
		this(f, stderr, stdout, scope, heap, new ArrayList<ClassLoader>(Collections.singleton(Evaluator.class.getClassLoader())), new RascalURIResolver(new URIResolverRegistry()));
	}

	public Evaluator(IValueFactory vf, PrintWriter stderr, PrintWriter stdout, ModuleEnvironment scope, GlobalEnvironment heap, List<ClassLoader> classLoaders, RascalURIResolver rascalPathResolver) {
		super();
		
		this.vf = vf;
		this.heap = heap;
		this.typeDeclarator = new TypeDeclarationEvaluator(this);
		this.currentEnvt = scope;
		this.rootScope = scope;
		heap.addModule(scope);
		this.classLoaders = classLoaders;
		this.javaBridge = new JavaBridge(classLoaders, vf, config);
		this.rascalPathResolver = rascalPathResolver;
		this.resolverRegistry = rascalPathResolver.getRegistry();
		this.defStderr = stderr;
		this.defStdout = stdout;
		this.constructorDeclaredListeners = new HashMap<IConstructorDeclared,Object>();
		this.suspendTriggerListeners = new CopyOnWriteArrayList<IRascalSuspendTriggerListener>();
		
		updateProperties();

		if (stderr == null) {
			throw new NullPointerException();
		}
		if (stdout == null) {
			throw new NullPointerException();
		}
		
		// register some schemes
		FileURIResolver files = new FileURIResolver();
		resolverRegistry.registerInputOutput(files);

		HttpURIResolver http = new HttpURIResolver();
		resolverRegistry.registerInput(http);

		CWDURIResolver cwd = new CWDURIResolver();
		resolverRegistry.registerInputOutput(cwd);

		ClassResourceInput library = new ClassResourceInput(resolverRegistry, "std", getClass(), "/org/rascalmpl/library");
		resolverRegistry.registerInput(library);

		ClassResourceInput testdata = new ClassResourceInput(resolverRegistry, "testdata", getClass(), "/org/rascalmpl/test/data");
		resolverRegistry.registerInput(testdata);
		
		ClassResourceInput benchmarkdata = new ClassResourceInput(resolverRegistry, "benchmarks", getClass(), "/org/rascalmpl/benchmark");
		resolverRegistry.registerInput(benchmarkdata);
		
		resolverRegistry.registerInput(new JarURIResolver());

		resolverRegistry.registerInputOutput(rascalPathResolver);

		resolverRegistry.registerInputOutput(new HomeURIResolver());
		resolverRegistry.registerInputOutput(new TempURIResolver());
		
		// here we have code that makes sure that courses can be edited by
		// maintainers of Rascal, using the -Drascal.courses=/path/to/courses property.
		final String courseSrc = System.getProperty("rascal.courses");
		if (courseSrc != null) {
		   FileURIResolver fileURIResolver = new FileURIResolver() {
		    @Override
		    public String scheme() {
		      return "courses";
		    }
		    
		    @Override
		    protected String getPath(URI uri) {
		      String path = uri.getPath();
		      return courseSrc + (path.startsWith("/") ? path : ("/" + path));
		    }
		  };
		  
		  resolverRegistry.registerInputOutput(fileURIResolver);
		}
		else {
		  resolverRegistry.registerInput(new ClassResourceInput(resolverRegistry, "courses", getClass(), "/org/rascalmpl/courses"));
		}
	
		ClassResourceInput tutor = new ClassResourceInput(resolverRegistry, "tutor", getClass(), "/org/rascalmpl/tutor");
		resolverRegistry.registerInput(tutor);
		
		// default event trigger to swallow events
		setEventTrigger(AbstractInterpreterEventTrigger.newNullEventTrigger());
	}

	private Evaluator(Evaluator source, ModuleEnvironment scope) {
		super();
		
		// this.accumulators = source.accumulators;
		// this.testReporter = source.testReporter;
		this.vf = source.vf;
		this.heap = source.heap;
		this.typeDeclarator = new TypeDeclarationEvaluator(this);
		// TODO: this is probably not OK
		this.currentEnvt = scope;
		this.rootScope = scope;
		// TODO: this is probably not OK
		heap.addModule(scope);
		this.classLoaders = source.classLoaders;
		// TODO: the Java bridge is probably sharable if its methods are synchronized
		this.javaBridge = new JavaBridge(classLoaders, vf, config);
		this.rascalPathResolver = source.rascalPathResolver;
		this.resolverRegistry = source.resolverRegistry;
		this.defStderr = source.defStderr;
		this.defStdout = source.defStdout;
		this.constructorDeclaredListeners = new HashMap<IConstructorDeclared,Object>(source.constructorDeclaredListeners);
		this.suspendTriggerListeners = new CopyOnWriteArrayList<IRascalSuspendTriggerListener>(source.suspendTriggerListeners);
		
		updateProperties();
		
		// default event trigger to swallow events
		setEventTrigger(AbstractInterpreterEventTrigger.newNullEventTrigger());
	}

	@Override
	public IRascalMonitor setMonitor(IRascalMonitor monitor) {
		if (monitor == this) {
			return monitor;
		}
		
		interrupt = false;
		IRascalMonitor old = monitor;
		this.monitor = monitor;
		return old;
	}
	
	@Override	
	public int endJob(boolean succeeded) {
		if (monitor != null)
			return monitor.endJob(succeeded);
		return 0;
	}

	@Override	
	public void event(int inc) {
		if (monitor != null)
			monitor.event(inc);
	}

	@Override	
	public void event(String name, int inc) {
		if (monitor != null)
			monitor.event(name, inc);
	}

	@Override	
	public void event(String name) {
		if (monitor != null)
			monitor.event(name);
	}

	@Override	
	public void startJob(String name, int workShare, int totalWork) {
		if (monitor != null)
			monitor.startJob(name, workShare, totalWork);
	}

	@Override	
	public void startJob(String name, int totalWork) {
		if (monitor != null)
			monitor.startJob(name, totalWork);
	}
	
	@Override	
	public void startJob(String name) {
		if (monitor != null)
			monitor.startJob(name);
	}
	
	@Override	
	public void todo(int work) {
		if (monitor != null)
			monitor.todo(work);
	}
	
	@Override	
	public boolean isCanceled() {
		if(monitor == null)
			return false;
		else
			return monitor.isCanceled();
	}
	
	@Override
	public void registerConstructorDeclaredListener(IConstructorDeclared iml) {
		constructorDeclaredListeners.put(iml,dummy);
	}
	
	@Override	
	public void notifyConstructorDeclaredListeners() {
		for (IConstructorDeclared iml : constructorDeclaredListeners.keySet()) {
			if (iml != null) {
				iml.handleConstructorDeclaredEvent();
			}
		}
		constructorDeclaredListeners.clear();
	}
	
	@Override
	public List<ClassLoader> getClassLoaders() {
		return Collections.unmodifiableList(classLoaders);
	}

	@Override	
	public ModuleEnvironment __getRootScope() {
		return rootScope;
	}

	@Override	
	public PrintWriter getStdOut() {
		return curStdout == null ? defStdout : curStdout;
	}

	@Override	
	public TypeDeclarationEvaluator __getTypeDeclarator() {
		return typeDeclarator;
	}

	@Override	
	public GlobalEnvironment __getHeap() {
		return heap;
	}

	@Override	
	public void __setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}


	@Override	
	public Stack<Accumulator> __getAccumulators() {
		return accumulators;
	}

	@Override	
	public IValueFactory __getVf() {
		return vf;
	}

	public static TypeFactory __getTf() {
		return tf;
	}

	@Override	
	public JavaBridge __getJavaBridge() {
		return javaBridge;
	}

	@Override	
	public void interrupt() {
		__setInterrupt(true);
	}

	@Override	
	public boolean isInterrupted() {
		return interrupt || isCanceled();
	}

	@Override	
	public PrintWriter getStdErr() {
		return curStderr == null ? defStderr : curStderr;
	}

	public void setTestResultListener(ITestResultListener l) {
		testReporter = l;
	}

	public JavaBridge getJavaBridge() {
		return javaBridge;
	}

	@Override	
	public URIResolverRegistry getResolverRegistry() {
		return resolverRegistry;
	}

	@Override
	public RascalURIResolver getRascalResolver() {
		return rascalPathResolver;
	}
	
	@Override	
	public void indent(String n) {
		indentStack.push(n);
	}
	
	@Override	
	public void unindent() {
		indentStack.pop();
	}
	
	@Override	
	public String getCurrentIndent() {
		return indentStack.peek();
	}

	/**
	 * Call a Rascal function with a number of arguments
	 * 
	 * @return either null if its a void function, or the return value of the
	 *         function.
	 */
	@Override
	public IValue call(IRascalMonitor monitor, String name, IValue... args) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			return call(name, args);
		}
		finally {
			setMonitor(old);
		}
	}
	
	
	
	public IValue call(String returnType, String name, IValue... args) {
	  return call(Names.toQualifiedName(returnType, name, getCurrentEnvt().getLocation()), null, args);
	};
	
	@Override
	public IValue call(String name, IValue... args) {
	  return call(name, (Map<String,IValue>) null, args);
	}
	
	/**
	 * Call a Rascal function with a number of arguments
	 * 
	 * @return either null if its a void function, or the return value of the
	 *         function.
	 */
	@Override
	public IValue call(IRascalMonitor monitor, String module, String name, IValue... args) {
		IRascalMonitor old = setMonitor(monitor);
		Environment oldEnv = getCurrentEnvt();
		
		try {
			ModuleEnvironment modEnv = getHeap().getModule(module);
			setCurrentEnvt(modEnv);
			return call(name, (Map<String,IValue>) null, args);
		}
		finally {
			setMonitor(old);
			setCurrentEnvt(oldEnv);
		}
	}
	
	/**
	 * This function processes commandline parameters as they are typically passed
	 * to a Rascal/Java program running on the commandline (a list of strings). 
	 * 
	 * The strings are interpreted as follows. If the first character is a '-' or the first two are '--'
	 * then the string is the name of a keyword parameter of the main function. The type of the
	 * declared parameter is used to determine how to parse the next string or strings. Note that
	 * several strings in a row that do not start with '-' or '--' will be composed into a list or
	 * a set depending on the type of the respective keyword parameter.
	 */
	public IValue main(IRascalMonitor monitor, String module, String function, String[] commandline) {
	  IRascalMonitor old = setMonitor(monitor);
    Environment oldEnv = getCurrentEnvt();
    
    try {
      ModuleEnvironment modEnv = getHeap().getModule(module);
      setCurrentEnvt(modEnv);
      
      Name name = Names.toName(function, modEnv.getLocation());
      OverloadedFunction func = (OverloadedFunction) getCurrentEnvt().getVariable(name);
      
      if (func == null) {
        throw new UndeclaredVariable(function, name);
      }
      
      AbstractFunction main = func.getFunctions().get(0);
      
      if (func.getFunctions().size() > 1) {
        throw new CommandlineError("should only have one main function", main);
      }
      
      if (main.getArity() == 1) {
        return func.call(getMonitor(), new Type[] { tf.listType(tf.stringType()) },new IValue[] { parsePlainCommandLineArgs(commandline)}, null).getValue();
      }
      else if (main.hasKeywordArgs() && main.getArity() == 0) {
        Map<String, IValue> args = parseKeywordCommandLineArgs(monitor, commandline, main);
        return func.call(getMonitor(), new Type[] { },new IValue[] {}, args).getValue();
      }
      else {
        throw new CommandlineError("main function should either have one argument of type list[str], or keyword parameters", main);
      }
    }
    finally {
      setMonitor(old);
      setCurrentEnvt(oldEnv);
    }
  }

  private IList parsePlainCommandLineArgs(String[] commandline) {
    IListWriter w = vf.listWriter();
    for (String arg : commandline) {
      w.append(vf.string(arg));
    }
    return w.done();
  }

  public Map<String, IValue> parseKeywordCommandLineArgs(IRascalMonitor monitor, String[] commandline, AbstractFunction func) {
    List<KeywordParameter> kwps = func.getKeywordParameterDefaults();
    Map<String, Type> expectedTypes = new HashMap<String,Type>();
    
    for (KeywordParameter kwp : kwps) {
      expectedTypes.put(kwp.getName(), kwp.getType());
    }

    Map<String, IValue> params = new HashMap<String,IValue>();
    
    for (int i = 0; i < commandline.length; i++) {
      if (commandline[i].equals("-help")) {
        throw new CommandlineError("Help", func);
      }
      else if (commandline[i].startsWith("-")) {
        String label = commandline[i].replaceFirst("^-+", "");
        Type expected = expectedTypes.get(label);
        
        if (expected == null) {
          throw new CommandlineError("unknown argument: " + label, func);
        }
        
        if (expected.isSubtypeOf(tf.boolType())) {
          if (i == commandline.length - 1 || commandline[i+1].startsWith("-")) {
            params.put(label, vf.bool(true));
          }
          else if (i < commandline.length - 1) {
            String arg = commandline[++i].trim();
            if (arg.equals("1") || arg.equals("true")) {
              params.put(label, vf.bool(true));
            }
            else {
              params.put(label, vf.bool(false));
            }
          }
          
          continue;
        }
        else if (i == commandline.length - 1 || commandline[i+1].startsWith("-")) {
          throw new CommandlineError("expected option for " + label, func);
        }
        else if (expected.isSubtypeOf(tf.listType(tf.valueType()))) {
          IListWriter writer = vf.listWriter(expected.getElementType());
          
          while (i + 1 < commandline.length && !commandline[i+1].startsWith("-")) {
            writer.append(parseCommandlineOption(func, expected.getElementType(), commandline[++i]));
          }
          
          params.put(label, writer.done());
        }
        else if (expected.isSubtypeOf(tf.setType(tf.valueType()))) {
          ISetWriter writer = vf.setWriter(expected.getElementType());
          
          while (i + 1 < commandline.length && !commandline[i+1].startsWith("-")) {
            writer.insert(parseCommandlineOption(func, expected.getElementType(), commandline[++i]));
          }
          
          params.put(label, writer.done());
        }
        else {
          params.put(label, parseCommandlineOption(func, expected, commandline[++i]));
        }
      }
    }
    
    return params;
  }

  private IValue parseCommandlineOption(AbstractFunction main, Type expected, String option) {
    if (expected.isSubtypeOf(tf.stringType())) {
      return vf.string(option);
    }
    else {
      StringReader reader = new StringReader(option);
      try {
        return new StandardTextReader().read(vf, expected, reader);
      } catch (FactTypeUseException e) {
        throw new CommandlineError("expected " + expected + " but got " + option + " (" + e.getMessage() + ")", main);
      } catch (IOException e) {
        throw new CommandlineError("unxped problem while parsing commandline:" + e.getMessage(), main);
      }
    }
  }
  
  @Override
  public IValue call(String name, String module, Map<String, IValue> kwArgs, IValue[] args) {
	  IRascalMonitor old = setMonitor(monitor);
    Environment oldEnv = getCurrentEnvt();
    
    try {
      ModuleEnvironment modEnv = getHeap().getModule(module);
      setCurrentEnvt(modEnv);
      return call(name, kwArgs, args);
    }
    finally {
      setMonitor(old);
      setCurrentEnvt(oldEnv);
    }
  }
	
	private IValue call(String name, Map<String,IValue> kwArgs, IValue[] args) {
	  QualifiedName qualifiedName = Names.toQualifiedName(name, getCurrentEnvt().getLocation());
	  setCurrentAST(qualifiedName);
		return call(qualifiedName, kwArgs, args);
	}
	
  private IValue call(QualifiedName qualifiedName, Map<String,IValue> kwArgs, IValue... args) {
    OverloadedFunction func = (OverloadedFunction) getCurrentEnvt().getVariable(qualifiedName);
		RascalTypeFactory rtf = RascalTypeFactory.getInstance();
    
		Type[] types = new Type[args.length];

		int i = 0;
		for (IValue v : args) {
			Type type = v.getType();
      types[i++] = type.isSubtypeOf(Factory.Tree) ? rtf.nonTerminalType((IConstructor) v) : type;
		}
		
		if (func == null) {
			throw new UndeclaredFunction(Names.fullName(qualifiedName), types, this, getCurrentAST());
		}

		return func.call(getMonitor(), types, args, kwArgs).getValue();
  }
	
	
	
	@Override	
	public IConstructor parseObject(IConstructor startSort, IMap robust, URI location, char[] input){
		IGTD<IConstructor, IConstructor, ISourceLocation> parser = getObjectParser(location);
		String name = "";
		if (SymbolAdapter.isStartSort(startSort)) {
			name = "start__";
			startSort = SymbolAdapter.getStart(startSort);
		}
		
		if (SymbolAdapter.isSort(startSort) || SymbolAdapter.isLex(startSort) || SymbolAdapter.isLayouts(startSort)) {
			name += SymbolAdapter.getName(startSort);
		}

		int[][] lookaheads = new int[robust.size()][];
		IConstructor[] robustProds = new IConstructor[robust.size()];
		initializeRecovery(robust, lookaheads, robustProds);
		
		__setInterrupt(false);
		IActionExecutor<IConstructor> exec = new RascalFunctionActionExecutor(this);
		
		return (IConstructor) parser.parse(name, location, input, exec, new DefaultNodeFlattener<IConstructor, IConstructor, ISourceLocation>(), new UPTRNodeFactory(), robustProds.length == 0 ? null : new Recoverer(robustProds, lookaheads));
	}
	
	/**
	 * This converts a map from productions to character classes to
	 * two pair-wise arrays, with char-classes unfolded as lists of ints.
	 */
	private void initializeRecovery(IMap robust, int[][] lookaheads, IConstructor[] robustProds) {
		int i = 0;
		
		for (IValue prod : robust) {
			robustProds[i] = (IConstructor) prod;
			List<Integer> chars = new LinkedList<Integer>();
			IList ranges = (IList) robust.get(prod);
			
			for (IValue range : ranges) {
				int from = ((IInteger) ((IConstructor) range).get("begin")).intValue();
				int to = ((IInteger) ((IConstructor) range).get("end")).intValue();
				
				for (int j = from; j <= to; j++) {
					chars.add(j);
				}
			}
			
			lookaheads[i] = new int[chars.size()];
			for (int k = 0; k < chars.size(); k++) {
				lookaheads[i][k] = chars.get(k);
			}
			
			i++;
		}
	}

	@Override
	public IConstructor parseObject(IRascalMonitor monitor, IConstructor startSort, IMap robust, URI location){
		IRascalMonitor old = setMonitor(monitor);
		
		try{
			char[] input = getResourceContent(location);
			return parseObject(startSort, robust, location, input);
		}catch(IOException ioex){
			throw RuntimeExceptionFactory.io(vf.string(ioex.getMessage()), getCurrentAST(), getStackTrace());
		}finally{
			setMonitor(old);
		}
	}
	
	@Override
	public IConstructor parseObject(IRascalMonitor monitor, IConstructor startSort, IMap robust, String input){
		IRascalMonitor old = setMonitor(monitor);
		try{
			return parseObject(startSort, robust, URIUtil.invalidURI(), input.toCharArray());
		}finally{
			setMonitor(old);
		}
	}
	
	@Override
	public IConstructor parseObject(IRascalMonitor monitor, IConstructor startSort, IMap robust, String input, ISourceLocation loc){
		IRascalMonitor old = setMonitor(monitor);
		try{
			return parseObject(startSort, robust, loc.getURI(), input.toCharArray());
		}finally{
			setMonitor(old);
		}
	}
	
	private IGTD<IConstructor, IConstructor, ISourceLocation> getObjectParser(URI loc){
		return org.rascalmpl.semantics.dynamic.Import.getParser(this, (ModuleEnvironment) getCurrentEnvt().getRoot(), loc, false);
	}

	@Override
	public IConstructor getGrammar(Environment env) {
		ModuleEnvironment root = (ModuleEnvironment) env.getRoot();
		return getParserGenerator().getGrammar(monitor, root.getName(), root.getSyntaxDefinition());
	}
	
	@Override
	public IConstructor getGrammar(IRascalMonitor monitor, URI uri) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			ParserGenerator pgen = getParserGenerator();
			String main = uri.getAuthority();
			ModuleEnvironment env = getHeap().getModule(main);
			return pgen.getGrammar(monitor, main, env.getSyntaxDefinition());
		}
		finally {
			setMonitor(old);
		}
	}
	
	public IValue diagnoseAmbiguity(IRascalMonitor monitor, IConstructor parseTree) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			ParserGenerator pgen = getParserGenerator();
			return pgen.diagnoseAmbiguity(parseTree);
		}
		finally {
			setMonitor(old);
		}
	}
	
	public IConstructor getExpandedGrammar(IRascalMonitor monitor, URI uri) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			ParserGenerator pgen = getParserGenerator();
			String main = uri.getAuthority();
			ModuleEnvironment env = getHeap().getModule(main);
			monitor.startJob("Expanding Grammar");
			return pgen.getExpandedGrammar(monitor, main, env.getSyntaxDefinition());
		}
		finally {
			monitor.endJob(true);
			setMonitor(old);
		}
	}
	
	public ISet getNestingRestrictions(IRascalMonitor monitor, IConstructor g) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			ParserGenerator pgen = getParserGenerator();
			return pgen.getNestingRestrictions(monitor, g);
		}
		finally {
			setMonitor(old);
		}
	}
	
	private ParserGenerator parserGenerator;
  
	
	public ParserGenerator getParserGenerator() {
		startJob("Loading parser generator", 40);
		if(parserGenerator == null ){
		  if (isBootstrapper()) {
		    throw new ImplementationError("Cyclic bootstrapping is occurring, probably because a module in the bootstrap dependencies is using the concrete syntax feature.");
		  }
			parserGenerator = new ParserGenerator(getMonitor(), getStdErr(), classLoaders, getValueFactory(), config);
		}
		endJob(true);
		return parserGenerator;
	}

	@Override	
	public void setCurrentAST(AbstractAST currentAST) {
		this.currentAST = currentAST;
	}

	@Override	
	public AbstractAST getCurrentAST() {
		return currentAST;
	}

	public void addRascalSearchPathContributor(IRascalSearchPathContributor contrib) {
		rascalPathResolver.addPathContributor(contrib);
	}

	public void addRascalSearchPath(final URI uri) {
		rascalPathResolver.addPathContributor(new URIContributor(uri));
	}
 
	public void addClassLoader(ClassLoader loader) {
		// later loaders have precedence
		classLoaders.add(0, loader);
	}

	@Override	
	public StackTrace getStackTrace() {
		StackTrace trace = new StackTrace();
		Environment env = currentEnvt;
		while (env != null) {
			trace.add(env.getLocation(), env.getName());
			env = env.getCallerScope();
		}
		return trace.freeze();
	}

	/**
	 * Evaluate a statement
	 * 
	 * Note, this method is not supposed to be called within another overriden
	 * eval(...) method, because it triggers and idle event after evaluation is
	 * done.
	 * 
	 * @param stat
	 * @return
	 */
	@Override	
	public Result<IValue> eval(Statement stat) {
		__setInterrupt(false);
		try {
			if (Evaluator.doProfiling) {
				profiler = new Profiler(this);
				profiler.start();

			}
			currentAST = stat;
			try {
				return stat.interpret(this);
			} finally {
				if (Evaluator.doProfiling) {
					if (profiler != null) {
						profiler.pleaseStop();
						profiler.report();
						profiler = null;
					}
				}
				getEventTrigger().fireIdleEvent();
			}
		} catch (Return e) {
			throw new UnguardedReturn(stat);
		} catch (Failure e) {
			throw new UnguardedFail(stat, e);
		} catch (Insert e) {
			throw new UnguardedInsert(stat);
		}
	}

	/**
	 * Parse and evaluate a command in the current execution environment
	 * 
	 * Note, this method is not supposed to be called within another overriden
	 * eval(...) method, because it triggers and idle event after evaluation is
	 * done.
	 * 
	 * @param command
	 * @return
	 */
	@Override
	public Result<IValue> eval(IRascalMonitor monitor, String command, URI location) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			return eval(command, location);
		}
		finally {
			setMonitor(old);
			getEventTrigger().fireIdleEvent();
		}
	}
	
	/**
	 * Parse and evaluate a command in the current execution environment
	 * 
	 * Note, this method is not supposed to be called within another overriden
	 * eval(...) method, because it triggers and idle event after evaluation is
	 * done.	 
	 * 
	 * @param command
	 * @return
	 */
	@Override
	public Result<IValue> evalMore(IRascalMonitor monitor, String commands, URI location) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			return evalMore(commands, location);
		}
		finally {
			setMonitor(old);
			getEventTrigger().fireIdleEvent();
		}
	}

	private Result<IValue> eval(String command, URI location)
			throws ImplementationError {
		__setInterrupt(false);
    IActionExecutor<IConstructor> actionExecutor =  new NoActionExecutor();
		IConstructor tree = new RascalParser().parse(Parser.START_COMMAND, location, command.toCharArray(), actionExecutor, new DefaultNodeFlattener<IConstructor, IConstructor, ISourceLocation>(), new UPTRNodeFactory());
		
		if (!noBacktickOutsideStringConstant(command)) {
		  tree = org.rascalmpl.semantics.dynamic.Import.parseFragments(this, tree, location, getCurrentModuleEnvironment());
		}
		
		Command stat = new ASTBuilder().buildCommand(tree);
		
		if (stat == null) {
			throw new ImplementationError("Disambiguation failed: it removed all alternatives");
		}

		return eval(stat);
	}
	
	private Result<IValue> evalMore(String command, URI location)
			throws ImplementationError {
		__setInterrupt(false);
		IConstructor tree;
		
		IActionExecutor<IConstructor> actionExecutor = new NoActionExecutor();
		tree = new RascalParser().parse(Parser.START_COMMANDS, location, command.toCharArray(), actionExecutor, new DefaultNodeFlattener<IConstructor, IConstructor, ISourceLocation>(), new UPTRNodeFactory());
	
	  if (!noBacktickOutsideStringConstant(command)) {
	    tree = org.rascalmpl.semantics.dynamic.Import.parseFragments(this, tree, location, getCurrentModuleEnvironment());
		}

		Commands stat = new ASTBuilder().buildCommands(tree);
		
		if (stat == null) {
			throw new ImplementationError("Disambiguation failed: it removed all alternatives");
		}

		return eval(stat);
	}

	/*
	 * This is dangereous, since inside embedded concrete fragments there may be unbalanced
	 * double quotes as well as unbalanced backticks. For now it is a workaround that prevents
	 * generation of parsers when some backtick is inside a string constant.
	 */
	private boolean noBacktickOutsideStringConstant(String command) {
		boolean instring = false;
		byte[] b = command.getBytes();
		
		for (int i = 0; i < b.length; i++) {
			if (b[i] == '\"') {
				instring = !instring;
			}
			else if (!instring && b[i] == '`') {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public IConstructor parseCommand(IRascalMonitor monitor, String command, URI location) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			return parseCommand(command, location);
		}
		finally {
			setMonitor(old);
		}
	}	
	
	private IConstructor parseCommand(String command, URI location) {
		__setInterrupt(false);
    IActionExecutor<IConstructor> actionExecutor =  new NoActionExecutor();
		IConstructor tree =  new RascalParser().parse(Parser.START_COMMAND, location, command.toCharArray(), actionExecutor, new DefaultNodeFlattener<IConstructor, IConstructor, ISourceLocation>(), new UPTRNodeFactory());

		if (!noBacktickOutsideStringConstant(command)) {
		  tree = org.rascalmpl.semantics.dynamic.Import.parseFragments(this, tree, location, getCurrentModuleEnvironment());
		}
		
		return tree;
	}

	@Override
	public IConstructor parseCommands(IRascalMonitor monitor, String commands, URI location) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			__setInterrupt(false);
		  IActionExecutor<IConstructor> actionExecutor =  new NoActionExecutor();
      IConstructor tree = new RascalParser().parse(Parser.START_COMMANDS, location, commands.toCharArray(), actionExecutor, new DefaultNodeFlattener<IConstructor, IConstructor, ISourceLocation>(), new UPTRNodeFactory());
  
			if (!noBacktickOutsideStringConstant(commands)) {
			  tree = parseFragments(this, tree, location, getCurrentModuleEnvironment());
			}
			
			return tree;
		}
		finally {
			setMonitor(old);
		}
	}
	
	@Override
	public Result<IValue> eval(IRascalMonitor monitor, Command command) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			return eval(command);
		}
		finally {
			setMonitor(old);
			getEventTrigger().fireIdleEvent();
		}
	}
	
	private Result<IValue> eval(Commands commands) {
		__setInterrupt(false);
		if (Evaluator.doProfiling) {
			profiler = new Profiler(this);
			profiler.start();

		}
		try {
			Result<IValue> last = ResultFactory.nothing();
			for (EvalCommand command : commands.getCommands()) {
				last = command.interpret(this);
			}
			return last;
		} finally {
			if (Evaluator.doProfiling) {
				if (profiler != null) {
					profiler.pleaseStop();
					profiler.report();
					profiler = null;
				}
			}
		}
	}
	
	private Result<IValue> eval(Command command) {
		__setInterrupt(false);
		if (Evaluator.doProfiling) {
			profiler = new Profiler(this);
			profiler.start();

		}
		try {
			return command.interpret(this);
		} finally {
			if (Evaluator.doProfiling) {
				if (profiler != null) {
//				  getCurrentModuleEnvironment().storeVariable("PROFILE", ResultFactory.makeResult(tf.lrelType(tf.sourceLocationType(), tf.integerType()), profiler.getProfileData(), this));
					profiler.pleaseStop();
					profiler.report();
					profiler = null;
				}
			}
		}
	}

	/**
	 * Evaluate a declaration
	 * 
	 * @param declaration
	 * @return
	 */
	public Result<IValue> eval(IRascalMonitor monitor, Declaration declaration) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			__setInterrupt(false);
			currentAST = declaration;
			Result<IValue> r = declaration.interpret(this);
			if (r != null) {
				return r;
			}

			throw new NotYetImplemented(declaration);
		}
		finally {
			setMonitor(old);
		}
	}
	
	public void doImport(IRascalMonitor monitor, String string) {
		IRascalMonitor old = setMonitor(monitor);
		interrupt = false;
		try {
		  ISourceLocation uri = vf.sourceLocation(URIUtil.rootScheme("import"));
		  org.rascalmpl.semantics.dynamic.Import.importModule(string, uri, this);
		}
		finally {
			setMonitor(old);
		}
	}

	public void reloadModules(IRascalMonitor monitor, Set<String> names, URI errorLocation) {
		reloadModules(monitor, names, errorLocation, true);
	}
	
	// TODO Update for extends; extends need to be cleared and reinterpreted.
	private void reloadModules(IRascalMonitor monitor, Set<String> names, URI errorLocation, boolean recurseToExtending) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			Set<String> onHeap = new HashSet<String>();
			Set<String> extendingModules = new HashSet<String>();

			
			try {
				monitor.startJob("Cleaning modules", names.size());
				for (String mod : names) {
					if (heap.existsModule(mod)) {
						//System.err.println("NOTE: will reload " + mod + " + all its dependents");
						onHeap.add(mod);
						if (recurseToExtending) {
							extendingModules.addAll(heap.getExtendingModules(mod));
						}
						heap.removeModule(heap.getModule(mod));
					}
					monitor.event("Processed " + mod, 1);
				}
				extendingModules.removeAll(names);
			} finally {
				monitor.endJob(true);
			}
			
			try {
				monitor.startJob("Reloading modules", onHeap.size());
				for (String mod : onHeap) {
					if (!heap.existsModule(mod)) {
						defStderr.print("Reloading module " + mod);
						reloadModule(mod, errorLocation);
					}
					monitor.event("loaded " + mod, 1);
				}
			} finally {
				monitor.endJob(true);
			}
			
			Set<String> dependingImports = new HashSet<String>();
			Set<String> dependingExtends = new HashSet<String>();
			dependingImports.addAll(getImportingModules(names));
			dependingExtends.addAll(getExtendingModules(names));

			monitor.event("Reconnecting importers of affected modules");
			for (String mod : dependingImports) {
			  ModuleEnvironment env = heap.getModule(mod);
			  Set<String> todo = new HashSet<String>(env.getImports());
			  for (String imp : todo) {
			    if (names.contains(imp)) {
			      env.unImport(imp);
			      ModuleEnvironment imported = heap.getModule(imp);
			      if (imported != null) {
			        env.addImport(imp, imported);
			      }
			    }

			  }
			  monitor.event("Reconnected " + mod, 1);
			}

			monitor.event("Reconnecting extenders of affected modules");
			for (String mod : dependingExtends) {
			  ModuleEnvironment env = heap.getModule(mod);
			  Set<String> todo = new HashSet<String>(env.getExtends());
			  for (String ext : todo) {
			    if (names.contains(ext)) {
			      env.unExtend(ext);
			      ModuleEnvironment extended = heap.getModule(ext);
			      if (extended != null) {
			        env.addExtend(ext);
			      }
			    }
			  }
			  monitor.event("Reconnected " + mod, 1);
			}
			
			if (recurseToExtending && !extendingModules.isEmpty()) {
				reloadModules(monitor, extendingModules, errorLocation, false);
			}
			
			if (!names.isEmpty()) {
				notifyConstructorDeclaredListeners();
			}
		}
		finally {
			setMonitor(old);
		}
	}

	private void reloadModule(String name, URI errorLocation) {	
		ModuleEnvironment env = new ModuleEnvironment(name, getHeap());
		heap.addModule(env);

		try {
			ISourceLocation loc = getValueFactory().sourceLocation(errorLocation);
      org.rascalmpl.semantics.dynamic.Import.loadModule(loc, name, this);
		} catch (StaticError e) {
			heap.removeModule(env);
			throw e;
		} catch (Throw e) {
			heap.removeModule(env);
			throw e;
		} 
	}

	/**
	 * transitively compute which modules depend on the given modules
	 * @param names
	 * @return
	 */
	private Set<String> getImportingModules(Set<String> names) {
		Set<String> found = new HashSet<String>();
		LinkedList<String> todo = new LinkedList<String>(names);
		
		while (!todo.isEmpty()) {
			String mod = todo.pop();
			Set<String> dependingModules = heap.getImportingModules(mod);
			dependingModules.removeAll(found);
			found.addAll(dependingModules);
			todo.addAll(dependingModules);
		}
		
		return found;
	}
	
	private Set<String> getExtendingModules(Set<String> names) {
		Set<String> found = new HashSet<String>();
		LinkedList<String> todo = new LinkedList<String>(names);
		
		while (!todo.isEmpty()) {
			String mod = todo.pop();
			Set<String> dependingModules = heap.getExtendingModules(mod);
			dependingModules.removeAll(found);
			found.addAll(dependingModules);
			todo.addAll(dependingModules);
		}
		
		return found;
	}
	
	@Override	
	public void unwind(Environment old) {
		setCurrentEnvt(old);
	}

	@Override	
	public void pushEnv() {
		Environment env = new Environment(getCurrentEnvt(), getCurrentLocation(), getCurrentEnvt().getName());
		setCurrentEnvt(env);
	}

	private ISourceLocation getCurrentLocation() {
		return currentAST != null ? currentAST.getLocation() : getCurrentEnvt().getLocation();
	}
	
	@Override  
	public void pushEnv(String name) {
		Environment env = new Environment(getCurrentEnvt(), getCurrentLocation(), name);
		setCurrentEnvt(env);
	}

	@Override	
	public Environment pushEnv(Statement s) {
		/* use the same name as the current envt */
		Environment env = new Environment(getCurrentEnvt(), s.getLocation(), getCurrentEnvt().getName());
		setCurrentEnvt(env);
		return env;
	}

	
	@Override	
	public void printHelpMessage(PrintWriter out) {
		out.println("Welcome to the Rascal command shell.");
		out.println();
		out.println("Shell commands:");
		out.println(":help                      Prints this message");
		out.println(":quit or EOF               Quits the shell");
		out.println(":declarations              Lists all visible rules, functions and variables");
		out.println(":set <option> <expression> Sets an option");
		out.println("e.g. profiling    true/false");
		out.println("     tracing      true/false");
		out.println(":edit <modulename>         Opens an editor for that module");
		out.println(":modules                   Lists all imported modules");
		out.println(":test                      Runs all unit tests currently loaded");
		out.println(":unimport <modulename>     Undo an import");
		out.println(":undeclare <name>          Undeclares a variable or function introduced in the shell");
		out.println(":history                   Print the command history");
		out.println(":clear                     Clears the console");			
		out.println();
		out.println("Example rascal statements and declarations:");
		out.println("1 + 1;                     Expressions simply print their output and (static) type");
		out.println("int a;                     Declarations allocate a name in the current scope");
		out.println("a = 1;                     Assignments store a value in a (optionally previously declared) variable");
		out.println("int a = 1;                 Declaration with initialization");
		out.println("import IO;                 Importing a module makes its public members available");
		out.println("println(\"Hello World\")     Function calling");
		out.println();
		out.println("Please read the manual for further information");
		out.flush();
	}

	// Modules -------------------------------------------------------------
	@Override	
	public ModuleEnvironment getCurrentModuleEnvironment() {
		if (!(currentEnvt instanceof ModuleEnvironment)) {
			throw new ImplementationError("Current env should be a module environment");
		}
		return ((ModuleEnvironment) currentEnvt);
	}

	private char[] getResourceContent(URI location) throws IOException{
		char[] data;
		Reader textStream = null;
		
		try {
			textStream = resolverRegistry.getCharacterReader(location);
			data = InputConverter.toChar(textStream);
		}
		finally{
			if(textStream != null){
				textStream.close();
			}
		}
		
		return data;
	}
	
	/**
	 * Parse a module. Practical for implementing IDE features or features that
	 * use Rascal to implement Rascal. Parsing a module currently has the side
	 * effect of declaring non-terminal types in the given environment.
	 */
	@Override
	public IConstructor parseModule(IRascalMonitor monitor, URI location) throws IOException{
	  // TODO remove this code and replace by facility in rascal-eclipse to retrieve the
	  // correct file references from a rascal:// URI
//	  URI resolved = rascalPathResolver.resolve(location);
//	  if(resolved != null){
//	    location = resolved;
//	  }
		return parseModule(monitor, getResourceContent(location), location);
	}
	
	public IConstructor parseModule(IRascalMonitor monitor, char[] data, URI location){
		IRascalMonitor old = setMonitor(monitor);
		try {
			return org.rascalmpl.semantics.dynamic.Import.parseModule(data, location, this);
		}
		finally{
			setMonitor(old);
		}
	}
	
	@Override	
	public void updateProperties() {
		Evaluator.doProfiling = config.getProfilingProperty();

		AbstractFunction.setCallTracing(config.getTracingProperty());
	}

	public Configuration getConfiguration() {
	  return config;
	}
	
	public Stack<Environment> getCallStack() {
		Stack<Environment> stack = new Stack<Environment>();
		Environment env = currentEnvt;
		while (env != null) {
			stack.add(0, env);
			env = env.getCallerScope();
		}
		return stack;
	}

	@Override	
	public Environment getCurrentEnvt() {
		return currentEnvt;
	}

	@Override	
	public void setCurrentEnvt(Environment env) {
		currentEnvt = env;
	}

	@Override	
	public IEvaluator<Result<IValue>> getEvaluator() {
		return this;
	}

	@Override	
	public GlobalEnvironment getHeap() {
		return __getHeap();
	}

	@Override	
	public boolean runTests(IRascalMonitor monitor) {
		IRascalMonitor old = setMonitor(monitor);
		try {
			final boolean[] allOk = new boolean[] { true };
			final ITestResultListener l = testReporter != null ? testReporter : new DefaultTestResultListener(getStdOut());

			new TestEvaluator(this, new ITestResultListener() {

				@Override
				public void report(boolean successful, String test, ISourceLocation loc, String message, Throwable t) {
					if (!successful)
						allOk[0] = false;
					l.report(successful, test, loc, message, t);
				}

				@Override
				public void done() {
					l.done();
				}

				@Override
				public void start(int count) {
					l.start(count);
				}
			}).test();
			return allOk[0];
		}
		finally {
			setMonitor(old);
		}
	}

	@Override	
	public IValueFactory getValueFactory() {
		return __getVf();
	}

	public void setAccumulators(Accumulator accu) {
		__getAccumulators().push(accu);
	}

	@Override	
	public Stack<Accumulator> getAccumulators() {
		return __getAccumulators();
	}

	@Override
	public void setAccumulators(Stack<Accumulator> accumulators) {
		this.accumulators = accumulators;
	}

	@Override
	public IRascalMonitor getMonitor() {
		if (monitor != null)
			return monitor;
		
		return new NullRascalMonitor();
	}

	public void overrideDefaultWriters(PrintWriter newStdOut, PrintWriter newStdErr) {
		this.curStdout = newStdOut;
		this.curStderr = newStdErr;
	}

	public void revertToDefaultWriters() {
		this.curStderr = null;
		this.curStdout = null;
	}

	public Result<IValue> call(IRascalMonitor monitor, ICallableValue fun, Type[] argTypes, IValue[] argValues) {
		if (Evaluator.doProfiling && profiler == null) {
			profiler = new Profiler(this);
			profiler.start();
			try {
				return fun.call(monitor, argTypes, argValues, null);
			} finally {
				if (profiler != null) {
					profiler.pleaseStop();
					profiler.report();
					profiler = null;
				}
			}
		}
		else {
			return fun.call(monitor, argTypes, argValues, null);
		}
	}
	
	@Override
	public void addSuspendTriggerListener(IRascalSuspendTriggerListener listener) {
		suspendTriggerListeners.add(listener);
	}

	@Override
	public void removeSuspendTriggerListener(
			IRascalSuspendTriggerListener listener) {
		suspendTriggerListeners.remove(listener);
	}
	
	@Override
	public void notifyAboutSuspension(AbstractAST currentAST) {
		if (!suspendTriggerListeners.isEmpty() && currentAST.isBreakable()) {
			 /* 
			  * NOTE: book-keeping of the listeners and notification takes place here,
			  * delegated from the individual AST nodes.
			  */
			for (IRascalSuspendTriggerListener listener : suspendTriggerListeners) {
				listener.suspended(this, currentAST);
			}
		}
	}

	public AbstractInterpreterEventTrigger getEventTrigger() {
		return eventTrigger;
	}

	public void setEventTrigger(AbstractInterpreterEventTrigger eventTrigger) {
		this.eventTrigger = eventTrigger;
	}

  @Override
	public void freeze() {
		// TODO Auto-generated method stub
	}

	@Override
	public IEvaluator<Result<IValue>> fork() {
		return new Evaluator(this, rootScope);
	}

  public void setBootstrapperProperty(boolean b) {
    this.isBootstrapper = b;
  }
  
  public boolean isBootstrapper() {
    return isBootstrapper;
  }

  public void removeSearchPathContributor(IRascalSearchPathContributor contrib) {
    rascalPathResolver.remove(contrib);
  }
 
  @Override
  public List<IRascalSuspendTriggerListener> getSuspendTriggerListeners() {
    return suspendTriggerListeners;
  }

  @Override
  public void warning(String message, ISourceLocation src) {
    if (monitor != null) {
      monitor.warning(message, src);
    }
  }

  	private Stack<TraversalEvaluator> teStack = new Stack<TraversalEvaluator>();
  	
	@Override
	public TraversalEvaluator __getCurrentTraversalEvaluator() {
		if (teStack.size() > 0)
			return teStack.peek();
		return null;
	}
	
	@Override
	public void __pushTraversalEvaluator(TraversalEvaluator te) {
		teStack.push(te);
	}
	
	@Override
	public TraversalEvaluator __popTraversalEvaluator() {
		return teStack.pop();
	}

 
}
