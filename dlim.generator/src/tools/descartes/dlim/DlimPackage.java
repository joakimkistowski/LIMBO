/**
 */
package tools.descartes.dlim;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tools.descartes.dlim.DlimFactory
 * @model kind="package"
 * @generated
 */
public interface DlimPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dlim";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://sdq.ipd.uka.de/dlimm/0.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dlim";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DlimPackage eINSTANCE = tools.descartes.dlim.impl.DlimPackageImpl.init();

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.FunctionImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 3;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__COMBINE = 0;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.SequenceImpl <em>Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.SequenceImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSequence()
	 * @generated
	 */
	int SEQUENCE = 0;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__COMBINE = FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__NAME = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Terminate After Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__TERMINATE_AFTER_TIME = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reference Clock</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__REFERENCE_CLOCK = FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sequence Function Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__SEQUENCE_FUNCTION_CONTAINERS = FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Terminate After Loops</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__TERMINATE_AFTER_LOOPS = FUNCTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>First Iteration Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__FIRST_ITERATION_START = FUNCTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>First Iteration End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__FIRST_ITERATION_END = FUNCTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Loop Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__LOOP_DURATION = FUNCTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Final Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__FINAL_DURATION = FUNCTION_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 9;

	/**
	 * The operation id for the '<em>Duration Defined</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE___DURATION_DEFINED__DIAGNOSTICCHAIN_MAP = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.CombinatorImpl <em>Combinator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.CombinatorImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getCombinator()
	 * @generated
	 */
	int COMBINATOR = 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINATOR__OPERATOR = 0;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINATOR__FUNCTION = 1;

	/**
	 * The number of structural features of the '<em>Combinator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINATOR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Combinator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.TimeDependentFunctionContainerImpl <em>Time Dependent Function Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.TimeDependentFunctionContainerImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getTimeDependentFunctionContainer()
	 * @generated
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION = 1;

	/**
	 * The feature id for the '<em><b>First Iteration Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START = 2;

	/**
	 * The feature id for the '<em><b>First Iteration End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END = 3;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION = 4;

	/**
	 * The feature id for the '<em><b>Point Of Reference Clock Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT = 5;

	/**
	 * The feature id for the '<em><b>Point Of Reference Clock Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE = 6;

	/**
	 * The number of structural features of the '<em>Time Dependent Function Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER_FEATURE_COUNT = 7;

	/**
	 * The operation id for the '<em>Duration Greater Zero</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER___DURATION_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = 0;

	/**
	 * The operation id for the '<em>Reference Clock In Tree Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER___REFERENCE_CLOCK_IN_TREE_NODE__DIAGNOSTICCHAIN_MAP = 1;

	/**
	 * The number of operations of the '<em>Time Dependent Function Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DEPENDENT_FUNCTION_CONTAINER_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.SeasonalImpl <em>Seasonal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.SeasonalImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSeasonal()
	 * @generated
	 */
	int SEASONAL = 4;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEASONAL__COMBINE = FUNCTION__COMBINE;

	/**
	 * The number of structural features of the '<em>Seasonal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEASONAL_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Seasonal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEASONAL_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.NoiseImpl <em>Noise</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.NoiseImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getNoise()
	 * @generated
	 */
	int NOISE = 5;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOISE__COMBINE = FUNCTION__COMBINE;

	/**
	 * The number of structural features of the '<em>Noise</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOISE_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Noise</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOISE_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.UniformNoiseImpl <em>Uniform Noise</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.UniformNoiseImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getUniformNoise()
	 * @generated
	 */
	int UNIFORM_NOISE = 6;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_NOISE__COMBINE = NOISE__COMBINE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_NOISE__MIN = NOISE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_NOISE__MAX = NOISE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Uniform Noise</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_NOISE_FEATURE_COUNT = NOISE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Uniform Noise</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_NOISE_OPERATION_COUNT = NOISE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.NormalNoiseImpl <em>Normal Noise</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.NormalNoiseImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getNormalNoise()
	 * @generated
	 */
	int NORMAL_NOISE = 7;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_NOISE__COMBINE = NOISE__COMBINE;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_NOISE__MEAN = NOISE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Standard Deviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_NOISE__STANDARD_DEVIATION = NOISE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Normal Noise</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_NOISE_FEATURE_COUNT = NOISE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Normal Noise</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_NOISE_OPERATION_COUNT = NOISE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.BurstImpl <em>Burst</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.BurstImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getBurst()
	 * @generated
	 */
	int BURST = 8;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST__COMBINE = FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>Peak</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST__PEAK = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST__BASE = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Peak Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST__PEAK_TIME = FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Burst</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Peak Time Greater Zero</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Burst</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BURST_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.TrendImpl <em>Trend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.TrendImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getTrend()
	 * @generated
	 */
	int TREND = 9;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREND__COMBINE = FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>Function Output At Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREND__FUNCTION_OUTPUT_AT_START = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function Output At End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREND__FUNCTION_OUTPUT_AT_END = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREND_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREND_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.ConstantImpl <em>Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.ConstantImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getConstant()
	 * @generated
	 */
	int CONSTANT = 10;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__COMBINE = SEASONAL__COMBINE;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT__CONSTANT = SEASONAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_FEATURE_COUNT = SEASONAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_OPERATION_COUNT = SEASONAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.SinImpl <em>Sin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.SinImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSin()
	 * @generated
	 */
	int SIN = 11;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN__COMBINE = SEASONAL__COMBINE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN__MIN = SEASONAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN__MAX = SEASONAL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN__PERIOD = SEASONAL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN__PHASE = SEASONAL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Sin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_FEATURE_COUNT = SEASONAL_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Sin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_OPERATION_COUNT = SEASONAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.ExponentialIncreaseAndDeclineImpl <em>Exponential Increase And Decline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.ExponentialIncreaseAndDeclineImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getExponentialIncreaseAndDecline()
	 * @generated
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE = 12;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE__COMBINE = BURST__COMBINE;

	/**
	 * The feature id for the '<em><b>Peak</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE__PEAK = BURST__PEAK;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE__BASE = BURST__BASE;

	/**
	 * The feature id for the '<em><b>Peak Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE__PEAK_TIME = BURST__PEAK_TIME;

	/**
	 * The number of structural features of the '<em>Exponential Increase And Decline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE_FEATURE_COUNT = BURST_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Peak Time Greater Zero</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP;

	/**
	 * The number of operations of the '<em>Exponential Increase And Decline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_AND_DECLINE_OPERATION_COUNT = BURST_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.ExponentialIncreaseLogarithmicDeclineImpl <em>Exponential Increase Logarithmic Decline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.ExponentialIncreaseLogarithmicDeclineImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getExponentialIncreaseLogarithmicDecline()
	 * @generated
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE = 13;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__COMBINE = BURST__COMBINE;

	/**
	 * The feature id for the '<em><b>Peak</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__PEAK = BURST__PEAK;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__BASE = BURST__BASE;

	/**
	 * The feature id for the '<em><b>Peak Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__PEAK_TIME = BURST__PEAK_TIME;

	/**
	 * The feature id for the '<em><b>Logarithmic Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER = BURST_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exponential Increase Logarithmic Decline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE_FEATURE_COUNT = BURST_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Peak Time Greater Zero</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP;

	/**
	 * The number of operations of the '<em>Exponential Increase Logarithmic Decline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE_OPERATION_COUNT = BURST_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.LinearIncreaseAndDeclineImpl <em>Linear Increase And Decline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.LinearIncreaseAndDeclineImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getLinearIncreaseAndDecline()
	 * @generated
	 */
	int LINEAR_INCREASE_AND_DECLINE = 14;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE__COMBINE = BURST__COMBINE;

	/**
	 * The feature id for the '<em><b>Peak</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE__PEAK = BURST__PEAK;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE__BASE = BURST__BASE;

	/**
	 * The feature id for the '<em><b>Peak Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE__PEAK_TIME = BURST__PEAK_TIME;

	/**
	 * The number of structural features of the '<em>Linear Increase And Decline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE_FEATURE_COUNT = BURST_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Peak Time Greater Zero</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP;

	/**
	 * The number of operations of the '<em>Linear Increase And Decline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_INCREASE_AND_DECLINE_OPERATION_COUNT = BURST_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.AbsoluteSinImpl <em>Absolute Sin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.AbsoluteSinImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getAbsoluteSin()
	 * @generated
	 */
	int ABSOLUTE_SIN = 15;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN__COMBINE = SIN__COMBINE;

	/**
	 * The feature id for the '<em><b>Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN__MIN = SIN__MIN;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN__MAX = SIN__MAX;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN__PERIOD = SIN__PERIOD;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN__PHASE = SIN__PHASE;

	/**
	 * The number of structural features of the '<em>Absolute Sin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN_FEATURE_COUNT = SIN_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Absolute Sin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_SIN_OPERATION_COUNT = SIN_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.LinearTrendImpl <em>Linear Trend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.LinearTrendImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getLinearTrend()
	 * @generated
	 */
	int LINEAR_TREND = 16;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_TREND__COMBINE = TREND__COMBINE;

	/**
	 * The feature id for the '<em><b>Function Output At Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_TREND__FUNCTION_OUTPUT_AT_START = TREND__FUNCTION_OUTPUT_AT_START;

	/**
	 * The feature id for the '<em><b>Function Output At End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_TREND__FUNCTION_OUTPUT_AT_END = TREND__FUNCTION_OUTPUT_AT_END;

	/**
	 * The number of structural features of the '<em>Linear Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_TREND_FEATURE_COUNT = TREND_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Linear Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_TREND_OPERATION_COUNT = TREND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.ExponentialTrendImpl <em>Exponential Trend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.ExponentialTrendImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getExponentialTrend()
	 * @generated
	 */
	int EXPONENTIAL_TREND = 17;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_TREND__COMBINE = TREND__COMBINE;

	/**
	 * The feature id for the '<em><b>Function Output At Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_TREND__FUNCTION_OUTPUT_AT_START = TREND__FUNCTION_OUTPUT_AT_START;

	/**
	 * The feature id for the '<em><b>Function Output At End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_TREND__FUNCTION_OUTPUT_AT_END = TREND__FUNCTION_OUTPUT_AT_END;

	/**
	 * The number of structural features of the '<em>Exponential Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_TREND_FEATURE_COUNT = TREND_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Exponential Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_TREND_OPERATION_COUNT = TREND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.LogarithmicTrendImpl <em>Logarithmic Trend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.LogarithmicTrendImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getLogarithmicTrend()
	 * @generated
	 */
	int LOGARITHMIC_TREND = 18;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGARITHMIC_TREND__COMBINE = TREND__COMBINE;

	/**
	 * The feature id for the '<em><b>Function Output At Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGARITHMIC_TREND__FUNCTION_OUTPUT_AT_START = TREND__FUNCTION_OUTPUT_AT_START;

	/**
	 * The feature id for the '<em><b>Function Output At End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGARITHMIC_TREND__FUNCTION_OUTPUT_AT_END = TREND__FUNCTION_OUTPUT_AT_END;

	/**
	 * The feature id for the '<em><b>Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGARITHMIC_TREND__ORDER = TREND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Logarithmic Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGARITHMIC_TREND_FEATURE_COUNT = TREND_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Logarithmic Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGARITHMIC_TREND_OPERATION_COUNT = TREND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.SinTrendImpl <em>Sin Trend</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.SinTrendImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSinTrend()
	 * @generated
	 */
	int SIN_TREND = 19;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_TREND__COMBINE = TREND__COMBINE;

	/**
	 * The feature id for the '<em><b>Function Output At Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_TREND__FUNCTION_OUTPUT_AT_START = TREND__FUNCTION_OUTPUT_AT_START;

	/**
	 * The feature id for the '<em><b>Function Output At End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_TREND__FUNCTION_OUTPUT_AT_END = TREND__FUNCTION_OUTPUT_AT_END;

	/**
	 * The number of structural features of the '<em>Sin Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_TREND_FEATURE_COUNT = TREND_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Sin Trend</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIN_TREND_OPERATION_COUNT = TREND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.ReferenceClockObjectImpl <em>Reference Clock Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.ReferenceClockObjectImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getReferenceClockObject()
	 * @generated
	 */
	int REFERENCE_CLOCK_OBJECT = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLOCK_OBJECT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Loop Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLOCK_OBJECT__LOOP_TIME = 1;

	/**
	 * The feature id for the '<em><b>Seq Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLOCK_OBJECT__SEQ_TIME = 2;

	/**
	 * The number of structural features of the '<em>Reference Clock Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLOCK_OBJECT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Reference Clock Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CLOCK_OBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.ArrivalRatesFromFileImpl <em>Arrival Rates From File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.ArrivalRatesFromFileImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getArrivalRatesFromFile()
	 * @generated
	 */
	int ARRIVAL_RATES_FROM_FILE = 21;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRIVAL_RATES_FROM_FILE__COMBINE = FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>File Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRIVAL_RATES_FROM_FILE__FILE_PATH = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Arrival Rates From File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRIVAL_RATES_FROM_FILE_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Arrival Rate</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRIVAL_RATES_FROM_FILE___GET_ARRIVAL_RATE__DOUBLE = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Read File</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRIVAL_RATES_FROM_FILE___READ_FILE = FUNCTION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>Arrival Rates From File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRIVAL_RATES_FROM_FILE_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.UnivariateFunctionImpl <em>Univariate Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.UnivariateFunctionImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getUnivariateFunction()
	 * @generated
	 */
	int UNIVARIATE_FUNCTION = 23;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVARIATE_FUNCTION__COMBINE = FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVARIATE_FUNCTION__FUNCTION = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Univariate Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVARIATE_FUNCTION_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Univariate Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIVARIATE_FUNCTION_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.AbsoluteValueFunctionImpl <em>Absolute Value Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.AbsoluteValueFunctionImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getAbsoluteValueFunction()
	 * @generated
	 */
	int ABSOLUTE_VALUE_FUNCTION = 22;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_VALUE_FUNCTION__COMBINE = UNIVARIATE_FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_VALUE_FUNCTION__FUNCTION = UNIVARIATE_FUNCTION__FUNCTION;

	/**
	 * The number of structural features of the '<em>Absolute Value Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_VALUE_FUNCTION_FEATURE_COUNT = UNIVARIATE_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Absolute Value Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSOLUTE_VALUE_FUNCTION_OPERATION_COUNT = UNIVARIATE_FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.PolynomialImpl <em>Polynomial</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.PolynomialImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getPolynomial()
	 * @generated
	 */
	int POLYNOMIAL = 24;

	/**
	 * The feature id for the '<em><b>Combine</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL__COMBINE = FUNCTION__COMBINE;

	/**
	 * The feature id for the '<em><b>Factors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL__FACTORS = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Polynomial</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Polynomial</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL_OPERATION_COUNT = FUNCTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.impl.PolynomialFactorImpl <em>Polynomial Factor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.impl.PolynomialFactorImpl
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getPolynomialFactor()
	 * @generated
	 */
	int POLYNOMIAL_FACTOR = 25;

	/**
	 * The feature id for the '<em><b>Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL_FACTOR__FACTOR = 0;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL_FACTOR__OFFSET = 1;

	/**
	 * The number of structural features of the '<em>Polynomial Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL_FACTOR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Polynomial Factor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLYNOMIAL_FACTOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.ClockType <em>Clock Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.ClockType
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getClockType()
	 * @generated
	 */
	int CLOCK_TYPE = 26;

	/**
	 * The meta object id for the '{@link tools.descartes.dlim.Operator <em>Operator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tools.descartes.dlim.Operator
	 * @see tools.descartes.dlim.impl.DlimPackageImpl#getOperator()
	 * @generated
	 */
	int OPERATOR = 27;


	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Sequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence</em>'.
	 * @see tools.descartes.dlim.Sequence
	 * @generated
	 */
	EClass getSequence();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see tools.descartes.dlim.Sequence#getName()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_Name();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getTerminateAfterTime <em>Terminate After Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminate After Time</em>'.
	 * @see tools.descartes.dlim.Sequence#getTerminateAfterTime()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_TerminateAfterTime();

	/**
	 * Returns the meta object for the containment reference '{@link tools.descartes.dlim.Sequence#getReferenceClock <em>Reference Clock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reference Clock</em>'.
	 * @see tools.descartes.dlim.Sequence#getReferenceClock()
	 * @see #getSequence()
	 * @generated
	 */
	EReference getSequence_ReferenceClock();

	/**
	 * Returns the meta object for the containment reference list '{@link tools.descartes.dlim.Sequence#getSequenceFunctionContainers <em>Sequence Function Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sequence Function Containers</em>'.
	 * @see tools.descartes.dlim.Sequence#getSequenceFunctionContainers()
	 * @see #getSequence()
	 * @generated
	 */
	EReference getSequence_SequenceFunctionContainers();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getTerminateAfterLoops <em>Terminate After Loops</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminate After Loops</em>'.
	 * @see tools.descartes.dlim.Sequence#getTerminateAfterLoops()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_TerminateAfterLoops();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getFirstIterationStart <em>First Iteration Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Iteration Start</em>'.
	 * @see tools.descartes.dlim.Sequence#getFirstIterationStart()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_FirstIterationStart();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getFirstIterationEnd <em>First Iteration End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Iteration End</em>'.
	 * @see tools.descartes.dlim.Sequence#getFirstIterationEnd()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_FirstIterationEnd();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getLoopDuration <em>Loop Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Duration</em>'.
	 * @see tools.descartes.dlim.Sequence#getLoopDuration()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_LoopDuration();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sequence#getFinalDuration <em>Final Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final Duration</em>'.
	 * @see tools.descartes.dlim.Sequence#getFinalDuration()
	 * @see #getSequence()
	 * @generated
	 */
	EAttribute getSequence_FinalDuration();

	/**
	 * Returns the meta object for the '{@link tools.descartes.dlim.Sequence#durationDefined(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Duration Defined</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Duration Defined</em>' operation.
	 * @see tools.descartes.dlim.Sequence#durationDefined(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getSequence__DurationDefined__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Combinator <em>Combinator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Combinator</em>'.
	 * @see tools.descartes.dlim.Combinator
	 * @generated
	 */
	EClass getCombinator();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Combinator#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see tools.descartes.dlim.Combinator#getOperator()
	 * @see #getCombinator()
	 * @generated
	 */
	EAttribute getCombinator_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link tools.descartes.dlim.Combinator#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see tools.descartes.dlim.Combinator#getFunction()
	 * @see #getCombinator()
	 * @generated
	 */
	EReference getCombinator_Function();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.TimeDependentFunctionContainer <em>Time Dependent Function Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Dependent Function Container</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer
	 * @generated
	 */
	EClass getTimeDependentFunctionContainer();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getName()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EAttribute getTimeDependentFunctionContainer_Name();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getDuration()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EAttribute getTimeDependentFunctionContainer_Duration();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getFirstIterationStart <em>First Iteration Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Iteration Start</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getFirstIterationStart()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EAttribute getTimeDependentFunctionContainer_FirstIterationStart();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getFirstIterationEnd <em>First Iteration End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Iteration End</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getFirstIterationEnd()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EAttribute getTimeDependentFunctionContainer_FirstIterationEnd();

	/**
	 * Returns the meta object for the containment reference '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getFunction()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EReference getTimeDependentFunctionContainer_Function();

	/**
	 * Returns the meta object for the reference '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Point Of Reference Clock Object</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getPointOfReferenceClockObject()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EReference getTimeDependentFunctionContainer_PointOfReferenceClockObject();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.TimeDependentFunctionContainer#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Point Of Reference Clock Type</em>'.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#getPointOfReferenceClockType()
	 * @see #getTimeDependentFunctionContainer()
	 * @generated
	 */
	EAttribute getTimeDependentFunctionContainer_PointOfReferenceClockType();

	/**
	 * Returns the meta object for the '{@link tools.descartes.dlim.TimeDependentFunctionContainer#durationGreaterZero(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Duration Greater Zero</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Duration Greater Zero</em>' operation.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#durationGreaterZero(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getTimeDependentFunctionContainer__DurationGreaterZero__DiagnosticChain_Map();

	/**
	 * Returns the meta object for the '{@link tools.descartes.dlim.TimeDependentFunctionContainer#referenceClockInTreeNode(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Reference Clock In Tree Node</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reference Clock In Tree Node</em>' operation.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer#referenceClockInTreeNode(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getTimeDependentFunctionContainer__ReferenceClockInTreeNode__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see tools.descartes.dlim.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for the containment reference list '{@link tools.descartes.dlim.Function#getCombine <em>Combine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Combine</em>'.
	 * @see tools.descartes.dlim.Function#getCombine()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_Combine();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Seasonal <em>Seasonal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Seasonal</em>'.
	 * @see tools.descartes.dlim.Seasonal
	 * @generated
	 */
	EClass getSeasonal();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Noise <em>Noise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Noise</em>'.
	 * @see tools.descartes.dlim.Noise
	 * @generated
	 */
	EClass getNoise();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.UniformNoise <em>Uniform Noise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uniform Noise</em>'.
	 * @see tools.descartes.dlim.UniformNoise
	 * @generated
	 */
	EClass getUniformNoise();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.UniformNoise#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see tools.descartes.dlim.UniformNoise#getMin()
	 * @see #getUniformNoise()
	 * @generated
	 */
	EAttribute getUniformNoise_Min();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.UniformNoise#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see tools.descartes.dlim.UniformNoise#getMax()
	 * @see #getUniformNoise()
	 * @generated
	 */
	EAttribute getUniformNoise_Max();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.NormalNoise <em>Normal Noise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normal Noise</em>'.
	 * @see tools.descartes.dlim.NormalNoise
	 * @generated
	 */
	EClass getNormalNoise();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.NormalNoise#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see tools.descartes.dlim.NormalNoise#getMean()
	 * @see #getNormalNoise()
	 * @generated
	 */
	EAttribute getNormalNoise_Mean();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.NormalNoise#getStandardDeviation <em>Standard Deviation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Deviation</em>'.
	 * @see tools.descartes.dlim.NormalNoise#getStandardDeviation()
	 * @see #getNormalNoise()
	 * @generated
	 */
	EAttribute getNormalNoise_StandardDeviation();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Burst <em>Burst</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Burst</em>'.
	 * @see tools.descartes.dlim.Burst
	 * @generated
	 */
	EClass getBurst();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Burst#getPeak <em>Peak</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak</em>'.
	 * @see tools.descartes.dlim.Burst#getPeak()
	 * @see #getBurst()
	 * @generated
	 */
	EAttribute getBurst_Peak();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Burst#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see tools.descartes.dlim.Burst#getBase()
	 * @see #getBurst()
	 * @generated
	 */
	EAttribute getBurst_Base();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Burst#getPeakTime <em>Peak Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Peak Time</em>'.
	 * @see tools.descartes.dlim.Burst#getPeakTime()
	 * @see #getBurst()
	 * @generated
	 */
	EAttribute getBurst_PeakTime();

	/**
	 * Returns the meta object for the '{@link tools.descartes.dlim.Burst#peakTimeGreaterZero(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Peak Time Greater Zero</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Peak Time Greater Zero</em>' operation.
	 * @see tools.descartes.dlim.Burst#peakTimeGreaterZero(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getBurst__PeakTimeGreaterZero__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Trend <em>Trend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trend</em>'.
	 * @see tools.descartes.dlim.Trend
	 * @generated
	 */
	EClass getTrend();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Trend#getFunctionOutputAtStart <em>Function Output At Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Output At Start</em>'.
	 * @see tools.descartes.dlim.Trend#getFunctionOutputAtStart()
	 * @see #getTrend()
	 * @generated
	 */
	EAttribute getTrend_FunctionOutputAtStart();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Trend#getFunctionOutputAtEnd <em>Function Output At End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Output At End</em>'.
	 * @see tools.descartes.dlim.Trend#getFunctionOutputAtEnd()
	 * @see #getTrend()
	 * @generated
	 */
	EAttribute getTrend_FunctionOutputAtEnd();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Constant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant</em>'.
	 * @see tools.descartes.dlim.Constant
	 * @generated
	 */
	EClass getConstant();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Constant#getConstant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant</em>'.
	 * @see tools.descartes.dlim.Constant#getConstant()
	 * @see #getConstant()
	 * @generated
	 */
	EAttribute getConstant_Constant();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Sin <em>Sin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sin</em>'.
	 * @see tools.descartes.dlim.Sin
	 * @generated
	 */
	EClass getSin();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sin#getMin <em>Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min</em>'.
	 * @see tools.descartes.dlim.Sin#getMin()
	 * @see #getSin()
	 * @generated
	 */
	EAttribute getSin_Min();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sin#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see tools.descartes.dlim.Sin#getMax()
	 * @see #getSin()
	 * @generated
	 */
	EAttribute getSin_Max();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sin#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see tools.descartes.dlim.Sin#getPeriod()
	 * @see #getSin()
	 * @generated
	 */
	EAttribute getSin_Period();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.Sin#getPhase <em>Phase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phase</em>'.
	 * @see tools.descartes.dlim.Sin#getPhase()
	 * @see #getSin()
	 * @generated
	 */
	EAttribute getSin_Phase();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.ExponentialIncreaseAndDecline <em>Exponential Increase And Decline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Increase And Decline</em>'.
	 * @see tools.descartes.dlim.ExponentialIncreaseAndDecline
	 * @generated
	 */
	EClass getExponentialIncreaseAndDecline();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline <em>Exponential Increase Logarithmic Decline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Increase Logarithmic Decline</em>'.
	 * @see tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline
	 * @generated
	 */
	EClass getExponentialIncreaseLogarithmicDecline();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline#getLogarithmicOrder <em>Logarithmic Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Logarithmic Order</em>'.
	 * @see tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline#getLogarithmicOrder()
	 * @see #getExponentialIncreaseLogarithmicDecline()
	 * @generated
	 */
	EAttribute getExponentialIncreaseLogarithmicDecline_LogarithmicOrder();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.LinearIncreaseAndDecline <em>Linear Increase And Decline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Increase And Decline</em>'.
	 * @see tools.descartes.dlim.LinearIncreaseAndDecline
	 * @generated
	 */
	EClass getLinearIncreaseAndDecline();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.AbsoluteSin <em>Absolute Sin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Absolute Sin</em>'.
	 * @see tools.descartes.dlim.AbsoluteSin
	 * @generated
	 */
	EClass getAbsoluteSin();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.LinearTrend <em>Linear Trend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Trend</em>'.
	 * @see tools.descartes.dlim.LinearTrend
	 * @generated
	 */
	EClass getLinearTrend();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.ExponentialTrend <em>Exponential Trend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Trend</em>'.
	 * @see tools.descartes.dlim.ExponentialTrend
	 * @generated
	 */
	EClass getExponentialTrend();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.LogarithmicTrend <em>Logarithmic Trend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logarithmic Trend</em>'.
	 * @see tools.descartes.dlim.LogarithmicTrend
	 * @generated
	 */
	EClass getLogarithmicTrend();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.LogarithmicTrend#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order</em>'.
	 * @see tools.descartes.dlim.LogarithmicTrend#getOrder()
	 * @see #getLogarithmicTrend()
	 * @generated
	 */
	EAttribute getLogarithmicTrend_Order();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.SinTrend <em>Sin Trend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sin Trend</em>'.
	 * @see tools.descartes.dlim.SinTrend
	 * @generated
	 */
	EClass getSinTrend();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.ReferenceClockObject <em>Reference Clock Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Clock Object</em>'.
	 * @see tools.descartes.dlim.ReferenceClockObject
	 * @generated
	 */
	EClass getReferenceClockObject();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.ReferenceClockObject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see tools.descartes.dlim.ReferenceClockObject#getName()
	 * @see #getReferenceClockObject()
	 * @generated
	 */
	EAttribute getReferenceClockObject_Name();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.ReferenceClockObject#getLoopTime <em>Loop Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Loop Time</em>'.
	 * @see tools.descartes.dlim.ReferenceClockObject#getLoopTime()
	 * @see #getReferenceClockObject()
	 * @generated
	 */
	EAttribute getReferenceClockObject_LoopTime();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.ReferenceClockObject#getSeqTime <em>Seq Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seq Time</em>'.
	 * @see tools.descartes.dlim.ReferenceClockObject#getSeqTime()
	 * @see #getReferenceClockObject()
	 * @generated
	 */
	EAttribute getReferenceClockObject_SeqTime();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.ArrivalRatesFromFile <em>Arrival Rates From File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arrival Rates From File</em>'.
	 * @see tools.descartes.dlim.ArrivalRatesFromFile
	 * @generated
	 */
	EClass getArrivalRatesFromFile();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.ArrivalRatesFromFile#getFilePath <em>File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Path</em>'.
	 * @see tools.descartes.dlim.ArrivalRatesFromFile#getFilePath()
	 * @see #getArrivalRatesFromFile()
	 * @generated
	 */
	EAttribute getArrivalRatesFromFile_FilePath();

	/**
	 * Returns the meta object for the '{@link tools.descartes.dlim.ArrivalRatesFromFile#getArrivalRate(double) <em>Get Arrival Rate</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Arrival Rate</em>' operation.
	 * @see tools.descartes.dlim.ArrivalRatesFromFile#getArrivalRate(double)
	 * @generated
	 */
	EOperation getArrivalRatesFromFile__GetArrivalRate__double();

	/**
	 * Returns the meta object for the '{@link tools.descartes.dlim.ArrivalRatesFromFile#readFile() <em>Read File</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Read File</em>' operation.
	 * @see tools.descartes.dlim.ArrivalRatesFromFile#readFile()
	 * @generated
	 */
	EOperation getArrivalRatesFromFile__ReadFile();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.AbsoluteValueFunction <em>Absolute Value Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Absolute Value Function</em>'.
	 * @see tools.descartes.dlim.AbsoluteValueFunction
	 * @generated
	 */
	EClass getAbsoluteValueFunction();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.UnivariateFunction <em>Univariate Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Univariate Function</em>'.
	 * @see tools.descartes.dlim.UnivariateFunction
	 * @generated
	 */
	EClass getUnivariateFunction();

	/**
	 * Returns the meta object for the containment reference '{@link tools.descartes.dlim.UnivariateFunction#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see tools.descartes.dlim.UnivariateFunction#getFunction()
	 * @see #getUnivariateFunction()
	 * @generated
	 */
	EReference getUnivariateFunction_Function();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.Polynomial <em>Polynomial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polynomial</em>'.
	 * @see tools.descartes.dlim.Polynomial
	 * @generated
	 */
	EClass getPolynomial();

	/**
	 * Returns the meta object for the containment reference list '{@link tools.descartes.dlim.Polynomial#getFactors <em>Factors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Factors</em>'.
	 * @see tools.descartes.dlim.Polynomial#getFactors()
	 * @see #getPolynomial()
	 * @generated
	 */
	EReference getPolynomial_Factors();

	/**
	 * Returns the meta object for class '{@link tools.descartes.dlim.PolynomialFactor <em>Polynomial Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polynomial Factor</em>'.
	 * @see tools.descartes.dlim.PolynomialFactor
	 * @generated
	 */
	EClass getPolynomialFactor();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.PolynomialFactor#getFactor <em>Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factor</em>'.
	 * @see tools.descartes.dlim.PolynomialFactor#getFactor()
	 * @see #getPolynomialFactor()
	 * @generated
	 */
	EAttribute getPolynomialFactor_Factor();

	/**
	 * Returns the meta object for the attribute '{@link tools.descartes.dlim.PolynomialFactor#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see tools.descartes.dlim.PolynomialFactor#getOffset()
	 * @see #getPolynomialFactor()
	 * @generated
	 */
	EAttribute getPolynomialFactor_Offset();

	/**
	 * Returns the meta object for enum '{@link tools.descartes.dlim.ClockType <em>Clock Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Clock Type</em>'.
	 * @see tools.descartes.dlim.ClockType
	 * @generated
	 */
	EEnum getClockType();

	/**
	 * Returns the meta object for enum '{@link tools.descartes.dlim.Operator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Operator</em>'.
	 * @see tools.descartes.dlim.Operator
	 * @generated
	 */
	EEnum getOperator();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DlimFactory getDlimFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.SequenceImpl <em>Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.SequenceImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSequence()
		 * @generated
		 */
		EClass SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__NAME = eINSTANCE.getSequence_Name();

		/**
		 * The meta object literal for the '<em><b>Terminate After Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__TERMINATE_AFTER_TIME = eINSTANCE.getSequence_TerminateAfterTime();

		/**
		 * The meta object literal for the '<em><b>Reference Clock</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEQUENCE__REFERENCE_CLOCK = eINSTANCE.getSequence_ReferenceClock();

		/**
		 * The meta object literal for the '<em><b>Sequence Function Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEQUENCE__SEQUENCE_FUNCTION_CONTAINERS = eINSTANCE.getSequence_SequenceFunctionContainers();

		/**
		 * The meta object literal for the '<em><b>Terminate After Loops</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__TERMINATE_AFTER_LOOPS = eINSTANCE.getSequence_TerminateAfterLoops();

		/**
		 * The meta object literal for the '<em><b>First Iteration Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__FIRST_ITERATION_START = eINSTANCE.getSequence_FirstIterationStart();

		/**
		 * The meta object literal for the '<em><b>First Iteration End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__FIRST_ITERATION_END = eINSTANCE.getSequence_FirstIterationEnd();

		/**
		 * The meta object literal for the '<em><b>Loop Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__LOOP_DURATION = eINSTANCE.getSequence_LoopDuration();

		/**
		 * The meta object literal for the '<em><b>Final Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE__FINAL_DURATION = eINSTANCE.getSequence_FinalDuration();

		/**
		 * The meta object literal for the '<em><b>Duration Defined</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SEQUENCE___DURATION_DEFINED__DIAGNOSTICCHAIN_MAP = eINSTANCE.getSequence__DurationDefined__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.CombinatorImpl <em>Combinator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.CombinatorImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getCombinator()
		 * @generated
		 */
		EClass COMBINATOR = eINSTANCE.getCombinator();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMBINATOR__OPERATOR = eINSTANCE.getCombinator_Operator();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMBINATOR__FUNCTION = eINSTANCE.getCombinator_Function();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.TimeDependentFunctionContainerImpl <em>Time Dependent Function Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.TimeDependentFunctionContainerImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getTimeDependentFunctionContainer()
		 * @generated
		 */
		EClass TIME_DEPENDENT_FUNCTION_CONTAINER = eINSTANCE.getTimeDependentFunctionContainer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DEPENDENT_FUNCTION_CONTAINER__NAME = eINSTANCE.getTimeDependentFunctionContainer_Name();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DEPENDENT_FUNCTION_CONTAINER__DURATION = eINSTANCE.getTimeDependentFunctionContainer_Duration();

		/**
		 * The meta object literal for the '<em><b>First Iteration Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_START = eINSTANCE.getTimeDependentFunctionContainer_FirstIterationStart();

		/**
		 * The meta object literal for the '<em><b>First Iteration End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DEPENDENT_FUNCTION_CONTAINER__FIRST_ITERATION_END = eINSTANCE.getTimeDependentFunctionContainer_FirstIterationEnd();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_DEPENDENT_FUNCTION_CONTAINER__FUNCTION = eINSTANCE.getTimeDependentFunctionContainer_Function();

		/**
		 * The meta object literal for the '<em><b>Point Of Reference Clock Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT = eINSTANCE.getTimeDependentFunctionContainer_PointOfReferenceClockObject();

		/**
		 * The meta object literal for the '<em><b>Point Of Reference Clock Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DEPENDENT_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE = eINSTANCE.getTimeDependentFunctionContainer_PointOfReferenceClockType();

		/**
		 * The meta object literal for the '<em><b>Duration Greater Zero</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TIME_DEPENDENT_FUNCTION_CONTAINER___DURATION_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = eINSTANCE.getTimeDependentFunctionContainer__DurationGreaterZero__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '<em><b>Reference Clock In Tree Node</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TIME_DEPENDENT_FUNCTION_CONTAINER___REFERENCE_CLOCK_IN_TREE_NODE__DIAGNOSTICCHAIN_MAP = eINSTANCE.getTimeDependentFunctionContainer__ReferenceClockInTreeNode__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.FunctionImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Combine</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__COMBINE = eINSTANCE.getFunction_Combine();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.SeasonalImpl <em>Seasonal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.SeasonalImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSeasonal()
		 * @generated
		 */
		EClass SEASONAL = eINSTANCE.getSeasonal();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.NoiseImpl <em>Noise</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.NoiseImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getNoise()
		 * @generated
		 */
		EClass NOISE = eINSTANCE.getNoise();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.UniformNoiseImpl <em>Uniform Noise</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.UniformNoiseImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getUniformNoise()
		 * @generated
		 */
		EClass UNIFORM_NOISE = eINSTANCE.getUniformNoise();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIFORM_NOISE__MIN = eINSTANCE.getUniformNoise_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIFORM_NOISE__MAX = eINSTANCE.getUniformNoise_Max();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.NormalNoiseImpl <em>Normal Noise</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.NormalNoiseImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getNormalNoise()
		 * @generated
		 */
		EClass NORMAL_NOISE = eINSTANCE.getNormalNoise();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL_NOISE__MEAN = eINSTANCE.getNormalNoise_Mean();

		/**
		 * The meta object literal for the '<em><b>Standard Deviation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL_NOISE__STANDARD_DEVIATION = eINSTANCE.getNormalNoise_StandardDeviation();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.BurstImpl <em>Burst</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.BurstImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getBurst()
		 * @generated
		 */
		EClass BURST = eINSTANCE.getBurst();

		/**
		 * The meta object literal for the '<em><b>Peak</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BURST__PEAK = eINSTANCE.getBurst_Peak();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BURST__BASE = eINSTANCE.getBurst_Base();

		/**
		 * The meta object literal for the '<em><b>Peak Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BURST__PEAK_TIME = eINSTANCE.getBurst_PeakTime();

		/**
		 * The meta object literal for the '<em><b>Peak Time Greater Zero</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BURST___PEAK_TIME_GREATER_ZERO__DIAGNOSTICCHAIN_MAP = eINSTANCE.getBurst__PeakTimeGreaterZero__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.TrendImpl <em>Trend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.TrendImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getTrend()
		 * @generated
		 */
		EClass TREND = eINSTANCE.getTrend();

		/**
		 * The meta object literal for the '<em><b>Function Output At Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREND__FUNCTION_OUTPUT_AT_START = eINSTANCE.getTrend_FunctionOutputAtStart();

		/**
		 * The meta object literal for the '<em><b>Function Output At End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREND__FUNCTION_OUTPUT_AT_END = eINSTANCE.getTrend_FunctionOutputAtEnd();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.ConstantImpl <em>Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.ConstantImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getConstant()
		 * @generated
		 */
		EClass CONSTANT = eINSTANCE.getConstant();

		/**
		 * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT__CONSTANT = eINSTANCE.getConstant_Constant();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.SinImpl <em>Sin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.SinImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSin()
		 * @generated
		 */
		EClass SIN = eINSTANCE.getSin();

		/**
		 * The meta object literal for the '<em><b>Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIN__MIN = eINSTANCE.getSin_Min();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIN__MAX = eINSTANCE.getSin_Max();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIN__PERIOD = eINSTANCE.getSin_Period();

		/**
		 * The meta object literal for the '<em><b>Phase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIN__PHASE = eINSTANCE.getSin_Phase();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.ExponentialIncreaseAndDeclineImpl <em>Exponential Increase And Decline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.ExponentialIncreaseAndDeclineImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getExponentialIncreaseAndDecline()
		 * @generated
		 */
		EClass EXPONENTIAL_INCREASE_AND_DECLINE = eINSTANCE.getExponentialIncreaseAndDecline();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.ExponentialIncreaseLogarithmicDeclineImpl <em>Exponential Increase Logarithmic Decline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.ExponentialIncreaseLogarithmicDeclineImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getExponentialIncreaseLogarithmicDecline()
		 * @generated
		 */
		EClass EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE = eINSTANCE.getExponentialIncreaseLogarithmicDecline();

		/**
		 * The meta object literal for the '<em><b>Logarithmic Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_INCREASE_LOGARITHMIC_DECLINE__LOGARITHMIC_ORDER = eINSTANCE.getExponentialIncreaseLogarithmicDecline_LogarithmicOrder();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.LinearIncreaseAndDeclineImpl <em>Linear Increase And Decline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.LinearIncreaseAndDeclineImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getLinearIncreaseAndDecline()
		 * @generated
		 */
		EClass LINEAR_INCREASE_AND_DECLINE = eINSTANCE.getLinearIncreaseAndDecline();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.AbsoluteSinImpl <em>Absolute Sin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.AbsoluteSinImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getAbsoluteSin()
		 * @generated
		 */
		EClass ABSOLUTE_SIN = eINSTANCE.getAbsoluteSin();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.LinearTrendImpl <em>Linear Trend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.LinearTrendImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getLinearTrend()
		 * @generated
		 */
		EClass LINEAR_TREND = eINSTANCE.getLinearTrend();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.ExponentialTrendImpl <em>Exponential Trend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.ExponentialTrendImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getExponentialTrend()
		 * @generated
		 */
		EClass EXPONENTIAL_TREND = eINSTANCE.getExponentialTrend();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.LogarithmicTrendImpl <em>Logarithmic Trend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.LogarithmicTrendImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getLogarithmicTrend()
		 * @generated
		 */
		EClass LOGARITHMIC_TREND = eINSTANCE.getLogarithmicTrend();

		/**
		 * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOGARITHMIC_TREND__ORDER = eINSTANCE.getLogarithmicTrend_Order();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.SinTrendImpl <em>Sin Trend</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.SinTrendImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getSinTrend()
		 * @generated
		 */
		EClass SIN_TREND = eINSTANCE.getSinTrend();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.ReferenceClockObjectImpl <em>Reference Clock Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.ReferenceClockObjectImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getReferenceClockObject()
		 * @generated
		 */
		EClass REFERENCE_CLOCK_OBJECT = eINSTANCE.getReferenceClockObject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_CLOCK_OBJECT__NAME = eINSTANCE.getReferenceClockObject_Name();

		/**
		 * The meta object literal for the '<em><b>Loop Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_CLOCK_OBJECT__LOOP_TIME = eINSTANCE.getReferenceClockObject_LoopTime();

		/**
		 * The meta object literal for the '<em><b>Seq Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_CLOCK_OBJECT__SEQ_TIME = eINSTANCE.getReferenceClockObject_SeqTime();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.ArrivalRatesFromFileImpl <em>Arrival Rates From File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.ArrivalRatesFromFileImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getArrivalRatesFromFile()
		 * @generated
		 */
		EClass ARRIVAL_RATES_FROM_FILE = eINSTANCE.getArrivalRatesFromFile();

		/**
		 * The meta object literal for the '<em><b>File Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRIVAL_RATES_FROM_FILE__FILE_PATH = eINSTANCE.getArrivalRatesFromFile_FilePath();

		/**
		 * The meta object literal for the '<em><b>Get Arrival Rate</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ARRIVAL_RATES_FROM_FILE___GET_ARRIVAL_RATE__DOUBLE = eINSTANCE.getArrivalRatesFromFile__GetArrivalRate__double();

		/**
		 * The meta object literal for the '<em><b>Read File</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ARRIVAL_RATES_FROM_FILE___READ_FILE = eINSTANCE.getArrivalRatesFromFile__ReadFile();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.AbsoluteValueFunctionImpl <em>Absolute Value Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.AbsoluteValueFunctionImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getAbsoluteValueFunction()
		 * @generated
		 */
		EClass ABSOLUTE_VALUE_FUNCTION = eINSTANCE.getAbsoluteValueFunction();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.UnivariateFunctionImpl <em>Univariate Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.UnivariateFunctionImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getUnivariateFunction()
		 * @generated
		 */
		EClass UNIVARIATE_FUNCTION = eINSTANCE.getUnivariateFunction();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNIVARIATE_FUNCTION__FUNCTION = eINSTANCE.getUnivariateFunction_Function();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.PolynomialImpl <em>Polynomial</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.PolynomialImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getPolynomial()
		 * @generated
		 */
		EClass POLYNOMIAL = eINSTANCE.getPolynomial();

		/**
		 * The meta object literal for the '<em><b>Factors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLYNOMIAL__FACTORS = eINSTANCE.getPolynomial_Factors();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.impl.PolynomialFactorImpl <em>Polynomial Factor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.impl.PolynomialFactorImpl
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getPolynomialFactor()
		 * @generated
		 */
		EClass POLYNOMIAL_FACTOR = eINSTANCE.getPolynomialFactor();

		/**
		 * The meta object literal for the '<em><b>Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLYNOMIAL_FACTOR__FACTOR = eINSTANCE.getPolynomialFactor_Factor();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLYNOMIAL_FACTOR__OFFSET = eINSTANCE.getPolynomialFactor_Offset();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.ClockType <em>Clock Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.ClockType
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getClockType()
		 * @generated
		 */
		EEnum CLOCK_TYPE = eINSTANCE.getClockType();

		/**
		 * The meta object literal for the '{@link tools.descartes.dlim.Operator <em>Operator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tools.descartes.dlim.Operator
		 * @see tools.descartes.dlim.impl.DlimPackageImpl#getOperator()
		 * @generated
		 */
		EEnum OPERATOR = eINSTANCE.getOperator();

	}

} //DlimPackage
