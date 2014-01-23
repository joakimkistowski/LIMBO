/**
 */
package dlim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import dlim.DlimPackage;
import dlim.ExponentialIncreaseLogarithmicDecline;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exponential Increase Logarithmic Decline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.ExponentialIncreaseLogarithmicDeclineImpl#getLogarithmicOrder <em>Logarithmic Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExponentialIncreaseLogarithmicDeclineImpl extends BurstImpl implements ExponentialIncreaseLogarithmicDecline {
	/**
	 * The default value of the '{@link #getLogarithmicOrder() <em>Logarithmic Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogarithmicOrder()
	 * @generated
	 * @ordered
	 */
	protected static final double LOGARITHMIC_ORDER_EDEFAULT = 4.0;

	/**
	 * The cached value of the '{@link #getLogarithmicOrder() <em>Logarithmic Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogarithmicOrder()
	 * @generated
	 * @ordered
	 */
	protected double logarithmicOrder = LOGARITHMIC_ORDER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExponentialIncreaseLogarithmicDeclineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLogarithmicOrder() {
		return logarithmicOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogarithmicOrder(double newLogarithmicOrder) {
		double oldLogarithmicOrder = logarithmicOrder;
		logarithmicOrder = newLogarithmicOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER, oldLogarithmicOrder, logarithmicOrder));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER:
				return getLogarithmicOrder();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER:
				setLogarithmicOrder((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER:
				setLogarithmicOrder(LOGARITHMIC_ORDER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER:
				return logarithmicOrder != LOGARITHMIC_ORDER_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (logarithmicOrder: ");
		result.append(logarithmicOrder);
		result.append(')');
		return result.toString();
	}

} //ExponentialIncreaseLogarithmicDeclineImpl
