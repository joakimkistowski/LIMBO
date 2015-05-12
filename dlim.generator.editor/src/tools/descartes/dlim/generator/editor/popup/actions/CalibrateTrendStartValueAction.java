/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.assistant.Calibrator;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.generator.ModelEvaluatorUtil;
import tools.descartes.dlim.generator.editor.dialogs.LaunchCalibrationDialog;
import tools.descartes.dlim.presentation.DlimEditor;

/**
 * Action for the calibration of a Trend's startValue attribute.
 *
 * @author Joakim von Kistowski
 *
 */
public class CalibrateTrendStartValueAction extends CalibrationAction {

	private ModelEvaluator evaluator;
	private Trend trend;

	/**
	 * Constructor for CalibrateTrendStartValueAction.
	 */
	public CalibrateTrendStartValueAction() {
		super();
	}

	/**
	 * Run the action.
	 *
	 * @param action the action
	 */
	@Override
	public void run(IAction action) {
		trend = (Trend) DlimFileUtils.getEObjectFromSelection(getCurrentSelection());
		evaluator = new ModelEvaluator(
				ModelEvaluatorUtil.getRootSequence(trend), 5,
				IGeneratorConstants.CALIBRATION);
		LaunchCalibrationDialog dialog = new LaunchCalibrationDialog(
				"Calibrate Trend Start",
				ModelEvaluatorUtil.getFunctionBegin(trend), getShell(), this);
		dialog.open();
		if (!dialog.wasCanceled()) {
			// trend.setFunctionOutputAtStart(dialog.getNewValue());

			// Change the value via a command.
			IEditorPart editor = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor) editor;
				SetCommand set = new SetCommand(dlimEditor.getEditingDomain(),
						trend,
						DlimPackage.eINSTANCE.getTrend_FunctionOutputAtStart(),
						dialog.getNewValue());
				dlimEditor.getEditingDomain().getCommandStack().execute(set);
			}
		}
	}

	/**
	 * Calibrates the Trend's startValue.
	 */
	@Override
	public double executeCalibration(double desiredValue)
			throws CalibrationException {
		return Calibrator.calibrateTrendStartValue(desiredValue, trend,
				evaluator);
	}

}
