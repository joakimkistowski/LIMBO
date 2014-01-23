package dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import dlim.Sequence;
import dlim.exporter.utils.DlimFileUtils;
import dlim.exporterHandler.ExporterHandler;
import dlim.generator.editor.dialogs.SelectExporterDialog;
import dlim.generator.editor.utils.ProjectManager;

/**
 * Evaluates the model and creates an arrival rate and time-stamp series.
 * @author Jóakim G. v. Kistowski
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
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		ExporterHandler handler = ExporterHandler.getHandlerSingleton();
		SelectExporterDialog exporterDialog = new SelectExporterDialog(shell, handler.getExporterLabels());
		exporterDialog.open();
		
		ProjectManager pManager = new ProjectManager(currentSelection);
		
		if (!exporterDialog.wasCanceled()) {
			String filePath = DlimFileUtils.getSelectionPath(currentSelection);
			handler.executeExtension(exporterDialog.getSelectedIndex(), pManager.getProjectPath(),
					filePath, (Sequence)DlimFileUtils.getRootEObject(currentSelection));
		}
		
		
		/*TimeStampGeneratorParametersDialog paramDialog =
				new TimeStampGeneratorParametersDialog(pManager.getSelectionPath(currentSelection),shell);
		paramDialog.open();
		
		if (!paramDialog.wasCanceled()) {
			ModelEvaluator evaluator =
					new ModelEvaluator((Sequence)pManager.getRootEObject(currentSelection),
							paramDialog.getRndSeed(),
							IGeneratorConstants.EVALUATION);
			ArrivalRateGenerator arrRateGen = new ArrivalRateGenerator(evaluator, pManager.getProjectPath());
			TimeStampGenerator tsGen = new TimeStampGenerator(evaluator, pManager.getProjectPath());
			arrRateGen.generateArrivalRates(paramDialog.getStep());
			if (!paramDialog.getSamplingMode().equals(IGeneratorConstants.NOTIMESTAMPS)) {
				tsGen.generateTimeStampsFromArrivalRates(arrRateGen.getArrRateList(), paramDialog.getSamplingMode(),
						paramDialog.getDecimalPlaces(), paramDialog.getStretch(), paramDialog.getArDevisor());
			}
		}*/
		
		pManager.refreshProject();
	}
	

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

}
