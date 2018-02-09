package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests the ParkList class
 * @author Jack Wasserman
 *
 */
public class ParkListTest {
	/**
	 * Tests the addPark and getters methods
	 */
	@Test
	public void testAddAndGetters() {
		ParkList pl = new ParkList();
		assertTrue(pl.isEmpty());
		Park p = new Park("park-0-0", "Centennial Campus", "Park contained on Engineering Campus", 1.0);
		assertTrue(pl.addPark("Centennial Campus", "Park contained on Engineering Campus", 1.0));
		
		assertTrue(pl.addPark("Main Campus", "Lovely park on Main Campus", 0));
		
		assertFalse(pl.addPark(null, "Skiing and Snowboarding", 2.0));
		
		assertEquals(p.getName(), pl.getParkAt(0).getName());
		
		assertEquals("Parks", pl.getName());
		
		try{
			pl.getParkAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		try{
			pl.getParkAt(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		assertEquals(2, pl.size());
		assertFalse(pl.isEmpty());		
	}
	
	/**
	 * Tests the indexOf and the get2dArray methods
	 */
	@Test
	public void testIndexOfAnd2dArray() {
		ParkList pl = new ParkList();
		
		assertTrue(pl.addPark("Centennial Campus", "Park contained on Engineering Campus", 1.0));
		assertTrue(pl.addPark("Main Campus", "Lovely park on Main Campus", 0));
		

		assertEquals(0, pl.indexOfID("park-0"));
		assertEquals(1, pl.indexOfID("park-1"));
		assertEquals(-1, pl.indexOfID("park-0-2"));

//		assertEquals(0, pl.indexOfID("Centennial Campus"));
//		assertEquals(1, pl.indexOfID("Main Campus"));
//		assertEquals(-1, pl.indexOfID("park-0-2"));

		assertEquals(2, pl.get2DArray().length);
	}

}
