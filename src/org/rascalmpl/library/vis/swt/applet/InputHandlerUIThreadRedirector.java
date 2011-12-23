package org.rascalmpl.library.vis.swt.applet;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.rascalmpl.library.vis.swt.ICallbackEnv;

public class InputHandlerUIThreadRedirector extends InputHandler {

	private final InputHandler wrappedHandler;
	private final ICallbackEnv env;

	public InputHandlerUIThreadRedirector(ICallbackEnv env, InputHandler wrapped) {
		super(wrapped.getParent(), wrapped.getOverlapFigures());
		this.wrappedHandler = wrapped;
		this.env = env;
	}
	
	
	@Override
	public void notifyFigureChanged() {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.notifyFigureChanged();
			}
		});
		
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.keyPressed(e);
			}
		});
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.keyReleased(e);
			}
		});
	}

	@Override
	public void mouseEnter(final MouseEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.mouseEnter(e);
			}
		});
	}

	@Override
	public void mouseExit(final MouseEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.mouseExit(e);
			}
		});
	}

	@Override
	public void mouseHover(final MouseEvent e) {
		// nothing is done with mouse over in the input handler
//		env.runOutsideUIThread(new Runnable() {
//			@Override
//			public void run() {
//				wrappedHandler.mouseHover(e);
//			}
//		});
	}

	@Override
	public void mouseMove(final MouseEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.mouseMove(e);
			}
		});
	}

	@Override
	public void mouseDoubleClick(final MouseEvent e) {
		// not used in input handler
//		env.runOutsideUIThread(new Runnable() {
//			@Override
//			public void run() {
//				wrappedHandler.mouseDoubleClick(e);
//			}
//		});
	}

	@Override
	public void mouseDown(final MouseEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.mouseDown(e);
			}
		});
	}

	@Override
	public void mouseUp(final MouseEvent e) {
		env.runOutsideUIThread(new Runnable() {
			@Override
			public void run() {
				wrappedHandler.mouseUp(e);
			}
		});
	}
	

}
