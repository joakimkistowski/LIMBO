/**
 */
package dlim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.Constant#getConstant <em>Constant</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getConstant()
 * @model
 * @generated
 */
public interface Constant extends Seasonal {
	/**
	 * Returns the value of the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant</em>' attribute.
	 * @see #setConstant(double)
	 * @see dlim.DlimPackage#getConstant_Constant()
	 * @model
	 * @generated
	 */
	double getConstant();

	/**
	 * Sets the value of the '{@link dlim.Constant#getConstant <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant</em>' attribute.
	 * @see #getConstant()
	 * @generated
	 */
	void setConstant(double value);

} // Constant
