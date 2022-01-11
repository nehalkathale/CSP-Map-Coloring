package edu.uncc.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ScopeData {
	

    private HashSet<Literal> modifiedLiterals;
    
   
    private boolean scopeDataNoValFlag;
    
    private List<Tuple<Literal, Scope>> savedTuples;
    
    public ScopeData() {
    	savedTuples = new ArrayList<>();
    	modifiedLiterals = new HashSet<>();
    }

 
    public void resetTuples() {
    	savedTuples.clear();
    	modifiedLiterals.clear();
    }

   
    public boolean checkIfStoredTupleIsEmpty() {
        return savedTuples.isEmpty();
    }

    
    public void addScopeDataTuple(Literal literal, Scope scope) {
        if (!modifiedLiterals.contains(literal)) {
        	savedTuples.add(new Tuple<Literal, Scope>(literal, scope));
            modifiedLiterals.add(literal);
        }
    }

   
    public void setScopeDataWithNoValueFlag(boolean val) {
    	scopeDataNoValFlag = val;
    }
    

    public ScopeData resetChangedValues() {
    	modifiedLiterals = null;
        return this;
    }

    
    public boolean getScopeDataWithNoValueFlag() {
        return scopeDataNoValFlag;
    }

    public List<Tuple<Literal, Scope>> getStoredTuples() {
        return savedTuples;
    }

    public void restoreDomains(CSP constraintSatisfactionProblem) {
        for (Tuple<Literal, Scope> tuple : getStoredTuples())
            constraintSatisfactionProblem.setScope(tuple.fetchT1Element(), tuple.fetchT2Element());
    }

    public String toString() {
        
    	StringBuilder output = new StringBuilder();
        
    	for (Tuple<Literal, Scope> tuple : savedTuples) {
    		output.append(tuple.fetchT1Element() + "=" + tuple.fetchT2Element() + " ");
    	}

        if (scopeDataNoValFlag)
            output.append("!");
        return output.toString();
    }

  
    public List<Tuple<Literal, Scope>> getSavedTuples() {
		return savedTuples;
	}


	public void setSavedTuples(List<Tuple<Literal, Scope>> savedTuples) {
		this.savedTuples = savedTuples;
	}


	public HashSet<Literal> getModifiedLiterals() {
		return modifiedLiterals;
	}


	public void setModifiedLiterals(HashSet<Literal> modifiedLiterals) {
		this.modifiedLiterals = modifiedLiterals;
	}

 
    public boolean checkScopeDataWithNoValueFlag() {
        return scopeDataNoValFlag;
    }

  
    public void assignScopeDataWithNoValueFlag(boolean scopeDataWithNoValueFlag) {
        this.scopeDataNoValFlag = scopeDataWithNoValueFlag;
    }
	
}