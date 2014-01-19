package dlim.generator.editor.popup.actions;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import dlim.DlimPackage;
import dlim.Trend;
import dlim.assistant.CalibrationException;
import dlim.assistant.Calibrator;
import dlim.exporter.utils.DlimFileUtils;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;
import dlim.generator.ModelEvaluatorUtil;
import dlim.generator.editor.dialogs.LaunchCalibrationDialog;
import dlim.presentation.DlimEditor;

/**
 * Action for the calibration of a Trend's startValue attribute.
 * @author Jóakim G. v. Kistowski
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
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		trend = (Trend) DlimFileUtils.getEObjectFromSelection(currentSelection);
		evaluator = new ModelEvaluator(ModelEvaluatorUtil.getRootSequence(trend),
				5, IGeneratorConstants.CALIBRATION);
		LaunchCalibrationDialog dialog = new LaunchCalibrationDialog("Calibrate Trend Start"
				, ModelEvaluatorUtil.getFunctionBegin(trend),
				shell, this);
		dialog.open();
		if (!dialog.wasCanceled()) {
			//trend.setFunctionOutputAtStart(dialog.getNewValue());
			
			// Change the value via a command.
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor)editor;
				SetCommand set = new SetCommand(dlimEditor.getEditingDomain(),trend,
						DlimPackage.eINSTANCE.getTrend_FunctionOutputAtStart(),dialog.getNewValue());
				dlimEditor.getEditingDomain().getCommandStack().execute(set);
			}
		}
	}

	/**
	 * Calibrates the Trend's startValue.
	 */
	@Override
	public double executeCalibration(double desiredValue) throws CalibrationException {
		return Calibrator.calibrateTrendStartValue(desiredValue, trend, evaluator);
	}

}
