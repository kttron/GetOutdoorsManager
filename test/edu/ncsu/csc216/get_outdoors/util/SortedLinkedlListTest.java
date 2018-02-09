/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.model.Activity;


/**
 * Tests the custom SortedLinkedList
 * @author Jack Wasserman
 *
 */
public class SortedLinkedlListTest {
	/**
	 * Tests the size method
	 */
	@Test
	public void testSize() {
		SortedLinkedList<String> list = new SortedLinkedList<String>();
		
		assertTrue(list.isEmpty());
		
		assertEquals(0, list.size());
		list.add("First");
		list.add("Second");
		list.add("Third");
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		list.add("Fourth");
		assertEquals(4, list.size());
		list.add("Fifth");
		list.add("Sixth");
		list.add("Seventh");
		list.add("Eigth");
		list.add("Ninth");
		assertEquals(9, list.size());
		list.add("Tenth");
		assertEquals(10, list.size());
		list.add("Eleventh");
		assertEquals(11, list.size());
		
		//list.remove(2);
	   // assertEquals(11, list.size());
	}
	/**
	 * Tests the get method
	 */
	@Test
	public void testGetNull() {
		SortedLinkedList<String> sal = new SortedLinkedList<>();
		try {
			sal.add(null);
			sal.get(0);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
		assertEquals(sal.size(), 0);
	}
	
	/**
	 * Tests adding a null object
	 */
	@Test
	public void testRemoveNull() {
		SortedLinkedList<String> sal = new SortedLinkedList<>();
		try {
			sal.add(null);
			sal.remove(0);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
		assertEquals(sal.size(), 0);
	}
	
	/**
	 * Tests removing the middle object
	 */
	@Test
	public void testRemoveInMiddle() {
		SortedLinkedList<String> sal = new SortedLinkedList<>();
		sal.add("a");
		sal.add("b");
		sal.add("c");
		assertEquals("b", sal.remove(1));
	}
	
	
	
	/**
	 * Tests the order of the sorted linked list with different object types
	 */
	@Test
	public void testOrder() {	
		SortedLinkedList<String> sal = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		assertEquals("a b c d", sal.toString());
		
		SortedLinkedList<Activity> sal2 = new SortedLinkedList<Activity>();
		
		Activity activity = new Activity("1", "Bike", "Biking", false, 2);
		
		sal2.add(activity);
		
		Activity activity2 = new Activity("2", "Ski", "Skiing and Snowboarding", true, 25);
		
		sal2.add(activity2);
		
		Activity activity3 = new Activity("3", "Aerobics", "Water Aerobics", true, 0);
		
		sal2.add(activity3);
		
		assertEquals(activity3.toString() + " " + activity.toString() + " " + activity2.toString(), sal2.toString());		
	
	}
	
	/**
	 * Tests the contain and add methods
	 */
	@Test
	public void testContainsAndAdd() {
		SortedLinkedList<String> sal = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		try {
			sal.add(null);
			fail();
		} catch (NullPointerException e) {
			assertNull(e.getMessage());
		}
		
		assertTrue(sal.contains("d"));
		assertFalse(sal.contains("e"));
		//Test growing the linked list
		sal.add("f");
		sal.add("h");
		sal.add("e");
		sal.add("g");
		sal.add("i");
		sal.add("j");
		sal.add("l");
		sal.add("k");
		
		assertEquals(12, sal.size());	
	}
	
	/**
	 * Tests the get and remove methods
	 */
	@Test
	public void testGetAndRemove() {
		SortedLinkedList<String> sal = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		try {
			sal.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		try {
			sal.get(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		assertEquals("a", sal.get(0));
		assertEquals("c", sal.get(2));
		
		
		try {
			sal.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		try {
			sal.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		sal.remove(0);
		
		assertEquals("b", sal.get(0));
		assertEquals("d", sal.get(2));
		
		assertEquals(0, sal.indexOf("b"));
		assertEquals(2, sal.indexOf("d"));
	}
	
	/**
	 * Tests the hashcode and equals methods
	 */
	@Test
	public void testHashcodeAndEquals() {
		SortedLinkedList<String> sal = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");

		assertEquals(1045146564, sal.hashCode());
		
		SortedLinkedList<String> sal2 = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		SortedLinkedList<String> sal3 = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("c");
		
		assertFalse(sal.equals(sal2));
		assertFalse(sal.equals(sal3));
		
	}

}