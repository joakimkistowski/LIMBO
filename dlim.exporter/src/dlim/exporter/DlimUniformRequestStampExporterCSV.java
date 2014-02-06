package dlim.exporter;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import dlim.generator.ArrivalRateTuple;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;
import dlim.exporter.dialogs.RequestTimeStampParametersDialog;
import dlim.exporter.utils.ArrivalRateGenerator;
import dlim.exporter.utils.TimeStampWriter;

/**
 * Exports request time-stamps with a uniformly random distance within each sampled arrival rate interval.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DlimUniformRequestStampExporterCSV implements IDlimExporter {

	@Override
	public void export(String projectPath, String modelPath, ModelEvaluator evaluator) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		RequestTimeStampParametersDialog paramDialog =
				new RequestTimeStampParametersDialog(modelPath,shell);
		paramDialog.open();
		
		if (!paramDialog.wasCanceled()) {
			evaluator.setRandomSeed(paramDialog.getRndSeed());
			List<ArrivalRateTuple> arrList = ArrivalRateGenerator.generateArrivalRates(evaluator, paramDialog.getStep());
			TimeStampWriter.generateTimeStampsFromArrivalRates(evaluator, projectPath, arrList,
					IGeneratorConstants.UNIFORMDISTRIBUTIONSAMPLING, paramDialog.getDecimalPlaces(),
					paramDialog.getStretch(), paramDialog.getArDevisor(),"","csv");
		}
	}

}
