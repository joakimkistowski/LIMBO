/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.assistant;

import tools.descartes.dlim.Burst;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.generator.ModelEvaluatorUtil;

/**
 * Provides methods for the calibration of interpolated function attributes. The
 * returned value is always the attribute value, which results in the overall
 * model output of desiredValue.
 *
 * @author Joakim von Kistowski
 *
 */
public final class Calibrator {

	private static final double EPSILON = 1e-15;
	private static final double MAXCALIBRATIONERROR = 1e-5;

	/**
	 * It's all statics anyways.
	 */
	private Calibrator() {

	}

	/**
	 * Sets trend.start in a way that results in the entire model producing
	 * desiredValue as output at the beginning of the trend.
	 *
	 * @param desiredValue the desired value
	 * @param trend the trend
	 * @param evaluator            must be initialized using IGeneratorConstants.CALIBRATION
	 * @return the double
	 * @throws CalibrationException             If the different values at the Trend's beginning time have no
	 *             effect on the model output.
	 */
	public static double calibrateTrendStartValue(double desiredValue,
			Trend trend, ModelEvaluator evaluator) throws CalibrationException {
		double oldValue = trend.getFunctionOutputAtStart();
		double time = ModelEvaluatorUtil.getFunctionBegin(trend);
		trend.setFunctionOutputAtStart(0);
		double f0 = evaluator.getArrivalRateAtTime(time);
		trend.setFunctionOutputAtStart(1);
		double f1 = evaluator.getArrivalRateAtTime(time);
		if (f1 == f0) {
			throw new CalibrationException("Trend is not being executed"
					+ " at the time at which its startValue is defined.");
		}
		// desiredValue = f0 + (f1-f0)*returnValue
		double newValue = (desiredValue - f0) / (f1 - f0);
		trend.setFunctionOutputAtStart(newValue);
		if (evaluator.getArrivalRateAtTime(time) - desiredValue > MAXCALIBRATIONERROR) {
			trend.setFunctionOutputAtStart(oldValue);
			throw new CalibrationException(
					"Calibration returned incorrect value."
							+ " Is this Trend an (indirect) child of a UnivariateFuncion?");
		}
		trend.setFunctionOutputAtStart(oldValue);

		return newValue;
	}

	/**
	 * Sets trend.end in a way that results in the entire model producing
	 * desiredValue as output at the end of the trend.
	 *
	 * @param desiredValue the desired value
	 * @param trend the trend
	 * @param evaluator            must be initialized using IGeneratorConstants.CALIBRATION
	 * @return the double
	 * @throws CalibrationException             If the different values at the Trend's end time have no
	 *             effect on the model output.
	 */
	public static double calibrateTrendEndValue(double desiredValue,
			Trend trend, ModelEvaluator evaluator) throws CalibrationException {
		double oldValue = trend.getFunctionOutputAtEnd();
		double end = ModelEvaluatorUtil.getFunctionBegin(trend)
				+ ModelEvaluatorUtil.getFunctionDuration(trend);
		double time = end;
		double epsilon = EPSILON;
		// Since the next element is already under execution at the end time,
		// it must be decremented by the smallest amount possible
		// This actually has to be an "==" and not an epsilon comparison,
		// since that's the point of the entire thing.
		while (end == time) {
			time -= epsilon;
			epsilon *= 2;
		}
		trend.setFunctionOutputAtEnd(0);
		double f0 = evaluator.getArrivalRateAtTime(time);
		trend.setFunctionOutputAtEnd(1);
		double f1 = evaluator.getArrivalRateAtTime(time);

		if (f1 == f0) {
			throw new CalibrationException("Trend is not being executed"
					+ " at the time at which its endValue is defined.");
		}
		// desiredValue = f0 + (f1-f0)*returnValue
		double newValue = (desiredValue - f0) / (f1 - f0);
		trend.setFunctionOutputAtEnd(newValue);
		if (evaluator.getArrivalRateAtTime(time) - desiredValue > MAXCALIBRATIONERROR) {
			trend.setFunctionOutputAtEnd(oldValue);
			throw new CalibrationException(
					"Calibration returned incorrect value."
							+ " Is this Trend an (indirect) child of a UnivariateFuncion?");
		}
		trend.setFunctionOutputAtEnd(oldValue);

		return newValue;
	}

	/**
	 * Sets trend.start in a way that results in the entire model producing
	 * desiredValue as output at the peak of the burst.
	 *
	 * @param desiredValue the desired value
	 * @param burst the burst
	 * @param evaluator            must be initialized using IGeneratorConstants.CALIBRATION
	 * @return the double
	 * @throws CalibrationException             If the different values at the Trend's beginning time have no
	 *             effect on the model output.
	 */
	public static double calibrateBurstPeakValue(double desiredValue,
			Burst burst, ModelEvaluator evaluator) throws CalibrationException {
		double oldValue = burst.getPeak();
		double time = ModelEvaluatorUtil.getFunctionBegin(burst)
				+ burst.getPeakTime();
		burst.setPeak(0);
		double f0 = evaluator.getArrivalRateAtTime(time);
		burst.setPeak(1);
		double f1 = evaluator.getArrivalRateAtTime(time);
		if (f1 == f0) {
			throw new CalibrationException("Burst is not being executed"
					+ " at the time at which its peakValue is defined.");
		}
		// desiredValue = f0 + (f1-f0)*returnValue
		double newValue = (desiredValue - f0) / (f1 - f0);
		burst.setPeak(newValue);
		if (evaluator.getArrivalRateAtTime(time) - desiredValue > MAXCALIBRATIONERROR) {
			burst.setPeak(oldValue);
			throw new CalibrationException(
					"Calibration returned incorrect value."
							+ " Is this Burst an (indirect) child of a UnivariateFuncion?");
		}
		burst.setPeak(oldValue);

		return newValue;
	}
}
