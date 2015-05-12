/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski and Andreas Weber
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter.utils;

import java.io.PrintWriter;

/**
 * Creates a time-stamp file from an arrival rate list. Timestamps are sampled
 * with equal distance.
 *
 * @author Joakim von Kistowski, Andreas Weber
 *
 */
public class EqualDistanceTimestampWriter extends TimeStampWriter {

	/**
	 * Instantiates a new equal distance timestamp writer.
	 */
	public EqualDistanceTimestampWriter() {
		super();
	}

	/**
	 * Instantiates a new equal distance timestamp writer.
	 *
	 * @param endOfLineCharacter            The character before the end of a line in the output file.
	 *            Note: the "\n" is always printed after this character. It does
	 *            not have to be included here.
	 */
	public EqualDistanceTimestampWriter(String endOfLineCharacter) {
		super(endOfLineCharacter);
	}

	/**
	 * @see tools.descartes.dlim.exporter.utils.TimeStampWriter#writeTimestampsForArrivalRate
	 * (java.io.PrintWriter, double, double, double, double)
	 */
	@Override
	protected void writeTimestampsForArrivalRate(PrintWriter writer,
			double step, double arrRate, double tmpStep, double tmpTime) {
		int loops = (int) (arrRate * tmpStep);
		for (double j = 0; j < loops; j++) {
			double d = tmpTime + (j / loops) * tmpStep
					- tmpStep / 2.0;
			writer.println(formatDoubleForDecimalPlaces(d) + getEndOfLineCharacter());
		}
	}
}
