package assignment2;

public class Node<E> {

	private Node<E> next;
	private Node<E> previous;
	private E nodeData;
	
	//Constructor
	public Node() {
		
	}
	
	public Node(E data) {
		next = Node.newNull();
		previous = Node.newNull();
		this.nodeData = data;
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

	public E getNodeData() {
		return nodeData;
	}

	public void setNodeData(E data) {
		this.nodeData = data;
	}

	public String toString() {
		if(nodeData == null)
			return null;
		else
			return nodeData.toString();
	}

}	
