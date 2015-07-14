/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator;

/**
 * A container class containing an arrival rate and its time-stamp. Also offers
 * some utilities.
 *
 * @author Joakim von Kistowski
 */
public class ArrivalRateTuple implements Comparable<ArrivalRateTuple> {

	/**
	 * Sets the global sorting mode (if sorted by time or arrival rate).
	 */
	private static boolean sortByTime = false;

	/**
	 * The tuple's time stamp.
	 */
	private double timeStamp;

	/**
	 * The tuple's arrival rate.
	 */
	private double arrivalRate;

	/**
	 * Create a new tuple of arrival rate and its time-stamp.
	 *
	 * @param timeStamp the time stamp
	 * @param arrivalRate the arrival rate
	 */
	public ArrivalRateTuple(double timeStamp, double arrivalRate) {
		this.timeStamp = timeStamp;
		this.arrivalRate = arrivalRate;
	}

	/**
	 * Get the time-difference between two arrival rate tuples. Returns
	 * time-stamp/2 if null is passed.
	 *
	 * @param t the t
	 * @return the step
	 */
	public double getStep(ArrivalRateTuple t) {
		if (t == null) {
			return timeStamp * 2;
		}
		return Math.abs(t.getTimeStamp() - timeStamp);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(arrivalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(timeStamp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ArrivalRateTuple other = (ArrivalRateTuple) obj;
		if (Double.doubleToLongBits(arrivalRate) != Double
				.doubleToLongBits(other.arrivalRate)) {
			return false;
		}
		if (Double.doubleToLongBits(timeStamp) != Double
				.doubleToLongBits(other.timeStamp)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public double getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp the new time stamp
	 */
	public void setTimeStamp(double timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the arrival rate.
	 *
	 * @return the arrival rate
	 */
	public double getArrivalRate() {
		return arrivalRate;
	}

	/**
	 * Sets the arrival rate.
	 *
	 * @param arrivalRate the new arrival rate
	 */
	public void setArrivalRate(double arrivalRate) {
		this.arrivalRate = arrivalRate;
	}

	/**
	 * Checks if arrival rate tuples are sorted by time or arrival rate. This is
	 * a static global setting!
	 *
	 * @return true, if is sort by time
	 */
	public static boolean isSortByTime() {
		return sortByTime;
	}

	/**
	 * Set whether arrival rate tuples are to be sortet by time or arrival rate.
	 * This is a static global setting!
	 *
	 * @param sortByTime the new sort by time
	 */
	public static void setSortByTime(boolean sortByTime) {
		ArrivalRateTuple.sortByTime = sortByTime;
	}

	/**
	 * Compares two arrival rate tuples within one another. Uses either
	 * time-stamp or arrival rate based on isSortByTime().
	 *
	 * @param o the o
	 * @return the int
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ArrivalRateTuple o) {
		if (sortByTime) {
			if (timeStamp < o.getTimeStamp()) {
				return -1;
			} else if (timeStamp > o.getTimeStamp()) {
				return 1;
			}
		} else {
			if (arrivalRate < o.getArrivalRate()) {
				return -1;
			} else if (arrivalRate > o.getArrivalRate()) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Returns a simple output String for the arrival rate tuple.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return timeStamp + "," + arrivalRate + ";";
	}

	/**
	 * Clones the current arrival rate tuple.
	 */
	@Override
	public ArrivalRateTuple clone() {
		return new ArrivalRateTuple(this.timeStamp, this.arrivalRate);
	}
}
