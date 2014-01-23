package dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import dlim.generator.editor.dialogs.SavePlotViewImageDialog;
import dlim.generator.editor.views.PlotView;

/**
 * Saves the graph in the PlotView to an image file.
 * @author Jóakim G. v. Kistowski
 */
public class SaveGraphFromPlotViewAction implements IViewActionDelegate {
	
	@Override
	public void run(IAction action) {
		//Display save dialog
		SavePlotViewImageDialog dialog = new SavePlotViewImageDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		dialog.open();
		
		if (!dialog.wasCanceled()) {
			IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
			for (int i = 0; i < references.length; i++) {
				if (references[i].getId().equals("dlim.generator.editor.views.PlotView")) {
					PlotView view = (PlotView)(references[i].getView(true));
					view.savePlotImage(dialog.getImageFilePath(), dialog.getWidth(), dialog.getHeight());
				}
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
