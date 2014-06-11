package dlim.exporter;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import dlim.exporter.dialogs.RequestTimeStampParametersDialog;
import dlim.exporter.utils.ArrivalRateGenerator;
import dlim.exporter.utils.EqualDistanceTimestampWriter;
import dlim.generator.ArrivalRateTuple;
import dlim.generator.ModelEvaluator;

/**
 * Exports request time-stamps with a equal distance within each sampled arrival rate interval.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DlimEqualDistanceRequestStampExporter extends DlimRequestStampExporter implements IDlimExporter {

	/**
	 * Exports request time-stamps with a equal distance within each sampled arrival rate interval.
	 */
	@Override
	public void export(String projectPath, String modelPath, ModelEvaluator evaluator) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		
		RequestTimeStampParametersDialog paramDialog =
				new RequestTimeStampParametersDialog(modelPath,shell);
		paramDialog.open();
		
		if (!paramDialog.wasCanceled()) {
			IPath timeStampFolderPath = perpareTimestampDir(projectPath);
			File file = timeStampFolderPath.append(evaluator.getName() + "TimeStamps.txt").toFile();
			List<ArrivalRateTuple> arrList = ArrivalRateGenerator.generateArrivalRates(evaluator, paramDialog.getStep());
			EqualDistanceTimestampWriter writer = new EqualDistanceTimestampWriter();
			writer.generateTimeStampsFromArrivalRates(file, arrList, paramDialog.getDecimalPlaces(), 
					paramDialog.getStretch(), paramDialog.getArDevisor());
		}
	}
}
