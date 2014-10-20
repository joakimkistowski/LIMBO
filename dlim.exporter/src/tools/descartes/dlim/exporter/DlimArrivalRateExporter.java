package tools.descartes.dlim.exporter;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.exporter.IDlimExporter;
import tools.descartes.dlim.exporter.dialogs.ArrivalRateTimeStampParametersDialog;
import tools.descartes.dlim.exporter.utils.ArrivalRateGenerator;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;


/**
 * Exports a simple Arrival Rate file with time-stamps for the respective arrival rates.
 * @author J�akim G. v. Kistowski
 *
 */
public class DlimArrivalRateExporter implements IDlimExporter {

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
			ArrivalRateGenerator.writeArrivalRates(arrRates, projectPath, evaluator.getName(), ";", "txt");
		}
	}

}
