/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
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
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.presentation.DlimEditorPlugin;

/**
 * This class provides resource and path utilities within an Eclipse project.
 *
 * @author Joakim von Kistowski
 *
 */
public class ProjectManager {

	/**
	 * The ID of the Eclipse Preferences for the editor.
	 */
	private static final String DLIM_EDITOR_PREFERENCES_ID = "tools.descartes.dlim.editor.preferences";
	private static final String EDITOR_NODE_PREFERENCES_ID = "editor";

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
			DlimEditorPlugin.INSTANCE.log(
					new Status(Status.ERROR, DlimEditorPlugin.PLUGIN_ID, "Failed to refresh Project", e));
		}
	}

	/**
	 * Stores the String to the Eclipse Preferences.
	 * @param key the storage key.
	 * @param value the value to store.
	 */
	public static void saveStringToPreferences(String key, String value) {
		IEclipsePreferences dlimPrefs = InstanceScope.INSTANCE.getNode(DLIM_EDITOR_PREFERENCES_ID);
		Preferences filePathPref = dlimPrefs.node(EDITOR_NODE_PREFERENCES_ID);
		filePathPref.put(key, value);
		try {
			dlimPrefs.flush();
		} catch (BackingStoreException e) {
			DlimEditorPlugin.INSTANCE.log(
					new Status(Status.WARNING, DlimEditorPlugin.PLUGIN_ID, "Could not save property: " + key, e));
		}
	}

	/**
	 * Retrieves the String from the Eclipse Preferences.
	 * @param key the storage key.
	 * @return the stored String, returns an empty String for
	 * invalid or inaccessible keys.
	 */
	public static String retrieveStringFromPreferences(String key) {
		IEclipsePreferences dlimPrefs = InstanceScope.INSTANCE.getNode(ProjectManager.DLIM_EDITOR_PREFERENCES_ID);
		Preferences filePathPref = dlimPrefs.node(EDITOR_NODE_PREFERENCES_ID);
		return filePathPref.get(key, "");
	}

}
