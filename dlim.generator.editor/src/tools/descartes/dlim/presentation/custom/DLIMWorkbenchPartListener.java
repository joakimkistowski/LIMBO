/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.presentation.custom;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.editor.views.PlotView;
import tools.descartes.dlim.presentation.DlimEditor;

/**
 * Listener for active editor parts (tabs).
 * Updates the Plot-View.
 * @author Joakim von Kistowski
 *
 */
public class DLIMWorkbenchPartListener implements IPartListener2 {


	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";
	private static final String EDITORID = "tools.descartes.dlim.presentation.DlimEditorID";
	
	private void updatePlot(IWorkbenchPartReference part) {
		Sequence root = null;
		if (part.getId().equals(EDITORID)) {
			IEditorPart editor = part.getPage().getActiveEditor();
			if (editor instanceof DlimEditor) {
				DlimEditor dlimeditor = (DlimEditor) editor;
				Resource resource = dlimeditor.getEditingDomain().getResourceSet().getResources().get(0);
				EObject eobj = resource.getContents().get(0);
				if (eobj instanceof Sequence) {
					root = (Sequence) eobj;
				}
			}
			
			if (root != null) {
				IViewReference[] references = PlatformUI.getWorkbench()
		                .getActiveWorkbenchWindow().getActivePage()
		                .getViewReferences();
		        for (int i = 0; i < references.length; i++) {
		            if (references[i].getId().equals(PLOTVIEWID)) {
		                PlotView view = (PlotView) (references[i]
		                        .getView(true));
		                view.updatePlot(root);
		            }
		        }
			}
		}
	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		updatePlot(partRef);
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		updatePlot(partRef);
		
	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		updatePlot(partRef);
	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		
	}

}
