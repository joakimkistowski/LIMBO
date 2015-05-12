/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.dialogs;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.generator.editor.utils.ProjectManager;

/**
 * This dialog takes the user parameters for the saving of the plotview into an
 * image file.
 *
 * @author Joakim von Kistowski
 *
 */
public class SavePlotViewImageDialog extends TitleAreaDialog {

	/**
	 * ID for last stored image in Eclipse Preferences.
	 */
	private static final String IMAGE_STORE_ID = "dlim.saveplotviewimage";

	private Text widthText;

	private Text imageFilePathText;
	private Text heightText;

	private String imageFilePath = "";
	private int height = 400;
	private int width = 800;
	private boolean canceled = false;

	/**
	 * Creates a new dialog.
	 *
	 * @param parentShell the parent shell
	 */
	public SavePlotViewImageDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Set titles.
	 */
	@Override
	public void create() {
		super.create();
		setTitle("Save Plot as PNG");
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
		columnContainer.setLayout(new GridLayout(1, false));
		createSizeParameterField(columnContainer);
		createImageFilePathField(columnContainer);

		return dialogContainer;
	}

	// file path UI
	private void createImageFilePathField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Image Path: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 300;
		imageFilePathText = new Text(gridContainer, SWT.BORDER);
		imageFilePathText.setText(ProjectManager.retrieveStringFromPreferences(IMAGE_STORE_ID));
		imageFilePathText.setLayoutData(parameterFieldData);
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
				FileDialog dialog = new FileDialog(getParentShell(), SWT.SAVE);
				String[] filterNames = { "PNG", "All Files" };
				String[] filterExtensions = new String[] { "*.png", "*.*" };
				dialog.setFilterNames(filterNames);
				dialog.setFilterExtensions(filterExtensions);
				dialog.setText("Save Plot as Image");
				String newPath = dialog.open();
				if (newPath != null && !newPath.isEmpty()) {
					imageFilePathText.setText(newPath);
				}
			}
		});
	}

	// random generator seed UI
	private void createSizeParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(4, false);
		gridContainer.setLayout(layout);

		Label parameterFieldLabel1 = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel1.setText("Width: ");
		GridData parameterFieldData1 = new GridData();
		parameterFieldData1.grabExcessHorizontalSpace = false;
		parameterFieldData1.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData1.widthHint = 40;
		widthText = new Text(gridContainer, SWT.BORDER);
		widthText.setText(String.valueOf(width));
		widthText.setLayoutData(parameterFieldData1);

		Label parameterFieldLabel2 = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel2.setText(" Height: ");
		GridData parameterFieldData2 = new GridData();
		parameterFieldData2.grabExcessHorizontalSpace = false;
		parameterFieldData2.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData2.widthHint = 40;
		heightText = new Text(gridContainer, SWT.BORDER);
		heightText.setText(String.valueOf(height));
		heightText.setLayoutData(parameterFieldData2);
	}

	/**
	 * Dialog window title.
	 *
	 * @param newShell the new shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Save Plot");
	}

	/**
	 * Cancel button was pressed.
	 */
	@Override
	protected void cancelPressed() {
		canceled = true;
		super.cancelPressed();
	}

	/**
	 * The file path of the PNG image.
	 *
	 * @return the image file path
	 */
	public String getImageFilePath() {
		return imageFilePath;
	}

	/**
	 * The image height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * The image width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
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
			width = Integer.parseInt(widthText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Width must be an Integer.", IMessageProvider.ERROR);
			error = true;
		}

		try {
			height = Integer.parseInt(heightText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Height must be an Integer.", IMessageProvider.ERROR);
			error = true;
		}

		imageFilePath = imageFilePathText.getText().trim();

		IPath filePath = new Path(imageFilePath);
		if (!filePath.isValidPath(imageFilePath)) {
			setMessage("The image path is not valid.", IMessageProvider.ERROR);
			error = true;
		}

		if (!error
				&& (filePath.getFileExtension() == null || !filePath
				.getFileExtension().equals("png"))) {
			setMessage("The image must be a png.", IMessageProvider.ERROR);
			error = true;
		}

		if (!error) {
			ProjectManager.saveStringToPreferences(IMAGE_STORE_ID, imageFilePath);
			imageFilePath = filePath.toString();
			super.okPressed();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		return super.getInitialSize();
	}
}
