/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.impl.custom.tests;

import junit.framework.TestCase;
import tools.descartes.dlim.DlimPackage;

/**
 * Tests the custom DlimFactory implementation.
 * 
 * @author Joakim von Kistowski
 */
public class CustomDlimFactoryTest extends TestCase {

	/**
	 * Create a new test case.
	 * 
	 * @param name
	 *            test case name.
	 */
	public CustomDlimFactoryTest(String name) {
		super(name);
	}

	/**
	 * Tests if DlimPackage returns a factory of correct type.
	 */
	public void testCorrectTypeCreation() {
		assertEquals(DlimPackage.eINSTANCE.getDlimFactory()
				.createArrivalRatesFromFile().getClass().getName(),
				"tools.descartes.dlim.impl.custom.CustomArrivalRatesFromFileImpl");
	}

}
