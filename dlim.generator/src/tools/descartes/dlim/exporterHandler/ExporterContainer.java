/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporterHandler;

import tools.descartes.dlim.exporter.IDlimExporter;

/**
 * Contains an Exporter implementing the Exporter extension point.
 *
 * @author Joakim von Kistowski
 */
public class ExporterContainer {

	private IDlimExporter exporter;
	private String label;

	/**
	 * Create a new exporter extension with a label.
	 *
	 * @param label the label
	 * @param exporter the exporter
	 */
	public ExporterContainer(String label, IDlimExporter exporter) {
		this.exporter = exporter;
		this.label = label;
	}

	/**
	 * Get the exporter.
	 *
	 * @return the exporter
	 */
	public IDlimExporter getExporter() {
		return exporter;
	}

	/**
	 * Set the exporter.
	 *
	 * @param exporter the new exporter
	 */
	public void setExporter(IDlimExporter exporter) {
		this.exporter = exporter;
	}

	/**
	 * Get the exporter label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the exporter label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
