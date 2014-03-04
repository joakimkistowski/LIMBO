package dlim.exporter.utils;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.random.JDKRandomGenerator;

import com.ibm.icu.math.BigDecimal;

import dlim.generator.ArrivalRateTuple;

public class UniformDistributionTimestampWriter extends TimeStampWriter {

	
	//The random number generator
	private JDKRandomGenerator rndGenerator;
	double duration;

	//The list of time-stamps within each interval.
	private List<Double> timeStampList = new ArrayList<Double>();


	public UniformDistributionTimestampWriter(JDKRandomGenerator rndGenerator) {
		super();
		this.rndGenerator = rndGenerator;
	}

	public UniformDistributionTimestampWriter(String endOfLineCharacter, JDKRandomGenerator rndGenerator) {
		super(endOfLineCharacter);
		this.rndGenerator = rndGenerator;
	}
	
	
	/**
	 * 
	 * @param file The filename of the produced output file
	 * @param arrRates The list of arrival rates as provided by the ArrivalRateGenerator.
	 * @param decimalPlaces The amount of decimal places a time-stamp is allowed to have.
	 * @param stretch The factor by which to stretch the times of the arrival rate tuples.
	 * 					Using a value < 1 compresses the time.
	 * @param arDevisor Divide the arrival rates from the arrival rate tuples by this to produce less time-stamps.
	 * 					Using a value < 1 produces more time stamps
	 * @param duration Duration of root sequence
	 */
	public void generateTimeStampsFromArrivalRates(File file, List<ArrivalRateTuple> arrRates,
			int decimalPlaces, double stretch, double arDevisor, double duration) {
		this.duration = duration;

		generateTimeStampsFromArrivalRates(file, arrRates,
				decimalPlaces, stretch, arDevisor);
	}

	//Adds the time-stamp to the list of time-stamps for this arrival rate tuple
	private void addBDToList(BigDecimal bd) {
		if (bd != null) {

			//clamp value
			if (bd.doubleValue() > duration*stretch) {
				bd = new BigDecimal(duration*stretch);
			} else if (bd.doubleValue() < 0.0) {
				bd = new BigDecimal(0.0);
			}
			bd = bd.setScale(decimalplaces, BigDecimal.ROUND_HALF_UP);
			timeStampList.add(bd.doubleValue());
		}
	}

	//Print the time-stamp list to the .txt file
	//Don't forget to sort first.
	private void printList(PrintWriter writer) {
		Collections.sort(timeStampList);
		for (Double timeStamp : timeStampList) {
			writer.println(timeStamp + endOfLineCharacter);
		}
	}

	protected void writeTimestampsForArrivalRate(PrintWriter writer, double step,
			double arrRate, double tmpStep, double tmpTime) {
		timeStampList.clear();
		for (double j = 0; j < (int)(arrRate*tmpStep); j++) {
			BigDecimal bd = new BigDecimal(tmpTime + (rndGenerator.nextDouble() - 0.5)*(step*stretch));
			addBDToList(bd);
		}
		printList(writer);
	}
}
