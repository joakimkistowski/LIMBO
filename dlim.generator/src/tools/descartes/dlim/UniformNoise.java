/**
 */
package tools.descartes.dlim;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Uniform Noise</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link tools.descartes.dlim.UniformNoise#getMin <em>Min</em>}</li>
 * <li>{@link tools.descartes.dlim.UniformNoise#getMax <em>Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see tools.descartes.dlim.DlimPackage#getUniformNoise()
 * @model
 * @generated
 */
public interface UniformNoise extends Noise {
	/**
	 * Returns the value of the '<em><b>Min</b></em>' attribute. The default
	 * value is <code>"1.0"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Min</em>' attribute.
	 * @see #setMin(double)
	 * @see tools.descartes.dlim.DlimPackage#getUniformNoise_Min()
	 * @model default="1.0"
	 * @generated
	 */
	double getMin();

	/**
	 * Sets the value of the '{@link tools.descartes.dlim.UniformNoise#getMin
	 * <em>Min</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Min</em>' attribute.
	 * @see #getMin()
	 * @generated
	 */
	void setMin(double value);

	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute. The default
	 * value is <code>"1.0"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(double)
	 * @see tools.descartes.dlim.DlimPackage#getUniformNoise_Max()
	 * @model default="1.0"
	 * @generated
	 */
	double getMax();

	/**
	 * Sets the value of the '{@link tools.descartes.dlim.UniformNoise#getMax
	 * <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(double value);

} // UniformNoise
