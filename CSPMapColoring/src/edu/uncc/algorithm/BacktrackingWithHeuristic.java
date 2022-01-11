package edu.uncc.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import edu.uncc.utility.*;


public class BacktrackingWithHeuristic extends Backtracking {

	protected StrategySelection strategyTobeUsed = StrategySelection.USE_DEFAULT_ORDER;
    protected Inference strategyForInference = Inference.NONE;
    protected boolean use_LCV_status;

	public BacktrackingWithHeuristic(boolean use_MRV, boolean use_DEG, boolean activate_AC3, boolean user_LCV, boolean enableForwardChecks) {
		if (use_MRV) {
			setStrategySelection(use_DEG ? StrategySelection.USE_MRV_WITH_DEG : StrategySelection.USE_MRV);
		}
			
		if (enableForwardChecks) {
			setInferenceStrategy(Inference.CHECK_GOING_FORWARD);
		}
			
		checkIFLCVIsUsed(user_LCV);
	}

    //	setVariableSelection
    public void setStrategySelection(StrategySelection selectedStrategy) {
        strategyTobeUsed = selectedStrategy;
    }

    /**
     * Selects the algorithm for INFERENCE.
     */
    
    //	setInference
    public void setInferenceStrategy(Inference strategyForInference) {
        this.strategyForInference = strategyForInference;
    }

    /**
     * Selects the least constraining value heuristic as implementation for
     * ORDER-DOMAIN-VALUES.
     */
    //	isLCV
    public void checkIFLCVIsUsed(boolean use_LCV_status) {
        this.use_LCV_status = use_LCV_status;
    }

    /**
     * Starts with a constraint propagation if AC-3 is enabled and then calls
     * the super class implementation.
     */
    //	solve
    public Calculation calculate(CSP constraintSatisfactionProblem) {
        return super.calculate(constraintSatisfactionProblem);
    }

    /**
     * Primitive operation, selecting a not yet assigned variable.
     */
    @Override
    public Literal chooseLiteralWithNoValue(Calculation calculation, CSP constraintSatisfactionProblem) {
        switch (strategyTobeUsed) {
            case USE_MRV:
                return userMRVStrategy(constraintSatisfactionProblem, calculation).get(0);
            case USE_MRV_WITH_DEG:
                List<Literal> listOfLiterals = userMRVStrategy(constraintSatisfactionProblem, calculation);
                return useDEGStrategy(listOfLiterals, calculation, constraintSatisfactionProblem).get(0);
            default:
                for (Literal literal : constraintSatisfactionProblem.getLiterals()) {
                    if (!(calculation.isCalculatedExists(literal))) {
                    	return literal;
                    }
                }
        }
        return null;
    }

    @Override
    protected Iterable<?> scopeDataOrdering(Literal literal, Calculation calculation, CSP constraintSatisfactionProblem) {
        if (!use_LCV_status) {
            return constraintSatisfactionProblem.getScope(literal);
        } else {
            return useLCVStrategy(literal, constraintSatisfactionProblem);
        }
    }

   
    //	operateInference
    @Override
    public ScopeData operateInference(Literal literal, Calculation calculation, CSP constraintSatisfactionProblem) {
        switch (strategyForInference) {
            case CHECK_GOING_FORWARD:
                return checkForwardAndProceed(literal, calculation, constraintSatisfactionProblem);
            default:
                return new ScopeData().resetChangedValues();
        }
    }

    // //////////////////////////////////////////////////////////////
    // heuristics for selecting the next unassigned variable and domain ordering

    /**
     * Implements the minimum-remaining-values heuristic.
     */
    private List<Literal> userMRVStrategy(CSP constraintSatisfactionProblem, Calculation calculation) {

    	List<Literal> output = new ArrayList<Literal>();
        
    	int max_value_mrv = Integer.MAX_VALUE;
        
    	for (Literal literal : constraintSatisfactionProblem.getLiterals()) {
            if (!calculation.isCalculatedExists(literal)) {
                int num = constraintSatisfactionProblem.getScope(literal).returnLengthOfObjectArray();
                if (num <= max_value_mrv) {
                    if (num < max_value_mrv) {
                        output.clear();
                        max_value_mrv = num;
                    }
                    output.add(literal);
                }
            }
        }
        return output;
    }

    /**
     * Implements the degree heuristic.
     */
    private List<Literal> useDEGStrategy(List<Literal> listOfLiterals, Calculation calculation, CSP constraintSatisfactionProblem) {
        
    	List<Literal> output = new ArrayList<Literal>();
        
    	int intMin_degreeMax = Integer.MIN_VALUE;
        
    	for (Literal literal : listOfLiterals) {
            
    		int degreeValue = 0;
            
    		for (Rules rule : constraintSatisfactionProblem.getRules(literal)) {
                
    			Literal adjacentLiteral = constraintSatisfactionProblem.getAdjacentElement(literal, rule);
                
    			if (!calculation.isCalculatedExists(adjacentLiteral) && constraintSatisfactionProblem.getScope(adjacentLiteral).returnLengthOfObjectArray() > 1) {
    				++degreeValue;
    			}
            }
    		
            if (degreeValue >= intMin_degreeMax) {
                if (degreeValue > intMin_degreeMax) {
                    output.clear();
                    intMin_degreeMax = degreeValue;
                }
                output.add(literal);
            }
        }
        return output;
    }

    /**
     * Implements the least constraining value heuristic.
     */
	private List<Object> useLCVStrategy(Literal literal, CSP constraintSatisfactionProblem) {
		
		List<Tuple<Object, Integer>> tuples = new ArrayList<>();
		
		for (Object value : constraintSatisfactionProblem.getScope(literal)) {
			int num = calculateNumberOfLostValues(literal, value, constraintSatisfactionProblem);
			tuples.add(new Tuple<>(value, num));
		}
		
		Collections.sort(tuples, new Comparator<Tuple<Object, Integer>>() {
			@Override
			public int compare(Tuple<Object, Integer> o1, Tuple<Object, Integer> o2) {
				return o1.fetchT2Element() < o2.fetchT2Element() ? -1 : o1.fetchT2Element() > o2.fetchT2Element() ? 1 : 0;
			}
		});
		
		List<Object> output = new ArrayList<>();
		
		for (Tuple<Object, Integer> tuple : tuples) {
			output.add(tuple.fetchT1Element());
		}
			
		return output;
	}

    private int calculateNumberOfLostValues(Literal literal, Object object, CSP constraintSatisfactionProblem) {
        
    	int output = 0;
        
    	Calculation calculation = new Calculation();
        
    	calculation.assignCalculatedValue(literal, object);
        
    	for (Rules rule : constraintSatisfactionProblem.getRules(literal)) {
            Literal adjacentLiteral = constraintSatisfactionProblem.getAdjacentElement(literal, rule);
            for (Object objectNValue : constraintSatisfactionProblem.getScope(adjacentLiteral)) {
                calculation.assignCalculatedValue(adjacentLiteral, objectNValue);
                if (!rule.isRuleSatisfied(calculation)) {
                    ++output;
                }
            }
        }
    	
        return output;
    }

    /**
     * Implements forward checking.
     */
    //	doForwardChecking
	private ScopeData checkForwardAndProceed(Literal literal, Calculation calculation, CSP constraintSatisfactionProblem) {
		
		ScopeData outputScopeData = new ScopeData();
		
		for (Rules rule : constraintSatisfactionProblem.getRules(literal)) {
			
			List<Literal> listOfLiterals = rule.getParticipatingLiterals();
			
			if (listOfLiterals.size() == 2) {
				for (Literal adjacentLiteral : rule.getParticipatingLiterals()) {
					if (!calculation.isCalculatedExists(adjacentLiteral)) {
						if (updateValues(adjacentLiteral, rule, calculation, constraintSatisfactionProblem, outputScopeData)) {
							if (constraintSatisfactionProblem.getScope(adjacentLiteral).checkIfObjectArrayIsEmpty()) {
								outputScopeData.setScopeDataWithNoValueFlag(true);
								return outputScopeData;
							}
						}
					}
				}
			}
			
		}
		
		return outputScopeData;
	}

	private boolean updateValues(Literal literal, Rules rule, Calculation calculation, CSP constraintSatisfactionProblem, ScopeData scopeData) {

		boolean checkIfValueIsUpdated = false;
		
		for (Object object : constraintSatisfactionProblem.getScope(literal)) {
			
			calculation.assignCalculatedValue(literal, object);
			
			if (!rule.isRuleSatisfied(calculation)) {
				scopeData.addScopeDataTuple(literal, constraintSatisfactionProblem.getScope(literal));
				constraintSatisfactionProblem.deleteElementFromScope(literal, object);
				checkIfValueIsUpdated = true;
			}
			
			calculation.deleteCalculatedValue(literal);
		}
		return checkIfValueIsUpdated;
	}


    public enum StrategySelection {
        USE_DEFAULT_ORDER, USE_MRV, USE_MRV_WITH_DEG
    }

    public enum Inference {
        NONE, CHECK_GOING_FORWARD
    }
	
	
}