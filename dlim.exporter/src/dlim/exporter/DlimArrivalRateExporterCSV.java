package dlim.exporter;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import dlim.generator.ArrivalRateTuple;
import dlim.generator.ModelEvaluator;
import dlim.exporter.dialogs.ArrivalRateTimeStampParametersDialog;
import dlim.exporter.utils.ArrivalRateGenerator;


/**
 * Exports a simple Arrival Rate file with time-stamps for the respective arrival rates.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DlimArrivalRateExporterCSV implements IDlimExporter {

	/**
	 * Exports a simple Arrival Rate file with time-stamps for the respective arrival rates.
	 */
	@Override
	public void export(String projectPath, String modelPath, ModelEvaluator evaluator) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		ArrivalRateTimeStampParametersDialog paramDialog =
				new ArrivalRateTimeStampParametersDialog(modelPath,shell);
		paramDialog.open();
		
		if (!paramDialog.wasCanceled()) {
			evaluator.setRandomSeed(paramDialog.getRndSeed());
			
			List<ArrivalRateTuple> arrRates = ArrivalRateGenerator.generateArrivalRates(evaluator, paramDialog.getStep());
			ArrivalRateGenerator.writeArrivalRates(arrRates, projectPath, evaluator.getName(), "", "csv");
		}
	}

}
