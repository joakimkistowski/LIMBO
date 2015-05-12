/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator;

/**
 * Provides constants for the different sampling modes.
 *
 * @author Joakim von Kistowski
 *
 */
public interface IGeneratorConstants {

	/**
	 * The time-stamp generator is not to be executed. No time stamps are to be
	 * produced.
	 */
	public static final String NOTIMESTAMPS = "dlim:noTimeStamps";

	/**
	 * Time-stamps are to be equally distributed within each sampled arrival
	 * rate interval.
	 */
	public static final String EQUALDISTANCESAMPLING = "dlim:equal";

	/**
	 * Time-stamps are to be randomly generated within each sampled arrival rate
	 * interval.
	 */
	public static final String UNIFORMDISTRIBUTIONSAMPLING = "dlim:uniform";

	/**
	 * Noises are to return 0.
	 */
	public static final String CALIBRATION = "dlim:calibration";

	/**
	 * Noises are to be evaluated.
	 */
	public static final String EVALUATION = "dlim:evaluation";

	/**
	 * The default Random Seed.
	 */
	public static final int DEFAULTRANDOMSEED = 5;

}
