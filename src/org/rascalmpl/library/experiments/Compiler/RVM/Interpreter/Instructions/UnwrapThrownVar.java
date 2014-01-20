package org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.Instructions;

import org.rascalmpl.library.experiments.Compiler.RVM.Interpreter.CodeBlock;

public class UnwrapThrownVar extends Instruction {
	
	final String fuid;
	final int pos;
	
	public UnwrapThrownVar(CodeBlock ins, String fuid, int pos) {
		super(ins, Opcode.UNWRAPTHROWNVAR);
		this.fuid = fuid;
		this.pos = pos;
	}
	
	public String toString() { 
		return "UNWRAPTHROWNVAR " + fuid + ", " + pos + " [" + codeblock.getFunctionIndex(fuid) + ", " + pos + "]";
	}
	
	public void generate(){
		codeblock.addCode2(opcode.getOpcode(), codeblock.getFunctionIndex(fuid), pos);
	}

}
