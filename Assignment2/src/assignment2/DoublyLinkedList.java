package assignment2;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

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
		
		Node<E> dataNode = new Node<E>(element);
		Node<E> currentNode = head;

		while (currentNode.getNext() != null && (sortStrategy.compareWith(currentNode.getNodeData(), element)) < 0) {
			currentNode = currentNode.getNext();
		}
		
		//Input is less than all the elements
		if(currentNode == head  && (sortStrategy.compareWith(currentNode.getNodeData(), element)) > 0) {
			insertFirst(dataNode);
			return true;
		}
		
		//Input is greater than all the elements
		if(currentNode.getNext() == null && (sortStrategy.compareWith(currentNode.getNodeData(), element)) < 0) {
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
	
	private void insertFirst(Node<E> element) {
		if( isEmpty() )                
			tail = element;            
		else
			head.setPrevious(element);   
		element.setNext(head);        
		head = element;           
		size++;
	}

	private void insertLast(Node<E> element) {
		if( isEmpty() )                
			head = element;         
		else {
			tail.setNext(element);     
			element.setPrevious(tail); 
		}
		tail = element;
		size++;
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
		return new DoublyLinkedListIterator<E>(head);
	}

	@Override
	public Object[] toArray() {
		ArrayList<E> listArrayList = new ArrayList<E>();
		
		Iterator<E> listIterator = iterator();
		while(listIterator.hasNext()){
			listArrayList.add(listIterator.next());
		}
		
		Object [] listArray = new Object[listArrayList.size()];
		
		return listArrayList.toArray(listArray);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return super.toArray(a);
	}

	@Override
	public String toString() {
		ArrayList<E> arrayList = new ArrayList<E>();
		
		Iterator<E> listIterator = iterator();
		while(listIterator.hasNext()){
			arrayList.add(listIterator.next());
		}
		
		return arrayList.toString();
	}

	@Override
	public E get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}