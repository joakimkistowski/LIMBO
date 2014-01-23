package dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import dlim.exporter.utils.DlimFileUtils;
import dlim.generator.editor.views.PlotView;

/**
 * This action toggles the PlotView visualization.
 * @author Jóakim G. v. Kistowski
 */
public class DecomposeInPlotViewAction implements IObjectActionDelegate {

	private static ISelection currentSelection;
	
	@Override
	public void run(IAction action) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("dlim.generator.editor.views.PlotView");
			
			IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
			for (int i = 0; i < references.length; i++) {
				if (references[i].getId().equals("dlim.generator.editor.views.PlotView")) {
					PlotView view = (PlotView)(references[i].getView(true));
					view.setDecompose(true);
					view.updatePlot(DlimFileUtils.getEObjectFromSelection(currentSelection));
				}
			}
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		
	}
	
	public static ISelection getSelection() {
		return currentSelection;
	}

}
