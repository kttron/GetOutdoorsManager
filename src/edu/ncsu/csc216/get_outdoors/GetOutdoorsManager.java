package edu.ncsu.csc216.get_outdoors;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.*;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * The main class for the GetOutdoors tools. Holds references to the top-level
 * data structures and acts as the controller between the model and the GUI
 * presentation view.
 * 
 * @author Jessica Young Schmidt
 * @author Your Name Here
 */
public class GetOutdoorsManager extends Observable implements Observer {
    /** Increment for increasing the capacity of the array of TrailList */
    private static final int RESIZE = 3;
    // States for file processing
    /** Start State */
    private final State startState = new StartState();
    /** State when processing activities */
    private final State activityState = new ActivityState();
    /** State when processing parks */
    public final State parkState = new ParkState();
    /** State when processing trails */
    public final State trailState = new TrailState();
    /** Heading for activities in files */
    private static final String ACTIVITY_HEADER = "Activities";
    /** Heading for parks in files */
    private static final String PARK_HEADER = "Parks";
    /** Heading for trails in files */
    private static final String TRAIL_HEADER = "Trails";
    /** Delimiter in files */
    public static final String DELIMITER = "\t";

    /** State for reading from file */
    private State state;

    /** A collection of TrailList */
    private TrailList[] trailLists;
    /** Number of TrailList */
    private int numLists;
    /** A collection of Activities */
    private ActivityList activities;
    /** A collection of Parks */
    private ParkList parks;
    /** Filename for saving the information */
    private String filename;
    /** True if manager has changed since last save */
    private boolean changed;


	/**
	 * Constructs the GetOutdoorsManager by doing the following: 1. Constructs the
	 * trailLists array with at least three elements. 2. Constructs a new
	 * ActivityList. The GetOutdoorsManager instance should be an Observer of the
	 * ActivityList. 3. Constructs a new ParkList. The GetOutdoorsManager instance
	 * should be an Observer of the ParkList. 4. changed should be set to false.
	 */
	public GetOutdoorsManager() {

		this.trailLists = new TrailList[RESIZE];
		this.activities = new ActivityList();
		this.activities.addObserver(this);
		this.parks = new ParkList();
		this.parks.addObserver(this);
		this.numLists = 0;
		this.changed = false;
	}

	/**
	 * Returns the value stored in changed.
	 * 
	 * @return isChanged.
	 */
	public boolean isChanged() {
		return this.changed;

	}

	/**
	 * Sets the changed field to the given value.
	 * 
	 * @param changed true if the manager has been changed, false if otherwise
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Returns the filename.
	 * 
	 * @return FileName, gets and returns FileName.
	 */
	public String getFilename() {
		return this.filename;

	}

	/**
	 * Sets the FileName. If the filename parameter is null, the empty string, or
	 * string with whitespace only, an IllegalArgumentException is thrown.
	 * 
	 * @param fileName name of the file.
	 */
	public void setFilename(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException();
		}
		this.filename = fileName;
	}

	/**
	 * Returns the number of TrailLists.
	 * 
	 * @return number of TrailLists.
	 */
	public int getNumTrailLists() {
		return this.numLists;
	}


	/**
	 * Returns the TrailList at the given index.
	 * 
	 * @param index
	 *            index < 0 or index >= numLists IndexOutOfBoundsException is
	 *            thrown.
	 * @return TrailList, list of the trails.
	 */
	public TrailList getTrailList(int index) {
		if (index < 0 || index >= numLists) {
			throw new IndexOutOfBoundsException();
		}

		return this.trailLists[index];
	}

	/**
	 * Returns trailLists.
	 * 
	 * @return trailLists, open trails.
	 */
	public TrailList[] getTrailLists() {
//		TrailList[] arr = new TrailList[trailLists[RESIZE].size()];
//		int index = 0;
//		for (TrailList trailList : this.trailLists) {
//			arr[index] = trailList;
//			index++;
//		}
//		return arr;
		return this.trailLists;
	}

	/**
	 * Return Activities.
	 * 
	 * @return Activities, like skiing or hiking.
	 */
	public ActivityList getActivities() {
		return this.activities;

	}

	/**
	 * Returns the index of the added TrailList. If necessary, the TrailLists array
	 * should be resized to accommodate new TrailList. Every TrailList added should
	 * have the GetOutdoorsManager instance added as an Observer.
	 * 
	 * @param park
	 *            parks in given trail list.
	 * @return Park.
	 */
	public int addTrailList(Park park) {
		 TrailList trailList = new TrailList(park);
		trailLists[numLists++] = trailList;
		return numLists;
	}

	/**
	 * Returns parks.
	 * 
	 * @return Parks.
	 */
	public ParkList getParks() {
		return this.parks;

	}

	/**
	 * If a ActivityList, ParkList, or TrailList observed by GetOutdoorsManager
	 * changes, the update() method is automatically called.
	 * 
	 * @param o
	 *            when o is equals to parks, the method should iterate through parks
	 *            to check that each park in parks has a TrailList in trailLists.
	 * @param arg
	 *            if a park doesn’t have an associated TrailList, a TrailList should
	 *            be added. Object.
	 */
	public void update(Observable o, Object arg) {
		this.setChanged();
		if (o instanceof ParkList && arg != null) {
			Park park = (Park) arg;
			boolean found = false;
			for (TrailList trailList : this.trailLists) {
				if (trailList != null && park != null && trailList.getTrailListID() != null && trailList.getTrailListID().equals(park.getParkID())) {
					found = true;
				}
			}

			if (!found) {
				TrailList trailList = new TrailList(park);
				trailList.addObserver(this);
				this.trailLists[0] = trailList;
			}
		}
		this.changed = true;
		this.notifyObservers();

	}
	/**
     * Saves the GetOutdoorsManager to file.
     * 
     * @param filename filename to save GetOutdoorsManager information to.
     * @return true is saved successfully
     */
    public boolean saveDataFile(String filename) {
        if (filename == null || filename.trim().equals("")) {
            System.err.println("Invalid filename: " + filename);
            return false;
        } else {
            try {
                PrintStream out = new PrintStream(new File(filename.trim()));
                out.println("# Activities\n");
                for (int i = 0; i < activities.size(); i++) {
                    out.println("*\t" + clean(activities.getActivityAt(i).toString()));
                }
                out.println("\n# Parks\n");
                for (int i = 0; i < parks.size(); i++) {
                    out.println("*\t" + clean(parks.getParkAt(i).toString()));
                }
                out.println("\n# Trails\n");
                for (int i = 0; i < numLists; i++) {
                    TrailList trails = trailLists[i];
                    for (int j = 0; j < trails.size(); j++) {
                        out.println("*\t" + clean(trails.getParkName()) + "\t"
                                + clean(trails.getTrailAt(j).toString()));
                    }
                }
                changed = false;
                out.close();
                return true;
            } catch (IOException e) {
                System.err.println("An error occurred while saving file " + filename);
                e.printStackTrace(System.err);
                return false;
            }
        }
    }

    /**
     * Opens a data file with the given name and creates the data structures from
     * the file.
     * 
     * @param fname filename to create GetOutdoorsManager information from.
     * @return true is opened successfully
     */
    public boolean openDataFile(String fname) {
        if (changed) {
            saveDataFile(this.filename);
        }
        try {
            state = startState;
            Scanner fileIn = new Scanner(new File(fname));
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                processLine(line);
            }

            setChanged(true);
            notifyObservers(this);
            changed = false;
            fileIn.close();
            return true;
        } catch (IOException e) {
            System.err.println("An error occurred while reading file " + fname);
            return false;
        }
    }
    /**
     * Cleans text input to remove line break character
     * 
     * @param text - the text to clean
     * @return the cleaned text
     */
    private String clean(String text) {
        return text.replace("\n", " ").trim();
    }

    /**
     * Processes a single line from the input text file
     * 
     * @param line the line being processed
     */
    private void processLine(String line) {
        try (Scanner lineScan = new Scanner(line)) {
            if (lineScan.hasNext()) {
                String token = lineScan.next();
                if (token.equals("#")) {
                    state.onHeader(lineScan.nextLine().trim());
                } else {
                    state.onContent(line);
                }
            }
        }
    }

    /**
     * Represents a State in the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private abstract class State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        public abstract void onHeader(String line);

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        public abstract void onContent(String line);

    }

    /**
     * Start State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class StartState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            if (line.equalsIgnoreCase(ACTIVITY_HEADER)) {
                state = activityState;
            } else {
                throw new IllegalArgumentException(
                        "File must start with the ACTIVITY information.");
            }

        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            throw new IllegalArgumentException("File must start with the ACTIVITY information.");
        }

    }

    /**
     * Activity State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class ActivityState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            if (line.equalsIgnoreCase(PARK_HEADER)) {
                state = parkState;
            } else {
                throw new IllegalArgumentException(
                        "File must define PARK after the ACTIVITY information.");
            }
        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            if (!line.trim().equals("")) { // Not a blank line
                try (Scanner lineScan = new Scanner(line)) {
                    lineScan.useDelimiter(DELIMITER);
                    lineScan.next(); // * at start
                    String activityName = lineScan.next();
                    String activityDesc = lineScan.next();
                    boolean needSnow = lineScan.nextBoolean();
                    int snowBoundary = lineScan.nextInt();
                    activities.addActivity(activityName, activityDesc, needSnow, snowBoundary);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Error loading activity information: " + e.getMessage());
                }
            }
        }

    }

    /**
     * Park State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class ParkState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            if (line.equalsIgnoreCase(TRAIL_HEADER)) {
                state = trailState;
            } else {
                throw new IllegalArgumentException(
                        "File must define TRAIL after the PARK information.");
            }
        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            if (!line.trim().equals("")) { // Not a blank line
                try (Scanner lineScan = new Scanner(line)) {
                    lineScan.useDelimiter(DELIMITER);
                    lineScan.next(); // * at start
                    String parkName = lineScan.next();
                    String parkDesc = lineScan.next();
                    double snow = lineScan.nextDouble();
                    parks.addPark(parkName, parkDesc, snow);
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Error loading park information: " + e.getMessage());
                }
            }
        }

    }

    /**
     * Trail State for the FSM for processing the input file
     * 
     * @author Jason King
     * 
     *         Initial FSM for config file
     * 
     * @author Jessica Young Schmidt
     * 
     *         FSM for GetOutdoors
     *
     */
    private class TrailState extends State {

        /**
         * Transition for when a header (line begins with a #) is encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onHeader(String line) {
            throw new IllegalArgumentException(
                    "File must not define any other headers after TRAIL.");
        }

        /**
         * Transition for when a non-header (line does not begin with a #) is
         * encountered
         * 
         * @param line the line of text to process
         */
        @Override
        public void onContent(String line) {
            if (!line.trim().equals("")) { // Not a blank line
                try (Scanner lineScan = new Scanner(line)) {
                    lineScan.useDelimiter(DELIMITER);
                    lineScan.next(); // * at start
                    String parkName = lineScan.next();
                    for (int i = 0; i < numLists; i++) {
                        if (trailLists[i].getParkName().equals(parkName)) {
                            String trailName = lineScan.next();
                            boolean trailMaintenance = lineScan.nextBoolean();
                            double snow = lineScan.nextDouble();
                            double distance = lineScan.nextDouble();
                            Difficulty difficulty = Difficulty.valueOf(lineScan.next());

                            SortedArrayList<Activity> activitiesList = new SortedArrayList<Activity>();
                            if (lineScan.hasNextLine()) {
                                String actLine = lineScan.nextLine();
                                Scanner actScan = new Scanner(actLine);
                                actScan.useDelimiter(DELIMITER);
                                while (actScan.hasNext()) {
                                    String activityName = actScan.next();
                                    for (int j = 0; j < activities.size(); j++) {
                                        Activity activity = activities.getActivityAt(j);
                                        if (activity.getName().equals(activityName)) {
                                            activitiesList.add(activity);
                                        }
                                    }
                                }
                                actScan.close();
                            }
                            trailLists[i].addTrail(trailName, activitiesList, trailMaintenance,
                                    snow, distance, difficulty);
                        }
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Error loading trail information: " + e.getMessage());
                }
            }
        }
    }
}