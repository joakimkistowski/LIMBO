package dlim.exporter.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This dialog promts for user parameters for request time-stamp generation.
 * @author Jóakim G. v. Kistowski
 *
 */
public class RequestTimeStampParametersDialog extends TitleAreaDialog {

	private Text decimalPlacesText;
	private Text stepText;
	private Text rndSeedText;
	private Text stretchText;
	private Text arDevisorText;
	
	private int decimalPlaces = 3;
	private int rndSeed = 5;
	private double step = 1.0;
	private double stretch = 1.0;
	private double arDevisor = 1.0;
	private boolean canceled = false;
	
	private String fileString;
	
	/**
	 * Create a new Dialog.
	 * @param fileString The path of the model file.
	 * @param parentShell
	 */
	public RequestTimeStampParametersDialog(String fileString, Shell parentShell) {
		super(parentShell);
		this.fileString = fileString;
	}
	
	private void setDefaultValues() {
		decimalPlaces = 3;
		rndSeed = 5;
		step = 1.0;
		stretch = 1.0;
		arDevisor = 1.0;
		canceled = false;
	}
	
	/**
	 * Sets titles.
	 */
	public void create() {
		super.create();
		setTitle("Request Time Stamp Generation Parameters");
		setMessage("For Model: " + fileString, IMessageProvider.INFORMATION);
	}

	/**
	 * Creates the GUI elements.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new FillLayout(SWT.VERTICAL));
		createDecimalPlaceParameterField(columnContainer);
		createStepParameterField(columnContainer);
		createRndSeedParameterField(columnContainer);
		Label delimiterLabel = new Label(columnContainer,SWT.NONE);
		delimiterLabel.setText("Time Stamp Modifiers");
		delimiterLabel.setAlignment(SWT.CENTER);
		createStretchParameterField(columnContainer);
		createArDevisorParameterField(columnContainer);
		return dialogContainer;
	}
	
	private void createDecimalPlaceParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Maximum Decimal Places: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 16;
		decimalPlacesText = new Text(gridContainer, SWT.BORDER);
		decimalPlacesText.setText("3");
		decimalPlacesText.setLayoutData(parameterFieldData);
	}
	
	private void createRndSeedParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Random Generator Seed: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 16;
		rndSeedText = new Text(gridContainer, SWT.BORDER);
		rndSeedText.setText("5");
		rndSeedText.setLayoutData(parameterFieldData);
	}
	
	private void createStepParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Sampling Interval Width: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 20;
		stepText = new Text(gridContainer, SWT.BORDER);
		stepText.setText("1.0");
		stepText.setLayoutData(parameterFieldData);
	}
	
	private void createStretchParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Stretch Model Times by: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 20;
		stretchText = new Text(gridContainer, SWT.BORDER);
		stretchText.setText("1.0");
		stretchText.setLayoutData(parameterFieldData);
	}
	
	private void createArDevisorParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Divide Model Arrival Rates by: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 24;
		arDevisorText = new Text(gridContainer, SWT.BORDER);
		arDevisorText.setText("1.0");
		arDevisorText.setLayoutData(parameterFieldData);
	}
	
	/**
	 * Dialog window label.
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Request Time Stamp Generation Parameters");
	}
	
	/**
	 * Cancel button was pressed.
	 */
	@Override
	protected void cancelPressed() {
		setDefaultValues();
		canceled = true;
		super.cancelPressed();
	}
	
	/**
	 * Returns true if user canceled the dialog.
	 * @return
	 */
	public boolean wasCanceled() {
		return canceled;
	}
	
	/**
	 * Ok Button was pressed.
	 * Parses parameters from the UI. Displays errors to the user.
	 */
	@Override
	protected void okPressed() {
		boolean error = false;
		try {
			decimalPlaces = Integer.parseInt(decimalPlacesText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Decimal Places must be an Integer.", IMessageProvider.ERROR);
			error = true;
		}
		try {
			step = Double.parseDouble(stepText.getText().trim());
			
		} catch (NumberFormatException e) {
			setMessage("Sampling Interval Width must be a number.", IMessageProvider.ERROR);
			error = true;
		}
		try {
			rndSeed = Integer.parseInt(rndSeedText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Random Seed must be an Integer.", IMessageProvider.ERROR);
			error = true;
		}
		try {
			stretch = Double.parseDouble(stretchText.getText().trim());
			
		} catch (NumberFormatException e) {
			setMessage("Time stretch factor must be a number.", IMessageProvider.ERROR);
			error = true;
		}
		try {
			arDevisor = Double.parseDouble(arDevisorText.getText().trim());
			
		} catch (NumberFormatException e) {
			setMessage("Arrival Rate Devisor must be a number.", IMessageProvider.ERROR);
			error = true;
		}
		if (arDevisor <= 0) {
			error = true;
			setMessage("Arrival Rate Devisor must greater than 0.", IMessageProvider.ERROR);
		}
		if (stretch <= 0) {
			error = true;
			setMessage("Time stretch factor must greater than 0.", IMessageProvider.ERROR);
		}
		if (step <= 0) {
			error = true;
			setMessage("Sampling Interval Width must greater than 0.", IMessageProvider.ERROR);
		}
		if (decimalPlaces <= 0) {
			error = true;
			setMessage("Decimal Places must greater than 0.", IMessageProvider.ERROR);
		}
		
		
		if (!error) {
			super.okPressed();
		}
	}
	
	/**
	 * Get the random number generator seed.
	 * @return
	 */
	public int getRndSeed() {
		return rndSeed;
	}
	
	/**
	 * Get the number of allowed time-stamp decimal places.
	 * @return
	 */
	public int getDecimalPlaces() {
		return decimalPlaces;
	}
	
	/**
	 * Get the sampling interval width.
	 * @return
	 */
	public double getStep() {
		return step;
	}
	
	/**
	 * Get the time stretch factor.
	 * @return
	 */
	public double getStretch() {
		return stretch;
	}

	/**
	 * Get the devisor for arrival rates for time-stamp generation.
	 * @return
	 */
	public double getArDevisor() {
		return arDevisor;
	}
	
	@Override
	protected Point getInitialSize() {
		//return new Point(340,600);
		return super.getInitialSize();
	}
}
