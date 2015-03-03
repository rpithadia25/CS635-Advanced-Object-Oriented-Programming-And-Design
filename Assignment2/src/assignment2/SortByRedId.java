package assignment2;

public class SortByRedId<E> implements SortStrategy<E> {

	@Override
	public int compareWith(E firstElement, E secondElement) {
		return ((Student) firstElement).getRedId().compareTo(((Student) secondElement).getRedId());
	}

}
