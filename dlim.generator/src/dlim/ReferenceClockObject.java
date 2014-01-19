/**
 */
package dlim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Clock Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.ReferenceClockObject#getName <em>Name</em>}</li>
 *   <li>{@link dlim.ReferenceClockObject#getLoopTime <em>Loop Time</em>}</li>
 *   <li>{@link dlim.ReferenceClockObject#getSeqTime <em>Seq Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getReferenceClockObject()
 * @model
 * @generated
 */
public interface ReferenceClockObject extends EObject {
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
	 * @see dlim.DlimPackage#getReferenceClockObject_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dlim.ReferenceClockObject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Loop Time</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Time</em>' attribute.
	 * @see #setLoopTime(double)
	 * @see dlim.DlimPackage#getReferenceClockObject_LoopTime()
	 * @model default="0.0" derived="true"
	 * @generated
	 */
	double getLoopTime();

	/**
	 * Sets the value of the '{@link dlim.ReferenceClockObject#getLoopTime <em>Loop Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Time</em>' attribute.
	 * @see #getLoopTime()
	 * @generated
	 */
	void setLoopTime(double value);

	/**
	 * Returns the value of the '<em><b>Seq Time</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seq Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seq Time</em>' attribute.
	 * @see #setSeqTime(double)
	 * @see dlim.DlimPackage#getReferenceClockObject_SeqTime()
	 * @model default="0.0" derived="true"
	 * @generated
	 */
	double getSeqTime();

	/**
	 * Sets the value of the '{@link dlim.ReferenceClockObject#getSeqTime <em>Seq Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seq Time</em>' attribute.
	 * @see #getSeqTime()
	 * @generated
	 */
	void setSeqTime(double value);

} // ReferenceClockObject
