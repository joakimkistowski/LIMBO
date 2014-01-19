package dlim.exporterHandler;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.e4.core.di.annotations.Execute;

import dlim.Sequence;
import dlim.exporter.IDlimExporter;
import dlim.generator.ModelEvaluator;

/**
 * Handles all implementations of the Exporter extension point.
 * @author Jóakim G. v. Kistowski
 */
public class ExporterHandler {
	
	public static final String IEXPORTER_ID = "dlim.generator.dlim_generator_exporters";
	private ArrayList<ExporterContainer> exporters = new ArrayList<ExporterContainer>();
	
	private static ExporterHandler handlerSingleton;
	
	/**
	 * Get the Singleton for the Handler.
	 * @return
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
	    IConfigurationElement[] config =
	        registry.getConfigurationElementsFor(IEXPORTER_ID);
	    try {
	      for (IConfigurationElement e : config) {
	        final Object o =
	            e.createExecutableExtension("class");
	        String label = e.getAttribute("label");
	        if (o instanceof IDlimExporter) {
	        	exporters.add(new ExporterContainer(label,(IDlimExporter)o));
	        }
	      }
	    } catch (CoreException ex) {
	      System.out.println(ex.getMessage());
	    }
	  }

	/**
	 * Get the ordered List of all exporter labels.
	 * @return
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
	 * @param exporterIndex
	 * @param projectPath The Eclipse-project within which the DLIM instance is located.
	 * @param modelPath The path of the DLIM instance.
	 * @param rootObject The model root object.
	 */
	public void executeExtension(final int exporterIndex,
			final String projectPath, final String modelPath, final Sequence rootObject) {
		ISafeRunnable runnable = new ISafeRunnable() {
			
			@Override
			public void handleException(Throwable e) {
				System.out.println("Exception in client");
			}
			
			@Override
			public void run() throws Exception {
				IDlimExporter exporter = exporters.get(exporterIndex).getExporter();
				exporter.export(projectPath, modelPath, new ModelEvaluator(rootObject));
			}
		};
		SafeRunner.run(runnable);
	}
}
