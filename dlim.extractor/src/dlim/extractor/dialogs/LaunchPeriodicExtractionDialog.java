package dlim.extractor.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import dlim.DlimPackage;
import dlim.Operator;
import dlim.Sequence;
import dlim.assistant.CalibrationException;
import dlim.extractor.ModelExtractor;
import dlim.generator.ArrivalRateTuple;

/**
 * This dialog takes the user parameters for the periodic extraction process.
 * @author Jóakim G. v. Kistowski
 *
 */
public class LaunchPeriodicExtractionDialog extends TitleAreaDialog {

	private Text seasonalPeriodText;

	private List seasonalsPerTrendList;
	private Text seasonalsPerTrend1Text;
	private Text seasonalsPerTrend2Text;
	private Button addToListButton;
	private Button extractNoiseButton;
	
	private Combo seasonalShapeCombo;
	private Combo trendShapeCombo;
	private Combo operatorCombo;
	
	private boolean seasonalsPerTrend1Valid = false;
	private boolean seasonalsPerTrend2Valid = false;
	
	private double seasonalPeriod = 24.0;
	private ArrayList<int[]> seasonalsPerTrendContainer = new ArrayList<int[]>();
	private String seasonalShape = "SinTrend";
	private String trendShape = "SinTrend";
	private String operatorLiteral = "MULT";
	private boolean extractNoise = false;
	private boolean canceled = false;
	
	private Sequence rootSequence;
	private java.util.List<ArrivalRateTuple> readArrivalRates;

	
	/**
	 * Creates a new dialog.
	 * @param fileString
	 * @param projectPath
	 * @param parentShell
	 */
	public LaunchPeriodicExtractionDialog(Shell parentShell,
			Sequence rootSequence, java.util.List<ArrivalRateTuple> readArrivalRates) {
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
		columnContainer.setLayout(new GridLayout(1,false));
		Composite gridComposite = new Composite(columnContainer, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		gridComposite.setLayout(gridLayout);
		createSeasonalPeriodParameterField(gridComposite);
		createExtractNoiseCheckBox(gridComposite);
		Composite trendListComposite = new Composite(columnContainer,SWT.NONE);
		trendListComposite.setLayout(new GridLayout(1,false));
		createTrendList(trendListComposite);
		createListControlField(trendListComposite);
		
		
		Composite formSelectionComposite = new Composite(columnContainer, SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(2,false);
		formSelectionComposite.setLayout(formSelectionLayout);
		createSeasonalShapeSelectionField(formSelectionComposite);
		createTrendShapeSelectionField(formSelectionComposite);
		createOperatorSelectionField(formSelectionComposite);
		

		return dialogContainer;
	}
	
	private void createTrendList(Composite parent) {
		GridData parentData = new GridData();
		parentData.grabExcessHorizontalSpace = true;
		parentData.grabExcessVerticalSpace = true;
		parentData.horizontalAlignment = GridData.FILL;
		parentData.verticalAlignment = GridData.FILL;
		parentData.heightHint = 160;
		parent.setLayoutData(parentData);
		
		Label fieldLabel = new Label(parent,SWT.NONE);
		fieldLabel.setText("Piece-wise Trend per Seasonals: ");
		fieldLabel.setAlignment(SWT.LEFT);
		seasonalsPerTrendList = new List(parent, SWT.BORDER);
		
		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.grabExcessVerticalSpace = true;
		textData.horizontalAlignment = GridData.FILL;
		textData.verticalAlignment = GridData.FILL;
		
		seasonalsPerTrendList.setLayoutData(textData);
		
		seasonalsPerTrendList.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					seasonalsPerTrendContainer.remove(seasonalsPerTrendList.getSelectionIndex());
					seasonalsPerTrendList.remove(seasonalsPerTrendList.getSelectionIndex());
				}
			}
		});
	}
	
	private void createListControlField(Composite container) {
		Composite parent = new Composite(container,SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 2;
		parent.setLayout(gridLayout);
		
		Label fieldLabel = new Label(parent,SWT.NONE);
		fieldLabel.setText("Add Trend with following piece-wise durations: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		{
			seasonalsPerTrend1Text = new Text(parent,SWT.BORDER);
			seasonalsPerTrend1Text.setText("");
			GridData textData = new GridData();
			textData.grabExcessHorizontalSpace = false;
			textData.horizontalAlignment = SWT.BEGINNING;
			textData.widthHint = 70;
			seasonalsPerTrend1Text.setLayoutData(textData);
		}
		{
			seasonalsPerTrend2Text = new Text(parent,SWT.BORDER);
			seasonalsPerTrend2Text.setText("");
			GridData textData = new GridData();
			textData.grabExcessHorizontalSpace = false;
			textData.horizontalAlignment = SWT.BEGINNING;
			textData.widthHint = 70;
			seasonalsPerTrend2Text.setLayoutData(textData);
		}
		
		addToListButton = new Button(parent,SWT.PUSH);
		addToListButton.setText("Add");
		addToListButton.setEnabled(false);
		
		seasonalsPerTrend1Text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					Integer.parseInt(seasonalsPerTrend1Text.getText().trim());
					seasonalsPerTrend1Valid = true;
					if (seasonalsPerTrend1Valid && seasonalsPerTrend2Valid) {
						addToListButton.setEnabled(true);
					}
				} catch (NumberFormatException exception) {
					seasonalsPerTrend1Valid = false;
					addToListButton.setEnabled(false);
				}
			}
		});
		
		seasonalsPerTrend2Text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					Integer.parseInt(seasonalsPerTrend2Text.getText().trim());
					seasonalsPerTrend2Valid = true;
					if (seasonalsPerTrend1Valid && seasonalsPerTrend2Valid) {
						addToListButton.setEnabled(true);
					}
				} catch (NumberFormatException exception) {
					seasonalsPerTrend2Valid = false;
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
				int[] seasonalsPerTrend = new int[2];
				seasonalsPerTrend[0] = Integer.parseInt(seasonalsPerTrend1Text.getText().trim());
				seasonalsPerTrend[1] = Integer.parseInt(seasonalsPerTrend2Text.getText().trim());
				seasonalsPerTrendContainer.add(seasonalsPerTrend);
				seasonalsPerTrendList.add(" " + seasonalsPerTrend[0] + " , " + 
						seasonalsPerTrend[1]);
				seasonalsPerTrend1Text.setText("");
				seasonalsPerTrend2Text.setText("");
				seasonalsPerTrend1Valid = false;
				seasonalsPerTrend2Valid = false;
				addToListButton.setEnabled(false);
			}
		});
	}
	
	//period of seasonal part
	private void createSeasonalPeriodParameterField(Composite container) {
		Label parameterFieldLabel = new Label(container, SWT.NONE);
		parameterFieldLabel.setText("    Seasonal Period: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 40;
		seasonalPeriodText = new Text(container, SWT.BORDER);
		seasonalPeriodText.setText(String.valueOf(seasonalPeriod));
		seasonalPeriodText.setLayoutData(parameterFieldData);
	}
	
	private void createExtractNoiseCheckBox(Composite container) {
		Label fieldLabel = new Label(container ,SWT.NONE);
		fieldLabel.setText("          ");
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
		
		extractNoise = extractNoiseButton.getSelection();
		seasonalShape = seasonalShapeCombo.getText().trim();
		trendShape = trendShapeCombo.getText().trim();
		operatorLiteral = operatorCombo.getText().trim();
		
		
		if (!error) {
			try {
				ModelExtractor.extractSequenceFromArrivalRateFilePeriodic(rootSequence,
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
	
	public ArrayList<int[]> getSeasonalsPerTrend() {
		return seasonalsPerTrendContainer;
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
