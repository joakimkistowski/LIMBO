package dlim.exporterHandler;

import dlim.exporter.IDlimExporter;

/**
 * Contains an Exporter implementing the Exporter extension point.
 * @author Jóakim G. v. Kistowski
 */
public class ExporterContainer {
	  
	private IDlimExporter exporter;
	private String label;

	/**
	 * Create a new exporter extension with a label.
	 * @param label
	 * @param exporter
	 */
	public ExporterContainer(String label, IDlimExporter exporter) {
		this.exporter = exporter;
		this.label = label;
	}

	/**
	 * Get the exporter.
	 * @return
	 */
  	public IDlimExporter getExporter() {
  		return exporter;
	}

  	/**
  	 * Set the exporter.
  	 * @param exporter
  	 */
	public void setExporter(IDlimExporter exporter) {
		this.exporter = exporter;
	}

	/**
	 * Get the exporter label.
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the exporter label.
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
