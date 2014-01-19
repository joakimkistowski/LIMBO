package dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import dlim.generator.editor.views.PlotView;

/**
 * Toggles the PlotView visualization.
 * @author Jóakim G. v. Kistowski
 */
public class ToggleDecomposeInPlotViewAction implements IViewActionDelegate {
	
	@Override
	public void run(IAction action) {
		IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < references.length; i++) {
			if (references[i].getId().equals("dlim.generator.editor.views.PlotView")) {
				PlotView view = (PlotView)(references[i].getView(true));
				view.setDecompose(!view.getDecompose());
				view.updatePlot();
			}
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		
	}

	

}
