/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractorHandler;

import tools.descartes.dlim.extractor.IDlimExtractor;
import tools.descartes.dlim.reader.IDlimArrivalRateReader;

/**
 * Contains an Extractor that implements the Extractor extension point.
 *
 * @author Joakim von Kistowski
 */
public class ExtractorContainer {

	private IDlimExtractor extractor;
	private IDlimArrivalRateReader reader;
	private String label;

	/**
	 * Create a new extractor extension point implementation. Needs a given
	 * label, an IDlimExtractor implementation, and an IDlimArrivalRateReader
	 * implementation.
	 *
	 * @param label the label
	 * @param extractor the extractor
	 * @param reader the reader
	 */
	public ExtractorContainer(String label, IDlimExtractor extractor,
			IDlimArrivalRateReader reader) {
		this.extractor = extractor;
		this.reader = reader;
		this.label = label;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the extractor.
	 *
	 * @return the extractor
	 */
	public IDlimExtractor getExtractor() {
		return extractor;
	}

	/**
	 * Sets the extractor.
	 *
	 * @param extractor the new extractor
	 */
	public void setExtractor(IDlimExtractor extractor) {
		this.extractor = extractor;
	}

	/**
	 * Gets the reader.
	 *
	 * @return the reader
	 */
	public IDlimArrivalRateReader getReader() {
		return reader;
	}

	/**
	 * Sets the reader.
	 *
	 * @param reader the new reader
	 */
	public void setReader(IDlimArrivalRateReader reader) {
		this.reader = reader;
	}
}
