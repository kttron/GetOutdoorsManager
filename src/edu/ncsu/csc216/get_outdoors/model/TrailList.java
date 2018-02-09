package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * TrailList has an instance of an SortedArrayList that will hold Trail objects.
 * @author kavitpatel
 */
public class TrailList extends Observable implements Observer, Tabular {
	/** The sorted array list of trails */
	private SortedArrayList<Trail> trails = new SortedArrayList<Trail>();
	/** The number of trails in the trail list */
	private int numTrails;
	/** The ID of the trail list */
	private String trailListID;
	/** The name of the park */
	private String parkName;
	/** The park object the trail is in */
	private Park park;

	/**
	 * Controls the value of a Trail's trailID when adding the Trail to the
	 * TrailList.
	 * 
	 * @return trailListID the ID of the trailList
	 */
	public String getTrailListID() {
		return this.trailListID;

	}

	/**
	 * Gets the number of trails in the trail list
	 * @return this.numTrails the number of trails in the trail list
	 */
	private int numTrails() {
		return this.numTrails;

	}

	/**
	 * Constructs the edu.ncsu.csc216.get_outdoors.util.SortedArrayList, sets
	 * numTrails to 0, and sets the field with the parameter value.
	 * 
	 * @param park the park the trail list is in
	 */
	public TrailList(Park park) {
		if (park == null || park.getParkID() == null || park.getParkID().trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		this.numTrails = numTrails();
		this.trailListID = park.getParkID().trim();
		this.parkName = park.getName().trim();
		this.park = park;
	}

	/**
	 * Returns the park name.
	 * 
	 * @return this.parkName the name of the park
	 */
	public String getParkName() {
		return this.parkName;
	}

	/**
	 * Constructs a Trail using the provided parameters and creates a unique id for
	 * the Trail using the TrailListID and numTrails
	 * 
	 * @param trailName the name of the trail
	 * @param activities the activities that can be done on the trail
	 * @param trailMaintenance if the trail is closed for maintenance or not
	 * @param snow the amount of snow on the trail
	 * @param distance the distance of the trail
	 * @param difficulty the difficulty of the trail
	 * @return true if the trail was added to the trail list, false if otherwise
	 */
	public boolean addTrail(String trailName, SortedArrayList<Activity> activities, boolean trailMaintenance,
			double snow, double distance, Difficulty difficulty) {
		try {
			Trail trail = new Trail(this.trailListID + "-" + this.numTrails, trailName, activities, trailMaintenance, snow, distance, difficulty);
			this.numTrails++;
			this.trails.add(trail);
			this.notifyObservers(this);
			trail.addObserver(this);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the Trail at the given index in the list.
	 * 
	 * @param index the index to get the trail from
	 * @return Trail at the given index
	 */
	public Trail getTrailAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return trails.get(index);
	}

	/**
	 * Returns the Trail removed from the trails list at the given index.
	 * 
	 * @param index the index to remove the trail from
	 * @return Trail that was removed from the list
	 */
	public Trail removeTrail(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		numTrails--;
		return this.trails.remove(index);
	}

	/**
	 * Removes the first occurrence of the Trail with the given TrailID from the
	 * list. Returns the Trail.
	 * 
	 * @param trailID the ID of the trail to remove
	 * @return Trail that was removed from the list
	 */
	public Trail removeTrail(String trailID) {
		for(int i = 0; i < trails.size(); i++) {
			if (trails.get(i).getTrailID().equals(trailID)) {
				Trail temp = trails.get(i);
				trails.remove(i);
				numTrails--;
				return temp;
			}
		}
		return null;		
	}

	/**
	 * Returns the number of Trails in the trails list.
	 * 
	 * @return trails.size the number of trails in the list
	 */
	public int size() {
		return trails.size();

	}

	/**
	 * Returns true if the trails list is empty and false otherwise.
	 * 
	 * @return true if there are no trails in the list, false if otherwise
	 */
	public boolean isEmpty() {
		return this.trails.isEmpty();

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numTrails;
		result = prime * result + ((park == null) ? 0 : park.hashCode());
		result = prime * result + ((parkName == null) ? 0 : parkName.hashCode());
		result = prime * result + ((trailListID == null) ? 0 : trailListID.hashCode());
		result = prime * result + ((trails == null) ? 0 : trails.hashCode());
		return result;
	}

	/**
	 * Two TrailList objects are considered the same if they have the same park.
	 * @param o the object to compare this object to
	 * @return true if o is equal to this object, false if otherwise
	 */
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if (o != null && o instanceof TrailList) {
			TrailList trailList = (TrailList)o;
			return this.park.compareTo(trailList.park) == 0;
		}
		return false;

	}

	/**
	 * Gets the park the TrailList is in
	 * @return this.park the park the TrailList is in
	 */
	public Park getPark() {
		return this.park;
	}
	/**
	 * Returns an Object[][] array. Each row, i, contains a Trail.
	 * 
	 * @return arr the 2d array of trails and their components
	 */
	public Object[][] get2DArray() {
		Object[][] arr = new Object[this.trails.size()][7];
		for(int i = 0; i < this.numTrails; i++) {
			arr[i][0] = trails.get(i).getTrailID();
			arr[i][1] = trails.get(i).getTrailName();
			arr[i][2] = trails.get(i).closedForMaintenance();
			arr[i][3] = trails.get(i).getSnow();
			arr[i][4] = trails.get(i).getDistance();
			arr[i][5] = trails.get(i).getDifficulty();
			arr[i][6] = trails.get(i).getActivities();
		}
		return arr;
	}

	/**
	 * Returns an Object[][] array. Each row, i, contains a Trail that is currently
	 * open for activity.
	 * 
	 * @param activity the activity to define the 2dArray to get
	 * @return arr the 2dArray containing the trailList details
	 */
	public Object[][] get2DArray(Activity activity) {
		int cnt = 0;
		for(int i = 0; i < numTrails; i++) {
			if(trails.get(i).trailOpen(activity))
				cnt++;
		}
		Object[][] arr = new Object[cnt][7];
		for(int i = 0, j = 0; i < this.numTrails; i++) {
			if(trails.get(i).trailOpen(activity)) {
				arr[j][0] = trails.get(i).getTrailID();
				arr[j][1] = trails.get(i).getTrailName();
				arr[j][2] = trails.get(i).closedForMaintenance();
				arr[j][3] = trails.get(i).getSnow();
				arr[j][4] = trails.get(i).getDistance();
				arr[j][5] = trails.get(i).getDifficulty();
				arr[j][6] = trails.get(i).getActivities();
				j++;
			}
		}
		return arr;
	}

	/**
	 * Returns an Object[][] array. Each row, i, contains a Trail that is currently
	 * not closed due to maintenance.
	 * 
	 * @return arr the 2dArray containing the trailList details without the closedForMaintenance field
	 */
	public Object[][] get2DArrayNoMaintenance() {
		int cnt = 0;
		for(int i = 0; i < numTrails; i++) {
			if(trails.get(i).closedForMaintenance())
				cnt++;
		}
		Object[][] arr = new Object[cnt][7];
		for(int i = 0, j = 0; i < this.numTrails; i++) {
			if(trails.get(i).closedForMaintenance()) {
				arr[j][0] = trails.get(i).getTrailID();
				arr[j][1] = trails.get(i).getTrailName();
				arr[j][2] = trails.get(i).closedForMaintenance();
				arr[j][3] = trails.get(i).getSnow();
				arr[j][4] = trails.get(i).getDistance();
				arr[j][5] = trails.get(i).getDifficulty();
				arr[j][6] = trails.get(i).getActivities();
				j++;
			}
		}
		return arr;

	}

	/**
	 * Returns the index of the first occurrence of a Trail that has an exact match
	 * to the provided id or -1 if there are no Trails with an exact match on the
	 * given id.
	 * 
	 * @param id the id of the trail to get the index of from the trailList
	 * @return i the index of the specified trailList, -1 if the trail is not in the list
	 */
	public int indexOfID(String id) {
		for(int i = 0; i < this.trails.size(); i++) {
			if(this.trails.get(i).getTrailID().equals(id)) {
				return i;
			}
		}
		return -1;

	}

	/**
	 * If a Trail in the TrailList changes, the update() method is automatically
	 * called. TrailList should propagate the notification of the change to its
	 * Observers IF the Observable o is contained in the list of Trails. The arg
	 * parameter is passed to notifyObservers(). If Park associated with the
	 * TrailList changes (snowChange value updated), the update() method is
	 * automatically called. TrailList should add the snow change to each trail and
	 * propagate the notification of the change to its Observers. The arg parameter
	 * is passed to notifyObservers().
	 * 
	 * @param o the observable that is checked to see if it is contained in the list
	 * @param arg object passed in as argument
	 */
	public void update(Observable o, Object arg) {
		if(this.trails.contains((Trail) o)){
			this.notifyObservers(arg);
			park.notifyObservers(arg);
		}
		
	}
}
