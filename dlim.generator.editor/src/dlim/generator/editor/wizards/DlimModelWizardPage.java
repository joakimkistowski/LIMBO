package dlim.generator.editor.wizards;

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

import dlim.DlimPackage;
import dlim.Sequence;
import dlim.extractor.HLDlimParameterContainer;
import dlim.generator.editor.views.PlotCanvas;

/**
 * Abstract parent class of all DLIM instance creation wizard pages.
 * Features a plot canvas for visualization of the current DLIM instance.
 * @author Jóakim G. v. Kistowski
 */
public abstract class DlimModelWizardPage extends WizardPage {

	protected PlotCanvas dlimPlotter;
	protected Sequence rootSequence;
	
	protected DlimModelWizardPage(String pageName, Sequence rootSequence) {
		super(pageName);
		this.rootSequence = rootSequence;
	}

	/**
	 * Fills the control area. Provides a plot canvas for DLIM instance visualization.
	 * The interactive area must be filled by children implementations.
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		GridLayout compositeLayout = new GridLayout(1,true);
		composite.setLayout(compositeLayout);
		{
			GridData data = new GridData();
			data.widthHint = parent.getSize().y;
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
			composite.setLayoutData(data);
		}
		Composite interactiveArea = new Composite(composite,SWT.NONE);
		interactiveArea.setLayout(new GridLayout(1,true));
		{
			GridData data = new GridData();
			//data.heightHint = 400;
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = false;
			data.grabExcessVerticalSpace = false;
			interactiveArea.setLayoutData(data);
		}
		
		Composite plotterComposite = new Composite(composite,SWT.NONE);
		plotterComposite.setLayout(new GridLayout(1,true));
		{
			GridData data = new GridData();
			data.heightHint = 200;
			data.widthHint = composite.getSize().y;
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
			plotterComposite.setLayoutData(data);
		}
		
		dlimPlotter = new PlotCanvas(plotterComposite, SWT.NONE, true);
		dlimPlotter.setRootSequence(rootSequence);
		dlimPlotter.setRightMargin(10);
		dlimPlotter.setDrawLegend(false);
		{
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
			dlimPlotter.setLayoutData(data);
		}
		
		fillInteractiveArea(interactiveArea);
		
		setPageComplete(validatePage());
		setControl(composite);
	}
	
	/**
	 * Override this for parameter validation. It is automatically called by the validation
	 * listener.
	 * @return
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
	 * Add this listener to any text area. It will trigger the validatePage() method.
	 * @param text
	 */
	protected void addValidationListener(Text text) {
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
			
		});
	}
	
	/**
	 * Add this listener to any combo. It will trigger the validatePage() method.
	 * @param combo
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
	 * @return
	 */
	protected Collection<String> getInitialTrendNames() {
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
	 * Fills all GUI text elements with their respective HLDLIM values.
	 * @param container
	 */
	public void fillPageWithHLDlimParameters(HLDlimParameterContainer container) {
		parseParameters(container);
		setPageComplete(validatePage());
	}
	
	/**
	 * Fill the interactive area of the wizard page here.
	 * @param interactiveArea
	 */
	protected abstract void fillInteractiveArea(Composite interactiveArea);

	/**
	 * Set all GUI elements using the provided HLDLIM paramters.
	 * @param container
	 */
	protected abstract void parseParameters(HLDlimParameterContainer container);
	
	protected CustomDlimModelWizard getDlimModelWizard() {
		if (getWizard() instanceof CustomDlimModelWizard) {
			return (CustomDlimModelWizard)getWizard();
		}
		return null;
	}
	
	/**
	 * Gets the next wizard page.
	 */
	@Override
	public IWizardPage getNextPage(){
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
