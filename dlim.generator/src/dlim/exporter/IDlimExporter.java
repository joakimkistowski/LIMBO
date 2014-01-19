package dlim.exporter;

import dlim.generator.ModelEvaluator;

/**
 * This Interface must be implemented for the implementation of the Extractor extension point.
 * @author Jóakim G. v. Kistowski
 */
public interface IDlimExporter {

	/**
	 * Export a DLIM instance.
	 * @param projectPath The Eclipse-project within which the DLIM file is located.
	 * @param modelPath The absolute path of the model file.
	 * @param evaluator The DLIM model evaluator instance.
	 */
	public void export(String projectPath, String modelPath, ModelEvaluator evaluator);

}
