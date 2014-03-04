package dlim.exporter;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import dlim.exporter.dialogs.RequestTimeStampParametersDialog;
import dlim.exporter.utils.ArrivalRateGenerator;
import dlim.exporter.utils.UniformDistributionTimestampWriter;
import dlim.generator.ArrivalRateTuple;
import dlim.generator.ModelEvaluator;

/**
 * Exports request time-stamps with a uniformly random distance within each sampled arrival rate interval.
 * @author J�akim G. v. Kistowski
 *
 */
public class DlimUniformRequestStampExporterCSV extends DlimRequestStampExporter implements IDlimExporter {

	@Override
	public void export(String projectPath, String modelPath, ModelEvaluator evaluator) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		RequestTimeStampParametersDialog paramDialog =
				new RequestTimeStampParametersDialog(modelPath,shell);
		paramDialog.open();
		
		if (!paramDialog.wasCanceled()) {
			IPath timeStampFolderPath = perpareTimestampDir(projectPath);
			File file = timeStampFolderPath.append(evaluator.getName() + "TimeStamps.csv").toFile();
			List<ArrivalRateTuple> arrList = ArrivalRateGenerator.generateArrivalRates(evaluator, paramDialog.getStep());
			UniformDistributionTimestampWriter writer = new UniformDistributionTimestampWriter("", paramDialog.getRndSeed());
			writer.generateTimeStampsFromArrivalRates(file, arrList, paramDialog.getDecimalPlaces(), 
					paramDialog.getStretch(), paramDialog.getArDevisor(), evaluator.getDuration());
		}
	}

}
