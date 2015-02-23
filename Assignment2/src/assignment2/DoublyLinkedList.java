package assignment2;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> extends AbstractSequentialList<E> {

	private Node<E> head;
	private Node<E> tail;
	public int size;
	private Algorithm algorithm;

	public DoublyLinkedList(Algorithm algorithm) {
		head = null;
		this.algorithm = algorithm;
	}
	
	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int getSize() {
		return size;
	}

	//Insert student lexicographically
	@Override
	public boolean add(E element) {
		
		Node<E> dataNode = new Node<E>(element);

		//Check if the list is empty
		if(head == null && tail == null) {
			head = dataNode;
			tail = head;
			size++;
			return true;
		} 

		Node<E> currentNode = head;
		Comparable<E> comparable = (Comparable<E>) currentNode.getNodeData();
		
		//Condition to keep iterating when the input is lexicographically greater
		while (currentNode.getNext() != null && (comparable.compareTo(element) < 0 )) {
			currentNode = currentNode.getNext();
		}

		//Input is lexicographically less than all the names
		if(currentNode == head && (comparable.compareTo(element) > 0)) {
			insertFirst(dataNode);
			return true;
		}

		//Input is lexicographically greater than all the names
		if(currentNode.getNext() == null && (comparable.compareTo(element) < 0)) {
			insertLast(dataNode);
			return true;
		}

		//Insert in between
		dataNode.setNext(currentNode);
		dataNode.setPrevious(currentNode.getPrevious());
		currentNode.getPrevious().setNext(dataNode);
		currentNode.setPrevious(dataNode);
		size++;
		return true;
	}

	public void printKthStudent(int k) throws IndexOutOfBoundsException {
		if(head != null) {
			Node<E> currentStudent = head;
			int i = 0;
			while(currentStudent != null && i < k) {
				currentStudent = currentStudent.getNext();
				i++;
			}
			if(currentStudent == null)
				throw new IndexOutOfBoundsException("k is out of bounds.");
			else
				System.out.print("The k'th student is: "+currentStudent.getNodeData().toString()+"\n");
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

	public void printAllStudents() {
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

	private void insertFirst(Node<E> student) {
		if( isEmpty() )                
			tail = student;            
		else
			head.setPrevious(student);   
		student.setNext(head);        
		head = student;               
	}

	private void insertLast(Node<E> student) {
		if( isEmpty() )                
			head = student;         
		else {
			tail.setNext(student);     
			student.setPrevious(tail); 
		}
		tail = student;             
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
				E result =  current.getNodeData();
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
