/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.exporter.dialogs.ArrivalRateTimeStampParametersDialog;
import tools.descartes.dlim.exporter.utils.ArrivalRateGenerator;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Exports a simple Arrival Rate file with time-stamps for the respective
 * arrival rates.
 *
 * @author Joakim von Kistowski
 *
 */
public class DlimArrivalRateExporterCSV implements IDlimExporter {

	/**
	 * Exports a simple Arrival Rate file with time-stamps for the respective
	 * arrival rates.
	 *
	 * @param projectPath the project path
	 * @param modelPath the model path
	 * @param evaluator the evaluator
	 */
	@Override
	public void export(String projectPath, String modelPath,
			ModelEvaluator evaluator) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();

		ArrivalRateTimeStampParametersDialog paramDialog = new ArrivalRateTimeStampParametersDialog(
				evaluator, modelPath, shell);
		paramDialog.open();

		if (!paramDialog.wasCanceled()) {
			evaluator.setRandomSeed(paramDialog.getRndSeed());

			List<ArrivalRateTuple> arrRates = ArrivalRateGenerator
					.generateArrivalRates(evaluator, paramDialog.getStep());
			ArrivalRateGenerator.writeArrivalRates(arrRates, projectPath,
					evaluator.getName(), "", "csv");
		}
	}

}
