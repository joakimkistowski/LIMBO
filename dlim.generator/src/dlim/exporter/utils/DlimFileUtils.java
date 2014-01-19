package dlim.exporter.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * This class provides resource and path utilities within an Eclipse project.
 * @author Jóakim G. v. Kistowski
 *
 */
public class DlimFileUtils {

	
	/**
	 * Returns the selected EObject.
	 * @param selectedResource
	 * @return
	 */
	public static EObject getEObjectFromSelection(ISelection selectedResource) {
		if (!(selectedResource instanceof IStructuredSelection)) {
			return null;
		}
		IStructuredSelection structuredSelection = (IStructuredSelection) selectedResource;
	    Object modelElement = structuredSelection.getFirstElement();
	    if (modelElement instanceof EObject) {
	    	return (EObject)modelElement;
	    }
	    return null;
	}
	
	/**
	 * Returns the selected IResource.
	 * @param selectedResource
	 * @return
	 */
	public static IResource getResourceFromSelection(ISelection selectedResource) {
		if (!(selectedResource instanceof IStructuredSelection)) {
			return null;
		}
		IStructuredSelection structuredSelection = (IStructuredSelection) selectedResource;
	    Object resourceElement = structuredSelection.getFirstElement();
	    if (resourceElement instanceof IResource) {
	    	return (IResource)resourceElement;
	    }
	    if (!(resourceElement instanceof IAdaptable)) {
	    	return null;
	    }
	    IAdaptable adaptable = (IAdaptable)resourceElement;
	    Object adapter = adaptable.getAdapter(IResource.class);
	    return (IResource)adapter;
	}
	
	/**
	 * Get all resources within a Selection set.
	 * @param selectedResource
	 * @return
	 */
	public static List<IResource> getResourceListFromSelection(ISelection selectedResource) {
		ArrayList<IResource> resourceList = new ArrayList<IResource>();
		if (!(selectedResource instanceof IStructuredSelection)) {
			return resourceList;
		}
		IStructuredSelection structuredSelection = (IStructuredSelection) selectedResource;
		for (Object resourceElement : structuredSelection.toList()) {
		    if (resourceElement instanceof IResource) {
		    	resourceList.add((IResource)resourceElement);
		    }
		    /*if (resourceElement instanceof IAdaptable) {
		    	IAdaptable adaptable = (IAdaptable)resourceElement;
			    Object adapter = adaptable.getAdapter(IResource.class);
			    resourceList.add((IResource)adapter);
		    }*/
		}
	    return resourceList;
	}
	
	/**
	 * Get the path of the first selected resource.
	 * @param selectedResource
	 * @return
	 */
	public static String getSelectionPath(ISelection selectedResource) {
		IResource resource = getResourceFromSelection(selectedResource);
		return resource.getRawLocation().toString();
	}
	
	/**
	 * Returns the model root of the model contained in the selectedResource's file.
	 * When using this for dlim files, the resulting Object should be cast to dlim.Sequence.
	 * @param selectedResource
	 * @return
	 */
	public static EObject getRootEObject(ISelection selectedResource) {
		IResource resource = getResourceFromSelection(selectedResource);
		
		URI fileURI = URI.createFileURI(resource.getLocation().toString());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("dlim", new EcoreResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource dlimResource = resourceSet.getResource(fileURI, true);
		return dlimResource.getContents().get(0);
	}
	
	/**
	 * Returns the model root of the model contained in the file at the modelFilePath.
	 * When using this for dlim files, the resulting Object should be cast to dlim.Sequence.
	 * @param modelFilePath
	 * @return
	 */
	public static EObject getRootEObject(String modelFilePath) {
		URI fileURI = URI.createFileURI(modelFilePath);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("dlim", new EcoreResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource dlimResource = resourceSet.getResource(fileURI, true);
		return dlimResource.getContents().get(0);
	}
	
	/**
	 * Returns the model root of the model contained in the resource.
	 * When using this for dlim files, the resulting Object should be cast to dlim.Sequence.
	 * @param resource
	 * @return
	 */
	public static EObject getRootEObject(IResource resource) {
		
		URI fileURI = URI.createFileURI(resource.getLocation().toString());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("dlim", new EcoreResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource dlimResource = resourceSet.getResource(fileURI, true);
		return dlimResource.getContents().get(0);
	}
	
}
