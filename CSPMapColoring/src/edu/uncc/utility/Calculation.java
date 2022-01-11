package edu.uncc.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Formatter.*;

public class Calculation {

	List<Literal> literalsList;

	Hashtable<Literal, Object> literalVal;

	int btCount = 0;

	public Calculation() {
		literalsList = new ArrayList();
		literalVal = new Hashtable<>();
	}

	public List<Literal> getAllLiterals() {
		return Collections.unmodifiableList(literalsList);
	}

	
	public Object getCalulatedValue(Literal literal) {
		return literalVal.get(literal);
	}

	
	public void assignCalculatedValue(Literal literal, Object value) {
		if (!literalVal.containsKey(literal)) {
			literalsList.add(literal);
		}
		literalVal.put(literal, value);
	}

	
	public void deleteCalculatedValue(Literal literal) {
		if (isCalculatedExists(literal)) {
			literalsList.remove(literal);
			literalVal.remove(literal);
		}
	}


	public boolean isCalculatedExists(Literal literal) {
		return literalVal.get(literal) != null;
	}

	
	public boolean checkForValidityAgainstRules(List<Rules> rulesList) {

		for (Rules rule : rulesList) {
			if (!rule.isRuleSatisfied(this)) {
				return false;
			}
		}

		return true;
	}

	
	public boolean areAllValuesSatisfied(List<Literal> literalsList) {
		for (Literal literal : literalsList) {
			if (!isCalculatedExists(literal))
				return false;
		}
		return true;
	}

	
	public boolean checkIfAllValuesSatisfied(Literal[] literalsArray) {
		for (Literal literal : literalsArray) {
			if (!isCalculatedExists(literal))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if this assignment is consistent as well as complete with
	 * respect to the given CSP.
	 */
	public boolean isSolution(CSP constraintSatisfactionProblem) {
		return checkForValidityAgainstRules(constraintSatisfactionProblem.getRules())
				&& areAllValuesSatisfied(constraintSatisfactionProblem.getLiterals());
	}

	// copy()
	public Calculation addValues() {
		Calculation calculation = new Calculation();
		for (Literal literal : literalsList) {
			calculation.assignCalculatedValue(literal, literalVal.get(literal));
		}
		return calculation;
	}

	public void addBTCount() {
		btCount++;
	}

	public int returnBTCount() {
		return btCount;
	}

	@Override
	public String toString() {

		boolean csv = false;

		StringBuilder output = new StringBuilder();

		for (Literal literal : literalsList) {
			if (csv) {
				//output.append(", ");
				System.out.format("%32s%16s", literal.getLiteralName(),literalVal.get(literal));
				System.out.println();
			}
			
			csv = true;
		}
		//output.append(" }");
		return output.toString();
	}

	public void setLiteralsList(List<Literal> literalsList) {
		this.literalsList = literalsList;
	}

	public Hashtable<Literal, Object> getLiterVal() {
		return literalVal;
	}

	public void setLiteralVal(Hashtable<Literal, Object> literVal) {
		this.literalVal = literVal;
	}

	public int getBTCount() {
		return btCount;
	}

	public void setBTCount(int btCount) {
		this.btCount = btCount;
	}

}