package assignment2;

import java.util.Iterator;

public class TestList {

	public static void main(String[] args) throws IndexOutOfBoundsException {
		
		DoublyLinkedList<Student> list = new DoublyLinkedList<Student>();
		
		Student drake =  new Student();
		drake.setGpa(4.00);
		drake.setName("Drake");
		drake.setRedId("817340687");
		list.add(drake);
		Student floyd = new Student();
		floyd.setGpa(2.7);
		floyd.setName("Floyd");
		floyd.setRedId("817654321");
		list.add(floyd);
		Student ellen = new Student();
		ellen.setGpa(3.5);
		ellen.setName("Ellen");
		ellen.setRedId("817123456");
		list.add(ellen);
		Student andrew = new Student();
		andrew.setGpa(2.5);
		andrew.setName("Andrew");
		andrew.setRedId("817123453");
		list.add(andrew);
		Student garfield = new Student();
		garfield.setGpa(4.0);
		garfield.setName("Garfield");
		garfield.setRedId("817123413");
		list.add(garfield);
		
		Iterator<Student> itr = list.iterator();
		while(itr.hasNext())
			System.out.println(itr.next() + " ");
//		
//		list.printKthStudent(4);
//		list.printAllStudents();
		//list.printHonorNames();
		//list.printProbationRedIds();
	}
}
