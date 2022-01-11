package edu.uncc.algorithm;

import edu.uncc.utility.*;

public class Backtracking {

	public static final String CSP_MINIMUM_REMAINING_VALUES = "MRV";
	public static final String CSP_DEGREE_HEURISTIC = "DEG";
	public static final String CSP_LEAST_CONSTRAINING_VALUES = "LCV";

	// solve
	public Calculation evaluate(CSP constraintSatisfactionProblem, String runWithHeuristic,
			boolean enableForwardChecks) {
		return searchWithBackTrack(constraintSatisfactionProblem, new Calculation(), runWithHeuristic,
				enableForwardChecks);
	}

	/**
	 * Template method, which can be configured by overriding the three primitive
	 * operations below.
	 */
	private Calculation searchWithBackTrack(CSP constraintSatisfactionProblem,
			Calculation calculation, String runWithHeuristic, boolean enableForwardChecks) {

		Calculation cal = null;

		if (calculation.areAllValuesSatisfied(constraintSatisfactionProblem.getLiterals())) {
			cal = calculation;
			System.out.println(cal);
		} else {

			boolean use_MRV = false;
			boolean use_DEG = false;
			boolean use_LCV = false;
			boolean no_Selection = false;

			if (runWithHeuristic.equalsIgnoreCase(CSP_MINIMUM_REMAINING_VALUES)) {
				use_MRV = true;
			} else if (runWithHeuristic.equalsIgnoreCase(CSP_DEGREE_HEURISTIC)) {
				use_DEG = true;
				use_MRV = true;
			} else if (runWithHeuristic.equalsIgnoreCase(CSP_LEAST_CONSTRAINING_VALUES)) {
				use_LCV = true;
			} else {
				// no heuristic
				no_Selection = true;
			}

			Literal literal = null;
			BacktrackingWithHeuristic backtrackingWithHeuristic = null;

			if (no_Selection) {
				literal = chooseLiteralWithNoValue(calculation, constraintSatisfactionProblem);
			} else {

				backtrackingWithHeuristic = new BacktrackingWithHeuristic(use_MRV, use_DEG, false, use_LCV,
						enableForwardChecks);
				literal = backtrackingWithHeuristic.chooseLiteralWithNoValue(calculation,
						constraintSatisfactionProblem);

			}
			for (Object value : scopeDataOrdering(literal, calculation, constraintSatisfactionProblem)) {

				calculation.assignCalculatedValue(literal, value);

				if (calculation.checkForValidityAgainstRules(constraintSatisfactionProblem.getRules(literal))) {

					ScopeData scopeData = null;

					if (no_Selection) {
						scopeData = operateInference(literal, calculation, constraintSatisfactionProblem);
					} else {
						scopeData = backtrackingWithHeuristic.operateInference(literal, calculation,
								constraintSatisfactionProblem);
					}

					if (!scopeData.getScopeDataWithNoValueFlag()) {
						cal = searchWithBackTrack(constraintSatisfactionProblem, calculation, runWithHeuristic,
								enableForwardChecks);
						if (cal != null) {
							break;
						}
					}

					scopeData.restoreDomains(constraintSatisfactionProblem);
				}
				calculation.addBTCount();
				calculation.deleteCalculatedValue(literal);
			}
		}
		return cal;
	}

	// selectUnassignedVar
	protected Literal chooseLiteralWithNoValue(Calculation calculation,
			CSP constraintSatisfactionProblem) {
		for (Literal literal : constraintSatisfactionProblem.getLiterals()) {
			if (!(calculation.isCalculatedExists(literal))) {
				return literal;
			}
		}
		return null;
	}

	// orderDomainValues
	protected Iterable<?> scopeDataOrdering(Literal literal, Calculation calculation,
			CSP constraintSatisfactionProblem) {
		return constraintSatisfactionProblem.getScope(literal);
	}

	// inference
	protected ScopeData operateInference(Literal literal, Calculation calculation,
			CSP constraintSatisfactionProblem) {
		return new ScopeData().resetChangedValues();
	}

	// @Override
	public Calculation calculate(CSP constraintSatisfactionProblem) {
		return null;
	}

}