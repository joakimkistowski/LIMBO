package tools.descartes.dlim.extractor;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.extractor.IDlimExtractor;
import tools.descartes.dlim.extractor.dialogs.LaunchPeriodicExtractionDialog;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Extracts a DLIM instance based on the Periodic Extraction Model.
 * @author Jóakim G. v. Kistowski
 */
public class PeriodicProcessExtractor implements IDlimExtractor {

	/**
	 * Extracts a DLIM instance based on the Periodic Extraction Model.
	 */
	@Override
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> readArrivalRates) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		LaunchPeriodicExtractionDialog dialog = new LaunchPeriodicExtractionDialog(shell,root,readArrivalRates);
		dialog.open();

		/*if (!dialog.wasCanceled()) {
			ModelExtractor.extractSequenceFromArrivalRateFilePeriodic(root,
					readArrivalRates,dialog.getSeasonalPeriod(),
					dialog.getSeasonalsPerTrend(),dialog.getSeasonalShape(),dialog.getTrendShape(),
					dialog.getOperatorLiteral(), dialog.isExtractNoise());
		}*/
	}

}
