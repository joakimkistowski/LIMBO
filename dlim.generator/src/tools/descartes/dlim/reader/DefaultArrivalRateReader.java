/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.reader;

import java.io.IOException;
import java.util.List;

import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Default arrival rate reader. Reads the default arrival rate format as
 * produced by the dlim.exporter plugin.
 * 
 * @author Joakim von Kistowski
 */
public class DefaultArrivalRateReader implements IDlimArrivalRateReader {

	/**
	 * Reads an arrival rate trace into a arrival rate tuple list. Starts
	 * reading at the given time offset.
	 */
	@Override
	public List<ArrivalRateTuple> readFileToList(String filePath, double offset)
			throws IOException {
		return ArrivalRateReader.readFileToList(filePath, offset);
	}

}
