/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.reader.tests;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.reader.ArrivalRateReader;
import tools.descartes.dlim.reader.RequestTimeSeriesReader;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Tests the Arrival Rate file from Timestamp file reading.
 * 
 * @author Joakim von Kistowski
 */
public class RequestTimeSeriesReaderTest extends TestCase {

	private static final String TEST_SORTED_TIMESTAMPS = DlimTestUtils.TEST_INPUT_DIR
			+ "sorted_timestamps.txt";
	private static final String TEST_UNSORTED_TIMESTAMPS = DlimTestUtils.TEST_INPUT_DIR
			+ "unsorted_timestamps.txt";

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public RequestTimeSeriesReaderTest(String name) {
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
	 * Test sorted timestamp reading.
	 */
	public void testCreateArrivalRatesFromSortedTimeStamps() {
		RequestTimeSeriesReader.createArrivalRatesFromSortedTimeStamps(
				TEST_SORTED_TIMESTAMPS, DlimTestUtils.TEST_OUTPUT_DIR);

		try {
			List<ArrivalRateTuple> list = ArrivalRateReader.readFileToList(
					DlimTestUtils.TEST_OUTPUT_DIR
							+ "sorted_timestampsArrivalRates.txt", 0.0);
			assertEquals(68.0, list.get(5).getArrivalRate(),
					DlimTestUtils.FP_DELTA);
			assertEquals(5.5, list.get(5).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
			assertEquals(11.0, list.get(22).getArrivalRate(),
					DlimTestUtils.FP_DELTA);
			assertEquals(22.5, list.get(22).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
		} catch (IOException e) {
			fail("RequestTimeSeriesReader did not write to expected location.");
		}
	}

	/**
	 * Test unsorted timestamp reading.
	 */
	public void testCreateArrivalRatesFromUnsortedTimeStamps() {
		RequestTimeSeriesReader.createArrivalRatesFromUnsortedTimeStamps(
				TEST_UNSORTED_TIMESTAMPS, DlimTestUtils.TEST_OUTPUT_DIR);

		try {
			List<ArrivalRateTuple> list = ArrivalRateReader.readFileToList(
					DlimTestUtils.TEST_OUTPUT_DIR
							+ "unsorted_timestampsArrivalRates.txt", 0.0);
			assertEquals(68.0, list.get(5).getArrivalRate(),
					DlimTestUtils.FP_DELTA);
			assertEquals(5.5, list.get(5).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
			assertEquals(11.0, list.get(22).getArrivalRate(),
					DlimTestUtils.FP_DELTA);
			assertEquals(22.5, list.get(22).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
		} catch (IOException e) {
			fail("RequestTimeSeriesReader did not write to expected location.");
		}
	}

}
