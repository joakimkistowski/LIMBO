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
 * This Interface must be implemented for the Extractor extension point. Reads
 * an arrival rate trace file into an in memory list. Implement this for parsing
 * an unsupported format. Use dlim.reader.DefaultArrivalRateReader for the
 * default format as used by dlim.extractor and dlim.exporter plugins.
 *
 * @author Joakim von Kistowski
 */
public interface IDlimArrivalRateReader {

	/**
	 * Reads an arrival rate file into an in-memory arrival rate tuple list.
	 *
	 * @param filePath
	 *            The file's path.
	 * @param offset
	 *            The time offset at which to start reading
	 * @return ordered list of arrival rate tuples
	 * @throws IOException Exception on missing or unreadable files
	 */
	public List<ArrivalRateTuple> readFileToList(String filePath, double offset)
			throws IOException;
}
