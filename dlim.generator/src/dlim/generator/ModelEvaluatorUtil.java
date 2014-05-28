package dlim.generator;

import org.eclipse.emf.ecore.EObject;

import dlim.Combinator;
import dlim.Function;
import dlim.Operator;
import dlim.ReferenceClockObject;
import dlim.Sequence;
import dlim.TimeDependentFunctionContainer;
import dlim.UnivariateFunction;

/**
 * Provides utility methods for model evaluation.
 * @author Jóakim G. v. Kistowski
 *
 */
public class ModelEvaluatorUtil {

	//This is set by the model evaluator at the beginning of evaluation.
	//While the ModelEvaluatorUtil is capable of deriving this itself,
	//storing it leads to a significantly improved performance.
	private static Sequence rootSequence;
	
	//use this for better performance then getRootSequence(EObject modelElement)
	private static Sequence getRootSequence() {
		return rootSequence;
	}

	/**
	 * Sets the rootSequence, referenced by the utilities.
	 * @param rootSequence
	 */
	public static void setRootSequence(Sequence rootSequence) {
		ModelEvaluatorUtil.rootSequence = rootSequence;
	}
	
	/**
	 * Returns the Operator's neutral element.
	 * @param op
	 * @return 1, for multiplication; 0, otherwise.
	 */
	public static double neutralElement(Operator op) {
		if (op == null) {
			return 0.0;
		} else if (op.getLiteral().equals("DIV")||op.getLiteral().equals("MULT")) {
			return 1.0;
		} else {
			return 0.0;
		}
	}
	
	/**
	 * Returns the Function's duration depending on ReferenceClockType
	 * and PointOfReferenceClockObject of the containing Element.
	 * @param f
	 * @return
	 */
	public static double getFunctionDuration(Function f) {
		//return Parent Element Duration
		if (getParentElement(f) != null) {
			if (getParentElement(f).getPointOfReferenceClockType().getLiteral().contains("CONTAINERCLOCK")) {
				return getParentElement(f).getDuration();
			} else if (getParentElement(f).getPointOfReferenceClockType().getLiteral().contains("SEQCLOCK")) {
				if(getParentElement(f).getPointOfReferenceClockObject() == null) {
					return getParentSequence(getParentElement(f)).getFinalDuration();
				} else {
					return getParentSequence(getParentElement(f).getPointOfReferenceClockObject()).getFinalDuration();
				}
			} else if (getParentElement(f).getPointOfReferenceClockType().getLiteral().contains("LOOPCLOCK")) {
				if(getParentElement(f).getPointOfReferenceClockObject() == null) {
					return getParentSequence(getParentElement(f)).getLoopDuration();
				} else {
					return getParentSequence(getParentElement(f).getPointOfReferenceClockObject()).getLoopDuration();
				}
			} else {
				return getRootSequence().getFinalDuration();
			}
		//return parent Function Duration
		} else if (getParentCombinator(f) != null) {
			return getFunctionDuration(getParentFunction(getParentCombinator(f)));
		//return Root Sequence Duration if this function is the root sequence
		} else if (f instanceof Sequence) {
			return ((Sequence)f).getFinalDuration();
		} else if (f.eContainer() != null && f.eContainer() instanceof UnivariateFunction) {
			return getFunctionDuration((Function)f.eContainer());
		} else {
			return 0.0;
		}
	}
	
	/**
	 * Returns the "start" attribute of a parent Element or Sequence,
	 * depending on the parent Element's referenceClock.
	 * @param f
	 * @return
	 */
	public static double getFunctionBegin(Function f) {
		if (f.eContainer() == null) {
			//begin of rootSequence
			return 0.0;
		}
		//get referenceClock
		if (getParentElement(f) != null) {
			TimeDependentFunctionContainer parentElement = getParentElement(f);
			if (parentElement.getPointOfReferenceClockType().getLiteral().contains("CONTAINERCLOCK")) {
				return parentElement.getFirstIterationStart();
			} else if (parentElement.getPointOfReferenceClockType().getLiteral().contains("LOOPCLOCK")
					|| parentElement.getPointOfReferenceClockType().getLiteral().contains("SEQCLOCK")) {
				if(parentElement.getPointOfReferenceClockObject() == null) {
					return getParentSequence(parentElement).getFirstIterationStart();
				} else {
					return getParentSequence(parentElement.getPointOfReferenceClockObject()).getFirstIterationStart();
				}
			}
		}
		if (getParentCombinator(f) != null) {
			return getFunctionBegin(getParentFunction(getParentCombinator(f)));
		} else if (f.eContainer() != null && f.eContainer() instanceof UnivariateFunction) {
			return getFunctionBegin((Function)f.eContainer());
		}
		
		//begin of rootSequence
		return 0.0;
	}
	
	//returns the direct parent of f, if it is an Element
	//returns null otherwise
	private static TimeDependentFunctionContainer getParentElement(Function f) {
		EObject parent = f.eContainer();
		if (parent != null && parent instanceof TimeDependentFunctionContainer) {
			return (TimeDependentFunctionContainer)parent;
		}
		return null;
	}
	
	/**
	 * Returns the direct parent of f, if it is a Combinator.
	 * Otherwise: returns null.
	 * @param f
	 * @return
	 */
	public static Combinator getParentCombinator(Function f) {
		EObject parent = f.eContainer();
		if (parent != null && parent instanceof Combinator) {
			return (Combinator)parent;
		}
		return null;
	}
	
	//returns the direct parent of c, if it is a Function
	//returns null otherwise
	private static Function getParentFunction(Combinator c) {
		EObject parent = c.eContainer();
		if (parent != null && parent instanceof Function) {
			return (Function)parent;
		}
		return null;
	}
	
	/**
	 * Checks recursively, weather the part of the tree contained by node contains leaf.
	 * @param node
	 * @param leaf
	 * @return
	 */
	public static boolean containsInTree(EObject node, EObject leaf) {
		if (leaf.eContainer() == null) {
			return false;
		}
		if (leaf.eContainer().equals(node)) {
			return true;
		} else {
			return containsInTree(node,leaf.eContainer());
		}
	}
	
	/**
	 * Returns the Sequence containing clock.
	 * @param clock
	 * @return
	 */
	public static Sequence getParentSequence(ReferenceClockObject clock) {
		EObject parent = (Sequence)clock.eContainer();
		return (Sequence)parent;
	}
	
	/**
	 * Returns the direct parent of e, if it is a Sequence.
	 * Otherwise: returns null.
	 * @param e
	 * @return
	 */
	public static Sequence getParentSequence(TimeDependentFunctionContainer e) {
		EObject parent = e.eContainer();
		if (parent != null && parent instanceof Sequence) {
			return (Sequence)parent;
		}
		return null;
	}
	
	/**
	 * Returns the root Container of the modelElement, if it is a Sequence.
	 * Returns null otherwise.
	 * @param modelElement
	 * @return
	 */
	public static Sequence getRootSequence(EObject modelElement) {
		if (modelElement.eContainer() == null && modelElement instanceof Sequence) {
			return (Sequence)modelElement;
		} else if (modelElement.eContainer() != null) {
			return getRootSequence(modelElement.eContainer());
		}
		return null;
	}
}
