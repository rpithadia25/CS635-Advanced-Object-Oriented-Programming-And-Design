package assignment2;

import java.util.ArrayList;
import java.util.Iterator;

public class OnProbationDecorator<E> extends ProbationDecorator<E> {

	public OnProbationDecorator(DoublyLinkedList<Student> doublyLinkedList) {
		this.decoratedList = doublyLinkedList;
	}

	@Override
	public String toString() {
		ArrayList<E> doublyLinkedListArray = new ArrayList<E>();
		
		Iterator<E> listIterator = iterator();
		while(listIterator.hasNext()) {
			doublyLinkedListArray.add(listIterator.next());
		}

		return doublyLinkedListArray.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return (new OnProbationFilter(decoratedList.iterator()));
	}

	@Override
	public Object[] toArray() {
		ArrayList<E> doublyLinkedListArray = new ArrayList<E>();
		
		Iterator<E> listIterator = iterator();
		while(listIterator.hasNext()) {
			doublyLinkedListArray.add(listIterator.next());
		}
		
		Object [] listArray = new Object[doublyLinkedListArray.size()];
		return doublyLinkedListArray.toArray(listArray);
	}


}
