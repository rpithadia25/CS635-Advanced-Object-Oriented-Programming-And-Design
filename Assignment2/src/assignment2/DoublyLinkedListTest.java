package assignment2;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {

	DoublyLinkedList<Student> sortByNameList;
	DoublyLinkedList<Student> sortByGpaList;
	DoublyLinkedList<Student> sortByRedIdList;
	Student drake;
	Student floyd;
	Student ellen;
	Student andrew;
	Student garfield;
	
	@Before
	public void setUp() throws Exception {
		sortByNameList = new DoublyLinkedList(new SortByName());
		sortByGpaList = new DoublyLinkedList(new SortByGpa());
		sortByRedIdList = new DoublyLinkedList(new SortByRedId());
		
		drake =  new Student();
		drake.setGpa(4.00);
		drake.setName("Drake");
		drake.setRedId("817000002");
		
		floyd = new Student();
		floyd.setGpa(2.7);
		floyd.setName("Floyd");
		floyd.setRedId("817000001");
		
		ellen = new Student();
		ellen.setGpa(3.5);
		ellen.setName("Ellen");
		ellen.setRedId("817123456");
		
		andrew = new Student();
		andrew.setGpa(2.5);
		andrew.setName("Andrew");
		andrew.setRedId("817123457");
	
		garfield = new Student();
		garfield.setGpa(4.0);
		garfield.setName("Garfield");
		garfield.setRedId("817123500");
	}

	@After
	public void tearDown() throws Exception {
		sortByNameList = null;
		sortByGpaList = null;
		sortByRedIdList = null;
	}

	@Test
	public void testSize() {
		sortByNameList.add(drake);
		sortByNameList.add(floyd);
		
		assertEquals(2, sortByNameList.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, sortByNameList.isEmpty());
	}

	@Test
	public void testAddE() {
		assertTrue(sortByGpaList.add(andrew));
		assertTrue(sortByRedIdList.add(garfield));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		sortByNameList.add(null);
	}
	
	@Test
	public void testToString() {
		sortByNameList.add(floyd);
		sortByNameList.add(ellen);
		
		String actualListString = sortByNameList.toString();
		String expectedListString = "[Ellen, Floyd]";
		assertEquals(expectedListString, actualListString);
	}

	@Test(expected = NoSuchElementException.class)
	public void testIterator() {
		sortByRedIdList.add(drake);
		sortByRedIdList.add(ellen);
		sortByRedIdList.add(andrew);
		
		Iterator<Student> sortByRedIdIterator = sortByRedIdList.iterator();
		Iterator<Student> sortByNameIterator = sortByNameList.iterator(); //Iterator for Empty List
		assertTrue(sortByRedIdIterator.hasNext());
		assertEquals(drake, sortByRedIdIterator.next());
		assertFalse(sortByNameIterator.hasNext());
		sortByNameIterator.next();
	}

	@Test
	public void testToArray() {
		sortByNameList.add(drake);
		sortByNameList.add(andrew);
		
		Object [] actualListArray = sortByNameList.toArray();
		Object [] expectedListArray = {andrew, drake};
		assertArrayEquals(expectedListArray, actualListArray);
	}

}
