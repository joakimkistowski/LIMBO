/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor;

import java.util.List;

import org.eclipse.core.runtime.Status;

import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Default implementation of the IDlimExtractor interface for the Extractor
 * extension point. Use this when testing new arrival rate file readers.
 *
 * @author Joakim von Kistowski
 */
public class SimpleExtractor implements IDlimExtractor {

	/**
	 * Extracts the read arrival rate list into a DLIM Sequence. Seasonal period
	 * and Trend length are pre-set.
	 */
	@Override
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> readArrivalRates) {
		try {
			ModelExtractor.extractArrivalRateFileIntoSequenceBinarySplits(root,
					readArrivalRates, 24, 2, "SinTrend", "SinTrend", "MULT",
					false);
		} catch (CalibrationException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID,
							"Extration Parameter Exception: " + e.getMessage(), e));
		}

	}

}
