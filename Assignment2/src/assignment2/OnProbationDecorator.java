package assignment2;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class OnProbationDecorator<E> extends ProbationDecorator<E> {

	public OnProbationDecorator(AbstractSequentialList doublyLinkedList) {
		this.decoratedList = doublyLinkedList;
	}

	@Override
	public String toString() {
		ArrayList<E> doublyLinkedListArray = new ArrayList<E>();

		Iterator<E> listIterator = iterator();
		while (listIterator.hasNext()) {
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
		while (listIterator.hasNext()) {
			doublyLinkedListArray.add(listIterator.next());
		}

		Object[] listArray = new Object[doublyLinkedListArray.size()];
		return doublyLinkedListArray.toArray(listArray);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

}
