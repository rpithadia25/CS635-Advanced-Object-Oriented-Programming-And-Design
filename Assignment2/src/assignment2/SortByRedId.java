package assignment2;

public class SortByRedId implements SortStrategy {

	@Override
	public int compareWith(Object firstElement, Object secondElement) {
		return ((Student) firstElement).getRedId().compareTo(((Student) secondElement).getRedId());
	}

}
