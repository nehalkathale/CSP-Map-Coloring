package edu.uncc.utility;

import java.util.ArrayList;
import java.util.List;

public class UnequalityRule implements Rules {

	
	private Literal literalFirst;
	
	
	private Literal literalSecond;
	

	private List<Literal> literalsList;
	
	
	public Literal getLiteralFirst() {
		return literalFirst;
	}

	public void setLiteralFirst(Literal literalFirst) {
		this.literalFirst = literalFirst;
	}

	public Literal getLiteralSecond() {
		return literalSecond;
	}

	public void setLiteralSecond(Literal literalSecond) {
		this.literalSecond = literalSecond;
	}

	public UnequalityRule(Literal literal1, Literal literal2) {
		this.literalFirst = literal1;
		this.literalSecond = literal2;
		literalsList = new ArrayList<>(2);
		literalsList.add(literal1);
		literalsList.add(literal2);
	}
	
	@Override
	public List<Literal> getParticipatingLiterals() {
		return literalsList;
	}

	@Override
	public boolean isRuleSatisfied(Calculation calculation) {
		Object object = calculation.getCalulatedValue(literalFirst);
		return object == null || !object.equals(calculation.getCalulatedValue(literalSecond));
	}

	public void setLiteral1(Literal literal1) {
		this.literalFirst = literal1;
	}
	
	public void setLiteral2(Literal literal2) {
		this.literalSecond = literal2;
	}
	
	public void setListOfLiterals(List<Literal> listOfLiterals) {
		this.literalsList = literalsList;
	}
	
}