/**
 */
package tools.descartes.dlim.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

import tools.descartes.dlim.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see tools.descartes.dlim.DlimPackage
 * @generated
 */
public class DlimValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DlimValidator INSTANCE = new DlimValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "tools.descartes.dlim";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Duration Defined' of 'Sequence'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int SEQUENCE__DURATION_DEFINED = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Duration Greater Zero' of 'Time Dependent Function Container'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION_GREATER_ZERO = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Reference Clock In Tree Node' of 'Time Dependent Function Container'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int TIME_DEPENDENT_FUNCTION_CONTAINER__REFERENCE_CLOCK_IN_TREE_NODE = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Peak Time Greater Zero' of 'Burst'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int BURST__PEAK_TIME_GREATER_ZERO = 4;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 4;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DlimValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return DlimPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case DlimPackage.SEQUENCE:
				return validateSequence((Sequence)value, diagnostics, context);
			case DlimPackage.COMBINATOR:
				return validateCombinator((Combinator)value, diagnostics, context);
			case DlimPackage.TIME_DEPENDENT_FUNCTION_CONTAINER:
				return validateTimeDependentFunctionContainer((TimeDependentFunctionContainer)value, diagnostics, context);
			case DlimPackage.FUNCTION:
				return validateFunction((Function)value, diagnostics, context);
			case DlimPackage.SEASONAL:
				return validateSeasonal((Seasonal)value, diagnostics, context);
			case DlimPackage.NOISE:
				return validateNoise((Noise)value, diagnostics, context);
			case DlimPackage.UNIFORM_NOISE:
				return validateUniformNoise((UniformNoise)value, diagnostics, context);
			case DlimPackage.NORMAL_NOISE:
				return validateNormalNoise((NormalNoise)value, diagnostics, context);
			case DlimPackage.BURST:
				return validateBurst((Burst)value, diagnostics, context);
			case DlimPackage.TREND:
				return validateTrend((Trend)value, diagnostics, context);
			case DlimPackage.CONSTANT:
				return validateConstant((Constant)value, diagnostics, context);
			case DlimPackage.SIN:
				return validateSin((Sin)value, diagnostics, context);
			case DlimPackage.EXPONENTIAL_INCREASE_AND_DECLINE:
				return validateExponentialIncreaseAndDecline((ExponentialIncreaseAndDecline)value, diagnostics, context);
			case DlimPackage.EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE:
				return validateExponentialIncreaseLogarithmicDecline((ExponentialIncreaseLogarithmicDecline)value, diagnostics, context);
			case DlimPackage.LINEAR_INCREASE_AND_DECLINE:
				return validateLinearIncreaseAndDecline((LinearIncreaseAndDecline)value, diagnostics, context);
			case DlimPackage.ABSOLUTE_SIN:
				return validateAbsoluteSin((AbsoluteSin)value, diagnostics, context);
			case DlimPackage.LINEAR_TREND:
				return validateLinearTrend((LinearTrend)value, diagnostics, context);
			case DlimPackage.EXPONENTIAL_TREND:
				return validateExponentialTrend((ExponentialTrend)value, diagnostics, context);
			case DlimPackage.LOGARITHMIC_TREND:
				return validateLogarithmicTrend((LogarithmicTrend)value, diagnostics, context);
			case DlimPackage.SIN_TREND:
				return validateSinTrend((SinTrend)value, diagnostics, context);
			case DlimPackage.REFERENCE_CLOCK_OBJECT:
				return validateReferenceClockObject((ReferenceClockObject)value, diagnostics, context);
			case DlimPackage.ARRIVAL_RATES_FROM_FILE:
				return validateArrivalRatesFromFile((ArrivalRatesFromFile)value, diagnostics, context);
			case DlimPackage.ABSOLUTE_VALUE_FUNCTION:
				return validateAbsoluteValueFunction((AbsoluteValueFunction)value, diagnostics, context);
			case DlimPackage.UNIVARIATE_FUNCTION:
				return validateUnivariateFunction((UnivariateFunction)value, diagnostics, context);
			case DlimPackage.POLYNOMIAL:
				return validatePolynomial((Polynomial)value, diagnostics, context);
			case DlimPackage.POLYNOMIAL_FACTOR:
				return validatePolynomialFactor((PolynomialFactor)value, diagnostics, context);
			case DlimPackage.CLOCK_TYPE:
				return validateClockType((ClockType)value, diagnostics, context);
			case DlimPackage.OPERATOR:
				return validateOperator((Operator)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSequence(Sequence sequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(sequence, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(sequence, diagnostics, context);
		if (result || diagnostics != null) result &= validateSequence_durationDefined(sequence, diagnostics, context);
		return result;
	}

	/**
	 * Validates the durationDefined constraint of '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSequence_durationDefined(Sequence sequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return sequence.durationDefined(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCombinator(Combinator combinator, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(combinator, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeDependentFunctionContainer(TimeDependentFunctionContainer timeDependentFunctionContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(timeDependentFunctionContainer, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateTimeDependentFunctionContainer_durationGreaterZero(timeDependentFunctionContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateTimeDependentFunctionContainer_referenceClockInTreeNode(timeDependentFunctionContainer, diagnostics, context);
		return result;
	}

	/**
	 * Validates the durationGreaterZero constraint of '<em>Time Dependent Function Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeDependentFunctionContainer_durationGreaterZero(TimeDependentFunctionContainer timeDependentFunctionContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return timeDependentFunctionContainer.durationGreaterZero(diagnostics, context);
	}

	/**
	 * Validates the referenceClockInTreeNode constraint of '<em>Time Dependent Function Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeDependentFunctionContainer_referenceClockInTreeNode(TimeDependentFunctionContainer timeDependentFunctionContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return timeDependentFunctionContainer.referenceClockInTreeNode(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFunction(Function function, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(function, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSeasonal(Seasonal seasonal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(seasonal, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNoise(Noise noise, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(noise, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUniformNoise(UniformNoise uniformNoise, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(uniformNoise, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNormalNoise(NormalNoise normalNoise, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(normalNoise, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBurst(Burst burst, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(burst, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(burst, diagnostics, context);
		if (result || diagnostics != null) result &= validateBurst_peakTimeGreaterZero(burst, diagnostics, context);
		return result;
	}

	/**
	 * Validates the peakTimeGreaterZero constraint of '<em>Burst</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBurst_peakTimeGreaterZero(Burst burst, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return burst.peakTimeGreaterZero(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrend(Trend trend, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(trend, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConstant(Constant constant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(constant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSin(Sin sin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(sin, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExponentialIncreaseAndDecline(ExponentialIncreaseAndDecline exponentialIncreaseAndDecline, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(exponentialIncreaseAndDecline, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(exponentialIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validateBurst_peakTimeGreaterZero(exponentialIncreaseAndDecline, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExponentialIncreaseLogarithmicDecline(ExponentialIncreaseLogarithmicDecline exponentialIncreaseLogarithmicDecline, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(exponentialIncreaseLogarithmicDecline, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validateBurst_peakTimeGreaterZero(exponentialIncreaseLogarithmicDecline, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLinearIncreaseAndDecline(LinearIncreaseAndDecline linearIncreaseAndDecline, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(linearIncreaseAndDecline, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(linearIncreaseAndDecline, diagnostics, context);
		if (result || diagnostics != null) result &= validateBurst_peakTimeGreaterZero(linearIncreaseAndDecline, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbsoluteSin(AbsoluteSin absoluteSin, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(absoluteSin, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLinearTrend(LinearTrend linearTrend, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(linearTrend, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExponentialTrend(ExponentialTrend exponentialTrend, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(exponentialTrend, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLogarithmicTrend(LogarithmicTrend logarithmicTrend, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(logarithmicTrend, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSinTrend(SinTrend sinTrend, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(sinTrend, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateReferenceClockObject(ReferenceClockObject referenceClockObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(referenceClockObject, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArrivalRatesFromFile(ArrivalRatesFromFile arrivalRatesFromFile, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(arrivalRatesFromFile, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbsoluteValueFunction(AbsoluteValueFunction absoluteValueFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(absoluteValueFunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnivariateFunction(UnivariateFunction univariateFunction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(univariateFunction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePolynomial(Polynomial polynomial, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(polynomial, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePolynomialFactor(PolynomialFactor polynomialFactor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(polynomialFactor, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClockType(ClockType clockType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperator(Operator operator, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //DlimValidator
