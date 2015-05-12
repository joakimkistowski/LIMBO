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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.generator.editor.utils.ProjectManager;

/**
 * This dialog has the user select the exporter to use for time-stamp
 * generation.
 *
 * @author Joakim von Kistowski
 *
 */
public class SelectExtractorDialog extends TitleAreaDialog {

	/**
	 * ID of Eclipse Preference for lasts used trace.
	 */
	private static final String EXTRACTED_STORE_ID = "dlim.extractordialogstorepath";

	// parameter input fields
	private List exporterList;
	private Text arrivalRateFilePathText;
	private Text offsetText;

	private String[] labels;
	private int selectedIndex = 0;
	private double offset = 0.0;

	private String arrivalRateFilePath = "";

	private boolean canceled = false;

	/**
	 * Instantiates a new select extractor dialog.
	 *
	 * @param parentShell the parent shell
	 * @param labels the labels
	 */
	public SelectExtractorDialog(Shell parentShell, String[] labels) {
		super(parentShell);
		this.labels = labels;
		setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	/**
	 * Set dialog titles.
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Extract model Sequence from an existing arrival rate trace.");
	}

	/**
	 * Set up GUI elements.
	 *
	 * @param parent the parent
	 * @return the control
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new GridLayout(1, false));
		GridData parentData = new GridData();
		parentData.grabExcessHorizontalSpace = true;
		parentData.grabExcessVerticalSpace = true;
		parentData.horizontalAlignment = GridData.FILL;
		parentData.verticalAlignment = GridData.FILL;
		columnContainer.setLayoutData(parentData);

		createOffsetParameterField(columnContainer);
		createTxtFilePathField(columnContainer);

		exporterList = new List(columnContainer, SWT.BORDER);
		for (String label : labels) {
			exporterList.add(label);
		}
		exporterList.select(0);
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.grabExcessVerticalSpace = true;
		textData.horizontalAlignment = GridData.FILL;
		textData.verticalAlignment = GridData.FILL;
		exporterList.setLayoutData(textData);

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
		arrivalRateFilePathText = new Text(gridContainer, SWT.BORDER);
		arrivalRateFilePathText.setText(ProjectManager.retrieveStringFromPreferences(EXTRACTED_STORE_ID));
		arrivalRateFilePathText.setLayoutData(parameterFieldData);
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
					arrivalRateFilePathText.setText(newPath);
				}
			}
		});
	}

	// offset of model start within the arrival rate file
	private void createOffsetParameterField(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		Label parameterFieldLabel = new Label(container, SWT.NONE);
		parameterFieldLabel
		.setText("Model start offset within arrival rate file: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 40;
		offsetText = new Text(container, SWT.BORDER);
		offsetText.setText(String.valueOf(offset));
		offsetText.setLayoutData(parameterFieldData);
	}

	/**
	 * Dialog window label.
	 *
	 * @param newShell the new shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Extract Sequence");
	}

	/**
	 * Cancel button press.
	 */
	@Override
	protected void cancelPressed() {
		canceled = true;
		super.cancelPressed();
	}

	/**
	 * Returns true if the user canceled calibration.
	 *
	 * @return true, if successful
	 */
	public boolean wasCanceled() {
		return canceled;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		boolean error = false;

		selectedIndex = exporterList.getSelectionIndex();
		if (selectedIndex < 0) {
			setMessage("Please select an Extractor.", IMessageProvider.ERROR);
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

		arrivalRateFilePath = arrivalRateFilePathText.getText().trim();
		IPath filePath = new Path(arrivalRateFilePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					filePath.toString()));
			br.close();
		} catch (IOException e) {
			setMessage("Error reading file. Does it exist?",
					IMessageProvider.ERROR);
			error = true;
		}

		// success
		if (!error) {
			ProjectManager.saveStringToPreferences(EXTRACTED_STORE_ID, arrivalRateFilePath);
			arrivalRateFilePath = filePath.toString();
			super.okPressed();
		}
	}

	/**
	 * Gets the selected index.
	 *
	 * @return the selected index
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public double getOffset() {
		return offset;
	}

	/**
	 * Gets the arrival rate file path.
	 *
	 * @return the arrival rate file path
	 */
	public String getArrivalRateFilePath() {
		return arrivalRateFilePath;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(480, 400);
		// return super.getInitialSize();
	}
}
