/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.generator.tests;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.exporter.utils.DlimFileUtils;
import tools.descartes.dlim.generator.DiffAnalyzer;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.reader.ArrivalRateReader;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Tests the DiffAnalyzer.
 * 
 * @author Joakim von Kistowski
 */
public class DiffAnalyzerTest extends TestCase {

	private static final String DIFF_TEST_FILE = DlimTestUtils.TEST_INPUT_DIR
			+ "diffTestArrivalRates.csv";

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public DiffAnalyzerTest(String name) {
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
	 * Test the DiffAnalyzer.
	 */
	public void testDiffAnalyzer() {
		Sequence root = (Sequence) DlimFileUtils
				.getRootEObject(DlimTestUtils.TEST_MODEL);
		ModelEvaluator evaluator = new ModelEvaluator(root);

		DiffAnalyzer analyzer = new DiffAnalyzer(evaluator,
				DlimTestUtils.getProjectPath());
		try {
			List<Double> stats = analyzer.calculateDiff(
					ArrivalRateReader.readFileToList(DIFF_TEST_FILE, 0.0), 0.0);

			assertEquals(1.2725940132786857, stats.get(0),
					DlimTestUtils.FP_DELTA); // mean
			assertEquals(0.2437987567144031, stats.get(1),
					DlimTestUtils.FP_DELTA); // median
			assertEquals(0.01967103156809735, stats.get(2),
					DlimTestUtils.FP_DELTA); // dtwdist
			assertEquals(0.039423918279074717, stats.get(3),
					DlimTestUtils.FP_DELTA); // relative mean
			assertEquals(0.02391546411863334, stats.get(4),
					DlimTestUtils.FP_DELTA); // relative median
		} catch (IOException e) {
			fail("Did not find arrival rate file.");
		}
	}
}
