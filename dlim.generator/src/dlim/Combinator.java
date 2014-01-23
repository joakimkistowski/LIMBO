/**
 */
package dlim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combinator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.Combinator#getOperator <em>Operator</em>}</li>
 *   <li>{@link dlim.Combinator#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getCombinator()
 * @model
 * @generated
 */
public interface Combinator extends EObject {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The default value is <code>"ADD"</code>.
	 * The literals are from the enumeration {@link dlim.Operator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see dlim.Operator
	 * @see #setOperator(Operator)
	 * @see dlim.DlimPackage#getCombinator_Operator()
	 * @model default="ADD"
	 * @generated
	 */
	Operator getOperator();

	/**
	 * Sets the value of the '{@link dlim.Combinator#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see dlim.Operator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(Operator value);

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
	 * @see dlim.DlimPackage#getCombinator_Function()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Function getFunction();

	/**
	 * Sets the value of the '{@link dlim.Combinator#getFunction <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' containment reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Function value);

} // Combinator
