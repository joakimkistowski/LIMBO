/**
 */
package tools.descartes.dlim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combinator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tools.descartes.dlim.Combinator#getOperator <em>Operator</em>}</li>
 *   <li>{@link tools.descartes.dlim.Combinator#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see tools.descartes.dlim.DlimPackage#getCombinator()
 * @model
 * @generated
 */
public interface Combinator extends EObject {
	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The default value is <code>"ADD"</code>.
	 * The literals are from the enumeration {@link tools.descartes.dlim.Operator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see tools.descartes.dlim.Operator
	 * @see #setOperator(Operator)
	 * @see tools.descartes.dlim.DlimPackage#getCombinator_Operator()
	 * @model default="ADD"
	 * @generated
	 */
	Operator getOperator();

	/**
	 * Sets the value of the '{@link tools.descartes.dlim.Combinator#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see tools.descartes.dlim.Operator
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
	 * @see tools.descartes.dlim.DlimPackage#getCombinator_Function()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Function getFunction();

	/**
	 * Sets the value of the '{@link tools.descartes.dlim.Combinator#getFunction <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' containment reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Function value);

} // Combinator
