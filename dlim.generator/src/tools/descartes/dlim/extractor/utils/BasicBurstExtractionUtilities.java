/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.ArrayList;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;

import tools.descartes.dlim.Burst;
import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Operator;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.assistant.Calibrator;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Utility class containing functionality needed for the extraction of
 * basic bursts.
 * 
 * @author Joakim von Kistowski
 *
 */
public final class BasicBurstExtractionUtilities {

	/**
	 * Detects a burst if its arrival rate is this greater than the
	 * respective (modified) seasonal pattern.
	 */
	private static final double BURSTDETECTIONFACTOR = 1.2;
	
	/**
	 * Private Extractor to prevent instantiation.
	 */
	private BasicBurstExtractionUtilities() {
		
	}
	
	/**
	 * Build the burst part.
	 * @param root The model's root sequence.
	 * @param container The container containing all known extraction data.
	 */
	public static void buildBurstPart(Sequence root, ExtractionDataContainer container) {
		if (container.getBursts().size() > 0) {
			double halfBurstWidth = container.getBurstWidth() / 2.0;
			DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
			Combinator burstCombinator = factory.createCombinator();
			burstCombinator.setOperator(Operator.ADD);
			Sequence burstSequence = factory.createSequence();
			burstSequence.setTerminateAfterTime(container.getDuration());
			burstSequence.setTerminateAfterLoops(1);
			burstSequence.setName("bursts");
			burstCombinator.setFunction(burstSequence);

			int index = 0;
			double offsetDurationDecrement = halfBurstWidth;
			for (ArrivalRateTuple burst : container.getBursts()) {
				TimeDependentFunctionContainer offsetContainer = factory
						.createTimeDependentFunctionContainer();
				offsetContainer.setName("burstOffset" + index);
				offsetContainer.setDuration(burst.getTimeStamp()
						- offsetDurationDecrement);
				offsetDurationDecrement = container.getBurstWidth() + burst.getTimeStamp();
				TimeDependentFunctionContainer burstContainer = factory
						.createTimeDependentFunctionContainer();
				burstContainer.setDuration(container.getBurstWidth());
				burstContainer.setName("burst" + index);
				Burst burstFunction = createBurstWithCorrectShape("");
				burstFunction.setBase(0.0);
				burstFunction.setPeakTime(halfBurstWidth);
				burstContainer.setFunction(burstFunction);
				burstSequence.getSequenceFunctionContainers().add(
						offsetContainer);
				burstSequence.getSequenceFunctionContainers().add(
						burstContainer);
				index++;
			}

			root.getCombine().add(burstCombinator);

			// calibrate burst peaks
			index = 0;
			for (TimeDependentFunctionContainer tdfc : burstSequence
					.getSequenceFunctionContainers()) {
				if (tdfc.getFunction() != null
						&& tdfc.getFunction() instanceof Burst) {
					Burst burstFunction = (Burst) tdfc.getFunction();
					burstFunction.setPeak(calibrateBurstPeakValue(root,
							burstFunction, container.getBursts().get(index).getArrivalRate()));
					index++;
				}
			}
		}
	}
	
	/**
	 * Get the time-stamps and arrival rates of bursts (meaning major deviations
	 * from the extracted model instance).
	 * @param root The model's root sequence.
	 * @param peakBaselineModel The baseline sequence, without local minima.
	 * 			Previously constructed specifically for burst detection.
	 * @param container The container containing all known extraction data.
	 * @return The arrival rate tuples of the burst peaks.
	 */
	public static ArrayList<ArrivalRateTuple> getBursts(Sequence root,
			Sequence peakBaselineModel, ExtractionDataContainer container) {
		double timeOffset = container.getLocalMins().get(0).getTimeStamp();
		ArrayList<ArrivalRateTuple> burstList = new ArrayList<ArrivalRateTuple>();
		ModelEvaluator currentBaselineModel = new ModelEvaluator(
				peakBaselineModel, 5, IGeneratorConstants.CALIBRATION);

		for (ArrivalRateTuple max : container.getLocalMaxes()) {
			double currentTime = max.getTimeStamp() - timeOffset;
			if (currentTime >= 0
					&& max.getArrivalRate() > currentBaselineModel
					.getArrivalRateAtTime(currentTime)
					* BURSTDETECTIONFACTOR) {
				burstList.add(new ArrivalRateTuple(currentTime, max
						.getArrivalRate()));

				// merge bursts that are two close to one another
				if (burstList.size() > 1) {
					if (burstList.get(burstList.size() - 1).getTimeStamp()
							- burstList.get(burstList.size() - 2)
							.getTimeStamp() < container.getBurstWidth()) {
						if (burstList.get(burstList.size() - 1)
								.getArrivalRate() < burstList.get(
										burstList.size() - 2).getArrivalRate()) {
							burstList.remove(burstList.size() - 1);
						} else {
							burstList.remove(burstList.size() - 2);
						}
					}
				}
			}
		}


		return burstList;
	}
	
	/**
	 * Calculates a static width for all bursts in a trace.
	 * Width is based on the trace metadata in container.
	 * @param container Available extraction data.
	 * @return Standard burst width.
	 */
	public static double calculateBurstWidth(ExtractionDataContainer container) {
		if (container.getPeakNum() > 0.0) {
			return container.getPeriod() / container.getPeakNum();
		} else if (container.getSubContainerList() != null
				&& !container.getSubContainerList().isEmpty()) {
			return calculateBurstWidth(container.getSubContainerList().get(0));
		}
		return container.getPeriod() / 2;
	}
	
	/*
	 * Create the burst with the correct DLIM burst function.
	 */
	private static Burst createBurstWithCorrectShape(String shapeString) {
		try {
			return (Burst) DlimPackage.eINSTANCE.getDlimFactory()
					.create((EClass) (DlimPackage.eINSTANCE
							.getEClassifier(shapeString)));
		} catch (NullPointerException e) {
			return DlimPackage.eINSTANCE.getDlimFactory()
					.createExponentialIncreaseAndDecline();
		}
	}

	private static double calibrateBurstPeakValue(Sequence rootSequence,
			Burst burst, double value) {
		ModelEvaluator evaluator = new ModelEvaluator(rootSequence, 0,
				IGeneratorConstants.CALIBRATION);
		double endValue = value;
		try {
			endValue = Calibrator.calibrateBurstPeakValue(value, burst,
					evaluator);
		} catch (CalibrationException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID,
							"CalibrationException:" + e.getMessage(), e));
		}
		return endValue;
	}
}
