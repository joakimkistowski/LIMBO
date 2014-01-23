package dlim.extractorHandler;

import dlim.extractor.IDlimExtractor;
import dlim.reader.IDlimArrivalRateReader;

/**
 * Contains an Extractor that implements the Extractor extension point.
 * @author Jóakim G. v. Kistowski
 */
public class ExtractorContainer {
	  
	private IDlimExtractor extractor;
	private IDlimArrivalRateReader reader;
	private String label;

	/**
	 * Create a new extractor extension point implementation.
	 * Needs a given label, an IDlimExtractor implementation, and an IDlimArrivalRateReader implementation.
	 * @param label
	 * @param extractor
	 * @param reader
	 */
	public ExtractorContainer(String label, IDlimExtractor extractor, IDlimArrivalRateReader reader) {
		this.extractor = extractor;
		this.reader = reader;
		this.label = label;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public IDlimExtractor getExtractor() {
		return extractor;
	}


	public void setExtractor(IDlimExtractor extractor) {
		this.extractor = extractor;
	}


	public IDlimArrivalRateReader getReader() {
		return reader;
	}


	public void setReader(IDlimArrivalRateReader reader) {
		this.reader = reader;
	}
}
