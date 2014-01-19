package dlim.reader;

import java.io.IOException;
import java.util.List;

import dlim.generator.ArrivalRateTuple;

/**
 * Default arrival rate reader. Reads the default arrival rate format as produced by the
 * dlim.exporter plugin.
 * @author Jóakim G. v. Kistowski
 */
public class DefaultArrivalRateReader implements IDlimArrivalRateReader {

	/**
	 * Reads an arrival rate trace into a arrival rate tuple list.
	 * Starts reading at the given time offset.
	 */
	@Override
	public List<ArrivalRateTuple> readFileToList(String filePath, double offset)
			throws IOException {
		return ArrivalRateReader.readFileToList(filePath, offset);
	}

}
