package dlim.extractor;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import dlim.Sequence;
import dlim.extractor.dialogs.LaunchExtractionDialog;
import dlim.generator.ArrivalRateTuple;

/**
 * Extracts a DLIM instance based on the Simple Extraction Model.
 * @author Jóakim G. v. Kistowski
 */
public class SimpleProcessExtractor implements IDlimExtractor {

	/**
	 * Extracts a DLIM instance based on the Simple Extraction Model.
	 */
	@Override
	public void extractIntoSequence(Sequence root,
			List<ArrivalRateTuple> readArrivalRates) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		LaunchExtractionDialog dialog = new LaunchExtractionDialog(shell);
		dialog.open();

		if (!dialog.wasCanceled()) {
			ModelExtractor.extractArrivalRateFileIntoSequence(root,
					readArrivalRates,dialog.getSeasonalPeriod(),
					dialog.getSeasonalsPerTrend(),dialog.getSeasonalShape(),dialog.getTrendShape(),
					dialog.getOperatorLiteral(), dialog.isExtractNoise());
		}
	}

}
