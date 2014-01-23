package dlim.generator.editor.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import dlim.exporter.utils.DlimFileUtils;

/**
 * This class provides resource and path utilities within an Eclipse project.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ProjectManager {

	private IProject currentProject = null;
	
	/**
	 * Create a ProjectManager for the project that contains the current selection.
	 * @param selection
	 */
	public ProjectManager(ISelection selection) {
		IResource selectedResource = DlimFileUtils.getResourceFromSelection(selection);
		if (selectedResource == null) {
			//if selection is an EObject in the model editor
			EObject modelElement = DlimFileUtils.getEObjectFromSelection(selection);
			//get IResource containing model element
			if (modelElement != null) {
				URI uri = modelElement.eResource().getURI();
				if (uri.isPlatformResource()) {
					selectedResource = ResourcesPlugin.getWorkspace().getRoot().
							findMember(uri.toPlatformString(true));
				}
			}
		}
		if (selectedResource != null){
			currentProject = selectedResource.getProject();
		}
	}
	
	/**
	 * Create a ProjectManager for the project that contains the current resource.
	 * @param resource
	 */
	public ProjectManager(IResource resource) {
		if (resource != null) {
			currentProject = resource.getProject();
		}
	}
	
	
	/**
	 * Get the project's root path.
	 * @return
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
