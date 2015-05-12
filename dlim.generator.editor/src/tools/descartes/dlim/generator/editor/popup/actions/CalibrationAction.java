/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import tools.descartes.dlim.assistant.CalibrationException;

/**
 * Abstract parent class for all calibration actions.
 *
 * @author Joakim von Kistowski
 *
 */
public abstract class CalibrationAction implements IObjectActionDelegate {

	/** The shell. */
	private Shell shell;

	/** The current selection. */
	private ISelection currentSelection;

	/**
	 * Constructor for CalibrationAction.
	 */
	public CalibrationAction() {
		super();
	}

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
	 * Run the action.
	 *
	 * @param action the action
	 * @param targetPart the target part
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * This method is called by the LaunchCalibrationDialog for the actual
	 * calibration.
	 *
	 * @param desiredValue
	 *            The desired total model arrival rate.
	 * @return The model attribute value.
	 * @throws CalibrationException
	 *             Possible calibration errors.
	 */
	public abstract double executeCalibration(double desiredValue)
			throws CalibrationException;

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

}
