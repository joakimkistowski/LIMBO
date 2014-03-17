package dlim.generator.editor.dialogs;

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
 * This dialog has the user select the exporter to use for time-stamp generation.
 * @author Jóakim G. v. Kistowski
 *
 */
public class SelectExporterDialog extends TitleAreaDialog {
	
	//parameter input fields
	private List exporterList;
	
	private String[] labels;
	private int selectedIndex = 0;

	private boolean canceled = false;
	
	public SelectExporterDialog(Shell parentShell, String[] labels) {
		super(parentShell);
		this.labels = labels;
	}
	

	/**
	 * Set dialog titles.
	 */
	public void create() {
		super.create();
		setTitle("Select Time Stamp Exporter");
	}

	/**
	 * Set up GUI elements.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new GridLayout(1,false));
		GridData parentData = new GridData();
		parentData.grabExcessHorizontalSpace = true;
		parentData.grabExcessVerticalSpace = true;
		parentData.horizontalAlignment = GridData.FILL;
		parentData.verticalAlignment = GridData.FILL;
		columnContainer.setLayoutData(parentData);
		
		exporterList = new List(columnContainer,SWT.BORDER);
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
	 * @return
	 */
	public boolean wasCanceled() {
		return canceled;
	}
	
	@Override
	protected void okPressed() {
		boolean error = false;
		
		selectedIndex = exporterList.getSelectionIndex();
		if (selectedIndex < 0) {
			setMessage("Please select an Exporter.", IMessageProvider.ERROR);
			error = true;
		}
		
		//success
		if (!error) {
			super.okPressed();
		}
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(360,400);
		//return super.getInitialSize();
	}
}
