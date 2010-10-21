package org.rascalmpl.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.rascalmpl.interpreter.env.ModuleEnvironment;

public interface IRascalParser {

	/**
	 * Parse a command in the context of a number of imported concrete syntax
	 * modules
	 */
	public IConstructor parseCommand(URI location,
			String command)
			throws IOException;

	public IConstructor parseModule(URI location,
			InputStream source, ModuleEnvironment env) throws IOException;

	public IConstructor parseModule(URI location,
			char[] data, ModuleEnvironment env) throws IOException;

	/**
	 * Parse a sentence in an object language defined by the sdfImports
	 */
	public IConstructor parseString(String source) throws IOException;

	/**
	 * Parse a sentence in an object language defined by the sdfImports
	 */
	public IConstructor parseStream(InputStream source) throws IOException;

}