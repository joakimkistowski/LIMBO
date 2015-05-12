/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * This dialog has the user select the exporter to use for time-stamp
 * generation.
 *
 * @author Joakim von Kistowski
 *
 */
public class SelectExporterDialog extends TitleAreaDialog {

	// parameter input fields
	private List exporterList;

	private String[] labels;
	private int selectedIndex = 0;

	private boolean canceled = false;

	/**
	 * Instantiates a new select exporter dialog.
	 *
	 * @param parentShell the parent shell
	 * @param labels the labels
	 */
	public SelectExporterDialog(Shell parentShell, String[] labels) {
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
		setTitle("Select Time Stamp Exporter");
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

	/**
	 * Dialog window label.
	 *
	 * @param newShell the new shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Export Time Stamps");
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
			setMessage("Please select an Exporter.", IMessageProvider.ERROR);
			error = true;
		}

		// success
		if (!error) {
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

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(360, 400);
		// return super.getInitialSize();
	}
}
