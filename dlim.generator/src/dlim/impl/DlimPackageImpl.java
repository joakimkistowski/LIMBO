/**
 */
package dlim.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import dlim.AbsoluteSin;
import dlim.AbsoluteValueFunction;
import dlim.ArrivalRatesFromFile;
import dlim.Burst;
import dlim.ClockType;
import dlim.Combinator;
import dlim.Constant;
import dlim.DlimFactory;
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
import dlim.Operator;
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
import dlim.util.DlimValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DlimPackageImpl extends EPackageImpl implements DlimPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass combinatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeDependentFunctionContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass seasonalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noiseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniformNoiseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass normalNoiseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass burstEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trendEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialIncreaseAndDeclineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialIncreaseLogarithmicDeclineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearIncreaseAndDeclineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass absoluteSinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearTrendEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialTrendEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logarithmicTrendEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sinTrendEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceClockObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrivalRatesFromFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass absoluteValueFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass univariateFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polynomialEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass polynomialFactorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum clockTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum operatorEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see dlim.DlimPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DlimPackageImpl() {
		super(eNS_URI, DlimFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DlimPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DlimPackage init() {
		if (isInited) return (DlimPackage)EPackage.Registry.INSTANCE.getEPackage(DlimPackage.eNS_URI);

		// Obtain or create and register package
		DlimPackageImpl theDlimPackage = (DlimPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DlimPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DlimPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDlimPackage.createPackageContents();

		// Initialize created meta-data
		theDlimPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theDlimPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return DlimValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theDlimPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DlimPackage.eNS_URI, theDlimPackage);
		return theDlimPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequence() {
		return sequenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_Name() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_TerminateAfterTime() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSequence_ReferenceClock() {
		return (EReference)sequenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSequence_SequenceFunctionContainers() {
		return (EReference)sequenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_TerminateAfterLoops() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_FirstIterationStart() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_FirstIterationEnd() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_LoopDuration() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequence_FinalDuration() {
		return (EAttribute)sequenceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getSequence__DurationDefined__DiagnosticChain_Map() {
		return sequenceEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCombinator() {
		return combinatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCombinator_Operator() {
		return (EAttribute)combinatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCombinator_Function() {
		return (EReference)combinatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeDependentFunctionContainer() {
		return timeDependentFunctionContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDependentFunctionContainer_Name() {
		return (EAttribute)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDependentFunctionContainer_Duration() {
		return (EAttribute)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDependentFunctionContainer_FirstIterationStart() {
		return (EAttribute)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDependentFunctionContainer_FirstIterationEnd() {
		return (EAttribute)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimeDependentFunctionContainer_Function() {
		return (EReference)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimeDependentFunctionContainer_PointOfReferenceClockObject() {
		return (EReference)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDependentFunctionContainer_PointOfReferenceClockType() {
		return (EAttribute)timeDependentFunctionContainerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTimeDependentFunctionContainer__DurationGreaterZero__DiagnosticChain_Map() {
		return timeDependentFunctionContainerEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTimeDependentFunctionContainer__ReferenceClockInTreeNode__DiagnosticChain_Map() {
		return timeDependentFunctionContainerEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunction_Combine() {
		return (EReference)functionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSeasonal() {
		return seasonalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNoise() {
		return noiseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUniformNoise() {
		return uniformNoiseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniformNoise_Min() {
		return (EAttribute)uniformNoiseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniformNoise_Max() {
		return (EAttribute)uniformNoiseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNormalNoise() {
		return normalNoiseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNormalNoise_Mean() {
		return (EAttribute)normalNoiseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNormalNoise_StandardDeviation() {
		return (EAttribute)normalNoiseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBurst() {
		return burstEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBurst_Peak() {
		return (EAttribute)burstEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBurst_Base() {
		return (EAttribute)burstEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBurst_PeakTime() {
		return (EAttribute)burstEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getBurst__PeakTimeGreaterZero__DiagnosticChain_Map() {
		return burstEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrend() {
		return trendEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrend_FunctionOutputAtStart() {
		return (EAttribute)trendEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrend_FunctionOutputAtEnd() {
		return (EAttribute)trendEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstant() {
		return constantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstant_Constant() {
		return (EAttribute)constantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSin() {
		return sinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSin_Min() {
		return (EAttribute)sinEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSin_Max() {
		return (EAttribute)sinEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSin_Period() {
		return (EAttribute)sinEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSin_Phase() {
		return (EAttribute)sinEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExponentialIncreaseAndDecline() {
		return exponentialIncreaseAndDeclineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExponentialIncreaseLogarithmicDecline() {
		return exponentialIncreaseLogarithmicDeclineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExponentialIncreaseLogarithmicDecline_LogarithmicOrder() {
		return (EAttribute)exponentialIncreaseLogarithmicDeclineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearIncreaseAndDecline() {
		return linearIncreaseAndDeclineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbsoluteSin() {
		return absoluteSinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearTrend() {
		return linearTrendEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExponentialTrend() {
		return exponentialTrendEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogarithmicTrend() {
		return logarithmicTrendEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLogarithmicTrend_Order() {
		return (EAttribute)logarithmicTrendEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSinTrend() {
		return sinTrendEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceClockObject() {
		return referenceClockObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceClockObject_Name() {
		return (EAttribute)referenceClockObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceClockObject_LoopTime() {
		return (EAttribute)referenceClockObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReferenceClockObject_SeqTime() {
		return (EAttribute)referenceClockObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrivalRatesFromFile() {
		return arrivalRatesFromFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrivalRatesFromFile_FilePath() {
		return (EAttribute)arrivalRatesFromFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getArrivalRatesFromFile__GetArrivalRate__double() {
		return arrivalRatesFromFileEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getArrivalRatesFromFile__ReadFile() {
		return arrivalRatesFromFileEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbsoluteValueFunction() {
		return absoluteValueFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnivariateFunction() {
		return univariateFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnivariateFunction_Function() {
		return (EReference)univariateFunctionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolynomial() {
		return polynomialEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPolynomial_Factors() {
		return (EReference)polynomialEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPolynomialFactor() {
		return polynomialFactorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPolynomialFactor_Factor() {
		return (EAttribute)polynomialFactorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPolynomialFactor_Offset() {
		return (EAttribute)polynomialFactorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getClockType() {
		return clockTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOperator() {
		return operatorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DlimFactory getDlimFactory() {
		return (DlimFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		sequenceEClass = createEClass(SEQUENCE);
		createEAttribute(sequenceEClass, SEQUENCE__NAME);
		createEAttribute(sequenceEClass, SEQUENCE__TERMINATE_AFTER_TIME);
		createEReference(sequenceEClass, SEQUENCE__REFERENCE_CLOCK);
		createEReference(sequenceEClass, SEQUENCE__SEQUENCE_FUNCTION_CONTAINERS);
		createEAttribute(sequenceEClass, SEQUENCE__TERMINATE_AFTER_LOOPS);
		createEAttribute(sequenceEClass, SEQUENCE__FIRST_ITERATION_START);
		createEAttribute(sequenceEClass, SEQUENCE__FIRST_ITERATION_END);
		createEAttribute(sequenceEClass, SEQUENCE__LOOP_DURATION);
		createEAttribute(sequenceEClass, SEQUENCE__FINAL_DURATION);
		createEOperation(sequenceEClass, SEQUENCE___DURATION_DEFINED__DIAGNOSTICCHAIN_MAP);

		combinatorEClass = createEClass(COMBINATOR);
		createEAttribute(combinatorEClass, COMBINATOR__OPERATOR);
		createEReference(combinatorEClass, COMBINATOR__FUNCTION);

		timeDependentFunctionContainerEClass = createEClass(TIME_DEPENDENT_FUNCTION_CONTAINER);
		createEAttribute(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__NAME);
		createEAttribute(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION);
		createEAttribute(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START);
		createEAttribute(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END);
		createEReference(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION);
		createEReference(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT);
		createEAttribute(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE);
		createEOperation(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER___DURATION_GREATER_ZERO__DIAGNOSTICCHAIN_MAP);
		createEOperation(timeDependentFunctionContainerEClass, TIME_DEPENDENT_FUNCTION_CONTAINER___REFERENCE_CLOCK_IN_TREE_NODE__DIAGNOSTICCHAIN_MAP);

		functionEClass = createEClass(FUNCTION);
		createEReference(functionEClass, FUNCTION__COMBINE);

		seasonalEClass = createEClass(SEASONAL);

		noiseEClass = createEClass(NOISE);

		uniformNoiseEClass = createEClass(UNIFORM_NOISE);
		createEAttribute(uniformNoiseEClass, UNIFORM_NOISE__MIN);
		createEAttribute(uniformNoiseEClass, UNIFORM_NOISE__MAX);

		normalNoiseEClass = createEClass(NORMAL_NOISE);
		createEAttribute(normalNoiseEClass, NORMAL_NOISE__MEAN);
		createEAttribute(normalNoiseEClass, NORMAL_NOISE__STANDARD_DEVIATION);

		burstEClass = createEClass(BURST);
		createEAttribute(burstEClass, BURST__PEAK);
		createEAttribute(burstEClass, BURST__BASE);
		createEAttribute(burstEClass, BURST__PEAK_TIME);
		createEOperation(burstEClass, BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP);

		trendEClass = createEClass(TREND);
		createEAttribute(trendEClass, TREND__FUNCTION_OUTPUT_AT_START);
		createEAttribute(trendEClass, TREND__FUNCTION_OUTPUT_AT_END);

		constantEClass = createEClass(CONSTANT);
		createEAttribute(constantEClass, CONSTANT__CONSTANT);

		sinEClass = createEClass(SIN);
		createEAttribute(sinEClass, SIN__MIN);
		createEAttribute(sinEClass, SIN__MAX);
		createEAttribute(sinEClass, SIN__PERIOD);
		createEAttribute(sinEClass, SIN__PHASE);

		exponentialIncreaseAndDeclineEClass = createEClass(EXPONENTIAL_INCREASE_AND_DECLINE);

		exponentialIncreaseLogarithmicDeclineEClass = createEClass(EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE);
		createEAttribute(exponentialIncreaseLogarithmicDeclineEClass, EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER);

		linearIncreaseAndDeclineEClass = createEClass(LINEAR_INCREASE_AND_DECLINE);

		absoluteSinEClass = createEClass(ABSOLUTE_SIN);

		linearTrendEClass = createEClass(LINEAR_TREND);

		exponentialTrendEClass = createEClass(EXPONENTIAL_TREND);

		logarithmicTrendEClass = createEClass(LOGARITHMIC_TREND);
		createEAttribute(logarithmicTrendEClass, LOGARITHMIC_TREND__ORDER);

		sinTrendEClass = createEClass(SIN_TREND);

		referenceClockObjectEClass = createEClass(REFERENCE_CLOCK_OBJECT);
		createEAttribute(referenceClockObjectEClass, REFERENCE_CLOCK_OBJECT__NAME);
		createEAttribute(referenceClockObjectEClass, REFERENCE_CLOCK_OBJECT__LOOP_TIME);
		createEAttribute(referenceClockObjectEClass, REFERENCE_CLOCK_OBJECT__SEQ_TIME);

		arrivalRatesFromFileEClass = createEClass(ARRIVAL_RATES_FROM_FILE);
		createEAttribute(arrivalRatesFromFileEClass, ARRIVAL_RATES_FROM_FILE__FILE_PATH);
		createEOperation(arrivalRatesFromFileEClass, ARRIVAL_RATES_FROM_FILE___GET_ARRIVAL_RATE__DOUBLE);
		createEOperation(arrivalRatesFromFileEClass, ARRIVAL_RATES_FROM_FILE___READ_FILE);

		absoluteValueFunctionEClass = createEClass(ABSOLUTE_VALUE_FUNCTION);

		univariateFunctionEClass = createEClass(UNIVARIATE_FUNCTION);
		createEReference(univariateFunctionEClass, UNIVARIATE_FUNCTION__FUNCTION);

		polynomialEClass = createEClass(POLYNOMIAL);
		createEReference(polynomialEClass, POLYNOMIAL__FACTORS);

		polynomialFactorEClass = createEClass(POLYNOMIAL_FACTOR);
		createEAttribute(polynomialFactorEClass, POLYNOMIAL_FACTOR__FACTOR);
		createEAttribute(polynomialFactorEClass, POLYNOMIAL_FACTOR__OFFSET);

		// Create enums
		clockTypeEEnum = createEEnum(CLOCK_TYPE);
		operatorEEnum = createEEnum(OPERATOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		sequenceEClass.getESuperTypes().add(this.getFunction());
		seasonalEClass.getESuperTypes().add(this.getFunction());
		noiseEClass.getESuperTypes().add(this.getFunction());
		uniformNoiseEClass.getESuperTypes().add(this.getNoise());
		normalNoiseEClass.getESuperTypes().add(this.getNoise());
		burstEClass.getESuperTypes().add(this.getFunction());
		trendEClass.getESuperTypes().add(this.getFunction());
		constantEClass.getESuperTypes().add(this.getSeasonal());
		sinEClass.getESuperTypes().add(this.getSeasonal());
		exponentialIncreaseAndDeclineEClass.getESuperTypes().add(this.getBurst());
		exponentialIncreaseLogarithmicDeclineEClass.getESuperTypes().add(this.getBurst());
		linearIncreaseAndDeclineEClass.getESuperTypes().add(this.getBurst());
		absoluteSinEClass.getESuperTypes().add(this.getSin());
		linearTrendEClass.getESuperTypes().add(this.getTrend());
		exponentialTrendEClass.getESuperTypes().add(this.getTrend());
		logarithmicTrendEClass.getESuperTypes().add(this.getTrend());
		sinTrendEClass.getESuperTypes().add(this.getTrend());
		arrivalRatesFromFileEClass.getESuperTypes().add(this.getFunction());
		absoluteValueFunctionEClass.getESuperTypes().add(this.getUnivariateFunction());
		univariateFunctionEClass.getESuperTypes().add(this.getFunction());
		polynomialEClass.getESuperTypes().add(this.getFunction());

		// Initialize classes, features, and operations; add parameters
		initEClass(sequenceEClass, Sequence.class, "Sequence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSequence_Name(), ecorePackage.getEString(), "name", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequence_TerminateAfterTime(), ecorePackage.getEDouble(), "terminateAfterTime", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSequence_ReferenceClock(), this.getReferenceClockObject(), null, "referenceClock", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSequence_SequenceFunctionContainers(), this.getTimeDependentFunctionContainer(), null, "sequenceFunctionContainers", null, 1, -1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequence_TerminateAfterLoops(), ecorePackage.getEInt(), "terminateAfterLoops", "-1", 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequence_FirstIterationStart(), ecorePackage.getEDouble(), "firstIterationStart", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequence_FirstIterationEnd(), ecorePackage.getEDouble(), "firstIterationEnd", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequence_LoopDuration(), ecorePackage.getEDouble(), "loopDuration", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequence_FinalDuration(), ecorePackage.getEDouble(), "finalDuration", null, 0, 1, Sequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getSequence__DurationDefined__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "durationDefined", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "chain", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(combinatorEClass, Combinator.class, "Combinator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCombinator_Operator(), this.getOperator(), "operator", "ADD", 0, 1, Combinator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCombinator_Function(), this.getFunction(), null, "function", null, 1, 1, Combinator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timeDependentFunctionContainerEClass, TimeDependentFunctionContainer.class, "TimeDependentFunctionContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimeDependentFunctionContainer_Name(), ecorePackage.getEString(), "name", null, 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeDependentFunctionContainer_Duration(), ecorePackage.getEDouble(), "duration", "1.0", 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeDependentFunctionContainer_FirstIterationStart(), ecorePackage.getEDouble(), "firstIterationStart", null, 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeDependentFunctionContainer_FirstIterationEnd(), ecorePackage.getEDouble(), "firstIterationEnd", null, 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTimeDependentFunctionContainer_Function(), this.getFunction(), null, "function", null, 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimeDependentFunctionContainer_PointOfReferenceClockObject(), this.getReferenceClockObject(), null, "pointOfReferenceClockObject", null, 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTimeDependentFunctionContainer_PointOfReferenceClockType(), this.getClockType(), "pointOfReferenceClockType", "ELEMCLOCK", 0, 1, TimeDependentFunctionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getTimeDependentFunctionContainer__DurationGreaterZero__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "durationGreaterZero", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "chain", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getTimeDependentFunctionContainer__ReferenceClockInTreeNode__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "referenceClockInTreeNode", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "chain", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(functionEClass, Function.class, "Function", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFunction_Combine(), this.getCombinator(), null, "combine", null, 0, -1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(seasonalEClass, Seasonal.class, "Seasonal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(noiseEClass, Noise.class, "Noise", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uniformNoiseEClass, UniformNoise.class, "UniformNoise", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUniformNoise_Min(), ecorePackage.getEDouble(), "min", "1.0", 0, 1, UniformNoise.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUniformNoise_Max(), ecorePackage.getEDouble(), "max", "1.0", 0, 1, UniformNoise.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(normalNoiseEClass, NormalNoise.class, "NormalNoise", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNormalNoise_Mean(), ecorePackage.getEDouble(), "mean", "1.0", 0, 1, NormalNoise.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNormalNoise_StandardDeviation(), ecorePackage.getEDouble(), "standardDeviation", "1.0", 0, 1, NormalNoise.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(burstEClass, Burst.class, "Burst", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBurst_Peak(), ecorePackage.getEDouble(), "peak", "2.0", 0, 1, Burst.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBurst_Base(), ecorePackage.getEDouble(), "base", "0.0", 0, 1, Burst.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBurst_PeakTime(), ecorePackage.getEDouble(), "peakTime", "1.0", 0, 1, Burst.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getBurst__PeakTimeGreaterZero__DiagnosticChain_Map(), ecorePackage.getEBoolean(), "peakTimeGreaterZero", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "chain", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(trendEClass, Trend.class, "Trend", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTrend_FunctionOutputAtStart(), ecorePackage.getEDouble(), "functionOutputAtStart", "0.0", 0, 1, Trend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrend_FunctionOutputAtEnd(), ecorePackage.getEDouble(), "functionOutputAtEnd", "1.0", 0, 1, Trend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstant_Constant(), ecorePackage.getEDouble(), "constant", null, 0, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sinEClass, Sin.class, "Sin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSin_Min(), ecorePackage.getEDouble(), "min", "1.0", 0, 1, Sin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSin_Max(), ecorePackage.getEDouble(), "max", "1.0", 0, 1, Sin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSin_Period(), ecorePackage.getEDouble(), "period", "10.0", 0, 1, Sin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSin_Phase(), ecorePackage.getEDouble(), "phase", "0.0", 0, 1, Sin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(exponentialIncreaseAndDeclineEClass, ExponentialIncreaseAndDecline.class, "ExponentialIncreaseAndDecline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exponentialIncreaseLogarithmicDeclineEClass, ExponentialIncreaseLogarithmicDecline.class, "ExponentialIncreaseLogarithmicDecline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExponentialIncreaseLogarithmicDecline_LogarithmicOrder(), ecorePackage.getEDouble(), "logarithmicOrder", "4.0", 0, 1, ExponentialIncreaseLogarithmicDecline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linearIncreaseAndDeclineEClass, LinearIncreaseAndDecline.class, "LinearIncreaseAndDecline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(absoluteSinEClass, AbsoluteSin.class, "AbsoluteSin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linearTrendEClass, LinearTrend.class, "LinearTrend", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exponentialTrendEClass, ExponentialTrend.class, "ExponentialTrend", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(logarithmicTrendEClass, LogarithmicTrend.class, "LogarithmicTrend", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLogarithmicTrend_Order(), ecorePackage.getEDouble(), "order", "4.0", 0, 1, LogarithmicTrend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sinTrendEClass, SinTrend.class, "SinTrend", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(referenceClockObjectEClass, ReferenceClockObject.class, "ReferenceClockObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReferenceClockObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, ReferenceClockObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceClockObject_LoopTime(), ecorePackage.getEDouble(), "loopTime", "0.0", 0, 1, ReferenceClockObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getReferenceClockObject_SeqTime(), ecorePackage.getEDouble(), "seqTime", "0.0", 0, 1, ReferenceClockObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(arrivalRatesFromFileEClass, ArrivalRatesFromFile.class, "ArrivalRatesFromFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrivalRatesFromFile_FilePath(), ecorePackage.getEString(), "filePath", "C:/arrivalRates/arrivalRateFile.txt", 0, 1, ArrivalRatesFromFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = initEOperation(getArrivalRatesFromFile__GetArrivalRate__double(), ecorePackage.getEDouble(), "getArrivalRate", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDouble(), "x", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getArrivalRatesFromFile__ReadFile(), null, "readFile", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(absoluteValueFunctionEClass, AbsoluteValueFunction.class, "AbsoluteValueFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(univariateFunctionEClass, UnivariateFunction.class, "UnivariateFunction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnivariateFunction_Function(), this.getFunction(), null, "function", null, 1, 1, UnivariateFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(polynomialEClass, Polynomial.class, "Polynomial", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPolynomial_Factors(), this.getPolynomialFactor(), null, "factors", null, 1, -1, Polynomial.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(polynomialFactorEClass, PolynomialFactor.class, "PolynomialFactor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPolynomialFactor_Factor(), ecorePackage.getEDouble(), "factor", "0.0", 0, 1, PolynomialFactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getPolynomialFactor_Offset(), ecorePackage.getEDouble(), "offset", "0.0", 0, 1, PolynomialFactor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(clockTypeEEnum, ClockType.class, "ClockType");
		addEEnumLiteral(clockTypeEEnum, ClockType.ROOT_CLOCK);
		addEEnumLiteral(clockTypeEEnum, ClockType.ELEMENT_CLOCK);
		addEEnumLiteral(clockTypeEEnum, ClockType.SEQUENCE_CLOCK);
		addEEnumLiteral(clockTypeEEnum, ClockType.LOOP_CLOCK);

		initEEnum(operatorEEnum, Operator.class, "Operator");
		addEEnumLiteral(operatorEEnum, Operator.ADD);
		addEEnumLiteral(operatorEEnum, Operator.SUBTRACT);
		addEEnumLiteral(operatorEEnum, Operator.MULT);

		// Create resource
		createResource(eNS_URI);
	}

} //DlimPackageImpl
