package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ActivtyList class
 * @author Jack Wasserman
 *
 */
public class ActivityListTest {
	/**
	 * Tests the addActivity and getters methods
	 */
	@Test
	public void testAddAndGetters() {
		ActivityList al = new ActivityList();
		assertTrue(al.isEmpty());
		Activity a = new Activity("act-1", "Bike", "Biking", false, 2);
		assertTrue(al.addActivity("Bike", "Biking", false, 2));
		
		assertTrue(al.addActivity("Ski", "Skiing and Snowboarding", true, 25));
		
		assertFalse(al.addActivity(null, "Skiing and Snowboarding", true, 0));
		
		assertEquals(a.getName(), al.getActivityAt(0).getName());
		
		assertEquals("Activities", al.getName());
		
		try{
			al.getActivityAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		try{
			al.getActivityAt(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		assertEquals(2, al.size());
		assertFalse(al.isEmpty());		
	}
	
	/**
	 * Tests the indexOf and the get2dArray methods
	 */
	@Test
	public void testIndexOfAnd2dArray() {
		ActivityList al = new ActivityList();
		assertTrue(al.addActivity("Bike", "Biking", false, 2));
		
		assertTrue(al.addActivity("Ski", "Skiing and Snowboarding", true, 25));
		
		assertEquals(0, al.indexOfID("act-0"));
		assertEquals(1, al.indexOfID("act-1"));
		assertEquals(-1, al.indexOfID("act-2"));
		
		assertEquals(2, al.get2DArray().length);
	}

}
