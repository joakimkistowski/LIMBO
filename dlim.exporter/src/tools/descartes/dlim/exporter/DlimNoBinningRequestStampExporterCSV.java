/*******************************************************************************
 * Copyright (c) 2016 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.exporter.dialogs.NoBinningRequestTimeStampParametersDialog;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Exports request time-stamps by deriving the inter-arrival time for each time stamp separately.
 *
 * @author Joakim von Kistowski
 *
 */
public class DlimNoBinningRequestStampExporterCSV extends
DlimRequestStampExporter implements IDlimExporter {

	/**
	 * Exports request time-stamps with a equal distance within each sampled
	 * arrival rate interval.
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
		NoBinningRequestTimeStampParametersDialog paramDialog = new NoBinningRequestTimeStampParametersDialog(
				evaluator, modelPath, shell);
		paramDialog.open();

		if (!paramDialog.wasCanceled()) {
			IPath timeStampFolderPath = perpareTimestampDir(projectPath);
			File file = timeStampFolderPath.append(
					evaluator.getName() + "TimeStamps.csv").toFile();
			
			
			double stretch = paramDialog.getStretch();
			double arDevisor = paramDialog.getArDevisor();
			double zeroResamplingTime = paramDialog.getZeroResamplingTime();
			
			try {
				PrintWriter timeStampWriter = new PrintWriter(file, "UTF-8");

				double currentTime = 0.0;
				double finalDuration = evaluator.getTerminatingDuration();
				while (currentTime < finalDuration) {
					double arrivalRate = evaluator.getArrivalRateAtTime(currentTime) / arDevisor;
					double step = zeroResamplingTime;
					if (arrivalRate > 0) {
						step = 1.0 / arrivalRate;
						timeStampWriter.println(currentTime * stretch);
					}
					currentTime += step;
				}
				
				timeStampWriter.close();
			} catch (FileNotFoundException e) {
				DlimGeneratorPlugin.INSTANCE.log(
						new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
								"FileNotFound, Could not write time stamps.", e));
			} catch (UnsupportedEncodingException e) {
				DlimGeneratorPlugin.INSTANCE.log(
						new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
								"UnsupportedEncoding, Could not write time stamps.", e));
			}
		}
	}
}
