package edu.uncc.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class CSP {
	
	private Hashtable<Literal, List<Rules>> networkConstraintTable;
	
	private Hashtable<Literal, Integer> hashedIndex;
	
	public CSP() {
	}
	private List<Literal> literalsList;
	
	private List<Scope> scopeList;
	
	private List<Rules> rulesList;
	

	public CSP(List<Literal> listLiterals) {
		
		literalsList = new ArrayList<>(listLiterals.size());
		scopeList = new ArrayList<>(listLiterals.size());
		rulesList = new ArrayList<>();
		
        hashedIndex = new Hashtable<>();
        
        networkConstraintTable = new Hashtable<>();
        
        Scope scope = new Scope(new ArrayList<>(0));
        
        int position = 0;
        
        for (Literal literal : listLiterals) {
        	literalsList.add(literal);
        	scopeList.add(scope);
            hashedIndex.put(literal, position++);
            networkConstraintTable.put(literal, new ArrayList<>());
        }
        
	}
	
	public List<Literal> getLiterals() {
        return Collections.unmodifiableList(literalsList);
    }

    public int positionOf(Literal literal) {
        return hashedIndex.get(literal);
    }

    public Scope getScope(Literal literal) {
        return scopeList.get(hashedIndex.get(literal));
    }

    public void setScope(Literal literal, Scope scope) {
    	scopeList.set(positionOf(literal), scope);
    }
    
    public void deleteElementFromScope(Literal literal, Object object) {
        
    	Scope scope = getScope(literal);
        
    	List<Object> objects = new ArrayList<Object>(scope.returnLengthOfObjectArray());
        
    	for (Object v : scope) {
    		if (!v.equals(object)) {
    			objects.add(v);
    		}
    	}
            
        setScope(literal, new Scope(objects));
    }
    
   
    public List<Rules> getRules() {
        return rulesList;
    }

   
    public List<Rules> getRules(Literal literal) {
        return networkConstraintTable.get(literal);
    }

 
    public void addNewRule(Rules rule) {
    	rulesList.add(rule);
        for (Literal literal : rule.getParticipatingLiterals())
        	networkConstraintTable.get(literal).add(rule);
    }

   
   
    public Literal getAdjacentElement(Literal literal, Rules rule) {
        List<Literal> scope = rule.getParticipatingLiterals();
        if (scope.size() == 2) {
            if (literal == scope.get(0)) {
            	return scope.get(1);
            } else if (literal == scope.get(1)) {
            	return scope.get(0);
            }
        }
        return null;
    }

    /**
     * Returns a copy which contains a copy of the domains list and is in all other aspects a flat copy of this.
     */
  
    public CSP storedScopes() {
        CSP output = new CSP();
        output.literalsList = literalsList;
        output.scopeList = new ArrayList<>(scopeList.size());
        output.scopeList.addAll(scopeList);
        output.rulesList = rulesList;
        output.hashedIndex = hashedIndex;
        output.networkConstraintTable = networkConstraintTable;
        return output;
    }

    
    public void setLiterals(List<Literal> listOfLiterals) {
        this.literalsList = listOfLiterals;
    }

  
    public List<Scope> getScope() {
        return scopeList;
    }

    
    public void setScope(List<Scope> listOfScopes) {
        this.scopeList = listOfScopes;
    }

    	
    public void setRules(List<Rules> listOfRules) {
        this.rulesList = listOfRules;
    }

    public Hashtable<Literal, Integer> getHashedIndex() {
        return hashedIndex;
    }

    public void setHashedIndex(Hashtable<Literal, Integer> hashedIndex) {
        this.hashedIndex = hashedIndex;
    }

    public Hashtable<Literal, List<Rules>> getConstraintNetwork() {
        return networkConstraintTable;
    }

    public void setConstraintNetwork(Hashtable<Literal, List<Rules>> constraintNetwork) {
        this.networkConstraintTable = constraintNetwork;
    }
    
}