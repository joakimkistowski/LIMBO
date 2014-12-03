/**
 */
package tools.descartes.dlim.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import tools.descartes.dlim.*;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see tools.descartes.dlim.DlimPackage
 * @generated
 */
public class DlimAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static DlimPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public DlimAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DlimPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DlimSwitch<Adapter> modelSwitch = new DlimSwitch<Adapter>() {
		@Override
		public Adapter caseSequence(Sequence object) {
			return createSequenceAdapter();
		}

		@Override
		public Adapter caseCombinator(Combinator object) {
			return createCombinatorAdapter();
		}

		@Override
		public Adapter caseTimeDependentFunctionContainer(
				TimeDependentFunctionContainer object) {
			return createTimeDependentFunctionContainerAdapter();
		}

		@Override
		public Adapter caseFunction(Function object) {
			return createFunctionAdapter();
		}

		@Override
		public Adapter caseSeasonal(Seasonal object) {
			return createSeasonalAdapter();
		}

		@Override
		public Adapter caseNoise(Noise object) {
			return createNoiseAdapter();
		}

		@Override
		public Adapter caseUniformNoise(UniformNoise object) {
			return createUniformNoiseAdapter();
		}

		@Override
		public Adapter caseNormalNoise(NormalNoise object) {
			return createNormalNoiseAdapter();
		}

		@Override
		public Adapter caseBurst(Burst object) {
			return createBurstAdapter();
		}

		@Override
		public Adapter caseTrend(Trend object) {
			return createTrendAdapter();
		}

		@Override
		public Adapter caseConstant(Constant object) {
			return createConstantAdapter();
		}

		@Override
		public Adapter caseSin(Sin object) {
			return createSinAdapter();
		}

		@Override
		public Adapter caseExponentialIncreaseAndDecline(
				ExponentialIncreaseAndDecline object) {
			return createExponentialIncreaseAndDeclineAdapter();
		}

		@Override
		public Adapter caseExponentialIncreaseLogarithmicDecline(
				ExponentialIncreaseLogarithmicDecline object) {
			return createExponentialIncreaseLogarithmicDeclineAdapter();
		}

		@Override
		public Adapter caseLinearIncreaseAndDecline(
				LinearIncreaseAndDecline object) {
			return createLinearIncreaseAndDeclineAdapter();
		}

		@Override
		public Adapter caseAbsoluteSin(AbsoluteSin object) {
			return createAbsoluteSinAdapter();
		}

		@Override
		public Adapter caseLinearTrend(LinearTrend object) {
			return createLinearTrendAdapter();
		}

		@Override
		public Adapter caseExponentialTrend(ExponentialTrend object) {
			return createExponentialTrendAdapter();
		}

		@Override
		public Adapter caseLogarithmicTrend(LogarithmicTrend object) {
			return createLogarithmicTrendAdapter();
		}

		@Override
		public Adapter caseSinTrend(SinTrend object) {
			return createSinTrendAdapter();
		}

		@Override
		public Adapter caseReferenceClockObject(ReferenceClockObject object) {
			return createReferenceClockObjectAdapter();
		}

		@Override
		public Adapter caseArrivalRatesFromFile(ArrivalRatesFromFile object) {
			return createArrivalRatesFromFileAdapter();
		}

		@Override
		public Adapter caseAbsoluteValueFunction(AbsoluteValueFunction object) {
			return createAbsoluteValueFunctionAdapter();
		}

		@Override
		public Adapter caseUnivariateFunction(UnivariateFunction object) {
			return createUnivariateFunctionAdapter();
		}

		@Override
		public Adapter casePolynomial(Polynomial object) {
			return createPolynomialAdapter();
		}

		@Override
		public Adapter casePolynomialFactor(PolynomialFactor object) {
			return createPolynomialFactorAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Sequence <em>Sequence</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Sequence
	 * @generated
	 */
	public Adapter createSequenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Combinator <em>Combinator</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Combinator
	 * @generated
	 */
	public Adapter createCombinatorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.TimeDependentFunctionContainer
	 * <em>Time Dependent Function Container</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.TimeDependentFunctionContainer
	 * @generated
	 */
	public Adapter createTimeDependentFunctionContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Function <em>Function</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Seasonal <em>Seasonal</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Seasonal
	 * @generated
	 */
	public Adapter createSeasonalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Noise <em>Noise</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Noise
	 * @generated
	 */
	public Adapter createNoiseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.UniformNoise <em>Uniform Noise</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.UniformNoise
	 * @generated
	 */
	public Adapter createUniformNoiseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.NormalNoise <em>Normal Noise</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.NormalNoise
	 * @generated
	 */
	public Adapter createNormalNoiseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Burst <em>Burst</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Burst
	 * @generated
	 */
	public Adapter createBurstAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Trend <em>Trend</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Trend
	 * @generated
	 */
	public Adapter createTrendAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Constant <em>Constant</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Constant
	 * @generated
	 */
	public Adapter createConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Sin <em>Sin</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Sin
	 * @generated
	 */
	public Adapter createSinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.ExponentialIncreaseAndDecline
	 * <em>Exponential Increase And Decline</em>}'. <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.ExponentialIncreaseAndDecline
	 * @generated
	 */
	public Adapter createExponentialIncreaseAndDeclineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline
	 * <em>Exponential Increase Logarithmic Decline</em>}'. <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline
	 * @generated
	 */
	public Adapter createExponentialIncreaseLogarithmicDeclineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.LinearIncreaseAndDecline
	 * <em>Linear Increase And Decline</em>}'. <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.LinearIncreaseAndDecline
	 * @generated
	 */
	public Adapter createLinearIncreaseAndDeclineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.AbsoluteSin <em>Absolute Sin</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.AbsoluteSin
	 * @generated
	 */
	public Adapter createAbsoluteSinAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.LinearTrend <em>Linear Trend</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.LinearTrend
	 * @generated
	 */
	public Adapter createLinearTrendAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.ExponentialTrend <em>Exponential Trend</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.ExponentialTrend
	 * @generated
	 */
	public Adapter createExponentialTrendAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.LogarithmicTrend <em>Logarithmic Trend</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.LogarithmicTrend
	 * @generated
	 */
	public Adapter createLogarithmicTrendAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.SinTrend <em>Sin Trend</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.SinTrend
	 * @generated
	 */
	public Adapter createSinTrendAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.ReferenceClockObject
	 * <em>Reference Clock Object</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.ReferenceClockObject
	 * @generated
	 */
	public Adapter createReferenceClockObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.ArrivalRatesFromFile
	 * <em>Arrival Rates From File</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.ArrivalRatesFromFile
	 * @generated
	 */
	public Adapter createArrivalRatesFromFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.AbsoluteValueFunction
	 * <em>Absolute Value Function</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.AbsoluteValueFunction
	 * @generated
	 */
	public Adapter createAbsoluteValueFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.UnivariateFunction
	 * <em>Univariate Function</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.UnivariateFunction
	 * @generated
	 */
	public Adapter createUnivariateFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.Polynomial <em>Polynomial</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.Polynomial
	 * @generated
	 */
	public Adapter createPolynomialAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tools.descartes.dlim.PolynomialFactor <em>Polynomial Factor</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so
	 * that we can easily ignore cases; it's useful to ignore a case when
	 * inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tools.descartes.dlim.PolynomialFactor
	 * @generated
	 */
	public Adapter createPolynomialFactorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // DlimAdapterFactory
