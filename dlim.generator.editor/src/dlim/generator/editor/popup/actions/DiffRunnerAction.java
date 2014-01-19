package dlim.generator.editor.popup.actions;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import dlim.Sequence;
import dlim.exporter.utils.DlimFileUtils;
import dlim.generator.DiffAnalyzer;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;
import dlim.generator.editor.dialogs.DiffResultsDialog;
import dlim.generator.editor.dialogs.LaunchDiffDialog;
import dlim.generator.editor.utils.ProjectManager;

/**
 * This action runs the DiffAnalyzer for a given DLIM. The txt arrival rate file
 * is provided by a LaunchDiffDialog.
 * @author Jóakim G. v. Kistowski
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
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		ProjectManager pManager = new ProjectManager(currentSelection);
		
		LaunchDiffDialog dialog = new LaunchDiffDialog(DlimFileUtils.getSelectionPath(currentSelection),shell);
		dialog.open();
		
		if (!dialog.wasCanceled()) {
			ModelEvaluator evaluator =
					new ModelEvaluator((Sequence)DlimFileUtils.getRootEObject(currentSelection),
																dialog.getRndSeed(),
																IGeneratorConstants.EVALUATION);
			DiffAnalyzer analyzer = new DiffAnalyzer(evaluator, pManager.getProjectPath());
			List<Double> statisticalValues = analyzer.calculateDiff(dialog.getTxtFilePath(), dialog.getOffset());
			
			DiffResultsDialog resultsDialog = new DiffResultsDialog(shell,
					statisticalValues.get(0),statisticalValues.get(1),statisticalValues.get(2),
					statisticalValues.get(3),statisticalValues.get(4));
			resultsDialog.open();
			/*MessageDialog.openInformation(
					shell,
					"Diff Results",
					"Diff mean: " + statisticalValues.get(0)
					+ ", diff median: " + statisticalValues.get(1)
					+ ", DTW distance: " + statisticalValues.get(2));*/
		}
		
		pManager.refreshProject();
	}
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

}
