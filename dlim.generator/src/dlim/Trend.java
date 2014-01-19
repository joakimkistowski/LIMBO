/**
 */
package dlim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trend</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.Trend#getFunctionOutputAtStart <em>Function Output At Start</em>}</li>
 *   <li>{@link dlim.Trend#getFunctionOutputAtEnd <em>Function Output At End</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getTrend()
 * @model abstract="true"
 * @generated
 */
public interface Trend extends Function {

	/**
	 * Returns the value of the '<em><b>Function Output At Start</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Output At Start</em>' attribute.
	 * @see #setFunctionOutputAtStart(double)
	 * @see dlim.DlimPackage#getTrend_FunctionOutputAtStart()
	 * @model default="0.0"
	 * @generated
	 */
	double getFunctionOutputAtStart();

	/**
	 * Sets the value of the '{@link dlim.Trend#getFunctionOutputAtStart <em>Function Output At Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Output At Start</em>' attribute.
	 * @see #getFunctionOutputAtStart()
	 * @generated
	 */
	void setFunctionOutputAtStart(double value);

	/**
	 * Returns the value of the '<em><b>Function Output At End</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Output At End</em>' attribute.
	 * @see #setFunctionOutputAtEnd(double)
	 * @see dlim.DlimPackage#getTrend_FunctionOutputAtEnd()
	 * @model default="1.0"
	 * @generated
	 */
	double getFunctionOutputAtEnd();

	/**
	 * Sets the value of the '{@link dlim.Trend#getFunctionOutputAtEnd <em>Function Output At End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Output At End</em>' attribute.
	 * @see #getFunctionOutputAtEnd()
	 * @generated
	 */
	void setFunctionOutputAtEnd(double value);
} // Trend
