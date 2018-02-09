package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;


import org.junit.Test;
/**
 * Tests the Activity class
 * @author Jack Wasserman
 *
 */
public class ActivityTest {

	/**
	 * Test getter methods
	 */
	@Test
	public void testGetters() {
		Activity activity = new Activity("1", "Bike", "Biking", false, 2);
		
		assertFalse(activity.getNeedSnow());
		assertEquals("1", activity.getActivityID());
		assertEquals("Bike", activity.getName());
		assertEquals("Biking", activity.getDescription());
		assertFalse(activity.snowNeeded());
		assertEquals(2, activity.getSnowBoundary());
		
		try {
			Activity activity1 = new Activity(null, "Bike", "Biking", false, 2);
			activity1.getName();
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		try {
			Activity activity1 = new Activity("act-1", null, "Biking", false, 2);
			activity1.getName();
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		try {
			Activity activity1 = new Activity("act-1", "Bike", "Biking", false, 2);
			activity1.setDescription(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Description can not be null", e.getMessage());
		}
		
		try {
			Activity activity1 = new Activity("act-1", null, "Biking", false, 2);
			activity1.setSnowBoundary(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
	}

	/**
	 * Tests toString, hashCode, and equals methods
	 */
	@Test
	public void testToStringAndHashcode() {
		Activity activity = new Activity("1", "Bike", "Biking", false, 2);
		Activity activitySame = new Activity("1", "Bike", "Biking", false, 2);
		Activity activityNotSame2 = new Activity("1", "Bike2", "Biking", false, 2);
		Park p = new Park("Main", "Park on main", "Park");
		
		assertEquals("Bike" + "\t" + "Biking" + "\t" + "false" + "\t" + "2", activity.toString());
		
		Activity activity2 = new Activity("2", "Ski", "Skiing and Snowboarding", true, 25);
		assertFalse(activity.equals(activity2));
		assertTrue(activity.equals(activity));
		assertFalse(activity.equals(activityNotSame2));
		assertEquals("Bike".hashCode(), activity.hashCode());
		
		assertFalse(activity.equals(activity2));
		assertFalse(activity.equals(p));
		
		assertTrue(activity.equals(activitySame));
		
		assertEquals(-1, activity.compareTo(activity2));
		assertEquals(1, activity2.compareTo(activity));
		assertEquals(0, activity.compareTo(activitySame));
	}

}