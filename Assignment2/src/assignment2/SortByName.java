package assignment2;

public class SortByName<E> implements SortStrategy<E>{

	@Override
	public boolean add(E element) {
	
		Student student = (Student) element;
		student.setStrategy(new SortByName<E>());
		return true;
	}

	@Override
	public int compare(E firstElement, E secondElement) {
		return ((Student) firstElement).getName().compareTo(((Student) secondElement).getName());
	}
}
