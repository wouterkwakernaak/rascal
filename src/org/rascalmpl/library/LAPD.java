package org.rascalmpl.library;

import java.io.IOException;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.rascalmpl.interpreter.TypeReifier;

import lapd.neo4j.GraphDbValueIO;

public class LAPD {
	
	private GraphDbValueIO graphDbValueIO;
	private IValueFactory valueFactory;
	private TypeReifier typeReifier;
	
	public LAPD(IValueFactory valueFactory) {
		try {
			graphDbValueIO = new GraphDbValueIO(valueFactory);
		} catch (IOException e) { }
		this.valueFactory = valueFactory;
		typeReifier = new TypeReifier(this.valueFactory);
	}

	public void write(IString id, IValue value) {
		try {
			graphDbValueIO.write(id.toString(), value);
		} catch (Exception e) {	}		
	}

	public IValue read(IString id, IValue reifiedType)  {
		try {
			org.eclipse.imp.pdb.facts.type.Type type = typeReifier.valueToType((IConstructor)reifiedType);
			return graphDbValueIO.read(id.toString(), type);
		} catch (Exception e) {	}		
		return null;
	}

}
