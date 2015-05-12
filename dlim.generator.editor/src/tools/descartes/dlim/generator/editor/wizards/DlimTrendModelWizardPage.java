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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.Constant;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Function;
import tools.descartes.dlim.Operator;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.assistant.Calibrator;
import tools.descartes.dlim.extractor.HLDlimParameterContainer;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Provides GUI for the entering of HLDLIM parameters for the Trend part..
 *
 * @author Joakim von Kistowski
 */
public class DlimTrendModelWizardPage extends DlimModelWizardPage {

	private Text periodNumText;
	private Text listControlText;

	private Combo shapeCombo;
	private Combo operatorCombo;

	private List trendList;

	private Button addToListButton;
	private Button toggleDecompositionButton;

	private double periodNum = 7;
	private double offset = 0.0;

	private double periodDuration;
	private double addToList = 0;

	private Combinator trendCombinator;

	/**
	 * Instantiates a new dlim trend model wizard page.
	 *
	 * @param pageName the page name
	 * @param rootSequence the root sequence
	 */
	protected DlimTrendModelWizardPage(String pageName, Sequence rootSequence) {
		super(pageName, rootSequence);
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#fillInteractiveArea
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void fillInteractiveArea(Composite interactiveArea) {
		// periodDuration = calculatePeriodDuration();
		Composite textFieldComposite = new Composite(interactiveArea, SWT.NONE);
		GridLayout fieldLayout = new GridLayout(4, false);
		fieldLayout.marginWidth = 5;
		fieldLayout.marginHeight = 5;
		fieldLayout.verticalSpacing = 2;
		fieldLayout.horizontalSpacing = 0;
		textFieldComposite.setLayout(fieldLayout);
		createPeriodNumField(textFieldComposite);
		// createOffsetField(textFieldComposite);

		// list view here
		Composite listComposite = new Composite(interactiveArea, SWT.NONE);
		GridLayout listLayout = new GridLayout(1, false);
		listLayout.marginWidth = 5;
		listLayout.marginHeight = 5;
		listLayout.verticalSpacing = 0;
		listLayout.horizontalSpacing = 0;
		listComposite.setLayout(listLayout);
		createTrendList(listComposite);

		// list view controls here
		Composite listControlComposite = new Composite(interactiveArea,
				SWT.NONE);
		GridLayout listControlLayout = new GridLayout(3, false);
		listControlLayout.marginWidth = 5;
		listControlLayout.marginHeight = 5;
		listControlLayout.verticalSpacing = 0;
		listControlLayout.horizontalSpacing = 4;
		listControlComposite.setLayout(listControlLayout);
		createListControlField(listControlComposite);

		Composite dropDownComposite = new Composite(interactiveArea, SWT.NONE);
		GridLayout dropDownLayout = new GridLayout(2, false);
		dropDownLayout.marginWidth = 5;
		dropDownLayout.marginHeight = 5;
		dropDownLayout.verticalSpacing = 2;
		dropDownLayout.horizontalSpacing = 0;
		dropDownComposite.setLayout(dropDownLayout);
		createFormSelectionField(dropDownComposite);
		createOperatorSelectionField(dropDownComposite);

		Composite toggleDecompositionComposite = new Composite(interactiveArea,
				SWT.NONE);
		GridLayout decompositionLayout = new GridLayout(1, false);
		decompositionLayout.marginWidth = 5;
		decompositionLayout.marginHeight = 5;
		decompositionLayout.verticalSpacing = 0;
		decompositionLayout.horizontalSpacing = 0;
		toggleDecompositionComposite.setLayout(decompositionLayout);
		createDecompositionButton(toggleDecompositionComposite);

	}

	private void createPeriodNumField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Number of Seasonal Periods within one Trend: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		periodNumText = new Text(parent, SWT.BORDER);
		periodNumText.setText("" + periodNum);
		addValidationListener(periodNumText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		periodNumText.setLayoutData(textData);
	}

	private void createFormSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Select Trend Shape: ");
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

	private void createOperatorSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Select Operator: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		operatorCombo = new Combo(parent, SWT.BORDER);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;

		operatorCombo.setLayoutData(textData);

		// populate operatorCombo
		for (Operator op : Operator.values()) {
			operatorCombo.add(op.getLiteral());
		}
		operatorCombo.setText("MULT");
		addValidationListener(operatorCombo);
	}

	private void createTrendList(Composite parent) {
		GridData parentData = new GridData();
		parentData.grabExcessHorizontalSpace = true;
		parentData.grabExcessVerticalSpace = true;
		parentData.horizontalAlignment = GridData.FILL;
		parentData.verticalAlignment = GridData.FILL;
		parentData.heightHint = 160;
		parent.setLayoutData(parentData);

		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel
		.setText("Interpolate max. seasonal peak to target arrival rate using Trend: ");
		fieldLabel.setAlignment(SWT.LEFT);
		trendList = new List(parent, SWT.BORDER);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.grabExcessVerticalSpace = true;
		textData.horizontalAlignment = GridData.FILL;
		textData.verticalAlignment = GridData.FILL;

		trendList.setLayoutData(textData);

		trendList.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					trendList.remove(trendList.getSelectionIndices());
					validatePage();
				}
			}
		});
	}

	private void createListControlField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel
		.setText("    Interpolate max. seasonal peak to target arrival rate: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		listControlText = new Text(parent, SWT.BORDER);
		listControlText.setText("");

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 70;

		listControlText.setLayoutData(textData);

		addToListButton = new Button(parent, SWT.PUSH);
		addToListButton.setText("Add");
		addToListButton.setEnabled(false);

		listControlText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					addToList = Double.parseDouble(listControlText.getText()
							.trim());
					addToListButton.setEnabled(true);
				} catch (NumberFormatException exception) {
					addToListButton.setEnabled(false);
				}
			}
		});

		addToListButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleEvent(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleEvent(e);

			}

			private void handleEvent(SelectionEvent e) {
				trendList.add("" + addToList);
				validatePage();
			}
		});
	}

	private void createDecompositionButton(Composite parent) {
		GridData parentData = new GridData();
		parentData.horizontalAlignment = GridData.FILL;
		parent.setLayoutData(parentData);

		toggleDecompositionButton = new Button(parent, SWT.CHECK);
		toggleDecompositionButton
		.setText("Explicitly show Trend Contribution in Plot");
		toggleDecompositionButton.setSelection(true);
		toggleDecompositionButton.setLayoutData(new GridData(SWT.RIGHT,
				SWT.TOP, true, true, 1, 1));

		getDlimPlotter().setDecompositionMode(true);
		toggleDecompositionButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getDlimPlotter().setDecompositionMode(toggleDecompositionButton
						.getSelection());
				validatePage();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				getDlimPlotter().setDecompositionMode(toggleDecompositionButton
						.getSelection());
				validatePage();
			}
		});
	}

	/* (non-Javadoc)
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#validatePage()
	 */
	@Override
	protected boolean validatePage() {

		// periodDuration = calculatePeriodDuration();
		try {
			periodNum = Double.parseDouble(periodNumText.getText().trim());
			if (periodNum < 0) {
				setMessage("The Trends' duration cannot be negative",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Number of Periods per Trend must be a number.",
					IMessageProvider.ERROR);
			return false;
		}

		/*
		 * try { offset = Double.parseDouble(offsetText.getText().trim()); if
		 * (offset < 0 || offset >= periodDuration) {
		 * setMessage("The offset must take place within the first seasonal period."
		 * , IMessageProvider.ERROR); return false; } } catch
		 * (NumberFormatException e) { setMessage("Offset must be a number.",
		 * IMessageProvider.ERROR); return false; }
		 */

		setMessage(getDescription());
		populateModel();
		updatePlot();
		return true;

	}

	private void populateModel() {
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		getRootSequence().getCombine().remove(trendCombinator);
		getRootSequence().setTerminateAfterLoops(1);
		try {
			trendCombinator = factory.createCombinator();
			trendCombinator.setOperator(Operator.get(operatorCombo.getText()
					.trim()));
			Sequence trendSequence = factory.createSequence();
			trendSequence.setName("trends");
			trendSequence.setTerminateAfterLoops(1);
			trendCombinator.setFunction(trendSequence);
			if (periodNum > 0 && trendList.getItemCount() > 1) {
				int loops = (int) periodNum * (trendList.getItemCount() - 1);

				if (offset > 0) {
					TimeDependentFunctionContainer offsetElement = factory
							.createTimeDependentFunctionContainer();
					offsetElement.setName("trendOffset");
					offsetElement.setDuration(offset);
					Constant offsetConstant = factory.createConstant();
					offsetElement.setFunction(offsetConstant);
					trendSequence.getSequenceFunctionContainers().add(
							offsetElement);
					loops++;
				}

				// populate trendSequence
				for (int i = 1; i < trendList.getItemCount(); i++) {
					TimeDependentFunctionContainer trendElement = factory
							.createTimeDependentFunctionContainer();
					trendElement.setName("trend" + i);
					trendElement.setDuration(periodDuration * periodNum);
					Trend trend = (Trend) factory
							.create((EClass) (DlimPackage.eINSTANCE
									.getEClassifier(shapeCombo.getText().trim())));
					trendElement.setFunction(trend);
					trendSequence.getSequenceFunctionContainers().add(
							trendElement);
				}

				if (offset > 0) {
					TimeDependentFunctionContainer offsetElement = factory
							.createTimeDependentFunctionContainer();
					offsetElement.setName("holdLastValue");
					offsetElement.setDuration(periodDuration - offset);
					Constant offsetConstant = factory.createConstant();
					// offsetConstant.setValue(value);
					offsetElement.setFunction(offsetConstant);
					trendSequence.getSequenceFunctionContainers().add(
							offsetElement);
				}

				getRootSequence().getCombine().add(trendCombinator);
				getRootSequence().setTerminateAfterLoops(loops);

				// calibrate Trends
				int i = 0;
				for (TimeDependentFunctionContainer e : trendSequence
						.getSequenceFunctionContainers()) {
					Function f = e.getFunction();
					if (f instanceof Trend) {
						Trend trend = (Trend) f;
						trend.setFunctionOutputAtStart(calibrateTrendStartValue(
								trend,
								Double.parseDouble(trendList.getItem(i).trim()),
								i));
						i++;
						trend.setFunctionOutputAtEnd(calibrateTrendEndValue(
								trend,
								Double.parseDouble(trendList.getItem(i).trim()),
								i));
					}
				}
				// set offset constant values
				if (offset > 0.0) {
					Constant offset1 = (Constant) trendSequence
							.getSequenceFunctionContainers().get(0)
							.getFunction();
					offset1.setConstant(((Trend) (trendSequence
							.getSequenceFunctionContainers().get(1)
							.getFunction())).getFunctionOutputAtStart());

					int lastIndex = trendSequence
							.getSequenceFunctionContainers().size() - 1;
					Constant offset2 = (Constant) trendSequence
							.getSequenceFunctionContainers().get(lastIndex)
							.getFunction();
					offset2.setConstant(((Trend) (trendSequence
							.getSequenceFunctionContainers().get(lastIndex - 1)
							.getFunction())).getFunctionOutputAtEnd());
				}
			}
		} catch (NullPointerException e) {
			// do nothing
		}
	}

	private double calibrateTrendStartValue(Trend trend, double value, int index) {
		ModelEvaluator evaluator = new ModelEvaluator(getRootSequence(), 0,
				IGeneratorConstants.CALIBRATION);
		double startValue = value;
		try {
			startValue = Calibrator.calibrateTrendStartValue(value, trend,
					evaluator);
		} catch (CalibrationException e) {
			setMessage("Trend Value " + index + ": " + e.getMessage(),
					IMessageProvider.WARNING);
		}
		return startValue;
	}

	private double calibrateTrendEndValue(Trend trend, double value, int index) {
		ModelEvaluator evaluator = new ModelEvaluator(getRootSequence(), 0,
				IGeneratorConstants.CALIBRATION);
		double endValue = value;
		try {
			endValue = Calibrator.calibrateTrendEndValue(value, trend,
					evaluator);
		} catch (CalibrationException e) {
			setMessage("Trend Value " + index + ": " + e.getMessage(),
					IMessageProvider.WARNING);
		}
		return endValue;
	}

	/**
	 * Calculate period duration.
	 *
	 * @param highestPeakTime the highest peak time
	 * @param periodDuration the period duration
	 */
	protected void calculatePeriodDuration(double highestPeakTime,
			double periodDuration) {
		this.periodDuration = periodDuration;
		/*
		 * for (TimeDependentFunctionContainer e:
		 * rootSequence.getSequenceFunctionContainers()) { periodDuration +=
		 * e.getDuration(); }
		 */
		// set initial offset to highest peak
		offset = highestPeakTime;
		try {
			validatePage();
		} catch (NullPointerException e) {
			//do nothing
		}
	}

	/**
	 * Gets the trend shape.
	 *
	 * @return the trend shape
	 */
	public String getTrendShape() {
		return shapeCombo.getText();
	}

	/**
	 * Gets the operator literal.
	 *
	 * @return the operator literal
	 */
	public String getOperatorLiteral() {
		return operatorCombo.getText();
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#parseParameters
	 * (tools.descartes.dlim.extractor.HLDlimParameterContainer)
	 */
	@Override
	protected void parseParameters(HLDlimParameterContainer container) {
		shapeCombo.setText(container.getTrendShape());
		operatorCombo.setText(container.getOperatorLiteral());
		periodNumText.setText("" + container.getSeasonalsPerTrend());
		listControlText.setText("");

		periodNum = container.getSeasonalsPerTrend();
		offset = container.getTrendOffset();
		periodDuration = container.getSeasonalPeriod();

		trendList.removeAll();
		for (int i = 0; i < container.getTrendPoints().length; i++) {
			trendList.add("" + container.getTrendPoints()[i]);
		}
	}

}
