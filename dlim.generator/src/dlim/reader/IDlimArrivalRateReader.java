package dlim.reader;

import java.io.IOException;
import java.util.List;

import dlim.generator.ArrivalRateTuple;

/**
 * This Interface must be implemented for the Extractor extension point.
 * Reads an arrival rate trace file into an in memory list. Implement this for parsing an
 * unsupported format. Use dlim.reader.DefaultArrivalRateReader for the default format as
 * used by dlim.extractor and dlim.exporter plugins.
 * @author Jóakim G. v. Kistowski
 */
public interface IDlimArrivalRateReader {

	/**
	 * Reads an arrival rate file into an in-memory arrival rate tuple list.
	 * @param filePath The file's path.
	 * @param offset The time offset at which to start reading
	 * @return
	 * @throws IOException
	 */
	public List<ArrivalRateTuple> readFileToList(String filePath, double offset) throws IOException;
}
