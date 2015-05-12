/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.exporter.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.utils.ArrivalRateGenerator;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.reader.DefaultArrivalRateReader;
import tools.descartes.dlim.reader.IDlimArrivalRateReader;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Generates arrival rates and writes them to a file. Compares written rates
 * with model output.
 * 
 * @author Joakim von Kistowski
 */
public class ArrivalRateGeneratorTest extends TestCase {

	private static final String TEST_NAME = "arrivalrategeneratortest";

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public ArrivalRateGeneratorTest(String name) {
		super(name);
	}

	/**
	 * Sets up the test. Cleans output directory.
	 */
	@Override
	protected void setUp() {
		DlimTestUtils.cleanTestOutputDirectories();
	}

	/**
	 * Test arrival rate writing.
	 */
	public void testWriteArrivalRates() {
		Sequence root = (Sequence) DlimFileUtils
				.getRootEObject(DlimTestUtils.TEST_MODEL);
		ModelEvaluator evaluator = new ModelEvaluator(root);

		ArrivalRateGenerator.writeArrivalRates(
				ArrivalRateGenerator.generateArrivalRates(evaluator, 1.0),
				new File("").getAbsolutePath(), TEST_NAME, "", "csv");
		IDlimArrivalRateReader reader = new DefaultArrivalRateReader();
		try {
			List<ArrivalRateTuple> list = reader.readFileToList(
					"./arrivalRates/" + TEST_NAME + "ArrivalRates.csv", 0.0);
			assertEquals(evaluator.getArrivalRateAtTime(6.5), list.get(6)
					.getArrivalRate(), DlimTestUtils.FP_DELTA);
			assertEquals(2.5, list.get(2).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
			assertEquals(evaluator.getArrivalRateAtTime(8.5), list.get(8)
					.getArrivalRate(), DlimTestUtils.FP_DELTA);
			assertEquals(9.5, list.get(9).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
		} catch (IOException e) {
			fail("Did not find arrival rate file.");
		}
	}

}
