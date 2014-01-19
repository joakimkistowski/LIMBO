/**
 */
package dlim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Univariate Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.UnivariateFunction#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getUnivariateFunction()
 * @model abstract="true"
 * @generated
 */
public interface UnivariateFunction extends Function {
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
	 * @see dlim.DlimPackage#getUnivariateFunction_Function()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Function getFunction();

	/**
	 * Sets the value of the '{@link dlim.UnivariateFunction#getFunction <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' containment reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Function value);

} // UnivariateFunction
