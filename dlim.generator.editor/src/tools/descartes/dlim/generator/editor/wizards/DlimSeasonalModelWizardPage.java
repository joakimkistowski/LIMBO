/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.wizards;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.Constant;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.extractor.HLDlimParameterContainer;

/**
 * Provides GUI for the entering of the HLDLIM parameters for the Seasonal part.
 *
 * @author Joakim von Kistowski
 */
public class DlimSeasonalModelWizardPage extends DlimModelWizardPage {

	private Text periodText;
	private Text numPeaksText;
	private Text baseText;
	private Text innerBaseText;
	private Text firstPeakText;
	private Text lastPeakText;
	private Text peakIntervalText;

	private Combo shapeCombo;

	private double period = 24.0;
	private int numPeaks = 0;
	private double base = 0;
	private double innerBase = 0;
	private double firstPeak = 10;
	private double lastPeak = 10;
	private double peakInterval = 12;


	/**
	 * Instantiates a new dlim seasonal model wizard page.
	 *
	 * @param pageName the page name
	 * @param rootSequence the root sequence
	 */
	protected DlimSeasonalModelWizardPage(String pageName, Sequence rootSequence) {
		super(pageName, rootSequence);
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#fillInteractiveArea
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void fillInteractiveArea(Composite interactiveArea) {
		Composite parent = new Composite(interactiveArea, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		parent.setLayout(gridLayout);

		createDurationField(parent);
		createNumPeaksField(parent);
		createBaseField(parent);
		createInnerBaseField(parent);
		createfirstPeakField(parent);
		createlastPeakField(parent);
		createPeakIntervalField(parent);

		Composite formSelectionComposite = new Composite(interactiveArea,
				SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(2, false);
		formSelectionLayout.marginWidth = 5;
		formSelectionLayout.marginHeight = 5;
		formSelectionLayout.verticalSpacing = 0;
		formSelectionLayout.horizontalSpacing = 0;
		formSelectionComposite.setLayout(formSelectionLayout);

		createFormSelectionField(formSelectionComposite);

	}

	private void createDurationField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Period: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		periodText = new Text(parent, SWT.BORDER);
		periodText.setText("" + period);
		addValidationListener(periodText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		periodText.setLayoutData(textData);
	}

	private void createNumPeaksField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Number of Peaks: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		numPeaksText = new Text(parent, SWT.BORDER);
		numPeaksText.setText("" + numPeaks);
		addValidationListener(numPeaksText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		numPeaksText.setLayoutData(textData);
	}

	private void createBaseField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Base Arrival Rate Level: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		baseText = new Text(parent, SWT.BORDER);
		baseText.setText("" + base);
		addValidationListener(baseText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		baseText.setLayoutData(textData);
	}

	private void createfirstPeakField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    First Peak Arrival Rate: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		firstPeakText = new Text(parent, SWT.BORDER);
		firstPeakText.setText("" + firstPeak);
		firstPeakText.setEnabled(false);
		addValidationListener(firstPeakText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		firstPeakText.setLayoutData(textData);
	}

	private void createlastPeakField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Last Peak Arrival Rate: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		lastPeakText = new Text(parent, SWT.BORDER);
		lastPeakText.setText("" + lastPeak);
		lastPeakText.setEnabled(false);
		addValidationListener(lastPeakText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		lastPeakText.setLayoutData(textData);
	}

	private void createInnerBaseField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Base Arrival Rate Level between Peaks: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		innerBaseText = new Text(parent, SWT.BORDER);
		innerBaseText.setText("" + innerBase);
		addValidationListener(innerBaseText);
		innerBaseText.setEnabled(false);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		innerBaseText.setLayoutData(textData);
	}

	private void createPeakIntervalField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Interval containing Peaks: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		peakIntervalText = new Text(parent, SWT.BORDER);
		peakIntervalText.setText("12.0");
		addValidationListener(peakIntervalText);
		peakIntervalText.setEnabled(false);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		peakIntervalText.setLayoutData(textData);
	}

	private void createFormSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Select Seasonal Shape: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		shapeCombo = new Combo(parent, SWT.BORDER);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;

		shapeCombo.setLayoutData(textData);

		// populate shapeCombo
		for (String name : getInitialTrendNames()) {
			shapeCombo.add(name);
		}
		shapeCombo.select(0);
		addValidationListener(shapeCombo);
	}

	/* (non-Javadoc)
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#validatePage()
	 */
	@Override
	protected boolean validatePage() {
		try {
			period = Double.parseDouble(periodText.getText().trim());
			if (period <= 0) {
				setMessage("Period must be > 0.", IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Period must be a number.", IMessageProvider.ERROR);
			return false;
		}

		try {
			numPeaks = Integer.parseInt(numPeaksText.getText().trim());
			if (firstPeakText != null && innerBaseText != null
					&& lastPeakText != null && peakIntervalText != null) {
				if (numPeaks > 0) {
					firstPeakText.setEnabled(true);
				} else {
					firstPeakText.setEnabled(false);
				}
				if (numPeaks > 1) {
					innerBaseText.setEnabled(true);
					lastPeakText.setEnabled(true);
					peakIntervalText.setEnabled(true);
				} else {
					innerBaseText.setEnabled(false);
					lastPeakText.setEnabled(false);
					peakIntervalText.setEnabled(false);
				}
			}
			if (numPeaks < 0) {
				setMessage("Number of Peaks must be >= 0.",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Number of Peaks must be an Integer.",
					IMessageProvider.ERROR);
			return false;
		}

		try {
			base = Double.parseDouble(baseText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Base must be a number.", IMessageProvider.ERROR);
			return false;
		}

		try {
			if (innerBaseText.isEnabled()) {
				innerBase = Double.parseDouble(innerBaseText.getText().trim());
			}
		} catch (NumberFormatException e) {
			setMessage("Base between Peaks must be a number.",
					IMessageProvider.ERROR);
			return false;
		} catch (NullPointerException e) {
			return true;
		}

		try {
			if (firstPeakText.isEnabled()) {
				firstPeak = Double.parseDouble(firstPeakText.getText().trim());
				if (firstPeak < base) {
					setMessage(
							"First Peak Arrival Rate must not be smaller than the base level.",
							IMessageProvider.ERROR);
					return false;
				}
			}
		} catch (NumberFormatException e) {
			setMessage("First Peak Arrival Rate must be a number.",
					IMessageProvider.ERROR);
			return false;
		} catch (NullPointerException e) {
			return true;
		}

		try {
			if (lastPeakText.isEnabled()) {
				lastPeak = Double.parseDouble(lastPeakText.getText().trim());
				if (lastPeak < base) {
					setMessage(
							"Last Peak Arrival Rate must not be smaller than the base level.",
							IMessageProvider.ERROR);
					return false;
				}
			}
		} catch (NumberFormatException e) {
			setMessage("Last Peak Arrival Rate must be a number.",
					IMessageProvider.ERROR);
			return false;
		} catch (NullPointerException e) {
			return true;
		}

		try {
			if (peakIntervalText.isEnabled()) {
				peakInterval = Double.parseDouble(peakIntervalText.getText()
						.trim());
				if (peakInterval <= 0 || peakInterval >= period) {
					setMessage(
							"Peak Interval must range between 0 and Period ("
									+ period + ")", IMessageProvider.ERROR);
					return false;
				}
			}
		} catch (NumberFormatException e) {
			setMessage("Peak Interval must be a number.",
					IMessageProvider.ERROR);
			return false;
		} catch (NullPointerException e) {
			return true;
		}

		setMessage(getDescription());
		populateModel();
		updatePlot();
		preSetTrends();
		return true;
	}

	private void populateModel() {
		getRootSequence().getSequenceFunctionContainers().clear();

		getRootSequence().setTerminateAfterLoops(1);
		getRootSequence().setTerminateAfterTime(0.0);

		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();

		if (numPeaks == 0) {
			TimeDependentFunctionContainer constantElement = factory
					.createTimeDependentFunctionContainer();
			constantElement.setName("constantSeasonal");
			constantElement.setDuration(period);
			Constant constant = factory.createConstant();
			constant.setConstant(base);
			constantElement.setFunction(constant);
			getRootSequence().getSequenceFunctionContainers().add(constantElement);
		}

		try {
			if (numPeaks > 0) {
				double currentArrivalRate = base;

				for (int i = 0; i < (numPeaks - 1) * 2 + 1; i++) {
					TimeDependentFunctionContainer trendElement = factory
							.createTimeDependentFunctionContainer();
					trendElement.setName("seasonal" + i);
					if (i == 0) {
						trendElement.setDuration((period - peakInterval) / 2.0);
						if (numPeaks == 1) {
							trendElement.setDuration(period / 2.0);
						}
					} else {
						trendElement.setDuration(peakInterval
								/ ((numPeaks - 1) * 2.0));
					}
					Trend trend = (Trend) factory
							.create((EClass) (DlimPackage.eINSTANCE
									.getEClassifier(shapeCombo.getText().trim())));
					trend.setFunctionOutputAtStart(currentArrivalRate);
					if (numPeaks == 1) {
						currentArrivalRate = firstPeak;
					} else if (i % 2 == 0) {
						currentArrivalRate = firstPeak + (lastPeak - firstPeak)
								* (i / 2) / (numPeaks - 1);
					} else {
						currentArrivalRate = innerBase;
					}
					trend.setFunctionOutputAtEnd(currentArrivalRate);
					trendElement.setFunction(trend);
					getRootSequence().getSequenceFunctionContainers().add(
							trendElement);
				}
				Trend trend = (Trend) factory
						.create((EClass) (DlimPackage.eINSTANCE
								.getEClassifier(shapeCombo.getText().trim())));
				trend.setFunctionOutputAtStart(currentArrivalRate);
				trend.setFunctionOutputAtEnd(base);
				TimeDependentFunctionContainer trendElement = factory
						.createTimeDependentFunctionContainer();
				trendElement.setDuration((period - peakInterval) / 2.0);
				if (numPeaks == 1) {
					trendElement.setDuration(period / 2.0);
				}
				trendElement.setName("seasonal" + numPeaks * 2);
				trendElement.setFunction(trend);
				getRootSequence().getSequenceFunctionContainers().add(trendElement);
			}
		} catch (NullPointerException e) {
			//do nothing
		}
	}

	private void preSetTrends() {
		if (getDlimModelWizard().getTrendPage() instanceof DlimTrendModelWizardPage) {
			DlimTrendModelWizardPage page = getDlimModelWizard().getTrendPage();
			double highestPeakTime = 0.0;

			if (firstPeak > 0 || lastPeak > 0) {
				if (numPeaks == 1 && firstPeak > 0) {
					highestPeakTime = getRootSequence().getLoopDuration() / 2.0;
					//this oddness check doesn't work on negative numbers,
					//fortunately there cannot be a negative amount of peaks
				} else if (firstPeak == lastPeak && numPeaks % 2 == 1) {
					highestPeakTime = getRootSequence().getLoopDuration() / 2.0;
				} else if (lastPeak > firstPeak) {
					highestPeakTime = (getRootSequence().getLoopDuration() + peakInterval) / 2.0;
				} else {
					highestPeakTime = (getRootSequence().getLoopDuration() - peakInterval) / 2.0;
				}
			}
			page.calculatePeriodDuration(highestPeakTime, period);
		}
	}

	/**
	 * Gets the seasonal shape.
	 *
	 * @return the seasonal shape
	 */
	public String getSeasonalShape() {
		return shapeCombo.getText();
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#parseParameters
	 * (tools.descartes.dlim.extractor.HLDlimParameterContainer)
	 */
	@Override
	protected void parseParameters(HLDlimParameterContainer container) {

		periodText.setText("" + container.getSeasonalPeriod());
		numPeaksText.setText("" + container.getPeakNum());
		baseText.setText("" + container.getBase());
		innerBaseText.setText("" + container.getInnerBase());
		firstPeakText.setText("" + container.getFirstPeak());
		lastPeakText.setText("" + container.getLastPeak());
		peakIntervalText.setText("" + container.getPeakIntervalWidth());
		shapeCombo.setText(container.getSeasonalShape());

		period = container.getSeasonalPeriod();
		numPeaks = container.getPeakNum();
		base = container.getBase();
		innerBase = container.getInnerBase();
		firstPeak = container.getFirstPeak();
		lastPeak = container.getLastPeak();
		peakInterval = container.getPeakIntervalWidth();

		preSetTrends();
	}
}
