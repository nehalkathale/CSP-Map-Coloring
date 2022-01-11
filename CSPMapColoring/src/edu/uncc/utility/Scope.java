package edu.uncc.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scope implements Iterable<Object>{

    private Object[] objectArray;

    public Scope (List<?> listOfValues) {
        this.objectArray = new Object[listOfValues.size()];
        for (int p = 0; p < listOfValues.size(); p++) {
        	this.objectArray[p] = listOfValues.get(p);
        }
    }

    public Scope (Object[] arrayOfValues) {
        this.objectArray = new Object[arrayOfValues.length];
        for (int p = 0; p < arrayOfValues.length; p++) {
        	this.objectArray[p] = arrayOfValues[p];
        }
    }

    public int returnLengthOfObjectArray() {
        return objectArray.length;
    }

    public Object getObjectFromArray(int index) {
        return objectArray[index];
    }

    public boolean checkIfObjectArrayIsEmpty() {
        return objectArray.length == 0;
    }

    public boolean checkIfObjectArrayContainsElement(Object element) {
        for (Object v : objectArray) {
        	if (v.equals(element)) {
        		return true;
        	}
        }
        
        return false;
    }

    @Override
    public Iterator<Object> iterator() {
        return new CSPIterator<>(objectArray);
    }


    public List<Object> convertArrayToList() {
        
    	List<Object> returnObjectList = new ArrayList<>();
        
    	for (Object element : objectArray) {
        	returnObjectList.add(element);        	
        }
    	
        return returnObjectList;
    }

    @Override
    public boolean equals(Object element) {
        if (element instanceof Scope) {
        	Scope scope = (Scope) element;
            if (scope.returnLengthOfObjectArray() != objectArray.length) {
            	return false;
            } else {
            	for (int p = 0; p < objectArray.length; p++) {
            		if (!objectArray[p].equals(scope.objectArray[p])) {
            			return false;
            		}
            	}
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashValue = 9;
        int multiplierValue = 13;
        for (int i = 0; i < objectArray.length; i++) {
        	hashValue = hashValue * multiplierValue + objectArray[i].hashCode();
        }
        return hashValue;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("{ ");
        boolean csv = false;
        for (Object element : objectArray) {
            if (csv) {
            	output.append(", ");
            }
            output.append(element.toString());
            csv = true;
        }
        output.append(" }");
        return output.toString();
    }

    public Object[] getObjectArray() {
        return objectArray;
    }

    public void setObjectArray(Object[] objectArray) {
        this.objectArray = objectArray;
    }
    
}