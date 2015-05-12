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
import tools.descartes.dlim.exporter.utils.EqualDistanceTimestampWriter;
import tools.descartes.dlim.exporter.utils.UniformDistributionTimestampWriter;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;
import tools.descartes.dlim.reader.ArrivalRateReader;
import tools.descartes.dlim.reader.RequestTimeSeriesReader;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Tests the Arrival Rate file from Timestamp file reading.
 * 
 * @author Joakim von Kistowski
 */
public class TimeStampWriterTest extends TestCase {

	private static final String TEST_EQUALDISTANCE_NAME = DlimTestUtils.TEST_OUTPUT_DIR
			+ "/equaldisttstest.txt";
	private static final String TEST_RANDOM_NAME = DlimTestUtils.TEST_OUTPUT_DIR
			+ "/randomtstest.txt";

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public TimeStampWriterTest(String name) {
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
	 * Test timestamp generation for equal distance timestamps.
	 */
	public void testEqualDistanceTimeStamps() {
		Sequence root = (Sequence) DlimFileUtils
				.getRootEObject(DlimTestUtils.TEST_MODEL);
		ModelEvaluator evaluator = new ModelEvaluator(root);
		EqualDistanceTimestampWriter tsw = new EqualDistanceTimestampWriter(";");
		// Create TimeStamps
		tsw.generateTimeStampsFromArrivalRates(
				new File(TEST_EQUALDISTANCE_NAME),
				ArrivalRateGenerator.generateArrivalRates(evaluator, 1.0), 4,
				1.0, 1.0);

		checkTimeStampCreation(evaluator, TEST_EQUALDISTANCE_NAME);

	}

	/**
	 * Test timestamp generation for timestamps with a uniform random
	 * distribution.
	 */
	public void testUniformRandomTimeStamps() {
		Sequence root = (Sequence) DlimFileUtils
				.getRootEObject(DlimTestUtils.TEST_MODEL);
		ModelEvaluator evaluator = new ModelEvaluator(root);
		UniformDistributionTimestampWriter tsw = new UniformDistributionTimestampWriter(
				";", evaluator.getRndGenerator());
		// Create TimeStamps
		tsw.generateTimeStampsFromArrivalRates(new File(TEST_RANDOM_NAME),
				ArrivalRateGenerator.generateArrivalRates(evaluator, 1.0), 4,
				1.0, 1.0, evaluator.getDuration());

		checkTimeStampCreation(evaluator, TEST_RANDOM_NAME);
	}

	/**
	 * Tests for correct time stamp creation for any given time stamp writer
	 * implementing the TimeStampWriter Interface.
	 * 
	 * @param tsOutPath
	 * @param tsw
	 */
	private void checkTimeStampCreation(ModelEvaluator evaluator,
			String tsOutPath) {
		// Read time stamps back in for checking
		RequestTimeSeriesReader.createArrivalRatesFromSortedTimeStamps(
				tsOutPath, DlimTestUtils.TEST_OUTPUT_DIR);
		String tmpArrivalRateFileName = tsOutPath.substring(0,
				tsOutPath.length() - 4)
				+ "ArrivalRates.txt";
		try {
			List<ArrivalRateTuple> list = ArrivalRateReader.readFileToList(
					tmpArrivalRateFileName, 0.0);
			assertEquals((int) evaluator.getArrivalRateAtTime(5.5), list.get(5)
					.getArrivalRate(), DlimTestUtils.FP_DELTA);
			assertEquals(7.5, list.get(7).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
			assertEquals((int) evaluator.getArrivalRateAtTime(2.5), list.get(2)
					.getArrivalRate(), DlimTestUtils.FP_DELTA);
			assertEquals(11.5, list.get(11).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
		} catch (IOException e) {
			fail("RequestTimeSeriesReader did not write to expected location.");
		}
	}

}
