package org.rascalmpl.library;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.imp.pdb.facts.IBool;
import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.rascalmpl.interpreter.IEvaluatorContext;
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

	public IValue read(IString id, IValue reifiedType, IEvaluatorContext ctx) throws GraphDbMappingException  {
		TypeStore typeStore = getTypeStore(ctx);
		org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType, typeStore);
		return graphDbValueIO.read(id.getValue(), type, typeStore);
	}
	
	public IValue read(IString id, IEvaluatorContext ctx) throws GraphDbMappingException  {		
		return graphDbValueIO.read(id.getValue(), getTypeStore(ctx));
	}
	
	public IValue executeQuery(IString query, IValue reifiedType, IBool isCollection, IEvaluatorContext ctx) throws GraphDbMappingException {
		TypeStore typeStore = getTypeStore(ctx);
		org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType, typeStore);
		return graphDbValueIO.executeQuery(query.getValue(), type, typeStore, isCollection.getValue());
	}
	
	public IValue executeJavaQuery(IInteger queryId, IString graphId, IValue reifiedType, IEvaluatorContext ctx) throws GraphDbMappingException {
		TypeStore typeStore = getTypeStore(ctx);
		org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType, typeStore);
		return graphDbValueIO.executeJavaQuery(queryId.intValue(), graphId.getValue(), type, typeStore);
	}
	
	public IString generateUniqueId() {
		return valueFactory.string(UUID.randomUUID().toString());
	}
	
	public ISourceLocation getDbDirectoryPath() {
		return valueFactory.sourceLocation(graphDbValueIO.getDbDirectoryPath());
	}
	
	private TypeStore getTypeStore(IEvaluatorContext ctx) {
		return ctx.getEvaluator().__getRootScope().getStore();
	}
	
}
