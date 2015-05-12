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
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tools.descartes.dlim.Burst;
import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.UniformNoise;
import tools.descartes.dlim.extractor.HLDlimParameterContainer;

/**
 * The wizard page for the burst and noise parts as defined by the HLDLIM.
 *
 * @author Joakim von Kistowski
 */
public class DlimBurstModelWizardPage extends DlimModelWizardPage {

	private Text offsetText;
	private Text periodText;
	private Text peakText;
	private Text widthText;

	private Text noiseMinText;
	private Text noiseMaxText;

	private Combo shapeCombo;
	private Button toggleDecompositionButton;

	private double offset = 36.0;
	private double period = 48.0;
	private double peak = 0.0;
	private double width = 0.0;

	private double noiseMin = 0.0;
	private double noiseMax = 0.0;

	private Combinator burstCombinator;
	private Combinator noiseCombinator;

	/**
	 * Instantiates a new dlim burst model wizard page.
	 *
	 * @param pageName the page name
	 * @param rootSequence the root sequence
	 */
	protected DlimBurstModelWizardPage(String pageName, Sequence rootSequence) {
		super(pageName, rootSequence);
	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#fillInteractiveArea
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void fillInteractiveArea(Composite interactiveArea) {
		GridData parentData = new GridData();
		parentData.horizontalAlignment = GridData.FILL;
		interactiveArea.setLayoutData(parentData);

		createDelimiterLabel("    Recurring Bursts:", interactiveArea);
		Composite parent = new Composite(interactiveArea, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		parent.setLayout(gridLayout);
		createBurstOffsetField(parent);
		createBurstPeriodField(parent);
		createBurstPeakField(parent);
		createBurstWidthField(parent);

		Composite formSelectionComposite = new Composite(interactiveArea,
				SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(2, false);
		formSelectionLayout.marginWidth = 5;
		formSelectionLayout.marginHeight = 5;
		formSelectionLayout.verticalSpacing = 0;
		formSelectionLayout.horizontalSpacing = 0;
		formSelectionComposite.setLayout(formSelectionLayout);
		createFormSelectionField(formSelectionComposite);

		createDelimiterLabel(" ", interactiveArea);
		createDelimiterLabel("    Noise:", interactiveArea);
		Composite noise = new Composite(interactiveArea, SWT.NONE);
		GridLayout noiseLayout = new GridLayout(4, false);
		noiseLayout.marginWidth = 5;
		noiseLayout.marginHeight = 5;
		noiseLayout.verticalSpacing = 2;
		noiseLayout.horizontalSpacing = 0;
		noise.setLayout(gridLayout);
		createNoiseMinField(noise);
		createNoiseMaxField(noise);
		Label noiseHint = new Label(interactiveArea, SWT.NONE);
		noiseHint
		.setText("    Other Noises (such as Gaussian Noise) can easily be added in the EMF editor.");

		Composite toggleDecompositionComposite = new Composite(interactiveArea,
				SWT.NONE);
		GridLayout decompositionLayout = new GridLayout(1, false);
		decompositionLayout.marginWidth = 5;
		decompositionLayout.marginHeight = 5;
		decompositionLayout.verticalSpacing = 0;
		decompositionLayout.horizontalSpacing = 0;
		toggleDecompositionComposite.setLayout(decompositionLayout);
		createDecompositionButton(toggleDecompositionComposite);
	}

	private void createDelimiterLabel(String text, Composite parent) {
		Label delimiterLabel = new Label(parent, SWT.NONE);
		delimiterLabel.setText(text);
		delimiterLabel.setAlignment(SWT.RIGHT);
		delimiterLabel.setFont(JFaceResources.getFontRegistry().getBold(""));
	}

	private void createBurstOffsetField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("First Burst Offset: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		offsetText = new Text(parent, SWT.BORDER);
		offsetText.setText("" + offset);
		addValidationListener(offsetText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		offsetText.setLayoutData(textData);
	}

	private void createBurstPeriodField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Inter Burst Period: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		periodText = new Text(parent, SWT.BORDER);
		periodText.setText("" + period);
		addValidationListener(periodText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		periodText.setLayoutData(textData);
	}

	private void createBurstPeakField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Burst Peak Arrival Rate: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		peakText = new Text(parent, SWT.BORDER);
		peakText.setText("" + peak);
		addValidationListener(peakText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		peakText.setLayoutData(textData);
	}

	private void createBurstWidthField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Burst Width: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		widthText = new Text(parent, SWT.BORDER);
		widthText.setText("" + width);
		addValidationListener(widthText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		widthText.setLayoutData(textData);
	}

	private void createFormSelectionField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Select Burst Shape: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		shapeCombo = new Combo(parent, SWT.BORDER);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = true;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 220;

		shapeCombo.setLayoutData(textData);

		// populate shapeCombo
		for (String name : getInitiaBurstNames()) {
			shapeCombo.add(name);
		}
		shapeCombo.select(0);
		addValidationListener(shapeCombo);
	}

	private Collection<String> getInitiaBurstNames() {
		ArrayList<String> initialObjectNames = new ArrayList<String>();
		for (EClassifier eClassifier : DlimPackage.eINSTANCE.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				if (!eClass.isAbstract()
						&& DlimPackage.eINSTANCE.getBurst().isSuperTypeOf(
								eClass)) {
					initialObjectNames.add(eClass.getName());
				}
			}
		}
		Collections.sort(initialObjectNames,
				CommonPlugin.INSTANCE.getComparator());
		return initialObjectNames;
	}

	private void createNoiseMinField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("Minimum Noise Arrival Rate: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		noiseMinText = new Text(parent, SWT.BORDER);
		noiseMinText.setText("" + noiseMin);
		addValidationListener(noiseMinText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		noiseMinText.setLayoutData(textData);
	}

	private void createNoiseMaxField(Composite parent) {
		Label fieldLabel = new Label(parent, SWT.NONE);
		fieldLabel.setText("    Maximum Noise Arrival Rate: ");
		fieldLabel.setAlignment(SWT.RIGHT);
		noiseMaxText = new Text(parent, SWT.BORDER);
		noiseMaxText.setText("" + noiseMax);
		addValidationListener(noiseMaxText);

		GridData textData = new GridData();
		textData.grabExcessHorizontalSpace = false;
		textData.horizontalAlignment = SWT.BEGINNING;
		textData.widthHint = 40;

		noiseMaxText.setLayoutData(textData);
	}

	private void createDecompositionButton(Composite parent) {
		GridData parentData = new GridData();
		parentData.grabExcessHorizontalSpace = true;
		parentData.grabExcessVerticalSpace = true;
		parentData.horizontalAlignment = GridData.FILL;
		parentData.verticalAlignment = GridData.FILL;
		parent.setLayoutData(parentData);

		toggleDecompositionButton = new Button(parent, SWT.CHECK);
		toggleDecompositionButton
		.setText("Explicitly show Trend, Burst, and Noise Contributions in Plot");
		toggleDecompositionButton.setSelection(true);
		toggleDecompositionButton.setLayoutData(new GridData(SWT.RIGHT,
				SWT.TOP, true, true, 1, 1));

		getDlimPlotter().setDecompositionMode(true);
		toggleDecompositionButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getDlimPlotter().setDecompositionMode(toggleDecompositionButton
						.getSelection());
				validatePage();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				getDlimPlotter().setDecompositionMode(toggleDecompositionButton
						.getSelection());
				validatePage();
			}
		});
	}

	/* (non-Javadoc)
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#validatePage()
	 */
	@Override
	protected boolean validatePage() {
		try {
			period = Double.parseDouble(periodText.getText().trim());
			if (period <= 0) {
				setMessage("Inter Burst Period must be > 0.",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Inter Burst Period must be a number.",
					IMessageProvider.ERROR);
			return false;
		}

		try {
			offset = Double.parseDouble(offsetText.getText().trim());
			if (offset <= 0) {
				setMessage("First Burst Offset must be > 0.",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("First Burst Offset must be a number.",
					IMessageProvider.ERROR);
			return false;
		}

		try {
			peak = Double.parseDouble(peakText.getText().trim());
			if (peak < 0) {
				setMessage("Maximum Burst Value must be >= 0.",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Maximum Burst Value be a number.",
					IMessageProvider.ERROR);
			return false;
		}

		try {
			width = Double.parseDouble(widthText.getText().trim());
			if (width < 0) {
				setMessage("Burst Width must be >= 0.", IMessageProvider.ERROR);
				return false;
			} else if (width != 0.0 && width > period) {
				setMessage(
						"Burst Width must not be greater than the Inter Burst Period.",
						IMessageProvider.ERROR);
				return false;
			} else if (width != 0.0 && width > offset * 2.0) {
				setMessage("Burst Width must not be greater than 2*Offset.",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Maximum Burst Value be a number.",
					IMessageProvider.ERROR);
			return false;
		}
		try {
			noiseMin = Double.parseDouble(noiseMinText.getText().trim());
		} catch (NumberFormatException e) {
			setMessage("Noise Minimum Value must be a number.",
					IMessageProvider.ERROR);
			return false;
		}
		try {
			noiseMax = Double.parseDouble(noiseMaxText.getText().trim());
			if (noiseMax < noiseMin) {
				setMessage(
						"Noise Maximum Value must be greater than Noise Minimum Value.",
						IMessageProvider.ERROR);
				return false;
			}
		} catch (NumberFormatException e) {
			setMessage("Noise Maximum Value must be a number.",
					IMessageProvider.ERROR);
			return false;
		}

		setMessage(getDescription());
		populateModel();
		updatePlot();
		return true;
	}

	private void populateModel() {
		// bursts
		getRootSequence().getCombine().remove(burstCombinator);
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		try {
			if (width > 0 && peak > 0) {
				burstCombinator = factory.createCombinator();
				Sequence burstContainerSequence = factory.createSequence();
				burstContainerSequence.setName("burstContainer");
				burstContainerSequence.setTerminateAfterTime(getRootSequence()
						.getFinalDuration());
				burstCombinator.setFunction(burstContainerSequence);
				Sequence burstSequence;
				if (offset - width / 2.0 == 0.0) {
					burstSequence = burstContainerSequence;
				} else {
					burstSequence = factory.createSequence();
					TimeDependentFunctionContainer offsetElement = factory
							.createTimeDependentFunctionContainer();
					offsetElement.setName("burstOffset");
					offsetElement.setDuration(offset - width / 2.0);
					burstContainerSequence.getSequenceFunctionContainers().add(
							offsetElement);
					TimeDependentFunctionContainer burstElement = factory
							.createTimeDependentFunctionContainer();
					burstElement.setName("bursts");
					burstElement.setDuration(getRootSequence().getFinalDuration()
							- (offset - width / 2.0));
					burstContainerSequence.getSequenceFunctionContainers().add(
							burstElement);
					burstSequence.setTerminateAfterTime(burstElement
							.getDuration());
					burstElement.setFunction(burstSequence);
				}
				burstSequence.setName("bursts");
				TimeDependentFunctionContainer burstElement = factory
						.createTimeDependentFunctionContainer();
				burstElement.setName("burst");
				burstElement.setDuration(width);
				Burst burst = (Burst) factory
						.create((EClass) (DlimPackage.eINSTANCE
								.getEClassifier(shapeCombo.getText().trim())));
				burst.setBase(0.0);
				burst.setPeakTime(width / 2.0);
				burst.setPeak(peak);
				burstElement.setFunction(burst);
				burstSequence.getSequenceFunctionContainers().add(burstElement);
				TimeDependentFunctionContainer waitElement = factory
						.createTimeDependentFunctionContainer();
				waitElement.setName("wait");
				waitElement.setDuration(period - width);
				burstSequence.getSequenceFunctionContainers().add(waitElement);

				getRootSequence().getCombine().add(burstCombinator);
			}
		} catch (NullPointerException e) {
			// do nothing
		}

		// noise
		getRootSequence().getCombine().remove(noiseCombinator);
		if (noiseMin != 0.0 || noiseMax != 0.0) {
			noiseCombinator = factory.createCombinator();
			UniformNoise noise = factory.createUniformNoise();
			noise.setMin(noiseMin);
			noise.setMax(noiseMax);
			noiseCombinator.setFunction(noise);

			getRootSequence().getCombine().add(noiseCombinator);
		}

	}

	/**
	 * @see tools.descartes.dlim.generator.editor.wizards.DlimModelWizardPage#parseParameters
	 * (tools.descartes.dlim.extractor.HLDlimParameterContainer)
	 */
	@Override
	protected void parseParameters(HLDlimParameterContainer container) {
		offsetText.setText("" + container.getBurstOffset());
		periodText.setText("" + container.getBurstPeriod());
		peakText.setText("" + container.getBurstPeak());
		widthText.setText("" + container.getBurstWidth());
		noiseMinText.setText("" + container.getNoiseMin());
		noiseMaxText.setText("" + container.getNoiseMax());

		offset = container.getBurstOffset();
		period = container.getBurstPeriod();
		peak = container.getBurstPeak();
		width = container.getBurstWidth();
		noiseMin = container.getNoiseMin();
		noiseMax = container.getNoiseMax();
	}
}
