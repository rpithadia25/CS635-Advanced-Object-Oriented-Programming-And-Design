package assignment2;

public interface SortStrategy<E> {
	public int compareWith(E firstElement, E secondElement);
}
