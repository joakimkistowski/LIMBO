/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import tools.descartes.dlim.Combinator;
import tools.descartes.dlim.Function;
import tools.descartes.dlim.Operator;
import tools.descartes.dlim.ReferenceClockObject;
import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.TimeDependentFunctionContainer;
import tools.descartes.dlim.UnivariateFunction;

/**
 * Provides utility methods for model evaluation.
 *
 * @author Joakim von Kistowski
 *
 */
public final class ModelEvaluatorUtil {

	// This is set by the model evaluator at the beginning of evaluation.
	// While the ModelEvaluatorUtil is capable of deriving this itself,
	// storing it leads to a significantly improved performance.
	private static Sequence rootSequence;

	/**
	 * It's all static anyways.
	 */
	private ModelEvaluatorUtil() {

	}

	// use this for better performance then getRootSequence(EObject
	// modelElement)
	private static Sequence getRootSequence() {
		return rootSequence;
	}

	/**
	 * Sets the rootSequence, referenced by the utilities.
	 *
	 * @param rootSequence the new root sequence
	 */
	public static void setRootSequence(Sequence rootSequence) {
		ModelEvaluatorUtil.rootSequence = rootSequence;
	}

	/**
	 * Returns the Operator's neutral element.
	 *
	 * @param op the op
	 * @return 1, for multiplication; 0, otherwise.
	 */
	public static double neutralElement(Operator op) {
		if (op == null) {
			return 0.0;
		} else if (op.getLiteral().equals("DIV")
				|| op.getLiteral().equals("MULT")) {
			return 1.0;
		} else {
			return 0.0;
		}
	}

	/**
	 * Returns the Function's duration depending on ReferenceClockType and
	 * PointOfReferenceClockObject of the containing Element.
	 *
	 * @param f the f
	 * @return the function duration
	 */
	public static double getFunctionDuration(Function f) {
		// return Parent Element Duration
		if (getParentElement(f) != null) {
			if (getParentElement(f).getPointOfReferenceClockType().getLiteral()
					.contains("CONTAINERCLOCK")) {
				return getParentElement(f).getDuration();
			} else if (getParentElement(f).getPointOfReferenceClockType()
					.getLiteral().contains("SEQCLOCK")) {
				if (getParentElement(f).getPointOfReferenceClockObject() == null) {
					return getParentSequence(getParentElement(f))
							.getFinalDuration();
				} else {
					return getParentSequence(
							getParentElement(f)
							.getPointOfReferenceClockObject())
							.getFinalDuration();
				}
			} else if (getParentElement(f).getPointOfReferenceClockType()
					.getLiteral().contains("LOOPCLOCK")) {
				if (getParentElement(f).getPointOfReferenceClockObject() == null) {
					return getParentSequence(getParentElement(f))
							.getLoopDuration();
				} else {
					return getParentSequence(
							getParentElement(f)
							.getPointOfReferenceClockObject())
							.getLoopDuration();
				}
			} else {
				return getRootSequence().getFinalDuration();
			}
			// return parent Function Duration
		} else if (getParentCombinator(f) != null) {
			return getFunctionDuration(getParentFunction(getParentCombinator(f)));
			// return Root Sequence Duration if this function is the root
			// sequence
		} else if (f instanceof Sequence) {
			return ((Sequence) f).getFinalDuration();
		} else if (f.eContainer() != null
				&& f.eContainer() instanceof UnivariateFunction) {
			return getFunctionDuration((Function) f.eContainer());
		} else {
			return 0.0;
		}
	}

	/**
	 * Returns the "start" attribute of a parent Element or Sequence, depending
	 * on the parent Element's referenceClock.
	 *
	 * @param f the f
	 * @return the function begin
	 */
	public static double getFunctionBegin(Function f) {
		if (f.eContainer() == null) {
			// begin of rootSequence
			return 0.0;
		}
		// get referenceClock
		if (getParentElement(f) != null) {
			TimeDependentFunctionContainer parentElement = getParentElement(f);
			if (parentElement.getPointOfReferenceClockType().getLiteral()
					.contains("CONTAINERCLOCK")) {
				return parentElement.getFirstIterationStart();
			} else if (parentElement.getPointOfReferenceClockType()
					.getLiteral().contains("LOOPCLOCK")
					|| parentElement.getPointOfReferenceClockType()
					.getLiteral().contains("SEQCLOCK")) {
				if (parentElement.getPointOfReferenceClockObject() == null) {
					return getParentSequence(parentElement)
							.getFirstIterationStart();
				} else {
					return getParentSequence(
							parentElement.getPointOfReferenceClockObject())
							.getFirstIterationStart();
				}
			}
		}
		if (getParentCombinator(f) != null) {
			return getFunctionBegin(getParentFunction(getParentCombinator(f)));
		} else if (f.eContainer() != null
				&& f.eContainer() instanceof UnivariateFunction) {
			return getFunctionBegin((Function) f.eContainer());
		}

		// begin of rootSequence
		return 0.0;
	}

	// returns the direct parent of f, if it is an Element
	// returns null otherwise
	private static TimeDependentFunctionContainer getParentElement(Function f) {
		EObject parent = f.eContainer();
		if (parent != null && parent instanceof TimeDependentFunctionContainer) {
			return (TimeDependentFunctionContainer) parent;
		}
		return null;
	}

	/**
	 * Returns the direct parent of f, if it is a Combinator. Otherwise: returns
	 * null.
	 *
	 * @param f the f
	 * @return the parent combinator
	 */
	public static Combinator getParentCombinator(Function f) {
		EObject parent = f.eContainer();
		if (parent != null && parent instanceof Combinator) {
			return (Combinator) parent;
		}
		return null;
	}

	// returns the direct parent of c, if it is a Function
	// returns null otherwise
	private static Function getParentFunction(Combinator c) {
		EObject parent = c.eContainer();
		if (parent != null && parent instanceof Function) {
			return (Function) parent;
		}
		return null;
	}

	/**
	 * Checks recursively, weather the part of the tree contained by node
	 * contains leaf.
	 *
	 * @param node the node
	 * @param leaf the leaf
	 * @return true, if successful
	 */
	public static boolean containsInTree(EObject node, EObject leaf) {
		if (leaf.eContainer() == null) {
			return false;
		}
		if (leaf.eContainer().equals(node)) {
			return true;
		} else {
			return containsInTree(node, leaf.eContainer());
		}
	}

	/**
	 * Returns the Sequence containing clock.
	 *
	 * @param clock the clock
	 * @return the parent sequence
	 */
	public static Sequence getParentSequence(ReferenceClockObject clock) {
		EObject parent = clock.eContainer();
		return (Sequence) parent;
	}

	/**
	 * Returns the direct parent of e, if it is a Sequence. Otherwise: returns
	 * null.
	 *
	 * @param e the e
	 * @return the parent sequence
	 */
	public static Sequence getParentSequence(TimeDependentFunctionContainer e) {
		EObject parent = e.eContainer();
		if (parent != null && parent instanceof Sequence) {
			return (Sequence) parent;
		}
		return null;
	}

	/**
	 * Returns the root Container of the modelElement, if it is a Sequence.
	 * Returns null otherwise.
	 *
	 * @param modelElement the model element
	 * @return the root sequence
	 */
	public static Sequence getRootSequence(EObject modelElement) {
		if (modelElement.eContainer() == null
				&& modelElement instanceof Sequence) {
			return (Sequence) modelElement;
		} else if (modelElement.eContainer() != null) {
			return getRootSequence(modelElement.eContainer());
		}
		return null;
	}
	
	/**
	 * Checks and returns true, if the model contains and uses a
	 * ReferenceClockObject.
	 * @param root The model's root
	 * @return True, if at least one Sequence in the model contains a
	 * 			ReferenceClockObject.
	 */
	public static boolean containsReferenceClock(Function root) {
		List<Function> functions = new ArrayList<Function>();
		
		if (root instanceof Sequence) {
			Sequence sequence = (Sequence) root;
			if (sequence.getReferenceClock() != null) {
				return true;
			}
			for (TimeDependentFunctionContainer tdfc : sequence.getSequenceFunctionContainers()) {
				Function f = tdfc.getFunction();
				if (f != null) {
					functions.add(f);
				}
			}
		}
		for (Combinator c : root.getCombine()) {
			Function combF = c.getFunction();
			if (combF != null) {
				functions.add(c.getFunction());
			}
		}
		
		if (functions.isEmpty()) {
			return false;
		} else {
			boolean contains = false;
			for (Function f : functions) {
				contains = contains || containsReferenceClock(f);
			}
			return contains;
		}
	}
}
