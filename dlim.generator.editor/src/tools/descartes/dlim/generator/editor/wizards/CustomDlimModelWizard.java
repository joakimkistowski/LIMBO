/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.presentation.DlimEditorPlugin;
import tools.descartes.dlim.presentation.DlimModelWizard;

/**
 * This custom wizard helps with the creation of new DLIM instances.
 *
 * @author Joakim von Kistowski
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

	/**
	 * Instantiates a new custom dlim model wizard.
	 */
	public CustomDlimModelWizard() {
		super();
		rootObject = dlimFactory.createSequence();
		rootObject.setName("");
	}

	/**
	 * Populate the model here.
	 *
	 * @return the e object
	 */
	@Override
	protected EObject createInitialModel() {
		// Sequence rootObject = dlimFactory.createSequence();
		rootObject.setName(getModelName());
		return rootObject;
	}

	// reads the file name of the new DLIM file
	private String getModelName() {
		String[] names = newFileCreationPage.getFileName().split("\\.");
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
		newFileCreationPage = new DlimModelWizardNewFileCreationPage(
				"Whatever", selection);
		newFileCreationPage.setTitle(DlimEditorPlugin.INSTANCE
				.getString("_UI_DlimModelWizard_label"));
		newFileCreationPage.setDescription(DlimEditorPlugin.INSTANCE
				.getString("_UI_DlimModelWizard_description"));
		newFileCreationPage.setFileName(DlimEditorPlugin.INSTANCE
				.getString("_UI_DlimEditorFilenameDefaultBase")
				+ "."
				+ FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);

		// Try and get the resource selection to determine a current directory
		// for the file dialog.
		//
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			//
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				//
				IResource selectedResource = (IResource) selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}

				// This gives us a directory...
				//
				if (selectedResource instanceof IFolder
						|| selectedResource instanceof IProject) {
					// Set this for the container.
					//
					newFileCreationPage.setContainerFullPath(selectedResource
							.getFullPath());

					// Make up a unique new name here.
					//
					String defaultModelBaseFilename = DlimEditorPlugin.INSTANCE
							.getString("_UI_DlimEditorFilenameDefaultBase");
					String defaultModelFilenameExtension = FILE_EXTENSIONS
							.get(0);
					String modelFilename = defaultModelBaseFilename + "."
							+ defaultModelFilenameExtension;
					for (int i = 1; ((IContainer) selectedResource)
							.findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "."
								+ defaultModelFilenameExtension;
					}
					newFileCreationPage.setFileName(modelFilename);
				}
			}
		}
		initialObjectCreationPage = new DlimModelWizardNullInitialObjectCreationPage(
				"Whatever2");
		initialObjectCreationPage.setTitle(DlimEditorPlugin.INSTANCE
				.getString("_UI_DlimModelWizard_label"));
		initialObjectCreationPage.setDescription(DlimEditorPlugin.INSTANCE
				.getString("_UI_Wizard_initial_object_description"));
		// addPage(initialObjectCreationPage);

		choicePage = new DlimPageChoiceModelWizardPage("choice page");
		choicePage.setTitle("Initial Model Parameters");
		choicePage
		.setDescription("Choose the Initial Model Parameters to be set.");
		addPage(choicePage);

		readPage = new DlimReadFileModelWizardPage("read page", rootObject);
		readPage.setTitle("Extract Parameters");
		readPage.setDescription("Extract Model Parameters from an existing Trace (Optional)");
		addPage(readPage);

		seasonalPage = new DlimSeasonalModelWizardPage("seasonalPage",
				rootObject);
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

	/**
	 * Gets the choice page.
	 *
	 * @return the choice page
	 */
	public DlimPageChoiceModelWizardPage getChoicePage() {
		return choicePage;
	}

	/**
	 * Gets the read page.
	 *
	 * @return the read page
	 */
	public DlimReadFileModelWizardPage getReadPage() {
		return readPage;
	}

	/**
	 * Gets the seasonal page.
	 *
	 * @return the seasonal page
	 */
	public DlimSeasonalModelWizardPage getSeasonalPage() {
		return seasonalPage;
	}

	/**
	 * Gets the trend page.
	 *
	 * @return the trend page
	 */
	public DlimTrendModelWizardPage getTrendPage() {
		return trendPage;
	}

	/**
	 * Gets the burst page.
	 *
	 * @return the burst page
	 */
	public DlimBurstModelWizardPage getBurstPage() {
		return burstPage;
	}

	/**
	 * Checks if is show read page.
	 *
	 * @return true, if is show read page
	 */
	public boolean isShowReadPage() {
		return showReadPage;
	}

	/**
	 * Sets the show read page.
	 *
	 * @param showReadPage the new show read page
	 */
	public void setShowReadPage(boolean showReadPage) {
		this.showReadPage = showReadPage;
	}

	/**
	 * Checks if is show seasonal page.
	 *
	 * @return true, if is show seasonal page
	 */
	public boolean isShowSeasonalPage() {
		return showSeasonalPage;
	}

	/**
	 * Sets the show seasonal page.
	 *
	 * @param showSeasonalPage the new show seasonal page
	 */
	public void setShowSeasonalPage(boolean showSeasonalPage) {
		this.showSeasonalPage = showSeasonalPage;
	}

	/**
	 * Checks if is show trend page.
	 *
	 * @return true, if is show trend page
	 */
	public boolean isShowTrendPage() {
		return showTrendPage;
	}

	/**
	 * Sets the show trend page.
	 *
	 * @param showTrendPage the new show trend page
	 */
	public void setShowTrendPage(boolean showTrendPage) {
		this.showTrendPage = showTrendPage;
	}

	/**
	 * Checks if is show burst page.
	 *
	 * @return true, if is show burst page
	 */
	public boolean isShowBurstPage() {
		return showBurstPage;
	}

	/**
	 * Sets the show burst page.
	 *
	 * @param showBurstPage the new show burst page
	 */
	public void setShowBurstPage(boolean showBurstPage) {
		this.showBurstPage = showBurstPage;
	}

	/**
	 * I don't need the InitialObjectCreationPage, since the root element of a
	 * DLIM is always a Sequence.
	 *
	 * @author Joakim von Kistowski
	 *
	 */
	private class DlimModelWizardNullInitialObjectCreationPage extends
	DlimModelWizardInitialObjectCreationPage {

		public DlimModelWizardNullInitialObjectCreationPage(String pageId) {
			super(pageId);
		}

		@Override
		public String getEncoding() {
			return "UTF-8";
		}
	}
}
