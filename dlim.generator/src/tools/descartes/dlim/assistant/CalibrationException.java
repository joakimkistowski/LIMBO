/*******************************************************************************
* Copyright (c) 2014 Joakim von Kistowski
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package tools.descartes.dlim.assistant;

/**
 * Is thrown during errors in calibration of interpolated Functions (i.e. Trends
 * and Bursts).
 *
 * @author Joakim von Kistowski
 *
 */
public class CalibrationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 529028740127235542L;

	// The message as set during calibration
	private String message;

	/**
	 * Create an exception that results from an error during calibration.
	 *
	 * @param message
	 *            This message will be displayed to the user.
	 */
	public CalibrationException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Get the description of the error.
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
