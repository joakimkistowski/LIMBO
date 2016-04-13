/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import tools.descartes.dlim.DlimGeneratorPlugin;

/**
 * Reads a time-stamp series into an arrival rate series.
 *
 * @author Joakim von Kistowski
 *
 */
public final class RequestTimeSeriesReader {

	// The bucket width of the arrival rates.
	// Future work: make this a parameter to be set by the user?
	private static final double STEP = 1.0;

	/**
	 * It's all static anyway.
	 */
	private RequestTimeSeriesReader() {

	}

	/**
	 * Reads a time-stamp series into an arrival rate series. Time stamps must
	 * be sorted. Works directly on the HDD.
	 *
	 * @param path
	 *            The path of the time-stamp series.
	 * @param outputDir
	 *            The directory in which the arrival rate file is to be written.
	 */
	public static void createArrivalRatesFromSortedTimeStamps(String path,
			String outputDir) {
		IPath absPath = new Path(path);
		IPath outPath = new Path(outputDir);
		String fileName = ReadingUtils.getFileName(path);
		outPath = outPath.append(fileName + "ArrivalRates.txt");

		// ArrivalRateChart arrivalRateChart = new
		// ArrivalRateChart("Arrival Rates");

		double bucket = 0.0;
		int arrivalRateCounter = 0;

		try {
			PrintWriter arrRateWriter = new PrintWriter(outPath.toString(),
					"UTF-8");
			BufferedReader br = new BufferedReader(new FileReader(
					absPath.toFile()));
			String line;
			while ((line = br.readLine()) != null) {
				double timeStamp = 0;
				try {
					timeStamp = Double.parseDouble(line);
				} catch (NumberFormatException e) {
					line = line.substring(0, line.length() - 1);
					timeStamp = Double.parseDouble(line);
				}
				while (timeStamp >= bucket + STEP) {
					printCurrentRate(bucket + STEP / 2, arrivalRateCounter,
							arrRateWriter);
					arrivalRateCounter = 0;
					bucket += STEP;
				}
				arrivalRateCounter++;
			}
			printCurrentRate(bucket + STEP / 2, arrivalRateCounter,
					arrRateWriter);
			// arrivalRateChart.printChartFile("time", "arrival rates",
			// outputDir + "/" + fileName + "ArrivalRates.pdf");
			br.close();
			arrRateWriter.close();
		} catch (IOException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
							"Could not write arrival rate file", e));
		}

	}

	/**
	 * Reads a time-stamp series into an arrival rate series. Pre sorts the time
	 * stamps. Amount of parseble time-staps is thus limited by heap size.
	 *
	 * @param path
	 *            The path of the time-stamp series.
	 * @param outputDir
	 *            The directory in which the arrival rate file is to be written.
	 */
	public static void createArrivalRatesFromUnsortedTimeStamps(String path,
			String outputDir) {
		IPath absPath = new Path(path);
		// step 1: make model path absolute
		if (path.startsWith("platform:/resource")) {
			String workspacePath = ResourcesPlugin.getWorkspace().getRoot()
					.getLocation().toString();
			absPath = new Path(workspacePath + path.substring(18));
		}
		String filePath = absPath.toString();
		String fileName = ReadingUtils.getFileName(filePath);

		LinkedList<Double> timeStampList = new LinkedList<Double>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.substring(0, line.length() - 1);
				timeStampList.add(Double.parseDouble(line.trim()));
			}
			br.close();

			Collections.sort(timeStampList);
			writeArrivalRatesFromTimeStamps(timeStampList, outputDir, fileName);

		} catch (IOException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.WARNING, DlimGeneratorPlugin.PLUGIN_ID,
							"Could not write arrival rate file", e));
		}
	}

	/*
	 * Uses the sorted time stamp list. Puts the time-stamps into buckets and
	 * counts them to get the arrival rate.
	 */
	private static void writeArrivalRatesFromTimeStamps(
			LinkedList<Double> timeStampList, String outputDir, String name)
					throws FileNotFoundException, UnsupportedEncodingException {
		double bucket = 0.0;
		int arrivalRateCounter = 0;
		String outputPath = outputDir + "/" + name + "ArrivalRates.txt";
		PrintWriter arrRateWriter = new PrintWriter(outputPath, "UTF-8");

		for (Double timeStamp : timeStampList) {
			while (timeStamp >= bucket + STEP) {
				printCurrentRate(bucket + STEP / 2, arrivalRateCounter,
						arrRateWriter);
				arrivalRateCounter = 0;
				bucket += STEP;
			}
			arrivalRateCounter++;
		}
		printCurrentRate(bucket + STEP / 2, arrivalRateCounter, arrRateWriter);
		arrRateWriter.close();
	}

	// print current arrival rate to txt and chart
	private static void printCurrentRate(double time, double arrRate,
			PrintWriter writer) {
		writer.println(time + "," + arrRate + ";");
	}

}
