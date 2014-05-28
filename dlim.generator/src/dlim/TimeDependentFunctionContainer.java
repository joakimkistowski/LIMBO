/**
 */
package dlim;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getName <em>Name</em>}</li>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getDuration <em>Duration</em>}</li>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getFirstIterationStart <em>First Iteration Start</em>}</li>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getFirstIterationEnd <em>First Iteration End</em>}</li>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getFunction <em>Function</em>}</li>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}</li>
 *   <li>{@link dlim.TimeDependentFunctionContainer#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getTimeDependentFunctionContainer()
 * @model
 * @generated
 */
public interface TimeDependentFunctionContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(double)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_Duration()
	 * @model default="1.0"
	 * @generated
	 */
	double getDuration();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(double value);

	/**
	 * Returns the value of the '<em><b>First Iteration Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Iteration Start</em>' attribute.
	 * @see #setFirstIterationStart(double)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_FirstIterationStart()
	 * @model derived="true"
	 * @generated
	 */
	double getFirstIterationStart();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getFirstIterationStart <em>First Iteration Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Iteration Start</em>' attribute.
	 * @see #getFirstIterationStart()
	 * @generated
	 */
	void setFirstIterationStart(double value);

	/**
	 * Returns the value of the '<em><b>First Iteration End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Iteration End</em>' attribute.
	 * @see #setFirstIterationEnd(double)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_FirstIterationEnd()
	 * @model derived="true"
	 * @generated
	 */
	double getFirstIterationEnd();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getFirstIterationEnd <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Iteration End</em>' attribute.
	 * @see #getFirstIterationEnd()
	 * @generated
	 */
	void setFirstIterationEnd(double value);

	/**
	 * Returns the value of the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' containment reference.
	 * @see #setFunction(Function)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_Function()
	 * @model containment="true"
	 * @generated
	 */
	Function getFunction();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getFunction <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' containment reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Function value);

	/**
	 * Returns the value of the '<em><b>Point Of Reference Clock Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Point Of Reference Clock Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Point Of Reference Clock Object</em>' reference.
	 * @see #setPointOfReferenceClockObject(ReferenceClockObject)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_PointOfReferenceClockObject()
	 * @model
	 * @generated
	 */
	ReferenceClockObject getPointOfReferenceClockObject();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point Of Reference Clock Object</em>' reference.
	 * @see #getPointOfReferenceClockObject()
	 * @generated
	 */
	void setPointOfReferenceClockObject(ReferenceClockObject value);

	/**
	 * Returns the value of the '<em><b>Point Of Reference Clock Type</b></em>' attribute.
	 * The default value is <code>"CONTAINERCLOCK"</code>.
	 * The literals are from the enumeration {@link dlim.ClockType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Point Of Reference Clock Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Point Of Reference Clock Type</em>' attribute.
	 * @see dlim.ClockType
	 * @see #setPointOfReferenceClockType(ClockType)
	 * @see dlim.DlimPackage#getTimeDependentFunctionContainer_PointOfReferenceClockType()
	 * @model default="CONTAINERCLOCK"
	 * @generated
	 */
	ClockType getPointOfReferenceClockType();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentFunctionContainer#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point Of Reference Clock Type</em>' attribute.
	 * @see dlim.ClockType
	 * @see #getPointOfReferenceClockType()
	 * @generated
	 */
	void setPointOfReferenceClockType(ClockType value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean durationGreaterZero(DiagnosticChain chain, Map<?, ?> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean referenceClockInTreeNode(DiagnosticChain chain, Map<?, ?> context);

} // Element
