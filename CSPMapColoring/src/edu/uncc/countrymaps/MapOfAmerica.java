package edu.uncc.countrymaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.uncc.utility.*;

public class MapOfAmerica extends CSP {

	private static final String RED = "RED";
    
	private static final String GREEN = "GREEN";
    
	private static final String BLUE = "BLUE";
    
	private static final String YELLOW = "YELLOW";
	
	private static final String ORANGE = "ORANGE";

    //50 STATES
    private static final Literal ALABAMA = new Literal("ALABAMA");
    
    private static final Literal ALASKA = new Literal("ALASKA");
    
    private static final Literal ARIZONA = new Literal("ARIZONA");
    
    private static final Literal ARKANSAS = new Literal("ARKANSAS");
    
    private static final Literal CALIFORNIA = new Literal("CALIFORNIA");
    
    private static final Literal COLORADO = new Literal("COLORADO");
    
    private static final Literal CONNECTICUT = new Literal("CONNECTICUT");
    
    private static final Literal DELAWARE = new Literal("DELAWARE");
    
    private static final Literal FLORIDA = new Literal("FLORIDA");
    
    private static final Literal GEORGIA = new Literal("GEORGIA");
    
    private static final Literal HAWAII = new Literal("HAWAII");
    
    private static final Literal IDAHO = new Literal("IDAHO");
    
    private static final Literal ILLINOIS = new Literal("ILLINOIS");
    
    private static final Literal INDIANA = new Literal("INDIANA");
    
    private static final Literal IOWA = new Literal("IOWA");
    
    private static final Literal KANSAS = new Literal("KANSAS");
    
    private static final Literal KENTUCKY = new Literal("KENTUCKY");
    
    private static final Literal LOUISIANA = new Literal("LOUISIANA");
    
    private static final Literal MAINE = new Literal("MAINE");
    
    private static final Literal MARYLAND = new Literal("MARYLAND");
    
    private static final Literal MASSACHUSETTS = new Literal("MASSACHUSETTS");
    
    private static final Literal MICHIGAN = new Literal("MICHIGAN");
    
    private static final Literal MINNESOTA = new Literal("MINNESOTA");
    
    private static final Literal MISSISSIPPI = new Literal("MISSISSIPPI");
    
    private static final Literal MISSOURI = new Literal("MISSOURI");
    
    private static final Literal MONTANA = new Literal("MONTANA");
    
    private static final Literal NEBRASKA = new Literal("NEBRASKA");
    
    private static final Literal NEVADA = new Literal("NEVADA");
    
    private static final Literal NEW_HAMSHIRE = new Literal("NEW HAMSHIRE");
    
    private static final Literal NEW_JERSEY = new Literal("NEW JERSEY");
    
    private static final Literal NEW_MEXICO = new Literal("NEW MEXICO");
    
    private static final Literal NEW_YORK = new Literal("NEW YORK");
    
    private static final Literal NORTH_CAROLINA = new Literal("NORTH CAROLINA");
    
    private static final Literal NORTH_DAKOTA = new Literal("NORTH DAKOTA");
    
    private static final Literal OHIO = new Literal("OHIO");
    
    private static final Literal OKLAHOMA = new Literal("OKLAHOMA");
    
    private static final Literal OREGON = new Literal("OREGON");
    
    private static final Literal PENNSYLVANIA = new Literal("PENNSYLVANIA");
    
    private static final Literal RHODE_ISLAND = new Literal("RHODE ISLAND");
    
    private static final Literal SOUTH_CAROLINA = new Literal("SOUTH CAROLINA");
    
    private static final Literal SOUTH_DAKOTA = new Literal("SOUTH DAKOTA");
    
    private static final Literal TENNESSE = new Literal("TENNESSE");
    
    private static final Literal TEXAS = new Literal("TEXAS");
    
    private static final Literal UTAH = new Literal("UTAH");
    
    private static final Literal VERMONT = new Literal("VERMONT");
    
    private static final Literal VIRGINIA = new Literal("VIRGINIA");
    
    private static final Literal WASHINGTON = new Literal("WASHINGTON");
    
    private static final Literal WEST_VIRGINIA = new Literal("WEST VIRGINIA");
    
    private static final Literal WISCONSIN = new Literal("WISCONSIN");
    
    private static final Literal WYOMING = new Literal("WYOMING");

    
    private static List<Literal> populateStates() {
    	
        List<Literal> variables = new ArrayList<>();
        
        variables.add(ALABAMA);
        variables.add(ALASKA);
        variables.add(ARIZONA);
        variables.add(ARKANSAS);
        variables.add(CALIFORNIA);
        variables.add(COLORADO);
        variables.add(CONNECTICUT);
        variables.add(DELAWARE);
        variables.add(FLORIDA);
        variables.add(GEORGIA);
        variables.add(HAWAII);
        variables.add(IDAHO);
        variables.add(ILLINOIS);
        variables.add(INDIANA);
        variables.add(IOWA);
        variables.add(KANSAS);
        variables.add(KENTUCKY);
        variables.add(LOUISIANA);
        variables.add(MAINE);
        variables.add(MARYLAND);
        variables.add(MASSACHUSETTS);
        variables.add(MICHIGAN);
        variables.add(MINNESOTA);
        variables.add(MISSISSIPPI);
        variables.add(MISSOURI);
        variables.add(MONTANA);
        variables.add(NEBRASKA);
        variables.add(NEVADA);
        variables.add(NEW_HAMSHIRE);
        variables.add(NEW_JERSEY);
        variables.add(NEW_MEXICO);
        variables.add(NEW_YORK);
        variables.add(NORTH_CAROLINA);
        variables.add(NORTH_DAKOTA);
        variables.add(OHIO);
        variables.add(OKLAHOMA);
        variables.add(OREGON);
        variables.add(PENNSYLVANIA);
        variables.add(RHODE_ISLAND);
        variables.add(SOUTH_CAROLINA);
        variables.add(SOUTH_DAKOTA);
        variables.add(TENNESSE);
        variables.add(TEXAS);
        variables.add(UTAH);
        variables.add(VERMONT);
        variables.add(VIRGINIA);
        variables.add(WASHINGTON);
        variables.add(WEST_VIRGINIA);
        variables.add(WISCONSIN);
        variables.add(WYOMING);
        
        return variables;
    }

    
    public MapOfAmerica() {
        
    	super(populateStates());

		Scope colorsArray = new Scope(new Object[] { RED, GREEN, BLUE, YELLOW });

        for (Literal literal : getLiterals()) {
            setScope(literal, colorsArray);
        }

        
        addNewRule(new UnequalityRule(ALABAMA, MISSISSIPPI));
        addNewRule(new UnequalityRule(ALABAMA, GEORGIA));
        addNewRule(new UnequalityRule(ALABAMA, TENNESSE));
        addNewRule(new UnequalityRule(ALABAMA, FLORIDA));

        addNewRule(new UnequalityRule(ARIZONA, CALIFORNIA));
        addNewRule(new UnequalityRule(ARIZONA, NEVADA));
        addNewRule(new UnequalityRule(ARIZONA, NEW_MEXICO));
        addNewRule(new UnequalityRule(ARIZONA, UTAH));
        addNewRule(new UnequalityRule(ARIZONA, COLORADO));

   
        addNewRule(new UnequalityRule(ARKANSAS, OKLAHOMA));
        addNewRule(new UnequalityRule(ARKANSAS, TEXAS));
        addNewRule(new UnequalityRule(ARKANSAS, LOUISIANA));
        addNewRule(new UnequalityRule(ARKANSAS, MISSISSIPPI));
        addNewRule(new UnequalityRule(ARKANSAS, TENNESSE));
        addNewRule(new UnequalityRule(ARKANSAS, MISSOURI));

   
        addNewRule(new UnequalityRule(CALIFORNIA, OREGON));
        addNewRule(new UnequalityRule(CALIFORNIA, NEVADA));

     
        addNewRule(new UnequalityRule(COLORADO, UTAH));
        addNewRule(new UnequalityRule(COLORADO, WYOMING));
        addNewRule(new UnequalityRule(COLORADO, NEBRASKA));
        addNewRule(new UnequalityRule(COLORADO, KANSAS));
        addNewRule(new UnequalityRule(COLORADO, OKLAHOMA));
        addNewRule(new UnequalityRule(COLORADO, NEW_MEXICO));

       
        addNewRule(new UnequalityRule(CONNECTICUT, NEW_YORK));
        addNewRule(new UnequalityRule(CONNECTICUT, MASSACHUSETTS));
        addNewRule(new UnequalityRule(CONNECTICUT, RHODE_ISLAND));


        addNewRule(new UnequalityRule(DELAWARE, PENNSYLVANIA));
        addNewRule(new UnequalityRule(DELAWARE, NEW_JERSEY));
        addNewRule(new UnequalityRule(DELAWARE, MARYLAND));

        
        addNewRule(new UnequalityRule(FLORIDA, GEORGIA));
       
        addNewRule(new UnequalityRule(GEORGIA, TENNESSE));
        addNewRule(new UnequalityRule(GEORGIA, NORTH_CAROLINA));
        addNewRule(new UnequalityRule(GEORGIA, SOUTH_CAROLINA));

        addNewRule(new UnequalityRule(IDAHO, WASHINGTON));
        addNewRule(new UnequalityRule(IDAHO, OREGON));
        addNewRule(new UnequalityRule(IDAHO, NEVADA));
        addNewRule(new UnequalityRule(IDAHO, UTAH));
        addNewRule(new UnequalityRule(IDAHO, WYOMING));
        addNewRule(new UnequalityRule(IDAHO, MONTANA));

        
        addNewRule(new UnequalityRule(ILLINOIS, WISCONSIN));
        addNewRule(new UnequalityRule(ILLINOIS, INDIANA));
        addNewRule(new UnequalityRule(ILLINOIS, IOWA));
        addNewRule(new UnequalityRule(ILLINOIS, MISSOURI));
        addNewRule(new UnequalityRule(ILLINOIS, MICHIGAN));
        addNewRule(new UnequalityRule(ILLINOIS, KENTUCKY));

    
        addNewRule(new UnequalityRule(INDIANA, MICHIGAN));
        addNewRule(new UnequalityRule(INDIANA, OHIO));
        addNewRule(new UnequalityRule(INDIANA, KENTUCKY));

        
        addNewRule(new UnequalityRule(IOWA, MINNESOTA));
        addNewRule(new UnequalityRule(IOWA, SOUTH_DAKOTA));
        addNewRule(new UnequalityRule(IOWA, NEBRASKA));
        addNewRule(new UnequalityRule(IOWA, MISSOURI));
        addNewRule(new UnequalityRule(IOWA, WISCONSIN));

        
        addNewRule(new UnequalityRule(KANSAS, OKLAHOMA));
        addNewRule(new UnequalityRule(KANSAS, NEBRASKA));
        addNewRule(new UnequalityRule(KANSAS, MISSOURI));

        
        addNewRule(new UnequalityRule(KENTUCKY, TENNESSE));
        addNewRule(new UnequalityRule(KENTUCKY, VIRGINIA));
        addNewRule(new UnequalityRule(KENTUCKY, WEST_VIRGINIA));
        addNewRule(new UnequalityRule(KENTUCKY, OHIO));
        addNewRule(new UnequalityRule(KENTUCKY, MISSOURI));

        
        addNewRule(new UnequalityRule(LOUISIANA, TEXAS));
        addNewRule(new UnequalityRule(LOUISIANA, MISSISSIPPI));

     
        addNewRule(new UnequalityRule(MAINE, NEW_HAMSHIRE));

       
        addNewRule(new UnequalityRule(MARYLAND, WEST_VIRGINIA));
        addNewRule(new UnequalityRule(MARYLAND, PENNSYLVANIA));
        addNewRule(new UnequalityRule(MARYLAND, VIRGINIA));

        
        addNewRule(new UnequalityRule(MASSACHUSETTS, VERMONT));
        addNewRule(new UnequalityRule(MASSACHUSETTS, NEW_HAMSHIRE));
        addNewRule(new UnequalityRule(MASSACHUSETTS, RHODE_ISLAND));
        addNewRule(new UnequalityRule(MASSACHUSETTS, NEW_YORK));

        
        addNewRule(new UnequalityRule(MICHIGAN, WISCONSIN));
        addNewRule(new UnequalityRule(MICHIGAN, OHIO));
    
        addNewRule(new UnequalityRule(MINNESOTA, NORTH_DAKOTA));
        addNewRule(new UnequalityRule(MINNESOTA, SOUTH_DAKOTA));
        addNewRule(new UnequalityRule(MINNESOTA, WISCONSIN));

    
        addNewRule(new UnequalityRule(MISSISSIPPI, TENNESSE));

      
        addNewRule(new UnequalityRule(MISSOURI, NEBRASKA));
        addNewRule(new UnequalityRule(MISSOURI, OKLAHOMA));
        addNewRule(new UnequalityRule(MISSOURI, TENNESSE));

        addNewRule(new UnequalityRule(MONTANA, NORTH_DAKOTA));
        addNewRule(new UnequalityRule(MONTANA, SOUTH_DAKOTA));
        addNewRule(new UnequalityRule(MONTANA, WYOMING));

     
        addNewRule(new UnequalityRule(NEBRASKA, SOUTH_DAKOTA));
        addNewRule(new UnequalityRule(NEBRASKA, WYOMING));

       
        addNewRule(new UnequalityRule(NEVADA, OREGON));
        addNewRule(new UnequalityRule(NEVADA, UTAH));

     
        addNewRule(new UnequalityRule(NEW_HAMSHIRE, VERMONT));

        
        addNewRule(new UnequalityRule(NEW_JERSEY, PENNSYLVANIA));
        addNewRule(new UnequalityRule(NEW_JERSEY, NEW_YORK));

      
        addNewRule(new UnequalityRule(NEW_MEXICO, TEXAS));
        addNewRule(new UnequalityRule(NEW_MEXICO, OKLAHOMA));
        addNewRule(new UnequalityRule(NEW_MEXICO, UTAH));

     
        addNewRule(new UnequalityRule(NEW_YORK, VERMONT));
        addNewRule(new UnequalityRule(NEW_YORK, PENNSYLVANIA));

        
        addNewRule(new UnequalityRule(NORTH_CAROLINA, VIRGINIA));
        addNewRule(new UnequalityRule(NORTH_CAROLINA, TENNESSE));
        addNewRule(new UnequalityRule(NORTH_CAROLINA, SOUTH_CAROLINA));

       
        addNewRule(new UnequalityRule(NORTH_DAKOTA, SOUTH_DAKOTA));

    
        addNewRule(new UnequalityRule(OHIO, WEST_VIRGINIA));
        addNewRule(new UnequalityRule(OHIO, PENNSYLVANIA));

       
        addNewRule(new UnequalityRule(OKLAHOMA, TEXAS));

        addNewRule(new UnequalityRule(OREGON, WASHINGTON));

      
        addNewRule(new UnequalityRule(PENNSYLVANIA, WEST_VIRGINIA));

       
        addNewRule(new UnequalityRule(SOUTH_DAKOTA, WYOMING));

        addNewRule(new UnequalityRule(TENNESSE, VIRGINIA));
        addNewRule(new UnequalityRule(UTAH, WYOMING));
        addNewRule(new UnequalityRule(VIRGINIA, WEST_VIRGINIA));
    }

    
    public void validateStates() {
        
    	List<Rules> rules = getRules();
        
    	List<String> statesList = new ArrayList<>();
        
    	for (Literal literal : getLiterals()) {
            statesList.add(literal.getLiteralName());
        }
    	
        Collections.sort(statesList);

        int cnt = 0;
 
        for (String currentState : statesList) {

        	System.out.println((cnt + 1) + " State : " + currentState);
            
        	List<String> adjacentCountries = new ArrayList<>();
            
        	for (Rules rule : rules) {
                if (rule instanceof UnequalityRule) {
                    UnequalityRule isNotEqualConstraint = (UnequalityRule) rule;
                    if (currentState.equals(isNotEqualConstraint.getLiteralFirst().getLiteralName())) {
                        adjacentCountries.add(isNotEqualConstraint.getLiteralSecond().getLiteralName());
                    } else if (currentState.equals(isNotEqualConstraint.getLiteralSecond().getLiteralName())) {
                        adjacentCountries.add(isNotEqualConstraint.getLiteralFirst().getLiteralName());
                    }
                }
            }
        	
            if (adjacentCountries.isEmpty()) {
                System.out.println("No Adjacent Country");
            } else {
                Collections.sort(adjacentCountries);
                int nbr = 1;
                for (String neighbor : adjacentCountries) {
                    System.out.println("  " + nbr + " : " + neighbor);
                    nbr++;
                }
            }
            cnt++;
        }
    }

    public static void main(String[] args) {
        MapOfAmerica mapOfAmericaA = new MapOfAmerica();
        mapOfAmericaA.validateStates();
    }
	
}