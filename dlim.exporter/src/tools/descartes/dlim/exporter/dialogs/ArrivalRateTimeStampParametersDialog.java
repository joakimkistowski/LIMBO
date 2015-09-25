/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.exporter.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * This dialog promts for user parameters for time-stamp and arrival rate series
 * generation.
 *
 * @author Joakim von Kistowski
 *
 */
public class ArrivalRateTimeStampParametersDialog extends TitleAreaDialog {

	private Text stepText;
	private Text rndSeedText;

	private int rndSeed = 5;
	private double step = 1.0;
	private boolean canceled = false;

	private ModelEvaluator evaluator;
	private String fileString;

	/**
	 * Create a new Dialog.
	 *
	 * @param fileString            The path of the model file.
	 * @param evaluator The model evaluator.
	 * @param parentShell the parent shell
	 */
	public ArrivalRateTimeStampParametersDialog(ModelEvaluator evaluator, String fileString,
			Shell parentShell) {
		super(parentShell);
		this.fileString = fileString;
		this.evaluator = evaluator;
	}

	private void setDefaultValues() {
		rndSeed = 5;
		step = 1.0;
		canceled = false;
	}

	/**
	 * Sets titles.
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Arrival Rate Time Stamp Generation Parameters");
		setMessage("For Model: " + fileString, IMessageProvider.INFORMATION);
		if (evaluator.getDuration() >= Double.MAX_VALUE) {
			setMessage("Model has infinite duration. Terminating after first loop!", IMessageProvider.WARNING);
		}
	}

	/**
	 * Creates the GUI elements.
	 *
	 * @param parent the parent
	 * @return the control
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new FillLayout(SWT.VERTICAL));
		createStepParameterField(columnContainer);
		createRndSeedParameterField(columnContainer);

		return dialogContainer;
	}

	private void createRndSeedParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Random Generator Seed: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 16;
		rndSeedText = new Text(gridContainer, SWT.BORDER);
		rndSeedText.setText("5");
		rndSeedText.setLayoutData(parameterFieldData);
	}

	private void createStepParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Sampling Interval Width: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 20;
		stepText = new Text(gridContainer, SWT.BORDER);
		stepText.setText("1.0");
		stepText.setLayoutData(parameterFieldData);
	}

	/**
	 * Dialog window label.
	 *
	 * @param newShell the new shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Arrival Rate Time Stamp Generation Parameters");
	}

	/**
	 * Cancel button was pressed.
	 */
	@Override
	protected void cancelPressed() {
		setDefaultValues();
		canceled = true;
		super.cancelPressed();
	}

	/**
	 * Returns true if user canceled the dialog.
	 *
	 * @return true, if successful
	 */
	public boolean wasCanceled() {
		return canceled;
	}

	/**
	 * Ok Button was pressed. Parses parameters from the UI. Displays errors to
	 * the user.
	 */
	@Override
	protected void okPressed() {
		boolean error = false;
		try {
			step = Double.parseDouble(stepText.getText().trim());

		} catch (NumberFormatException e) {
			setMessage("Sampling Interval Width must be a number.",
					IMessageProvider.ERROR);
			error = true;
		}
		try {
			rndSeed = Integer.parseInt(rndSeedText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Random Seed must be an Integer.",
					IMessageProvider.ERROR);
			error = true;
		}
		if (step <= 0) {
			error = true;
			setMessage("Sampling Interval Width must greater than 0.",
					IMessageProvider.ERROR);
		}

		if (!error) {
			super.okPressed();
		}
	}

	/**
	 * Get the random number generator seed.
	 *
	 * @return the rnd seed
	 */
	public int getRndSeed() {
		return rndSeed;
	}

	/**
	 * Get the sampling interval width.
	 *
	 * @return the step
	 */
	public double getStep() {
		return step;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		// return new Point(340,600);
		return super.getInitialSize();
	}
}
