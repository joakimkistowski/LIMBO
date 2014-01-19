package dlim.generator.editor.popup.actions;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import dlim.Burst;
import dlim.DlimPackage;
import dlim.assistant.CalibrationException;
import dlim.assistant.Calibrator;
import dlim.exporter.utils.DlimFileUtils;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;
import dlim.generator.ModelEvaluatorUtil;
import dlim.generator.editor.dialogs.LaunchCalibrationDialog;
import dlim.presentation.DlimEditor;

/**
 * Action for the calibration of a Burst's peakValue attribute.
 * @author Jóakim G. v. Kistowski
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
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		burst = (Burst) DlimFileUtils.getEObjectFromSelection(currentSelection);
		evaluator = new ModelEvaluator(ModelEvaluatorUtil.getRootSequence(burst),
				5, IGeneratorConstants.CALIBRATION);
		LaunchCalibrationDialog dialog = new LaunchCalibrationDialog("Calibrate Burst Peak"
				, ModelEvaluatorUtil.getFunctionBegin(burst)+burst.getPeakTime(),
				shell, this);
		dialog.open();
		if (!dialog.wasCanceled()) {
			//burst.setPeak(dialog.getNewValue());
			
			// Change the value via a command.
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor)editor;
				SetCommand set = new SetCommand(dlimEditor.getEditingDomain(),burst,
						DlimPackage.eINSTANCE.getBurst_Peak(),dialog.getNewValue());
				dlimEditor.getEditingDomain().getCommandStack().execute(set);
			}
		}
	}

	/**
	 * Calibrates the Burst's peakValue.
	 */
	@Override
	public double executeCalibration(double desiredValue) throws CalibrationException {
		return Calibrator.calibrateBurstPeakValue(desiredValue, burst, evaluator);	
	}


}
