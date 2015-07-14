/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.util;

import java.util.Comparator;

import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Comparator for Comparing ArrivalRateTuples based on their time stamps.
 * 
 * @author Joakim von Kistowski
 *
 */
public final class ArrivalRateTimeStampComparator implements Comparator<ArrivalRateTuple> {

	private static final double EPSILON = 0.0000001;
	
	/**
	 * Default Comparator Instance.
	 */
	public static final ArrivalRateTimeStampComparator INSTANCE = new ArrivalRateTimeStampComparator();
	
	/**
	 * Private constructor to disallow external instantiation.
	 */
	private ArrivalRateTimeStampComparator() {
	}
	
	@Override
	public int compare(ArrivalRateTuple o1, ArrivalRateTuple o2) {
		if (Math.abs(o1.getTimeStamp() - o2.getTimeStamp()) < EPSILON) {
			return 0;
		} else if (o1.getTimeStamp() < o2.getTimeStamp()) {
			return -1;
		}
		return 1;
	}

}
