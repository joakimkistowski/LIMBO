/**
 */
package tools.descartes.dlim;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tools.descartes.dlim.Function#getCombine <em>Combine</em>}</li>
 * </ul>
 * </p>
 *
 * @see tools.descartes.dlim.DlimPackage#getFunction()
 * @model abstract="true"
 * @generated
 */
public interface Function extends EObject {
	/**
	 * Returns the value of the '<em><b>Combine</b></em>' containment reference list.
	 * The list contents are of type {@link tools.descartes.dlim.Combinator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combine</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combine</em>' containment reference list.
	 * @see tools.descartes.dlim.DlimPackage#getFunction_Combine()
	 * @model containment="true"
	 * @generated
	 */
	EList<Combinator> getCombine();

} // Function
