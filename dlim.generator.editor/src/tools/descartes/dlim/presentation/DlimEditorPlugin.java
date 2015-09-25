/**
 */
package tools.descartes.dlim.presentation;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleContext;

import tools.descartes.dlim.presentation.custom.DLIMSelectionListener;
import tools.descartes.dlim.presentation.custom.DLIMWorkbenchPartListener;

/**
 * This is the central singleton for the Dlim editor plugin. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public final class DlimEditorPlugin extends EMFPlugin {

	/**
	 * The Plugin's ID.
	 * @generated not
	 */
	public static final String PLUGIN_ID = "tools.descartes.dlim.presentation.DlimEditorPlugin";

	/**
	 * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @generated
	 */
	public static final DlimEditorPlugin INSTANCE = new DlimEditorPlugin();

	/**
	 * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public DlimEditorPlugin() {
		super(new ResourceLocator[] {});
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 *
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 *
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public static class Implementation extends EclipseUIPlugin {
		/**
		 * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
		 *
		 * @generated not
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
		
		/**
		 * @generated not
		 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
		 */
		public void start(BundleContext context) throws Exception {
			super.start(context);
			plugin = this;
			ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
	        service.addSelectionListener(new DLIMSelectionListener());
	        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(new DLIMWorkbenchPartListener());
		}

		/**
		 * @generated not
		 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
		 */
		public void stop(BundleContext context) throws Exception {
			plugin = null;
			super.stop(context);
		}
	}

}
