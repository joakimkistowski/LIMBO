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
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.exporterHandler.ExporterHandler;
import tools.descartes.dlim.generator.editor.dialogs.SelectExporterDialog;
import tools.descartes.dlim.generator.editor.utils.ProjectManager;

/**
 * Evaluates the model and creates an arrival rate and time-stamp series.
 *
 * @author Joakim von Kistowski
 *
 */
public class TimeStampGeneratorAction implements IObjectActionDelegate {

	private Shell shell;
	private ISelection currentSelection;

	/**
	 * Constructor for TimeStampGeneratorAction.
	 */
	public TimeStampGeneratorAction() {
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

		ExporterHandler handler = ExporterHandler.getHandlerSingleton();
		SelectExporterDialog exporterDialog = new SelectExporterDialog(shell,
				handler.getExporterLabels());
		exporterDialog.open();

		ProjectManager pManager = new ProjectManager(currentSelection);

		if (!exporterDialog.wasCanceled()) {
			String filePath = DlimFileUtils.getSelectionPath(currentSelection);
			handler.executeExtension(exporterDialog.getSelectedIndex(),
					pManager.getProjectPath(), filePath,
					(Sequence) DlimFileUtils.getRootEObject(currentSelection));
		}

		/*
		 * TimeStampGeneratorParametersDialog paramDialog = new
		 * TimeStampGeneratorParametersDialog
		 * (pManager.getSelectionPath(currentSelection),shell);
		 * paramDialog.open();
		 *
		 * if (!paramDialog.wasCanceled()) { ModelEvaluator evaluator = new
		 * ModelEvaluator((Sequence)pManager.getRootEObject(currentSelection),
		 * paramDialog.getRndSeed(), IGeneratorConstants.EVALUATION);
		 * ArrivalRateGenerator arrRateGen = new ArrivalRateGenerator(evaluator,
		 * pManager.getProjectPath()); TimeStampGenerator tsGen = new
		 * TimeStampGenerator(evaluator, pManager.getProjectPath());
		 * arrRateGen.generateArrivalRates(paramDialog.getStep()); if
		 * (!paramDialog
		 * .getSamplingMode().equals(IGeneratorConstants.NOTIMESTAMPS)) {
		 * tsGen.generateTimeStampsFromArrivalRates(arrRateGen.getArrRateList(),
		 * paramDialog.getSamplingMode(), paramDialog.getDecimalPlaces(),
		 * paramDialog.getStretch(), paramDialog.getArDevisor()); } }
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
