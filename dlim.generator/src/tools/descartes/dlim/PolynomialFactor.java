/**
 */
package tools.descartes.dlim;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Polynomial Factor</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link tools.descartes.dlim.PolynomialFactor#getFactor <em>Factor</em>}</li>
 * <li>{@link tools.descartes.dlim.PolynomialFactor#getOffset <em>Offset</em>}</li>
 * </ul>
 * </p>
 *
 * @see tools.descartes.dlim.DlimPackage#getPolynomialFactor()
 * @model
 * @generated
 */
public interface PolynomialFactor extends EObject {
	/**
	 * Returns the value of the '<em><b>Factor</b></em>' attribute. The default
	 * value is <code>"0.0"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factor</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Factor</em>' attribute.
	 * @see #setFactor(double)
	 * @see tools.descartes.dlim.DlimPackage#getPolynomialFactor_Factor()
	 * @model default="0.0" derived="true"
	 * @generated
	 */
	double getFactor();

	/**
	 * Sets the value of the '
	 * {@link tools.descartes.dlim.PolynomialFactor#getFactor <em>Factor</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Factor</em>' attribute.
	 * @see #getFactor()
	 * @generated
	 */
	void setFactor(double value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute. The default
	 * value is <code>"0.0"</code>. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Offset</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(double)
	 * @see tools.descartes.dlim.DlimPackage#getPolynomialFactor_Offset()
	 * @model default="0.0" derived="true"
	 * @generated
	 */
	double getOffset();

	/**
	 * Sets the value of the '
	 * {@link tools.descartes.dlim.PolynomialFactor#getOffset <em>Offset</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(double value);

} // PolynomialFactor
