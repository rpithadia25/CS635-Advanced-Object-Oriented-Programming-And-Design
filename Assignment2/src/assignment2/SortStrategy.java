package assignment2;

public interface SortStrategy<E> {
	public boolean add(E element);
	public int compare(E firstElement, E secondElement);
}
