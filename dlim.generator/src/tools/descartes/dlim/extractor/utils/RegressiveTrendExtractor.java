/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.Iterator;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Utility class containing everything needed for the extraction of
 * Trends, using error minimizing regression.
 * 
 * @author Joakim von Kistowski
 *
 */
public final class RegressiveTrendExtractor {
	
	/**
	 * The default offset for trend segments. Relative to the seasonal duration.
	 */
	private static final double DEFAULT_RELATIVE_TREND_OFFSET = 0.5;
	
	/**
	 * Private constructor to prevent instatiation.
	 */
	private RegressiveTrendExtractor() {
	}
	
	/**
	 * Gets a list of the values for trend end/start points. Uses an advanced regression technique for those.
	 * Not to be confused with the arrival rates gotten from getTrendValues()
	 * @param container The container of with all available extraction data.
	 * @return A double array containing the end/start points to set at the DLIM trends.
	 */
	public static double[] getTrendEndPointsUsingRegression(ExtractionDataContainer container) {
		int trendPointNum = (int) (container.getDuration() / container.getPeriod()) / container.getSeasonalsPerTrend();
		
		double[] trendValues = new double[trendPointNum];
		double trendOffset = container.getPeriod() / 2.0;
		ExtractionDataContainer currentSubContainer = container;
		Iterator<ExtractionDataContainer> containerIterator = container.getSubContainerList().iterator(); 
		if (!container.getSubContainerList().isEmpty()) {
			currentSubContainer = container.getSubContainerList().get(0);
		}
		ArrivalRateTuple[] regPoints = getArrivalRatePointsForRegression(currentSubContainer);
		
		for (int i = 0; i < trendPointNum; i++) {
			double seasonalStart = container.getPeriod() * i;
			double trendCalibrationTimeStamp = trendOffset + seasonalStart;
			//get the appropriate subcontainer
			while (trendCalibrationTimeStamp > currentSubContainer.getBeginTime() + currentSubContainer.getDuration()
					&& containerIterator.hasNext()) {
				currentSubContainer = containerIterator.next();
				regPoints = getArrivalRatePointsForRegression(currentSubContainer);
			}
			ArrivalRateTuple[] originals = getCorrespondingOriginalArrivalRates(regPoints, seasonalStart, container);
			if (container.getOperatorLiteral().contains("MULT")) {
				trendValues[i] = getMultiplicativeRegressionPoint(regPoints, originals);
			} else if (container.getOperatorLiteral().contains("ADD")) {
				trendValues[i] = getAdditiveTrendPoint(regPoints, originals);
			} else {
				trendValues[i] = -1.0 * getAdditiveTrendPoint(regPoints, originals);
			}
			
		}
		return trendValues;
	}
	
	private static double getMultiplicativeRegressionPoint(ArrivalRateTuple[] regPoints, ArrivalRateTuple[] originals) {
		SimpleRegression regression = new SimpleRegression(false);
		for (int i = 0; i < regPoints.length; i++) {
			regression.addData(regPoints[i].getArrivalRate(), originals[i].getArrivalRate());
		}
		return regression.getSlope();
	}
	
	private static double getAdditiveTrendPoint(ArrivalRateTuple[] regPoints, ArrivalRateTuple[] originals) {
		double sum = 0;
		for (int i = 0; i < regPoints.length; i++) {
			sum += (originals[i].getArrivalRate() - regPoints[i].getArrivalRate());
		}
		return sum / (double) regPoints.length;
	}
	
	/**
	 * Gets those arrival rates from the global arrival rate list that correspond to the
	 * time stamps of the regression points.
	 * Uses linear interpolation to interpolate, if exactly corresponding arrival rate tuples
	 * don't exist.
	 */
	private static ArrivalRateTuple[] getCorrespondingOriginalArrivalRates(
			ArrivalRateTuple[] regPoints, double seasonalStart, ExtractionDataContainer globalContainer) {
		ArrivalRateTuple[] originals = new ArrivalRateTuple[regPoints.length];

		int regIndex = 0;
		ArrivalRateTuple prev = globalContainer.getArrivalRateList().get(0);
		
		for (ArrivalRateTuple next : globalContainer.getArrivalRateList()) {
			double w;
			while ((w = calculateInterpolationWeight(
					seasonalStart + regPoints[regIndex].getTimeStamp(), next, prev)) >= 0) {
				originals[regIndex] = new ArrivalRateTuple(
						prev.getTimeStamp() + w * (next.getTimeStamp() - prev.getTimeStamp()),
						prev.getArrivalRate() + w * (next.getArrivalRate() - prev.getArrivalRate()));
				regIndex++;
				if (regIndex >= regPoints.length) {
					break;
				}
			}
			if (regIndex >= regPoints.length) {
				break;
			}
			prev = next;
		}
		return originals;
	}
	
	/**
	 * Calculates the weight for linear interpolation of time stamps between two neighboring arrival rate tuples.
	 * Returns a weight "w" so that timstamp = prev + w * (next-prev). Returns a negative number if the timestamp is
	 * located between prev and next.
	 * @param next The next arrival rate tuple.
	 * @param prev The previous arrival rate tuple.
	 * @return The interpolation weight.
	 */
	private static double calculateInterpolationWeight(double timestamp, ArrivalRateTuple next, ArrivalRateTuple prev) {
		if (timestamp < prev.getTimeStamp() || timestamp >= next.getTimeStamp()) {
			return -1.0;
		}
		double w = timestamp - prev.getTimeStamp();
		return w / (next.getTimeStamp() - prev.getTimeStamp());
	}
	
	/**
	 * Gets the arrival rate points of the current seasonal iteration. Will be used as regression input.
	 */
	private static ArrivalRateTuple[] getArrivalRatePointsForRegression(ExtractionDataContainer container) {
			int innerBaseCount = 0;
			if (container.getInnerBases() != null) {
				innerBaseCount = container.getInnerBases().length;
			}
			ArrivalRateTuple[] regPoints =
					new ArrivalRateTuple[innerBaseCount + container.getPeaks().length];
			for (int i = 0; i < container.getPeaks().length; i++) {
				regPoints[i * 2] = container.getPeaks()[i];
			}
			if (container.getInnerBases() != null) {
				for (int i = 0; i < container.getInnerBases().length; i++) {
					regPoints[i * 2 + 1] = container.getInnerBases()[i];
				}
			}
			return regPoints;
	}
	
	/**
	 * Build the Trend part within the DLIM instance. container must contain
	 * trend point values as calculated by the RegressiveTrendExtractor. This means that
	 * the calculated values must be the actual values to set for the trend. They must NOT
	 * be target arrival rates at the trend segment's end points.
	 * @param root The model's root sequence.
	 * @param container the container containing all known extraction data.
	 */
	public static void buildTrendPart(Sequence root, ExtractionDataContainer container) {
		BasicTrendExtractionUtilities.buildTrendPart(root, container,
				container.getPeriod() * DEFAULT_RELATIVE_TREND_OFFSET, false);

	}
	
}
