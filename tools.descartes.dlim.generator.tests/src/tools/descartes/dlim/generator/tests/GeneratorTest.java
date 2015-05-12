/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.generator.tests;

import junit.framework.TestCase;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * General test case testing the ModelEvaluator, TimeKeeper, ModelEvaluatorUtil,
 * ReferenceClocks, and FileUtils using one larger test case.
 * 
 * @author Joakim von Kistowski
 */
public class GeneratorTest extends TestCase {

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public GeneratorTest(String name) {
		super(name);
	}

	/**
	 * Test function model evaluation for non-noisy output.
	 */
	public void testNonNoiseOutput() {
		Sequence root = (Sequence) DlimFileUtils
				.getRootEObject(DlimTestUtils.TEST_MODEL);
		ModelEvaluator evaluator = new ModelEvaluator(root);

		assertEquals(67.90422978769126, evaluator.getArrivalRateAtTime(7.5),
				DlimTestUtils.FP_DELTA);
		assertEquals(24.022190628493753, evaluator.getArrivalRateAtTime(16.5),
				DlimTestUtils.FP_DELTA);
		assertEquals(13.620250332468729, evaluator.getArrivalRateAtTime(18.5),
				DlimTestUtils.FP_DELTA);
		assertEquals(31.040088379881254, evaluator.getArrivalRateAtTime(27.5),
				DlimTestUtils.FP_DELTA);
		assertEquals(8.392110488311772, evaluator.getArrivalRateAtTime(38.5),
				DlimTestUtils.FP_DELTA);
	}

	/**
	 * Test model evaluation for noisy output.
	 */
	public void testNoisyOutput() {
		Sequence root = (Sequence) DlimFileUtils
				.getRootEObject(DlimTestUtils.TEST_MODEL);
		ModelEvaluator evaluator = new ModelEvaluator(root, 12,
				IGeneratorConstants.EVALUATION);

		assertEquals(10.877117954799253, evaluator.getArrivalRateAtTime(2.5),
				DlimTestUtils.FP_DELTA);
		assertEquals(9.951871986911737, evaluator.getArrivalRateAtTime(23.5),
				DlimTestUtils.FP_DELTA);
	}

}
