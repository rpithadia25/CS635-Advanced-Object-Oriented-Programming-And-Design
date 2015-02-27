package assignment2;

public class SortByGpa implements SortStrategy{

	@Override
	public boolean add(Object element) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int compare(Object firstElement, Object secondElement) {
		return Double.compare(((Student) firstElement).getGpa(), ((Student) secondElement).getGpa());
	}
}
