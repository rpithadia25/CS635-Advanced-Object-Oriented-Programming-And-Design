package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OnProbationFilter<E> implements Iterator<Student> {

	public static final double PROBATION_THRESHOLD = 2.85;
	private Iterator<Student> probationIterator;
	private Student current;
	private boolean usedFlag;
	
	public OnProbationFilter (Iterator<Student> input) {
		probationIterator = input;
		usedFlag = true;
	}
	
	@Override
	public boolean hasNext() {
		while(probationIterator.hasNext()) {
			current = probationIterator.next();
			if(current.getGpa() < PROBATION_THRESHOLD) {
				usedFlag = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public Student next() {
		if(!usedFlag) {
			usedFlag = true;
			return current;
		} else if (hasNext()) {
			usedFlag = true;
			return current;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("This operation is currently not supported.");
	}
}
