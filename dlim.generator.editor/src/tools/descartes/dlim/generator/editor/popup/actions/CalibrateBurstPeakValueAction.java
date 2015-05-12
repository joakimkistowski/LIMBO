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

import tools.descartes.dlim.Burst;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.assistant.Calibrator;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.generator.ModelEvaluatorUtil;
import tools.descartes.dlim.generator.editor.dialogs.LaunchCalibrationDialog;
import tools.descartes.dlim.presentation.DlimEditor;

/**
 * Action for the calibration of a Burst's peakValue attribute.
 *
 * @author Joakim von Kistowski
 *
 */
public class CalibrateBurstPeakValueAction extends CalibrationAction {

	private ModelEvaluator evaluator;
	private Burst burst;

	/**
	 * Constructor for CalibrateTrendEndValueAction.
	 */
	public CalibrateBurstPeakValueAction() {
		super();
	}

	/**
	 * Run the action.
	 *
	 * @param action the action
	 */
	@Override
	public void run(IAction action) {
		burst = (Burst) DlimFileUtils.getEObjectFromSelection(getCurrentSelection());
		evaluator = new ModelEvaluator(
				ModelEvaluatorUtil.getRootSequence(burst), 5,
				IGeneratorConstants.CALIBRATION);
		LaunchCalibrationDialog dialog = new LaunchCalibrationDialog(
				"Calibrate Burst Peak",
				ModelEvaluatorUtil.getFunctionBegin(burst)
				+ burst.getPeakTime(), getShell(), this);
		dialog.open();
		if (!dialog.wasCanceled()) {
			// burst.setPeak(dialog.getNewValue());

			// Change the value via a command.
			IEditorPart editor = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor) editor;
				SetCommand set = new SetCommand(dlimEditor.getEditingDomain(),
						burst, DlimPackage.eINSTANCE.getBurst_Peak(),
						dialog.getNewValue());
				dlimEditor.getEditingDomain().getCommandStack().execute(set);
			}
		}
	}

	/**
	 * Calibrates the Burst's peakValue.
	 *
	 * @param desiredValue the desired value
	 * @return the double
	 * @throws CalibrationException the calibration exception
	 */
	@Override
	public double executeCalibration(double desiredValue)
			throws CalibrationException {
		return Calibrator.calibrateBurstPeakValue(desiredValue, burst,
				evaluator);
	}

}
