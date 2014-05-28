/**
 */
package dlim.util;

import dlim.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import dlim.AbsoluteSin;
import dlim.AbsoluteValueFunction;
import dlim.ArrivalRatesFromFile;
import dlim.Burst;
import dlim.Combinator;
import dlim.Constant;
import dlim.DlimPackage;
import dlim.ExponentialIncreaseAndDecline;
import dlim.ExponentialIncreaseLogarithmicDecline;
import dlim.ExponentialTrend;
import dlim.Function;
import dlim.LinearIncreaseAndDecline;
import dlim.LinearTrend;
import dlim.LogarithmicTrend;
import dlim.Noise;
import dlim.NormalNoise;
import dlim.Polynomial;
import dlim.PolynomialFactor;
import dlim.ReferenceClockObject;
import dlim.Seasonal;
import dlim.Sequence;
import dlim.Sin;
import dlim.SinTrend;
import dlim.TimeDependentFunctionContainer;
import dlim.Trend;
import dlim.UniformNoise;
import dlim.UnivariateFunction;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see dlim.DlimPackage
 * @generated
 */
public class DlimSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DlimPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DlimSwitch() {
		if (modelPackage == null) {
			modelPackage = DlimPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case DlimPackage.SEQUENCE: {
				Sequence sequence = (Sequence)theEObject;
				T result = caseSequence(sequence);
				if (result == null) result = caseFunction(sequence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.COMBINATOR: {
				Combinator combinator = (Combinator)theEObject;
				T result = caseCombinator(combinator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER: {
				TimeDependentFunctionContainer timeDependentFunctionContainer = (TimeDependentFunctionContainer)theEObject;
				T result = caseTimeDependentFunctionContainer(timeDependentFunctionContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.SEASONAL: {
				Seasonal seasonal = (Seasonal)theEObject;
				T result = caseSeasonal(seasonal);
				if (result == null) result = caseFunction(seasonal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.NOISE: {
				Noise noise = (Noise)theEObject;
				T result = caseNoise(noise);
				if (result == null) result = caseFunction(noise);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.UNIFORM_NOISE: {
				UniformNoise uniformNoise = (UniformNoise)theEObject;
				T result = caseUniformNoise(uniformNoise);
				if (result == null) result = caseNoise(uniformNoise);
				if (result == null) result = caseFunction(uniformNoise);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.NORMAL_NOISE: {
				NormalNoise normalNoise = (NormalNoise)theEObject;
				T result = caseNormalNoise(normalNoise);
				if (result == null) result = caseNoise(normalNoise);
				if (result == null) result = caseFunction(normalNoise);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.BURST: {
				Burst burst = (Burst)theEObject;
				T result = caseBurst(burst);
				if (result == null) result = caseFunction(burst);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.TREND: {
				Trend trend = (Trend)theEObject;
				T result = caseTrend(trend);
				if (result == null) result = caseFunction(trend);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.CONSTANT: {
				Constant constant = (Constant)theEObject;
				T result = caseConstant(constant);
				if (result == null) result = caseSeasonal(constant);
				if (result == null) result = caseFunction(constant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.SIN: {
				Sin sin = (Sin)theEObject;
				T result = caseSin(sin);
				if (result == null) result = caseSeasonal(sin);
				if (result == null) result = caseFunction(sin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.EXPONENTIAL_INCREASE_AND_DECLINE: {
				ExponentialIncreaseAndDecline exponentialIncreaseAndDecline = (ExponentialIncreaseAndDecline)theEObject;
				T result = caseExponentialIncreaseAndDecline(exponentialIncreaseAndDecline);
				if (result == null) result = caseBurst(exponentialIncreaseAndDecline);
				if (result == null) result = caseFunction(exponentialIncreaseAndDecline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE: {
				ExponentialIncreaseLogarithmicDecline exponentialIncreaseLogarithmicDecline = (ExponentialIncreaseLogarithmicDecline)theEObject;
				T result = caseExponentialIncreaseLogarithmicDecline(exponentialIncreaseLogarithmicDecline);
				if (result == null) result = caseBurst(exponentialIncreaseLogarithmicDecline);
				if (result == null) result = caseFunction(exponentialIncreaseLogarithmicDecline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.LINEAR_INCREASE_AND_DECLINE: {
				LinearIncreaseAndDecline linearIncreaseAndDecline = (LinearIncreaseAndDecline)theEObject;
				T result = caseLinearIncreaseAndDecline(linearIncreaseAndDecline);
				if (result == null) result = caseBurst(linearIncreaseAndDecline);
				if (result == null) result = caseFunction(linearIncreaseAndDecline);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.ABSOLUTE_SIN: {
				AbsoluteSin absoluteSin = (AbsoluteSin)theEObject;
				T result = caseAbsoluteSin(absoluteSin);
				if (result == null) result = caseSin(absoluteSin);
				if (result == null) result = caseSeasonal(absoluteSin);
				if (result == null) result = caseFunction(absoluteSin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.LINEAR_TREND: {
				LinearTrend linearTrend = (LinearTrend)theEObject;
				T result = caseLinearTrend(linearTrend);
				if (result == null) result = caseTrend(linearTrend);
				if (result == null) result = caseFunction(linearTrend);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.EXPONENTIAL_TREND: {
				ExponentialTrend exponentialTrend = (ExponentialTrend)theEObject;
				T result = caseExponentialTrend(exponentialTrend);
				if (result == null) result = caseTrend(exponentialTrend);
				if (result == null) result = caseFunction(exponentialTrend);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.LOGARITHMIC_TREND: {
				LogarithmicTrend logarithmicTrend = (LogarithmicTrend)theEObject;
				T result = caseLogarithmicTrend(logarithmicTrend);
				if (result == null) result = caseTrend(logarithmicTrend);
				if (result == null) result = caseFunction(logarithmicTrend);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.SIN_TREND: {
				SinTrend sinTrend = (SinTrend)theEObject;
				T result = caseSinTrend(sinTrend);
				if (result == null) result = caseTrend(sinTrend);
				if (result == null) result = caseFunction(sinTrend);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.REFERENCE_CLOCK_OBJECT: {
				ReferenceClockObject referenceClockObject = (ReferenceClockObject)theEObject;
				T result = caseReferenceClockObject(referenceClockObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.ARRIVAL_RATES_FROM_FILE: {
				ArrivalRatesFromFile arrivalRatesFromFile = (ArrivalRatesFromFile)theEObject;
				T result = caseArrivalRatesFromFile(arrivalRatesFromFile);
				if (result == null) result = caseFunction(arrivalRatesFromFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.ABSOLUTE_VALUE_FUNCTION: {
				AbsoluteValueFunction absoluteValueFunction = (AbsoluteValueFunction)theEObject;
				T result = caseAbsoluteValueFunction(absoluteValueFunction);
				if (result == null) result = caseUnivariateFunction(absoluteValueFunction);
				if (result == null) result = caseFunction(absoluteValueFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.UNIVARIATE_FUNCTION: {
				UnivariateFunction univariateFunction = (UnivariateFunction)theEObject;
				T result = caseUnivariateFunction(univariateFunction);
				if (result == null) result = caseFunction(univariateFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.POLYNOMIAL: {
				Polynomial polynomial = (Polynomial)theEObject;
				T result = casePolynomial(polynomial);
				if (result == null) result = caseFunction(polynomial);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DlimPackage.POLYNOMIAL_FACTOR: {
				PolynomialFactor polynomialFactor = (PolynomialFactor)theEObject;
				T result = casePolynomialFactor(polynomialFactor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequence(Sequence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Combinator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Combinator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCombinator(Combinator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Dependent Function Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Dependent Function Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeDependentFunctionContainer(TimeDependentFunctionContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Seasonal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Seasonal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSeasonal(Seasonal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Noise</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Noise</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoise(Noise object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uniform Noise</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uniform Noise</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUniformNoise(UniformNoise object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Normal Noise</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Normal Noise</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNormalNoise(NormalNoise object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Burst</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Burst</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBurst(Burst object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trend</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trend</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrend(Trend object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstant(Constant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSin(Sin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Increase And Decline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Increase And Decline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialIncreaseAndDecline(ExponentialIncreaseAndDecline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Increase Logarithmic Decline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Increase Logarithmic Decline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialIncreaseLogarithmicDecline(ExponentialIncreaseLogarithmicDecline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Increase And Decline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Increase And Decline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearIncreaseAndDecline(LinearIncreaseAndDecline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Absolute Sin</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Absolute Sin</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbsoluteSin(AbsoluteSin object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Trend</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Trend</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinearTrend(LinearTrend object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Trend</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Trend</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialTrend(ExponentialTrend object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Logarithmic Trend</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Logarithmic Trend</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogarithmicTrend(LogarithmicTrend object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sin Trend</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sin Trend</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSinTrend(SinTrend object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Clock Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Clock Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceClockObject(ReferenceClockObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arrival Rates From File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arrival Rates From File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrivalRatesFromFile(ArrivalRatesFromFile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Absolute Value Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Absolute Value Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbsoluteValueFunction(AbsoluteValueFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Univariate Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Univariate Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnivariateFunction(UnivariateFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Polynomial</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Polynomial</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePolynomial(Polynomial object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Polynomial Factor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Polynomial Factor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePolynomialFactor(PolynomialFactor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //DlimSwitch
