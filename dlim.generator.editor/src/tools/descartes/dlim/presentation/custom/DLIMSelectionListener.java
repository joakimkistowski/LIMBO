/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.presentation.custom;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.editor.views.PlotView;
import tools.descartes.dlim.util.DlimSwitch;

/**
 * Listener for updating the PlotView on Model changes.
 * @author Christian Stier
 *
 */
public class DLIMSelectionListener implements
		ISelectionListener {

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof TreeSelection) {
			 Object selectedObject = ((TreeSelection) selection).getFirstElement();
			 if (selectedObject instanceof EObject) {
				 Sequence sequence = new DlimSwitch<Sequence>() {
					 public Sequence caseSequence(Sequence sequence) {
						 return sequence;
					 }
					 public Sequence defaultCase(EObject object) {
				         if (object != null && object.eContainer() != null
				        		 && object.eContainer().eClass().getEPackage() == modelPackage) {
				             return doSwitch(object.eContainer());
				         } else {
				             return null;
				         }
					 };
				 }.doSwitch((EObject) selectedObject);
			    if (sequence != null) {
                    IViewReference[] references = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow().getActivePage()
                            .getViewReferences();
                    for (int i = 0; i < references.length; i++) {
                        if (references[i].getId().equals(PLOTVIEWID)) {
                            PlotView view = (PlotView) (references[i]
                                    .getView(true));
                            view.updatePlot(sequence);
                        }
                    }
			    }
			 }
		}
	}
}
