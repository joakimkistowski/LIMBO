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
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.presentation.DlimEditorPlugin;

/**
 * Action for the displaying of the PlotView.
 *
 * @author Joakim von Kistowski
 */
public class OpenPlotViewAction implements IEditorActionDelegate {

	@Override
	public void run(IAction action) {
		try {
			PlatformUI
			.getWorkbench()
			.getActiveWorkbenchWindow()
			.getActivePage()
			.showView(
					"tools.descartes.dlim.generator.editor.views.PlotView");
		} catch (PartInitException e) {
			DlimEditorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimEditorPlugin.PLUGIN_ID, "Failed to initialize Plotview", e));
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		//does nothing

	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		//does nothing
	}

}
