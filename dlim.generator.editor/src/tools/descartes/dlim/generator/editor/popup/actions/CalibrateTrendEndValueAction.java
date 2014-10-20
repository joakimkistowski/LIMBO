package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
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
 * Action for the calibration of a Trend's endValue attribute.
 * @author Jóakim G. v. Kistowski
 *
 */
public class CalibrateTrendEndValueAction extends CalibrationAction {
	
	private ModelEvaluator evaluator;
	private Trend trend;
	
	/**
	 * Constructor for CalibrateTrendEndValueAction.
	 */
	public CalibrateTrendEndValueAction() {
		super();
	}


	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		trend = (Trend) DlimFileUtils.getEObjectFromSelection(currentSelection);
		evaluator = new ModelEvaluator(ModelEvaluatorUtil.getRootSequence(trend),
				5, IGeneratorConstants.CALIBRATION);
		LaunchCalibrationDialog dialog = new LaunchCalibrationDialog("Calibrate Trend End"
				, ModelEvaluatorUtil.getFunctionBegin(trend)+ModelEvaluatorUtil.getFunctionDuration(trend),
				shell, this);
		dialog.open();
		if (!dialog.wasCanceled()) {
			//trend.setFunctionOutputAtEnd(dialog.getNewValue());
			
			// Change the value via a command.
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor)editor;
				SetCommand set = new SetCommand(dlimEditor.getEditingDomain(),trend,
						DlimPackage.eINSTANCE.getTrend_FunctionOutputAtEnd(),dialog.getNewValue());
				dlimEditor.getEditingDomain().getCommandStack().execute(set);
			}
		}
	}

	/**
	 * Calibrates the Trend's endValue.
	 */
	@Override
	public double executeCalibration(double desiredValue) throws CalibrationException {
		return Calibrator.calibrateTrendEndValue(desiredValue, trend, evaluator);	
	}


}
