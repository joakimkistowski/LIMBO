/**
 */
package dlim;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.Sequence#getName <em>Name</em>}</li>
 *   <li>{@link dlim.Sequence#getTerminateAfterTime <em>Terminate After Time</em>}</li>
 *   <li>{@link dlim.Sequence#getReferenceClock <em>Reference Clock</em>}</li>
 *   <li>{@link dlim.Sequence#getSequenceFunctionContainers <em>Sequence Function Containers</em>}</li>
 *   <li>{@link dlim.Sequence#getTerminateAfterLoops <em>Terminate After Loops</em>}</li>
 *   <li>{@link dlim.Sequence#getFirstIterationStart <em>First Iteration Start</em>}</li>
 *   <li>{@link dlim.Sequence#getFirstIterationEnd <em>First Iteration End</em>}</li>
 *   <li>{@link dlim.Sequence#getLoopDuration <em>Loop Duration</em>}</li>
 *   <li>{@link dlim.Sequence#getFinalDuration <em>Final Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getSequence()
 * @model
 * @generated
 */
public interface Sequence extends Function {
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
	 * @see dlim.DlimPackage#getSequence_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Terminate After Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Terminate After Time</em>' attribute.
	 * @see #setTerminateAfterTime(double)
	 * @see dlim.DlimPackage#getSequence_TerminateAfterTime()
	 * @model
	 * @generated
	 */
	double getTerminateAfterTime();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getTerminateAfterTime <em>Terminate After Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminate After Time</em>' attribute.
	 * @see #getTerminateAfterTime()
	 * @generated
	 */
	void setTerminateAfterTime(double value);

	/**
	 * Returns the value of the '<em><b>Reference Clock</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Clock</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Clock</em>' containment reference.
	 * @see #setReferenceClock(ReferenceClockObject)
	 * @see dlim.DlimPackage#getSequence_ReferenceClock()
	 * @model containment="true"
	 * @generated
	 */
	ReferenceClockObject getReferenceClock();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getReferenceClock <em>Reference Clock</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Clock</em>' containment reference.
	 * @see #getReferenceClock()
	 * @generated
	 */
	void setReferenceClock(ReferenceClockObject value);

	/**
	 * Returns the value of the '<em><b>Sequence Function Containers</b></em>' containment reference list.
	 * The list contents are of type {@link dlim.TimeDependentFunctionContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Function Containers</em>' containment reference list.
	 * @see dlim.DlimPackage#getSequence_SequenceFunctionContainers()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<TimeDependentFunctionContainer> getSequenceFunctionContainers();

	/**
	 * Returns the value of the '<em><b>Terminate After Loops</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loops</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Terminate After Loops</em>' attribute.
	 * @see #setTerminateAfterLoops(int)
	 * @see dlim.DlimPackage#getSequence_TerminateAfterLoops()
	 * @model default="-1"
	 * @generated
	 */
	int getTerminateAfterLoops();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getTerminateAfterLoops <em>Terminate After Loops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminate After Loops</em>' attribute.
	 * @see #getTerminateAfterLoops()
	 * @generated
	 */
	void setTerminateAfterLoops(int value);

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
	 * @see dlim.DlimPackage#getSequence_FirstIterationStart()
	 * @model derived="true"
	 * @generated
	 */
	double getFirstIterationStart();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getFirstIterationStart <em>First Iteration Start</em>}' attribute.
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
	 * @see dlim.DlimPackage#getSequence_FirstIterationEnd()
	 * @model derived="true"
	 * @generated
	 */
	double getFirstIterationEnd();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getFirstIterationEnd <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Iteration End</em>' attribute.
	 * @see #getFirstIterationEnd()
	 * @generated
	 */
	void setFirstIterationEnd(double value);

	/**
	 * Returns the value of the '<em><b>Loop Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Duration</em>' attribute.
	 * @see #setLoopDuration(double)
	 * @see dlim.DlimPackage#getSequence_LoopDuration()
	 * @model derived="true"
	 * @generated
	 */
	double getLoopDuration();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getLoopDuration <em>Loop Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Duration</em>' attribute.
	 * @see #getLoopDuration()
	 * @generated
	 */
	void setLoopDuration(double value);

	/**
	 * Returns the value of the '<em><b>Final Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final Duration</em>' attribute.
	 * @see #setFinalDuration(double)
	 * @see dlim.DlimPackage#getSequence_FinalDuration()
	 * @model derived="true"
	 * @generated
	 */
	double getFinalDuration();

	/**
	 * Sets the value of the '{@link dlim.Sequence#getFinalDuration <em>Final Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final Duration</em>' attribute.
	 * @see #getFinalDuration()
	 * @generated
	 */
	void setFinalDuration(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean durationDefined(DiagnosticChain chain, Map<?, ?> context);

} // Sequence
