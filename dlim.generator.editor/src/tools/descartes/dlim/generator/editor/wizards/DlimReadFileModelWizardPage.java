/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.wizards;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.extractor.HLDlimParameterContainer;
import tools.descartes.dlim.extractor.ModelExtractor;

/**
 * Offers to parse an arrival rate trace into HLDLIM parameters. Uses the
 * dlim.extractor.ModelExtractor.
 *
 * @author Joakim von Kistowski
 */
public class DlimReadFileModelWizardPage extends DlimModelWizardPage {

	private Text offsetText;
	private Text seasonalPeriodText;
	private Text seasonalsPerTrendText;

	private Button extractNoiseButton;

	private Text filePathText;

	private double offset = 0.0;
	private double seasonalPeriod = 24.0;
	private int seasonalsPerTrend = 7;
	private String filePath = "";

	/**
	 * Instantiates a new dlim read file model wizard page.
	 *
	 * @param pageName the page name
	 * @param rootSequence the root sequence
	 */
	protected DlimReadFileModelWizardPage(String pageName, Sequence rootSequence) {
		super(pageName, rootSequence);
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#fillInteractiveArea
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void fillInteractiveArea(Composite interactiveArea) {
		GridData parentData = new GridData();
		parentData.horizontalAlignment = GridData.FILL;
		interactiveArea.setLayoutData(parentData);

		Composite parent = new Composite(interactiveArea, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		parent.setLayout(gridLayout);
		createOffsetField(parent);
		createSeasonalPeriodField(parent);
		createSeasonalsPerTrendField(parent);
		createExtractNoiseCheckBox(parent);

		Composite fileSelectionComposite = new Composite(interactiveArea,
				SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(3, false);
		formSelectionLayout.marginWidth = 5;
		formSelectionLayout.marginHeight = 5;
		formSelectionLayout.verticalSpacing = 0;
		formSelectionLayout.horizontalSpacing = 0;
		fileSelectionComposite.setLayout(formSelectionLayout);
		createFileSelectionField(fileSelectionComposite);

	}

	private void createFileSelectionField(Composite fileSelectionComposite) {
		Label parameterFieldLabel = new Label(fileSelectionComposite, SWT.NONE);
		parameterFieldLabel.setText("Arrival Rate File: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 300;
		filePathText = new Text(fileSelectionComposite, SWT.BORDER);
		filePathText.setText(filePath);
		filePathText.setLayoutData(parameterFieldData);
		addValidationListener(filePathText);
		Button fileDialogButton = new Button(fileSelectionComposite, SWT.PUSH);
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
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				String[] filterNames = { "Arrival Rate files", "All Files" };
				String[] filterExtensions = new String[] { "*.csv;*.txt", "*.*" };
				dialog.setFilterNames(filterNames);
				dialog.setFilterExtensions(filterExtensions);
				dialog.setText("Select Arrival Rate File");
				String newPath = dialog.open();
				if (newPath != null && !newPath.isEmpty()) {
					filePathText.setText(newPath);
				}
			}
		});
	}

	private void createOffsetField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Model Start Offset: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		offsetText = new Text(parent, SWT.BORDER);
		offsetText.setText("" + offset);
		addValidationListener(offsetText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		offsetText.setLayoutData(textData);
	}

	private void createSeasonalPeriodField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Seasonal Period: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		seasonalPeriodText = new Text(parent, SWT.BORDER);
		seasonalPeriodText.setText("" + seasonalPeriod);
		addValidationListener(seasonalPeriodText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		seasonalPeriodText.setLayoutData(textData);
	}

	private void createSeasonalsPerTrendField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Seasonals per Trend: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		seasonalsPerTrendText = new Text(parent, SWT.BORDER);
		seasonalsPerTrendText.setText("" + seasonalsPerTrend);
		addValidationListener(seasonalsPerTrendText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		seasonalsPerTrendText.setLayoutData(textData);
	}

	private void createExtractNoiseCheckBox(Composite container) {
		Label fieldLabel = new Label(container, SWT.NONE);
		fieldLabel.setText("          ");
		extractNoiseButton = new Button(container, SWT.CHECK);
		extractNoiseButton.setText("Extract Noise");
		extractNoiseButton.setSelection(false);
		extractNoiseButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(validatePage());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				setPageComplete(validatePage());
			}
		});
	}

	/* (non-Javadoc)
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#validatePage()
	 */
	@Override
	protected boolean validatePage() {

		filePath = filePathText.getText();
		if (filePath.isEmpty()) {
			seasonalPeriodText.setEnabled(false);
			seasonalsPerTrendText.setEnabled(false);
			offsetText.setEnabled(false);
		} else {
			seasonalPeriodText.setEnabled(true);
			seasonalsPerTrendText.setEnabled(true);
			offsetText.setEnabled(true);
			try {
				BufferedReader br = new BufferedReader(new FileReader(
						filePath.toString()));
				br.close();
			} catch (IOException e) {
				setMessage("Error reading file. Does it exist?",
						IMessageProvider.ERROR);
				return false;
			}
		}

		if (seasonalPeriodText.isEnabled()) {
			try {
				seasonalPeriod = Double.parseDouble(seasonalPeriodText
						.getText().trim());
				if (seasonalPeriod <= 0) {
					setMessage("Seasonal Period must be > 0.",
							IMessageProvider.ERROR);
					return false;
				}
			} catch (NumberFormatException e) {
				setMessage("Inter Burst Period must be a number.",
						IMessageProvider.ERROR);
				return false;
			}
		}

		if (offsetText.isEnabled()) {
			try {
				offset = Double.parseDouble(offsetText.getText().trim());
				if (offset < 0) {
					setMessage("First Burst Offset must not be negative.",
							IMessageProvider.ERROR);
					return false;
				}
			} catch (NumberFormatException e) {
				setMessage("First Burst Offset must be a number.",
						IMessageProvider.ERROR);
				return false;
			}
		}

		if (seasonalsPerTrendText.isEnabled()) {
			try {
				seasonalsPerTrend = Integer.parseInt(seasonalsPerTrendText
						.getText().trim());
				if (seasonalsPerTrend < 0) {
					setMessage("Maximum Burst Value must be >= 0.",
							IMessageProvider.ERROR);
					return false;
				}
			} catch (NumberFormatException e) {
				setMessage("Maximum Burst Value be an integer number.",
						IMessageProvider.ERROR);
				return false;
			}
		}

		CustomDlimModelWizard wizard = (CustomDlimModelWizard) getWizard();
		setMessage(getDescription());
		if (!filePath.isEmpty()) {
			/*
			 * ModelExtractor.extractArrivalRateFileIntoSequence(rootSequence,
			 * filePath, seasonalPeriod, offset, seasonalsPerTrend,
			 * wizard.getSeasonalPage().getSeasonalShape(),
			 * wizard.getTrendPage().getTrendShape(),
			 * wizard.getTrendPage().getOperatorLiteral());
			 */
			try {
				HLDlimParameterContainer container = ModelExtractor
						.extractArrivalRateFileIntoParameters(filePath,
								seasonalPeriod, offset, seasonalsPerTrend,
								wizard.getSeasonalPage().getSeasonalShape(),
								wizard.getTrendPage().getTrendShape(), wizard
								.getTrendPage().getOperatorLiteral(),
								extractNoiseButton.getSelection());
				wizard.getSeasonalPage()
				.fillPageWithHLDlimParameters(container);
				wizard.getTrendPage().fillPageWithHLDlimParameters(container);
				wizard.getBurstPage().fillPageWithHLDlimParameters(container);
			} catch (CalibrationException e) {
				setMessage("Extraction Error: " + e.getMessage(),
						IMessageProvider.ERROR);
			}

		}
		// else {
		// clear Model
		//}
		updatePlot();
		return true;
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#parseParameters
	 * (tools.descartes.dlim.extractor.HLDlimParameterContainer)
	 */
	@Override
	protected void parseParameters(HLDlimParameterContainer container) {
		// do nothing
	}
}
