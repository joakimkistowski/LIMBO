/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.extractorHandler.ExtractorHandler;
import tools.descartes.dlim.generator.editor.dialogs.SelectExtractorDialog;
import tools.descartes.dlim.presentation.DlimEditor;

/**
 * Action that launches the Extractor handler for the selection of Extractor
 * extension point implementations.
 *
 * @author Joakim von Kistowski
 *
 */
public class ExtractionAction implements IObjectActionDelegate {

	/** The shell. */
	private Shell shell;

	/** The current selection. */
	private ISelection currentSelection;

	/**
	 * Gets the shell.
	 *
	 * @return the shell
	 */
	protected Shell getShell() {
		return shell;
	}

	/**
	 * Gets the current selection.
	 *
	 * @return the current selection
	 */
	protected ISelection getCurrentSelection() {
		return currentSelection;
	}

	/**
	 * Constructor for ExtractionAction.
	 */
	public ExtractionAction() {
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
	 * Selection changed.
	 *
	 * @param action the action
	 * @param selection the selection
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		Sequence sequence = (Sequence) DlimFileUtils
				.getEObjectFromSelection(currentSelection);

		ExtractorHandler handler = ExtractorHandler.getHandlerSingleton();

		SelectExtractorDialog dialog = new SelectExtractorDialog(shell,
				handler.getExtractorLabels());
		dialog.open();

		// IPath txtFilePath = new
		// Path(pManager.getProjectPath()).append("arrivalRates/testArrivalRates.txt");
		if (!dialog.wasCanceled()) {
			handler.executeExtension(dialog.getSelectedIndex(),
					dialog.getArrivalRateFilePath(), sequence,
					dialog.getOffset());

			/*
			 * Create a command to signal changes for the editor. This is a
			 * super dirty hack, all changes should be made via commands
			 * anyways, making this last command unnecessary.
			 *
			 * Unfortunately, changing everything via commands leads to crashes
			 * when undoing the commands; making this hack necessary in the
			 * first place.
			 */
			IEditorPart editor = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimEditor = (DlimEditor) editor;
				EditingDomain domain = dlimEditor.getEditingDomain();
				/*
				 * RemoveCommand remove = new RemoveCommand(domain,sequence,
				 * DlimPackage
				 * .eINSTANCE.getSequence_SequenceFunctionContainers(),
				 * sequence.getSequenceFunctionContainers());
				 * domain.getCommandStack().execute(remove);
				 *
				 * remove = new RemoveCommand(domain,sequence,
				 * DlimPackage.eINSTANCE.getFunction_Combine(),
				 * sequence.getCombine());
				 * domain.getCommandStack().execute(remove);
				 */

				SetCommand set = new SetCommand(
						domain,
						sequence,
						DlimPackage.eINSTANCE.getSequence_TerminateAfterLoops(),
						sequence.getTerminateAfterLoops());
				domain.getCommandStack().execute(set);

				/*
				 * set = new SetCommand(domain,sequence,
				 * DlimPackage.eINSTANCE.getSequence_TerminateAfterTime(),
				 * newSequence.getTerminateAfterTime());
				 * domain.getCommandStack().execute(set);
				 *
				 * set = new SetCommand(domain,sequence,
				 * DlimPackage.eINSTANCE.getSequence_SequenceFunctionContainers
				 * (), newSequence.getSequenceFunctionContainers());
				 * domain.getCommandStack().execute(set);
				 *
				 * set = new SetCommand(domain,sequence,
				 * DlimPackage.eINSTANCE.getFunction_Combine(),
				 * newSequence.getCombine());
				 * domain.getCommandStack().execute(set);
				 */
			}
		}
	}

}
