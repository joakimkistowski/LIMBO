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
import tools.descartes.dlim.reader.DefaultArrivalRateReader;
import tools.descartes.dlim.reader.IDlimArrivalRateReader;
import tools.descartes.dlim.tests.DlimTestUtils;

/**
 * Tests arrival rate to List reading using the DefaultArrivalRateReader, which
 * in turn uses the static methods of ArrivalRateReader.
 * 
 * @author Joakim von Kistowski
 */
public class DefaultArrivalRateReaderTest extends TestCase {

	private static final String TEST_ARRIVALRATES = DlimTestUtils.TEST_INPUT_DIR
			+ "testarrivalrates.txt";

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public DefaultArrivalRateReaderTest(String name) {
		super(name);
	}

	/**
	 * Test arrival rate reading.
	 */
	public void testCreateArrivalRatesFromSortedTimeStamps() {
		IDlimArrivalRateReader reader = new DefaultArrivalRateReader();
		try {
			List<ArrivalRateTuple> list = reader.readFileToList(
					TEST_ARRIVALRATES, 1.0);
			assertEquals(1483282.44369278, list.get(4).getArrivalRate(),
					DlimTestUtils.FP_DELTA);
			assertEquals(5.5, list.get(5).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
			assertEquals(832451.0443672254, list.get(26).getArrivalRate(),
					DlimTestUtils.FP_DELTA);
			assertEquals(27.5, list.get(27).getTimeStamp(),
					DlimTestUtils.FP_DELTA);
		} catch (IOException e) {
			fail("RequestTimeSeriesReader did not write to expected location.");
		}
	}

}
