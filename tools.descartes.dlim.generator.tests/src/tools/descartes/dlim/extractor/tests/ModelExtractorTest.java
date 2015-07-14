/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.extractor.HLDlimParameterContainer;
import tools.descartes.dlim.extractor.ModelExtractor;
import tools.descartes.dlim.generator.DiffAnalyzer;
import tools.descartes.dlim.generator.IGeneratorConstants;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.reader.ArrivalRateReader;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Tests the three pre-packaged model extraction processes.
 *
 * @author Joakim von Kistowski
 */
public class ModelExtractorTest extends TestCase {

	private static final String TEST_TRACE = DlimTestUtils.TEST_INPUT_DIR
			+ "wikipedia_trace.txt";

	private Sequence root;

	/**
	 * Create a new test case.
	 *
	 * @param name
	 *            test case name.
	 */
	public ModelExtractorTest(String name) {
		super(name);
	}

	/**
	 * Sets up the test. Creates model to extract into. Cleans output
	 * directories.
	 */
	@Override
	protected void setUp() {
		DlimTestUtils.cleanTestOutputDirectories();
		root = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
	}

	/**
	 * s-DLIM Extraction test.
	 */
	public void testArrivalRateFileIntoSequence() {

		try {
			ModelExtractor.extractArrivalRateFileIntoSequenceBinarySplits(root,
					ArrivalRateReader.readFileToList(TEST_TRACE, 0.0), 24, 1,
					"SinTrend", "SinTrend", "MULT", true);
			testOutputModel(0.16, 0.1);
		} catch (CalibrationException e) {
			fail("Model Extractor threw a CalibrationException: "
					+ e.getMessage());
		} catch (IOException e) {
			fail("Did not find arrival rate file.");
		}
	}

	/**
	 * p-DLIM Extraction test.
	 */
	public void testArrivalRateFilePeriodic() {
		int[] weekly = { 3, 4 };
		int[] biweekly = { 7, 7 };
		try {
			ModelExtractor.extractSequenceFromArrivalRateFilePeriodic(root,
					ArrivalRateReader.readFileToList(TEST_TRACE, 0.0), 24,
					Arrays.asList(weekly, biweekly), "SinTrend", "SinTrend",
					"MULT", false);
			testOutputModel(0.18, 0.15);
		} catch (CalibrationException e) {
			fail("Model Extractor threw a CalibrationException: "
					+ e.getMessage());
		} catch (IOException e) {
			fail("Did not find arrival rate file.");
		}
	}

	/**
	 * hl-DLIM Extraction test. This one is really hard to test for correctness,
	 * as the "correct" result could pretty much be anything. So all this test
	 * does is to check, whether the result ist at least in the approximate
	 * ballpark.
	 */
	public void testArrivalRateFileIntoParameters() {
		try {
			HLDlimParameterContainer params = ModelExtractor
					.extractArrivalRateFileIntoParameters(TEST_TRACE, 24, 0.0,
							1, "SinTrend", "SinTrend", "MULT", true);

			assertTrue(params.getFirstPeak() > 1000000
					&& params.getFirstPeak() < 3500000);
			assertTrue(params.getLastPeak() > 1000000
					&& params.getLastPeak() < 3500000);
			assertTrue(params.getInnerBase() > 1500000);
			assertTrue(params.getBase() > 0);
			assertTrue(params.getPeakIntervalWidth() < 24);
			assertTrue(params.getPeakNum() > 0);
			assertTrue(params.getNoiseMax() > 0);
			assertTrue(params.getTrendPoints().length > 50);

		} catch (CalibrationException e) {
			fail("Model Extractor threw a CalibrationException: "
					+ e.getMessage());
		}
	}

	private void testOutputModel(double maximumRelativeMeanError,
			double maximumRelativeMedianError) {
		ModelEvaluator evaluator = new ModelEvaluator(root, 0,
				IGeneratorConstants.CALIBRATION);
		DiffAnalyzer analyzer = new DiffAnalyzer(evaluator,
				DlimTestUtils.getProjectPath());
		try {
			List<Double> stats = analyzer.calculateDiff(
					ArrivalRateReader.readFileToList(TEST_TRACE, 0.0), 0.0);
			assertTrue(stats.get(3) <= maximumRelativeMeanError); // mean
			assertTrue(stats.get(4) <= maximumRelativeMedianError); // mean
		} catch (IOException e) {
			fail("Did not find arrival rate file.");
		}
	}

}
