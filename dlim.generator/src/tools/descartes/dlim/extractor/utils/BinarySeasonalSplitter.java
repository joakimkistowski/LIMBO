/*******************************************************************************
 * Copyright (c) 2015 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.extractor.utils;

import java.util.ArrayList;
import java.util.List;

import tools.descartes.dlim.DlimPackage;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.assistant.CalibrationException;
import tools.descartes.dlim.generator.ArrivalRateTuple;

/**
 * Splits the given arrival rate list in half.
 * Determines if the two halfs are different enough to warrant
 * separate seasonal patterns using a passed rule.
 * Then creates those patterns and writes them to a root sequence.
 * 
 * @author Joakim von Kistowski
 * 
 */
public class BinarySeasonalSplitter {

	private static int globalPartialSeasonalsCounter = 0;
	private static ExtractionDataContainer globalContainer;
	
	private List<ArrivalRateTuple> left = new ArrayList<ArrivalRateTuple>();
	private List<ArrivalRateTuple> right = new ArrayList<ArrivalRateTuple>();
	private ExtractionDataContainer cleft;
	private ExtractionDataContainer cright;
	private ExtractionDataContainer container;
	private double splitTime;
	
	/**
	 * Creates a new Splitter, based off an existing arrival rate list.
	 * @param container The extraction data that has been collected so far.
	 */
	public BinarySeasonalSplitter(ExtractionDataContainer container) {
		this.container = container;
		splitArrivalRateList();
	}
	
	/**
	 * Splits the total arrival rate list into left and right half.
	 */
	private void splitArrivalRateList() {
		//find the time point of the split
		splitTime = calcSplitTime();
		splitArrivalRateListInLeftAndRight(container.getArrivalRateList(),
				left, right, splitTime);
		
		//Clone container, put arrival rate list into container.
		cleft = container.clone();
		cleft.setArrivalRateList(left);
		cleft.setDuration(splitTime);
		cleft.setBeginTime(container.getBeginTime());
		//BasicSeasonalExtractionUtilities.performMinMaxSearch(cleft);
		cright = container.clone();
		cright.setArrivalRateList(right);
		cright.setDuration(container.getDuration() - splitTime);
		cright.setBeginTime(container.getBeginTime() + splitTime);
		//BasicSeasonalExtractionUtilities.performMinMaxSearch(cright);
//		cleft.getLocalMaxes().clear();
//		cleft.getLocalMins().clear();
//		cright.getLocalMaxes().clear();
//		cright.getLocalMins().clear();
		ArrayList<ArrivalRateTuple> localMaxesRight = new ArrayList<ArrivalRateTuple>();
		ArrayList<ArrivalRateTuple> localMaxesLeft = new ArrayList<ArrivalRateTuple>();
		ArrayList<ArrivalRateTuple> localMinsRight = new ArrayList<ArrivalRateTuple>();
		ArrayList<ArrivalRateTuple> localMinsLeft = new ArrayList<ArrivalRateTuple>();
		splitArrivalRateListInLeftAndRight(container.getLocalMaxes(),
				localMaxesLeft, localMaxesRight, splitTime);
		splitArrivalRateListInLeftAndRight(container.getLocalMins(),
				localMinsLeft, localMinsRight, splitTime);
		cleft.setLocalMins(localMinsLeft);
		cleft.setLocalMaxes(localMaxesLeft);
		cright.setLocalMins(localMinsRight);
		cright.setLocalMaxes(localMaxesRight);
	}
	
	/**
	 * Calculates the time of the split. It is located at the break between two
	 * seasonal iterations, which is closest to the middle of the load profile.
	 * @return The split time. 0 if an error occurs.
	 */
	private double calcSplitTime() {
		double splitTime = container.getDuration() / 2.0;
		int seasonalcount = (int) (splitTime / container.getPeriod());
//		double seasonals = 0;
//		while (seasonals < splitTime) {
//			double nextSeasonals = seasonals + container.getPeriod();
//			if (seasonals <= splitTime && nextSeasonals > splitTime) {
//				if ((splitTime - seasonals) <= (nextSeasonals - splitTime)) {
//					return seasonals;
//				} else {
//					return nextSeasonals;
//				}
//			}
//			seasonals = nextSeasonals;
//		}
//		return 0;
		return container.getPeriod() * (double) seasonalcount;
	}
	
	/**
	 * Creates one or two seasonal patterns, depending on the splitting rule.
	 * Appends the created patterns to the back of the root sequence.
 	 * @param root Resulting seasonal patterns are written into this Sequence.
 	 * @param baseline Resulting patterns for burst detection are written into this Sequence.
 	 * @param splittingRule The rule for deciding if the arrival rate list needs splitting.
	 * @throws CalibrationException 
	 */
	public void appendSplitsToRoot(Sequence root, Sequence baseline, ISplittingRule splittingRule)
			throws CalibrationException {
		//evaluate splitting rule
		if (splittingRule.warrantSplit(cleft, cright)) {

			//Recursive splitting of the new halves
			BinarySeasonalSplitter leftsplitter = new BinarySeasonalSplitter(cleft);
			BinarySeasonalSplitter rightsplitter = new BinarySeasonalSplitter(cright);
			leftsplitter.appendSplitsToRoot(root, baseline, splittingRule);
			rightsplitter.appendSplitsToRoot(root, baseline, splittingRule);
			
			//no splitting
		} else {
			Sequence[] partials = createPartialSeasonalIteration(container);
			globalContainer.getSubContainerList().add(container);
			appendPartialSeasonalIteration(root, baseline, partials[0], partials[1]);
		}
	}
	
	/**
	 * Splits the arrival rate list and writes the results into left and right parts.
	 * The "middle" arrival rate tuple appears in both lists.
	 * @param original The original list. Remains unchanged.
	 * @param left The left hand side list.
	 * @param right The right hand side list, time stamps are decremented.
	 * @param splitTime The first arrival rate tuple >= this point appears in both lists.
	 */
	private void splitArrivalRateListInLeftAndRight(List<ArrivalRateTuple> original,
			List<ArrivalRateTuple> left, List<ArrivalRateTuple> right, double splitTime) {
		boolean copyleft = true;
		for (ArrivalRateTuple t : original) {
			if (copyleft) {
				left.add(t);
			}
			if (t.getTimeStamp() >= splitTime) {
				copyleft = false;
				ArrivalRateTuple rightTuple = t.clone();
				rightTuple.setTimeStamp(rightTuple.getTimeStamp() - splitTime);
				right.add(rightTuple);
			}
		}
	}
	
	/**
	 * Extracts partial Seasonal Iterations, based off of the arrival rate list in the
	 * passed partialContainer,  for the actual seasonal pattern and burst detection baseline.
	 * returns the seasonal at index 0, and the burst baseline at index 1. 
	 */
	private Sequence[] createPartialSeasonalIteration(ExtractionDataContainer partialContainer)
			throws CalibrationException {
		Sequence[] seqs = new Sequence[2];
		//root
		seqs[0] = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		seqs[0].setName("Seasonal" + globalPartialSeasonalsCounter);
		//baseline
		seqs[1] = DlimPackage.eINSTANCE.getDlimFactory().createSequence();
		seqs[1].setName("SeasonalBaseline" + globalPartialSeasonalsCounter++);
		BasicSeasonalExtractionUtilities.extractSeasonalPart(seqs[0], seqs[1], partialContainer);
		return seqs;
	}
	
	/**
	 * Appends the partial seasonal sequences to their respective model root.
	 */
	private void appendPartialSeasonalIteration(Sequence root, Sequence baseline,
			Sequence seasonalroot, Sequence seasonalbaseline) {
		TimeDependentFunctionContainer stdfc = createTDFC(seasonalroot);
		TimeDependentFunctionContainer btdfc = createTDFC(seasonalbaseline);

		root.getSequenceFunctionContainers().add(stdfc);
		baseline.getSequenceFunctionContainers().add(btdfc);
		root.setTerminateAfterTime(0);
		root.setTerminateAfterLoops(1);
		baseline.setTerminateAfterTime(0);
		baseline.setTerminateAfterLoops(1);
	}
	
	/**
	 * Creates a TimeDependentFunctionContainer to contain the given Sequence.
	 */
	private TimeDependentFunctionContainer createTDFC(Sequence seasonalSeq) {
		TimeDependentFunctionContainer stdfc =
				DlimPackage.eINSTANCE.getDlimFactory().createTimeDependentFunctionContainer();
		stdfc.setDuration(seasonalSeq.getTerminateAfterTime());
		stdfc.setName(seasonalSeq.getName());
		stdfc.setFunction(seasonalSeq);
		return stdfc;
	}
	
	/**
	 * Resets the global count of splits. The counter is only used for naming.
	 */
	public static void resetBinarySplittingCount() {
		globalPartialSeasonalsCounter = 0;
	}

	/**
	 * Get the global Container for containing the original arrival rate list.
	 * @return The global container.
	 */
	public static ExtractionDataContainer getGlobalContainer() {
		return globalContainer;
	}

	/**
	 * Set the global Container for containing the original arrival rate list.
	 * @param globalContainer The global container.
	 */
	public static void setGlobalContainer(ExtractionDataContainer globalContainer) {
		BinarySeasonalSplitter.globalContainer = globalContainer;
	}
}
