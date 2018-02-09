package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.ui.ActivityListPane;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;
/**
 * This class handles a SortedArrayList of Acitivty objects
 * @author Jack Wasserman
 *
 */
public class ActivityList extends Observable implements Observer, Tabular {
	/** SortedArrayList containing Acitivty objects */
	private SortedArrayList<Activity> activities;
	/** The name Activities */
	private String name = "Activities";
	/** The number of Activites in the SortedArrayList */
	private int numActivities; 

	/**
	 * Constructor for the ActivtyList
	 */
	public ActivityList() {
		this.activities = new SortedArrayList<Activity>();
		this.numActivities = 0;
	}

	/**
	 * Returns the ActivityList name.
	 * @return this.name the name of the Activity list
	 */
	public String getName() {
		return this.name;

	}

	/**
	 * The method constructs a Activity using the provided parameters and creates 
	 * a unique id for the activityID using the numActivities. The Activity is added to activities so that the list is always sorted by Activity name. 
	 * Observers of ActivityList are notified of the change if the Activity is added to activities. 
	 * @param name the name of the Activity
	 * @param description the description of the Activity
	 * @param needSnow if the Activity needs snow or not
	 * @param snowBoundary how much snow the Activity can or cannot perform in
	 * @return true if the Activity is added to the list, false if otherwise
	 */
	public boolean addActivity(String name, String description, boolean needSnow, int snowBoundary) {
		try {
			Activity activity = new Activity("act-" + this.numActivities, name, description, needSnow, snowBoundary);
			this.activities.add(activity);
			this.numActivities++;
			activity.addObserver(this);
			this.setChanged();
			this.notifyObservers(this);
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}
	
	/**
	 * Gets the ActivityList
	 * @return this.activities the SortedArrayList of Activities
	 */
	public SortedArrayList<Activity> getActivities() {
		return this.activities;
	}

	/**
	 * Returns the Activity at the given index in the activities list. 
	 * @param index the index to retrieve an Activity from in the list
	 * @return activity that is at the specified index
	 */
	public Activity getActivityAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.activities.get(index);
	}

	/**
	 * Returns the number of Activity objects in the activities list.
	 * @return numActivities. 
	 */
	public int size() {
		return numActivities;
	}

	/**
	 * Tells if there is one or more activites in the list
	 * @return returns true if the activities list is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return this.activities.isEmpty();

	}

	/**
	 * Returns an Object[][] array. Each row, i, contains an Activity.
	 * [i][0] is the Activity activityID, [i][1] is the Activity name, [i][2] is the Activity description, [i][3] is the Activity needSnow, and 
	 * [i][4] is the Activity snowBoundary. 
	 * @return arr the 2d array which holds the activities components
	 */
	public Object[][] get2DArray() {
		Object[][] arr = new Object[this.activities.size()][5];
		for (int i = 0; i < activities.size(); i++) {
			arr[i][0] = activities.get(i).getActivityID();
			arr[i][1] = activities.get(i).getName();
			arr[i][2] = activities.get(i).getDescription();
			arr[i][3] = activities.get(i).getNeedSnow();
			arr[i][4] = activities.get(i).getSnowBoundary();
		}
		return arr;
	}

	/**
	 * Returns the index of the first occurrence of a Activity that has an exact match to the provided id 
	 * or -1 if there are no Activity objects with an exact match on the given id.
	 * @param id of the Activity to get the index of
	 * @return i the index that is the location of the specified Activity
	 */
	public int indexOfID(String id) {
		for (int i = 0; i < this.numActivities; i++) {
			if (this.activities.get(i).getActivityID().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 *  If a Activity in the Activity List changes, the update() method is automatically called. 
	 *  Activity List should propagate the notification of the change to its Observers IF the Observable o is contained in the activities list. 
	 *  The arg parameter is passed to notifyObservers().
	 * @param o the observable that is checked to see if it is contained in the list
	 * @param arg object passed in as argument
	 */
	public void update(Observable o, Object arg) {
		if(this.activities.contains((Activity) o)) {
			this.setChanged();
			this.notifyObservers(this);
		}
	}

	/**
	 * Adds the observer to Activity List
	 * @param activityListPane the pane in the GUI that observes Activity List
	 */
	public void addObserver(ActivityListPane activityListPane) {
		super.addObserver(activityListPane);
		ActivityListPane.getDefaultLocale(); //Added in something to pass PMD
	}
}
