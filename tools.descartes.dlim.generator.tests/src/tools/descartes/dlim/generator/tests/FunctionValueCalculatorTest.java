/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.generator.tests;

import junit.framework.TestCase;
import tools.descartes.dlim.AbsoluteSin;
import tools.descartes.dlim.Burst;
import tools.descartes.dlim.DlimFactory;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.ExponentialIncreaseAndDecline;
import tools.descartes.dlim.ExponentialIncreaseLogarithmicDecline;
import tools.descartes.dlim.LinearIncreaseAndDecline;
import tools.descartes.dlim.LogarithmicTrend;
import tools.descartes.dlim.Polynomial;
import tools.descartes.dlim.PolynomialFactor;
import tools.descartes.dlim.Sin;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.Trend;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.util.FunctionValueCalculator;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Tests the FunctionValueCalculator.
 *
 * @author Joakim von Kistowski
 */
public class FunctionValueCalculatorTest extends TestCase {

	private FunctionValueCalculator calc = null;

	/**
	 * Create a new test case.
	 *
	 * @param name
	 *            test case name.
	 */
	public FunctionValueCalculatorTest(String name) {
		super(name);
	}

	/**
	 * Sets up the function value calculator.
	 */
	@Override
	protected void setUp() {
		calc = new FunctionValueCalculator(null,
				IGeneratorConstants.CALIBRATION);
	}

	/**
	 * Deletes the function value calculator.
	 */
	@Override
	protected void tearDown() throws Exception {
		calc = null;
	}

	/**
	 * Test function value calculation.
	 */
	public void testGetFunctionValueForPolynomials() {
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();

		Polynomial p = factory.createPolynomial();
		PolynomialFactor p1 = factory.createPolynomialFactor();
		p1.setFactor(3);
		p1.setOffset(2);
		PolynomialFactor p2 = factory.createPolynomialFactor();
		p2.setFactor(2);
		p.getFactors().add(p1);
		p.getFactors().add(p2);

		assertEquals(14.0, calc.getSimpleFunctionValue(p, 2),
				DlimTestUtils.FP_DELTA);
	}

	/**
	 * Test function value calculation.
	 */
	public void testGetFunctionValueForSeasonals() {
		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();

		// Sin
		Sin sin = factory.createSin();
		sin.setMax(10);
		sin.setMin(0);
		sin.setPeriod(10);
		sin.setPhase(0);
		assertEquals(5.0, calc.getSimpleFunctionValue(sin, 5),
				DlimTestUtils.FP_DELTA);

		// Absolute Sin
		AbsoluteSin absSin = factory.createAbsoluteSin();
		absSin.setMax(10);
		absSin.setMin(0);
		absSin.setPeriod(10);
		absSin.setPhase(0);
		assertEquals(5.0, calc.getSimpleFunctionValue(sin, 5),
				DlimTestUtils.FP_DELTA);
	}

	private void configureBurst(Burst burst,
			TimeDependentFunctionContainer container) {
		final double base = 0.0;
		final double peak = 5.0;
		final double peakTime = 2.0;

		burst.setBase(base);
		burst.setPeak(peak);
		burst.setPeakTime(peakTime);
		container.setFunction(burst);
	}

	/**
	 * Test function value calculation.
	 */
	public void testGetFunctionValueForBursts() {
		final double logarithmicOrder = 4.0;
		final double sampleTime1 = 1.5;
		final double sampleTime2 = 2.5;

		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		TimeDependentFunctionContainer container = factory
				.createTimeDependentFunctionContainer();
		container.setDuration(5.0);

		// LinearIncreaseAndDecline
		LinearIncreaseAndDecline liad = factory
				.createLinearIncreaseAndDecline();
		configureBurst(liad, container);
		assertEquals(3.75, calc.getSimpleFunctionValue(liad, sampleTime1),
				DlimTestUtils.FP_DELTA);
		assertEquals(4.166666666666667,
				calc.getSimpleFunctionValue(liad, sampleTime2),
				DlimTestUtils.FP_DELTA);

		// ExponentialIncreaseAndDecline
		ExponentialIncreaseAndDecline eiad = factory
				.createExponentialIncreaseAndDecline();
		configureBurst(eiad, container);
		assertEquals(2.5200526772686183,
				calc.getSimpleFunctionValue(eiad, sampleTime1),
				DlimTestUtils.FP_DELTA);
		assertEquals(3.188053750819521,
				calc.getSimpleFunctionValue(eiad, sampleTime2),
				DlimTestUtils.FP_DELTA);

		// ExponentialIncreaseAndDecline
		ExponentialIncreaseLogarithmicDecline eild = factory
				.createExponentialIncreaseLogarithmicDecline();
		configureBurst(eild, container);
		eild.setLogarithmicOrder(logarithmicOrder);
		assertEquals(2.5200526772686183,
				calc.getSimpleFunctionValue(eild, sampleTime1),
				DlimTestUtils.FP_DELTA);
		assertEquals(4.776668597588648,
				calc.getSimpleFunctionValue(eild, sampleTime2),
				DlimTestUtils.FP_DELTA);
	}

	private void configureTrend(Trend trend,
			TimeDependentFunctionContainer container) {
		final double start = 0.0;
		final double end = 10.0;
		trend.setFunctionOutputAtEnd(end);
		trend.setFunctionOutputAtStart(start);
		container.setFunction(trend);
	}

	/**
	 * Test function value calculation.
	 */
	public void testGetFunctionValueForTrends() {
		final double logarithmicOrder = 4.0;
		final double sampleTime = 2.0;

		DlimFactory factory = DlimPackage.eINSTANCE.getDlimFactory();
		TimeDependentFunctionContainer container = factory
				.createTimeDependentFunctionContainer();
		container.setDuration(5.0);

		// LinearTrend
		Trend lt = factory.createLinearTrend();
		configureTrend(lt, container);
		assertEquals(4.0, calc.getSimpleFunctionValue(lt, sampleTime),
				DlimTestUtils.FP_DELTA);

		// ExponentialTrend
		Trend et = factory.createExponentialTrend();
		configureTrend(et, container);
		assertEquals(1.1898872226177144,
				calc.getSimpleFunctionValue(et, sampleTime),
				DlimTestUtils.FP_DELTA);

		// LogarithmicTrend
		LogarithmicTrend logt = factory.createLogarithmicTrend();
		logt.setOrder(logarithmicOrder);
		configureTrend(logt, container);
		assertEquals(7.777030259686199,
				calc.getSimpleFunctionValue(logt, sampleTime),
				DlimTestUtils.FP_DELTA);

		// SinTrend
		Trend st = factory.createSinTrend();
		configureTrend(st, container);
		assertEquals(3.4549150281252627,
				calc.getSimpleFunctionValue(st, sampleTime),
				DlimTestUtils.FP_DELTA);

	}

}
