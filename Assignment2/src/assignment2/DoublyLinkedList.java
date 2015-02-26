package assignment2;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> extends AbstractSequentialList<E> {

	private Node<E> 		head;
	private Node<E> 		tail;
	public 	int 			size;
	private SortStrategy<E> sortStrategy;

	public DoublyLinkedList(SortStrategy<E> algorithm) {
		head = null;
		tail = null;
		this.sortStrategy = algorithm;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int getSize() {
		return size;
	}

	//Add element
	@Override
	public boolean add(E element) {
		
		//Null values should not be allowed
		if(element == null) {
			throw new NullPointerException();
		}

		if(head == null) {
			head = null;
			head = new Node<E>(element);
			tail = head;
			size++;
			return true;
		}
		
		sortStrategy.add(element);
		
		Node<E> dataNode = new Node<E>(element);
		Node<E> currentNode = head;

		//Condition to keep iterating when the input is greater
//		while (currentNode.getNext() != null && ((((Student) currentNode.getNodeData()).compareTo(element)) < 0 )) {
//			currentNode = currentNode.getNext();
//		}
		
		Comparable<E> comparable = (Comparable<E>) currentNode.getNodeData();
		while (currentNode.getNext() != null && ((comparable.compareTo(element)) < 0 )) {
			currentNode = currentNode.getNext();
		}
		
		//Input is less than all the elements
		if(currentNode == head  && ((comparable.compareTo(element)) > 0)) {
			insertFirst(dataNode);
			return true;
		}
		
//		Input is greater than all the elements
		if(currentNode.getNext() == null && ((comparable.compareTo(element)) < 0)) {
			
			insertLast(dataNode);
			return true;
		}

		//Insert in between
		dataNode.setNext(currentNode);
		dataNode.setPrevious(currentNode.getPrevious());
		currentNode.getPrevious().setNext(dataNode);
		currentNode.setPrevious(dataNode);
		return true;

	}
	
	private void insertFirst(Node<E> element) {
		if( isEmpty() )                
			tail = element;            
		else
			head.setPrevious(element);   
		element.setNext(head);        
		head = element;               
	}

	private void insertLast(Node<E> element) {
		if( isEmpty() )                
			head = element;         
		else {
			tail.setNext(element);     
			element.setPrevious(tail); 
		}
		tail = element;             
	}

	public void printKthStudent(int k) throws IndexOutOfBoundsException {
		if(head != null) {
			Node<E> currentNode = head;
			int i = 0;
			while(currentNode != null && i < k) {
				currentNode = currentNode.getNext();
				i++;
			}
			
			if(currentNode == null)
				throw new IndexOutOfBoundsException("k is out of bounds.");
			else
				System.out.print("The k'th student is: "+currentNode.getNodeData().toString()+"\n");
		}
	}

	//Print RedIds of students with GPA < 2.85
	//	public void printProbationRedIds() {
	//		if(head != null) {
	//			Node currentStudent = head;
	//			System.out.println("\nProbation List:");
	//			while (currentStudent != null) {
	//				if(currentStudent.getNodeData().getGpa() < 2.85) {
	//					System.out.println(currentStudent.getNodeData().getRedId());
	//					currentStudent = currentStudent.getNext();
	//				} else
	//					currentStudent = currentStudent.getNext();
	//			}
	//		}
	//	}

	//Print name of students with GPA = 4.0
	//	public void printHonorNames() {
	//		if(head != null) {
	//			Node currentStudent = tail;
	//			System.out.println("\nHonor List:");
	//			while(currentStudent != null) {
	//				if(currentStudent.getNodeData().getGpa() == 4.0) {
	//					System.out.println(currentStudent.getNodeData().getName());
	//					currentStudent = currentStudent.getPrevious();
	//				} else
	//					currentStudent = currentStudent.getPrevious();
	//			}
	//		}
	//	}

	public void getAll() {
		if(size == 0) {
			return;
		}
		System.out.println("\nList of all students in lexicographical order:");
		if(head.getNext() == null)
			System.out.println(head.getNodeData().toString());
		Node<E> current = head;
		while(current != null) {
			System.out.println(current.getNodeData().toString());
			current = current.getNext();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return super.toArray(a);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	private class DoublyLinkedListIterator implements Iterator<E> {

		private Node<E> current;

		private DoublyLinkedListIterator() {
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

	@Override
	public E get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
