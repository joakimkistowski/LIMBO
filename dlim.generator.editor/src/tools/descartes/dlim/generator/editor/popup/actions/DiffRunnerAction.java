/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.DiffAnalyzer;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.generator.editor.dialogs.DiffResultsDialog;
import tools.descartes.dlim.generator.editor.dialogs.LaunchDiffDialog;
import tools.descartes.dlim.generator.editor.utils.ProjectManager;
import tools.descartes.dlim.reader.ArrivalRateReader;

/**
 * This action runs the DiffAnalyzer for a given DLIM. The txt arrival rate file
 * is provided by a LaunchDiffDialog.
 *
 * @author Joakim von Kistowski
 *
 */
public class DiffRunnerAction implements IObjectActionDelegate {

	private Shell shell;
	private ISelection currentSelection;

	/**
	 * Constructor for DiffRunnerAction.
	 */
	public DiffRunnerAction() {
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
	 * Run the action.
	 *
	 * @param action the action
	 */
	@Override
	public void run(IAction action) {
		ProjectManager pManager = new ProjectManager(currentSelection);

		LaunchDiffDialog dialog = new LaunchDiffDialog(
				DlimFileUtils.getSelectionPath(currentSelection), shell);
		dialog.open();

		if (!dialog.wasCanceled()) {
			ModelEvaluator evaluator = new ModelEvaluator(
					(Sequence) DlimFileUtils.getRootEObject(currentSelection),
					dialog.getRndSeed(), IGeneratorConstants.EVALUATION);
			DiffAnalyzer analyzer = new DiffAnalyzer(evaluator,
					pManager.getProjectPath());
			List<Double> statisticalValues;
			try {
				statisticalValues = analyzer.calculateDiff(
						ArrivalRateReader.readFileToList(dialog.getTxtFilePath(), 0.0), dialog.getOffset());

				if (statisticalValues.isEmpty()) {
					MessageDialog.openError(shell, "Error Calculating Difference.",
							"Could not calculate difference.\n"
									+ "This error is most likely caused by trying "
									+ "to compare a time stamp file with the model.\n"
									+ "Keep in mind that you can only compare the model with arrival rate files "
									+ "containing data points of the form \"<time stamp>,<arrival rate>[;]\"");
				}

				DiffResultsDialog resultsDialog = new DiffResultsDialog(shell,
						statisticalValues.get(0), statisticalValues.get(1),
						statisticalValues.get(2), statisticalValues.get(3),
						statisticalValues.get(4));
				resultsDialog.open();
			} catch (IOException e) {
				DlimGeneratorPlugin.INSTANCE.log(
						new Status(Status.WARNING, DlimGeneratorPlugin.PLUGIN_ID,
								"Error reading trace.", e));
			}
		}

		/*
		 * MessageDialog.openInformation( shell, "Diff Results",
		 * "Diff mean: " + statisticalValues.get(0) + ", diff median: " +
		 * statisticalValues.get(1) + ", DTW distance: " +
		 * statisticalValues.get(2));
		 */

		pManager.refreshProject();
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
