/**
 */
package dlim;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see dlim.DlimPackage
 * @generated
 */
public interface DlimFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * This returns an instance of the hand-written CustomDlimFactoryImpl,
	 * allowing for model element impl modification in custom classes within
	 * the dlim.impl.custom package.
	 * @generated not
	 */
	DlimFactory eINSTANCE = dlim.impl.custom.CustomDlimFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence</em>'.
	 * @generated
	 */
	Sequence createSequence();

	/**
	 * Returns a new object of class '<em>Combinator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Combinator</em>'.
	 * @generated
	 */
	Combinator createCombinator();

	/**
	 * Returns a new object of class '<em>Time Dependent Function Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Dependent Function Container</em>'.
	 * @generated
	 */
	TimeDependentFunctionContainer createTimeDependentFunctionContainer();

	/**
	 * Returns a new object of class '<em>Uniform Noise</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uniform Noise</em>'.
	 * @generated
	 */
	UniformNoise createUniformNoise();

	/**
	 * Returns a new object of class '<em>Normal Noise</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Normal Noise</em>'.
	 * @generated
	 */
	NormalNoise createNormalNoise();

	/**
	 * Returns a new object of class '<em>Constant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constant</em>'.
	 * @generated
	 */
	Constant createConstant();

	/**
	 * Returns a new object of class '<em>Sin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sin</em>'.
	 * @generated
	 */
	Sin createSin();

	/**
	 * Returns a new object of class '<em>Exponential Increase And Decline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Increase And Decline</em>'.
	 * @generated
	 */
	ExponentialIncreaseAndDecline createExponentialIncreaseAndDecline();

	/**
	 * Returns a new object of class '<em>Exponential Increase Logarithmic Decline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Increase Logarithmic Decline</em>'.
	 * @generated
	 */
	ExponentialIncreaseLogarithmicDecline createExponentialIncreaseLogarithmicDecline();

	/**
	 * Returns a new object of class '<em>Linear Increase And Decline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Increase And Decline</em>'.
	 * @generated
	 */
	LinearIncreaseAndDecline createLinearIncreaseAndDecline();

	/**
	 * Returns a new object of class '<em>Absolute Sin</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Absolute Sin</em>'.
	 * @generated
	 */
	AbsoluteSin createAbsoluteSin();

	/**
	 * Returns a new object of class '<em>Linear Trend</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Trend</em>'.
	 * @generated
	 */
	LinearTrend createLinearTrend();

	/**
	 * Returns a new object of class '<em>Exponential Trend</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exponential Trend</em>'.
	 * @generated
	 */
	ExponentialTrend createExponentialTrend();

	/**
	 * Returns a new object of class '<em>Logarithmic Trend</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Logarithmic Trend</em>'.
	 * @generated
	 */
	LogarithmicTrend createLogarithmicTrend();

	/**
	 * Returns a new object of class '<em>Sin Trend</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sin Trend</em>'.
	 * @generated
	 */
	SinTrend createSinTrend();

	/**
	 * Returns a new object of class '<em>Reference Clock Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reference Clock Object</em>'.
	 * @generated
	 */
	ReferenceClockObject createReferenceClockObject();

	/**
	 * Returns a new object of class '<em>Arrival Rates From File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arrival Rates From File</em>'.
	 * @generated
	 */
	ArrivalRatesFromFile createArrivalRatesFromFile();

	/**
	 * Returns a new object of class '<em>Absolute Value Function</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Absolute Value Function</em>'.
	 * @generated
	 */
	AbsoluteValueFunction createAbsoluteValueFunction();

	/**
	 * Returns a new object of class '<em>Polynomial</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Polynomial</em>'.
	 * @generated
	 */
	Polynomial createPolynomial();

	/**
	 * Returns a new object of class '<em>Polynomial Factor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Polynomial Factor</em>'.
	 * @generated
	 */
	PolynomialFactor createPolynomialFactor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DlimPackage getDlimPackage();

} //DlimFactory
