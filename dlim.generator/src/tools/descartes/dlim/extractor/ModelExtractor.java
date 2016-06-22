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
import java.util.List;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.eclipse.core.runtime.Status;

import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.NormalNoise;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.extractor.utils.BasicBurstExtractionUtilities;
import tools.descartes.dlim.extractor.utils.BasicExtractionUtils;
import tools.descartes.dlim.extractor.utils.BasicSeasonalExtractionUtilities;
import tools.descartes.dlim.extractor.utils.BasicTrendExtractionUtilities;
import tools.descartes.dlim.extractor.utils.BinarySeasonalSplitter;
import tools.descartes.dlim.extractor.utils.ExtractionDataContainer;
import tools.descartes.dlim.extractor.utils.RegressiveTrendExtractor;
import tools.descartes.dlim.extractor.utils.TimeDistanceSplittingHeuristic;
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
	public static void extractArrivalRateFileIntoSequenceBinarySplits(Sequence root,
			List<ArrivalRateTuple> arrList, double period,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral, boolean extractNoise)
					throws CalibrationException {
		
		ExtractionDataContainer container = new ExtractionDataContainer(
				arrList, period, seasonalsPerTrend, seasonalShape, trendShape, operatorLiteral);
		//Reset splitting counts for pretty names!
		BinarySeasonalSplitter.resetBinarySplittingCount();
		BinarySeasonalSplitter.setGlobalContainer(container);
		
		setupArrivalRateLists(arrList, container);
		reduceArrivalRateListNoise(container, extractNoise);
		BasicSeasonalExtractionUtilities.performMinMaxSearch(container);

		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();

		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// Future Work: get period; use the Fourier approach here?
		
		clearRootAndBaseline(root, baseline);
		//extract seasonal part using splitter
		BinarySeasonalSplitter splitter = new BinarySeasonalSplitter(container);
		splitter.appendSplitsToRoot(root, baseline, new TimeDistanceSplittingHeuristic());
		
		// build trend part
		container.setTrendPointValues(RegressiveTrendExtractor.getTrendEndPointsUsingRegression(container));
		
		container.setBurstWidth(BasicBurstExtractionUtilities.calculateBurstWidth(container));
		RegressiveTrendExtractor.buildTrendPart(root, container);
		RegressiveTrendExtractor.buildTrendPart(baseline, container);

		container.setBursts(BasicBurstExtractionUtilities.getBursts(root, baseline, container));
		BasicBurstExtractionUtilities.buildBurstPart(root, container);

		buildNormalNoisePart(root, container, extractNoise);
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
	public static void extractArrivalRateFileIntoSequenceNoSplits(Sequence root,
			List<ArrivalRateTuple> arrList, double period,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral, boolean extractNoise)
					throws CalibrationException {
		ExtractionDataContainer container = new ExtractionDataContainer(
				arrList, period, seasonalsPerTrend, seasonalShape, trendShape, operatorLiteral);
		setupArrivalRateLists(arrList, container);
		reduceArrivalRateListNoise(container, extractNoise);
		BasicSeasonalExtractionUtilities.performMinMaxSearch(container);

		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();

		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// Future Work: get period; use the Fourier approach here?

		// build seasonal part
		BasicSeasonalExtractionUtilities.extractSeasonalPart(root, baseline, container);

		// build trend part
		container.setTrendPointValues(getTrendValues(container));
		
		container.setBurstWidth(container.getPeriod() / container.getPeakNum());
		BasicTrendExtractionUtilities.buildTrendPart(root, container, container.getMaxPeakOffset(), true);
		BasicTrendExtractionUtilities.buildTrendPart(baseline, container, container.getMaxPeakOffset(), true);

		container.setBursts(BasicBurstExtractionUtilities.getBursts(root, baseline, container));
		BasicBurstExtractionUtilities.buildBurstPart(root, container);

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
		BasicSeasonalExtractionUtilities.performMinMaxSearch(container);

		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();


		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// todo: get period; use the Fourier approach here?

		// build seasonal part
		BasicSeasonalExtractionUtilities.extractSeasonalPart(root, baseline, container);

		// build trend part
		root.getCombine().clear();
		for (int[] seasonalsPerTrend : seasonalsPerTrendList) {
			double[] trendPointValues = getMediandValuesForPeriodicTrend(
					container, seasonalsPerTrend);
			BasicTrendExtractionUtilities.buildRepeatingTrend(root, seasonalsPerTrend,
					trendPointValues, container);
			BasicTrendExtractionUtilities.buildRepeatingTrend(baseline,
					seasonalsPerTrend, trendPointValues, container);
		}

		container.setBurstWidth(container.getPeriod() / container.getPeakNum());
		container.setBursts(BasicBurstExtractionUtilities.getBursts(root, baseline, container));
		BasicBurstExtractionUtilities.buildBurstPart(root, container);

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
		BasicSeasonalExtractionUtilities.performMinMaxSearch(container);

		Sequence root = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory()
				.createSequence();

		double duration = 0.0;
		duration = container.getArrivalRateList().get(container.getArrivalRateList().size() - 1)
				.getTimeStamp();
		container.setDuration(duration);

		// get base level
		container.setBase(BasicSeasonalExtractionUtilities.getBaseLevel(container));
		hlcontainer.setBase(container.getBase());
		// get peak num
		container.setPeakNum(BasicSeasonalExtractionUtilities.getPeakNum(container));
		hlcontainer.setPeakNum(container.getPeakNum());
		// get peaks
		container.setPeaks(BasicSeasonalExtractionUtilities.getPeaks(container));
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

		container.setMaxPeakOffset(BasicSeasonalExtractionUtilities.getMaxPeakOffset(container.getPeaks()));
		hlcontainer.setTrendOffset(container.getMaxPeakOffset());
		// get inner base (get peak num - 1 inner bases)
		ArrivalRateTuple[] innerBases = BasicSeasonalExtractionUtilities.getInnerBases(container);
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
		BasicSeasonalExtractionUtilities.buildSeasonalPart(root, baseline, container);

		// build trend part
		container.setTrendPointValues(getTrendValues(container));
		hlcontainer.setTrendPoints(container.getTrendPointValues());
		BasicTrendExtractionUtilities.buildTrendPart(root, container, container.getMaxPeakOffset(), true);
		BasicTrendExtractionUtilities.buildTrendPart(baseline, container, container.getMaxPeakOffset(), true);

		container.setBurstWidth(container.getPeriod() / container.getPeakNum());
		container.setBursts(BasicBurstExtractionUtilities.getBursts(root, baseline, container));
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
				hlcontainer.setBurstPeriod(BasicExtractionUtils.getMedian(interBurstTimes));
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

	/**
	 * Clears root and baseline of pre-existing elements.
	 */
	private static void clearRootAndBaseline(Sequence root, Sequence baseline) {
		root.getSequenceFunctionContainers().clear();
		root.getCombine().clear();
		baseline.getSequenceFunctionContainers().clear();
		baseline.getCombine().clear();
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
			trendValues[i] = BasicExtractionUtils.getArrivalRate(
					i * container.getPeriod() * container.getSeasonalsPerTrend()
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
			trendValues[i] = BasicExtractionUtils.getArrivalRate(container.getPeriod() * currentSeasonalAmount
					+ container.getMaxPeakOffset() + extractionOffset, container.getLocalMaxes(), false);

			// get Median arrival rate for trend point
			int trendPeriods =
					(int) ((container.getDuration() - container.getMaxPeakOffset() - extractionOffset) / trendDuration);
			double[] arrivalRatesAtPoint = new double[trendPeriods];
			for (int j = 0; j < trendPeriods; j++) {
				arrivalRatesAtPoint[j] = BasicExtractionUtils.getArrivalRate(j * trendDuration
						+ container.getPeriod() * currentSeasonalAmount + container.getMaxPeakOffset()
						+ extractionOffset, container.getLocalMaxes(), false);
			}
			trendValues[i] = BasicExtractionUtils.getMedian(arrivalRatesAtPoint);

			currentSeasonalAmount += seasonalsPerTrend[i];
		}
		trendValues[trendValues.length - 1] = trendValues[0];
		return trendValues;
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

	/**
	 * Reduces noise of a given arrival rate list with an expected seasonal period.
	 * @param arrivalRates The arrival rates for which to reduce noise.
	 * @param period Seasonal period within the arrival rate list.
	 */
	public static void reduceArrivalRateListNoise(List<ArrivalRateTuple> arrivalRates,
			double period) {
		ExtractionDataContainer reductionContainer = new ExtractionDataContainer(arrivalRates, period, 0, null, null, null);
		reduceArrivalRateListNoise(reductionContainer, true);
	}
	
	/**
	 * Reduce noise within the read arrival rate list by applying a gaussian
	 * filter.
	 * @param container Extraction container.
	 * @param extractNoise True, if noise is to be extracted.
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
