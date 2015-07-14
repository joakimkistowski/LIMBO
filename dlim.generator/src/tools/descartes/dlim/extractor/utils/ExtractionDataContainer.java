/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.ArrayList;
import java.util.List;

import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Container for all sorts of data to be carried in between the private methods of the ModelExtractor.
 * 
 * @author Joakim von Kistowski
 *
 */
public class ExtractionDataContainer {

	/**
	 *  The local minima within the read arrival rate list.
	 */
	private ArrayList<ArrivalRateTuple> localMins = new ArrayList<ArrivalRateTuple>();

	/**
	 *  The local maxima within the read arrival rate list.
	 */
	private ArrayList<ArrivalRateTuple> localMaxes = new ArrayList<ArrivalRateTuple>();
	
	/** The arrival rate list. */
	private List<ArrivalRateTuple> arrivalRateList;
	
	/** The noisy arrival rate list. */
	private ArrayList<ArrivalRateTuple> noisyArrivalRateList = new ArrayList<ArrivalRateTuple>();
	
	/** The bursts. */
	private ArrayList<ArrivalRateTuple> bursts = new ArrayList<ArrivalRateTuple>();
	
	/** The period. */
	private double period;
	
	/** The duration. */
	private double duration;
	
	/** The seasonals per trend. */
	private int seasonalsPerTrend;
	
	/** The seasonal shape. */
	private String seasonalShape;
	
	/** The trend shape. */
	private String trendShape;
	
	/** The operator literal. */
	private String operatorLiteral;
	
	/** The extract noise. */
	private boolean extractNoise;
	
	/** The base. */
	private double base;
	
	/** The peak num. */
	private int peakNum;
	
	/** The max peak offset. */
	private double maxPeakOffset;
	
	/** The burst width. */
	private double burstWidth;
	
	/** The peaks. */
	private ArrivalRateTuple[] peaks;
	
	/** The inner bases. */
	private ArrivalRateTuple[] innerBases;
	
	/** The trend point values. */
	private double[] trendPointValues;
	
	/**
	 * The beginning time offset of the container's arrival rate list.
	 */
	private double beginTime = 0;
	
	/** The sub container list. */
	private ArrayList<ExtractionDataContainer> subContainerList = new ArrayList<ExtractionDataContainer>();
	
	
	/**
	 * Instantiates a new extraction data container.
	 *
	 * @param arrList the arr list
	 * @param period the period
	 * @param seasonalsPerTrend the seasonals per trend
	 * @param seasonalShape the seasonal shape
	 * @param trendShape the trend shape
	 * @param operatorLiteral the operator literal
	 */
	public ExtractionDataContainer(
			List<ArrivalRateTuple> arrList, double period,
			int seasonalsPerTrend, String seasonalShape, String trendShape,
			String operatorLiteral) {
		this.arrivalRateList = arrList;
		this.period = period;
		this.seasonalsPerTrend = seasonalsPerTrend;
		this.seasonalShape = seasonalShape;
		this.trendShape = trendShape;
		this.operatorLiteral = operatorLiteral;
	}
	
	/**
	 * Gets the local arrival rate list.
	 *
	 * @return the local arrival rate list
	 */
	public List<ArrivalRateTuple> getArrivalRateList() {
		return arrivalRateList;
	}
	
	/**
	 * Sets the arrival rate list.
	 *
	 * @param arrivalRateList the new arrival rate list
	 */
	public void setArrivalRateList(List<ArrivalRateTuple> arrivalRateList) {
		this.arrivalRateList = arrivalRateList;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	/**
	 * Gets the seasonals per trend.
	 *
	 * @return the seasonals per trend
	 */
	public int getSeasonalsPerTrend() {
		return seasonalsPerTrend;
	}
	
	/**
	 * Sets the seasonals per trend.
	 *
	 * @param seasonalsPerTrend the new seasonals per trend
	 */
	public void setSeasonalsPerTrend(int seasonalsPerTrend) {
		this.seasonalsPerTrend = seasonalsPerTrend;
	}
	
	/**
	 * Gets the seasonal shape.
	 *
	 * @return the seasonal shape
	 */
	public String getSeasonalShape() {
		return seasonalShape;
	}
	
	/**
	 * Sets the seasonal shape.
	 *
	 * @param seasonalShape the new seasonal shape
	 */
	public void setSeasonalShape(String seasonalShape) {
		this.seasonalShape = seasonalShape;
	}
	
	/**
	 * Gets the trend shape.
	 *
	 * @return the trend shape
	 */
	public String getTrendShape() {
		return trendShape;
	}
	
	/**
	 * Sets the trend shape.
	 *
	 * @param trendShape the new trend shape
	 */
	public void setTrendShape(String trendShape) {
		this.trendShape = trendShape;
	}
	
	/**
	 * Gets the operator literal.
	 *
	 * @return the operator literal
	 */
	public String getOperatorLiteral() {
		return operatorLiteral;
	}
	
	/**
	 * Sets the operator literal.
	 *
	 * @param operatorLiteral the new operator literal
	 */
	public void setOperatorLiteral(String operatorLiteral) {
		this.operatorLiteral = operatorLiteral;
	}
	
	/**
	 * Checks if is extract noise.
	 *
	 * @return true, if is extract noise
	 */
	public boolean isExtractNoise() {
		return extractNoise;
	}
	
	/**
	 * Sets the extract noise.
	 *
	 * @param extractNoise the new extract noise
	 */
	public void setExtractNoise(boolean extractNoise) {
		this.extractNoise = extractNoise;
	}
	
	/**
	 * Gets the base.
	 *
	 * @return the base
	 */
	public double getBase() {
		return base;
	}
	
	/**
	 * Sets the base.
	 *
	 * @param base the new base
	 */
	public void setBase(double base) {
		this.base = base;
	}
	
	/**
	 * Gets the peak num.
	 *
	 * @return the peak num
	 */
	public int getPeakNum() {
		return peakNum;
	}
	
	/**
	 * Sets the peak num.
	 *
	 * @param peakNum the new peak num
	 */
	public void setPeakNum(int peakNum) {
		this.peakNum = peakNum;
	}
	
	/**
	 * Gets the peaks.
	 *
	 * @return the peaks
	 */
	public ArrivalRateTuple[] getPeaks() {
		return peaks;
	}
	
	/**
	 * Sets the peaks.
	 *
	 * @param peaks the new peaks
	 */
	public void setPeaks(ArrivalRateTuple[] peaks) {
		this.peaks = peaks;
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public double getPeriod() {
		return period;
	}

	/**
	 * Sets the period.
	 *
	 * @param period the new period
	 */
	public void setPeriod(double period) {
		this.period = period;
	}

	/**
	 * Gets the local mins.
	 *
	 * @return the local mins
	 */
	public ArrayList<ArrivalRateTuple> getLocalMins() {
		return localMins;
	}

	/**
	 * Gets the local maxes.
	 *
	 * @return the local maxes
	 */
	public ArrayList<ArrivalRateTuple> getLocalMaxes() {
		return localMaxes;
	}

	/**
	 * Sets the local mins.
	 *
	 * @param localMins the new local mins
	 */
	public void setLocalMins(ArrayList<ArrivalRateTuple> localMins) {
		this.localMins = localMins;
	}

	/**
	 * Sets the local maxes.
	 *
	 * @param localMaxes the new local maxes
	 */
	public void setLocalMaxes(ArrayList<ArrivalRateTuple> localMaxes) {
		this.localMaxes = localMaxes;
	}
	
	/**
	 * Gets the noisy arrival rate list.
	 *
	 * @return the noisy arrival rate list
	 */
	public ArrayList<ArrivalRateTuple> getNoisyArrivalRateList() {
		return noisyArrivalRateList;
	}

	/**
	 * Gets the max peak offset.
	 *
	 * @return the max peak offset
	 */
	public double getMaxPeakOffset() {
		return maxPeakOffset;
	}

	/**
	 * Sets the max peak offset.
	 *
	 * @param maxPeakOffset the new max peak offset
	 */
	public void setMaxPeakOffset(double maxPeakOffset) {
		this.maxPeakOffset = maxPeakOffset;
	}

	/**
	 * Gets the trend point values.
	 *
	 * @return the trend point values
	 */
	public double[] getTrendPointValues() {
		return trendPointValues;
	}

	/**
	 * Sets the trend point values.
	 *
	 * @param trendPointValues the new trend point values
	 */
	public void setTrendPointValues(double[] trendPointValues) {
		this.trendPointValues = trendPointValues;
	}

	/**
	 * Sets the bursts.
	 *
	 * @param bursts the new bursts
	 */
	public void setBursts(ArrayList<ArrivalRateTuple> bursts) {
		this.bursts = bursts;
	}

	/**
	 * Gets the bursts.
	 *
	 * @return the bursts
	 */
	public ArrayList<ArrivalRateTuple> getBursts() {
		return bursts;
	}

	/**
	 * Gets the burst width.
	 *
	 * @return the burst width
	 */
	public double getBurstWidth() {
		return burstWidth;
	}
	
	/**
	 * Sets the burst width.
	 *
	 * @param burstWidth the new burst width
	 */
	public void setBurstWidth(double burstWidth) {
		this.burstWidth = burstWidth;
	}

	/**
	 * Gets the inner bases.
	 *
	 * @return the inner bases
	 */
	public ArrivalRateTuple[] getInnerBases() {
		return innerBases;
	}

	/**
	 * Sets the inner bases.
	 *
	 * @param innerBases the new inner bases
	 */
	public void setInnerBases(ArrivalRateTuple[] innerBases) {
		this.innerBases = innerBases;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ExtractionDataContainer clone() {
		ExtractionDataContainer c = new ExtractionDataContainer(arrivalRateList, period,
				seasonalsPerTrend, seasonalShape, trendShape, operatorLiteral);
		c.localMaxes = localMaxes;
		c.localMins = localMins;
		c.noisyArrivalRateList = noisyArrivalRateList;
		c.bursts = bursts;
		c.duration = duration;
		c.extractNoise = extractNoise;
		c.base = base;
		c.peakNum = peakNum;
		c.maxPeakOffset = maxPeakOffset;
		c.burstWidth = burstWidth;
		c.innerBases = innerBases;
		c.peaks = peaks;
		c.trendPointValues = trendPointValues;
		c.subContainerList = subContainerList;
		return c;
	}

	/**
	 * Gets the beginning time offset of the container's arrival rate list.
	 *
	 * @return the begin time
	 */
	public double getBeginTime() {
		return beginTime;
	}

	
	/**
	 * Sets the beginning time offset of the container's arrival rate list.
	 *
	 * @param beginTime the new begin time
	 */
	public void setBeginTime(double beginTime) {
		this.beginTime = beginTime;
	}

	
	/**
	 * Gets the sub container list.
	 *
	 * @return the sub container list
	 */
	public ArrayList<ExtractionDataContainer> getSubContainerList() {
		return subContainerList;
	}
}
