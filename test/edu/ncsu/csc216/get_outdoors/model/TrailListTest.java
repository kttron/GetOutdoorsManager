package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;
/**
 * Tests the Trail List class
 * @author Jack Wasserman
 *
 */
public class TrailListTest {
	/**
	 * Tests the constructor
	 */
	@Test
	public void testTrailList() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		assertEquals("name", trailList.getParkName());
		assertEquals("parkID", trailList.getTrailListID());
	}
	
	/**
	 * Tests the addActivity method
	 */
	@Test
	public void testAddTrailList() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue( trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE) );
		assertEquals("parkID-0", trailList.getTrailAt(0).getTrailID());
		assertEquals(1, trailList.size());
	    assertEquals(false, trailList.isEmpty());
	    assertTrue(Integer.MIN_VALUE <= trailList.hashCode() && trailList.hashCode() <= Integer.MAX_VALUE);
	    assertTrue(trailList.equals(trailList));
	    
	}
	
	/**
	 * Tests the testEquals method
	 */
	@Test
	public void testEquals() {
		Park park = new Park("parkID", "name", "description");
		Park park2 = new Park("parkID2", "name2", "description");
		TrailList trailList = new TrailList(park);
		TrailList trailList2 = new TrailList(park);
		TrailList trailList3 = new TrailList(park2);
		assertFalse(trailList == (null));
		assertTrue(trailList.equals(trailList2));
		assertFalse(trailList.equals(park));
		assertFalse(trailList.equals(trailList3));
		assertTrue(park.equals(trailList.getPark()));
	}
	
	/**
	 * Tests the addTrailList and getTrail methods
	 */
	@Test
	public void testAddTrailListIndexOutOfBoundsException() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue( trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE) );
		try {
			trailList.getTrailAt(110);
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}
	
	/**
	 * Tests the removeTrail method
	 */
	@Test
	public void testRemoveTrailList() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue( trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE) );
	    assertEquals("parkID-0", trailList.removeTrail(0).getTrailID());
	    assertEquals(0, trailList.size());
	    assertEquals(true, trailList.isEmpty());
	    assertTrue(Integer.MIN_VALUE <= trailList.hashCode() && trailList.hashCode() <= Integer.MAX_VALUE);
	    assertTrue(trailList.equals(trailList));
	    assertNull(trailList.removeTrail("dummyID"));
	}
	
	/**
	 * Tests removeTrail with an invalid index
	 */
	@Test
	public void testRemoveTrailListIndexOutOfBoundsException() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue( trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE) );
		try {
			trailList.removeTrail(110);
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}
	
	/**
	 * Tests removeTrail
	 */
	@Test
	public void testRemoveTrail() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue( trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE) );
	}
	
	/**
	 * Tests get2dArray
	 */
	@Test
	public void testGet2DArray() {
		Park park = new Park("parkID", "name", "description");
		
		TrailList trailList = new TrailList(park);
		
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		
		activities.add(new Activity("activityID", "name", "description", true, 1));
		
		Object[][] resp2dArr = trailList.get2DArray();
		assertEquals(0, resp2dArr.length);
		
		Activity activity = new Activity("activityID", "name", "description", true, 1);
		
		Object[][] resp2dArrActivity = trailList.get2DArray(activity);
		assertEquals(0, resp2dArrActivity.length);
		
		Object[][] resp2dArrArrayNoMaintenance = trailList.get2DArrayNoMaintenance();
		assertEquals(0, resp2dArrArrayNoMaintenance.length);
		
		
		Park park1 = new Park("parkID", "name", "description");
		TrailList trailList1 = new TrailList(park1);
		SortedArrayList<Activity> activities1 = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue(trailList1.addTrail("trailName", activities1, true, 1.0, 2.0, Difficulty.MODERATE));
		
		Object[][] twoDimensionalArr = trailList1.get2DArray();
		assertEquals(1, twoDimensionalArr.length);
		
	}
	
	
	/**
	 * Tests get2dArray
	 */
	@Test
	public void testGet2DArrayActivity() {
		Park park = new Park("parkID", "name", "description");
		
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		Activity activity = new Activity("activityID", "name", "description", true, 1);
		activities.add(activity);
		
		trailList.addTrail("t1", activities, true, 1, 1, Difficulty.DIFFICULT);
		trailList.addTrail("t2", activities, false, 1, 1, Difficulty.DIFFICULT);
		
		Object[][] resp2dArr = trailList.get2DArray();
		assertEquals(2, resp2dArr.length);
		
		
		Object[][] resp2dArrActivity = trailList.get2DArray(activity);
		assertEquals(0, resp2dArrActivity.length);
		
		Object[][] resp2dArrArrayNoMaintenance = trailList.get2DArrayNoMaintenance();
		assertEquals(1, resp2dArrArrayNoMaintenance.length);
		
		
		Park park1 = new Park("parkID", "name", "description");
		TrailList trailList1 = new TrailList(park1);
		SortedArrayList<Activity> activities1 = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue(trailList1.addTrail("trailName", activities1, true, 1.0, 2.0, Difficulty.MODERATE));
		
		Object[][] twoDimensionalArr = trailList1.get2DArray();
		assertEquals(1, twoDimensionalArr.length);
		
	}
	
	/**
	 * Tests indexOfID with an Id that isn't in the list
	 */
	@Test
	public void testIndexOfIDNotFound() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		assertTrue( trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE) );
		assertEquals(-1, trailList.indexOfID("activityID"));
	}
	
	/**
	 * Tests the removeTrail(String) method
	 */
	@Test
	public void testRemoveTrailString() {
		Park park = new Park("parkID", "name", "description");
		TrailList trailList = new TrailList(park);
		SortedArrayList<Activity> activities = new SortedArrayList<>();
		activities.add(new Activity("activityID", "name", "description", true, 1));
		trailList.addTrail("trailName", activities, true, 1.0, 2.0, Difficulty.MODERATE);
		Trail t = trailList.getTrailAt(0);
		String id = t.getTrailID();
		
		assertEquals(t, trailList.removeTrail(id));
		
	}
}
