package assignment2;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedList<E> extends AbstractSequentialList<E> {

	private Node<E> head;
	private Node<E> tail;
	public int size;
	private SortStrategy<E> sortStrategy;

	public DoublyLinkedList(SortStrategy<E> algorithm) {
		head = Node.newNull();
		tail = Node.newNull();
		this.sortStrategy = algorithm;
	}

	public boolean isEmpty() {
		return head.isNull();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>(head);
	}

	// Add element
	@Override
	public boolean add(E element) {

		// Null values should not be allowed
		if (element == null) {
			throw new NullPointerException();
		}

		if (head.isNull()) {
			head = new Node<E>(element);
			tail = head;
			size++;
			return true;
		}

		Node<E> dataNode = new Node<E>(element);
		Node<E> currentNode = head;

		while (!currentNode.getNext().isNull()
				&& (sortStrategy.compareWith(currentNode.getData(), element)) < 0) {
			currentNode = currentNode.getNext();
		}

		// Input is less than all the elements
		if (currentNode == head
				&& (sortStrategy.compareWith(currentNode.getData(), element)) > 0) {
			insertFirst(dataNode);
			return true;
		}

		// Input is greater than all the elements
		if (currentNode.getNext().isNull()
				&& (sortStrategy.compareWith(currentNode.getData(), element)) < 0) {
			insertLast(dataNode);
			return true;
		}

		// Insert in between
		dataNode.setNext(currentNode);
		dataNode.setPrevious(currentNode.getPrevious());
		currentNode.getPrevious().setNext(dataNode);
		currentNode.setPrevious(dataNode);
		size++;
		return true;
	}

	private void insertFirst(Node<E> element) {
		if (isEmpty())
			tail = element;
		else
			head.setPrevious(element);
		element.setNext(head);
		head = element;
		size++;
	}

	private void insertLast(Node<E> element) {
		if (isEmpty())
			head = element;
		else {
			tail.setNext(element);
			element.setPrevious(tail);
		}
		tail = element;
		size++;
	}

	@Override
	public E get(int k) {
		Node<E> currentNode = head;
		if (head != null) {
			int i = 0;
			while (!currentNode.isNull() && i < k) {
				currentNode = currentNode.getNext();
				i++;
			}

			if (currentNode.isNull())
				throw new IndexOutOfBoundsException("k is out of bounds.");
		}
		return currentNode.getData();
	}

	@Override
	public Object[] toArray() {
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return super.toArray(a);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

}