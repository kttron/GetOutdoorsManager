package edu.ncsu.csc216.get_outdoors.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.model.Activity;
/**
 * Tests the custom SortedArrayList
 * @author Jack Wasserman
 *
 */
public class SortedArrayListTest {
	/**
	 * Tests size and is empty methods 
	 */
	@Test
	public void testSizeAndIsEmpty() {
		SortedArrayList<String> sal = new SortedArrayList<String>();
		assertEquals(0, sal.size());
		assertTrue(sal.isEmpty());
		sal.add("First");
		assertEquals(1, sal.size());
		
		sal.add("Second");
		sal.add("Third");
		
		assertEquals(3, sal.size());
		
		assertFalse(sal.isEmpty());
	}
	
	/**
	 * Tests the order of the sorted array list with different object types
	 */
	@Test
	public void testOrder() {	
		SortedArrayList<String> sal = new SortedArrayList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		assertEquals("a, b, c, d", sal.toString());
		
		SortedArrayList<Activity> sal2 = new SortedArrayList<Activity>();
		
		Activity activity = new Activity("1", "Bike", "Biking", false, 2);
		
		sal2.add(activity);
		
		Activity activity2 = new Activity("2", "Ski", "Skiing and Snowboarding", true, 25);
		
		sal2.add(activity2);
		
		Activity activity3 = new Activity("3", "Aerobics", "Water Aerobics", true, 0);
		
		sal2.add(activity3);
		
		assertEquals(activity3.toString() + ", " + activity.toString() + ", " + activity2.toString(), sal2.toString());		
	
	}

	/**
	 * Tests the contain and add methods
	 */
	@Test
	public void testContainsAndAdd() {
		SortedArrayList<String> sal = new SortedArrayList<String>(10);
		assertEquals(10, sal.length);
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
		//Test growing the array
		sal.add("f");
		sal.add("h");
		sal.add("e");
		sal.add("g");
		sal.add("i");
		sal.add("j");
		sal.add("l");
		sal.add("k");
		
		assertEquals(12, sal.size());
		assertEquals(20, sal.length);
		
	}
	
	/**
	 * Tests the get and remove methods
	 */
	@Test
	public void testGetAndRemove() {
		SortedArrayList<String> sal = new SortedArrayList<String>();
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
		SortedArrayList<String> sal = new SortedArrayList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		assertEquals(35, sal.hashCode());
		
		SortedLinkedList<String> sal2 = new SortedLinkedList<String>();
		sal.add("d");
		sal.add("b");
		sal.add("a");
		sal.add("c");
		
		SortedArrayList<String> sal3 = new SortedArrayList<String>();
		sal.add("d");
		sal.add("c");
		
		assertFalse(sal.equals(sal2));
		assertFalse(sal.equals(sal3));
		
	}
}
