package assignment2;

import static org.junit.Assert.*;

import java.util.AbstractSequentialList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnProbationDecoratorTest {

	AbstractSequentialList<Student> list;
	Student drake;
	Student floyd;
	Student ellen;
	Student andrew;
	Student garfield;

	@Before
	public void setUp() throws Exception {
		drake = new Student("Drake", "817000002", 4.00);
		floyd = new Student("Floyd", "817000001", 2.7);
		ellen = new Student("Ellen", "817123456", 3.5);
		andrew = new Student("Andrew", "817123457", 2.5);
		garfield = new Student("Garfield", "817123500", 4.0);

		list = new DoublyLinkedList<Student>(new SortByName());
		list.add(andrew);
		list.add(drake);
		list.add(floyd);
		list.add(garfield);
		list.add(ellen);
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testToString() {
		list = new OnProbationDecorator(list);
		String expectedString = "[Name: Andrew, RedId: 817123457, GPA: 2.5, Name: Floyd, RedId: 817000001, GPA: 2.7]";
		assertEquals(expectedString, list.toString());
	}

	@Test
	public void testToArray() {
		list = new OnProbationDecorator(list);
		Object[] expectedArray = { andrew, floyd };
		assertArrayEquals(expectedArray, list.toArray());
	}

}
