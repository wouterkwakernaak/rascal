package org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.Instructions;

import org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.CodeBlock;

public class UnwrapThrownLoc extends Instruction {
	
	final int pos;
	
	public UnwrapThrownLoc(CodeBlock ins, int pos) {
		super(ins, Opcode.UNWRAPTHROWNLOC);
		this.pos = pos;
	}
	
	public String toString() { return "UNWRAPTHROWNOC " + pos; }
	
	public void generate(){
		codeblock.addCode1(opcode.getOpcode(), pos);
	}
	
}
