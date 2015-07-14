/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;

import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.Constant;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Function;
import tools.descartes.dlim.Operator;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.assistant.Calibrator;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Utility class containing functionality needed for the extraction of
 * basic Trends. For more advanced trend extraction, see {@link RegressiveTrendExtractor}.
 * 
 * @author Joakim von Kistowski
 *
 */
public final class BasicTrendExtractionUtilities {

	/**
	 * Private Extractor to prevent instantiation.
	 */
	private BasicTrendExtractionUtilities() {
	}
	
	
	/**
	 * Build the Trend part within the DLIM instance.
	 * 
	 * if calibrateTrendPoints==true: Trend points in the container must indicate
	 * the target arrival rate that must be reached at a trend segment's end/beginning.
	 * @param root The model's root sequence.
	 * @param container The container containing all known extraction data.
	 * @param trendOffset The time offset for the beginning of the first trend segment.
	 * 			Usually corresponds to the largest peak in the seasonal iterations.
	 * @param calibrateTrendPoints Indicates whether trend points are arrival rate targets,
	 * 			which then require additional calibration, or if they are already the final
	 * 			values to be used in trend building.
	 */
	public static void buildTrendPart(Sequence root, ExtractionDataContainer container,
			double trendOffset, boolean calibrateTrendPoints) {
		double trendDuration = container.getPeriod() * container.getSeasonalsPerTrend();
		root.getCombine().clear();
		if (trendDuration > 0.0 && container.getMaxPeakOffset() + trendDuration < container.getDuration()
				&& container.getTrendPointValues().length > 1) {
			DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
			Combinator trendCombinator = factory.createCombinator();
			trendCombinator.setOperator(Operator.get(container.getOperatorLiteral()));
			Sequence trendSequence = factory.createSequence();
			trendSequence.setName("trends");
			trendSequence.setTerminateAfterTime(container.getDuration());
			trendCombinator.setFunction(trendSequence);
			root.getCombine().add(trendCombinator);

			TimeDependentFunctionContainer offsetElement = factory
					.createTimeDependentFunctionContainer();
			offsetElement.setDuration(trendOffset);
			offsetElement.setName("offset");
			offsetElement.setFunction(factory.createConstant());
			trendSequence.getSequenceFunctionContainers().add(offsetElement);

			for (int i = 0; i < container.getTrendPointValues().length - 1; i++) {
				TimeDependentFunctionContainer trendElement = factory
						.createTimeDependentFunctionContainer();
				trendElement.setDuration(trendDuration);
				trendElement.setName("trend" + i);
				trendElement
				.setFunction(createTrendWithCorrectShape(container.getSeasonalShape()));
				trendSequence.getSequenceFunctionContainers().add(trendElement);
			}
			TimeDependentFunctionContainer holdElement = factory
					.createTimeDependentFunctionContainer();
			holdElement.setDuration(container.getDuration() - trendOffset - trendDuration
					* (container.getTrendPointValues().length - 1));
			holdElement.setName("holdLastTrend");
			holdElement.setFunction(factory.createConstant());
			trendSequence.getSequenceFunctionContainers().add(holdElement);

			// calibrate Trends
			int i = 0;
			for (TimeDependentFunctionContainer e : trendSequence
					.getSequenceFunctionContainers()) {
				Function f = e.getFunction();
				if (f instanceof Trend) {
					Trend trend = (Trend) f;
					if (calibrateTrendPoints) {
						trend.setFunctionOutputAtStart(calibrateTrendStartValue(
								root, trend, container.getTrendPointValues()[i]));
					} else {
						trend.setFunctionOutputAtStart(container.getTrendPointValues()[i]);
					}
					i++;
					if (calibrateTrendPoints) {
						trend.setFunctionOutputAtEnd(calibrateTrendEndValue(root,
								trend, container.getTrendPointValues()[i]));
					} else {
						trend.setFunctionOutputAtEnd(container.getTrendPointValues()[i]);
					}
				}
			}
			// set offset constant values
			Constant offset1 = (Constant) trendSequence
					.getSequenceFunctionContainers().get(0).getFunction();
			offset1.setConstant(((Trend) (trendSequence
					.getSequenceFunctionContainers().get(1).getFunction()))
					.getFunctionOutputAtStart());

			int lastIndex = trendSequence.getSequenceFunctionContainers()
					.size() - 1;
			Constant offset2 = (Constant) trendSequence
					.getSequenceFunctionContainers().get(lastIndex)
					.getFunction();
			offset2.setConstant(((Trend) (trendSequence
					.getSequenceFunctionContainers().get(lastIndex - 1)
					.getFunction())).getFunctionOutputAtEnd());

		}
	}

	/**
	 * Build the Trend part for the periodic extraction process. Trend points in the container must indicate
	 * the target arrival rate that must be reached at a trend segment's end/beginning.
	 * @param root The model's root sequence.
	 * @param container The container containing all known extraction data.
	 * @param seasonalsPerTrend Do I need this (or remove)??
	 * @param trendPoints I probably should remove this parameter as well.
	 */
	public static void buildRepeatingTrend(Sequence root,
			int[] seasonalsPerTrend, double[] trendPoints,
			ExtractionDataContainer container) {

		double trendDuration = 0;
		for (int seasonals : seasonalsPerTrend) {
			trendDuration += seasonals;
		}
		trendDuration *= container.getPeriod();

		if (trendDuration > 0.0 && trendPoints.length > 1) {
			DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
			Combinator trendCombinator = factory.createCombinator();
			trendCombinator.setOperator(Operator.get(container.getOperatorLiteral()));
			Sequence trendSequenceContainer = factory.createSequence();
			trendSequenceContainer.setName("trendContainer");
			trendSequenceContainer.setTerminateAfterTime(container.getDuration());
			Sequence trendSequence = factory.createSequence();
			trendSequence.setName("trends");
			trendSequence.setTerminateAfterTime(container.getDuration());
			trendCombinator.setFunction(trendSequenceContainer);
			root.getCombine().add(trendCombinator);

			TimeDependentFunctionContainer offsetElement = factory
					.createTimeDependentFunctionContainer();
			offsetElement.setDuration(container.getMaxPeakOffset());
			offsetElement.setName("offset");
			offsetElement.setFunction(factory.createConstant());
			trendSequenceContainer.getSequenceFunctionContainers().add(
					offsetElement);

			TimeDependentFunctionContainer trendContainer = factory
					.createTimeDependentFunctionContainer();
			trendContainer.setDuration(container.getDuration());
			trendContainer.setName("trends");
			trendContainer.setFunction(trendSequence);
			trendSequenceContainer.getSequenceFunctionContainers().add(
					trendContainer);

			for (int i = 0; i < trendPoints.length - 1; i++) {
				TimeDependentFunctionContainer trendElement = factory
						.createTimeDependentFunctionContainer();
				trendElement.setDuration(seasonalsPerTrend[i] * container.getPeriod());
				trendElement.setName("trend" + i);
				trendElement
				.setFunction(createTrendWithCorrectShape(container.getTrendShape()));
				trendSequence.getSequenceFunctionContainers().add(trendElement);
			}

			// calibrate Trends
			int i = 0;
			for (TimeDependentFunctionContainer e : trendSequence
					.getSequenceFunctionContainers()) {
				Function f = e.getFunction();
				if (f instanceof Trend) {
					Trend trend = (Trend) f;
					trend.setFunctionOutputAtStart(calibrateTrendStartValue(
							root, trend, trendPoints[i]));
					i++;
					trend.setFunctionOutputAtEnd(calibrateTrendEndValue(root,
							trend, trendPoints[i]));
				}
			}
			// set offset constant value
			Constant offset1 = (Constant) trendSequenceContainer
					.getSequenceFunctionContainers().get(0).getFunction();
			offset1.setConstant(((Trend) (trendSequence
					.getSequenceFunctionContainers().get(0).getFunction()))
					.getFunctionOutputAtStart());

		}
	}
	
	/*
	 * Calibrate the start value of an interpolated trend function.
	 */
	private static double calibrateTrendStartValue(Sequence rootSequence,
			Trend trend, double value) {
		ModelEvaluator evaluator = new ModelEvaluator(rootSequence, 0,
				IGeneratorConstants.CALIBRATION);
		double startValue = value;
		try {
			startValue = Calibrator.calibrateTrendStartValue(value, trend,
					evaluator);
		} catch (CalibrationException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID,
							"CalibrationException:" + e.getMessage(), e));
		}
		return startValue;
	}

	/*
	 * Calibrate the end value of an interpolated trend function.
	 */
	private static double calibrateTrendEndValue(Sequence rootSequence,
			Trend trend, double value) {
		ModelEvaluator evaluator = new ModelEvaluator(rootSequence, 0,
				IGeneratorConstants.CALIBRATION);
		double endValue = value;
		try {
			endValue = Calibrator.calibrateTrendEndValue(value, trend,
					evaluator);
		} catch (CalibrationException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID,
							"CalibrationException:" + e.getMessage(), e));
		}
		return endValue;
	}
	
	/**
	 * Create the Trend with the correct implementation.
	 * @param shapeString the String literal for the trend's shape.
	 * @return The Trend as a DLIM Trend.
	 */
	public static Trend createTrendWithCorrectShape(String shapeString) {
		try {
			return (Trend) DlimPackage.eINSTANCE.getDlimFactory()
					.create((EClass) (DlimPackage.eINSTANCE
							.getEClassifier(shapeString)));
		} catch (NullPointerException e) {
			return DlimPackage.eINSTANCE.getDlimFactory().createSinTrend();
		}
	}
}
