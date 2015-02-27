package assignment2;

public class SortByGpa implements SortStrategy{
	
	@Override
	public int compareWith(Object firstElement, Object secondElement) {
		return Double.compare(((Student) firstElement).getGpa(), ((Student) secondElement).getGpa());
	}
}
