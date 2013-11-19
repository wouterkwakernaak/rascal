package org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.Instructions;

import org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.CodeBlock;

public class AndBool extends Instruction {
	
	public AndBool(CodeBlock ins) {
		super(ins, Opcode.ANDBOOL);
	}

}
