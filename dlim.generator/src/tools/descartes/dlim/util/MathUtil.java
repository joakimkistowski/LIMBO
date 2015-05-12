/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.util;

/**
 * Simple class for common mathematical util functions.
 *
 * @author Joakim von Kistowski
 *
 */
public final class MathUtil {

	/**
	 * Hidden Constructor for Utility Class.
	 */
	private MathUtil() {
		
	}

	/**
	 * Utility function to format a given double to the accuracy of decimalcount decimal places.
	 * @param d The double to be formatted.
	 * @param decimalcount The amount of post period decimal places.
	 * @return The formatted double as a String.
	 */
	public static String formatDoubleForDecimalPlaces(double d, int decimalcount) {
		long intpredecimals = (long) d;
		double tenexp = Math.pow(10, decimalcount);
		double decimals = d - intpredecimals;
		double postdecimals = decimals * (long) tenexp;
		long intpostdecimals = (long) postdecimals;
		//round
		if ((postdecimals - intpostdecimals) >= 0.5) {
			intpostdecimals++;
		}
		return intpredecimals + "." + intpostdecimals;
	}

}
