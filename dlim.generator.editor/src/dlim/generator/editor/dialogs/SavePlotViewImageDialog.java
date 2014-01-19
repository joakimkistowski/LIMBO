package dlim.generator.editor.dialogs;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This dialog takes the user parameters for the saving of the plotview
 * into an image file.
 * @author Jóakim G. v. Kistowski
 *
 */
public class SavePlotViewImageDialog extends TitleAreaDialog {

	private Text widthText;

	private Text imageFilePathText;
	private Text heightText;
	
	private String imageFilePath = "";
	private static String lastFunctioningImageFilePath = "";
	private int height = 400;
	private int width = 800;
	private boolean canceled = false;
	
	/**
	 * Creates a new dialog.
	 * @param fileString
	 * @param projectPath
	 * @param parentShell
	 */
	public SavePlotViewImageDialog(Shell parentShell) {
		super(parentShell);
	}
	
	/**
	 * Set titles.
	 */
	public void create() {
		super.create();
		setTitle("Save Plot as PNG");
	}

	/**
	 * Create GUI elements.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogContainer = (Composite) super.createDialogArea(parent);
		Composite columnContainer = new Composite(dialogContainer, SWT.NONE);
		columnContainer.setLayout(new GridLayout(1,false));
		createSizeParameterField(columnContainer);
		createImageFilePathField(columnContainer);
		

		return dialogContainer;
	}
	
	//file path UI
	private void createImageFilePathField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(3,false);
		gridContainer.setLayout(layout);
		Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
		parameterFieldLabel.setText("Image Path: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 300;
		imageFilePathText = new Text(gridContainer, SWT.BORDER);
		imageFilePathText.setText(lastFunctioningImageFilePath);
		imageFilePathText.setLayoutData(parameterFieldData);
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
				FileDialog dialog = new FileDialog(getParentShell(),SWT.SAVE);
				String[] filterNames = {"PNG","All Files"};
				String[] filterExtensions = new String [] {"*.png", "*.*"};
				dialog.setFilterNames(filterNames);
				dialog.setFilterExtensions(filterExtensions);
				dialog.setText("Save Plot as Image");
				String newPath = dialog.open();
				if (newPath != null && !newPath.isEmpty()) {
					imageFilePathText.setText(newPath);
				}
			}
		});
	}
	
	//random generator seed UI
	private void createSizeParameterField(Composite container) {
		Composite gridContainer = new Composite(container, SWT.NONE);
		GridLayout layout = new GridLayout(4,false);
		gridContainer.setLayout(layout);
		{
			Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
			parameterFieldLabel.setText("Width: ");
			GridData parameterFieldData = new GridData();
			parameterFieldData.grabExcessHorizontalSpace = false;
			parameterFieldData.horizontalAlignment = SWT.BEGINNING;
			parameterFieldData.widthHint = 40;
			widthText = new Text(gridContainer, SWT.BORDER);
			widthText.setText(String.valueOf(width));
			widthText.setLayoutData(parameterFieldData);
		}
		{
			Label parameterFieldLabel = new Label(gridContainer, SWT.NONE);
			parameterFieldLabel.setText(" Height: ");
			GridData parameterFieldData = new GridData();
			parameterFieldData.grabExcessHorizontalSpace = false;
			parameterFieldData.horizontalAlignment = SWT.BEGINNING;
			parameterFieldData.widthHint = 40;
			heightText = new Text(gridContainer, SWT.BORDER);
			heightText.setText(String.valueOf(height));
			heightText.setLayoutData(parameterFieldData);
		}
	}
	
	/**
	 * Dialog window title.
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Save Plot");
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
	 * The file path of the PNG image.
	 * @return
	 */
	public String getImageFilePath() {
		return imageFilePath;
	}
	
	/**
	 * The image height.
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * The image width.
	 * @return
	 */
	public int getWidth() {
		return width;
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
			width = Integer.parseInt(widthText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Width must be an Integer.", IMessageProvider.ERROR);
			error = true;
		}

		try {
			height = Integer.parseInt(heightText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Height must be an Integer.", IMessageProvider.ERROR);
			error = true;
		}
		
		
		imageFilePath = imageFilePathText.getText().trim();
		
		IPath filePath = new Path(imageFilePath);
		if (!filePath.isValidPath(imageFilePath)) {
			setMessage("The image path is not valid.", IMessageProvider.ERROR);
			error = true;
		}
		
		if (!error && (filePath.getFileExtension() == null || !filePath.getFileExtension().equals("png"))) {
			setMessage("The image must be a png.", IMessageProvider.ERROR);
			error = true;
		}
		
		
		if (!error) {
			lastFunctioningImageFilePath = imageFilePath;
			imageFilePath = filePath.toString();
			super.okPressed();
		}
	}
	
	@Override
	protected Point getInitialSize() {
		return super.getInitialSize();
	}
}
