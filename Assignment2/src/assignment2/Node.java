package assignment2;

public class Node<E> {

	private Node<E> next;
	private Node<E> previous;
	private E data;

	public Node() {

	}

	public Node(E data) {
		next = Node.newNull();
		previous = Node.newNull();
		this.data = data;
	}

	public static Node newNull() {
		return new NullNode();
	}
	
	public boolean isNull() {
		return false;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		if(previous != null)
			this.previous = previous;
		else
			throw new IllegalArgumentException();
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		if(next != null)
			this.next = next;
		else
			throw new IllegalArgumentException();
	}

	public E getData() {
		return data;
	}

	public void setNodeData(E data) {
		if(data != null)
			this.data = data;
		else
			throw new IllegalArgumentException();
	}

	public String toString() {
		if(data == null)
			return null;
		else
			return data.toString();
	}

}	
