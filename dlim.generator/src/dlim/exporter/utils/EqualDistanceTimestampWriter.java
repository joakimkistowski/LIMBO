package dlim.exporter.utils;

import java.io.PrintWriter;

import com.ibm.icu.math.BigDecimal;

/**
 * Creates a time-stamp file from an arrival rate list.
 * Timestamps are sampled with equal distance.
 * @author Jóakim G. v. Kistowski, Andreas Weber
 *
 */
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
}
