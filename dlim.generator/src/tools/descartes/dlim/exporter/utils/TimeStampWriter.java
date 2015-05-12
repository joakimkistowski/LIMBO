/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski and Andreas Weber
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.eclipse.core.runtime.Status;

import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.util.MathUtil;

/**
 * Creates a time-stamp file from an arrival rate list. Abstract class.
 * Subclasses must implement the
 * {@link #writeTimestampsForArrivalRate
 * (PrintWriter writer, double step, double arrRate, double tmpStep, double tmpTime)
 * writeTimestampsForArrivalRate} method to define the exact sampling of
 * timestamps
 *
 * @author Joakim von Kistowski, Andreas Weber
 *
 */
public abstract class TimeStampWriter {

	/**
	 * End of line character for the .csv.
	 */
	private String endOfLineCharacter = ";";

	/**
	 * Amount of allowed decimal places.
	 */
	private int decimalplaces = 3;


	/**
	 * Time stretch factor.
	 */
	private double stretch = 1.0;

	/**
	 * Gets the end of line character for the .csv.
	 *
	 * @return the end of line character
	 */
	protected String getEndOfLineCharacter() {
		return endOfLineCharacter;
	}

	/**
	 * Gets the decimal places.
	 *
	 * @return the decimal places
	 */
	protected int getDecimalplaces() {
		return decimalplaces;
	}

	/**
	 * Formats the double to only contain decimalplaces decimals after the period.
	 * @param d The double to format.
	 * @return The formatted double as a String.
	 */
	protected String formatDoubleForDecimalPlaces(double d) {
		return MathUtil.formatDoubleForDecimalPlaces(d, decimalplaces);
	}

	/**
	 * Gets the time stretch factor.
	 *
	 * @return the stretch
	 */
	protected double getStretch() {
		return stretch;
	}

	/**
	 * Instantiates a new time stamp writer.
	 *
	 * @param endOfLineCharacter            The character before the end of a line in the output file.
	 *            Note: the "\n" is always printed after this character. It does
	 *            not have to be included here.
	 */
	protected TimeStampWriter(String endOfLineCharacter) {
		this.endOfLineCharacter = endOfLineCharacter;
	}

	/**
	 * The constructor.
	 */
	protected TimeStampWriter() {
	}

	/**
	 * Generate time stamps from arrival rates.
	 *
	 * @param file            The filename of the produced output file
	 * @param arrRates            The list of arrival rates as provided by the
	 *            ArrivalRateGenerator.
	 * @param decimalPlaces            The amount of decimal places a time-stamp is allowed to have.
	 * @param stretch            The factor by which to stretch the times of the arrival rate
	 *            tuples. Using a value < 1 compresses the time.
	 * @param arDevisor            Divide the arrival rates from the arrival rate tuples by this
	 *            to produce less time-stamps. Using a value < 1 produces more
	 *            time stamps
	 */
	public void generateTimeStampsFromArrivalRates(File file,
			List<ArrivalRateTuple> arrRates, int decimalPlaces, double stretch,
			double arDevisor) {
		this.stretch = stretch;
		this.decimalplaces = decimalPlaces;
		try {
			PrintWriter timeStampWriter = new PrintWriter(file, "UTF-8");
			ArrivalRateTuple lastTupel = null;
			for (ArrivalRateTuple tuple : arrRates) {
				double step = tuple.getStep(lastTupel);
				double arrRate = tuple.getArrivalRate() / arDevisor;
				double time = tuple.getTimeStamp();

				double tmpStep = step * stretch;
				double tmpTime = time * stretch;
				writeTimestampsForArrivalRate(timeStampWriter, step, arrRate,
						tmpStep, tmpTime);
				lastTupel = tuple;
			}
			timeStampWriter.close();
		} catch (FileNotFoundException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
							"FileNotFound, Could not write time stamps.", e));
		} catch (UnsupportedEncodingException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
							"UnsupportedEncoding, Could not write time stamps.", e));
		}
	}

	/**
	 * Write the arrival rate using the provided writer in the specific chosen format.
	 * @param writer PrintWriter, is already initialized and writing.
	 * @param step step between two time stamps
	 * @param arrRate current arrival rate
	 * @param tmpStep the temporary step
	 * @param tmpTime the temporary time
	 */
	protected abstract void writeTimestampsForArrivalRate(PrintWriter writer,
			double step, double arrRate, double tmpStep, double tmpTime);

}
