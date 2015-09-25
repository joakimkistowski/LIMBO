/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.presentation.custom;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ModelEvaluatorUtil;
import tools.descartes.dlim.generator.editor.views.PlotView;
import tools.descartes.dlim.presentation.DlimEditor;

/**
 * DlimEditor extension, which allows the updating of the PlotView, when the
 * model has changed.
 * 
 * @author Joakim von Kistowski
 */
public class CustomDlimEditor extends DlimEditor {

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";
	
//	@Override
//	public void setCurrentViewer(Viewer viewer) {
//		// If it is changing...
//		//
//		if (currentViewer != viewer) {
//			if (selectionChangedListener == null) {
//				// Create the listener on demand.
//				//
//				selectionChangedListener = new ISelectionChangedListener() {
//					// This just notifies those things that are affected by the
//					// section.
//					//
//					public void selectionChanged(
//							SelectionChangedEvent selectionChangedEvent) {
//						setSelection(selectionChangedEvent.getSelection());
//						IViewReference[] references = PlatformUI.getWorkbench()
//								.getActiveWorkbenchWindow().getActivePage()
//								.getViewReferences();
//						for (int i = 0; i < references.length; i++) {
//							if (references[i].getId().equals(PLOTVIEWID)) {
//								PlotView view = (PlotView) (references[i]
//										.getView(true));
//								EObject root = editingDomain.getResourceSet()
//										.getResources().get(0).getContents()
//										.get(0);
//								//Only change the root of the viewer if we switched to a new DLIM instance.
//								if (root instanceof Sequence
//										&& (view.getRootSequence() == null
//										|| !ModelEvaluatorUtil.containsInTree(root, view.getRootSequence()))) {
//									view.updatePlot(root);
//								} else {
//									view.updatePlot();
//								}
//								
//							}
//						}
//					}
//				};
//			}
//
//			// Stop listening to the old one.
//			//
//			if (currentViewer != null) {
//				currentViewer
//						.removeSelectionChangedListener(selectionChangedListener);
//			}
//
//			// Start listening to the new one.
//			//
//			if (viewer != null) {
//				viewer.addSelectionChangedListener(selectionChangedListener);
//			}
//
//			// Remember it.
//			//
//			currentViewer = viewer;
//
//			// Set the editors selection based on the current viewer's
//			// selection.
//			//
//			setSelection(currentViewer == null ? StructuredSelection.EMPTY
//					: currentViewer.getSelection());
//		}
//	}
//
//	@Override
//	public void doSave(IProgressMonitor progressMonitor) {
//		super.doSave(progressMonitor);
//
//		IViewReference[] references = PlatformUI.getWorkbench()
//				.getActiveWorkbenchWindow().getActivePage().getViewReferences();
//		for (int i = 0; i < references.length; i++) {
//			if (references[i].getId().equals(PLOTVIEWID)) {
//				PlotView view = (PlotView) (references[i].getView(true));
//				view.updatePlot();
//			}
//		}
//	}

}
