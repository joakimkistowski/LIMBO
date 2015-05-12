/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.extractor.HLDlimParameterContainer;
import tools.descartes.dlim.generator.editor.views.PlotCanvas;

/**
 * Abstract parent class of all DLIM instance creation wizard pages. Features a
 * plot canvas for visualization of the current DLIM instance.
 *
 * @author Joakim von Kistowski
 */
public abstract class DlimModelWizardPage extends WizardPage {

	/** The dlim plotter. */
	private PlotCanvas dlimPlotter;

	/** The root sequence. */
	private Sequence rootSequence;

	/**
	 * Instantiates a new dlim model wizard page.
	 *
	 * @param pageName the page name
	 * @param rootSequence the root sequence
	 */
	protected DlimModelWizardPage(String pageName, Sequence rootSequence) {
		super(pageName);
		this.rootSequence = rootSequence;
	}

	/**
	 * Gets the root sequence.
	 *
	 * @return the root sequence
	 */
	protected Sequence getRootSequence() {
		return rootSequence;
	}

	/**
	 * Gets the dlim plotter.
	 *
	 * @return the dlim plotter
	 */
	protected PlotCanvas getDlimPlotter() {
		return dlimPlotter;
	}

	/**
	 * Fills the control area. Provides a plot canvas for DLIM instance
	 * visualization. The interactive area must be filled by children
	 * implementations.
	 *
	 * @param parent the parent
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout compositeLayout = new GridLayout(1, true);
		composite.setLayout(compositeLayout);
		GridData data1 = new GridData();
		data1.widthHint = parent.getSize().y;
		data1.horizontalAlignment = GridData.FILL;
		data1.grabExcessHorizontalSpace = true;
		data1.verticalAlignment = GridData.FILL;
		data1.grabExcessVerticalSpace = true;
		composite.setLayoutData(data1);

		Composite interactiveArea = new Composite(composite, SWT.NONE);
		interactiveArea.setLayout(new GridLayout(1, true));
		GridData data2 = new GridData();
		// data2.heightHint = 400;
		data2.horizontalAlignment = GridData.FILL;
		data2.grabExcessHorizontalSpace = false;
		data2.grabExcessVerticalSpace = false;
		interactiveArea.setLayoutData(data2);

		Composite plotterComposite = new Composite(composite, SWT.NONE);
		plotterComposite.setLayout(new GridLayout(1, true));
		GridData data3 = new GridData();
		data3.heightHint = 200;
		data3.widthHint = composite.getSize().y;
		data3.horizontalAlignment = GridData.FILL;
		data3.grabExcessHorizontalSpace = true;
		data3.verticalAlignment = GridData.FILL;
		data3.grabExcessVerticalSpace = true;
		plotterComposite.setLayoutData(data3);

		dlimPlotter = new PlotCanvas(plotterComposite, SWT.NONE, true);
		dlimPlotter.setRootSequence(rootSequence);
		dlimPlotter.setRightMargin(10);
		dlimPlotter.setDrawLegend(false);

		GridData data4 = new GridData();
		data4.horizontalAlignment = GridData.FILL;
		data4.grabExcessHorizontalSpace = true;
		data4.verticalAlignment = GridData.FILL;
		data4.grabExcessVerticalSpace = true;
		dlimPlotter.setLayoutData(data4);

		fillInteractiveArea(interactiveArea);

		setPageComplete(validatePage());
		setControl(composite);
	}

	/**
	 * Override this for parameter validation. It is automatically called by the
	 * validation listener.
	 *
	 * @return true, if successful
	 */
	protected boolean validatePage() {
		return true;
	}

	/**
	 * Updates the plot canvas.
	 */
	protected void updatePlot() {
		dlimPlotter.redraw();
	}

	/**
	 * Add this listener to any text area. It will trigger the validatePage()
	 * method.
	 *
	 * @param text the text
	 */
	protected final void addValidationListener(Text text) {
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}

		});
	}

	/**
	 * Add this listener to any combo. It will trigger the validatePage()
	 * method.
	 *
	 * @param combo the combo
	 */
	protected void addValidationListener(Combo combo) {
		combo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}

		});
	}

	/**
	 * Gets all names of implementations of the abstract DLIM Trend.
	 *
	 * @return the initial trend names
	 */
	protected Collection<String> getInitialTrendNames() {
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
	 * Fills all GUI text elements with their respective HLDLIM values.
	 *
	 * @param container the container
	 */
	public void fillPageWithHLDlimParameters(HLDlimParameterContainer container) {
		parseParameters(container);
		setPageComplete(validatePage());
	}

	/**
	 * Fill the interactive area of the wizard page here.
	 *
	 * @param interactiveArea the interactive area
	 */
	protected abstract void fillInteractiveArea(Composite interactiveArea);

	/**
	 * Set all GUI elements using the provided HLDLIM paramters.
	 *
	 * @param container the container
	 */
	protected abstract void parseParameters(HLDlimParameterContainer container);

	/**
	 * Gets the dlim model wizard.
	 *
	 * @return the dlim model wizard
	 */
	protected CustomDlimModelWizard getDlimModelWizard() {
		if (getWizard() instanceof CustomDlimModelWizard) {
			return (CustomDlimModelWizard) getWizard();
		}
		return null;
	}

	/**
	 * Gets the next wizard page.
	 *
	 * @return the next page
	 */
	@Override
	public IWizardPage getNextPage() {
		CustomDlimModelWizard wizard = getDlimModelWizard();
		if (this instanceof DlimReadFileModelWizardPage) {
			if (wizard.isShowSeasonalPage()) {
				return wizard.getSeasonalPage();
			}
		}
		if (this instanceof DlimReadFileModelWizardPage
				|| this instanceof DlimSeasonalModelWizardPage) {
			if (wizard.isShowTrendPage()) {
				return wizard.getTrendPage();
			}
		}
		if (this instanceof DlimReadFileModelWizardPage
				|| this instanceof DlimSeasonalModelWizardPage
				|| this instanceof DlimTrendModelWizardPage) {
			if (wizard.isShowBurstPage()) {
				return wizard.getBurstPage();
			}
		}
		return null;
	}

	/**
	 * Gets the previous wizard page.
	 *
	 * @return the previous page
	 */
	@Override
	public IWizardPage getPreviousPage() {
		CustomDlimModelWizard wizard = getDlimModelWizard();
		if (this instanceof DlimBurstModelWizardPage) {
			if (wizard.isShowTrendPage()) {
				return wizard.getTrendPage();
			}
		}
		if (this instanceof DlimBurstModelWizardPage
				|| this instanceof DlimTrendModelWizardPage) {
			if (wizard.isShowSeasonalPage()) {
				return wizard.getSeasonalPage();
			}
		}
		if (this instanceof DlimBurstModelWizardPage
				|| this instanceof DlimTrendModelWizardPage
				|| this instanceof DlimSeasonalModelWizardPage) {
			if (wizard.isShowReadPage()) {
				return wizard.getReadPage();
			}
		}
		return wizard.getChoicePage();
	}
}
