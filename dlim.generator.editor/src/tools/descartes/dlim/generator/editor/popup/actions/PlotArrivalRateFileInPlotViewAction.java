/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.generator.editor.dialogs.PlotArrivalRateFileDialog;
import tools.descartes.dlim.generator.editor.views.PlotView;

/**
 * This Action toggles the display of an arrival rate trace in the PlotView.
 * 
 * @author Joakim von Kistowski
 */
public class PlotArrivalRateFileInPlotViewAction implements IViewActionDelegate {

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";

	private Shell shell;

	@Override
	public void run(IAction action) {
		//
		// get plot view
		IViewReference[] references = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < references.length; i++) {
			if (references[i].getId().equals(PLOTVIEWID)) {
				PlotView view = (PlotView) (references[i].getView(true));
				if (view.isPlottingFile()) {
					view.setPlottingFile(false);
				} else {
					// show dialog
					PlotArrivalRateFileDialog dialog = new PlotArrivalRateFileDialog(
							shell);
					dialog.open();
					if (!dialog.wasCanceled()) {
						view.setPlottingFile(true);
						view.setArrivalRateFileList(dialog.getArrivalRateList());
					}
				}
				view.updatePlot();
			}
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void init(IViewPart view) {
		shell = view.getSite().getShell();

	}

}
