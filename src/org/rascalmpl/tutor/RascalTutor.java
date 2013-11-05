/*******************************************************************************
 * Copyright (c) 2009-2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:

 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
*******************************************************************************/
package org.rascalmpl.tutor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.swt.internal.Library;
import org.rascalmpl.interpreter.Evaluator;
import org.rascalmpl.interpreter.IRascalMonitor;
import org.rascalmpl.interpreter.NullRascalMonitor;
import org.rascalmpl.interpreter.env.GlobalEnvironment;
import org.rascalmpl.interpreter.env.ModuleEnvironment;
import org.rascalmpl.interpreter.load.StandardLibraryContributor;
import org.rascalmpl.uri.URIResolverRegistry;
import org.rascalmpl.uri.URIUtil;
import org.rascalmpl.values.ValueFactoryFactory;

public class RascalTutor {
	private final Evaluator eval;
	final static String BASE_SCHEME = "courses";
	private Server server;
	
	public RascalTutor() {
		GlobalEnvironment heap = new GlobalEnvironment();
		ModuleEnvironment root = heap.addModule(new ModuleEnvironment("___TUTOR___", heap));
		PrintWriter stderr = new PrintWriter(System.err);
		PrintWriter stdout = new PrintWriter(System.out);
		eval = new Evaluator(ValueFactoryFactory.getValueFactory(), stderr, stdout, root, heap);
	  eval.addRascalSearchPathContributor(StandardLibraryContributor.getInstance());
	 
		eval.addRascalSearchPath(URIUtil.rootScheme("tutor"));
		eval.addRascalSearchPath(URIUtil.rootScheme("courses"));
	}
	
	public org.rascalmpl.interpreter.Evaluator getRascalEvaluator() {
		return eval;
	}
	
	public void start(final int port, IRascalMonitor monitor) throws Exception {
		monitor.startJob("Loading Course Manager");
		eval.eval(monitor, "import " + "CourseManager" + ";", URIUtil.rootScheme("stdin"));
		monitor.endJob(true);
		
		Log.setLog(new Logger() {

			@Override
			public String getName() {
				return "no logger";
			}

			@Override
			public void warn(String msg, Object... args) {
			}

			@Override
			public void warn(Throwable thrown) {
			}

			@Override
			public void warn(String msg, Throwable thrown) {
			}

			@Override
			public void info(String msg, Object... args) {
			}

			@Override
			public void info(Throwable thrown) {
			}

			@Override
			public void info(String msg, Throwable thrown) {
			}

			@Override
			public boolean isDebugEnabled() {
				return false;
			}

			@Override
			public void setDebugEnabled(boolean enabled) {
			}

			@Override
			public void debug(String msg, Object... args) {
			}

			@Override
			public void debug(Throwable thrown) {
			}

			@Override
			public void debug(String msg, Throwable thrown) {
			}

			@Override
			public Logger getLogger(String name) {
				return this;
			}

			@Override
			public void ignore(Throwable ignored) {
			}
		});
		
		monitor.startJob("Starting Webserver");
		server = new Server();
		
		SelectChannelConnector connector=new SelectChannelConnector();
		connector.setPort(port);
		connector.setMaxIdleTime(30000);
		connector.setResponseHeaderSize(1000*1000);
		connector.setRequestBufferSize(1000*1000);
		connector.setConfidentialPort(8443);

		server.setConnectors(new Connector[]{connector});
		server.setHandler(getTutorHandler());
		server.start();
		monitor.endJob(true);
	}
	
	public void stop() throws Exception {
		if (server != null) {
			server.stop();
		}
	}
	
	public URIResolverRegistry getResolverRegistry() {
		return eval.getResolverRegistry();
	}
	
	public static void main(String[] args) {
		RascalTutor tutor = new RascalTutor();
		try {
			tutor.start(8081, new NullRascalMonitor());
		}
		catch (Exception e) {
			System.err.println("Cannot set up RascalTutor: " + e.getMessage());
		}
	}

	private ServletContextHandler getTutorHandler() throws IOException {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setAttribute("RascalEvaluator", eval);
		context.addServlet(new ServletHolder(new TutorDefaultHttpServlet()), "/");
		context.addServlet(new ServletHolder(new Show()), "/show");
		context.addServlet(new ServletHolder(new ValidateExam()), "/validateExam");
		context.addServlet(new ServletHolder(new Validate()), "/validate");
		context.addServlet(new ServletHolder(new Eval()), "/eval");
		context.addServlet(new ServletHolder(new Edit()), "/edit");
		context.addServlet(new ServletHolder(new Save()), "/save");
		context.addServlet(new ServletHolder(new Compile()), "/compile");

		String welcome[] = { BASE_SCHEME + ":///index.html"};
		context.setWelcomeFiles(welcome);
		
		return context;
	}
}
