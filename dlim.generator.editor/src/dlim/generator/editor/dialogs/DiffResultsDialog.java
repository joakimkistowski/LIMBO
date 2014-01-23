package dlim.generator.editor.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This dialog displays the results of the difference Calculation between a model
 * instance and an arrival rate txt file.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DiffResultsDialog extends TitleAreaDialog {


	private double mean = 0.0;
	private double median = 0.0;
	private double dtwDist = 0.0;
	private double meanPercent = 0.0;
	private double medianPercent = 0.0;

	
	
	/**
	 * Creates a new dialog.
	 * @param fileString
	 * @param projectPath
	 * @param parentShell
	 */
	public DiffResultsDialog(Shell parentShell, double mean, double median, double dtwDist, double meanPercent, double medianPercent) {
		super(parentShell);
		this.mean = mean;
		this.median = median;
		this.dtwDist = dtwDist;
		this.meanPercent = meanPercent;
		this.medianPercent = medianPercent;
	}
	
	/**
	 * Set titles.
	 */
	public void create() {
		super.create();
		setTitle("Difference Calculation Results");
	}

	/**
	 * Create GUI elements.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite gridContainer = new Composite(dialogContainer, SWT.NONE);
		GridLayout layout = new GridLayout(5,false);
		gridContainer.setLayout(layout);
		createValueDisplayField(gridContainer, "Mean Difference: ", "" + mean, "" + meanPercent*100);
		createValueDisplayField(gridContainer, "Median Difference: ", "" + median, "" + medianPercent*100);
		createValueDisplayField(gridContainer, "Normalized Curve Difference (based on DTW): ",
				"" + dtwDist, null);

		return dialogContainer;
	}
	
	private void createValueDisplayField(Composite container, String labelString, String textString, String percentString) {
		Label parameterFieldLabel = new Label(container, SWT.NONE);
		parameterFieldLabel.setText(labelString);
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 120;
		Text outputText = new Text(container, SWT.BORDER);
		outputText.setText(textString);
		outputText.setLayoutData(parameterFieldData);
		outputText.setEditable(false);
		GridData percentFieldData = new GridData();
		percentFieldData.grabExcessHorizontalSpace = false;
		percentFieldData.horizontalAlignment = SWT.BEGINNING;
		percentFieldData.widthHint = 110;
		if (percentString != null) {
			Label commaLabel = new Label(container, SWT.NONE);
			commaLabel.setText(",");
			Text percentText = new Text(container, SWT.BORDER);
			percentText.setText(percentString);
			percentText.setLayoutData(percentFieldData);
			percentText.setEditable(false);
			Label percentLabel = new Label(container, SWT.NONE);
			percentLabel.setText("%");
		} else {
			for (int i = 0; i < 3; i++) {
				Label spaceLabel = new Label(container, SWT.NONE);
				spaceLabel.setText(" ");
			}
		}
		
	}
	
	/**
	 * Dialog window title.
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Calculate Difference");
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK button, do not create a cancel button
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}
}
