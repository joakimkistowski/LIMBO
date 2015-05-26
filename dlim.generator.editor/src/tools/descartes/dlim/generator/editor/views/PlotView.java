/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.views;

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

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * This view contains a plot canvas on which the arrival rates as defined by the
 * current DLIM instance are plotted.
 *
 * @author Joakim von Kistowski
 */
public class PlotView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "tools.descartes.dlim.generator.plotview.views.PlotView";

	private PlotCanvas plotCanvas;
	private Sequence rootSequence = null;
	private boolean decompose = false;
	private boolean plottingFile = false;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 *
	 * @param parent the parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		plotCanvas = new PlotCanvas(parent, SWT.NONE, true);
		plotCanvas.setRootSequence(rootSequence);

		hookContextMenu();

		PlatformUI
		.getWorkbench()
		.getHelpSystem()
		.setHelp(plotCanvas,
				"tools.descartes.dlim.generator.plotview.viewer");
	}

	/**
	 * Update plot.
	 *
	 * @param eObject the root sequence
	 */
	public void updatePlot(EObject eObject) {
		this.rootSequence = (Sequence) eObject;
		updatePlot();
	}

	/**
	 * Sets the decomposition.
	 *
	 * @param decompose true if the view should decompose the plots
	 */
	public void setDecompose(boolean decompose) {
		this.decompose = decompose;
	}

	/**
	 * Gets decomposition.
	 *
	 * @return the true if the view should decompose the plots
	 */
	public boolean getDecompose() {
		return decompose;
	}

	/**
	 * Update plot.
	 */
	public void updatePlot() {
		plotCanvas.setRootSequence(rootSequence);
		plotCanvas.setDecompositionMode(decompose);
		plotCanvas.setPlottingFile(plottingFile);
		plotCanvas.redraw();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		plotCanvas.setFocus();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
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
	 *
	 * @param path the path
	 * @param width the width
	 * @param height the height
	 */
	public void savePlotImage(String path, int width, int height) {
		plotCanvas.savePlotCanvasImage(path, width, height);
	}

	/**
	 * Checks if is plotting file.
	 *
	 * @return true, if is plotting file
	 */
	public boolean isPlottingFile() {
		return plottingFile;
	}

	/**
	 * Set to true if an arrival rate trace file is to be plotted.
	 *
	 * @param plottingFile the new plotting file
	 */
	public void setPlottingFile(boolean plottingFile) {
		this.plottingFile = plottingFile;
	}

	/**
	 * Set the in-memory arrival rate trace for plotting in comparison to the
	 * model instance.
	 *
	 * @param arrivalRateFileList the new arrival rate file list
	 */
	public void setArrivalRateFileList(
			List<ArrivalRateTuple> arrivalRateFileList) {
		plotCanvas.setArrivalRateFileList(arrivalRateFileList);
	}
	
	/**
	 * Returns the PlotView's current root sequence.
	 * @return The root sequence.
	 */
	public Sequence getRootSequence() {
		return rootSequence;
	}
}