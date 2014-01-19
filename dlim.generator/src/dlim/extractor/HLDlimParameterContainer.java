package dlim.extractor;

/**
 * Contains the results of the extraction process,
 * to be used for the new Dlim Model Wizard.
 * @author Jóakim v. Kistowski
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
	
	
	public double getFirstPeak() {
		return firstPeak;
	}
	public void setFirstPeak(double firstPeak) {
		this.firstPeak = firstPeak;
	}
	public double getLastPeak() {
		return lastPeak;
	}
	public void setLastPeak(double lastPeak) {
		this.lastPeak = lastPeak;
	}
	public double getPeakIntervalWidth() {
		return peakIntervalWidth;
	}
	public void setPeakIntervalWidth(double peakIntervalWidth) {
		this.peakIntervalWidth = peakIntervalWidth;
	}
	public double getSeasonalPeriod() {
		return seasonalPeriod;
	}
	public void setSeasonalPeriod(double seasonalPeriod) {
		this.seasonalPeriod = seasonalPeriod;
	}
	public int getPeakNum() {
		return peakNum;
	}
	public void setPeakNum(int peakNum) {
		this.peakNum = peakNum;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getInnerBase() {
		return innerBase;
	}
	public void setInnerBase(double innerBase) {
		this.innerBase = innerBase;
	}
	public String getSeasonalShape() {
		return seasonalShape;
	}
	public void setSeasonalShape(String seasonalShape) {
		this.seasonalShape = seasonalShape;
	}
	public double getSeasonalsPerTrend() {
		return seasonalsPerTrend;
	}
	public void setSeasonalsPerTrend(double seasonalsPerTrend) {
		this.seasonalsPerTrend = seasonalsPerTrend;
	}
	public double getTrendOffset() {
		return trendOffset;
	}
	public void setTrendOffset(double trendOffset) {
		this.trendOffset = trendOffset;
	}
	public double[] getTrendPoints() {
		return trendPoints;
	}
	public void setTrendPoints(double[] trendPoints) {
		this.trendPoints = trendPoints;
	}
	public String getTrendShape() {
		return trendShape;
	}
	public void setTrendShape(String trendShape) {
		this.trendShape = trendShape;
	}
	public String getOperatorLiteral() {
		return operatorLiteral;
	}
	public void setOperatorLiteral(String operatorLiteral) {
		this.operatorLiteral = operatorLiteral;
	}
	public double getBurstPeak() {
		return burstPeak;
	}
	public void setBurstPeak(double burstPeak) {
		this.burstPeak = burstPeak;
	}
	public double getBurstWidth() {
		return burstWidth;
	}
	public void setBurstWidth(double burstWidth) {
		this.burstWidth = burstWidth;
	}
	public double getBurstPeriod() {
		return burstPeriod;
	}
	public void setBurstPeriod(double burstPeriod) {
		this.burstPeriod = burstPeriod;
	}
	public double getBurstOffset() {
		return burstOffset;
	}
	public void setBurstOffset(double burstOffset) {
		this.burstOffset = burstOffset;
	}
	public double getNoiseMin() {
		return noiseMin;
	}
	public void setNoiseMin(double noiseMin) {
		this.noiseMin = noiseMin;
	}
	public double getNoiseMax() {
		return noiseMax;
	}
	public void setNoiseMax(double noiseMax) {
		this.noiseMax = noiseMax;
	}
	
}
