/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.exporter;

import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * This Interface must be implemented for the implementation of the Extractor
 * extension point.
 * 
 * @author Joakim von Kistowski
 */
public interface IDlimExporter {

	/**
	 * Export a DLIM instance.
	 * 
	 * @param projectPath
	 *            The Eclipse-project within which the DLIM file is located.
	 * @param modelPath
	 *            The absolute path of the model file.
	 * @param evaluator
	 *            The DLIM model evaluator instance.
	 */
	public void export(String projectPath, String modelPath,
			ModelEvaluator evaluator);

}
