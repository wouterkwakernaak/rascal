package org.rascalmpl.library;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.rascalmpl.interpreter.TypeReifier;

import lapd.databases.neo4j.GraphDbMappingException;
import lapd.databases.neo4j.GraphDbValueIO;

public class LAPD {
	
	private GraphDbValueIO graphDbValueIO;
	private IValueFactory valueFactory;
	private TypeReifier typeReifier;
	
	public LAPD(IValueFactory valueFactory) throws IOException {
		graphDbValueIO = GraphDbValueIO.getInstance();
		graphDbValueIO.init(valueFactory);
		this.valueFactory = valueFactory;
		typeReifier = new TypeReifier(valueFactory);		
	}

	public void write(IString id, IValue value) throws GraphDbMappingException {
		graphDbValueIO.write(id.toString(), value);
	}

	public IValue read(IString id, IValue reifiedType) throws GraphDbMappingException  {
		TypeStore typeStore = new TypeStore();
		org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType, typeStore);
		return graphDbValueIO.read(id.toString(), type, typeStore);
	}
	
	public IString generateUniqueId() {
		return valueFactory.string(UUID.randomUUID().toString());
	}

}
