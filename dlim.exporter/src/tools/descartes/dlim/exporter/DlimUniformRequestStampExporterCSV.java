/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.exporter.dialogs.RequestTimeStampParametersDialog;
import tools.descartes.dlim.exporter.utils.ArrivalRateGenerator;
import tools.descartes.dlim.exporter.utils.UniformDistributionTimestampWriter;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Exports request time-stamps with a uniformly random distance within each
 * sampled arrival rate interval.
 *
 * @author Joakim von Kistowski
 *
 */
public class DlimUniformRequestStampExporterCSV extends
DlimRequestStampExporter implements IDlimExporter {

	/**
	 * @see tools.descartes.dlim.exporter.IDlimExporter#export
	 * (java.lang.String, java.lang.String, tools.descartes.dlim.generator.ModelEvaluator)
	 */
	@Override
	public void export(String projectPath, String modelPath,
			ModelEvaluator evaluator) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();

		RequestTimeStampParametersDialog paramDialog = new RequestTimeStampParametersDialog(
				evaluator, modelPath, shell);
		paramDialog.open();
		
		if (!paramDialog.wasCanceled()) {
			evaluator.setRandomSeed(paramDialog.getRndSeed());
			IPath timeStampFolderPath = perpareTimestampDir(projectPath);
			File file = timeStampFolderPath.append(
					evaluator.getName() + "TimeStamps.csv").toFile();
			List<ArrivalRateTuple> arrList = ArrivalRateGenerator
					.generateArrivalRates(evaluator, paramDialog.getStep());
			UniformDistributionTimestampWriter writer = new UniformDistributionTimestampWriter(
					"", evaluator.getRndGenerator());
			writer.generateTimeStampsFromArrivalRates(file, arrList,
					paramDialog.getDecimalPlaces(), paramDialog.getStretch(),
					paramDialog.getArDevisor(), evaluator.getTerminatingDuration());
		}
	}

}
