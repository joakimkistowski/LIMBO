/*******************************************************************************
* Copyright (c) 2014 Jóakim v. Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.generator.editor.views.PlotView;

/**
 * Toggles the PlotView visualization.
 * 
 * @author Jóakim v. Kistowski
 */
public class ToggleDecomposeInPlotViewAction implements IViewActionDelegate {

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";

	@Override
	public void run(IAction action) {
		IViewReference[] references = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < references.length; i++) {
			if (references[i].getId().equals(PLOTVIEWID)) {
				PlotView view = (PlotView) (references[i].getView(true));
				view.setDecompose(!view.getDecompose());
				view.updatePlot();
			}
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

}
