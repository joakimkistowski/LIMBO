package dlim.extractor;

import java.util.List;

import dlim.Sequence;
import dlim.generator.ArrivalRateTuple;

/**
 * This Interface must be implemented for the Extractor extension point.
 * @author Jóakim G. v. Kistowski
 */
public interface IDlimExtractor {

	/**
	 * Extract a Sequence from the List of read arrival rates.
	 * @param root The model's root Sequence.
	 * @param readArrivalRates a list of arrival rates with their respective time-stamps.
	 */
	public void extractIntoSequence(Sequence root, List<ArrivalRateTuple> readArrivalRates);
}
