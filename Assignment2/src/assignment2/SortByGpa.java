package assignment2;

public class SortByGpa<E> implements SortStrategy<E> {
	
	@Override
	public int compareWith(E firstElement, E secondElement) {
		return Double.compare(((Student) firstElement).getGpa(), ((Student) secondElement).getGpa());
	}
}
