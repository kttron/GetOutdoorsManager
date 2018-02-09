package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;
/**
 * Tests the Trail class
 * @author Jack Wasserman
 *
 */
public class TrailTest {

	/**
	 * Tests the setActivities method
	 */
	@Test
	public void testSetActivities() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE);
		trail.setActivities(activities);
		assertEquals("trailID", trail.getTrailID());
		assertEquals("trailName", trail.getTrailName());
	}
	
	/**
	 * Tests setting the trailMaintenance
	 */
	@Test
	public void testSetTrailMaintenance() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE);
		trail.setTrailMaintenance(false);
		assertFalse(trail.closedForMaintenance());
		Activity activity = new Activity("activityID", "name", "description", true, 1);
		assertFalse(trail.allow(activity));
		trail.setDistance(2.0);
		assertEquals("trailID", trail.getTrailID());
		assertEquals("trailName", trail.getTrailName());
	}
	/**
	 * Tests the setSnow method
	 */
	@Test
	public void testSetSnow() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE);
		trail.setSnow(0.0);
		assertEquals("trailID", trail.getTrailID());
		assertEquals("trailName", trail.getTrailName());
	}
	/**
	 * Tests the setActivities method
	 */
	@Test
	public void testActivities() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		Trail trail = new Trail("trailID", "trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE);
		trail.setActivities(activities);
		assertEquals(1, trail.getActivities().size());
		trail.setTrailMaintenance(true);
		assertEquals(1.0, trail.getSnow(), 0.0);
		assertEquals(2.0, trail.getDistance(), 0.0);
		assertEquals(Difficulty.MODERATE, trail.getDifficulty());
	}
	/**
	 * Tests the trailOpen method
	 */
	@Test
	public void testTrailOpen1() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		Activity activity = new Activity("activityID", "name", "description", true, 1);
		assertTrue(trail.trailOpen(activity));
	}
	/**
	 * Tests the trailOpen method
	 */
	@Test
	public void testTrailOpen2() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 0.0, 2.0, Difficulty.MODERATE);
		Activity activity = new Activity("activityID", "name", "description", true, 1);
		assertFalse(trail.trailOpen(activity));
	}
	/**
	 * Tests the trailOpen method
	 */
	@Test
	public void testTrailOpen3() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 0.0, 2.0, Difficulty.MODERATE);
		Activity activity = new Activity("activityID", "name", "description", false, 1);
		assertTrue(trail.trailOpen(activity));
	}
	/**
	 * Tests the trailOpen method
	 */
	@Test
	public void testTrailOpen4() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		Activity activity = new Activity("activityID", "name", "description", false, 1);
		assertFalse(trail.trailOpen(activity));
	}
	
	/**
	 * Tests the compareTo method
	 */
	@Test
	public void testCompareTo1() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertEquals(0, trail.compareTo(trail));
		Trail trail2 = new Trail("trailID", "trailName-NotTheSame", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertEquals("trailName".compareTo("trailName-NotTheSame"), trail.compareTo(trail2));
	}
	/**
	 * Tests the hashcode method
	 */
	@Test
	public void testHashcode() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertTrue(Integer.MIN_VALUE <= trail.hashCode() && trail.hashCode() <= Integer.MAX_VALUE);
	}
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals1() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertTrue(trail.equals(trail));
	}
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals2() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertFalse(trail == (null));
	}
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals3() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertFalse(trail.equals(activities));
	}
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEquals4() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		Trail trail2 = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		assertTrue(trail.equals(trail2));
	}
	
	/**
	 * Tests the addSnow method
	 */
	@Test
	public void testAddSnow() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Trail trail = new Trail("trailID", "trailName", activities, true, 2.0, 2.0, Difficulty.MODERATE);
		trail.addSnow(10);
		assertTrue(trail.getSnow() == 12.0);
	}
	
	/**
	 * Tests the toString method
	 */
	@Test
	public void testToString() {
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("id", "name", "description", true, 1));
		Trail trail = new Trail("trailID", "trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE);
		assertEquals(trail.toString(), "trailName\ttrue\t1.0\t2.0\t" + Difficulty.MODERATE + "\tname");
	}

}
