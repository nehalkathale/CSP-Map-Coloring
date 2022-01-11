package edu.uncc.countrymaps;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.utility.*;

public class MapOfAustralia extends CSP {

	public static final Literal NEWSOUTHWALES = new Literal("NEWSOUTHWALES");
    
	public static final Literal NORTHERNTERRITORY = new Literal("NORTHERNTERRITORY");
    
	public static final Literal QUEENSLAND = new Literal("QUEENSLAND");
    
	public static final Literal SOUTHAUSTRALIA = new Literal("SOUTHAUSTRALIA");
    
	public static final Literal TASMANIA = new Literal("TASMANIA");
    
	public static final Literal VICTORIA = new Literal("VICTORIA");
    
	public static final Literal WESTERNAUSTRALIA = new Literal("WESTERNAUSTRALIA");
    
	public static final String RED = "RED";
    
	public static final String GREEN = "GREEN";
    
	public static final String BLUE = "BLUE";

    private static List<Literal> populateStates() {
        
    	List<Literal> listOfLiterals = new ArrayList<Literal>();
        
    	listOfLiterals.add(NEWSOUTHWALES);
        listOfLiterals.add(WESTERNAUSTRALIA);
        listOfLiterals.add(NORTHERNTERRITORY);
        listOfLiterals.add(QUEENSLAND);
        listOfLiterals.add(SOUTHAUSTRALIA);
        listOfLiterals.add(VICTORIA);
        listOfLiterals.add(TASMANIA);
        
        return listOfLiterals;
    }

    public MapOfAustralia() {

    	super(populateStates());

        Scope colorsArray = new Scope(new Object[] { RED, GREEN, BLUE });

        for (Literal literal : getLiterals()) {
        	setScope(literal, colorsArray);
        }
            
        addNewRule(new UnequalityRule(WESTERNAUSTRALIA, NORTHERNTERRITORY));
        addNewRule(new UnequalityRule(WESTERNAUSTRALIA, SOUTHAUSTRALIA));
        addNewRule(new UnequalityRule(NORTHERNTERRITORY, SOUTHAUSTRALIA));
        addNewRule(new UnequalityRule(NORTHERNTERRITORY, QUEENSLAND));
        addNewRule(new UnequalityRule(SOUTHAUSTRALIA, QUEENSLAND));
        addNewRule(new UnequalityRule(SOUTHAUSTRALIA, NORTHERNTERRITORY));
        addNewRule(new UnequalityRule(SOUTHAUSTRALIA, VICTORIA));
        addNewRule(new UnequalityRule(QUEENSLAND, NORTHERNTERRITORY));
        addNewRule(new UnequalityRule(NORTHERNTERRITORY, VICTORIA));
    }
	
}