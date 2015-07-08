/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;

import tools.descartes.dlim.Burst;
import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.Constant;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Function;
import tools.descartes.dlim.NormalNoise;
import tools.descartes.dlim.Operator;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.assistant.Calibrator;
import tools.descartes.dlim.extractor.utils.ExtractionDataContainer;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Offers the default model extraction processes as used by the dlim.exporter
 * plugin.
 *
 * @author Joakim von Kistowski
 *
 */
public final class ModelExtractor {

	/**
	 * Detects a burst if its arrival rate is this greater than the
	 * respective (modified) seasonal pattern.
	 */
	private static final double BURSTDETECTIONFACTOR = 1.2;

	/**
	 * The maximum expected amount of seasonal peaks. To be used for noise reduction.
	 */
	private static final int EXPECTEDMAXPEAKSPERSEASONAL = 8;

	/**
	 *  The read list of arrival rates, may be filtered by the gaussian filter.
	 */
	//private static List<ArrivalRateTuple> arrivalRateList = new ArrayList<ArrivalRateTuple>();

	/**
	 *  The original read list of arrival rates, if the gaussian filter was
	 *  applied to the original one.
	 */
	//private static List<ArrivalRateTuple> noisyArrivalRateList = new ArrayList<ArrivalRateTuple>();

	/**
	 * This class is all statics anyways.
	 */
	private ModelExtractor() {

	}

	/**
	 * Extract an arrival Rate file using the simple extraction process.
	 *
	 * @param root the root
	 * @param arrList the read list of arrival rates
	 * @param period the seasonal period
	 * @param seasonalsPerTrend the seasonals per trend (trend segment length)
	 * @param seasonalShape the seasonal shape
	 * @param trendShape the trend shape
	 * @param operatorLiteral the operator literal (how is the trend to be applied to the seasonal part)
	 * @param extractNoise true, if noise is to be reduced and extracted
	 * @throws CalibrationException exception if calibration is ineffective (devision by 0 or unused function)
	 */
	public static void extractArrivalRateFileIntoSequence(Sequence root,
			List<ArrivalRateTuple> arrList, double period,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral, boolean extractNoise)
					throws CalibrationException {
		ExtractionDataContainer container = new ExtractionDataContainer(
				arrList, period, seasonalsPerTrend, seasonalShape, trendShape, operatorLiteral);
		setupArrivalRateLists(arrList, container);
		reduceArrivalRateListNoise(container, extractNoise);
		performMinMaxSearch(container);

		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();

		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// Future Work: get period; use the Fourier approach here?

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
		buildSeasonalPart(root, baseline, container);

		// build trend part
		container.setTrendPointValues(getTrendValues(container));
		
		container.setBurstWidth(container.getPeriod() / container.getPeakNum());
		buildTrendPart(root, container);
		buildTrendPart(baseline, container);

		container.setBursts(getBursts(root, baseline, container));
		buildBurstPart(root, container);

		buildNormalNoisePart(root, container, extractNoise);
	}

	/**
	 * Extract an arrival rate file using the periodic extraction process.
	 *
	 * @param root the root
	 * @param arrList the arr list
	 * @param period the period
	 * @param seasonalsPerTrendList the seasonals per trend list
	 * @param seasonalShape the seasonal shape
	 * @param trendShape the trend shape
	 * @param operatorLiteral the operator literal
	 * @param extractNoise the extract noise
	 * @throws CalibrationException the calibration exception
	 */
	public static void extractSequenceFromArrivalRateFilePeriodic(
			Sequence root, List<ArrivalRateTuple> arrList, double period,
			List<int[]> seasonalsPerTrendList, String seasonalShape,
			String trendShape, String operatorLiteral, boolean extractNoise)
					throws CalibrationException {
		ExtractionDataContainer container = new ExtractionDataContainer(
				arrList, period, 0, seasonalShape, trendShape, operatorLiteral);
		setupArrivalRateLists(arrList, container);
		reduceArrivalRateListNoise(container, extractNoise);
		performMinMaxSearch(container);

		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();


		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// todo: get period; use the Fourier approach here?

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
		buildSeasonalPart(root, baseline, container);

		// build trend part
		root.getCombine().clear();
		for (int[] seasonalsPerTrend : seasonalsPerTrendList) {
			double[] trendPointValues = getMediandValuesForPeriodicTrend(
					container, seasonalsPerTrend);
			buildRepeatingTrend(root, seasonalsPerTrend,
					trendPointValues, container);
			buildRepeatingTrend(baseline,
					seasonalsPerTrend, trendPointValues, container);
		}

		container.setBurstWidth(container.getPeriod() / container.getPeakNum());
		container.setBursts(getBursts(root, baseline, container));
		buildBurstPart(root, container);

		buildNormalNoisePart(root, container, extractNoise);
	}

	/**
	 * Extract HLDLIM parameters from an arrival rate list.
	 *
	 * @param arrivalRateFilePath the arrival rate file path
	 * @param period the period
	 * @param offset the offset
	 * @param seasonalsPerTrend the seasonals per trend
	 * @param seasonalShape the seasonal shape
	 * @param trendShape the trend shape
	 * @param operatorLiteral the operator literal
	 * @param extractNoise the extract noise
	 * @return the HL dlim parameter container
	 * @throws CalibrationException the calibration exception
	 */
	public static HLDlimParameterContainer extractArrivalRateFileIntoParameters(
			String arrivalRateFilePath, double period, double offset,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral, boolean extractNoise)
					throws CalibrationException {
		ExtractionDataContainer container = new ExtractionDataContainer(
				new ArrayList<ArrivalRateTuple>(), period, 0, seasonalShape, trendShape, operatorLiteral);
		HLDlimParameterContainer hlcontainer = new HLDlimParameterContainer();
		hlcontainer.setSeasonalShape(seasonalShape);
		hlcontainer.setTrendShape(trendShape);
		hlcontainer.setOperatorLiteral(operatorLiteral);
		hlcontainer.setSeasonalPeriod(period);
		hlcontainer.setSeasonalsPerTrend(seasonalsPerTrend);
		container.setSeasonalShape(seasonalShape);
		container.setTrendShape(trendShape);
		container.setOperatorLiteral(operatorLiteral);
		container.setPeriod(period);
		container.setSeasonalsPerTrend(seasonalsPerTrend);

		readFile(arrivalRateFilePath, offset, container);
		reduceArrivalRateListNoise(container, extractNoise);
		performMinMaxSearch(container);

		Sequence root = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();

		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// get base level
		container.setBase(getBaseLevel(container));
		hlcontainer.setBase(container.getBase());
		// get peak num
		container.setPeakNum(getPeakNum(container));
		hlcontainer.setPeakNum(container.getPeakNum());
		// get peaks
		container.setPeaks(getPeaks(container));
		hlcontainer.setPeakIntervalWidth(container.getPeaks()[container.getPeaks().length - 1].getTimeStamp()
				- container.getPeaks()[0].getTimeStamp());
		ArrivalRateTuple.setSortByTime(false);
		Arrays.sort(container.getPeaks());
		if (container.getPeaks()[container.getPeaks().length - 1].getTimeStamp()
										< container.getPeaks()[0].getTimeStamp()) {
			hlcontainer.setFirstPeak(container.getPeaks()[container.getPeaks().length - 1].getArrivalRate());
			hlcontainer.setLastPeak(container.getPeaks()[0].getArrivalRate());
		} else {
			hlcontainer.setLastPeak(container.getPeaks()[container.getPeaks().length - 1].getArrivalRate());
			hlcontainer.setFirstPeak(container.getPeaks()[0].getArrivalRate());
		}

		// interpolate new peaks using parameters
		for (int i = 0; i < container.getPeaks().length; i++) {
			container.getPeaks()[i].setTimeStamp((period - hlcontainer.getPeakIntervalWidth())
					/ 2.0
					+ (i * hlcontainer.getPeakIntervalWidth() / container.getPeaks().length));
			container.getPeaks()[i].setArrivalRate(hlcontainer.getFirstPeak() - i
					* (hlcontainer.getFirstPeak() - hlcontainer.getLastPeak())
					/ container.getPeaks().length);
		}

		container.setMaxPeakOffset(getMaxPeakOffset(container.getPeaks()));
		hlcontainer.setTrendOffset(container.getMaxPeakOffset());
		// get inner base (get peak num - 1 inner bases)
		ArrivalRateTuple[] innerBases = getInnerBases(container);
		if (innerBases != null) {
			ArrivalRateTuple.setSortByTime(false);
			Arrays.sort(innerBases);
			double innerBase = innerBases[(innerBases.length - 1) / 2]
					.getArrivalRate();
			hlcontainer.setInnerBase(innerBase);
			for (int i = 0; i < innerBases.length; i++) {
				innerBases[i].setArrivalRate(innerBase);
			}
		}
		container.setInnerBases(innerBases);

		// build seasonal part
		buildSeasonalPart(root, baseline, container);

		// build trend part
		container.setTrendPointValues(getTrendValues(container));
		hlcontainer.setTrendPoints(container.getTrendPointValues());
		buildTrendPart(root, container);
		buildTrendPart(baseline, container);

		container.setBurstWidth(container.getPeriod() / container.getPeakNum());
		container.setBursts(getBursts(root, baseline, container));
		hlcontainer.setBurstOffset(12.0);
		hlcontainer.setBurstPeriod(48.0);
		if (container.getBursts().size() > 0) {
			ModelEvaluator evaluator = new ModelEvaluator(root, 0,
					IGeneratorConstants.CALIBRATION);
			hlcontainer.setBurstOffset(container.getBursts().get(0).getTimeStamp());
			hlcontainer.setBurstWidth(container.getBurstWidth());
			hlcontainer.setBurstPeriod(container.getDuration());
			hlcontainer
			.setBurstPeak(container.getBursts().get(0).getArrivalRate()
					- evaluator.getArrivalRateAtTime(hlcontainer
							.getBurstOffset()));
			if (container.getBursts().size() > 1) {
				// get median inter-burst period
				double[] interBurstTimes = new double[container.getBursts().size() - 1];
				for (int i = 0; i < interBurstTimes.length; i++) {
					interBurstTimes[i] = container.getBursts().get(i + 1).getTimeStamp()
							- container.getBursts().get(i).getTimeStamp();
				}
				Arrays.sort(interBurstTimes);
				hlcontainer.setBurstPeriod(getMedian(interBurstTimes));
			}
		}

		// buildBurstPart(root, period/peakNum, duration, bursts);
		setUniformNoisePart(hlcontainer, container, extractNoise);

		return hlcontainer;
	}

	/*
	 * Read the arrival rate file. Do not use, when extending LIMBO. Use only
	 * for HLDLIM paramter extraction.
	 */
	private static void readFile(String arrivalRateFilePath, double offset,
			ExtractionDataContainer container) {
		container.getArrivalRateList().clear();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					arrivalRateFilePath));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.substring(0, line.length() - 1);
				String[] numbers = line.split(",");
				if (numbers.length >= 2) {
					double timeStamp = Double.parseDouble(numbers[0].trim());
					double readArrivalRate = Double.parseDouble(numbers[1]
							.trim());
					if (timeStamp - offset > 0) {
						container.getArrivalRateList().add(new ArrivalRateTuple(timeStamp
								- offset, readArrivalRate));
					}
				}
			}

			br.close();

			container.getNoisyArrivalRateList().clear();
			for (ArrivalRateTuple t : container.getArrivalRateList()) {
				container.getNoisyArrivalRateList().add(new ArrivalRateTuple(t.getTimeStamp(),
						t.getArrivalRate()));
			}
		} catch (IOException e) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID,
							"Arrival Rate File does not exist.", e));
		}

	}

	/*
	 * Searches for all local minima and maxima and stores them in the
	 * respective lists.
	 */
	private static void performMinMaxSearch(ExtractionDataContainer container) {
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

	// returns the median of all local minimums that define the end of each
	// seasonal part
	private static double getBaseLevel(ExtractionDataContainer container) {
		double[] bases = new double[(int) (container.getDuration() / container.getPeriod())];
		double currentTime = container.getLocalMins().get(0).getTimeStamp();
		for (int i = 0; i < bases.length; i++) {
			bases[i] = getArrivalRate(currentTime, container.getLocalMins(), false);
			currentTime += container.getPeriod();
		}
		Arrays.sort(bases);
		return getMedian(bases);
	}

	// gets the amount of peaks within each seasonal part
	private static int getPeakNum(ExtractionDataContainer container) {
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

	private static ArrivalRateTuple[] getPeaks(ExtractionDataContainer container) throws CalibrationException {
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
					getMedianTimeStamp(arrivalRateTuplesPerPeak[i]), 0);
		}

		ArrivalRateTuple.setSortByTime(false);
		for (int i = 0; i < container.getPeakNum(); i++) {
			Arrays.sort(arrivalRateTuplesPerPeak[i]);
			peaks[i].setArrivalRate(getMedianArrivalRate(arrivalRateTuplesPerPeak[i]));
		}

		// This sort should not be necessary, it only helps to prevent errors in
		// cases
		// in which this extraction method is pretty terrible anyways
		ArrivalRateTuple.setSortByTime(true);
		Arrays.sort(peaks);

		return peaks;
	}

	// gets the inner bases by nearest neighbour sampling within the local mins
	private static ArrivalRateTuple[] getInnerBases(ExtractionDataContainer container) {
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
				ArrivalRateTuple tmpTuple = getNearestArrivalRateTuple(
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

	/*
	 * Get the arrival rate at time-stamp time. Use either linear interpolation
	 * or nearest neighbor interpolation for undefined time stamps.
	 */
	private static double getArrivalRate(double time,
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

	/*
	 * Get the nearest neighbor arrival rate tuple to time-stamp time.
	 */
	private static ArrivalRateTuple getNearestArrivalRateTuple(double time,
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

	/**
	 * Returns the median value for a sorted double array.
	 *
	 * @param array
	 *            Must be sorted.
	 * @return
	 */
	private static double getMedian(double[] array) {
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
	 * @return
	 */
	private static double getMedianTimeStamp(ArrivalRateTuple[] array) {
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
	 * @return
	 */
	private static double getMedianArrivalRate(ArrivalRateTuple[] array) {
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length - 1) / 2].getArrivalRate() + array[(array.length) / 2]
					.getArrivalRate()) / 2.0;
		}
		return array[(array.length - 1) / 2].getArrivalRate();
	}

	/*
	 * Build the seasonal part of the DLIM instance.
	 */
	private static void buildSeasonalPart(Sequence root, Sequence baseline,
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
			Trend currentTrend = createTrendWithCorrectShape(container.getSeasonalShape());
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

	/*
	 * Create the Trend with the correct implementation.
	 */
	private static Trend createTrendWithCorrectShape(String shapeString) {
		try {
			return (Trend) DlimPackage.eINSTANCE.getDlimFactory()
					.create((EClass) (DlimPackage.eINSTANCE
							.getEClassifier(shapeString)));
		} catch (NullPointerException e) {
			return DlimPackage.eINSTANCE.getDlimFactory().createSinTrend();
		}
	}

	/*
	 * Returns the offset of the maximum peak within the seasonal iteration.
	 */
	private static double getMaxPeakOffset(ArrivalRateTuple[] peaks) {
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

	/*
	 * Get the list of arrival rates at the maximum seasonal peaks. This is
	 * needed for building the trend part. See HLDLIM trend part definition for
	 * details.
	 */
	private static double[] getTrendValues(ExtractionDataContainer container) {
		if (container.getSeasonalsPerTrend() <= 0) {
			return null;
		}
		// the extractor always starts at the first local minimum
		// we have to take this into account when sampling from read values;
		double extractionOffset = container.getLocalMins().get(0).getTimeStamp();
		int trendPointNum = (int) (container.getDuration() / container.getPeriod()) / container.getSeasonalsPerTrend();
		double[] trendValues = new double[trendPointNum];
		for (int i = 0; i < trendPointNum; i++) {
			trendValues[i] = getArrivalRate(i * container.getPeriod() * container.getSeasonalsPerTrend()
					+ container.getMaxPeakOffset() + extractionOffset, container.getLocalMaxes(), false);
		}
		return trendValues;
	}

	/*
	 * Get the median trend values (see getTrendValues) for repeating periodic
	 * trends.
	 */
	private static double[] getMediandValuesForPeriodicTrend(ExtractionDataContainer container,
			int[] seasonalsPerTrend) {
		if (seasonalsPerTrend.length <= 0) {
			return null;
		}

		double trendDuration = 0;
		for (int seasonals : seasonalsPerTrend) {
			trendDuration += seasonals;
		}
		trendDuration *= container.getPeriod();

		// the extractor always starts at the first local minimum
		// we have to take this into account when sampling from read values;
		double extractionOffset = container.getLocalMins().get(0).getTimeStamp();
		double[] trendValues = new double[seasonalsPerTrend.length + 1];
		int currentSeasonalAmount = 0;
		for (int i = 0; i < seasonalsPerTrend.length; i++) {
			trendValues[i] = getArrivalRate(container.getPeriod() * currentSeasonalAmount
					+ container.getMaxPeakOffset() + extractionOffset, container.getLocalMaxes(), false);

			// get Median arrival rate for trend point
			int trendPeriods =
					(int) ((container.getDuration() - container.getMaxPeakOffset() - extractionOffset) / trendDuration);
			double[] arrivalRatesAtPoint = new double[trendPeriods];
			for (int j = 0; j < trendPeriods; j++) {
				arrivalRatesAtPoint[j] = getArrivalRate(j * trendDuration
						+ container.getPeriod() * currentSeasonalAmount + container.getMaxPeakOffset()
						+ extractionOffset, container.getLocalMaxes(), false);
			}
			trendValues[i] = getMedian(arrivalRatesAtPoint);

			currentSeasonalAmount += seasonalsPerTrend[i];
		}
		trendValues[trendValues.length - 1] = trendValues[0];
		return trendValues;
	}

	/*
	 * Build the Trend part within the DLIM instance.
	 */
	private static void buildTrendPart(Sequence root, ExtractionDataContainer container) {
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
			offsetElement.setDuration(container.getMaxPeakOffset());
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
			holdElement.setDuration(container.getDuration() - container.getMaxPeakOffset() - trendDuration
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
					trend.setFunctionOutputAtStart(calibrateTrendStartValue(
							root, trend, container.getTrendPointValues()[i]));
					i++;
					trend.setFunctionOutputAtEnd(calibrateTrendEndValue(root,
							trend, container.getTrendPointValues()[i]));
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

	/*
	 * Build the Trend part for the periodic extraction process.
	 */
	private static void buildRepeatingTrend(Sequence root,
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

	/*
	 * Get the time-stamps and arrival rates of bursts (meaning major deviations
	 * from the extracted model instance).
	 */
	private static ArrayList<ArrivalRateTuple> getBursts(Sequence root,
			Sequence peakBaselineModel, ExtractionDataContainer container) {
		double burstWidth = container.getPeriod() / container.getPeakNum();
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
							.getTimeStamp() < burstWidth) {
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

	/*
	 * Build the burst part.
	 */
	private static void buildBurstPart(Sequence root, ExtractionDataContainer container) {
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

	/*
	 * Create a gaussian filter with a given kernel width.
	 */
	private static double[] createGaussianFilter(int width) {
		int filterWidth = width;
		if (filterWidth % 2 == 0) {
			filterWidth++;
		}
		filterWidth = Math.max(1, filterWidth);
		double[] filter = new double[filterWidth];
		double sigma = Math.sqrt((filterWidth * filterWidth - 1.0) / 12.0);
		int mean = filterWidth / 2;
		double filterSum = 0.0;
		Gaussian gaussian = new Gaussian(mean, sigma);
		for (int i = 0; i < filterWidth; i++) {
			filter[i] = gaussian.value(i);
			filterSum += filter[i];
		}

		// normalize to 1
		for (int i = 0; i < filterWidth; i++) {
			filter[i] = filter[i] / filterSum;
		}

		return filter;
	}

	/*
	 * Reduce noise within the read arrival rate list by applying a gaussian
	 * filter.
	 */
	private static void reduceArrivalRateListNoise(ExtractionDataContainer container,
			boolean extractNoise) {
		if (!extractNoise) {
			return;
		}
		double[] filter = createGaussianFilter((int) (container.getPeriod() / EXPECTEDMAXPEAKSPERSEASONAL));

		double[] arrivalRates = new double[container.getArrivalRateList().size()];
		int index = 0;
		for (ArrivalRateTuple t : container.getArrivalRateList()) {
			arrivalRates[index] = t.getArrivalRate();
			index++;
		}
		index = 0;
		for (ArrivalRateTuple t : container.getArrivalRateList()) {
			t.setArrivalRate(getFilteredValueAtIndex(arrivalRates, index,
					filter));
			index++;
		}
	}

	/*
	 * Apply gaussian filter to arrival rate at index index.
	 */
	private static double getFilteredValueAtIndex(double[] arrivalRateArray,
			int index, double[] filter) {
		int filterCenter = filter.length / 2;

		double filteredValue = 0.0;
		for (int i = 0; i < filter.length; i++) {
			filteredValue += filter[i]
					* getArrivalRateFromArray(arrivalRateArray, index
							+ (i - filterCenter));
		}
		return filteredValue;
	}

	/*
	 * Comfort function. Returns 0 for out of bound array indices.
	 */
	private static double getArrivalRateFromArray(double[] array, int index) {
		if (index < 0 || index >= array.length) {
			return 0.0;
		}
		return array[index];
	}

	/*
	 * Extracts a normal distributed noise by calculating the difference between
	 * the filtered and unfiltered arrival rate lists.
	 */
	private static void buildNormalNoisePart(Sequence root, ExtractionDataContainer container, boolean extractNoise) {
		if (!extractNoise) {
			return;
		}
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		double[] noiseDiffs = new double[container.getArrivalRateList().size()];
		int index = 0;
		double sum = 0.0;
		for (ArrivalRateTuple t : container.getArrivalRateList()) {
			noiseDiffs[index] = -t.getArrivalRate()
					+ container.getNoisyArrivalRateList().get(index).getArrivalRate();
			sum += noiseDiffs[index];
			index++;
		}
		double mean = sum / container.getArrivalRateList().size();
		double standardDeviation = 0.0;
		for (int i = 0; i < noiseDiffs.length; i++) {
			standardDeviation += (noiseDiffs[i] - mean)
					* (noiseDiffs[i] - mean);
		}
		standardDeviation = Math.sqrt(standardDeviation / noiseDiffs.length);

		Combinator noiseCombinator = factory.createCombinator();
		NormalNoise noise = factory.createNormalNoise();
		noise.setMean(mean);
		noise.setStandardDeviation(standardDeviation);
		noiseCombinator.setFunction(noise);
		root.getCombine().add(noiseCombinator);
	}

	/*
	 * Extracts a uniformly distributed noise by calculating the difference
	 * between the filtered and unfiltered arrival rate lists. Sets the min and
	 * max at the 10 and 90th percentile.
	 */
	private static void setUniformNoisePart(HLDlimParameterContainer hlcontainer,
			ExtractionDataContainer exdcontainer, boolean extractNoise) {
		if (!extractNoise) {
			return;
		}
		double[] noiseDiffs = new double[exdcontainer.getArrivalRateList().size()];
		int index = 0;
		for (ArrivalRateTuple t : exdcontainer.getArrivalRateList()) {
			noiseDiffs[index] = -t.getArrivalRate()
					+ exdcontainer.getNoisyArrivalRateList().get(index).getArrivalRate();
			index++;
		}
		Arrays.sort(noiseDiffs);
		hlcontainer.setNoiseMin(noiseDiffs[(int) (noiseDiffs.length * 0.1)]);
		hlcontainer.setNoiseMax(noiseDiffs[(int) (noiseDiffs.length * 0.9)]);
	}

	/*
	 * Setup the original and noisy (backup) arrival rate lists.
	 */
	private static void setupArrivalRateLists(final List<ArrivalRateTuple> arrList, ExtractionDataContainer container) {
		container.setArrivalRateList(arrList);
		container.getNoisyArrivalRateList().clear();
		for (ArrivalRateTuple t : container.getArrivalRateList()) {
			container.getNoisyArrivalRateList().add(new ArrivalRateTuple(t.getTimeStamp(), t
					.getArrivalRate()));
		}
	}
}
