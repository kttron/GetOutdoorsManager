package edu.ncsu.csc216.get_outdoors;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.model.Park;
/**
 * Tests the GetOutdoorsManager
 * @author Jack Wasserman
 *
 */
public class GetOutdoorsManagerTest {
	/**
	 * Tests the isChanged method
	 */
    @Test
    public void testGetOutdoorsManagerChanged() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertFalse(gom.isChanged());
    }
    /**
     * Tests the isChanged method after initialization
     */
    @Test
    public void testGetOutdoorsManagerChangedAfterInitialization() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        gom.setChanged(true);
        assertTrue(gom.isChanged());
        gom.openDataFile("Not a filename");
    }
    
    /**
     * Tests setting a null filename
     */
    @Test
    public void testNullFileName() {
    	try {
        	GetOutdoorsManager gom = new GetOutdoorsManager();
            gom.setFilename(null);
    	} catch(IllegalArgumentException e) {
    		assertNull(e.getMessage());
    	}

    }
    /**
     * Tests setFileName method
     */
    @Test
    public void testFileName() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        String fileName = "test-file";
        gom.setFilename(fileName);
        assertEquals(fileName, gom.getFilename());
    }
    
    /**
     * Tests an invalid index for getTrailList
     */
    @Test
    public void testGetTrailListInvalid() {
        try {
        	GetOutdoorsManager gom = new GetOutdoorsManager();
            gom.getTrailList(-1);
        } catch (IndexOutOfBoundsException e) {
        	assertNull(e.getMessage());
        }
        
        try {
        	GetOutdoorsManager gom = new GetOutdoorsManager();
            gom.getTrailList(10);
        } catch (IndexOutOfBoundsException e) {
        	assertNull(e.getMessage());
        }
    }
    
    /**
     * Tests addTrailList method
     */
    @Test
    public void testAddTrailList() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        Park park = new Park("park-0", "name", "desc");
        gom.addTrailList(park);
        assertEquals(1, gom.getNumTrailLists());
        assertEquals("park-0", gom.getTrailList(0).getTrailListID());
        assertEquals("park-0", gom.getTrailLists()[0].getTrailListID());
    }
    
    /**
     * Tests getActivities method
     */
    @Test
    public void testGetActivities() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertEquals("Activities", gom.getActivities().getName());
    }
    
    /**
     * Tests getParks method
     */
    @Test
    public void testGetParks() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertEquals("Parks", gom.getParks().getName());
    }
    
    /**
     * Tests the saveDataFile method
     */
    @Test
    public void testSaveDataFile() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertTrue(gom.saveDataFile("test-files\\NCSU.md"));
    }
    
    /**
     * Tests the saveDataFile method with an invalid parameter
     */
    @Test
    public void testSaveDataFileNoFile() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertFalse( gom.saveDataFile(""));
    }
    
    /**
     * Tests the saveDataFile method with an invalid parameter
     */
    @Test
    public void testSaveDataFileNull() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertFalse( gom.saveDataFile(null));
    }
    
    /**
     * Tests the openDataFile method
     */
    @Test
    public void testOpenDataFile() {
        GetOutdoorsManager gom = new GetOutdoorsManager();
        assertTrue( gom.openDataFile("test-files\\NCSU.md"));
    }
}