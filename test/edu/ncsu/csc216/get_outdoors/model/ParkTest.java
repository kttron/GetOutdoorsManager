
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Park class
 * @author Jack Wasserman
 *
 */
public class ParkTest {

	/**
	 * Test getter methods
	 */
	@Test
	public void testGetters() {
		try {
			Park parkFail = new Park(null, "Centennial Campus", "Park contained on Engineering Campus", 1.0);
			parkFail.getName();
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("parameters can't be null", e.getMessage());
		}
		
		try {
			Park parkFail = new Park(null, "Centennial Campus", "Park contained on Engineering Campus");
			parkFail.getName();
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("parameters can't be null", e.getMessage());
		}
		
		
		Park park = new Park("1", "Centennial Campus", "Park contained on Engineering Campus", 1.0);
		
		assertEquals("1", park.getParkID());
		assertEquals("Centennial Campus", park.getName());
		assertEquals("Park contained on Engineering Campus", park.getDescription());
		assertTrue(park.getSnowChange() == 1.0);
		
		park.setSnowChange(2.0);
		assertTrue(park.getSnowChange() == 2.0);
	}

	/**
	 * Tests toString, hashCode, and equals methods
	 */
	@Test
	public void testToString() {
		Park park = new Park("1", "Centennial Campus", "Park contained on Engineering Campus", 1.0);
		
		assertEquals("Centennial Campus" + "\t" + "Park contained on Engineering Campus" + "\t" + "1.0", park.toString());
		
		Park park2 = new Park("1", "Main Campus", "Lovely park on Main Campus");
		
		assertTrue(park2.getSnowChange() == 0.0); //Test other constructor and default 
		assertFalse(park.equals(park2));
		
		assertEquals(1020618414, park.hashCode());
		
		assertFalse(park.equals(park2));
		assertTrue(park.equals(park));
		assertFalse(park.equals("String"));
		
		assertEquals(-1, park.compareTo(park2));
		assertEquals(1, park2.compareTo(park));
		assertEquals(0, park.compareTo(park));
	}

}