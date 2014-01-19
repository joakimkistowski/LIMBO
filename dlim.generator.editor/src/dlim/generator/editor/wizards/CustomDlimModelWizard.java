package dlim.generator.editor.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;

import dlim.Sequence;
import dlim.presentation.DlimEditorPlugin;
import dlim.presentation.DlimModelWizard;

/**
 * This custom wizard helps with the creation of new DLIM instances.
 * @author Jóakim G. v. Kistowski
 *
 */
public class CustomDlimModelWizard extends DlimModelWizard {

	private boolean showReadPage = false;
	private boolean showSeasonalPage = true;
	private boolean showTrendPage = true;
	private boolean showBurstPage = true;
	
	private DlimReadFileModelWizardPage readPage;
	private DlimSeasonalModelWizardPage seasonalPage;
	private DlimTrendModelWizardPage trendPage;
	private DlimBurstModelWizardPage burstPage;
	private DlimPageChoiceModelWizardPage choicePage;

	private Sequence rootObject;
	
	public CustomDlimModelWizard() {
		super();
		rootObject  = dlimFactory.createSequence();
		rootObject.setName("");
	}
	
	/**
	 * Populate the model here.
	 */
	@Override
	protected EObject createInitialModel() {
		//Sequence rootObject = dlimFactory.createSequence();
		rootObject.setName(getModelName());
		return rootObject;
	}
	
	//reads the file name of the new DLIM file
	private String getModelName() {
		String names[] = newFileCreationPage.getFileName().split("\\.");
		String name = "";
		if (names.length > 1) {
			for (int i = 0; i < names.length - 1; i++) {
				name += "." + names[i];
			}
			return name.substring(1);
		} else if (names.length == 1) {
			return names[0].trim();
		} else {
			return newFileCreationPage.getFileName();
		}
		
	}
	
	/**
	 * Adds the wizard's pages.
	 */
	@Override
	public void addPages() {
		// Create a page, set the title, and the initial model file name.
		//
		newFileCreationPage = new DlimModelWizardNewFileCreationPage("Whatever", selection);
		newFileCreationPage.setTitle(DlimEditorPlugin.INSTANCE.getString("_UI_DlimModelWizard_label"));
		newFileCreationPage.setDescription(DlimEditorPlugin.INSTANCE.getString("_UI_DlimModelWizard_description"));
		newFileCreationPage.setFileName(DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);

		// Try and get the resource selection to determine a current directory for the file dialog.
		//
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			//
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				//
				IResource selectedResource = (IResource)selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}

				// This gives us a directory...
				//
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					//
					newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					//
					String defaultModelBaseFilename = DlimEditorPlugin.INSTANCE.getString("_UI_DlimEditorFilenameDefaultBase");
					String defaultModelFilenameExtension = FILE_EXTENSIONS.get(0);
					String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
					for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
					}
					newFileCreationPage.setFileName(modelFilename);
				}
			}
		}
		initialObjectCreationPage = new DlimModelWizardNullInitialObjectCreationPage("Whatever2");
		initialObjectCreationPage.setTitle(DlimEditorPlugin.INSTANCE.getString("_UI_DlimModelWizard_label"));
		initialObjectCreationPage.setDescription(DlimEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
		//addPage(initialObjectCreationPage);
		
		choicePage = new DlimPageChoiceModelWizardPage("choice page");
		choicePage.setTitle("Initial Model Parameters");
		choicePage.setDescription("Choose the Initial Model Parameters to be set.");
		addPage(choicePage);
		
		readPage = new DlimReadFileModelWizardPage("read page", rootObject);
		readPage.setTitle("Extract Parameters");
		readPage.setDescription("Extract Model Parameters from an existing Trace (Optional)");
		addPage(readPage);
		
		seasonalPage = new DlimSeasonalModelWizardPage("seasonalPage", rootObject);
		seasonalPage.setTitle("Seasonal Pattern");
		seasonalPage.setDescription("Choose a seasonal pattern for the model");
		addPage(seasonalPage);
		
		trendPage = new DlimTrendModelWizardPage("trendPage", rootObject);
		trendPage.setTitle("Overarching Trends");
		trendPage.setDescription("Choose the overarching trends for the model");
		addPage(trendPage);
		
		burstPage = new DlimBurstModelWizardPage("burstPage", rootObject);
		burstPage.setTitle("Recurring Bursts and Noise");
		burstPage.setDescription("Set bursts and noise for the model");
		addPage(burstPage);
	}
	
	public DlimPageChoiceModelWizardPage getChoicePage() {
		return choicePage;
	}
	
	public DlimReadFileModelWizardPage getReadPage() {
		return readPage;
	}

	public DlimSeasonalModelWizardPage getSeasonalPage() {
		return seasonalPage;
	}

	public DlimTrendModelWizardPage getTrendPage() {
		return trendPage;
	}

	public DlimBurstModelWizardPage getBurstPage() {
		return burstPage;
	}
	
	public boolean isShowReadPage() {
		return showReadPage;
	}

	public void setShowReadPage(boolean showReadPage) {
		this.showReadPage = showReadPage;
	}

	public boolean isShowSeasonalPage() {
		return showSeasonalPage;
	}

	public void setShowSeasonalPage(boolean showSeasonalPage) {
		this.showSeasonalPage = showSeasonalPage;
	}

	public boolean isShowTrendPage() {
		return showTrendPage;
	}

	public void setShowTrendPage(boolean showTrendPage) {
		this.showTrendPage = showTrendPage;
	}

	public boolean isShowBurstPage() {
		return showBurstPage;
	}

	public void setShowBurstPage(boolean showBurstPage) {
		this.showBurstPage = showBurstPage;
	}
	
	/**
	 * I don't need the InitialObjectCreationPage, since the root element of a DLIM is always a Sequence.
	 * @author Jóakim G. v. Kistowski
	 *
	 */
	private class DlimModelWizardNullInitialObjectCreationPage extends DlimModelWizardInitialObjectCreationPage {

		public DlimModelWizardNullInitialObjectCreationPage(String pageId) {
			super(pageId);
		}
		
		@Override
		public String getEncoding() {
			return "UTF-8";
		}
	}
}
