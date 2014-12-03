/**
 */
package tools.descartes.dlim;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Custom Function</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link tools.descartes.dlim.CustomFunction#getFunction <em>Function</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @see tools.descartes.dlim.DlimPackage#getCustomFunction()
 * @model
 * @generated
 */
public interface CustomFunction extends Function {
	/**
	 * Returns the value of the '<em><b>Function</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Function</em>' attribute.
	 * @see #setFunction(String)
	 * @see tools.descartes.dlim.DlimPackage#getCustomFunction_Function()
	 * @model
	 * @generated
	 */
	String getFunction();

	/**
	 * Sets the value of the '
	 * {@link tools.descartes.dlim.CustomFunction#getFunction <em>Function</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Function</em>' attribute.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean supported(DiagnosticChain chain, Map<?, ?> context);

} // CustomFunction
