/**
 */
package tools.descartes.dlim;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Polynomial</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tools.descartes.dlim.Polynomial#getFactors <em>Factors</em>}</li>
 * </ul>
 * </p>
 *
 * @see tools.descartes.dlim.DlimPackage#getPolynomial()
 * @model
 * @generated
 */
public interface Polynomial extends Function {
	/**
	 * Returns the value of the '<em><b>Factors</b></em>' containment reference list.
	 * The list contents are of type {@link tools.descartes.dlim.PolynomialFactor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factors</em>' containment reference list.
	 * @see tools.descartes.dlim.DlimPackage#getPolynomial_Factors()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<PolynomialFactor> getFactors();

} // Polynomial
