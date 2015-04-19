package assignment2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnProbationFilterTest {

	DoublyLinkedList<Student> list;
	Student drake;
	Student floyd;
	Student ellen;
	Student andrew;
	Student garfield;

	@Before
	public void setUp() throws Exception {
		list = new DoublyLinkedList(new SortByGpa());
		drake = new Student("Drake", "817000002", 4.00);
		floyd = new Student("Floyd", "817000001", 2.7); // On Probation
		ellen = new Student("Ellen", "817123456", 3.5);
		andrew = new Student("Andrew", "817123457", 2.5); // On Probation
		garfield = new Student("Garfield", "817123500", 4.0);
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testProbationIterator() {
		list.add(drake);
		list.add(floyd);
		list.add(ellen);
		list.add(andrew);
		list.add(garfield);
		Iterator<Student> iterator = new OnProbationFilter(list.iterator());
		assertEquals(andrew, iterator.next()); // Gpa: 2.5
		assertEquals(floyd, iterator.next()); // Gpa: 2.7
	}

	@Test
	public void testHasNext() {
		list.add(garfield); // Not on probation
		list.add(ellen); // Not on probation
		Iterator<Student> iterator = new OnProbationFilter(list.iterator());
		assertEquals(false, iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testNext() {
		list.add(drake);
		Iterator<Student> iterator = new OnProbationFilter(list.iterator());
		iterator.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		list.add(ellen);
		list.remove(ellen);
	}
}
