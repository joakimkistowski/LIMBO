package dlim.extractor.dialogs;

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

import dlim.DlimPackage;
import dlim.Operator;
import dlim.Sequence;
import dlim.assistant.CalibrationException;
import dlim.extractor.ModelExtractor;
import dlim.generator.ArrivalRateTuple;

/**
 * This dialog takes the user parameters for the simple extraction process.
 * @author Jóakim G. v. Kistowski
 *
 */
public class LaunchExtractionDialog extends TitleAreaDialog {

	private Text seasonalPeriodText;
	private Text seasonalsPerTrendText;
	
	private Button extractNoiseButton;
	
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

	//For model extraction
	private Sequence rootSequence;
	private List<ArrivalRateTuple> readArrivalRates;
	
	/**
	 * Creates a new dialog.
	 * @param fileString
	 * @param projectPath
	 * @param parentShell
	 */
	public LaunchExtractionDialog(Shell parentShell, Sequence rootSequence, List<ArrivalRateTuple> readArrivalRates) {
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
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new FillLayout(SWT.VERTICAL));
		Composite gridComposite = new Composite(columnContainer, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		gridComposite.setLayout(gridLayout);
		createSeasonalPeriodParameterField(gridComposite);
		createSeasonalsPerTrendParameterField(gridComposite);
		createExtractNoiseCheckBox(gridComposite);
		Composite formSelectionComposite = new Composite(columnContainer, SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(2,false);
		formSelectionComposite.setLayout(formSelectionLayout);
		createSeasonalShapeSelectionField(formSelectionComposite);
		createTrendShapeSelectionField(formSelectionComposite);
		createOperatorSelectionField(formSelectionComposite);
		

		return dialogContainer;
	}
	
	//period of seasonal part
	private void createSeasonalPeriodParameterField(Composite container) {
		Label parameterFieldLabel = new Label(container, SWT.NONE);
		parameterFieldLabel.setText("Seasonal Period: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 40;
		seasonalPeriodText = new Text(container, SWT.BORDER);
		seasonalPeriodText.setText(String.valueOf(seasonalPeriod));
		seasonalPeriodText.setLayoutData(parameterFieldData);
	}
	
	//seasonals per trend (implicit trend duration)
	private void createSeasonalsPerTrendParameterField(Composite container) {
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
		extractNoiseButton = new Button(container, SWT.CHECK);
		extractNoiseButton.setText("Extract Noise");
		extractNoiseButton.setSelection(false);
	}
	
	private void createSeasonalShapeSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent,SWT.NONE);
		fieldLabel.setText("Select Seasonal Shape: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		seasonalShapeCombo = new Combo(parent, SWT.BORDER);
		
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;
		
		seasonalShapeCombo.setLayoutData(textData);
		
		//populate shapeCombo
		for (String name : getInitialTrendNames()) {
			seasonalShapeCombo.add(name);
		}
		seasonalShapeCombo.select(seasonalShapeCombo.getItemCount()-1);
	}
	
	private void createTrendShapeSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent,SWT.NONE);
		fieldLabel.setText("Select Trend Shape: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		trendShapeCombo = new Combo(parent, SWT.BORDER);
		
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;
		
		trendShapeCombo.setLayoutData(textData);
		
		//populate shapeCombo
		for (String name : getInitialTrendNames()) {
			trendShapeCombo.add(name);
		}
		trendShapeCombo.select(trendShapeCombo.getItemCount()-1);
	}
	
	private void createOperatorSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent,SWT.NONE);
		fieldLabel.setText("Select Trend Operator: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		operatorCombo = new Combo(parent, SWT.BORDER);
		
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 120;
		
		operatorCombo.setLayoutData(textData);
		
		//populate operatorCombo
		for (Operator op : Operator.values()) {
			operatorCombo.add(op.getLiteral());
		}
		operatorCombo.setText("MULT");
	}
	
	private Collection<String> getInitialTrendNames() {
		ArrayList<String> initialObjectNames = new ArrayList<String>();
		for (EClassifier eClassifier : DlimPackage.eINSTANCE.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass)eClassifier;
				if (!eClass.isAbstract() && DlimPackage.eINSTANCE.getTrend().isSuperTypeOf(eClass)) {
					initialObjectNames.add(eClass.getName());
				}
			}
		}
		Collections.sort(initialObjectNames, CommonPlugin.INSTANCE.getComparator());
		return initialObjectNames;
	}
	
	/**
	 * Dialog window title.
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
	 * @return
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
			seasonalPeriod = Double.parseDouble(seasonalPeriodText.getText().trim());
			if (seasonalPeriod <= 0) {
				setMessage("Seasonal Period must be greater than 0.", IMessageProvider.ERROR);
				error = true;
			}
		} catch (NumberFormatException e) {
			setMessage("Seasonal Period must be a number.", IMessageProvider.ERROR);
			error = true;
		}
		
		try {
			seasonalsPerTrend = Integer.parseInt(seasonalsPerTrendText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Seasonals per Trend must be an integer number.", IMessageProvider.ERROR);
			error = true;
		}
		
		extractNoise = extractNoiseButton.getSelection();
		seasonalShape = seasonalShapeCombo.getText().trim();
		trendShape = trendShapeCombo.getText().trim();
		operatorLiteral = operatorCombo.getText().trim();
		
		
		if (!error) {
			
			//Perform extraction
			try {
				ModelExtractor.extractArrivalRateFileIntoSequence(rootSequence,
						readArrivalRates,getSeasonalPeriod(),
						getSeasonalsPerTrend(),getSeasonalShape(),getTrendShape(),
						getOperatorLiteral(), isExtractNoise());
				super.okPressed();
			} catch (CalibrationException e) {
				setMessage("Model Extraction Error: " + e.getMessage(), IMessageProvider.ERROR);
			}
			
		}
	}
	
	public double getSeasonalPeriod() {
		return seasonalPeriod;
	}
	
	public int getSeasonalsPerTrend() {
		return seasonalsPerTrend;
	}
	
	public String getSeasonalShape() {
		return seasonalShape;
	}

	public String getTrendShape() {
		return trendShape;
	}

	public String getOperatorLiteral() {
		return operatorLiteral;
	}
	
	@Override
	protected Point getInitialSize() {
		//return new Point(340,600);
		return super.getInitialSize();
	}

	public boolean isExtractNoise() {
		return extractNoise;
	}
}
