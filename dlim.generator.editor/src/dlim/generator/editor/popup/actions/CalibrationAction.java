package dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import dlim.assistant.CalibrationException;

/**
 * Abstract parent class for all calibration actions.
 * @author Jóakim G. v. Kistowski
 *
 */
public abstract class CalibrationAction implements IObjectActionDelegate {

	protected Shell shell;
	protected ISelection currentSelection;
	
	/**
	 * Constructor for CalibrationAction.
	 */
	public CalibrationAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * This method is called by the LaunchCalibrationDialog for the actual calibration.
	 * @param desiredValue The desired total model arrival rate.
	 * @return The model attribute value.
	 * @throws CalibrationException Possible calibration errors.
	 */
	public abstract double executeCalibration(double desiredValue) throws CalibrationException;
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

}
