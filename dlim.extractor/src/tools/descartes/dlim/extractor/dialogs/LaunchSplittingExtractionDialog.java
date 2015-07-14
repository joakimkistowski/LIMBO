/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.dialogs;

import java.util.List;

import org.eclipse.swt.widgets.Shell;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.extractor.ModelExtractor;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Dialog for launching the extraction process using binary seasonal splits and regressive trends.
 * 
 * @author Joakim von Kistowski
 *
 */
public class LaunchSplittingExtractionDialog extends LaunchExtractionDialog {

	/**
	 * Creates a new dialog.
	 *
	 * @param parentShell the parent shell
	 * @param rootSequence the root sequence
	 * @param readArrivalRates the read arrival rates
	 */
	public LaunchSplittingExtractionDialog(Shell parentShell,
			Sequence rootSequence, List<ArrivalRateTuple> readArrivalRates) {
		super(parentShell, rootSequence, readArrivalRates);
	}
	
	/**
	 * Set titles.
	 */
	public void create() {
		super.create();
		setTitle("Extract Sequence from Arrival Rate File using Binary Splits");
	}

	@Override
	protected void performExtraction(Sequence root,
			List<ArrivalRateTuple> arrList, double period,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral, boolean extractNoise) throws CalibrationException {
		ModelExtractor
		.extractArrivalRateFileIntoSequenceBinarySplits(root,
				arrList, getSeasonalPeriod(),
				getSeasonalsPerTrend(), getSeasonalShape(),
				getTrendShape(), getOperatorLiteral(),
				isExtractNoise());
	}
	
}
