/**
 */
package dlim;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Burst</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.Burst#getPeak <em>Peak</em>}</li>
 *   <li>{@link dlim.Burst#getBase <em>Base</em>}</li>
 *   <li>{@link dlim.Burst#getPeakTime <em>Peak Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getBurst()
 * @model abstract="true"
 * @generated
 */
public interface Burst extends Function {

	/**
	 * Returns the value of the '<em><b>Peak</b></em>' attribute.
	 * The default value is <code>"2.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Peak Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Peak</em>' attribute.
	 * @see #setPeak(double)
	 * @see dlim.DlimPackage#getBurst_Peak()
	 * @model default="2.0"
	 * @generated
	 */
	double getPeak();

	/**
	 * Sets the value of the '{@link dlim.Burst#getPeak <em>Peak</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak</em>' attribute.
	 * @see #getPeak()
	 * @generated
	 */
	void setPeak(double value);

	/**
	 * Returns the value of the '<em><b>Base</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base</em>' attribute.
	 * @see #setBase(double)
	 * @see dlim.DlimPackage#getBurst_Base()
	 * @model default="0.0"
	 * @generated
	 */
	double getBase();

	/**
	 * Sets the value of the '{@link dlim.Burst#getBase <em>Base</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' attribute.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(double value);

	/**
	 * Returns the value of the '<em><b>Peak Time</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Peak Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Peak Time</em>' attribute.
	 * @see #setPeakTime(double)
	 * @see dlim.DlimPackage#getBurst_PeakTime()
	 * @model default="1.0"
	 * @generated
	 */
	double getPeakTime();

	/**
	 * Sets the value of the '{@link dlim.Burst#getPeakTime <em>Peak Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Peak Time</em>' attribute.
	 * @see #getPeakTime()
	 * @generated
	 */
	void setPeakTime(double value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean peakTimeGreaterZero(DiagnosticChain chain, Map<?, ?> context);
} // Burst
