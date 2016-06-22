/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.extractor.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Operator;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.extractor.ModelExtractor;
import tools.descartes.dlim.extractor.utils.Autocorrelation;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.editor.utils.ProjectManager;

/**
 * This dialog takes the user parameters for the simple extraction process.
 * 
 * @author Joakim von Kistowski
 *
 */
public class LaunchExtractionDialog extends TitleAreaDialog {

	/**
	 * ID of Eclipse Preference for lasts used trace parameters.
	 */
	private static final String SEASONAL_PERIOD_ID =
			"tools.descartes.dlim.extractor.LaunchExtractionDialog.SEASONAL_PERIOD_ID";
	private static final String TREND_LENGTH_ID =
			"tools.descartes.dlim.extractor.LaunchExtractionDialog.TREND_LENGTH_ID";
	private static final String TREND_SHAPE_ID =
			"tools.descartes.dlim.extractor.LaunchExtractionDialog.TREND_SHAPE_ID";
	private static final String SEASONAL_SHAPE_ID =
			"tools.descartes.dlim.extractor.LaunchExtractionDialog.SEASONAL_SHAPE_ID";
	private static final String OPERATOR_LITERAL_ID =
			"tools.descartes.dlim.extractor.LaunchExtractionDialog.OPERATOR_LITERAL_ID";
	private static final String EXTRACT_NOISE_ID =
			"tools.descartes.dlim.extractor.LaunchExtractionDialog.EXTRACT_NOISE_ID";
	
	private Text seasonalPeriodText;
	private Text seasonalsPerTrendText;

	private Button extractNoiseButton;
	private Button autocorrelationButton;

	private Combo seasonalShapeCombo;
	private Combo trendShapeCombo;
	private Combo operatorCombo;

	private double seasonalPeriod = 24.0;
	private int seasonalsPerTrend = 7;
	private String seasonalShape = "SinTrend";
	private String trendShape = "SinTrend";
	private String operatorLiteral = "MULT";
	private boolean extractNoise = false;
	private boolean canceled = false;

	// For model extraction
	private Sequence rootSequence;
	private List<ArrivalRateTuple> readArrivalRates;

	/**
	 * Creates a new dialog.
	 *
	 * @param parentShell the parent shell
	 * @param rootSequence the root sequence
	 * @param readArrivalRates the read arrival rates
	 */
	public LaunchExtractionDialog(Shell parentShell, Sequence rootSequence,
			List<ArrivalRateTuple> readArrivalRates) {
		super(parentShell);
		this.rootSequence = rootSequence;
		this.readArrivalRates = readArrivalRates;
	}

	/**
	 * Set titles.
	 */
	public void create() {
		super.create();
		setTitle("Extract Sequence from Arrival Rate File");
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
		Composite gridComposite = new Composite(columnContainer, SWT.NONE);
		GridLayout gridLayout = new GridLayout(5, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		gridComposite.setLayout(gridLayout);
		createSeasonalPeriodParameterField(gridComposite);
		createSeasonalsPerTrendParameterField(gridComposite);
		createExtractNoiseCheckBox(gridComposite);
		Composite formSelectionComposite = new Composite(columnContainer,
				SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(2, false);
		formSelectionComposite.setLayout(formSelectionLayout);
		createSeasonalShapeSelectionField(formSelectionComposite);
		createTrendShapeSelectionField(formSelectionComposite);
		createOperatorSelectionField(formSelectionComposite);

		return dialogContainer;
	}

	// period of seasonal part
	private void createSeasonalPeriodParameterField(Composite container) {
		String lastSeasonalPeriod = ProjectManager.retrieveStringFromPreferences(SEASONAL_PERIOD_ID);
		if (!lastSeasonalPeriod.isEmpty()) {
			seasonalPeriod = Double.parseDouble(lastSeasonalPeriod);
		}
		
		Label parameterFieldLabel = new Label(container, SWT.NONE);
		parameterFieldLabel.setText("Seasonal Period: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 40;
		seasonalPeriodText = new Text(container, SWT.BORDER);
		seasonalPeriodText.setText(String.valueOf(seasonalPeriod));
		seasonalPeriodText.setLayoutData(parameterFieldData);
		autocorrelationButton = new Button(container, SWT.PUSH);
		autocorrelationButton.setText("Detect Period");
		autocorrelationButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selected();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				selected();
			}
			private void selected() {
				double period = Autocorrelation.seasonalPeriodUsingAutocorrelation(readArrivalRates);
				setSeasonalPeriod(period);
			}
		});
	}

	// seasonals per trend (implicit trend duration)
	private void createSeasonalsPerTrendParameterField(Composite container) {
		String lastSeasonalsPerTrend = ProjectManager.retrieveStringFromPreferences(TREND_LENGTH_ID);
		if (!lastSeasonalsPerTrend.isEmpty()) {
			seasonalsPerTrend = Integer.parseInt(lastSeasonalsPerTrend);
		}
		
		Label parameterFieldLabel = new Label(container, SWT.NONE);
		parameterFieldLabel.setText("    Seasonal Periods per Trend: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 40;
		seasonalsPerTrendText = new Text(container, SWT.BORDER);
		seasonalsPerTrendText.setText(String.valueOf(seasonalsPerTrend));
		seasonalsPerTrendText.setLayoutData(parameterFieldData);
	}

	private void createExtractNoiseCheckBox(Composite container) {
		boolean boxSelection = false;
		String lastExtractNoise = ProjectManager.retrieveStringFromPreferences(EXTRACT_NOISE_ID);
		if (!lastExtractNoise.isEmpty()) {
			boxSelection = Boolean.parseBoolean(lastExtractNoise);
		}
		
		extractNoiseButton = new Button(container, SWT.CHECK);
		extractNoiseButton.setText("Extract Noise");
		extractNoiseButton.setSelection(boxSelection);
	}

	private void createSeasonalShapeSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Select Seasonal Shape: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		seasonalShapeCombo = new Combo(parent, SWT.BORDER);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;

		seasonalShapeCombo.setLayoutData(textData);

		// populate shapeCombo
		for (String name : getInitialTrendNames()) {
			seasonalShapeCombo.add(name);
		}
		seasonalShapeCombo.select(seasonalShapeCombo.getItemCount() - 1);
		String lastSeasonalShape = ProjectManager.retrieveStringFromPreferences(SEASONAL_SHAPE_ID);
		if (!lastSeasonalShape.isEmpty()) {
			seasonalShapeCombo.setText(lastSeasonalShape);
		}
	}

	private void createTrendShapeSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Select Trend Shape: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		trendShapeCombo = new Combo(parent, SWT.BORDER);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;

		trendShapeCombo.setLayoutData(textData);

		// populate shapeCombo
		for (String name : getInitialTrendNames()) {
			trendShapeCombo.add(name);
		}
		trendShapeCombo.select(trendShapeCombo.getItemCount() - 1);
		String lastTrendShape = ProjectManager.retrieveStringFromPreferences(TREND_SHAPE_ID);
		if (!lastTrendShape.isEmpty()) {
			trendShapeCombo.setText(lastTrendShape);
		}
	}

	private void createOperatorSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Select Trend Operator: ");
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
		String lastOperator = ProjectManager.retrieveStringFromPreferences(OPERATOR_LITERAL_ID);
		if (!lastOperator.isEmpty()) {
			operatorCombo.setText(lastOperator);
		}
	}

	private Collection<String> getInitialTrendNames() {
		ArrayList<String> initialObjectNames = new ArrayList<String>();
		for (EClassifier eClassifier : DlimPackage.eINSTANCE.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				if (!eClass.isAbstract()
						&& DlimPackage.eINSTANCE.getTrend().isSuperTypeOf(
								eClass)) {
					initialObjectNames.add(eClass.getName());
				}
			}
		}
		Collections.sort(initialObjectNames,
				CommonPlugin.INSTANCE.getComparator());
		return initialObjectNames;
	}

	/**
	 * Dialog window title.
	 *
	 * @param newShell the new shell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Extract Sequence");
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
			seasonalPeriod = Double.parseDouble(seasonalPeriodText.getText()
					.trim());
			if (seasonalPeriod <= 0) {
				setMessage("Seasonal Period must be greater than 0.",
						IMessageProvider.ERROR);
				error = true;
			}
		} catch (NumberFormatException e) {
			setMessage("Seasonal Period must be a number.",
					IMessageProvider.ERROR);
			error = true;
		}

		try {
			seasonalsPerTrend = Integer.parseInt(seasonalsPerTrendText
					.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Seasonals per Trend must be an integer number.",
					IMessageProvider.ERROR);
			error = true;
		}

		extractNoise = extractNoiseButton.getSelection();
		ProjectManager.saveStringToPreferences(EXTRACT_NOISE_ID, String.valueOf(extractNoise));
		seasonalShape = seasonalShapeCombo.getText().trim();
		ProjectManager.saveStringToPreferences(SEASONAL_SHAPE_ID, seasonalShape);
		trendShape = trendShapeCombo.getText().trim();
		ProjectManager.saveStringToPreferences(TREND_SHAPE_ID, trendShape);
		operatorLiteral = operatorCombo.getText().trim();
		ProjectManager.saveStringToPreferences(OPERATOR_LITERAL_ID, operatorLiteral);

		if (!error) {

			//store data
			ProjectManager.saveStringToPreferences(SEASONAL_PERIOD_ID, String.valueOf(seasonalPeriod));
			ProjectManager.saveStringToPreferences(TREND_LENGTH_ID, String.valueOf(seasonalsPerTrend));
			
			// Perform extraction
			try {
				performExtraction(rootSequence,
								readArrivalRates, getSeasonalPeriod(),
								getSeasonalsPerTrend(), getSeasonalShape(),
								getTrendShape(), getOperatorLiteral(),
								isExtractNoise());
				super.okPressed();
			} catch (CalibrationException e) {
				setMessage("Model Extraction Error: " + e.getMessage(),
						IMessageProvider.ERROR);
			}

		}
	}
	
	/**
	 * Launches the Extraction. Override to reuse dialog for other extractions.
	 * @param root the root
	 * @param arrList the read list of arrival rates
	 * @param period the seasonal period
	 * @param seasonalsPerTrend the seasonals per trend (trend segment length)
	 * @param seasonalShape the seasonal shape
	 * @param trendShape the trend shape
	 * @param operatorLiteral the operator literal (how is the trend to be applied to the seasonal part)
	 * @param extractNoise true, if noise is to be reduced and extracted
	 * @throws CalibrationException exception if calibration is ineffective (devision by 0 or unused function)
	 */
	protected void performExtraction(Sequence root,
			List<ArrivalRateTuple> arrList, double period,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral, boolean extractNoise) throws CalibrationException {
		ModelExtractor
		.extractArrivalRateFileIntoSequenceNoSplits(root,
				arrList, getSeasonalPeriod(),
				getSeasonalsPerTrend(), getSeasonalShape(),
				getTrendShape(), getOperatorLiteral(),
				isExtractNoise());
	}

	/**
	 * Gets the seasonal period.
	 *
	 * @return the seasonal period
	 */
	public double getSeasonalPeriod() {
		return seasonalPeriod;
	}
	
	/**
	 * Sets the seasonal period. Updates dialog texts.
	 *
	 * @param seasonalPeriod the seasonal period
	 */
	protected void setSeasonalPeriod(double seasonalPeriod) {
		this.seasonalPeriod = seasonalPeriod;
		seasonalPeriodText.setText(String.valueOf(seasonalPeriod));
	}

	/**
	 * Gets the seasonals per trend.
	 *
	 * @return the seasonals per trend
	 */
	public int getSeasonalsPerTrend() {
		return seasonalsPerTrend;
	}

	/**
	 * Gets the seasonal shape.
	 *
	 * @return the seasonal shape
	 */
	public String getSeasonalShape() {
		return seasonalShape;
	}

	/**
	 * Gets the trend shape.
	 *
	 * @return the trend shape
	 */
	public String getTrendShape() {
		return trendShape;
	}

	/**
	 * Gets the operator literal.
	 *
	 * @return the operator literal
	 */
	public String getOperatorLiteral() {
		return operatorLiteral;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		// return new Point(340,600);
		return super.getInitialSize();
	}

	/**
	 * Checks if is extract noise.
	 *
	 * @return true, if is extract noise
	 */
	public boolean isExtractNoise() {
		return extractNoise;
	}
}
