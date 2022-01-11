package edu.uncc.utility;

import java.util.List;

public interface Rules {

	List<Literal> getParticipatingLiterals();
	
	boolean isRuleSatisfied(Calculation calculation);
	
}