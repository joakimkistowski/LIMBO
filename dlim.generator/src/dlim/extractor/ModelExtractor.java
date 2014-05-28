package dlim.extractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.analysis.function.Gaussian;
import org.eclipse.emf.ecore.EClass;

import dlim.Burst;
import dlim.Combinator;
import dlim.Constant;
import dlim.DlimFactory;
import dlim.DlimPackage;
import dlim.Function;
import dlim.NormalNoise;
import dlim.Operator;
import dlim.Sequence;
import dlim.TimeDependentFunctionContainer;
import dlim.Trend;
import dlim.assistant.CalibrationException;
import dlim.assistant.Calibrator;
import dlim.generator.ArrivalRateTuple;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;

/**
 * Offers the default model extraction processes as used by the dlim.exporter plugin.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ModelExtractor {
	
	private static final double BURSTDETECTIONFACTOR = 1.2;
	private static final int EXPECTEDMAXPEAKSPERSEASONAL = 8;
	
	//The read list of arrival rates, may be filtered by the gaussian filter.
	private static List<ArrivalRateTuple> arrivalRateList = new ArrayList<ArrivalRateTuple>();
	
	//The original read list of arrival rates, if  the gaussian filter was applied to the original one.
	private static List<ArrivalRateTuple> noisyArrivalRateList = new ArrayList<ArrivalRateTuple>();
	
	//The local minima within the read arrival rate list.
	private static ArrayList<ArrivalRateTuple> localMins = new ArrayList<ArrivalRateTuple>();
	
	//The local maxima within the read arrival rate list.
	private static ArrayList<ArrivalRateTuple> localMaxes = new ArrayList<ArrivalRateTuple>();
	
	/**
	 * Extract an arrival Rate file using the simple extraction process.
	 * @param root
	 * @param arrList
	 * @param period
	 * @param seasonalsPerTrend
	 * @param seasonalShape
	 * @param trendShape
	 * @param operatorLiteral
	 * @param extractNoise
	 */
	public static void extractArrivalRateFileIntoSequence(Sequence root, List<ArrivalRateTuple> arrList, double period, int seasonalsPerTrend,
			String seasonalShape, String trendShape, String operatorLiteral, boolean extractNoise) throws CalibrationException {
		setupArrivalRateLists(arrList);
		reduceArrivalRateListNoise(period, extractNoise);
		//ArrivalRateGenerator.writeArrivalRates(arrivalRateList, "C:/Programming/workspace/runtime-EclipseApplication/dlimModelingTest", "reducedNoise");
		performMinMaxSearch();
		
		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		
		double step = 1.0;
		double duration = 0.0;
		if (arrivalRateList.size() > 1) {
			step = arrivalRateList.get(0).getStep(arrivalRateList.get(1));
			//System.out.println("Step = " + step);
		}
		duration = arrivalRateList.get(arrivalRateList.size()-1).getTimeStamp();
		
		//todo: get period; use the Fourier approach here?
		
		//get base level
		double base = getBaseLevel(period, duration);
		//System.out.println("Base Level: " + base);
		// get peak num
		int peakNum = getPeakNum(period, duration);
		//System.out.println("Peak Num: " + peakNum);
		//get peaks
		ArrivalRateTuple[] peaks = getPeaks(period, duration, peakNum);
		/*System.out.println("Peaks:");
		for (int i = 0; i < peaks.length; i++) {
			System.out.println(peaks[i].toString());
		}*/
		
		double maxPeakOffset = getMaxPeakOffset(peaks);
		//get  inner base (get peak num - 1 inner bases)
		ArrivalRateTuple[] innerBases = getInnerBases(period, duration, peaks);
		/*if (innerBases != null) {
			System.out.println("Inner Bases:");
			for (int i = 0; i < innerBases.length; i++) {
				System.out.println(innerBases[i].toString());
			}
		}*/
		
		//build seasonal part
		buildSeasonalPart(root, baseline, duration, period, base, peaks, innerBases, seasonalShape);
		
		//build trend part
		double[] trendPointValues = getTrendValues(period, duration, maxPeakOffset, seasonalsPerTrend);
		buildTrendPart(root, maxPeakOffset, period*seasonalsPerTrend, duration,
				trendPointValues, trendShape, operatorLiteral);
		buildTrendPart(baseline, maxPeakOffset, period*seasonalsPerTrend, duration,
				trendPointValues, trendShape, operatorLiteral);	
		
		
		ArrayList<ArrivalRateTuple> bursts = getBursts(root, baseline, period, duration, period/peakNum);
		buildBurstPart(root, period/peakNum, duration, bursts);

		buildNormalNoisePart(root, extractNoise);
	}
	
	/**
	 * Extract an arrival rate file using the periodic extraction process.
	 * @param root
	 * @param arrList
	 * @param period
	 * @param seasonalsPerTrendList
	 * @param seasonalShape
	 * @param trendShape
	 * @param operatorLiteral
	 * @param extractNoise
	 */
	public static void extractSequenceFromArrivalRateFilePeriodic(Sequence root, List<ArrivalRateTuple> arrList, double period, List<int[]> seasonalsPerTrendList,
			String seasonalShape, String trendShape, String operatorLiteral, boolean extractNoise) throws CalibrationException {
		setupArrivalRateLists(arrList);
		reduceArrivalRateListNoise(period, extractNoise);
		performMinMaxSearch();
		
		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		
		double step = 1.0;
		double duration = 0.0;
		if (arrivalRateList.size() > 1) {
			step = arrivalRateList.get(0).getStep(arrivalRateList.get(1));
			//System.out.println("Step = " + step);
		}
		duration = arrivalRateList.get(arrivalRateList.size()-1).getTimeStamp();
		
		//todo: get period; use the Fourier approach here?
		
		//get base level
		double base = getBaseLevel(period, duration);
		//System.out.println("Base Level: " + base);
		// get peak num
		int peakNum = getPeakNum(period, duration);
		//System.out.println("Peak Num: " + peakNum);
		//get peaks
		ArrivalRateTuple[] peaks = getPeaks(period, duration, peakNum);
		//System.out.println("Peaks:");
		/*for (int i = 0; i < peaks.length; i++) {
			System.out.println(peaks[i].toString());
		}*/
		
		double maxPeakOffset = getMaxPeakOffset(peaks);
		//get  inner base (get peak num - 1 inner bases)
		ArrivalRateTuple[] innerBases = getInnerBases(period, duration, peaks);
		/*if (innerBases != null) {
			System.out.println("Inner Bases:");
			for (int i = 0; i < innerBases.length; i++) {
				System.out.println(innerBases[i].toString());
			}
		}*/
		//build seasonal part
		buildSeasonalPart(root, baseline, duration, period, base, peaks, innerBases, seasonalShape);
		
		//build trend part
		root.getCombine().clear();
		for (int[] seasonalsPerTrend : seasonalsPerTrendList) {
			double[] trendPointValues = getMediandValuesForPeriodicTrend(period, duration, maxPeakOffset, seasonalsPerTrend);
			buildRepeatingTrend(root, period, maxPeakOffset, seasonalsPerTrend,
					trendPointValues, duration, trendShape, operatorLiteral);
			buildRepeatingTrend(baseline, period, maxPeakOffset, seasonalsPerTrend,
					trendPointValues, duration, trendShape, operatorLiteral);
		}
		
		
		ArrayList<ArrivalRateTuple> bursts = getBursts(root, baseline, period, duration, period/peakNum);
		buildBurstPart(root, period/peakNum, duration, bursts);
		
		buildNormalNoisePart(root, extractNoise);
	}
	
	/**
	 * Extract HLDLIM parameters from an arrival rate list.
	 * @param arrivalRateFilePath
	 * @param period
	 * @param offset
	 * @param seasonalsPerTrend
	 * @param seasonalShape
	 * @param trendShape
	 * @param operatorLiteral
	 * @param extractNoise
	 * @return
	 */
	public static HLDlimParameterContainer extractArrivalRateFileIntoParameters(String arrivalRateFilePath, double period, double offset, int seasonalsPerTrend,
			String seasonalShape, String trendShape, String operatorLiteral, boolean extractNoise) throws CalibrationException {
		HLDlimParameterContainer container = new HLDlimParameterContainer();
		container.setSeasonalShape(seasonalShape);
		container.setTrendShape(trendShape);
		container.setOperatorLiteral(operatorLiteral);
		container.setSeasonalPeriod(period);
		container.setSeasonalsPerTrend(seasonalsPerTrend);
				
		readFile(arrivalRateFilePath,offset);
		reduceArrivalRateListNoise(period, extractNoise);
		performMinMaxSearch();
		
		Sequence root = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		Sequence baseline = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		
		double step = 1.0;
		double duration = 0.0;
		if (arrivalRateList.size() > 1) {
			step = arrivalRateList.get(0).getStep(arrivalRateList.get(1));
			//System.out.println("Step = " + step);
		}
		duration = arrivalRateList.get(arrivalRateList.size()-1).getTimeStamp();
		
		
		//get base level
		double base = getBaseLevel(period, duration);
		container.setBase(base);
		//System.out.println("Base Level: " + base);
		// get peak num
		int peakNum = getPeakNum(period, duration);
		container.setPeakNum(peakNum);
		//System.out.println("Peak Num: " + peakNum);
		//get peaks
		ArrivalRateTuple[] peaks = getPeaks(period, duration, peakNum);
		container.setPeakIntervalWidth(peaks[peaks.length-1].getTimeStamp() - peaks[0].getTimeStamp());
		ArrivalRateTuple.setSortByTime(false);
		Arrays.sort(peaks);
		if (peaks[peaks.length-1].getTimeStamp() < peaks[0].getTimeStamp()) {
			container.setFirstPeak(peaks[peaks.length-1].getArrivalRate());
			container.setLastPeak(peaks[0].getArrivalRate());
		} else {
			container.setLastPeak(peaks[peaks.length-1].getArrivalRate());
			container.setFirstPeak(peaks[0].getArrivalRate());
		}
		
		//interpolate new peaks using parameters
		for (int i = 0; i < peaks.length; i++) {
			peaks[i].setTimeStamp((period-container.getPeakIntervalWidth())/2.0
					+ (i*container.getPeakIntervalWidth()/peaks.length));
			peaks[i].setArrivalRate(container.getFirstPeak() - i*(container.getFirstPeak()-container.getLastPeak())/peaks.length);
		}
		
		
		//System.out.println("Peaks:");
		/*for (int i = 0; i < peaks.length; i++) {
			System.out.println(peaks[i].toString());
		}*/
		
		double maxPeakOffset = getMaxPeakOffset(peaks);
		container.setTrendOffset(maxPeakOffset);
		//get  inner base (get peak num - 1 inner bases)
		ArrivalRateTuple[] innerBases = getInnerBases(period, duration, peaks);
		if (innerBases != null) {
			ArrivalRateTuple.setSortByTime(false);
			Arrays.sort(innerBases);
			double innerBase = innerBases[(innerBases.length-1)/2].getArrivalRate();
			container.setInnerBase(innerBase);
			//System.out.println("Inner Bases:");
			for (int i = 0; i < innerBases.length; i++) {
				//System.out.println(innerBases[i].toString());
				innerBases[i].setArrivalRate(innerBase);
			}
		}
		
		//build seasonal part
		buildSeasonalPart(root, baseline, duration, period, base, peaks, innerBases, seasonalShape);
		
		//build trend part
		double[] trendPointValues = getTrendValues(period, duration, maxPeakOffset, seasonalsPerTrend);
		container.setTrendPoints(trendPointValues);
		buildTrendPart(root, maxPeakOffset, period*seasonalsPerTrend, duration,
				trendPointValues, trendShape, operatorLiteral);
		buildTrendPart(baseline, maxPeakOffset, period*seasonalsPerTrend, duration,
				trendPointValues, trendShape, operatorLiteral);	
		
		
		ArrayList<ArrivalRateTuple> bursts = getBursts(root, baseline, period, duration, period/peakNum);
		container.setBurstOffset(12.0);
		container.setBurstPeriod(48.0);
		if (bursts.size() > 0) {
			ModelEvaluator evaluator = new ModelEvaluator(root,0,IGeneratorConstants.CALIBRATION);
			container.setBurstOffset(bursts.get(0).getTimeStamp());
			container.setBurstWidth(period/peakNum);
			container.setBurstPeriod(duration);
			container.setBurstPeak(bursts.get(0).getArrivalRate() - evaluator.getArrivalRateAtTime(container.getBurstOffset()));
			if (bursts.size() > 1) {
				//get median inter-burst period
				double[] interBurstTimes = new double[bursts.size()-1];
				for (int i = 0; i < interBurstTimes.length; i++) {
					interBurstTimes[i] = bursts.get(i+1).getTimeStamp()-bursts.get(i).getTimeStamp();
				}
				Arrays.sort(interBurstTimes);
				container.setBurstPeriod(getMedian(interBurstTimes));
			}
		}
		
		//buildBurstPart(root, period/peakNum, duration, bursts);
		setUniformNoisePart(container, extractNoise);
		
		return container;
	}
	
	/*
	 * Read the arrival rate file. Do not use, when extending LIMBO. Use only for HLDLIM
	 * paramter extraction.
	 */
	private static void readFile(String arrivalRateFilePath, double offset) {
		arrivalRateList.clear();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(arrivalRateFilePath));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.substring(0, line.length() -1);
				String[] numbers = line.split(",");
				if (numbers.length >= 2) {
					double timeStamp = Double.parseDouble(numbers[0].trim());
					double readArrivalRate = Double.parseDouble(numbers[1].trim());
					if (timeStamp - offset > 0) {
						arrivalRateList.add(new ArrivalRateTuple(timeStamp-offset,readArrivalRate));
					}
				}
			}
			
			br.close();
			
			noisyArrivalRateList.clear();
			for (ArrivalRateTuple t: arrivalRateList) {
				noisyArrivalRateList.add(new ArrivalRateTuple(t.getTimeStamp(),t.getArrivalRate()));
			}
		} catch (IOException e) {
			System.out.println("Arrival Rate File does not exist.");
		}
		
	}
	
	/*
	 * Searches for all local minima and maxima and stores them in the respective lists.
	 */
	private static void performMinMaxSearch() {
		boolean descending = true;
		
		localMaxes.clear();
		localMins.clear();
		
		if (arrivalRateList.get(1).getArrivalRate() > arrivalRateList.get(0).getArrivalRate()) {
			descending = false;
			localMins.add(arrivalRateList.get(0));
		} else {
			descending = true;
			localMaxes.add(arrivalRateList.get(0));
		}
		
		ArrivalRateTuple lastT = arrivalRateList.get(0);
		
		for (ArrivalRateTuple t : arrivalRateList) {
			if (descending && (t.getArrivalRate() > lastT.getArrivalRate())) {
				localMins.add(lastT);
				descending = false;
			} else if (!descending && (t.getArrivalRate() < lastT.getArrivalRate())) {
				localMaxes.add(lastT);
				descending = true;
			}
			lastT = t;
		}
	}
	
	//returns the median of all local minimums that define the end of each seasonal part
	private static double getBaseLevel(double period, double duration) {
		double[] bases = new double[(int)(duration/period)];
		double currentTime = localMins.get(0).getTimeStamp();
		for (int i = 0; i < bases.length; i++) {
			bases[i] = getArrivalRate(currentTime, localMins, false);
			currentTime += period;
		}
		Arrays.sort(bases);
		return getMedian(bases);
	}
	
	//gets the amount of peaks within each seasonal part
	private static int getPeakNum(double period, double duration) {
		int[] peakNums = new int[(int)(duration/period)];
		double currentSeasonalBegin = localMins.get(0).getTimeStamp();
		int index = 0;
		for (ArrivalRateTuple t : localMaxes) {
			while (t.getTimeStamp() > currentSeasonalBegin + period) {
				index++;
				currentSeasonalBegin += period;
			}
			if (index >= peakNums.length) {
				break;
			}
			
			peakNums[index]++;
		}
		/*for (int i = 0; i < peakNums.length; i++) {
			System.out.println("PeakNums["+i+"]: " + peakNums[i]);
		}*/
		Arrays.sort(peakNums);
		return peakNums[(peakNums.length-1)/2];
	}
	
	private static ArrivalRateTuple[] getPeaks(double period, double duration, int peakNum) throws CalibrationException {
		int seasonals = (int)(duration/period);
		@SuppressWarnings("unchecked")
		List<ArrivalRateTuple>[] peaksPerSeasonal = new ArrayList[seasonals];
		//initialize peaksPerSeasonal
		for (int i = 0; i < seasonals; i++) {
			peaksPerSeasonal[i] = new ArrayList<ArrivalRateTuple>();
		}
		
		double currentSeasonalBegin = localMins.get(0).getTimeStamp();
		int index = 0;
		for (ArrivalRateTuple t : localMaxes) {
			while (t.getTimeStamp() > currentSeasonalBegin + period) {
				index++;
				currentSeasonalBegin += period;
			}
			if (index >= seasonals) {
				break;
			}
			
			peaksPerSeasonal[index].add(new ArrivalRateTuple(t.getTimeStamp()-currentSeasonalBegin,t.getArrivalRate()));
		}
		
		//sort peaks by time, this way I can get the 1st, the 2nd,... the nth peak per seasonal
		ArrivalRateTuple.setSortByTime(true);
		for (int i = 0; i < seasonals; i++) {
			Collections.sort(peaksPerSeasonal[i]);
		}
		
		ArrivalRateTuple[][] arrivalRateTuplesPerPeak = new ArrivalRateTuple[peakNum][seasonals];
		
		
		//since not all read seasonals have exactly a peakNum amount of peaks,
		//the peaks are split into their first and second half, this ensures that at least
		//the last and first peak are consistent
		for (int i = 0; i <= peakNum/2; i++) {
			for (int j = 0; j < seasonals; j++) {
				try {
					arrivalRateTuplesPerPeak[i][j] = peaksPerSeasonal[j].get(i);
				} catch (IndexOutOfBoundsException e) {
					try {
						arrivalRateTuplesPerPeak[i][j] = new ArrivalRateTuple(0,0);
						} catch (IndexOutOfBoundsException e2) {
							throw new CalibrationException("Seasonal Period is too small.");
						}
					
				}
			}
		}
		for (int i = peakNum-1; i > peakNum/2; i--) {
			for (int j = 0; j < seasonals; j++) {
				try {
					arrivalRateTuplesPerPeak[i][j] = peaksPerSeasonal[j].get(
							peaksPerSeasonal[j].size()-(peakNum-i));
				} catch (IndexOutOfBoundsException e) {
					arrivalRateTuplesPerPeak[i][j] = new ArrivalRateTuple(0,0);
				}
			}
		}
		
		//We have now sorted all peaks according to their place within the abstract seasonal.
		//We must now calculate the median timeStamps and arrival rate for each peak
		
		//First, calculate median time stamps
		ArrivalRateTuple[] peaks = new ArrivalRateTuple[peakNum];
		ArrivalRateTuple.setSortByTime(true);
		for (int i = 0; i < peakNum; i++) {
			Arrays.sort(arrivalRateTuplesPerPeak[i]);
			peaks[i] = new ArrivalRateTuple(getMedianTimeStamp(arrivalRateTuplesPerPeak[i]),0);
		}
		
		ArrivalRateTuple.setSortByTime(false);
		for (int i = 0; i < peakNum; i++) {
			Arrays.sort(arrivalRateTuplesPerPeak[i]);
			peaks[i].setArrivalRate(getMedianArrivalRate(arrivalRateTuplesPerPeak[i]));
		}
		
		//This sort should not be necessary, it only helps to prevent errors in cases
		//in which this extraction method is pretty terrible anyways
		ArrivalRateTuple.setSortByTime(true);
		Arrays.sort(peaks);
		
		return peaks;
	}
	
	//gets the inner bases by nearest neighbour sampling within the local mins
	private static ArrivalRateTuple[] getInnerBases(double period, double duration, ArrivalRateTuple[] peaks) {
		if (peaks.length <= 1) {
			return null;
		}
		int seasonals = (int)(duration/period);
		int innerBaseCount = peaks.length-1;
		ArrivalRateTuple[][] innerBasesPerPeak = new ArrivalRateTuple[innerBaseCount][seasonals];
		
		for (int i = 0; i < innerBaseCount; i++) {
			double currentSeasonalBegin = localMins.get(0).getTimeStamp();
			double samplingTime = (peaks[i].getTimeStamp() + peaks[i+1].getTimeStamp())/2;
			for (int j = 0; j < seasonals; j++) {
				ArrivalRateTuple tmpTuple = getNearestArrivalRateTuple(samplingTime + currentSeasonalBegin,
								localMins);
				innerBasesPerPeak[i][j] = new ArrivalRateTuple(tmpTuple.getTimeStamp()-currentSeasonalBegin,tmpTuple.getArrivalRate());
				currentSeasonalBegin += period;
			}
		}
		
		ArrivalRateTuple[] innerBases = new ArrivalRateTuple[innerBaseCount];
		for (int i = 0; i < innerBaseCount; i++) {
			//sort inner bases by time to get the median timeStamp
			ArrivalRateTuple.setSortByTime(true);
			Arrays.sort(innerBasesPerPeak[i]);
			double timeStamp = innerBasesPerPeak[i][(innerBasesPerPeak[i].length-1)/2].getTimeStamp();
			//if median timeStamp is not bordered by its peaks
			if (!(peaks[i].getTimeStamp() < timeStamp && timeStamp < peaks[i+1].getTimeStamp())) {
				timeStamp = (peaks[i].getTimeStamp() + peaks[i+1].getTimeStamp())/2.0;
			}
			
			//sort by arrival rate to get median arrival rate
			ArrivalRateTuple.setSortByTime(false);
			Arrays.sort(innerBasesPerPeak[i]);
			double arrivalRate = innerBasesPerPeak[i][(innerBasesPerPeak[i].length-1)/2].getArrivalRate();
			innerBases[i] = new ArrivalRateTuple(timeStamp,arrivalRate);
		}
		
		return innerBases;
	}
	
	/*
	 * Get the arrival rate at time-stamp time. Use either linear interpolation or nearest neighbor interpolation
	 * for undefined time stamps.
	 */
	private static double getArrivalRate(double time, List<ArrivalRateTuple> arrRates, boolean linearInterpolation) {
		if (arrRates.size() == 0) {
			return 0.0;
		}

		if (time >= arrRates.get(arrRates.size()-1).getTimeStamp()) {
			return 0.0;
		} else if (time < arrRates.get(0).getTimeStamp()) {
			if (time >= 0) {
				//interpolate between 0 and first arrival rate value
				if (linearInterpolation) {
					return (time/arrRates.get(0).getTimeStamp())*arrRates.get(0).getArrivalRate();
				} else {
					return arrRates.get(0).getArrivalRate();
				}
				
			}
			return 0.0;
		}
		
		double assumedStep = arrRates.get(0).getStep(null);
		
		//find the surrounding list elements
		int index = (int)(time / assumedStep);
		if (index >= arrRates.size() - 1) {
			index = arrRates.size() - 2;
		}
		ArrivalRateTuple lower = arrRates.get(index);
		ArrivalRateTuple higher = arrRates.get(index+1);
		while (time < lower.getTimeStamp()) {
			index--;
			lower = arrRates.get(index);
		}
		while (time >= higher.getTimeStamp()) {
			index++;
			higher = arrRates.get(index+1);
		}
		lower = arrRates.get(index);
		higher = arrRates.get(index+1);
		
		//interpolate result
		if (linearInterpolation) {
			return lower.getArrivalRate()
					+ ((time-lower.getTimeStamp())/(higher.getTimeStamp()-lower.getTimeStamp()))
						* (higher.getArrivalRate()-lower.getArrivalRate());
		} else {
			//nearest neighbor
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
	private static ArrivalRateTuple getNearestArrivalRateTuple(double time, List<ArrivalRateTuple> arrRates) {
		if (arrRates.size() == 0) {
			return null;
		}

		if (time >= arrRates.get(arrRates.size()-1).getTimeStamp()) {
			return arrRates.get(arrRates.size()-1);
		} else if (time < arrRates.get(0).getTimeStamp()) {
			return arrRates.get(0);
		}
		
		double assumedStep = arrRates.get(0).getStep(null);
		
		//find the surrounding list elements
		int index = (int)(time / assumedStep);
		if (index >= arrRates.size() - 1) {
			index = arrRates.size() - 2;
		}
		ArrivalRateTuple lower = arrRates.get(index);
		ArrivalRateTuple higher = arrRates.get(index+1);
		while (time < lower.getTimeStamp()) {
			index--;
			lower = arrRates.get(index);
		}
		while (time >= higher.getTimeStamp()) {
			index++;
			higher = arrRates.get(index+1);
		}
		lower = arrRates.get(index);
		higher = arrRates.get(index+1);
		
		//nearest neighbor
		if (time - lower.getTimeStamp() < higher.getTimeStamp() - time) {
			return lower;
		} else {
			return higher;
		}
	}
	
	/**
	 * Returns the median value for a sorted double array.
	 * @param array Must be sorted.
	 * @return
	 */
	private static double getMedian(double[] array) {
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length-1)/2] + array[(array.length)/2])/2.0;
		}
		return array[(array.length-1)/2];
	}
	
	/**
	 * Returns the median time stamp for a sorted ArrivalRateTuple array.
	 * @param array Must be sorted by time stamps.
	 * @return
	 */
	private static double getMedianTimeStamp(ArrivalRateTuple[] array) {
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length-1)/2].getTimeStamp() + array[(array.length)/2].getTimeStamp())/2.0;
		}
		return array[(array.length-1)/2].getTimeStamp();
	}
	
	/**
	 * Returns the median arrival rate for a sorted ArrivalRateTuple array.
	 * @param array Must be sorted by arrival rates.
	 * @return
	 */
	private static double getMedianArrivalRate(ArrivalRateTuple[] array) {
		if (array.length > 0 && array.length % 2 == 0) {
			return (array[(array.length-1)/2].getArrivalRate() + array[(array.length)/2].getArrivalRate())/2.0;
		}
		return array[(array.length-1)/2].getArrivalRate();
	}
	
	/*
	 * Build the seasonal part of the DLIM instance.
	 */
	private static void buildSeasonalPart(Sequence root, Sequence baseline, double duration, double period,
			double base, ArrivalRateTuple[] peaks, ArrivalRateTuple[] innerBases, String shapeString) {
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		root.getSequenceFunctionContainers().clear();
		root.getCombine().clear();
		root.setTerminateAfterTime(duration);
		root.setTerminateAfterLoops(-1);
		
		ArrivalRateTuple[] interpolationPoints = new ArrivalRateTuple[peaks.length*2+1];
		interpolationPoints[0] = new ArrivalRateTuple(0.0,base);
		interpolationPoints[interpolationPoints.length-1] = new ArrivalRateTuple(period,base);
		for (int i = 0; i < peaks.length; i++) {
			interpolationPoints[i*2+1] = peaks[i];
		}
		if (innerBases != null) {
			for (int i = 0; i < innerBases.length; i++) {
				interpolationPoints[i*2+2] = innerBases[i];
			}
		}
		
		for (int i = 1; i < interpolationPoints.length; i++) {
			TimeDependentFunctionContainer currentElement = factory.createTimeDependentFunctionContainer();
			currentElement.setName("seasonal" + i);
			currentElement.setDuration(interpolationPoints[i].getTimeStamp()
					-interpolationPoints[i-1].getTimeStamp());
			Trend currentTrend = createTrendWithCorrectShape(shapeString);
			currentTrend.setFunctionOutputAtStart(interpolationPoints[i-1].getArrivalRate());
			currentTrend.setFunctionOutputAtEnd(interpolationPoints[i].getArrivalRate());
			currentElement.setFunction(currentTrend);
			root.getSequenceFunctionContainers().add(currentElement);
		}
		
		//baseline
		{
			baseline.getSequenceFunctionContainers().clear();
			baseline.getCombine().clear();
			baseline.setTerminateAfterTime(duration);
			baseline.setTerminateAfterLoops(-1);
			double maxPeak = 0.0;
			for (int i = 0; i < peaks.length; i++) {
				if (peaks[i].getArrivalRate() > maxPeak) {
					maxPeak = peaks[i].getArrivalRate();
				}
			}
			TimeDependentFunctionContainer constantContainer = factory.createTimeDependentFunctionContainer();
			constantContainer.setName("constant");
			constantContainer.setDuration(period);
			baseline.getSequenceFunctionContainers().add(constantContainer);
			Constant constant = factory.createConstant();
			constant.setConstant(maxPeak);
			constantContainer.setFunction(constant);
		}
	}
	
	/*
	 * Create the Trend with the correct implementation.
	 */
	private static Trend createTrendWithCorrectShape(String shapeString) {
		try {
			return (Trend) DlimPackage.eINSTANCE.getDlimFactory().create((EClass)(DlimPackage.eINSTANCE.getEClassifier(shapeString)));
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
	 * Get the list of arrival rates at the maximum seasonal peaks. This is needed for building the trend part.
	 * See HLDLIM trend part definition for details.
	 */
	private static double[] getTrendValues(double period, double duration, double maxPeakOffset, int seasonalsPerTrend) {
		if (seasonalsPerTrend <= 0) {
			return null;
		}
		//the extractor always starts at the first local minimum
		//we have to take this into account when sampling from read values;
		double extractionOffset = localMins.get(0).getTimeStamp();
		int trendPointNum = (int)(duration/period)/seasonalsPerTrend;
		double[] trendValues = new double[trendPointNum];
		for (int i = 0; i < trendPointNum; i++) {
			trendValues[i] = getArrivalRate(i*period*seasonalsPerTrend + maxPeakOffset + extractionOffset, localMaxes, false);
		}
		return trendValues;
	}
	
	/*
	 * Get the median trend values (see getTrendValues) for repeating periodic trends.
	 */
	private static double[] getMediandValuesForPeriodicTrend(double period, double duration, double maxPeakOffset, int[] seasonalsPerTrend) {
		if (seasonalsPerTrend.length <= 0) {
			return null;
		}
		
		double trendDuration = 0;
		for (int seasonals : seasonalsPerTrend) {
			trendDuration += seasonals;
		}
		trendDuration *= period;
		
		//the extractor always starts at the first local minimum
		//we have to take this into account when sampling from read values;
		double extractionOffset = localMins.get(0).getTimeStamp();
		double[] trendValues = new double[seasonalsPerTrend.length+1];
		int currentSeasonalAmount = 0;
		for (int i = 0; i < seasonalsPerTrend.length; i++) {
			trendValues[i] = getArrivalRate(period*currentSeasonalAmount + maxPeakOffset + extractionOffset, localMaxes, false);
			
			//get Median arrival rate for trend point
			int trendPeriods = (int)((duration-maxPeakOffset-extractionOffset)/trendDuration);
			double[] arrivalRatesAtPoint = new double[trendPeriods];
			for (int j = 0; j < trendPeriods; j++) {
				arrivalRatesAtPoint[j] = getArrivalRate(j*trendDuration + period*currentSeasonalAmount + maxPeakOffset + extractionOffset, localMaxes, false);
			}
			trendValues[i] = getMedian(arrivalRatesAtPoint);
			
			currentSeasonalAmount += seasonalsPerTrend[i];
		}
		trendValues[trendValues.length-1] = trendValues[0];
		return trendValues;
	}
	
	/*
	 * Build the Trend part within the DLIM instance.
	 */
	private static void buildTrendPart(Sequence root, double maxPeakOffset, double trendDuration,
			double duration, double[] trendPoints, String shapeString, String operatorLiteral) {
		root.getCombine().clear();
		if (trendDuration > 0.0 && maxPeakOffset+trendDuration < duration && trendPoints.length > 1) {
			DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
			Combinator trendCombinator = factory.createCombinator();
			trendCombinator.setOperator(Operator.get(operatorLiteral));
			Sequence trendSequence = factory.createSequence();
			trendSequence.setName("trends");
			trendSequence.setTerminateAfterTime(duration);
			trendCombinator.setFunction(trendSequence);
			root.getCombine().add(trendCombinator);
			
			TimeDependentFunctionContainer offsetElement = factory.createTimeDependentFunctionContainer();
			offsetElement.setDuration(maxPeakOffset);
			offsetElement.setName("offset");
			offsetElement.setFunction(factory.createConstant());
			trendSequence.getSequenceFunctionContainers().add(offsetElement);
			
			for (int i = 0; i < trendPoints.length-1; i++) {
				TimeDependentFunctionContainer trendElement = factory.createTimeDependentFunctionContainer();
				trendElement.setDuration(trendDuration);
				trendElement.setName("trend"+i);
				trendElement.setFunction(createTrendWithCorrectShape(shapeString));
				trendSequence.getSequenceFunctionContainers().add(trendElement);
			}
			TimeDependentFunctionContainer holdElement = factory.createTimeDependentFunctionContainer();
			holdElement.setDuration(duration - maxPeakOffset - trendDuration*(trendPoints.length-1));
			holdElement.setName("holdLastTrend");
			holdElement.setFunction(factory.createConstant());
			trendSequence.getSequenceFunctionContainers().add(holdElement);
			
			//calibrate Trends
			int i=0;
			for (TimeDependentFunctionContainer e: trendSequence.getSequenceFunctionContainers()) {
				Function f = e.getFunction();
				if (f instanceof Trend) {
					Trend trend = (Trend)f;
					trend.setFunctionOutputAtStart(calibrateTrendStartValue(root, trend, trendPoints[i]));
					i++;
					trend.setFunctionOutputAtEnd(calibrateTrendEndValue(root, trend, trendPoints[i]));
				}
			}
			//set offset constant values
			Constant offset1 = (Constant)trendSequence.getSequenceFunctionContainers().get(0).getFunction();
			offset1.setConstant(((Trend)(trendSequence.getSequenceFunctionContainers().get(1).getFunction())).getFunctionOutputAtStart());
			
			int lastIndex = trendSequence.getSequenceFunctionContainers().size()-1;
			Constant offset2 = (Constant)trendSequence.getSequenceFunctionContainers().get(lastIndex).getFunction();
			offset2.setConstant(((Trend)(trendSequence.getSequenceFunctionContainers().get(lastIndex-1).getFunction())).getFunctionOutputAtEnd());
			
		}
	}
	
	/*
	 * Build the Trend part for the periodic extraction process.
	 */
	private static void buildRepeatingTrend(Sequence root, double seasonalPeriod, double maxPeakOffset, int[] seasonalsPerTrend,
			double[] trendPoints, double duration, String shapeString, String operatorLiteral) {
		
		double trendDuration = 0;
		for (int seasonals : seasonalsPerTrend) {
			trendDuration += seasonals;
		}
		trendDuration *= seasonalPeriod;
		
		if (trendDuration > 0.0 && trendPoints.length > 1) {
			DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
			Combinator trendCombinator = factory.createCombinator();
			trendCombinator.setOperator(Operator.get(operatorLiteral));
			Sequence trendSequenceContainer = factory.createSequence();
			trendSequenceContainer.setName("trendContainer");
			trendSequenceContainer.setTerminateAfterTime(duration);
			Sequence trendSequence = factory.createSequence();
			trendSequence.setName("trends");
			trendSequence.setTerminateAfterTime(duration);
			trendCombinator.setFunction(trendSequenceContainer);
			root.getCombine().add(trendCombinator);
			
			TimeDependentFunctionContainer offsetElement = factory.createTimeDependentFunctionContainer();
			offsetElement.setDuration(maxPeakOffset);
			offsetElement.setName("offset");
			offsetElement.setFunction(factory.createConstant());
			trendSequenceContainer.getSequenceFunctionContainers().add(offsetElement);
			
			TimeDependentFunctionContainer trendContainer = factory.createTimeDependentFunctionContainer();
			trendContainer.setDuration(duration);
			trendContainer.setName("trends");
			trendContainer.setFunction(trendSequence);
			trendSequenceContainer.getSequenceFunctionContainers().add(trendContainer);
			
			for (int i = 0; i < trendPoints.length-1; i++) {
				TimeDependentFunctionContainer trendElement = factory.createTimeDependentFunctionContainer();
				trendElement.setDuration(seasonalsPerTrend[i]*seasonalPeriod);
				trendElement.setName("trend"+i);
				trendElement.setFunction(createTrendWithCorrectShape(shapeString));
				trendSequence.getSequenceFunctionContainers().add(trendElement);
			}
			
			//calibrate Trends
			int i=0;
			for (TimeDependentFunctionContainer e: trendSequence.getSequenceFunctionContainers()) {
				Function f = e.getFunction();
				if (f instanceof Trend) {
					Trend trend = (Trend)f;
					trend.setFunctionOutputAtStart(calibrateTrendStartValue(root, trend, trendPoints[i]));
					i++;
					trend.setFunctionOutputAtEnd(calibrateTrendEndValue(root, trend, trendPoints[i]));
				}
			}
			//set offset constant value
			Constant offset1 = (Constant)trendSequenceContainer.getSequenceFunctionContainers().get(0).getFunction();
			offset1.setConstant(((Trend)(trendSequence.getSequenceFunctionContainers().get(0).getFunction())).getFunctionOutputAtStart());
			
		}
	}
	
	/*
	 * Calibrate the start value of an interpolated trend function.
	 */
	private static double calibrateTrendStartValue(Sequence rootSequence, Trend trend, double value) {
		ModelEvaluator evaluator = new ModelEvaluator(rootSequence,0,IGeneratorConstants.CALIBRATION);
		double startValue = value;
		try {
			startValue = Calibrator.calibrateTrendStartValue(value, trend, evaluator);
		} catch (CalibrationException e) {
		
		}
		return startValue;
	}
	
	/*
	 * Calibrate the end value of an interpolated trend function.
	 */
	private static double calibrateTrendEndValue(Sequence rootSequence, Trend trend, double value) {
		ModelEvaluator evaluator = new ModelEvaluator(rootSequence,0,IGeneratorConstants.CALIBRATION);
		double endValue = value;
		try {
			endValue = Calibrator.calibrateTrendEndValue(value, trend, evaluator);
		} catch (CalibrationException e) {
			
		}
		return endValue;
	}
	
	/*
	 * Get the time-stamps and arrival rates of bursts (meaning major deviations from the extracted model instance).
	 */
	private static ArrayList<ArrivalRateTuple> getBursts(Sequence root, Sequence peakBaselineModel, double period, double totalDuration, double burstWidth) {
		double timeOffset = localMins.get(0).getTimeStamp();
		ArrayList<ArrivalRateTuple> burstList = new ArrayList<ArrivalRateTuple>();
		ModelEvaluator currentBaselineModel = new ModelEvaluator(peakBaselineModel, 5, IGeneratorConstants.CALIBRATION);
		
		for (ArrivalRateTuple max : localMaxes) {
			double currentTime = max.getTimeStamp()-timeOffset;
			if (currentTime >= 0 && max.getArrivalRate() > currentBaselineModel.getArrivalRateAtTime(currentTime)*BURSTDETECTIONFACTOR) {
				burstList.add(new ArrivalRateTuple(currentTime,max.getArrivalRate()));
				
				//merge bursts that are two close to one another
				if (burstList.size() > 1) {
					if (burstList.get(burstList.size()-1).getTimeStamp()-burstList.get(burstList.size()-2).getTimeStamp() < burstWidth) {
						if (burstList.get(burstList.size()-1).getArrivalRate() < burstList.get(burstList.size()-2).getArrivalRate()) {
							burstList.remove(burstList.size()-1);
						} else {
							burstList.remove(burstList.size()-2);
						}
					}
				}
			}
		}
		
		/*for (ArrivalRateTuple t : burstList) {
			System.out.println("Burst: " + t.toString());
		}*/
		
		
		return burstList;
	}
	
	/*
	 * Build the burst part.
	 */
	private static void buildBurstPart(Sequence root, double burstWidth, double duration, ArrayList<ArrivalRateTuple> bursts) {
		if (bursts.size() > 0) {
			double halfBurstWidth = burstWidth/2.0;
			DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
			Combinator burstCombinator = factory.createCombinator();
			burstCombinator.setOperator(Operator.ADD);
			Sequence burstSequence = factory.createSequence();
			burstSequence.setTerminateAfterTime(duration);
			burstSequence.setTerminateAfterLoops(1);
			burstSequence.setName("bursts");
			burstCombinator.setFunction(burstSequence);
			
			int index = 0;
			double offsetDurationDecrement = halfBurstWidth;
			for (ArrivalRateTuple burst : bursts) {
				TimeDependentFunctionContainer offsetContainer = factory.createTimeDependentFunctionContainer();
				offsetContainer.setName("burstOffset"+index);
				offsetContainer.setDuration(burst.getTimeStamp()-offsetDurationDecrement);
				offsetDurationDecrement = burstWidth + burst.getTimeStamp();
				TimeDependentFunctionContainer burstContainer = factory.createTimeDependentFunctionContainer();
				burstContainer.setDuration(burstWidth);
				burstContainer.setName("burst"+index);
				Burst burstFunction = createBurstWithCorrectShape("");
				burstFunction.setBase(0.0);
				burstFunction.setPeakTime(halfBurstWidth);
				burstContainer.setFunction(burstFunction);
				burstSequence.getSequenceFunctionContainers().add(offsetContainer);
				burstSequence.getSequenceFunctionContainers().add(burstContainer);
				index++;
			}
			
			root.getCombine().add(burstCombinator);
			
			//calibrate burst peaks
			index = 0;
			for (TimeDependentFunctionContainer container : burstSequence.getSequenceFunctionContainers()) {
				if (container.getFunction() != null && container.getFunction() instanceof Burst) {
					Burst burstFunction = (Burst)container.getFunction();
					burstFunction.setPeak(calibrateBurstPeakValue(root,burstFunction,bursts.get(index).getArrivalRate()));
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
			return (Burst) DlimPackage.eINSTANCE.getDlimFactory().create((EClass)(DlimPackage.eINSTANCE.getEClassifier(shapeString)));
		} catch (NullPointerException e) {
			return DlimPackage.eINSTANCE.getDlimFactory().createExponentialIncreaseAndDecline();
		}
	}
	
	private static double calibrateBurstPeakValue(Sequence rootSequence, Burst burst, double value) {
		ModelEvaluator evaluator = new ModelEvaluator(rootSequence,0,IGeneratorConstants.CALIBRATION);
		double endValue = value;
		try {
			endValue = Calibrator.calibrateBurstPeakValue(value, burst, evaluator);
		} catch (CalibrationException e) {
			
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
		double sigma = Math.sqrt((filterWidth*filterWidth - 1.0)/12.0);
		int mean = filterWidth/2;
		double filterSum = 0.0;
		Gaussian gaussian = new Gaussian(mean,sigma);
		for (int i = 0; i < filterWidth; i++) {
			filter[i] = gaussian.value(i);
			filterSum += filter[i];
		}
		
		//normalize to 1
		for (int i = 0; i < filterWidth; i++) {
			filter[i] = filter[i]/filterSum;
			//System.out.println("filter["+(i)+"]: " + filter[i]);
		}
		
		return filter;
	}
	
	/*
	 * Reduce noise within the read arrival rate list by applying a gaussian filter.
	 */
	private static void reduceArrivalRateListNoise(double seasonalPeriod, boolean extractNoise) {
		if (!extractNoise) {
			return;
		}
		double[] filter = createGaussianFilter((int)(seasonalPeriod/EXPECTEDMAXPEAKSPERSEASONAL));
		
		double[] arrivalRates = new double[arrivalRateList.size()];
		int index = 0;
		for (ArrivalRateTuple t : arrivalRateList) {
			arrivalRates[index] = t.getArrivalRate();
			index++;
		}
		index = 0;
		for (ArrivalRateTuple t: arrivalRateList) {
			t.setArrivalRate(getFilteredValueAtIndex(arrivalRates,index,filter));
			index++;
		}
	}
	
	/*
	 * Apply gaussian filter to arrival rate at index index.
	 */
	private static double getFilteredValueAtIndex(double[] arrivalRateArray, int index, double[] filter) {
		int filterCenter = filter.length / 2;
		
		double filteredValue = 0.0;
		for (int i = 0; i < filter.length; i++) {
			filteredValue += filter[i]*getArrivalRateFromArray(arrivalRateArray, index + (i-filterCenter));
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
	 * Extracts a normal distributed noise by calculating the difference between the filtered
	 * and unfiltered arrival rate lists.
	 */
	private static void buildNormalNoisePart(Sequence root, boolean extractNoise) {
		if (!extractNoise) {
			return;
		}
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		double[] noiseDiffs = new double[arrivalRateList.size()];
		int index = 0;
		double sum = 0.0;
		for (ArrivalRateTuple t : arrivalRateList) {
			noiseDiffs[index] = - t.getArrivalRate() + noisyArrivalRateList.get(index).getArrivalRate();
			sum += noiseDiffs[index];
			index++;
		}
		double mean = sum / arrivalRateList.size();
		double standardDeviation = 0.0;
		for (int i = 0; i < noiseDiffs.length; i++) {
			standardDeviation += (noiseDiffs[i] - mean)*(noiseDiffs[i] - mean);
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
	 * Extracts a uniformly distributed noise by calculating the difference between the filtered
	 * and unfiltered arrival rate lists. Sets the min and max at the 10 and 90th percentile.
	 */
	private static void setUniformNoisePart(HLDlimParameterContainer container, boolean extractNoise) {
		if (!extractNoise) {
			return;
		}
		double[] noiseDiffs = new double[arrivalRateList.size()];
		int index = 0;
		for (ArrivalRateTuple t : arrivalRateList) {
			noiseDiffs[index] = - t.getArrivalRate() + noisyArrivalRateList.get(index).getArrivalRate();
			index++;
		}
		Arrays.sort(noiseDiffs);
		container.setNoiseMin(noiseDiffs[(int)(noiseDiffs.length*0.1)]);
		container.setNoiseMax(noiseDiffs[(int)(noiseDiffs.length*0.9)]);
	}
	
	/*
	 * Setup the original and noisy (backup) arrival rate lists.
	 */
	private static void setupArrivalRateLists(List<ArrivalRateTuple> arrList) {
		arrivalRateList = arrList;
		noisyArrivalRateList.clear();
		for (ArrivalRateTuple t: arrivalRateList) {
			noisyArrivalRateList.add(new ArrivalRateTuple(t.getTimeStamp(),t.getArrivalRate()));
		}
	}
}
