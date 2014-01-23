/**
 */
package dlim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exponential Increase Logarithmic Decline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.ExponentialIncreaseLogarithmicDecline#getLogarithmicOrder <em>Logarithmic Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getExponentialIncreaseLogarithmicDecline()
 * @model
 * @generated
 */
public interface ExponentialIncreaseLogarithmicDecline extends Burst {
	/**
	 * Returns the value of the '<em><b>Logarithmic Order</b></em>' attribute.
	 * The default value is <code>"4.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logarithmic Order</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logarithmic Order</em>' attribute.
	 * @see #setLogarithmicOrder(double)
	 * @see dlim.DlimPackage#getExponentialIncreaseLogarithmicDecline_LogarithmicOrder()
	 * @model default="4.0"
	 * @generated
	 */
	double getLogarithmicOrder();

	/**
	 * Sets the value of the '{@link dlim.ExponentialIncreaseLogarithmicDecline#getLogarithmicOrder <em>Logarithmic Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Logarithmic Order</em>' attribute.
	 * @see #getLogarithmicOrder()
	 * @generated
	 */
	void setLogarithmicOrder(double value);

} // ExponentialIncreaseLogarithmicDecline
