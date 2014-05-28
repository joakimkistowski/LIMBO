/**
 */
package dlim.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import dlim.ClockType;
import dlim.DlimPackage;
import dlim.Function;
import dlim.ReferenceClockObject;
import dlim.TimeDependentFunctionContainer;
import dlim.generator.ModelEvaluatorUtil;
import dlim.util.DlimValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Dependent Function Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getName <em>Name</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getFirstIterationStart <em>First Iteration Start</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getFirstIterationEnd <em>First Iteration End</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentFunctionContainerImpl#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeDependentFunctionContainerImpl extends MinimalEObjectImpl.Container implements TimeDependentFunctionContainer {
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
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final double DURATION_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected double duration = DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirstIterationStart() <em>First Iteration Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationStart()
	 * @generated
	 * @ordered
	 */
	protected static final double FIRST_ITERATION_START_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFirstIterationStart() <em>First Iteration Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationStart()
	 * @generated
	 * @ordered
	 */
	protected double firstIterationStart = FIRST_ITERATION_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirstIterationEnd() <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationEnd()
	 * @generated
	 * @ordered
	 */
	protected static final double FIRST_ITERATION_END_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFirstIterationEnd() <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationEnd()
	 * @generated
	 * @ordered
	 */
	protected double firstIterationEnd = FIRST_ITERATION_END_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFunction() <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
	protected Function function;

	/**
	 * The cached value of the '{@link #getPointOfReferenceClockObject() <em>Point Of Reference Clock Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointOfReferenceClockObject()
	 * @generated
	 * @ordered
	 */
	protected ReferenceClockObject pointOfReferenceClockObject;

	/**
	 * The default value of the '{@link #getPointOfReferenceClockType() <em>Point Of Reference Clock Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointOfReferenceClockType()
	 * @generated
	 * @ordered
	 */
	protected static final ClockType POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT = ClockType.CONTAINER_CLOCK;

	/**
	 * The cached value of the '{@link #getPointOfReferenceClockType() <em>Point Of Reference Clock Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointOfReferenceClockType()
	 * @generated
	 * @ordered
	 */
	protected ClockType pointOfReferenceClockType = POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeDependentFunctionContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.TIME_DEPENDENT_FUNCTION_CONTAINER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(double newDuration) {
		double oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFirstIterationStart() {
		return firstIterationStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstIterationStart(double newFirstIterationStart) {
		double oldFirstIterationStart = firstIterationStart;
		firstIterationStart = newFirstIterationStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START, oldFirstIterationStart, firstIterationStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFirstIterationEnd() {
		return firstIterationEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstIterationEnd(double newFirstIterationEnd) {
		double oldFirstIterationEnd = firstIterationEnd;
		firstIterationEnd = newFirstIterationEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END, oldFirstIterationEnd, firstIterationEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunction(Function newFunction, NotificationChain msgs) {
		Function oldFunction = function;
		function = newFunction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION, oldFunction, newFunction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunction(Function newFunction) {
		if (newFunction != function) {
			NotificationChain msgs = null;
			if (function != null)
				msgs = ((InternalEObject)function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION, null, msgs);
			if (newFunction != null)
				msgs = ((InternalEObject)newFunction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION, null, msgs);
			msgs = basicSetFunction(newFunction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION, newFunction, newFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceClockObject getPointOfReferenceClockObject() {
		if (pointOfReferenceClockObject != null && pointOfReferenceClockObject.eIsProxy()) {
			InternalEObject oldPointOfReferenceClockObject = (InternalEObject)pointOfReferenceClockObject;
			pointOfReferenceClockObject = (ReferenceClockObject)eResolveProxy(oldPointOfReferenceClockObject);
			if (pointOfReferenceClockObject != oldPointOfReferenceClockObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT, oldPointOfReferenceClockObject, pointOfReferenceClockObject));
			}
		}
		return pointOfReferenceClockObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceClockObject basicGetPointOfReferenceClockObject() {
		return pointOfReferenceClockObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointOfReferenceClockObject(ReferenceClockObject newPointOfReferenceClockObject) {
		ReferenceClockObject oldPointOfReferenceClockObject = pointOfReferenceClockObject;
		pointOfReferenceClockObject = newPointOfReferenceClockObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT, oldPointOfReferenceClockObject, pointOfReferenceClockObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClockType getPointOfReferenceClockType() {
		return pointOfReferenceClockType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointOfReferenceClockType(ClockType newPointOfReferenceClockType) {
		ClockType oldPointOfReferenceClockType = pointOfReferenceClockType;
		pointOfReferenceClockType = newPointOfReferenceClockType == null ? POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT : newPointOfReferenceClockType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE, oldPointOfReferenceClockType, pointOfReferenceClockType));
	}

	
	/**
	 * Returns a validation error if the Element's duration is <= 0.
	 * @generated not
	 */
	public boolean durationGreaterZero(DiagnosticChain chain, Map<?, ?> context) {
		if (getDuration() <= 0) {
			if (chain != null) {
				chain.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DlimValidator.DIAGNOSTIC_SOURCE,
						 DlimValidator.TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION_GREATER_ZERO,
						 "This TimeDependentFunctionContainer must be executed at some point. As a result its Duration must be > 0.0",
						 new Object [] { this, DlimPackage.eINSTANCE.getTimeDependentFunctionContainer_Duration()}));
			}
			return false;
		}
		return true;
	}

	/**
	 * Checks whether the referenceClock referenced in this Element will be
	 * running at the time this Element is being executed.
	 * @generated not
	 */
	public boolean referenceClockInTreeNode(DiagnosticChain chain, Map<?, ?> context) {
		if (getPointOfReferenceClockObject() != null
				&& !ModelEvaluatorUtil.containsInTree(ModelEvaluatorUtil.getParentSequence(getPointOfReferenceClockObject()), this)) {
			String clockName = getPointOfReferenceClockObject().getName();
			if (clockName == null) {
				clockName = "<UNNAMED>";
			}
			if (chain != null) {
				chain.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 DlimValidator.DIAGNOSTIC_SOURCE,
						 DlimValidator.TIME_DEPENDENT_FUNCTION_CONTAINER__REFERENCE_CLOCK_IN_TREE_NODE,
						 "The Sequence holding the PointOfReferenceClockObject '"
						 + clockName + "' must be an (indirect) parent of this TimeDependentFunctionContainer.",
						 new Object [] { this, DlimPackage.eINSTANCE.getTimeDependentFunctionContainer_PointOfReferenceClockObject()}));
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION:
				return basicSetFunction(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__NAME:
				return getName();
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION:
				return getDuration();
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START:
				return getFirstIterationStart();
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END:
				return getFirstIterationEnd();
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION:
				return getFunction();
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				if (resolve) return getPointOfReferenceClockObject();
				return basicGetPointOfReferenceClockObject();
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				return getPointOfReferenceClockType();
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
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__NAME:
				setName((String)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION:
				setDuration((Double)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START:
				setFirstIterationStart((Double)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END:
				setFirstIterationEnd((Double)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION:
				setFunction((Function)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				setPointOfReferenceClockObject((ReferenceClockObject)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				setPointOfReferenceClockType((ClockType)newValue);
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
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START:
				setFirstIterationStart(FIRST_ITERATION_START_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END:
				setFirstIterationEnd(FIRST_ITERATION_END_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION:
				setFunction((Function)null);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				setPointOfReferenceClockObject((ReferenceClockObject)null);
				return;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				setPointOfReferenceClockType(POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT);
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
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION:
				return duration != DURATION_EDEFAULT;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START:
				return firstIterationStart != FIRST_ITERATION_START_EDEFAULT;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END:
				return firstIterationEnd != FIRST_ITERATION_END_EDEFAULT;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION:
				return function != null;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				return pointOfReferenceClockObject != null;
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				return pointOfReferenceClockType != POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT;
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
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER___DURATION_GREATER_ZERO__DIAGNOSTICCHAIN_MAP:
				return durationGreaterZero((DiagnosticChain)arguments.get(0), (Map<?, ?>)arguments.get(1));
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER___REFERENCE_CLOCK_IN_TREE_NODE__DIAGNOSTICCHAIN_MAP:
				return referenceClockInTreeNode((DiagnosticChain)arguments.get(0), (Map<?, ?>)arguments.get(1));
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
		result.append(" (name: ");
		result.append(name);
		result.append(", duration: ");
		result.append(duration);
		result.append(", firstIterationStart: ");
		result.append(firstIterationStart);
		result.append(", firstIterationEnd: ");
		result.append(firstIterationEnd);
		result.append(", pointOfReferenceClockType: ");
		result.append(pointOfReferenceClockType);
		result.append(')');
		return result.toString();
	}

} //TimeDependentFunctionContainerImpl
