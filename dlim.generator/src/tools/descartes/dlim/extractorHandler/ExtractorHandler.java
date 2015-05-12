/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractorHandler;

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
import tools.descartes.dlim.extractor.IDlimExtractor;
import tools.descartes.dlim.reader.IDlimArrivalRateReader;

/**
 * Handles all registered implementations of the Extractor extension point.
 *
 * @author Joakim von Kistowski
 */
public final class ExtractorHandler {

	/**
	 * ID of the Extractor Extension Point.
	 */
	public static final String IEXTRACTOR_ID =
			"tools.descartes.dlim.generator.tools_descartes_dlim_generator_extractors";

	/**
	 * List of all Extractor Extension Point Implementations.
	 */
	private ArrayList<ExtractorContainer> extractors = new ArrayList<ExtractorContainer>();

	/**
	 * The Singleton of this class.
	 */
	private static ExtractorHandler handlerSingleton;

	/**
	 * Get the handler Singleton.
	 *
	 * @return Returns the singleton.
	 */
	public static ExtractorHandler getHandlerSingleton() {
		if (handlerSingleton == null) {
			handlerSingleton = new ExtractorHandler();
		}
		return handlerSingleton;
	}

	private ExtractorHandler() {
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
				.getConfigurationElementsFor(IEXTRACTOR_ID);
		try {
			for (IConfigurationElement e : config) {
				final Object extr = e
						.createExecutableExtension("extractor_class");
				final Object read = e.createExecutableExtension("reader_class");
				String label = e.getAttribute("label");
				if (extr instanceof IDlimExtractor
						&& read instanceof IDlimArrivalRateReader) {
					extractors.add(new ExtractorContainer(label,
							(IDlimExtractor) extr,
							(IDlimArrivalRateReader) read));
				}
			}
		} catch (CoreException ex) {
			DlimGeneratorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID, ex.getMessage(), ex));
		}
	}

	/**
	 * Get the ordered List of Extractor labels.
	 *
	 * @return the extractor labels
	 */
	public String[] getExtractorLabels() {
		String[] labels = new String[extractors.size()];
		int i = 0;
		for (ExtractorContainer e : extractors) {
			labels[i] = e.getLabel();
			i++;
		}
		return labels;
	}

	/**
	 * Execute an extractor at the given index within the list of registered
	 * Extractors. The index corresponds to its label index within the ordered
	 * label list.
	 *
	 * @param extractorIndex Index of Extension point implementation in list of extractors
	 * @param arrivalRateFilePath
	 *            Path of the arrival rate trace.
	 * @param rootObject
	 *            the Sequence into which the trace is to be extracted.
	 * @param offset
	 *            the offset within the arrival rate trace at which to start
	 *            reading it.
	 */
	public void executeExtension(final int extractorIndex,
			final String arrivalRateFilePath, final Sequence rootObject,
			final double offset) {
		ISafeRunnable runnable = new ISafeRunnable() {

			@Override
			public void handleException(Throwable e) {
				DlimGeneratorPlugin.INSTANCE.log(
						new Status(Status.ERROR, DlimGeneratorPlugin.PLUGIN_ID, "Exception in client", e));
			}

			@Override
			public void run() throws Exception {
				IDlimExtractor extractor = extractors.get(extractorIndex)
						.getExtractor();
				IDlimArrivalRateReader reader = extractors.get(extractorIndex)
						.getReader();
				extractor.extractIntoSequence(rootObject,
						reader.readFileToList(arrivalRateFilePath, offset));
			}
		};
		SafeRunner.run(runnable);
	}
}
