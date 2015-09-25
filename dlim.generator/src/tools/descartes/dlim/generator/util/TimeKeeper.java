/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.util;

import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.Function;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;

/**
 * Provides all logic necessary to set the derived time attributes within
 * Sequences and Elements.
 *
 * @author Joakim von Kistowski
 *
 */
public final class TimeKeeper {

	/**
	 * It's all static anyway.
	 */
	private TimeKeeper() {

	}

	/**
	 * Calculates the Elements end Time and returns it. Elements containing
	 * sequences do not take their sequence duration into account.
	 *
	 * @param elem the elem
	 * @param startTime the start time
	 * @return The time at which the element ends
	 */
	public static double calculateTimeDependentFunctionContainerTime(
			TimeDependentFunctionContainer elem, Double startTime) {

		elem.setFirstIterationStart(startTime);
		elem.setFirstIterationEnd(startTime + elem.getDuration());

		// set possible child Sequences within ELement
		if (elem.getFunction() != null) {
			calculateSequenceTime(elem.getFunction(), startTime);
		}

		return elem.getFirstIterationEnd();
	}

	/**
	 * Calculates loop duration, finalDuration, start and end for Sequence.
	 * Returns end.
	 *
	 * @param f The Sequence
	 * @param startTime Time at which sequence starts
	 * @return The time at which the Sequence ends.
	 */
	public static double calculateSequenceTime(Function f, Double startTime) {
		// combinatorTimes??
		calculateCombinatorFunctionTimes(f, startTime);

		if (!(f instanceof Sequence)) {
			return 0.0;
		}
		Sequence seq = (Sequence) f;

		seq.setFirstIterationStart(startTime);

		// calculate loop time,
		// calculates all TimeDependentFunctionContainerTimes within the
		// sequence as well
		double currentTime = startTime;
		double loopDuration = 0;
		for (TimeDependentFunctionContainer elem : seq
				.getSequenceFunctionContainers()) {
			currentTime = calculateTimeDependentFunctionContainerTime(elem,
					currentTime);
			loopDuration += elem.getDuration();
		}
		seq.setLoopDuration(loopDuration);

		// set Final Duration
		if (seq.getTerminateAfterLoops() < 0 && seq.getTerminateAfterTime() <= 0) {
			seq.setFinalDuration(Double.MAX_VALUE);
		} else if (seq.getTerminateAfterLoops() < 0) {
			seq.setFinalDuration(seq.getTerminateAfterTime());
		} else if (seq.getTerminateAfterTime() <= 0) {
			seq.setFinalDuration(seq.getTerminateAfterLoops()
					* seq.getLoopDuration());
		} else {
			seq.setFinalDuration(Math.min(
					seq.getTerminateAfterLoops() * seq.getLoopDuration(),
					seq.getTerminateAfterTime()));
		}

		// end from final duration, return end
		seq.setFirstIterationEnd(seq.getFirstIterationStart()
				+ seq.getFinalDuration());
		return seq.getFirstIterationEnd();
	}

	/*
	 * Calculate times for all Functions contained in f's Combinators.
	 */
	private static void calculateCombinatorFunctionTimes(Function f,
			Double startTime) {
		for (Combinator comb : f.getCombine()) {
			if (comb.getFunction() != null) {
				calculateSequenceTime(comb.getFunction(), startTime);
			}
		}
	}
}
