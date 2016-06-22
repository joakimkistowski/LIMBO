/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Utility class containing basic helper functions used when extracting arrival rate models.
 * 
 * @author Joakim von Kistowski
 *
 */
public final class BasicExtractionUtils {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private BasicExtractionUtils() {
		
	}
	
	/**
	 * Returns the median value for a sorted double array.
	 *
	 * @param array
	 *            Must be sorted.
	 * @return The array's median.
	 */
	public static double getMedian(double[] array) {
		//System.out.println(Arrays.toString(array));
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length - 1) / 2] + array[(array.length) / 2]) / 2.0;
		}
		return array[(array.length - 1) / 2];
	}

	/**
	 * Returns the median time stamp for a sorted ArrivalRateTuple array.
	 *
	 * @param array
	 *            Must be sorted by time stamps.
	 * @return The median time stamp.
	 */
	public static double getMedianTimeStamp(ArrivalRateTuple[] array) {
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length - 1) / 2].getTimeStamp() + array[(array.length) / 2]
					.getTimeStamp()) / 2.0;
		}
		return array[(array.length - 1) / 2].getTimeStamp();
	}

	/**
	 * Returns the median arrival rate for a sorted ArrivalRateTuple array.
	 *
	 * @param array
	 *            Must be sorted by arrival rates.
	 * @return The median arrival rate.
	 */
	public static double getMedianArrivalRate(ArrivalRateTuple[] array) {
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length - 1) / 2].getArrivalRate() + array[(array.length) / 2]
					.getArrivalRate()) / 2.0;
		}
		return array[(array.length - 1) / 2].getArrivalRate();
	}
	
	/**
	 * Get the arrival rate at time-stamp time. Uses either linear interpolation
	 * or nearest neighbor interpolation for undefined time stamps.
	 * @param time The time stamp of the arrival rate.
	 * @param arrRates The arrival rate list.
	 * @param linearInterpolation Set to true, for linear interpolation.
	 * @return The (interpolated) arrival rate at the time stamp.
	 */
	public static double getArrivalRate(double time,
			List<ArrivalRateTuple> arrRates, boolean linearInterpolation) {
		if (arrRates.size() == 0) {
			return 0.0;
		}

		if (time >= arrRates.get(arrRates.size() - 1).getTimeStamp()) {
			return 0.0;
		} else if (time < arrRates.get(0).getTimeStamp()) {
			if (time >= 0) {
				// interpolate between 0 and first arrival rate value
				if (linearInterpolation) {
					return (time / arrRates.get(0).getTimeStamp())
							* arrRates.get(0).getArrivalRate();
				} else {
					return arrRates.get(0).getArrivalRate();
				}

			}
			return 0.0;
		}

		double assumedStep = arrRates.get(0).getStep(null);

		// find the surrounding list elements
		int index = (int) (time / assumedStep);
		if (index >= arrRates.size() - 1) {
			index = arrRates.size() - 2;
		}
		ArrivalRateTuple lower = arrRates.get(index);
		ArrivalRateTuple higher = arrRates.get(index + 1);
		while (time < lower.getTimeStamp()) {
			index--;
			lower = arrRates.get(index);
		}
		while (time >= higher.getTimeStamp()) {
			index++;
			higher = arrRates.get(index + 1);
		}
		lower = arrRates.get(index);
		higher = arrRates.get(index + 1);

		// interpolate result
		if (linearInterpolation) {
			return lower.getArrivalRate()
					+ ((time - lower.getTimeStamp()) / (higher.getTimeStamp() - lower
							.getTimeStamp()))
							* (higher.getArrivalRate() - lower.getArrivalRate());
		} else {
			// nearest neighbor
			if (time - lower.getTimeStamp() < higher.getTimeStamp() - time) {
				return lower.getArrivalRate();
			} else {
				return higher.getArrivalRate();
			}
		}
	}
	
	/**
	 * Get the nearest neighbor arrival rate tuple to time-stamp time.
 	 * @param time The time stamp of the arrival rate tuple.
	 * @param arrRates The arrival rate list.
	 * @return The arrival rate tuple.
	 */
	public static ArrivalRateTuple getNearestArrivalRateTuple(double time,
			List<ArrivalRateTuple> arrRates) {
		if (arrRates.size() == 0) {
			return null;
		}

		if (time >= arrRates.get(arrRates.size() - 1).getTimeStamp()) {
			return arrRates.get(arrRates.size() - 1);
		} else if (time < arrRates.get(0).getTimeStamp()) {
			return arrRates.get(0);
		}

		double assumedStep = arrRates.get(0).getStep(null);

		// find the surrounding list elements
		int index = (int) (time / assumedStep);
		if (index >= arrRates.size() - 1) {
			index = arrRates.size() - 2;
		}
		ArrivalRateTuple lower = arrRates.get(index);
		ArrivalRateTuple higher = arrRates.get(index + 1);
		while (time < lower.getTimeStamp()) {
			index--;
			lower = arrRates.get(index);
		}
		while (time >= higher.getTimeStamp()) {
			index++;
			higher = arrRates.get(index + 1);
		}
		lower = arrRates.get(index);
		higher = arrRates.get(index + 1);

		// nearest neighbor
		if (time - lower.getTimeStamp() < higher.getTimeStamp() - time) {
			return lower;
		} else {
			return higher;
		}
	}
	
}
