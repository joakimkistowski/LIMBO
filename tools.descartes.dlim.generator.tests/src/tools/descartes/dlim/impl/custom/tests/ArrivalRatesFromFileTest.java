/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.impl.custom.tests;

import org.eclipse.emf.ecore.EObject;

import tools.descartes.dlim.ArrivalRatesFromFile;
import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.tests.DlimTestUtils;
import tools.descartes.dlim.tests.ModelElementTest;

/**
 * Tests the custom ArrivalRatesFromFile implementation.
 * 
 * @author Joakim von Kistowski
 */
public class ArrivalRatesFromFileTest extends ModelElementTest {

	private static final String TEST_FILE_PATH = DlimTestUtils.TEST_INPUT_DIR
			+ "testarrivalrates.txt";

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public ArrivalRatesFromFileTest(String name) {
		super(name);
	}

	/**
	 * Creates a new ArrivalRateFromFile using the DlimPackage's factory.
	 */
	@Override
	protected EObject setupFixture() {
		return DlimPackage.eINSTANCE.getDlimFactory()
				.createArrivalRatesFromFile();
	}

	/**
	 * Tests file path getter and setter.
	 */
	public void testSetAndGetFilePath() {
		ArrivalRatesFromFile fix = (ArrivalRatesFromFile) getFixture();
		fix.setFilePath(TEST_FILE_PATH);
		assertEquals(fix.getFilePath(), TEST_FILE_PATH);
	}

	/**
	 * Tests if files are read correctly.
	 */
	public void testReadFileAndGetArrivalRate() {
		ArrivalRatesFromFile fix = (ArrivalRatesFromFile) getFixture();
		fix.setFilePath(TEST_FILE_PATH);
		fix.readFile();

		// exact values
		assertEquals(fix.getArrivalRate(0.5), 299852.8550274427,
				DlimTestUtils.FP_DELTA);
		assertEquals(fix.getArrivalRate(28.5), 1131733.294019682,
				DlimTestUtils.FP_DELTA);

		// linear interpolation
		assertEquals(fix.getArrivalRate(1.0),
				(299852.8550274427 + 399856.212759782) / 2.0,
				DlimTestUtils.FP_DELTA);
	}

}
