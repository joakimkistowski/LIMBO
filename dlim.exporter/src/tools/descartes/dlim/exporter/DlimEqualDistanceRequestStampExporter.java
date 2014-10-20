package tools.descartes.dlim.exporter;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.exporter.IDlimExporter;
import tools.descartes.dlim.exporter.dialogs.RequestTimeStampParametersDialog;
import tools.descartes.dlim.exporter.utils.ArrivalRateGenerator;
import tools.descartes.dlim.exporter.utils.EqualDistanceTimestampWriter;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Exports request time-stamps with a equal distance within each sampled arrival rate interval.
 * @author J�akim G. v. Kistowski
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
