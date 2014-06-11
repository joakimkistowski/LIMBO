package dlim.exporter.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import dlim.generator.ArrivalRateTuple;

/**
 * Creates a time-stamp file from an arrival rate list.
 * Abstract class. Subclasses must implement the
 * {@link #writeTimestampsForArrivalRate(PrintWriter writer, double step, double arrRate, double tmpStep, double tmpTime) writeTimestampsForArrivalRate} 
 * method to define the exact sampling of timestamps
 * @author Jóakim G. v. Kistowski, Andreas Weber
 *
 */
public abstract class TimeStampWriter {

	protected String endOfLineCharacter = ";";
	protected int decimalplaces = 3;
	protected double stretch = 1.0;


	/**
	 * 
	 * @param endOfLineCharacter The character before the end of a line in the output file. Note: the "\n" is always
	 * printed after this character. It does not have to be included here.
	 */
	protected TimeStampWriter(String endOfLineCharacter) {
		this.endOfLineCharacter = endOfLineCharacter;
	}

	protected TimeStampWriter() {
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
	 */
	public void generateTimeStampsFromArrivalRates(File file, List<ArrivalRateTuple> arrRates,
			int decimalPlaces, double stretch, double arDevisor) {
		this.stretch = stretch;
		this.decimalplaces = decimalPlaces;
		try {
			PrintWriter timeStampWriter = new PrintWriter(file, "UTF-8");
			ArrivalRateTuple lastTupel = null;
			for (ArrivalRateTuple tuple : arrRates) {
				double step = tuple.getStep(lastTupel);
				double arrRate = tuple.getArrivalRate()/arDevisor;
				double time = tuple.getTimeStamp();

				double tmpStep = step*stretch;
				double tmpTime = time*stretch;
				writeTimestampsForArrivalRate(timeStampWriter, step, arrRate, tmpStep, tmpTime);
				lastTupel = tuple;
			}
			timeStampWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not write time stamps.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Could not write time stamps.");
			e.printStackTrace();
		}
	}

	protected abstract void writeTimestampsForArrivalRate(PrintWriter writer, double step,
			double arrRate, double tmpStep, double tmpTime);

}
