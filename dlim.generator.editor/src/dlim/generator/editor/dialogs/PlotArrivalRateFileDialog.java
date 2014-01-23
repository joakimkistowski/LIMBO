package dlim.generator.editor.dialogs;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import dlim.generator.ArrivalRateTuple;
import dlim.reader.ArrivalRateReader;

/**
 * This dialog takes user parameters for the inclusion
 * of an arrival rate trace within the plotview.
 * @author Jóakim G. v. Kistowski
 *
 */
public class PlotArrivalRateFileDialog extends TitleAreaDialog {
	
	public PlotArrivalRateFileDialog(Shell parentShell) {
		super(parentShell);
	}

	//parameter input fields
	private Text txtFilePathText;
	private Text offsetText;
	
	private double offset = 0.0;
	private static String txtFilePath = "";
	private boolean canceled = false;
	private List<ArrivalRateTuple> arrivalRateList;

	/**
	 * Set dialog titles.
	 */
	public void create() {
		super.create();
		setTitle("Plot Arrival Rate File for comparison.");
	}

	/**
	 * Set up GUI elements.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new FillLayout(SWT.VERTICAL));
		createOffsetParameterField(columnContainer);
		createTxtFilePathField(columnContainer);

		return dialogContainer;
	}
	
	//offset of model start within the arrival rate file
		private void createOffsetParameterField(Composite container) {
			Composite gridContainer = new Composite(container, SWT.NONE);
			GridLayout layout = new GridLayout(2,false);
			gridContainer.setLayout(layout);
			Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
			parameterFieldLabel.setText("Model start offset within arrival rate file: ");
			GridData parameterFieldData = new GridData();
			parameterFieldData.grabExcessHorizontalSpace = false;
			parameterFieldData.horizontalAlignment = SWT.BEGINNING;
			parameterFieldData.widthHint = 40;
			offsetText = new Text(gridContainer, SWT.BORDER);
			offsetText.setText(String.valueOf(offset));
			offsetText.setLayoutData(parameterFieldData);
		}
	
	//file path UI
		private void createTxtFilePathField(Composite container) {
			Composite gridContainer = new Composite(container, SWT.NONE);
			GridLayout layout = new GridLayout(3,false);
			gridContainer.setLayout(layout);
			Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
			parameterFieldLabel.setText("Arrival Rate File: ");
			GridData parameterFieldData = new GridData();
			parameterFieldData.grabExcessHorizontalSpace = false;
			parameterFieldData.horizontalAlignment = SWT.BEGINNING;
			parameterFieldData.widthHint = 300;
			txtFilePathText = new Text(gridContainer, SWT.BORDER);
			txtFilePathText.setText(txtFilePath);
			txtFilePathText.setLayoutData(parameterFieldData);
			Button fileDialogButton = new Button(gridContainer,SWT.PUSH);
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
					FileDialog dialog = new FileDialog(getParentShell(),SWT.OPEN);
					String[] filterNames = {"Arrival Rate files","All Files"};
					String[] filterExtensions = new String [] {"*.csv;*.txt", "*.*"};
					dialog.setFilterNames(filterNames);
					dialog.setFilterExtensions(filterExtensions);
					dialog.setText("Select Arrival Rate File");
					String newPath = dialog.open();
					if (newPath != null && !newPath.isEmpty()) {
						txtFilePathText.setText(newPath);
					}
				}
			});
		}

	
	/**
	 * Dialog window label.
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Plot Arrival Rate File");
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
	 * @return
	 */
	public boolean wasCanceled() {
		return canceled;
	}
	
	/**
	 * Sets the newValue, depending on user input, read values and possible calibration errors.
	 */
	@Override
	protected void okPressed() {
		boolean error = false;
		
		try {
			offset = Double.parseDouble(offsetText.getText().trim());
			if (offset < 0) {
				setMessage("Offset must not be negative.", IMessageProvider.ERROR);
				error = true;
			}
		} catch (NumberFormatException e) {
			setMessage("Offset must be a number.", IMessageProvider.ERROR);
			error = true;
		}
		
		String tmpFilePath = txtFilePathText.getText().trim();
		
		IPath filePath = new Path(tmpFilePath);
			
			
			//read value from txt file
			try {
				if (!error) {
					arrivalRateList = ArrivalRateReader.readFileToList(filePath.toString(), offset);
				}
			} catch (IOException e) {
				setMessage("Error reading file. Does it exist?", IMessageProvider.ERROR);
				error = true;
			}
		
		
		//success
		if (!error) {
			//store last used file
			txtFilePath = tmpFilePath;
			super.okPressed();
		}
	}
	
	
	public List<ArrivalRateTuple> getArrivalRateList() {
		return arrivalRateList;
	}
}
