package org.rascalmpl.library;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.core.internal.jobs.Worker;
import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.rascalmpl.eclipse.console.RascalScriptInterpreter;
import org.rascalmpl.interpreter.TypeReifier;

import lapd.databases.neo4j.GraphDbMappingException;
import lapd.databases.neo4j.GraphDbValueIO;

public class LAPD {
	
	private final GraphDbValueIO graphDbValueIO;
	private final IValueFactory valueFactory;
	private final TypeReifier typeReifier;
	
	public LAPD(IValueFactory valueFactory) throws IOException {
		graphDbValueIO = GraphDbValueIO.getInstance();
		graphDbValueIO.init(valueFactory);
		this.valueFactory = valueFactory;
		typeReifier = new TypeReifier(valueFactory);
	}

	public void write(IString id, IValue value) throws GraphDbMappingException {
		graphDbValueIO.write(id.getValue(), value);
	}

	public IValue read(IString id, IValue reifiedType) throws GraphDbMappingException  {
		TypeStore typeStore = getTypeStore();
		org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType, typeStore);
		return graphDbValueIO.read(id.getValue(), type, typeStore);
	}
	
	public IValue read(IString id) throws GraphDbMappingException  {		
		return graphDbValueIO.read(id.getValue(), getTypeStore());
	}
	
	public IValue executeQuery(IString query, IValue reifiedType) throws GraphDbMappingException {
		TypeStore typeStore = getTypeStore();
		org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType, typeStore);
		return graphDbValueIO.executeQuery(query.getValue(), type, typeStore);
	}
	
	public IValue executeQuery(IString query) throws GraphDbMappingException {		
		return graphDbValueIO.executeQuery(query.getValue(), getTypeStore());
	}
	
	public IString generateUniqueId() {
		return valueFactory.string(UUID.randomUUID().toString());
	}
	
	public ISourceLocation getDbDirectoryPath() {
		return valueFactory.sourceLocation(graphDbValueIO.getDbDirectoryPath());
	}
	
	private TypeStore getTypeStore() {
		return ((RascalScriptInterpreter)((Worker)Thread.currentThread()).currentJob()).getEval().__getRootScope().getStore();
	}

}
