package assignment2;

public class SortByName<E> implements SortStrategy<E> {

	@Override
	public int compareWith(E firstElement, E secondElement) {
		return ((Student) firstElement).getName().compareTo(
				((Student) secondElement).getName());
	}
}
