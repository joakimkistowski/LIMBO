/*******************************************************************************
 * Copyright (c) 2014 Jóakim v. Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import tools.descartes.dlim.exporter.utils.DlimFileUtils;

/**
 * This class provides resource and path utilities within an Eclipse project.
 *
 * @author Jóakim v. Kistowski
 *
 */
public class ProjectManager {

	private IProject currentProject = null;

	/**
	 * Create a ProjectManager for the project that contains the current
	 * selection.
	 *
	 * @param selection the selection
	 */
	public ProjectManager(ISelection selection) {
		IResource selectedResource = DlimFileUtils
				.getResourceFromSelection(selection);
		if (selectedResource == null) {
			// if selection is an EObject in the model editor
			EObject modelElement = DlimFileUtils
					.getEObjectFromSelection(selection);
			// get IResource containing model element
			if (modelElement != null) {
				URI uri = modelElement.eResource().getURI();
				if (uri.isPlatformResource()) {
					selectedResource = ResourcesPlugin.getWorkspace().getRoot()
							.findMember(uri.toPlatformString(true));
				}
			}
		}
		if (selectedResource != null) {
			currentProject = selectedResource.getProject();
		}
	}

	/**
	 * Create a ProjectManager for the project that contains the current
	 * resource.
	 *
	 * @param resource the resource
	 */
	public ProjectManager(IResource resource) {
		if (resource != null) {
			currentProject = resource.getProject();
		}
	}

	/**
	 * Get the project's root path.
	 *
	 * @return the project path
	 */
	public String getProjectPath() {
		return currentProject.getLocation().toString();
	}

	/**
	 * Refresh the ProjectManager's project.
	 */
	public void refreshProject() {
		try {
			currentProject.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			System.out.println("Failed to refresh Project");
			e.printStackTrace();
		}
	}

}
