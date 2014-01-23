package dlim.generator.editor.popup.actions;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import dlim.DlimPackage;
import dlim.Sequence;
import dlim.exporter.utils.DlimFileUtils;
import dlim.extractorHandler.ExtractorHandler;
import dlim.generator.editor.dialogs.SelectExtractorDialog;
import dlim.presentation.DlimEditor;

/**
 * Action that launches the Extractor handler for the selection of Extractor extension point
 * implementations.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ExtractionAction implements IObjectActionDelegate {

	protected Shell shell;
	protected ISelection currentSelection;
	
	/**
	 * Constructor for ExtractionAction.
	 */
	public ExtractionAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

	@Override
	public void run(IAction action) {
		Sequence sequence = (Sequence) DlimFileUtils.getEObjectFromSelection(currentSelection);
		
		ExtractorHandler handler = ExtractorHandler.getHandlerSingleton();
		
		SelectExtractorDialog dialog = new SelectExtractorDialog(shell, handler.getExtractorLabels());
		dialog.open();
		
		//IPath txtFilePath = new Path(pManager.getProjectPath()).append("arrivalRates/testArrivalRates.txt");
		if (!dialog.wasCanceled()) {
			handler.executeExtension(dialog.getSelectedIndex(), dialog.getArrivalRateFilePath(), sequence, dialog.getOffset());
			
			/* Create a command to signal changes for the editor.
			 * This is a super dirty hack, all changes should be made via commands anyways,
			 * making this last command unnecessary.
			 * 
			 * Unfortunately, changing everything via commands leads to crashes when undoing the commands;
			 * making this hack necessary in the first place.
			 */
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor)editor;
				EditingDomain domain = dlimEditor.getEditingDomain();
				/*RemoveCommand remove = new RemoveCommand(domain,sequence,
						DlimPackage.eINSTANCE.getSequence_SequenceFunctionContainers(),
						sequence.getSequenceFunctionContainers());
				domain.getCommandStack().execute(remove);
				
				remove = new RemoveCommand(domain,sequence,
						DlimPackage.eINSTANCE.getFunction_Combine(),
						sequence.getCombine());
				domain.getCommandStack().execute(remove);*/
				
				SetCommand set = new SetCommand(domain,sequence,
						DlimPackage.eINSTANCE.getSequence_TerminateAfterLoops(),
						sequence.getTerminateAfterLoops());
				domain.getCommandStack().execute(set);
				
				/*set = new SetCommand(domain,sequence,
						DlimPackage.eINSTANCE.getSequence_TerminateAfterTime(),
						newSequence.getTerminateAfterTime());
				domain.getCommandStack().execute(set);
				
				set = new SetCommand(domain,sequence,
						DlimPackage.eINSTANCE.getSequence_SequenceFunctionContainers(),
						newSequence.getSequenceFunctionContainers());
				domain.getCommandStack().execute(set);
				
				set = new SetCommand(domain,sequence,
						DlimPackage.eINSTANCE.getFunction_Combine(),
						newSequence.getCombine());
				domain.getCommandStack().execute(set);*/
			}
		}
	}

}
