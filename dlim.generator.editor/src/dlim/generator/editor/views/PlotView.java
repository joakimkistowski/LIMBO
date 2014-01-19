package dlim.generator.editor.views;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import dlim.Sequence;
import dlim.generator.ArrivalRateTuple;


/**
 * This view contains a plot canvas on which the arrival rates as defined by the current
 * DLIM instance are plotted.
 * @author Jóakim G. v. Kistowski
 */
public class PlotView extends ViewPart {
	
	
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "dlim.generator.plotview.views.PlotView";

	private PlotCanvas plotCanvas;
	private Sequence rootSequence = null;
	private boolean decompose = false;
	private boolean plottingFile = false;
	

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		
		plotCanvas = new PlotCanvas(parent, SWT.NONE, true);
		plotCanvas.setRootSequence(rootSequence);

		hookContextMenu();
		
		PlatformUI.getWorkbench().getHelpSystem().setHelp(plotCanvas, "dlim.generator.plotview.viewer");
	}
	
	public void updatePlot(EObject eObject) {
		this.rootSequence = (Sequence)eObject;
		updatePlot();
	}
	
	public void setDecompose(boolean decompose) {
		this.decompose = decompose;
	}
	
	public boolean getDecompose() {
		return decompose;
	}
	
	public void updatePlot() {
		plotCanvas.setRootSequence(rootSequence);
		plotCanvas.setDecompositionMode(decompose);
		plotCanvas.setPlottingFile(plottingFile);
		plotCanvas.redraw();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		plotCanvas.setFocus();
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(plotCanvas);
		plotCanvas.setMenu(menu);
		getSite().registerContextMenu(menuMgr, null);
	}
	
	private void fillContextMenu(IMenuManager manager) {
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	/**
	 * Saves the plot in the view to an image file.
	 * @param path
	 * @param width
	 * @param height
	 */
	public void savePlotImage(String path, int width, int height) {
		plotCanvas.savePlotCanvasImage(path, width, height);
	}
	
	public boolean isPlottingFile() {
		return plottingFile;
	}

	/**
	 * Set to true if an arrival rate trace file is to be plotted.
	 * @param plottingFile
	 */
	public void setPlottingFile(boolean plottingFile) {
		this.plottingFile = plottingFile;
	}
	
	/**
	 * Set the in-memory arrival rate trace for plotting in comparison to the model instance.
	 * @param arrivalRateFileList
	 */
	public void setArrivalRateFileList(List<ArrivalRateTuple> arrivalRateFileList) {
		plotCanvas.setArrivalRateFileList(arrivalRateFileList);
	}
}