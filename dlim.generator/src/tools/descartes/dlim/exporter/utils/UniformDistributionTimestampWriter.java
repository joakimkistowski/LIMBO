/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski and Andreas Weber
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter.utils;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.random.JDKRandomGenerator;

import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Creates a time-stamp file from an arrival rate list. Timestamps are sampled
 * using a uniform distribution
 *
 * @author Joakim von Kistowski, Andreas Weber
 *
 */
public class UniformDistributionTimestampWriter extends TimeStampWriter {

	// The random number generator
	private JDKRandomGenerator rndGenerator;

	// Total duration of sequence
	private double duration;

	// The list of time-stamps within each interval.
	private List<String> timeStampList = new ArrayList<String>();

	/**
	 *
	 * @param rndGenerator
	 *            Random generator used for sampling
	 */
	public UniformDistributionTimestampWriter(JDKRandomGenerator rndGenerator) {
		super();
		this.rndGenerator = rndGenerator;
	}

	/**
	 *
	 * @param endOfLineCharacter
	 *            The character before the end of a line in the output file.
	 *            Note: the "\n" is always printed after this character. It does
	 *            not have to be included here.
	 * @param rndGenerator
	 *            Random generator used for sampling
	 */
	public UniformDistributionTimestampWriter(String endOfLineCharacter,
			JDKRandomGenerator rndGenerator) {
		super(endOfLineCharacter);
		this.rndGenerator = rndGenerator;
	}

	/**
	 *
	 * @param file
	 *            The filename of the produced output file
	 * @param arrRates
	 *            The list of arrival rates as provided by the
	 *            ArrivalRateGenerator.
	 * @param decimalPlaces
	 *            The amount of decimal places a time-stamp is allowed to have.
	 * @param stretch
	 *            The factor by which to stretch the times of the arrival rate
	 *            tuples. Using a value < 1 compresses the time.
	 * @param arDevisor
	 *            Divide the arrival rates from the arrival rate tuples by this
	 *            to produce less time-stamps. Using a value < 1 produces more
	 *            time stamps
	 * @param duration
	 *            Duration of root sequence
	 */
	public void generateTimeStampsFromArrivalRates(File file,
			List<ArrivalRateTuple> arrRates, int decimalPlaces, double stretch,
			double arDevisor, double duration) {
		this.duration = duration;

		generateTimeStampsFromArrivalRates(file, arrRates, decimalPlaces,
				stretch, arDevisor);
	}

	// Adds the time-stamp to the list of time-stamps for this arrival rate
	// tuple
	private void addDoubleValueToList(double d) {
		// clamp value
		if (d > duration * getStretch()) {
			d = duration * getStretch();
		} else if (d < 0.0) {
			d = 0.0;
		}
		timeStampList.add(formatDoubleForDecimalPlaces(d));
	}

	// Print the time-stamp list to the .txt file
	// Don't forget to sort first.
	private void printList(PrintWriter writer) {
		Collections.sort(timeStampList);
		for (String timeStamp : timeStampList) {
			writer.println(timeStamp + getEndOfLineCharacter());
		}
	}

	@Override
	protected void writeTimestampsForArrivalRate(PrintWriter writer,
			double step, double arrRate, double tmpStep, double tmpTime) {
		timeStampList.clear();
		for (double j = 0; j < (int) (arrRate * tmpStep); j++) {
			double d = tmpTime
					+ (rndGenerator.nextDouble() - 0.5) * (step * getStretch());
			addDoubleValueToList(d);
		}
		printList(writer);
	}
}
