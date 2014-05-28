/**
 */
package dlim.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import dlim.Burst;
import dlim.DlimPackage;
import dlim.util.DlimValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Burst</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.BurstImpl#getPeak <em>Peak</em>}</li>
 *   <li>{@link dlim.impl.BurstImpl#getBase <em>Base</em>}</li>
 *   <li>{@link dlim.impl.BurstImpl#getPeakTime <em>Peak Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BurstImpl extends FunctionImpl implements Burst {
	/**
	 * The default value of the '{@link #getPeak() <em>Peak</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeak()
	 * @generated
	 * @ordered
	 */
	protected static final double PEAK_EDEFAULT = 2.0;
	/**
	 * The cached value of the '{@link #getPeak() <em>Peak</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeak()
	 * @generated
	 * @ordered
	 */
	protected double peak = PEAK_EDEFAULT;
	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final double BASE_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected double base = BASE_EDEFAULT;
	/**
	 * The default value of the '{@link #getPeakTime() <em>Peak Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakTime()
	 * @generated
	 * @ordered
	 */
	protected static final double PEAK_TIME_EDEFAULT = 1.0;
	/**
	 * The cached value of the '{@link #getPeakTime() <em>Peak Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeakTime()
	 * @generated
	 * @ordered
	 */
	protected double peakTime = PEAK_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BurstImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.BURST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPeak() {
		return peak;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeak(double newPeak) {
		double oldPeak = peak;
		peak = newPeak;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.BURST__PEAK, oldPeak, peak));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase(double newBase) {
		double oldBase = base;
		base = newBase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.BURST__BASE, oldBase, base));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPeakTime() {
		return peakTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPeakTime(double newPeakTime) {
		double oldPeakTime = peakTime;
		peakTime = newPeakTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.BURST__PEAK_TIME, oldPeakTime, peakTime));
	}

	/**
	 * Returns a validation error if the Burst's peakTime is <= 0;
	 * @generated not
	 */
	public boolean peakTimeGreaterZero(DiagnosticChain chain, Map<?, ?> context) {
		if (getPeakTime() <= 0) {
			if (chain != null) {
				chain.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DlimValidator.DIAGNOSTIC_SOURCE,
						 DlimValidator.BURST__PEAK_TIME_GREATER_ZERO,
						 "peakTime must be > 0. Use a Trend, if you want peakTime as 0.",
						 new Object [] { this, DlimPackage.eINSTANCE.getBurst_PeakTime() }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.BURST__PEAK:
				return getPeak();
			case DlimPackage.BURST__BASE:
				return getBase();
			case DlimPackage.BURST__PEAK_TIME:
				return getPeakTime();
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
			case DlimPackage.BURST__PEAK:
				setPeak((Double)newValue);
				return;
			case DlimPackage.BURST__BASE:
				setBase((Double)newValue);
				return;
			case DlimPackage.BURST__PEAK_TIME:
				setPeakTime((Double)newValue);
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
			case DlimPackage.BURST__PEAK:
				setPeak(PEAK_EDEFAULT);
				return;
			case DlimPackage.BURST__BASE:
				setBase(BASE_EDEFAULT);
				return;
			case DlimPackage.BURST__PEAK_TIME:
				setPeakTime(PEAK_TIME_EDEFAULT);
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
			case DlimPackage.BURST__PEAK:
				return peak != PEAK_EDEFAULT;
			case DlimPackage.BURST__BASE:
				return base != BASE_EDEFAULT;
			case DlimPackage.BURST__PEAK_TIME:
				return peakTime != PEAK_TIME_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case DlimPackage.BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP:
				return peakTimeGreaterZero((DiagnosticChain)arguments.get(0), (Map<?, ?>)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (peak: ");
		result.append(peak);
		result.append(", base: ");
		result.append(base);
		result.append(", peakTime: ");
		result.append(peakTime);
		result.append(')');
		return result.toString();
	}

} //BurstImpl
