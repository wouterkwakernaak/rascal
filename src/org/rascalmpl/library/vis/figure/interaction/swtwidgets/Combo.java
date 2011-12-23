/*******************************************************************************
 * Copyright (c) 2009-2011 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:

 *   * Bert Lisser - Bert.Lisser@cwi.nl (CWI)
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *******************************************************************************/
package org.rascalmpl.library.vis.figure.interaction.swtwidgets;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.rascalmpl.library.vis.properties.PropertyManager;
import org.rascalmpl.library.vis.swt.IFigureConstructionEnv;
import org.rascalmpl.values.ValueFactoryFactory;

public class Combo extends SWTWidgetFigureWithSingleCallBack<org.eclipse.swt.widgets.Combo> {

	private Queue<String> selectedValues;
	
	public Combo(IFigureConstructionEnv env, String[] choices, IValue cb,  PropertyManager properties) {
		super(env, cb, properties);
		widget = makeWidget(env.getSWTParent(), env,choices);
		widget.setVisible(false);
		selectedValues = new ConcurrentLinkedQueue<String>();
	}


	org.eclipse.swt.widgets.Combo makeWidget(Composite comp, IFigureConstructionEnv env,String[] choices) {
		 org.eclipse.swt.widgets.Combo combo = new org.eclipse.swt.widgets.Combo(comp, SWT.DROP_DOWN
				| SWT.BORDER);
		 for(String s : choices){
			 combo.add(s);
		 }
		 combo.select(0);
		combo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (widget.getSelectionIndex() < 0) return;
				selectedValues.add(widget.getItem(widget.getSelectionIndex()));
				doCallback();
			}
		});
		return combo;
	}


	@Override
	void executeCallback() {
		cbenv.executeRascalCallBackSingleArgument(callback, TypeFactory
				.getInstance().stringType(), ValueFactoryFactory.getValueFactory().string(selectedValues.poll()));
	}
}
