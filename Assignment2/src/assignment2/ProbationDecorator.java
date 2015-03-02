package assignment2;

import java.util.AbstractSequentialList;
import java.util.Iterator;

public abstract class ProbationDecorator<E> extends AbstractSequentialList<E> {

	protected AbstractSequentialList<Student> decoratedList;
	
	public ProbationDecorator() {
		
	}
	
	public ProbationDecorator(AbstractSequentialList list) {
		decoratedList = list;
	}
	
	public boolean add (Student student) {
		return decoratedList.add(student);
	}
	
	public boolean isEmpty() {
		return decoratedList.isEmpty();
	}
	
	public int size() {
		return decoratedList.size();
	}
	
	public abstract String toString();
	public abstract Iterator<E> iterator();
	public abstract Object[] toArray();
}
