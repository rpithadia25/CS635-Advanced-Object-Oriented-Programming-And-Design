package assignment2;

public class Node<E> {

	private Node next;
	private Node previous;
	private E nodeData;
	
	//Constructor
	public Node() {
		previous = null;
		next = null;
	}
	
	public Node(E data) {
		this();
		this.nodeData = data;
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
