package dlim.exporter.utils;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import com.ibm.icu.math.BigDecimal;

import dlim.generator.ArrivalRateTuple;

public class EqualDistanceTimestampWriter extends TimeStampWriter {

	public EqualDistanceTimestampWriter() {
		super();
	}
	
	/**
	 * 
	 * @param endOfLineCharacter The character before the end of a line in the output file. Note: the "\n" is always
	 * printed after this character. It does not have to be included here.
	 */
	public EqualDistanceTimestampWriter(String endOfLineCharacter) {
		super(endOfLineCharacter);
	}
	
	protected void writeTimestampsForArrivalRate(PrintWriter writer, double step,
			double arrRate, double tmpStep, double tmpTime) {
		int loops = (int)(arrRate*tmpStep);
		for (double j = 0; j < loops; j++) {
			BigDecimal bd = new BigDecimal(tmpTime+ (j/loops)*tmpStep - tmpStep/2.0);
			bd = bd.setScale(decimalplaces, BigDecimal.ROUND_HALF_UP);
			writer.println(bd.doubleValue() + endOfLineCharacter);
		}
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
	 * @param endOfLineCharacter The character before the end of a line in the output file. Note: the "\n" is always
	 * 					printed after this character. It does not have to be included here.
	 */
	public void generateTimeStampsFromArrivalRates(File file, List<ArrivalRateTuple> arrRates,
			int decimalPlaces, double stretch, double arDevisor)
	{
		super.generateTimeStampsFromArrivalRates(file, arrRates,
				decimalPlaces, stretch, arDevisor);
	}
}
