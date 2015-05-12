/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.dialogs;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.generator.editor.popup.actions.CalibrationAction;
import tools.descartes.dlim.generator.editor.utils.ProjectManager;
import tools.descartes.dlim.reader.ArrivalRateReader;

/**
 * This dialog takes user parameters for calibration of interpolated Functions
 * (Trends and Bursts).
 *
 * @author Joakim von Kistowski
 *
 */
public class LaunchCalibrationDialog extends TitleAreaDialog {

	private static final String LASTFILEPATH_STORE_ID = "dlim.calibrationdialogfilepath";

	// The action, that triggered this dialog.
	// CalibrationAction.executeCalibration(double desriedValue) will have to be
	// run
	// from within this dialog.
	private CalibrationAction calibrationAction;

	// parameter input fields
	private Text desiredValueText;
	private Text timeText;
	private Text txtFilePathText;

	// The dialog's output value
	private double newValue = 0;

	private static double offset = 0.0;
	private double defaultTime = 0.0;
	private boolean canceled = false;

	private String titleString;

	/**
	 * Create a new dialog.
	 *
	 * @param titleString            The title to be displayed in the dialog.
	 * @param defaultTime            The time at which the desiredValue should normally be defined.
	 * @param parentShell			 The parent shell.
	 * @param calibrationAction      The Action that instantiated and launched this dialog.
	 */
	public LaunchCalibrationDialog(String titleString, double defaultTime,
			Shell parentShell, CalibrationAction calibrationAction) {
		super(parentShell);
		this.titleString = titleString;
		this.defaultTime = defaultTime;
		this.calibrationAction = calibrationAction;
	}

	/**
	 * Set dialog titles.
	 */
	@Override
	public void create() {
		super.create();
		setTitle(titleString);
		setMessage("Calibration can only occur at times,"
				+ " at which the model element is actually executed.",
				IMessageProvider.INFORMATION);
	}

	/**
	 * Set up GUI elements.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new FillLayout(SWT.VERTICAL));
		createDesiredValueParameterField(columnContainer);
		Label delimiterLabel = new Label(columnContainer, SWT.NONE);
		delimiterLabel.setText("  or");
		createTxtFilePathField(columnContainer);
		createReadTimeField(columnContainer);

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
		txtFilePathText.setText(ProjectManager.retrieveStringFromPreferences(LASTFILEPATH_STORE_ID));
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

	// GUI for the time at which the arrival rate is to be read.
	private void createReadTimeField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel
		.setText("              Time of arrival rate value: ");
		parameterFieldLabel.setAlignment(SWT.RIGHT);
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.END;
		parameterFieldData.widthHint = 22;
		timeText = new Text(gridContainer, SWT.BORDER);
		timeText.setText(String.valueOf(defaultTime + offset));
		timeText.setLayoutData(parameterFieldData);
		Button resetButton = new Button(gridContainer, SWT.PUSH);
		resetButton.setText("Reset time");
		resetButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				offset = 0;
				timeText.setText(String.valueOf(defaultTime + offset));
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				offset = 0;
				timeText.setText(String.valueOf(defaultTime + offset));
			}
		});
	}

	// maunal desired value input
	private void createDesiredValueParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Desired Value: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 32;
		desiredValueText = new Text(gridContainer, SWT.BORDER);
		desiredValueText.setText("");
		desiredValueText.setLayoutData(parameterFieldData);
		desiredValueText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if (!desiredValueText.getText().equals("")) {
					txtFilePathText.setEnabled(false);
					timeText.setEnabled(false);
				} else {
					txtFilePathText.setEnabled(true);
					timeText.setEnabled(true);
				}
			}
		});
	}

	/**
	 * Dialog window label.
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Calibration Parameters");
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
	 * Returns true if the user cancelled calibration.
	 *
	 * @return true if canceled.s
	 */
	public boolean wasCanceled() {
		return canceled;
	}

	/**
	 * Sets the newValue, depending on user input, read values and possible
	 * calibration errors.
	 */
	@Override
	protected void okPressed() {
		boolean error = false;
		double desiredValue = 0;
		String desiredValueString = desiredValueText.getText();

		String tmpFilePath = txtFilePathText.getText().trim();

		IPath filePath = new Path(tmpFilePath);

		double currentTime = defaultTime + offset;

		// read the desired value (if defined)
		if (!desiredValueString.equals("")) {
			try {
				desiredValue = Double.parseDouble(desiredValueString.trim());
			} catch (NumberFormatException e) {
				setMessage("Desired Value must be a number.",
						IMessageProvider.ERROR);
				error = true;
			}
		} else {

			// read the point in time at which to get the value from the txt
			// file
			try {
				currentTime = Double.parseDouble(timeText.getText().trim());
			} catch (NumberFormatException e) {
				setMessage("Time of arrival rate must be a number.",
						IMessageProvider.ERROR);
				error = true;
			}

			// read value from txt file
			try {
				if (!error) {
					desiredValue = ArrivalRateReader
							.getArrivalRateAtTimeFromFile(currentTime,
									filePath.toString());
				}
			} catch (IOException e) {
				setMessage("Error reading file. Does it exist?",
						IMessageProvider.ERROR);
				error = true;
			}
		}

		// calibrate
		if (!error) {
			try {
				newValue = calibrationAction.executeCalibration(desiredValue);
			} catch (CalibrationException e) {
				error = true;
				setMessage(e.getMessage(), IMessageProvider.ERROR);
			}
		}

		// success
		if (!error) {
			// store last used time and file, if it was used
			if (desiredValueString.equals("")) {
				ProjectManager.saveStringToPreferences(LASTFILEPATH_STORE_ID, tmpFilePath);
				offset = currentTime - defaultTime;
			}
			super.okPressed();
		}
	}

	/**
	 * Get the calibration result.
	 *
	 * @return the new value as calibrated.
	 */
	public double getNewValue() {
		return newValue;
	}
}
