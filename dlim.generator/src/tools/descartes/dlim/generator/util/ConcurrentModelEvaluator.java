/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.util;

import java.util.ArrayList;

import tools.descartes.dlim.generator.ArrivalRateTuple;
import tools.descartes.dlim.generator.ModelEvaluator;

/**
 * Class for creating arrival rate lists from a DLIM instance by concurrently evaluating
 * multiple regions of the model.
 * @author Joakim von Kistowski
 *
 */
public class ConcurrentModelEvaluator {

	private ModelEvaluator evaluator;
	private int threadCount;
	private double max = 0.0;
	
	/**
	 * Creates a new ConcurrentModelEvaluator.
	 * @param evaluator The default evaluator, characterizing the model.
	 */
	public ConcurrentModelEvaluator(ModelEvaluator evaluator) {
		this(evaluator, Runtime.getRuntime().availableProcessors());
	}
	
	/**
	 * Creates a new ConcurrentModelEvaluator.
	 * @param evaluator The default evaluator, characterizing the model.
	 * @param threadCount The amount of threads to be used.
	 */
	public ConcurrentModelEvaluator(ModelEvaluator evaluator, int threadCount) {
		this.evaluator = evaluator;
		this.threadCount = threadCount;
	}
	
	/**
	 * Evaluates the model for the specified
	 * time interval and stores the result in a list.
	 * @param startTime The time to start evaluation (inclusive).
	 * @param endTime The time to end evaluation (exclusive).
	 * @param step The sampling steps.
	 * @return A list with the arrival rate tuples.
	 */
	public ArrayList<ArrivalRateTuple> evaluateForTimeStamps(double startTime, double endTime, double step) {
		max = 0.0;
		ArrayList<ArrivalRateTuple> tuples = new ArrayList<ArrivalRateTuple>();
		
		//ReferenceClockObjects make a model state-ful and require sequential execution.
		if (evaluator.containsReferenceClockObject()) {
			return evaluateForTimeStampsSequentially(startTime, endTime, step);
		}
		try {
			EvaluationThread[] threads = new EvaluationThread[threadCount];
			
			//always round up a little, to make sure we never fall short
			int stepcount = (int) ((endTime - startTime) / step) + 1;
			int stepsPerThread = (stepcount / threadCount) + 1;
			double threadStartTime = startTime;
			for (int i = 0; i < threadCount; i++) {
				double threadEndTime = threadStartTime + step * stepsPerThread;
				
				//clamp the rounded up errors
				threadEndTime = Math.min(threadEndTime, endTime);
				threads[i] = new EvaluationThread(threadStartTime, threadEndTime, step);
				threads[i].start();
				threadStartTime = threadEndTime;
			}
			for (int i = 0; i < threadCount; i++) {
//				while (!threads[i].isDone()) {
//					//sleep
//					Thread.sleep(5);
//				}
					threads[i].join();
					
					if (threads[i].isDone()) {
						max = Math.max(max, threads[i].getMax());
					}
					tuples.addAll(threads[i].getArrivalRates());
			}
		} catch (InterruptedException e) {
			System.out.println("Cuncurrent Evaluation is broken, evaluating sequentially!");
			return evaluateForTimeStampsSequentially(startTime, endTime, step);
		}
		return tuples;
	}
	
	/**
	 * Evaluates the model for the specified
	 * time interval and stores the result in a list, forces sequential execution.
	 * @param startTime The time to start evaluation (inclusive).
	 * @param endTime The time to end evaluation (exclusive).
	 * @param step The sampling steps.
	 * @return A list with the arrival rate tuples.
	 */
	public ArrayList<ArrivalRateTuple> evaluateForTimeStampsSequentially(double startTime,
			double endTime, double step) {
		ArrayList<ArrivalRateTuple> tuples = new ArrayList<ArrivalRateTuple>();
			for (double time = startTime; time < endTime; time += step) {
				double y = evaluator.getArrivalRateAtTime(time);
				max = Math.max(y, max);
				tuples.add(new ArrivalRateTuple(time, y));
			}
		return tuples;
	}
	
	/**
	 * Returns the maximum arrival rate, as determined during the last call
	 * of evaluateForTimeStamps().
	 * @return The maximum arrival rate.
	 */
	public double getMax() {
		return max;
	}
	
	/**
	 * Thread for evaluation of multiple arrival rate tuples.
	 * @author Joakim von Kistowski
	 *
	 */
	private class EvaluationThread extends Thread {
		
		private volatile ArrayList<ArrivalRateTuple> tuples = new ArrayList<ArrivalRateTuple>();
		private volatile boolean done = false;
		
		private double startTime;
		private double endTime;
		private double step;
		
		private double max;
		/**
		 * Create a new evaluation thread. Evaluates the model for the specified
		 * time interval and stores the result in a list.
		 * @param startTime The time to start evaluation (inclusive).
		 * @param endTime The time to end evaluation (exclusive).
		 * @param step The sampling steps.
		 */
		public EvaluationThread(double startTime, double endTime, double step) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.step = step;
		}
		
		/**
		 * Gets the arrival rate tuples.
		 * Tuples are written through to memory for multi-threaded access.
		 * @return The arrival rate tuples.
		 */
		public ArrayList<ArrivalRateTuple> getArrivalRates() {
			return tuples;
		}
		
		/**
		 * Runs the thread to and evaluates the model values within the specified interval.
		 * Writes results to a list to be accessed using getArrivalRates().
		 * isDone() is set to true, once the thread has finished.
		 */
		@Override
		public void run() {
			for (double time = startTime; time < endTime; time += step) {
				double y = evaluator.getArrivalRateAtTime(time);
				tuples.add(new ArrivalRateTuple(time, y));
				max = Math.max(y, max);
			}
			done = true;
		}
		
		/**
		 * Indicates that the thread has finished.
		 * @return True, if the thread has finished.
		 */
		public boolean isDone() {
			return done;
		}
		
		/**
		 * Returns the maximum arrival rate, determined during last thread execution.
		 * @return The max arrival rate.
		 */
		public synchronized double getMax() {
			return max;
		}
	}
}
