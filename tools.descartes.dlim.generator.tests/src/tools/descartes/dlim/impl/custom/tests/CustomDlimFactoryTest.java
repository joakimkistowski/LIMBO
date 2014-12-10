package tools.descartes.dlim.impl.custom.tests;

import junit.framework.TestCase;
import tools.descartes.dlim.DlimPackage;

/**
 * Tests the custom DlimFactory implementation.
 * 
 * @author Jóakim v. Kistowski
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
