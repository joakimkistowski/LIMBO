/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tools.descartes.dlim.Constant;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Utility class containing functionality needed for the extraction of
 * basic seasonal patterns.
 * 
 * @author Joakim von Kistowski
 *
 */
public final class BasicSeasonalExtractionUtilities {

	/**
	 * Private Extractor to prevent instantiation.
	 */
	private BasicSeasonalExtractionUtilities() {
	}
	
	/**
	 * Extracts a single seasonal part for the arrival rate list in the given container.
	 * MinMaxSearch must have been performed before calling this.
	 * @param root The root sequence to extract the seasonal part into.
	 * @param baseline The baseline sequence, for burst detection.
	 * @param container Currently available extraction data, including the arrival rate list.
	 * 			Local mins and local maxes must be set.
	 * @throws CalibrationException An exeption if calibration goes wrong.
	 */
	public static void extractSeasonalPart(Sequence root, Sequence baseline,
			ExtractionDataContainer container) throws CalibrationException {
		// get base level
		container.setBase(getBaseLevel(container));
		// get peak num
		container.setPeakNum(getPeakNum(container));
		// get peaks
		container.setPeaks(getPeaks(container));

		container.setMaxPeakOffset(getMaxPeakOffset(container.getPeaks()));
		// get inner base (get peak num - 1 inner bases)
		container.setInnerBases(getInnerBases(container));

		// build seasonal part
		BasicSeasonalExtractionUtilities.buildSeasonalPart(root, baseline, container);
	}
	
	/**
	 * Build the seasonal part of the DLIM instance.
	 * @param root The root sequence.
	 * @param baseline Separate sequence, later used for burst detection.
	 * @param container Extraction data container with all extraction data.
	 */
	public static void buildSeasonalPart(Sequence root, Sequence baseline,
			ExtractionDataContainer container) {
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		root.getSequenceFunctionContainers().clear();
		root.getCombine().clear();
		root.setTerminateAfterTime(container.getDuration());
		root.setTerminateAfterLoops(-1);

		ArrivalRateTuple[] interpolationPoints = new ArrivalRateTuple[container.getPeaks().length * 2 + 1];
		interpolationPoints[0] = new ArrivalRateTuple(0.0, container.getBase());
		interpolationPoints[interpolationPoints.length - 1] = new ArrivalRateTuple(
				container.getPeriod(), container.getBase());
		for (int i = 0; i < container.getPeaks().length; i++) {
			interpolationPoints[i * 2 + 1] = container.getPeaks()[i];
		}
		if (container.getInnerBases() != null) {
			for (int i = 0; i < container.getInnerBases().length; i++) {
				interpolationPoints[i * 2 + 2] = container.getInnerBases()[i];
			}
		}

		for (int i = 1; i < interpolationPoints.length; i++) {
			TimeDependentFunctionContainer currentElement = factory
					.createTimeDependentFunctionContainer();
			currentElement.setName("seasonal" + i);
			currentElement.setDuration(interpolationPoints[i].getTimeStamp()
					- interpolationPoints[i - 1].getTimeStamp());
			Trend currentTrend =
					BasicTrendExtractionUtilities.createTrendWithCorrectShape(container.getSeasonalShape());
			currentTrend.setFunctionOutputAtStart(interpolationPoints[i - 1]
					.getArrivalRate());
			currentTrend.setFunctionOutputAtEnd(interpolationPoints[i]
					.getArrivalRate());
			currentElement.setFunction(currentTrend);
			root.getSequenceFunctionContainers().add(currentElement);
		}

		// baseline
		baseline.getSequenceFunctionContainers().clear();
		baseline.getCombine().clear();
		baseline.setTerminateAfterTime(container.getDuration());
		baseline.setTerminateAfterLoops(-1);
		double maxPeak = 0.0;
		for (int i = 0; i < container.getPeaks().length; i++) {
			if (container.getPeaks()[i].getArrivalRate() > maxPeak) {
				maxPeak = container.getPeaks()[i].getArrivalRate();
			}
		}
		TimeDependentFunctionContainer constantContainer = factory
				.createTimeDependentFunctionContainer();
		constantContainer.setName("constant");
		constantContainer.setDuration(container.getPeriod());
		baseline.getSequenceFunctionContainers().add(constantContainer);
		Constant constant = factory.createConstant();
		constant.setConstant(maxPeak);
		constantContainer.setFunction(constant);
	}
	
	/**
	 * Searches for all local minima and maxima and stores them in the
	 * respective lists.
	 * @param container The extraction data container.
	 */
	public static void performMinMaxSearch(ExtractionDataContainer container) {
		boolean descending = true;

		container.getLocalMaxes().clear();
		container.getLocalMins().clear();

		if (container.getArrivalRateList().get(1).getArrivalRate() > container.getArrivalRateList().get(0)
				.getArrivalRate()) {
			descending = false;
			container.getLocalMins().add(container.getArrivalRateList().get(0));
		} else {
			descending = true;
			container.getLocalMaxes().add(container.getArrivalRateList().get(0));
		}

		ArrivalRateTuple lastT = container.getArrivalRateList().get(0);

		for (ArrivalRateTuple t : container.getArrivalRateList()) {
			if (descending && (t.getArrivalRate() > lastT.getArrivalRate())) {
				container.getLocalMins().add(lastT);
				descending = false;
			} else if (!descending
					&& (t.getArrivalRate() < lastT.getArrivalRate())) {
				container.getLocalMaxes().add(lastT);
				descending = true;
			}
			lastT = t;
		}
	}
	
	/**
	 * Returns the offset of the maximum peak within the seasonal iteration.
	 * @param peaks The seasonal peaks as determined by {@link #getPeaks(ExtractionDataContainer) getPeaks}
	 * @return The maximum peaks time offset within the seasonal iteration.
	 */
	public static double getMaxPeakOffset(ArrivalRateTuple[] peaks) {
		int maxPeakIndex = 0;
		double currentMax = 0;
		for (int i = 0; i < peaks.length; i++) {
			if (peaks[i].getArrivalRate() > currentMax) {
				maxPeakIndex = i;
				currentMax = peaks[i].getArrivalRate();
			}
		}
		return peaks[maxPeakIndex].getTimeStamp();
	}
	
	/**
	 * Returns the median of all local minimums that define the end of each seasonal iteration.
	 * @param container The provided ExtractionDataContainer, must contain local mins and maxes,
	 * 			duration, period, and the arrival rate list.
	 * @return The median arrival rate at the beginning/end of the seasonal iterations.
	 */
	public static double getBaseLevel(ExtractionDataContainer container) {
		double[] bases = new double[(int) (container.getDuration() / container.getPeriod())];
		double currentTime = container.getLocalMins().get(0).getTimeStamp();
		for (int i = 0; i < bases.length; i++) {
			bases[i] = BasicExtractionUtils.getArrivalRate(currentTime, container.getLocalMins(), false);
			currentTime += container.getPeriod();
		}
		Arrays.sort(bases);
		return BasicExtractionUtils.getMedian(bases);
	}
	
	/**
	 * Gets median the amount of peaks within the seasonal iterations.
	 * @param container The provided ExtractionDataContainer, must contain local mins and maxes,
	 * 			duration, period, and the arrival rate list.
	 * @return The median amount of seasonal peaks per iteration.
	 */
	public static int getPeakNum(ExtractionDataContainer container) {
		int[] peakNums = new int[(int) (container.getDuration() / container.getPeriod())];
		double currentSeasonalBegin = container.getLocalMins().get(0).getTimeStamp();
		int index = 0;
		for (ArrivalRateTuple t : container.getLocalMaxes()) {
			while (t.getTimeStamp() > currentSeasonalBegin + container.getPeriod()) {
				index++;
				currentSeasonalBegin += container.getPeriod();
			}
			if (index >= peakNums.length) {
				break;
			}

			peakNums[index]++;
		}
		Arrays.sort(peakNums);
		return peakNums[(peakNums.length - 1) / 2];
	}
	
	/**
	 *  Gets the inner bases by nearest neighbor sampling within the local mins.
	 * @param container The provided ExtractionDataContainer, must contain local mins and maxes,
	 * 			duration, period, and the arrival rate list.
	 * @return The median inner bases as arrival rate tuples relative to the start of each seasonal iteration.
	 */
	public static ArrivalRateTuple[] getInnerBases(ExtractionDataContainer container) {
		if (container.getPeaks().length <= 1) {
			return null;
		}
		int seasonals = (int) (container.getDuration() / container.getPeriod());
		int innerBaseCount = container.getPeaks().length - 1;
		ArrivalRateTuple[][] innerBasesPerPeak = new ArrivalRateTuple[innerBaseCount][seasonals];

		for (int i = 0; i < innerBaseCount; i++) {
			double currentSeasonalBegin = container.getLocalMins().get(0).getTimeStamp();
			double samplingTime = (container.getPeaks()[i].getTimeStamp() + container.getPeaks()[i + 1]
					.getTimeStamp()) / 2;
			for (int j = 0; j < seasonals; j++) {
				ArrivalRateTuple tmpTuple = BasicExtractionUtils.getNearestArrivalRateTuple(
						samplingTime + currentSeasonalBegin, container.getLocalMins());
				innerBasesPerPeak[i][j] = new ArrivalRateTuple(
						tmpTuple.getTimeStamp() - currentSeasonalBegin,
						tmpTuple.getArrivalRate());
				currentSeasonalBegin += container.getPeriod();
			}
		}

		ArrivalRateTuple[] innerBases = new ArrivalRateTuple[innerBaseCount];
		for (int i = 0; i < innerBaseCount; i++) {
			// sort inner bases by time to get the median timeStamp
			ArrivalRateTuple.setSortByTime(true);
			Arrays.sort(innerBasesPerPeak[i]);
			double timeStamp = innerBasesPerPeak[i][(innerBasesPerPeak[i].length - 1) / 2]
					.getTimeStamp();
			// if median timeStamp is not bordered by its peaks
			if (!(container.getPeaks()[i].getTimeStamp() < timeStamp && timeStamp < container.getPeaks()[i + 1]
					.getTimeStamp())) {
				timeStamp = (container.getPeaks()[i].getTimeStamp() + container.getPeaks()[i + 1]
						.getTimeStamp()) / 2.0;
			}

			// sort by arrival rate to get median arrival rate
			ArrivalRateTuple.setSortByTime(false);
			Arrays.sort(innerBasesPerPeak[i]);
			double arrivalRate = innerBasesPerPeak[i][(innerBasesPerPeak[i].length - 1) / 2]
					.getArrivalRate();
			innerBases[i] = new ArrivalRateTuple(timeStamp, arrivalRate);
		}

		return innerBases;
	}
	
	/**
	 * Derives the median seasonal peaks from the local mins and maxes in th
	 * provided ExtractionDataContainer.
	 * @param container The provided ExtractionDataContainer, must contain local mins and maxes,
	 * 			duration, period, and the arrival rate list.
	 * @return The median peaks as arrival rate tuples relative to the start of each seasonal iteration.
	 * @throws CalibrationException An exception if the seasonal pattern doesn't influence the final model output.
	 */
	public static ArrivalRateTuple[] getPeaks(ExtractionDataContainer container) throws CalibrationException {
		int seasonals = (int) (container.getDuration() / container.getPeriod());
		@SuppressWarnings("unchecked")
		List<ArrivalRateTuple>[] peaksPerSeasonal = new ArrayList[seasonals];
		// initialize peaksPerSeasonal
		for (int i = 0; i < seasonals; i++) {
			peaksPerSeasonal[i] = new ArrayList<ArrivalRateTuple>();
		}

		double currentSeasonalBegin = container.getLocalMins().get(0).getTimeStamp();
		int index = 0;
		for (ArrivalRateTuple t : container.getLocalMaxes()) {
			while (t.getTimeStamp() > currentSeasonalBegin + container.getPeriod()) {
				index++;
				currentSeasonalBegin += container.getPeriod();
			}
			if (index >= seasonals) {
				break;
			}

			peaksPerSeasonal[index].add(new ArrivalRateTuple(t.getTimeStamp()
					- currentSeasonalBegin, t.getArrivalRate()));
		}

		// sort peaks by time, this way I can get the 1st, the 2nd,... the nth
		// peak per seasonal
		ArrivalRateTuple.setSortByTime(true);
		for (int i = 0; i < seasonals; i++) {
			Collections.sort(peaksPerSeasonal[i]);
		}

		ArrivalRateTuple[][] arrivalRateTuplesPerPeak = new ArrivalRateTuple[container.getPeakNum()][seasonals];

		// since not all read seasonals have exactly a peakNum amount of peaks,
		// the peaks are split into their first and second half, this ensures
		// that at least
		// the last and first peak are consistent
		for (int i = 0; i <= container.getPeakNum() / 2; i++) {
			for (int j = 0; j < seasonals; j++) {
				try {
					arrivalRateTuplesPerPeak[i][j] = peaksPerSeasonal[j].get(i);
				} catch (IndexOutOfBoundsException e) {
					try {
						arrivalRateTuplesPerPeak[i][j] = new ArrivalRateTuple(
								0, 0);
					} catch (IndexOutOfBoundsException e2) {
						throw new CalibrationException(
								"Seasonal Period is too small.");
					}

				}
			}
		}
		for (int i = container.getPeakNum() - 1; i > container.getPeakNum() / 2; i--) {
			for (int j = 0; j < seasonals; j++) {
				try {
					arrivalRateTuplesPerPeak[i][j] = peaksPerSeasonal[j]
							.get(peaksPerSeasonal[j].size() - (container.getPeakNum() - i));
				} catch (IndexOutOfBoundsException e) {
					arrivalRateTuplesPerPeak[i][j] = new ArrivalRateTuple(0, 0);
				}
			}
		}

		// We have now sorted all peaks according to their place within the
		// abstract seasonal.
		// We must now calculate the median timeStamps and arrival rate for each
		// peak

		// First, calculate median time stamps
		ArrivalRateTuple[] peaks = new ArrivalRateTuple[container.getPeakNum()];
		ArrivalRateTuple.setSortByTime(true);
		for (int i = 0; i < container.getPeakNum(); i++) {
			Arrays.sort(arrivalRateTuplesPerPeak[i]);
			peaks[i] = new ArrivalRateTuple(
					BasicExtractionUtils.getMedianTimeStamp(arrivalRateTuplesPerPeak[i]), 0);
		}

		ArrivalRateTuple.setSortByTime(false);
		for (int i = 0; i < container.getPeakNum(); i++) {
			Arrays.sort(arrivalRateTuplesPerPeak[i]);
			peaks[i].setArrivalRate(BasicExtractionUtils.getMedianArrivalRate(arrivalRateTuplesPerPeak[i]));
		}

		// This sort should not be necessary, it only helps to prevent errors in
		// cases
		// in which this extraction method is pretty terrible anyways
		ArrivalRateTuple.setSortByTime(true);
		Arrays.sort(peaks);

		return peaks;
	}
}
