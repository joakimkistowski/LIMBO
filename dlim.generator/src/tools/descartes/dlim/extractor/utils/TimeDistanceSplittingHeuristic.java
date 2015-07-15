/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.List;

import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Utility class implementing a splitting rule that decides whether to split
 * based on the time-axis deviation of local maxima.
 * 
 * @author Joakim von Kistowski
 *
 */
public class TimeDistanceSplittingHeuristic implements ISplittingRule {

	/**
	 * Maximum allowed mean absolute deviation. Relative to the seasonal period.
	 */
	private static final double MAX_MEAN_DEVIATION = 0.08;
	
	/**
	 * Maximum allowed number of time-deviation matches.
	 */
	private static final int MAX_DEVIATING_MATCHES = 3;
	
	/**
	 * Maximum allowed relative difference in seasonal peak counts.
	 */
	private static final double MAX_MEAN_PEAK_DIFFERENCE = 0.12;
	
	@Override
	public boolean warrantSplit(ExtractionDataContainer cleft, ExtractionDataContainer cright) {
		//return false on tiny parts
		if ((cleft.getDuration() / cleft.getPeriod() <= 3.0) || (cright.getDuration() / cright.getPeriod() <= 3.0)) {
			return false;
		}
		
		//compare peak nums and time diff
		return doPeaksDiffer(cleft, cright) || areMeanMaxTimesDifferent(cleft, cright);
	}
	
	/**
	 * Checks if the relative difference in peak numbers per seasonal iteration is greater than
	 * {@link #MAX_MEAN_PEAK_DIFFERENCE}. 
	 * @param cleft The potential split's left hand side extraction data.
	 * @param cright The potential split's right hand side extraction data.
	 * @return true, if number of peaks differ eneough.
	 */
	private boolean doPeaksDiffer(ExtractionDataContainer cleft, ExtractionDataContainer cright) {
		double leftPeriodCount = cleft.getDuration() / cleft.getPeriod();
		double leftPeaks = ((double) cleft.getLocalMaxes().size()) / leftPeriodCount;
		
		double rightPeriodCount = cright.getDuration() / cright.getPeriod();
		double rightPeaks = ((double) cright.getLocalMaxes().size()) / rightPeriodCount;
		
		System.out.println("Relative Peak Difference = " + (Math.abs(leftPeaks - rightPeaks) / leftPeaks) * 100 + "%");
		
		return Math.abs(leftPeaks - rightPeaks) / leftPeaks > MAX_MEAN_PEAK_DIFFERENCE;
	}
	
	/**
	 * Checks if the mean time stamps of local arrival rate maxima are different past a set threshold.
	 * Evaluates the time stamps of three seasonal iterations in each half and compares them with the three
	 * evaluated iterations from the other half. Returns true if more than {@link #MAX_DEVIATING_MATCHES} matches exceed
	 * {@link #MAX_MEAN_DEVIATION} in relation to the seasonal period.
	 * @param cleft The potential split's left hand side extraction data.
	 * @param cright The potential split's right hand side extraction data.
	 * @return true, if enough deviation matches are found.
	 */
	private boolean areMeanMaxTimesDifferent(ExtractionDataContainer cleft, ExtractionDataContainer cright) {
		int deviationcount = 0;
		double period = cleft.getPeriod();
		
		//distances of left side maxes
		double[] leftDist = new double[3];
		leftDist[0] = getMeanDistanceFromWindowStart(cleft.getLocalMaxes(), 0.0, period);
		double windowStart = middlePeriodStart(cleft);
		leftDist[1] = getMeanDistanceFromWindowStart(cleft.getLocalMaxes(), windowStart, windowStart + period);
		windowStart = lastPeriodStart(cleft);
		leftDist[2] = getMeanDistanceFromWindowStart(cleft.getLocalMaxes(), windowStart, windowStart + period);
		
		//distances of right side maxes
		double[] rightDist = new double[3];
		rightDist[0] = getMeanDistanceFromWindowStart(cright.getLocalMaxes(), 0.0, period);
		windowStart = middlePeriodStart(cright);
		rightDist[1] = getMeanDistanceFromWindowStart(cright.getLocalMaxes(), windowStart, windowStart + period);
		windowStart = lastPeriodStart(cright);
		rightDist[2] = getMeanDistanceFromWindowStart(cright.getLocalMaxes(), windowStart, windowStart + period);
		
		//count matches
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println("Deviation = " + Math.abs(leftDist[i] - rightDist[j]) / period);
				if (Math.abs(leftDist[i] - rightDist[j]) / period > MAX_MEAN_DEVIATION) {
					deviationcount++;
				}
			}
		}
		
		System.out.println("Deviation Counts = " + deviationcount);
		
		return deviationcount > MAX_DEVIATING_MATCHES;
	}

	/**
	 * Gets the aggregate mean distance of all time stamps in the given time window relative to the window's start.
	 * @param tuples The arrival rate list containing all time stamps. May contain arrival rate tuples outside
	 * 				the window. These are ignored.
	 * @param windowStart The time of window start.
	 * @param windowEnd The time of window end.
	 * @return Mean absolute distance of timestamps within window to window start.
	 */
	private double getMeanDistanceFromWindowStart(List<ArrivalRateTuple> tuples, double windowStart, double windowEnd) {
		double sum = 0;
		int count = 0;
		for (ArrivalRateTuple t : tuples) {
			double ts = t.getTimeStamp();
			if (ts >= windowEnd) {
				break;
			} else if (ts >= windowStart && ts < windowEnd) {
				sum += (ts - windowStart);
				count++;
			}
		}
		return sum / count;
	}
	
	/**
	 * Gets the start time of the last complete seasonal iteration in within the container.
	 * @param container The extraction data container, including all known extraction data.
	 * @return The time.
	 */
	private double lastPeriodStart(ExtractionDataContainer container) {
		int iterationNum = (int) (container.getDuration() / container.getPeriod()) - 1;
		return container.getPeriod() * iterationNum;
	}
	
	/**
	 * Gets the start time of the middle complete seasonal iteration in within the container.
	 * @param container The extraction data container, including all known extraction data.
	 * @return The time.
	 */
	private double middlePeriodStart(ExtractionDataContainer container) {
		int iterationNum = (int) ((container.getDuration() / container.getPeriod()) - 1) / 2;
		return container.getPeriod() * iterationNum;
	}
	
}
