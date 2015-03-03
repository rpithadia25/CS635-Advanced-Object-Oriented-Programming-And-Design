package assignment2;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {

	public static <E> void main(String[] args) throws IndexOutOfBoundsException {
		
		AbstractSequentialList<Student> list = new DoublyLinkedList<Student>(new SortByName());
		//DoublyLinkedList<Student> list = new DoublyLinkedList<Student>(new SortByRedId());
//		DoublyLinkedList<Student> list = new DoublyLinkedList<Student>(new SortByGpa());
		
			
		Student drake =  new Student("Drake", "817000002", 4.00);
		Student floyd = new Student("Floyd", "817000001", 2.7);
		Student ellen = new Student("Ellen", "817123456", 3.5);
		Student andrew = new Student("Andrew", "817123457", 2.5);
		Student garfield = new Student("Garfield", "817123500", 4.0);
		
		list.add(drake);
		list.add(floyd);
		list.add(ellen);
		list.add(andrew);
		list.add(garfield);
		
		list = new OnProbationDecorator(list);
		//OnProbationDecorator<E> decorator = new OnProbationDecorator<E>(list);
//		decorator.add(drake);
//		decorator.add(floyd);
//		decorator.add(ellen);
//		decorator.add(andrew);
//		decorator.add(garfield);
		Object[] array = list.toArray();
//		System.out.println(list.get(0));
		//System.out.println(list.toString());
		
//		Iterator<Student> iterator = new OnProbationFilter<E>(list.iterator());
//		while(iterator.hasNext())
//			System.out.println(iterator.next() + " ");
		
//		Iterator<Student> itr = list.iterator();
//		while(itr.hasNext())
//			System.out.println(itr.next() + " ");

	}
}
