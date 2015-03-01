package assignment2;

public class Node<E> {

	private Node<E> next;
	private Node<E> previous;
	private E data;
	
	//Constructor
	public Node() {
		
	}
	
	public Node(E data) {
		next = Node.newNull();
		previous = Node.newNull();
		this.data = data;
	}
	
	public boolean isNull() {
		return false;
	}
	
	public static Node newNull() {
		return new NullNode();
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setNodeData(E data) {
		this.data = data;
	}

	public String toString() {
		if(data == null)
			return null;
		else
			return data.toString();
	}
	
//	public int size() {
//		int size = next.size();
//		return (size + 1);
//	}

}	
