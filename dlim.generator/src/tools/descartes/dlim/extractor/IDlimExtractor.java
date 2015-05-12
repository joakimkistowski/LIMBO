/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.extractor;

import java.util.List;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * This Interface must be implemented for the Extractor extension point.
 * 
 * @author Joakim von Kistowski
 */
public interface IDlimExtractor {

	/**
	 * Extract a Sequence from the List of read arrival rates.
	 * 
	 * @param root
	 *            The model's root Sequence.
	 * @param readArrivalRates
	 *            a list of arrival rates with their respective time-stamps.
	 */
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> readArrivalRates);
}
