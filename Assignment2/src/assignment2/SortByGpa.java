package assignment2;

public class SortByGpa implements SortStrategy{

	@Override
	public boolean add(Object element) {
		Student student = (Student) element;
		student.setStrategy(new SortByGpa());
		return true;
	}

	@Override
	public int compare(Object firstElement, Object secondElement) {
		return Double.compare(((Student) firstElement).getGpa(), ((Student) secondElement).getGpa());
	}
}
