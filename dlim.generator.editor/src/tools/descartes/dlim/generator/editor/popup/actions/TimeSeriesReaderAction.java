/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.editor.utils.ProjectManager;
import tools.descartes.dlim.presentation.DlimEditorPlugin;
import tools.descartes.dlim.reader.RequestTimeSeriesReader;

/**
 * Reads a time-stamp series into an arrival rate series.
 *
 * @author Joakim von Kistowski
 *
 */
public class TimeSeriesReaderAction implements IObjectActionDelegate {

	private Shell shell;
	private ISelection currentSelection;

	/**
	 * Constructor for TimeSeriesReaderAction.
	 */
	public TimeSeriesReaderAction() {
		super();
	}

	/**
	 * Sets the active part.
	 *
	 * @param action the action
	 * @param targetPart the target part
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * Run.
	 *
	 * @param action the action
	 */
	@Override
	public void run(IAction action) {

		// read Series
		ProjectManager pManager = new ProjectManager(currentSelection);

		IResource selectedResource = DlimFileUtils
				.getResourceFromSelection(currentSelection);
		String filePath = selectedResource.getRawLocation().toString();
		File outputFolder = new File(pManager.getProjectPath()
				+ "/arrivalRates");
		if (!outputFolder.exists()) {
			outputFolder.mkdir();
		}
		// RequestTimeSeriesReader.createArrivalRatesFromUnsortedTimeStamps(filePath,outputFolder.getAbsolutePath());
		RequestTimeSeriesReader.createArrivalRatesFromSortedTimeStamps(
				filePath, outputFolder.getAbsolutePath());

		MessageDialog.openInformation(shell, "Dlim Editor",
				"Reading time-series " + filePath + ".");

		for (IProject p : ResourcesPlugin.getWorkspace().getRoot()
				.getProjects()) {
			try {
				p.refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				DlimEditorPlugin.INSTANCE.log(
						new Status(Status.INFO, DlimEditorPlugin.PLUGIN_ID, "Failed to refresh Workspace", e));
			}
		}
	}

	/**
	 * Selection changed.
	 *
	 * @param action the action
	 * @param selection the selection
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

}
