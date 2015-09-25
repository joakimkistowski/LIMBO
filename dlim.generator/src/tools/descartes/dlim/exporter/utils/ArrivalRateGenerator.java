/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Generates an arrival rate file and chart by sampling a Descartes Load
 * Intensity Model using a given ModelEvaluator.
 *
 * @author Joakim von Kistowski
 *
 */
public final class ArrivalRateGenerator {

	/**
	 * It's all static anyways.
	 */
	private ArrivalRateGenerator() {

	}

	/**
	 * Run the ArrivalRateGenerator.
	 *
	 * @param evaluator the evaluator
	 * @param step            The width of the sampling interval.
	 * @return A list of ArrivalRateTuples (Time-stamps and arrival rates)
	 *         sampled from the model.
	 */
	public static List<ArrivalRateTuple> generateArrivalRates(
			ModelEvaluator evaluator, double step) {
		ArrayList<ArrivalRateTuple> arrRates = new ArrayList<ArrivalRateTuple>();
		for (double i = step / 2; i < evaluator.getTerminatingDuration(); i += step) {
			double arrRate = evaluator.getArrivalRateAtTime(i);
			arrRates.add(new ArrivalRateTuple(i, arrRate));
		}
		return arrRates;
	}

	/**
	 * Run the ArrivalRateGenerator and write the results to a .txt file and an
	 * arrival rate plot.
	 *
	 * @param arrRates the arr rates
	 * @param projectPath the project path
	 * @param modelName the model name
	 * @param endOfLineCharacter            The character before the end of a line in the output file.
	 *            Note: the "\n" is always printed after this character. It does
	 *            not have to be included here.
	 * @param fileSuffix            The file suffix ("txt","csv" ...)
	 */
	public static void writeArrivalRates(List<ArrivalRateTuple> arrRates,
			String projectPath, String modelName, String endOfLineCharacter,
			String fileSuffix) {
		try {
			IPath arrivalRateFolderPath = new Path(projectPath)
			.append("arrivalRates");
			File arrivalRateFolder = arrivalRateFolderPath.toFile();
			if (!arrivalRateFolder.exists()) {
				arrivalRateFolder.mkdir();
			}
			IPath arrivalRateTxtPath = arrivalRateFolderPath.append(modelName
					+ "ArrivalRates." + fileSuffix);
			PrintWriter arrRateWriter;
			arrRateWriter = new PrintWriter(arrivalRateTxtPath.toString(),
					"UTF-8");

			for (ArrivalRateTuple tuple : arrRates) {
				double arrRate = tuple.getArrivalRate();
				double time = tuple.getTimeStamp();
				arrRateWriter
				.println(time + "," + arrRate + endOfLineCharacter);
			}
			arrRateWriter.close();

		} catch (FileNotFoundException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID, "IO error writing Arrival Rate file.", e));
		} catch (UnsupportedEncodingException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID, "IO error writing Arrival Rate file.", e));
		}
	}
}
