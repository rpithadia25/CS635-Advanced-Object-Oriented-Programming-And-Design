package assignment2;

public class SortByRedId implements SortStrategy {

	@Override
	public boolean add(Object element) {
		Student student = (Student) element;
		student.setStrategy(new SortByRedId());
		return true;
	}

	@Override
	public int compare(Object firstElement, Object secondElement) {
		// TODO Auto-generated method stub
		return 0;
	}

}
