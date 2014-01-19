package dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import dlim.generator.editor.dialogs.PlotArrivalRateFileDialog;
import dlim.generator.editor.views.PlotView;

/**
 * This Action toggles the display of an arrival rate trace in the PlotView.
 * @author Jóakim G. v. Kistowski
 */
public class PlotArrivalRateFileInPlotViewAction implements IViewActionDelegate {
	
	private Shell shell;
	
	@Override
	public void run(IAction action) {
		//
		//get plot view
		IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < references.length; i++) {
			if (references[i].getId().equals("dlim.generator.editor.views.PlotView")) {
				PlotView view = (PlotView)(references[i].getView(true));
				if (view.isPlottingFile()) {
					view.setPlottingFile(false);
				} else {
					//show dialog
					PlotArrivalRateFileDialog dialog = new PlotArrivalRateFileDialog(shell);
					dialog.open();
					if (!dialog.wasCanceled()) {
						view.setPlottingFile(true);
						view.setArrivalRateFileList(dialog.getArrivalRateList());
					}
				}
				view.updatePlot();
			}
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void init(IViewPart view) {
		shell = view.getSite().getShell();
		
	}

	

}
