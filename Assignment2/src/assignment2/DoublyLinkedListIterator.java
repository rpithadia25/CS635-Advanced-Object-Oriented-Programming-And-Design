package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListIterator<E> implements Iterator<E>{

	private Node<E> current;
	
			DoublyLinkedListIterator(Node<E> head) {
				current = head;
			}
	
			@Override
			public boolean hasNext() {
				return (current != null);
			}
	
			@Override
			public E next() {
				if(hasNext()) {
					E result = current.getNodeData();
					current = current.getNext();
					return result;
				}
				throw new NoSuchElementException();
			}
	
			@Override
			public void remove() {
				throw new UnsupportedOperationException("This operation is currently not supported.");
			}
	
}
