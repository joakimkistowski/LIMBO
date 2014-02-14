package dlim.extractor;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import dlim.Sequence;
import dlim.extractor.dialogs.LaunchPeriodicExtractionDialog;
import dlim.generator.ArrivalRateTuple;

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
