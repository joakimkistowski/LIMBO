package dlim.generator.util;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;

import dlim.AbsoluteSin;
import dlim.Burst;
import dlim.ExponentialIncreaseAndDecline;
import dlim.ExponentialIncreaseLogarithmicDecline;
import dlim.ExponentialTrend;
import dlim.Function;
import dlim.LinearIncreaseAndDecline;
import dlim.LinearTrend;
import dlim.LogarithmicTrend;
import dlim.Noise;
import dlim.NormalNoise;
import dlim.Polynomial;
import dlim.PolynomialFactor;
import dlim.Seasonal;
import dlim.Sin;
import dlim.SinTrend;
import dlim.Trend;
import dlim.UniformNoise;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluatorUtil;

/**
 * Calculates and returns the arrival rate values for all simple Functions.
 * Simple Functions are all children of dlim.Function, that do not nest other Functions.
 * @author Jóakim G. v. Kistowski
 *
 */
public class FunctionValueCalculator {

	//Random number generation for Noise
	private JDKRandomGenerator rndGenerator;
	private NormalDistribution nDistribution;
	
	//This is IGeneratorConstants.CALIBRATION, if Noises are to have no effect.
	private String noiseMode = "";
	
	/**
	 * Create a new FunctionValueCalculator.
	 * @param rndGenerator The random number generator for Noises.
	 * @param noiseMode Set this to IGeneratorConstants.CALIBRATION if Noises are to return 0.
	 */
	public FunctionValueCalculator(JDKRandomGenerator rndGenerator, String noiseMode) {
		this.rndGenerator = rndGenerator;
		nDistribution = new NormalDistribution(rndGenerator, 0, 1, NormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
		this.noiseMode = noiseMode;
	}
	
	/**
	 * Returns the value of a Function f with input x.
	 * @param f
	 * @param x
	 * @return f(x)
	 */
	public double getSimpleFunctionValue(Function f, double x) {
		if (f instanceof Polynomial) {
			return getPolynomialValue((Polynomial)f, x);
		} else if (f instanceof Noise) {
			return getNoiseValue((Noise)f, x);
		} else if (f instanceof Seasonal) {
			return getSeasonalValue((Seasonal)f,x);
		} else if (f instanceof Burst) {
			return getBurstValue((Burst)f, x);
		} else if (f instanceof Trend) {
			return getTrendValue((Trend)f,x);
		} else {
			System.out.println("Function matches no Category: " + f.toString());
			return 0.0;
		}
	}
	
	public void setNoiseMode(String noiseMode) {
		this.noiseMode = noiseMode;
	}
	
	private double getPolynomialValue(Polynomial f, double x) {
		int index = f.getFactors().size()-1;
		double value = 0;
		for (PolynomialFactor factor : f.getFactors()) {
			if (index == 0) {
				value += factor.getFactor();
			} else {
				value += factor.getFactor() * Math.pow(x+factor.getOffset(), index);
			}
			index--;
		}
		return value;
	}
	
	private double getNoiseValue(Noise f, double x) {
		if (noiseMode.contains(IGeneratorConstants.CALIBRATION)){
			return 0.0;
		}
		if (f instanceof UniformNoise) {
			UniformNoise noise = (UniformNoise)f;
			return noise.getMin() + (noise.getMax() - noise.getMin()) * rndGenerator.nextDouble();
		} else if (f instanceof NormalNoise) {
			NormalNoise noise = (NormalNoise)f;
			return noise.getMean() + noise.getStandardDeviation() * nDistribution.sample();
		} else {
			System.out.println("Unknown Noise: " + f.toString());
			return 0.0;
		}
	}
	
	private double getSeasonalValue(Seasonal f, double x) {
		if (f instanceof Sin) {
			Sin sin = (Sin)f;
			if (f instanceof AbsoluteSin) {
				return sin.getMin() + (sin.getMax() - sin.getMin()) * Math.abs(Math.sin((x+sin.getPhase())*2*Math.PI/sin.getPeriod()));
			} else {
				return sin.getMin() + ((sin.getMax()-sin.getMin())/2.0) + ((sin.getMax()-sin.getMin())/2.0) * Math.sin((x+sin.getPhase())*2*Math.PI/sin.getPeriod());
			}
		} else {
			System.out.println("Unknown Seasonal: " + f.toString());
			return 0.0;
		}
	}
	
	private double getBurstValue(Burst f, double x) {
		if (f instanceof LinearIncreaseAndDecline) {
			LinearIncreaseAndDecline burst = (LinearIncreaseAndDecline)f;
			return calculateLinearIncreaseAndDeclineValue(x, burst.getBase(), burst.getPeak(), burst.getPeakTime(), ModelEvaluatorUtil.getFunctionDuration(f));
		} else if (f instanceof ExponentialIncreaseAndDecline) {
			ExponentialIncreaseAndDecline burst = (ExponentialIncreaseAndDecline)f;
			return calculateExponentialIncreaseAndDeclineValue(x, burst.getBase(), burst.getPeak(), burst.getPeakTime(), ModelEvaluatorUtil.getFunctionDuration(f));
		} else if (f instanceof ExponentialIncreaseLogarithmicDecline) {
			ExponentialIncreaseLogarithmicDecline burst = (ExponentialIncreaseLogarithmicDecline)f;
			return calculateExponentialIncreaseLogarthmicDeclineValue(x, burst.getBase(), burst.getPeak(), burst.getPeakTime(), burst.getLogarithmicOrder(), ModelEvaluatorUtil.getFunctionDuration(f));
		} else {
			System.out.println("Unknown Burst: " + f.toString());
			return 0.0;
		}
	}
	
	private double getTrendValue(Trend f, double x) {
		if (f instanceof LinearTrend) {
			LinearTrend trend = (LinearTrend)f;
			return trend.getFunctionOutputAtStart() + x*(trend.getFunctionOutputAtEnd() - trend.getFunctionOutputAtStart())/ModelEvaluatorUtil.getFunctionDuration(f);
		} else if (f instanceof ExponentialTrend) {
			ExponentialTrend trend = (ExponentialTrend)f;
			return calculateExponentialTrendValue(x, trend.getFunctionOutputAtEnd(), trend.getFunctionOutputAtStart(), ModelEvaluatorUtil.getFunctionDuration(f));
		} else if (f instanceof LogarithmicTrend) {
			LogarithmicTrend trend = (LogarithmicTrend)f;
			return calculateLogarithmicTrendValue(x, trend.getFunctionOutputAtEnd(), trend.getFunctionOutputAtStart(), trend.getOrder(), ModelEvaluatorUtil.getFunctionDuration(f));
		} else if (f instanceof SinTrend) {
			SinTrend trend = (SinTrend)f;
			return calculateSinTrendValue(x, trend.getFunctionOutputAtEnd(), trend.getFunctionOutputAtStart(), ModelEvaluatorUtil.getFunctionDuration(f));
		} else {
			System.out.println("Unknown Trend: " + f.toString());
			return 1.0;
		}
	}
	
	private double calculateLogarithmicTrendValue(double x, double endValue, double startValue, double order, double duration) {
		if (startValue > endValue) {
			double tmpX = Math.abs(x-duration);
			return endValue + (startValue-endValue)*(1/order)*Math.log((tmpX*(Math.exp(order)-1)/duration) + 1);
		}
		return startValue + (endValue-startValue)*(1/order)*Math.log((x*(Math.exp(order)-1)/duration) + 1);
	}
	
	private double calculateSinTrendValue(double x, double endValue, double startValue, double duration) {
		double minValue = startValue;
		double maxValue = endValue;
		double phase = -(Math.PI/2);
		if (startValue > endValue) {
			minValue = endValue;
			maxValue = startValue;
			phase = (Math.PI/2);
		}
		return minValue+((maxValue-minValue)/2)+((maxValue-minValue)/2)*Math.sin(phase+x*Math.PI/duration);
	}
	
	private double calculateExponentialTrendValue(double x, double endValue, double startValue, double duration) {
		double offset = 0;
		double start = startValue;
		double end = endValue;
		double minValue = Math.min(startValue,endValue);
		if (minValue <= 0) {
			offset = minValue - 0.5;
			start = start + 0.5 - minValue;
			end = end + 0.5 - minValue;
		}
		return offset + Math.exp(Math.log(start) + ((Math.log(end)-Math.log(start))
			* (x/duration)));
	}
	
	private double calculateLinearIncreaseAndDeclineValue(double x, double baseValue, double peakValue, double peakTime, double duration) {
		double tmpX = x;
		if (x > peakTime) {
			tmpX = peakTime - ((x-peakTime)*peakTime/(duration-peakTime));
		}
		return baseValue + ((peakValue-baseValue)/ peakTime) * tmpX;
	}
	
	private double calculateExponentialIncreaseAndDeclineValue(double x, double baseValue, double peakValue, double peakTime, double duration) {
		double tmpX = x;
		if (x > peakTime) {
			tmpX = peakTime - ((x-peakTime)*peakTime/(duration-peakTime));
		}
		return calculateExponentialTrendValue(tmpX, peakValue, baseValue, peakTime);
	}
	
	private double calculateExponentialIncreaseLogarthmicDeclineValue(double x, double baseValue, double peakValue, double peakTime, double order,double duration) {
		double tmpX = x;
		if (x > peakTime) {
			tmpX = peakTime - ((x-peakTime)*peakTime/(duration-peakTime));
			//return baseValue + (peakValue-baseValue)*(1/order)*Math.log((tmpX*(Math.exp(order)-1)/peakTime) + 1);
			return calculateLogarithmicTrendValue(tmpX, peakValue, baseValue, order, peakTime);
		} else {
			return calculateExponentialTrendValue(tmpX, peakValue, baseValue, peakTime);
		}
	}
}
