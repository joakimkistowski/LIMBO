/**
 */
package tools.descartes.dlim.provider;


import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Polynomial;
import tools.descartes.dlim.PolynomialFactor;

/**
 * This is the item provider adapter for a {@link tools.descartes.dlim.Polynomial} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PolynomialItemProvider
	extends FunctionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolynomialItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(DlimPackage.Literals.POLYNOMIAL__FACTORS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Polynomial.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Polynomial"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	@Override
	public String getText(Object object) {
		String label = newLabelText((Polynomial)object);
		return label == null || label.length() == 0 ?
			getString("_UI_Polynomial_type") :
			getString("_UI_Polynomial_type") + " " + label;
	}
	
	/**
	 * Create the new label text from the contents of the PolynomialFactors.
	 * @generated not
	 */
	private String newLabelText(Polynomial polynom) {
		if (polynom.getFactors().isEmpty()) {
			return "empty";
		}
		String labelText = "";
		int index = polynom.getFactors().size() - 1;
		for (PolynomialFactor f: polynom.getFactors()) {
			String offsetX = "(x + " + f.getOffset() + ")";
			if (f.getOffset() == 0.0) {
				offsetX = "x";
			}
			String order = " * "+offsetX+"^" + index;
			if (index == 0) {
				order = "";
			} else if (index == 1) {
				order = " * "+offsetX;
			}
			labelText += " + " + f.getFactor() + order;
			index--;
		}
		return labelText.substring(3);
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * Also catches updates in the contained PolynomialFactors.
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Polynomial.class)) {
			case DlimPackage.POLYNOMIAL__FACTORS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		switch (notification.getFeatureID(PolynomialFactor.class)) {
		case DlimPackage.POLYNOMIAL_FACTOR__FACTOR:
		case DlimPackage.POLYNOMIAL_FACTOR__OFFSET:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
	}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(DlimPackage.Literals.POLYNOMIAL__FACTORS,
				 DlimFactory.eINSTANCE.createPolynomialFactor()));
	}

}
