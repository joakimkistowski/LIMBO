/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import tools.descartes.dlim.DlimGeneratorPlugin;

import com.dtw.FastDTW;
import com.timeseries.TimeSeries;
import com.timeseries.TimeSeriesPoint;
import com.util.EuclideanDistance;

/**
 * Calculates the difference between a DLIM and an arrival rate .txt file.
 *
 * @author Joakim von Kistowski
 *
 */
public class DiffAnalyzer {

	private ModelEvaluator evaluator;
	private IPath projectPath;

	private TimeSeries fileTS;
	private TimeSeries modelTS;

	// This list holds the differences for each sampled point in time
	private ArrayList<Double> diffList;
	private ArrayList<Double> relativeDiffList;

	/**
	 * Create a new DiffAnalyzer for the model that is being evaluated by the
	 * evaluator. All output will be written into the Eclipse project, described
	 * by its root path (projectPath).
	 *
	 * @param evaluator the model's evaluator
	 * @param projectPath Path of the eclipse modeling project
	 * (used for output Path determination)
	 */
	public DiffAnalyzer(ModelEvaluator evaluator, String projectPath) {
		this.evaluator = evaluator;
		this.projectPath = new Path(projectPath);
		diffList = new ArrayList<Double>();
		relativeDiffList = new ArrayList<Double>();
		fileTS = new TimeSeries(2);
		modelTS = new TimeSeries(2);
	}

	/**
	 * Calculate the difference.
	 *
	 * @param readArrivalRates The arrival rates as read from the original trace.
	 *            The file to which the model is to be compared.
	 * @param offset the offset of the first arrival rate tuple in the arrival rate file
	 * @return A list of difference metrices: 1. absolute mean, 2. absolute
	 *         median, 3. DTW based difference, 4. relative mean, 5. relative
	 *         median
	 */
	public List<Double> calculateDiff(List<ArrivalRateTuple> readArrivalRates, double offset) {
		try {
			double maxArrivalRate = 0.0;
			IPath diffFolderPath = projectPath.append("diffs");
			File diffFolder = diffFolderPath.toFile();
			if (!diffFolder.exists()) {
				diffFolder.mkdir();
			}

			IPath diffTxtPath = diffFolderPath.append(evaluator.getName()
					+ "Diff.txt");
			PrintWriter diffWriter = new PrintWriter(diffTxtPath.toString(),
					"UTF-8");
			double diffSum = 0;
			double relativeDiffSum = 0;
			for (ArrivalRateTuple t : readArrivalRates) {
				double timeStamp = t.getTimeStamp();
				double readArrivalRate = t.getArrivalRate();
				double modelTimeStamp = timeStamp - offset;
				if (modelTimeStamp > 0
						&& modelTimeStamp < evaluator.getTerminatingDuration()) {
					double[] fileTSPoint = { modelTimeStamp,
							readArrivalRate };
					fileTS.addLast(modelTimeStamp, new TimeSeriesPoint(
							fileTSPoint));
					double functionArrivalRate = evaluator
							.getArrivalRateAtTime(modelTimeStamp);
					double[] modelTSPoint = { modelTimeStamp,
							functionArrivalRate };
					modelTS.addLast(modelTimeStamp, new TimeSeriesPoint(
							modelTSPoint));
					double diff = Math.abs(readArrivalRate
							- functionArrivalRate);
					diffWriter.println(modelTimeStamp + "," + diff + ";");
					diffList.add(diff);
					double relativeDiff = 0.0;
					if (readArrivalRate != 0.0) {
						relativeDiff = diff / readArrivalRate;
						// } else if (functionArrivalRate != 0.0) {
						// relativeDiff = diff/functionArrivalRate;
					}
					relativeDiffList.add(relativeDiff);

					relativeDiffSum += relativeDiff;
					diffSum += diff;

					// set maximum arrival rate
					if (readArrivalRate > maxArrivalRate) {
						maxArrivalRate = readArrivalRate;
					}
				}
			}
			diffWriter.close();

			if (diffList.size() == 0) {
				DlimGeneratorPlugin.INSTANCE.log(
						new Status(Status.WARNING, DlimGeneratorPlugin.PLUGIN_ID,
								"file has an incorrect format. Only files "
										+ "with the correct Arrival Rate format can be read."));
			}

			Collections.sort(diffList);
			Collections.sort(relativeDiffList);
			double dtwDist = FastDTW.getWarpInfoBetween(fileTS, modelTS, 10,
					new EuclideanDistance()).getDistance();

			// this devision should result in the distance between 0 and a
			// function being 1
			dtwDist = dtwDist / diffList.size();
			dtwDist = dtwDist / maxArrivalRate;

			double mean = diffSum / diffList.size();
			double median = diffList.get(diffList.size() / 2);


			LinkedList<Double> statisticalValues = new LinkedList<Double>();
			statisticalValues.add(mean);
			statisticalValues.add(median);
			statisticalValues.add(dtwDist);

			// relative mean and median
			statisticalValues.add(relativeDiffSum / relativeDiffList.size());
			statisticalValues
			.add(relativeDiffList.get(relativeDiffList.size() / 2));


			//Comment in this code for nice LaTeX-Style Diff results in the console
			//printToConsole(evaluator.getName(), statisticalValues);
			// System.out.println(evaluator.getName() + " & " +mean + " & " +
			// statisticalValues.get(3)*100
			// + " & " + median + " & " + statisticalValues.get(4)*100
			// + " & " + dtwDist + " \\\\");

			return statisticalValues;

		} catch (FileNotFoundException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
							"Output Diff File not found.", e));
		} catch (UnsupportedEncodingException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
							"Diff encoding unsupported.", e));
		} catch (IndexOutOfBoundsException e) {
			new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
					"Could not Calculate Difference. Wrong file Type?");
		}
		return new LinkedList<Double>();
	}

	//Comment in this code for nice LaTeX-Style Diff results in the console
	//	private static void printToConsole(String name, List<Double> stats) {
	//		String firstCol = "";
	//		if (name.contains("HLDLIM")) {
	//			firstCol += "HLDLIM\\\\ ";
	//		} else if (name.contains("_simple_")) {
	//			firstCol += "DLIM Simple\\\\ ";
	//		} else {
	//			firstCol = "DLIM Periodic\\\\ ";
	//		}
	//		if (name.contains("_Trendlength1_")) {
	//			firstCol += "Trend length 1\\\\ ";
	//		} else if (name.contains("_Trendlength2_")) {
	//			firstCol += "Trend length 2\\\\ ";
	//		} else if (name.contains("_Trendlength3_")) {
	//			firstCol += "Trend length 3\\\\ ";
	//		}
	//		if (name.contains("_NoiseEliminated")) {
	//			firstCol += "noise eliminated ";
	//		} else if (name.contains("_Noise")) {
	//			firstCol += "noise extracted ";
	//		} else {
	//			firstCol += "noise ignored ";
	//		}
	//
	//		//Additional (optional) output
	//		//		System.out.println(firstCol + " & " + roundTo(stats.get(0), 3) + " & "
	//		//				+ roundTo(stats.get(3) * 100, 3) + " & "
	//		//				+ roundTo(stats.get(1), 3) + " & "
	//		//				+ roundTo(stats.get(4) * 100, 3) + " & "
	//		//				+ roundTo(stats.get(2), 6) + " \\\\");
	//		//		System.out.println("\\hline");
	//	}
	//
	//	private static double roundTo(double val, int places) {
	//		BigDecimal bd = new BigDecimal(val);
	//		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
	//		return bd.doubleValue();
	//	}
}
