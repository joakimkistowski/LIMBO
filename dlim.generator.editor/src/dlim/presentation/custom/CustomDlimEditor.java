package dlim.presentation.custom;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import dlim.generator.editor.views.PlotView;
import dlim.presentation.DlimEditor;

/**
 * DlimEditor extension, which allows the updating of the PlotView, when the model has changed.
 * @author Jóakim G. v. Kistowski
 */
public class CustomDlimEditor extends DlimEditor {

	@Override
	public void setCurrentViewer(Viewer viewer) {
		// If it is changing...
		//
		if (currentViewer != viewer) {
			if (selectionChangedListener == null) {
				// Create the listener on demand.
				//
				selectionChangedListener =
					new ISelectionChangedListener() {
						// This just notifies those things that are affected by the section.
						//
						public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
							setSelection(selectionChangedEvent.getSelection());
							IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
							for (int i = 0; i < references.length; i++) {
								if (references[i].getId().equals("dlim.generator.editor.views.PlotView")) {
									PlotView view = (PlotView)(references[i].getView(true));
									view.updatePlot(editingDomain.getResourceSet().getResources().get(0).getContents().get(0));
								}
							}
						}
					};
			}

			// Stop listening to the old one.
			//
			if (currentViewer != null) {
				currentViewer.removeSelectionChangedListener(selectionChangedListener);
			}

			// Start listening to the new one.
			//
			if (viewer != null) {
				viewer.addSelectionChangedListener(selectionChangedListener);
			}

			// Remember it.
			//
			currentViewer = viewer;

			// Set the editors selection based on the current viewer's selection.
			//
			setSelection(currentViewer == null ? StructuredSelection.EMPTY : currentViewer.getSelection());
		}
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		
		IViewReference[] references = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < references.length; i++) {
			if (references[i].getId().equals("dlim.generator.editor.views.PlotView")) {
				PlotView view = (PlotView)(references[i].getView(true));
				view.updatePlot();
			}
		}
	}
	
}
