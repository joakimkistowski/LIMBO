/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.wizards;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * This page offers a choice of which HLDLIM parts the user wants to edit.
 * Respectively only the selected pages are shown.
 *
 * @author Joakim von Kistowski
 */
public class DlimPageChoiceModelWizardPage extends WizardPage {

	/**
	 * Instantiates a new dlim page choice model wizard page.
	 *
	 * @param pageName the page name
	 */
	protected DlimPageChoiceModelWizardPage(String pageName) {
		super(pageName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout compositeLayout = new GridLayout(1, true);
		compositeLayout.marginHeight = 4;
		composite.setLayout(compositeLayout);
		GridData data = new GridData();
		data.widthHint = parent.getSize().y;
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.verticalAlignment = GridData.FILL;
		data.grabExcessVerticalSpace = true;
		data.verticalIndent = 10;
		composite.setLayoutData(data);

		final Button readPageButton = new Button(composite, SWT.CHECK);
		readPageButton.setText("Extract Model Parameters from Trace");
		readPageButton.setSelection(getDlimModelWizard().isShowReadPage());
		readPageButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showReadPage(readPageButton.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				showReadPage(readPageButton.getSelection());
			}
		});

		final Button seasonalPageButton = new Button(composite, SWT.CHECK);
		seasonalPageButton.setText("Modify Seasonal Part");
		seasonalPageButton.setSelection(getDlimModelWizard()
				.isShowSeasonalPage());
		seasonalPageButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showSeasonalPage(seasonalPageButton.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				showSeasonalPage(seasonalPageButton.getSelection());
			}
		});

		final Button trendPageButton = new Button(composite, SWT.CHECK);
		trendPageButton.setText("Modify Trend Part");
		trendPageButton.setSelection(getDlimModelWizard().isShowTrendPage());
		trendPageButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showTrendPage(trendPageButton.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				showTrendPage(trendPageButton.getSelection());
			}
		});

		final Button burstPageButton = new Button(composite, SWT.CHECK);
		burstPageButton.setText("Modify Recurring Bursts and Noise");
		burstPageButton.setSelection(getDlimModelWizard().isShowBurstPage());
		burstPageButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showBurstPage(burstPageButton.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				showBurstPage(burstPageButton.getSelection());
			}
		});

		setPageComplete(true);
		setControl(composite);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	@Override
	public IWizardPage getNextPage() {
		CustomDlimModelWizard wizard = getDlimModelWizard();
		if (wizard.isShowReadPage()) {
			return wizard.getReadPage();
		}
		if (wizard.isShowSeasonalPage()) {
			return wizard.getSeasonalPage();
		} else if (wizard.isShowTrendPage()) {
			return wizard.getTrendPage();
		} else if (wizard.isShowBurstPage()) {
			return wizard.getBurstPage();
		}
		return null;
	}

	private CustomDlimModelWizard getDlimModelWizard() {
		if (getWizard() instanceof CustomDlimModelWizard) {
			return (CustomDlimModelWizard) getWizard();
		}
		return null;
	}

	private void showReadPage(boolean show) {
		getDlimModelWizard().setShowReadPage(show);
	}

	private void showSeasonalPage(boolean show) {
		getDlimModelWizard().setShowSeasonalPage(show);
	}

	private void showTrendPage(boolean show) {
		getDlimModelWizard().setShowTrendPage(show);
	}

	private void showBurstPage(boolean show) {
		getDlimModelWizard().setShowBurstPage(show);
	}

}
