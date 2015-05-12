/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.dialogs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.generator.editor.utils.ProjectManager;

/**
 * This dialog takes the user parameters for difference Calculation between a
 * model instance and an arrival rate txt file.
 *
 * @author Joakim von Kistowski
 *
 */
public class LaunchDiffDialog extends TitleAreaDialog {

	/**
	 * ID for the diff dialog stored file path preference.
	 */
	private static final String DIFF_PREFERENCES = "dlim.diff";

	private Text rndSeedText;
	private Text txtFilePathText;
	private Text offsetText;

	private String txtFilePath = "";
	private int rndSeed = 5;
	private double offset = 0.0;
	private boolean canceled = false;

	private String modelFileString;

	/**
	 * Creates a new dialog.
	 *
	 * @param fileString file path as string
	 * @param parentShell the parent shell
	 */
	public LaunchDiffDialog(String fileString, Shell parentShell) {
		super(parentShell);
		this.modelFileString = fileString;
	}

	private void setDefaultValues() {
		rndSeed = 5;
		txtFilePath = "";
	}



	/**
	 * Set titles.
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Calculate difference between Arrival Rate File and Model");
		setMessage("For Model: " + modelFileString,
				IMessageProvider.INFORMATION);
	}

	/**
	 * Create GUI elements.
	 *
	 * @param parent the parent
	 * @return the control
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new FillLayout(SWT.VERTICAL));
		createRndSeedParameterField(columnContainer);
		createOffsetParameterField(columnContainer);
		createTxtFilePathField(columnContainer);

		return dialogContainer;
	}

	// file path UI
	private void createTxtFilePathField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Arrival Rate File: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 300;
		txtFilePathText = new Text(gridContainer, SWT.BORDER);
		txtFilePathText.setText(ProjectManager.retrieveStringFromPreferences(DIFF_PREFERENCES));
		txtFilePathText.setLayoutData(parameterFieldData);
		Button fileDialogButton = new Button(gridContainer, SWT.PUSH);
		fileDialogButton.setText("Browse");
		fileDialogButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelection(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleSelection(e);
			}

			private void handleSelection(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getParentShell(), SWT.OPEN);
				String[] filterNames = { "Arrival Rate files", "All Files" };
				String[] filterExtensions = new String[] { "*.csv;*.txt", "*.*" };
				dialog.setFilterNames(filterNames);
				dialog.setFilterExtensions(filterExtensions);
				dialog.setText("Select Arrival Rate File");
				String newPath = dialog.open();
				if (newPath != null && !newPath.isEmpty()) {
					txtFilePathText.setText(newPath);
				}
			}
		});
	}

	// random generator seed UI
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
		rndSeedText.setText(String.valueOf(rndSeed));
		rndSeedText.setLayoutData(parameterFieldData);
	}

	// offset of model start within the arrival rate file
	private void createOffsetParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel
		.setText("Model start offset within arrival rate file: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 40;
		offsetText = new Text(gridContainer, SWT.BORDER);
		offsetText.setText(String.valueOf(offset));
		offsetText.setLayoutData(parameterFieldData);
	}

	/**
	 * Dialog window title.
	 *
	 * @param newShell the new shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Calculate Difference");
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
	 * The file path of the arrival rate txt file (relative to project root).
	 *
	 * @return the txt file path
	 */
	public String getTxtFilePath() {
		return txtFilePath;
	}

	/**
	 * Returns true if user has canceled the dialog.
	 *
	 * @return true, if successful
	 */
	public boolean wasCanceled() {
		return canceled;
	}

	/**
	 * Read the parameters from their GUI elements.
	 */
	@Override
	protected void okPressed() {
		boolean error = false;
		try {
			rndSeed = Integer.parseInt(rndSeedText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Random Seed must be an Integer.",
					IMessageProvider.ERROR);
			error = true;
		}

		try {
			offset = Double.parseDouble(offsetText.getText().trim());
			if (offset < 0) {
				setMessage("Offset must not be negative.",
						IMessageProvider.ERROR);
				error = true;
			}
		} catch (NumberFormatException e) {
			setMessage("Offset must be a number.", IMessageProvider.ERROR);
			error = true;
		}

		txtFilePath = txtFilePathText.getText().trim();

		IPath filePath = new Path(txtFilePath);

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					filePath.toString()));
			br.close();
		} catch (IOException e) {
			setMessage("Error reading file. Does it exist?",
					IMessageProvider.ERROR);
			error = true;
		}

		if (!error) {
			ProjectManager.saveStringToPreferences(DIFF_PREFERENCES, txtFilePath);
			txtFilePath = filePath.toString();
			super.okPressed();
		}
	}

	/**
	 * Gets the rnd seed.
	 *
	 * @return the rnd seed
	 */
	public int getRndSeed() {
		return rndSeed;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public double getOffset() {
		return offset;
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
