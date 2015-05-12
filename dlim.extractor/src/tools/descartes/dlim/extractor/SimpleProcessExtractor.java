/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.extractor.dialogs.LaunchExtractionDialog;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Extracts a DLIM instance based on the Simple Extraction Model.
 *
 * @author Joakim von Kistowski
 */
public class SimpleProcessExtractor implements IDlimExtractor {

	/**
	 * Extracts a DLIM instance based on the Simple Extraction Model.
	 *
	 * @param root the root
	 * @param readArrivalRates the read arrival rates
	 */
	@Override
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> readArrivalRates) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();
		LaunchExtractionDialog dialog = new LaunchExtractionDialog(shell, root,
				readArrivalRates);
		dialog.open();

		/*
		 * if (!dialog.wasCanceled()) { try {
		 * ModelExtractor.extractArrivalRateFileIntoSequence(root,
		 * readArrivalRates,dialog.getSeasonalPeriod(),
		 * dialog.getSeasonalsPerTrend
		 * (),dialog.getSeasonalShape(),dialog.getTrendShape(),
		 * dialog.getOperatorLiteral(), dialog.isExtractNoise()); } catch
		 * (ExtractionParameterException e) { MessageDialog.openError( shell,
		 * "Extraction Error", "Extraction Error: " + e.getMessage()); } }
		 */
	}

}
