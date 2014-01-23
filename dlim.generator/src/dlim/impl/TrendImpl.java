/**
 */
package dlim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import dlim.DlimPackage;
import dlim.Trend;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trend</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.TrendImpl#getFunctionOutputAtStart <em>Function Output At Start</em>}</li>
 *   <li>{@link dlim.impl.TrendImpl#getFunctionOutputAtEnd <em>Function Output At End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TrendImpl extends FunctionImpl implements Trend {
	/**
	 * The default value of the '{@link #getFunctionOutputAtStart() <em>Function Output At Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionOutputAtStart()
	 * @generated
	 * @ordered
	 */
	protected static final double FUNCTION_OUTPUT_AT_START_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getFunctionOutputAtStart() <em>Function Output At Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionOutputAtStart()
	 * @generated
	 * @ordered
	 */
	protected double functionOutputAtStart = FUNCTION_OUTPUT_AT_START_EDEFAULT;
	/**
	 * The default value of the '{@link #getFunctionOutputAtEnd() <em>Function Output At End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionOutputAtEnd()
	 * @generated
	 * @ordered
	 */
	protected static final double FUNCTION_OUTPUT_AT_END_EDEFAULT = 1.0;
	/**
	 * The cached value of the '{@link #getFunctionOutputAtEnd() <em>Function Output At End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionOutputAtEnd()
	 * @generated
	 * @ordered
	 */
	protected double functionOutputAtEnd = FUNCTION_OUTPUT_AT_END_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrendImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.TREND;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFunctionOutputAtStart() {
		return functionOutputAtStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionOutputAtStart(double newFunctionOutputAtStart) {
		double oldFunctionOutputAtStart = functionOutputAtStart;
		functionOutputAtStart = newFunctionOutputAtStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TREND__FUNCTION_OUTPUT_AT_START, oldFunctionOutputAtStart, functionOutputAtStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFunctionOutputAtEnd() {
		return functionOutputAtEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionOutputAtEnd(double newFunctionOutputAtEnd) {
		double oldFunctionOutputAtEnd = functionOutputAtEnd;
		functionOutputAtEnd = newFunctionOutputAtEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TREND__FUNCTION_OUTPUT_AT_END, oldFunctionOutputAtEnd, functionOutputAtEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_START:
				return getFunctionOutputAtStart();
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_END:
				return getFunctionOutputAtEnd();
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
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_START:
				setFunctionOutputAtStart((Double)newValue);
				return;
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_END:
				setFunctionOutputAtEnd((Double)newValue);
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
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_START:
				setFunctionOutputAtStart(FUNCTION_OUTPUT_AT_START_EDEFAULT);
				return;
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_END:
				setFunctionOutputAtEnd(FUNCTION_OUTPUT_AT_END_EDEFAULT);
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
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_START:
				return functionOutputAtStart != FUNCTION_OUTPUT_AT_START_EDEFAULT;
			case DlimPackage.TREND__FUNCTION_OUTPUT_AT_END:
				return functionOutputAtEnd != FUNCTION_OUTPUT_AT_END_EDEFAULT;
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
		result.append(" (functionOutputAtStart: ");
		result.append(functionOutputAtStart);
		result.append(", functionOutputAtEnd: ");
		result.append(functionOutputAtEnd);
		result.append(')');
		return result.toString();
	}

} //TrendImpl
