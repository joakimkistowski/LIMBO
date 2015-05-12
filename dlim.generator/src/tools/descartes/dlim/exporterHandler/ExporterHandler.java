/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.exporterHandler;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Execute;

import tools.descartes.dlim.DlimGeneratorPlugin;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.IDlimExporter;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Handles all implementations of the Exporter extension point.
 *
 * @author Joakim von Kistowski
 */
public final class ExporterHandler {

	/**
	 * Extension Point ID.
	 */
	public static final String IEXPORTER_ID = "tools.descartes.dlim.generator.tools_descartes_dlim_generator_exporters";
	private ArrayList<ExporterContainer> exporters = new ArrayList<ExporterContainer>();

	private static ExporterHandler handlerSingleton;

	/**
	 * Get the Singleton for the Handler.
	 *
	 * @return the handler singleton
	 */
	public static ExporterHandler getHandlerSingleton() {
		if (handlerSingleton == null) {
			handlerSingleton = new ExporterHandler();
		}
		return handlerSingleton;
	}

	private ExporterHandler() {
		if (handlerSingleton == null) {
			handlerSingleton = this;
			execute(Platform.getExtensionRegistry());
		}
	}

	@Execute
	private void execute(IExtensionRegistry registry) {
		evaluate(registry);
	}

	private void evaluate(IExtensionRegistry registry) {
		IConfigurationElement[] config = registry
				.getConfigurationElementsFor(IEXPORTER_ID);
		try {
			for (IConfigurationElement e : config) {
				final Object o = e.createExecutableExtension("class");
				String label = e.getAttribute("label");
				if (o instanceof IDlimExporter) {
					exporters.add(new ExporterContainer(label,
							(IDlimExporter) o));
				}
			}
		} catch (CoreException ex) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
							ex.getMessage(), ex));
		}
	}

	/**
	 * Get the ordered List of all exporter labels.
	 *
	 * @return the exporter labels
	 */
	public String[] getExporterLabels() {
		String[] labels = new String[exporters.size()];
		int i = 0;
		for (ExporterContainer e : exporters) {
			labels[i] = e.getLabel();
			i++;
		}
		return labels;
	}

	/**
	 * Execute the exporter at index.
	 *
	 * @param exporterIndex index of Exporter plugin in the list of registered plugins
	 * @param projectPath
	 *            The Eclipse-project within which the DLIM instance is located.
	 * @param modelPath
	 *            The path of the DLIM instance.
	 * @param rootObject
	 *            The model root object.
	 */
	public void executeExtension(final int exporterIndex,
			final String projectPath, final String modelPath,
			final Sequence rootObject) {
		ISafeRunnable runnable = new ISafeRunnable() {

			@Override
			public void handleException(Throwable e) {
				DlimGeneratorPlugin.INSTANCE.log(
						new Status(Status.INFO, DlimGeneratorPlugin.PLUGIN_ID,
								"Exception in client", e));
			}

			@Override
			public void run() throws Exception {
				IDlimExporter exporter = exporters.get(exporterIndex)
						.getExporter();
				exporter.export(projectPath, modelPath, new ModelEvaluator(
						rootObject));
			}
		};
		SafeRunner.run(runnable);
	}
}
