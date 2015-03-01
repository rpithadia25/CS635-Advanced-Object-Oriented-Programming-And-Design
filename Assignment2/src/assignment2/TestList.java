package assignment2;

import java.util.Iterator;

public class TestList {

	public static <E> void main(String[] args) throws IndexOutOfBoundsException {
		
		DoublyLinkedList<Student> list = new DoublyLinkedList<Student>(new SortByName());
//		DoublyLinkedList<Student> list = new DoublyLinkedList<Student>(new SortByRedId());
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
		
		//System.out.println(list.toString());
		Iterator<Student> itr = list.iterator();
		while(itr.hasNext())
			System.out.println(itr.next() + " ");

	}
}
