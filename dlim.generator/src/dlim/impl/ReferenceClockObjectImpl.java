/**
 */
package dlim.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import dlim.DlimPackage;
import dlim.ReferenceClockObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Clock Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.ReferenceClockObjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link dlim.impl.ReferenceClockObjectImpl#getLoopTime <em>Loop Time</em>}</li>
 *   <li>{@link dlim.impl.ReferenceClockObjectImpl#getSeqTime <em>Seq Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceClockObjectImpl extends MinimalEObjectImpl.Container implements ReferenceClockObject {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLoopTime() <em>Loop Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopTime()
	 * @generated
	 * @ordered
	 */
	protected static final double LOOP_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLoopTime() <em>Loop Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopTime()
	 * @generated
	 * @ordered
	 */
	protected double loopTime = LOOP_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSeqTime() <em>Seq Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeqTime()
	 * @generated
	 * @ordered
	 */
	protected static final double SEQ_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSeqTime() <em>Seq Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeqTime()
	 * @generated
	 * @ordered
	 */
	protected double seqTime = SEQ_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceClockObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.REFERENCE_CLOCK_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.REFERENCE_CLOCK_OBJECT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLoopTime() {
		return loopTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopTime(double newLoopTime) {
		double oldLoopTime = loopTime;
		loopTime = newLoopTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.REFERENCE_CLOCK_OBJECT__LOOP_TIME, oldLoopTime, loopTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSeqTime() {
		return seqTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSeqTime(double newSeqTime) {
		double oldSeqTime = seqTime;
		seqTime = newSeqTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.REFERENCE_CLOCK_OBJECT__SEQ_TIME, oldSeqTime, seqTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.REFERENCE_CLOCK_OBJECT__NAME:
				return getName();
			case DlimPackage.REFERENCE_CLOCK_OBJECT__LOOP_TIME:
				return getLoopTime();
			case DlimPackage.REFERENCE_CLOCK_OBJECT__SEQ_TIME:
				return getSeqTime();
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
			case DlimPackage.REFERENCE_CLOCK_OBJECT__NAME:
				setName((String)newValue);
				return;
			case DlimPackage.REFERENCE_CLOCK_OBJECT__LOOP_TIME:
				setLoopTime((Double)newValue);
				return;
			case DlimPackage.REFERENCE_CLOCK_OBJECT__SEQ_TIME:
				setSeqTime((Double)newValue);
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
			case DlimPackage.REFERENCE_CLOCK_OBJECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DlimPackage.REFERENCE_CLOCK_OBJECT__LOOP_TIME:
				setLoopTime(LOOP_TIME_EDEFAULT);
				return;
			case DlimPackage.REFERENCE_CLOCK_OBJECT__SEQ_TIME:
				setSeqTime(SEQ_TIME_EDEFAULT);
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
			case DlimPackage.REFERENCE_CLOCK_OBJECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DlimPackage.REFERENCE_CLOCK_OBJECT__LOOP_TIME:
				return loopTime != LOOP_TIME_EDEFAULT;
			case DlimPackage.REFERENCE_CLOCK_OBJECT__SEQ_TIME:
				return seqTime != SEQ_TIME_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", loopTime: ");
		result.append(loopTime);
		result.append(", seqTime: ");
		result.append(seqTime);
		result.append(')');
		return result.toString();
	}

} //ReferenceClockObjectImpl
