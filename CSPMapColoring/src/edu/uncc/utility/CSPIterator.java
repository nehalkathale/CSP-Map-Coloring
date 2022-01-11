package edu.uncc.utility;

import java.util.Iterator;

public class CSPIterator<T> implements Iterator<T> {

	T[] elements;
	
	int elementCounter;
	
	public CSPIterator(T[] elements) {
		this.elements = elements;
		elementCounter = 0;
	}
	
	@Override
	public boolean hasNext() {
		return elementCounter < elements.length;
	}

	@Override
	public T next() {
		return elements[elementCounter++];
	}

	
	public void delete() {
		throw new UnsupportedOperationException();
	}
	
}