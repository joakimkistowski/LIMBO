/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.editor.views.PlotView;
import tools.descartes.dlim.presentation.DlimEditorPlugin;

/**
 * This action toggles the PlotView visualization.
 *
 * @author Joakim von Kistowski
 */
public class DecomposeInPlotViewAction implements IObjectActionDelegate {

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";

	private static ISelection currentSelection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
			.getActivePage().showView(PLOTVIEWID);

			IViewReference[] references = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getViewReferences();
			for (int i = 0; i < references.length; i++) {
				if (references[i].getId().equals(PLOTVIEWID)) {
					PlotView view = (PlotView) (references[i].getView(true));
					view.setDecompose(true);
					view.updatePlot(DlimFileUtils
							.getEObjectFromSelection(currentSelection));
				}
			}

		} catch (PartInitException e) {
			DlimEditorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimEditorPlugin.PLUGIN_ID, "Failed to initialize Plotview", e));
		}

	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged
	 * (org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;

	}

	/**
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart
	 * (org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		//doesn't do anything
	}

	/**
	 * Gets the selection.
	 *
	 * @return the selection
	 */
	public static ISelection getSelection() {
		return currentSelection;
	}

}
