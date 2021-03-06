package org.rascalmpl.library.experiments.Compiler.RVM.Interpreter;

public class Coroutine {
	
	final Frame start; // stack frame of the main coroutine function 
	Frame frame; // the current active stack frame of the coroutine
	
	private boolean suspended = false;
	private boolean isInitialized = false;
	
	public Coroutine(Frame frame) {
		this.start = frame;
		this.frame = frame;
	}
	
	public void next(Frame previousCallFrame) {
		this.suspended = false;
		this.start.previousCallFrame = previousCallFrame;
	}
	
	public void suspend(Frame current) {
		this.start.previousCallFrame = null;
		this.frame = current; // sets the current stack frame of the active co-routine
		this.suspended = true;
		this.isInitialized = true;
	}
	
	public boolean isInitialized() {
		return this.isInitialized;
	}
	
	public boolean hasNext() {
		return suspended;
	}
	
	public Coroutine copy() {
		if(suspended || start.pc != 0) {
			throw new RuntimeException("Copying suspended or active coroutine is not allowed.");
		}
		return new Coroutine(start.copy());
	}
	
}
