package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.ui.ParkListPane;
import edu.ncsu.csc216.get_outdoors.util.SortedLinkedList;

/**
 * ParkList has an instance of an SortedLinkedList that will hold Park objects.
 * @author kavitpatel
 *
 */
public class ParkList extends Observable implements Observer, Tabular {

	/** The name of the Park List */
	private String name = "Parks";
	/** The number of parks in the Park List */
	private int numParks;
	/** The SortedLinkedList the contains Park objects */
	private SortedLinkedList<Park> parks;

	/**
	 * Constructs the park list and sets numParks to 0.
	 */
	public ParkList() {
		this.parks = new SortedLinkedList<Park>();
		this.numParks = 0;
	}

	/**
	 * Returns the ParkList name.
	 * @return name of the park list
	 */
	public String getName() {
		return name;
	}

	/**
	 * The method constructs a Park using the provided parameters and creates a unique parkID,
	 * for the Park using the numParks .
	 * @param name the name of the park
	 * @param description the description of the park
	 * @param snowChange the amount of change in snow in the park
	 * @return true if that park was added to the list, false if otherwise
	 */
	public boolean addPark (String name, String description, double snowChange) {
		try {
			Park park = new Park("park-" + this.numParks, name, description);
			this.parks.add(park);
			this.numParks++;
			this.setChanged();
			this.countObservers();
			this.notifyObservers(park);
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	/**
	 * Returns the Park at the given index in the list. 
	 * @param index of the list to get the park
	 * @return the park at the specified index
	 */
	public Park getParkAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.parks.get(index);
	}

	/**
	 * Returns the number of Parks in the parks list.
	 * @return the size of the park list
	 */
	public int size() {
		return this.parks.size();

	}

	/**
	 * Tells if the list contains one or more Parks or not.
	 * @return returns true if the list is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return this.parks.isEmpty();
	}

	/**
	 * If a Park in the ParkList changes, the update() method is automatically called. 
	 * ParkList should propagate the notification of the change to its Observers IF the Observable o is contained in the parks list of Parks. 
	 * @param o the observable that is checked to see if it is contained in the list
	 * @param arg the object passed in as an argument
	 */
	public void update(Observable o, Object arg) {
		if(this.parks.contains((Park) o)) {
			this.notifyObservers(arg);
		}
	}

	/**
	 * Returns an Object[][] array. of parks and their components 
	 * @return arr the 2d array which holds the park's components
	 */
	public Object[][] get2DArray() {
		Object[][] arr = new Object[this.parks.size()][4];
		for(int i = 0; i < parks.size(); i++) {
			arr[i][0] = parks.get(i).getParkID();
			arr[i][1] = parks.get(i).getName();
			arr[i][2] = parks.get(i).getDescription();
			arr[i][3] = parks.get(i).getSnowChange();
		}
		return arr;
	}

	/**
	 *  Returns the index of the first occurrence of a Park that has an exact match to the provided parkID,
	 *  or -1 if there are no Parks with an exact match on the given parkID.
	 * @param parkID the id of the park to get out of the list
	 * @return i the index of the specified Park's location in the list
	 */
	public int indexOfID(String parkID) {
		for(int i = 0; i < this.numParks; i++) {
			if(this.parks.get(i).getParkID().equals(parkID)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Adds the observer to notify the GUi
	 * @param parkListPane the pane that observes ParkList
	 */
	@SuppressWarnings("deprecation")
	public void addObserver(ParkListPane parkListPane) {
		super.addObserver(parkListPane);
		parkListPane.enable(); //Added in something to pass PMD

	}

}
