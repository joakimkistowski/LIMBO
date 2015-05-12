/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor;

/**
 * Contains the results of the extraction process, to be used for the new Dlim
 * Model Wizard.
 *
 * @author Joakim von Kistowski
 *
 */
public class HLDlimParameterContainer {

	private double firstPeak;
	private double lastPeak;
	private double peakIntervalWidth;
	private double seasonalPeriod;
	private int peakNum;
	private double base;
	private double innerBase;
	private String seasonalShape;

	private double seasonalsPerTrend;
	private double trendOffset;
	private double[] trendPoints;
	private String trendShape;
	private String operatorLiteral;

	private double burstPeak;
	private double burstWidth;
	private double burstPeriod;
	private double burstOffset;

	private double noiseMin;
	private double noiseMax;

	/**
	 * Gets the first peak.
	 *
	 * @return the first peak
	 */
	public double getFirstPeak() {
		return firstPeak;
	}

	/**
	 * Sets the first peak.
	 *
	 * @param firstPeak the new first peak
	 */
	public void setFirstPeak(double firstPeak) {
		this.firstPeak = firstPeak;
	}

	/**
	 * Gets the last peak.
	 *
	 * @return the last peak
	 */
	public double getLastPeak() {
		return lastPeak;
	}

	/**
	 * Sets the last peak.
	 *
	 * @param lastPeak the new last peak
	 */
	public void setLastPeak(double lastPeak) {
		this.lastPeak = lastPeak;
	}

	/**
	 * Gets the peak interval width.
	 *
	 * @return the peak interval width
	 */
	public double getPeakIntervalWidth() {
		return peakIntervalWidth;
	}

	/**
	 * Sets the peak interval width.
	 *
	 * @param peakIntervalWidth the new peak interval width
	 */
	public void setPeakIntervalWidth(double peakIntervalWidth) {
		this.peakIntervalWidth = peakIntervalWidth;
	}

	/**
	 * Gets the seasonal period.
	 *
	 * @return the seasonal period
	 */
	public double getSeasonalPeriod() {
		return seasonalPeriod;
	}

	/**
	 * Sets the seasonal period.
	 *
	 * @param seasonalPeriod the new seasonal period
	 */
	public void setSeasonalPeriod(double seasonalPeriod) {
		this.seasonalPeriod = seasonalPeriod;
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
	 * Gets the inner base.
	 *
	 * @return the inner base
	 */
	public double getInnerBase() {
		return innerBase;
	}

	/**
	 * Sets the inner base.
	 *
	 * @param innerBase the new inner base
	 */
	public void setInnerBase(double innerBase) {
		this.innerBase = innerBase;
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
	 * Gets the seasonals per trend.
	 *
	 * @return the seasonals per trend
	 */
	public double getSeasonalsPerTrend() {
		return seasonalsPerTrend;
	}

	/**
	 * Sets the seasonals per trend.
	 *
	 * @param seasonalsPerTrend the new seasonals per trend
	 */
	public void setSeasonalsPerTrend(double seasonalsPerTrend) {
		this.seasonalsPerTrend = seasonalsPerTrend;
	}

	/**
	 * Gets the trend offset.
	 *
	 * @return the trend offset
	 */
	public double getTrendOffset() {
		return trendOffset;
	}

	/**
	 * Sets the trend offset.
	 *
	 * @param trendOffset the new trend offset
	 */
	public void setTrendOffset(double trendOffset) {
		this.trendOffset = trendOffset;
	}

	/**
	 * Gets the trend points.
	 *
	 * @return the trend points
	 */
	public double[] getTrendPoints() {
		return trendPoints;
	}

	/**
	 * Sets the trend points.
	 *
	 * @param trendPoints the new trend points
	 */
	public void setTrendPoints(double[] trendPoints) {
		this.trendPoints = trendPoints;
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
	 * Gets the burst peak.
	 *
	 * @return the burst peak
	 */
	public double getBurstPeak() {
		return burstPeak;
	}

	/**
	 * Sets the burst peak.
	 *
	 * @param burstPeak the new burst peak
	 */
	public void setBurstPeak(double burstPeak) {
		this.burstPeak = burstPeak;
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
	 * Gets the burst period.
	 *
	 * @return the burst period
	 */
	public double getBurstPeriod() {
		return burstPeriod;
	}

	/**
	 * Sets the burst period.
	 *
	 * @param burstPeriod the new burst period
	 */
	public void setBurstPeriod(double burstPeriod) {
		this.burstPeriod = burstPeriod;
	}

	/**
	 * Gets the burst offset.
	 *
	 * @return the burst offset
	 */
	public double getBurstOffset() {
		return burstOffset;
	}

	/**
	 * Sets the burst offset.
	 *
	 * @param burstOffset the new burst offset
	 */
	public void setBurstOffset(double burstOffset) {
		this.burstOffset = burstOffset;
	}

	/**
	 * Gets the noise min.
	 *
	 * @return the noise min
	 */
	public double getNoiseMin() {
		return noiseMin;
	}

	/**
	 * Sets the noise min.
	 *
	 * @param noiseMin the new noise min
	 */
	public void setNoiseMin(double noiseMin) {
		this.noiseMin = noiseMin;
	}

	/**
	 * Gets the noise max.
	 *
	 * @return the noise max
	 */
	public double getNoiseMax() {
		return noiseMax;
	}

	/**
	 * Sets the noise max.
	 *
	 * @param noiseMax the new noise max
	 */
	public void setNoiseMax(double noiseMax) {
		this.noiseMax = noiseMax;
	}

}
