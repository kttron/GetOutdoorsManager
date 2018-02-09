package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;
/**
 * Handles Comparable Trail objects.
 * @author Jack Wasserman
 *
 */
public class Trail extends Observable implements Comparable<Trail> {
  	/** The ID of the trail */
  	private String trailID;
 	/** The name of the trail */
  	private String trailName;
 	/** Tells if the trail if closed for maintenance or not */
  	private boolean closedForMaintenance;
 	/** The amount of snow on the trail */
  	private double snow;
 	/** The distance the trail is */
  	private double distance;
 	/** The list of Activities on the trail */
  	private SortedArrayList<Activity> activities;
 	/** The difficulty of the trail */
  	Difficulty difficulty;
  
  	/**
  	 * The behaviors defined for setter methods apply when constructing an Trail
  	 * with the given parameters.
  	 * @param trailID the ID of the trail
 	 * @param trailName the name of the trail
 	 * @param activities the trail's activities
 	 * @param trailMaintenance tells if the trail is closed for maintenance or not
 	 * @param snow the amount of snow on the trail
 	 * @param distance the distance the trail is 
 	 * @param difficulty the difficulty of the trail
  	 */
	public Trail(String trailID, String trailName, SortedArrayList<Activity> activities, boolean trailMaintenance,
			double snow, double distance, Difficulty difficulty) {
		if(trailID == null || trailName == null || trailID.trim().isEmpty() || trailName.trim().isEmpty() 
				|| distance < 0 || activities == null || difficulty == null) {
			throw new IllegalArgumentException("Invalid arguments to create a trail");
		}
		this.trailID = trailID.trim();
		this.trailName = trailName.trim();
		this.snow = snow;
		this.distance = distance;
		this.activities = activities;
		this.closedForMaintenance = trailMaintenance;
		this.difficulty = difficulty;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 	 * Sets the activities for the trail
	 	 * @param activities the activities to assign to the trail
	 	 */
	public void setActivities(SortedArrayList<Activity> activities) {
		if (activities == null) {
			throw new IllegalArgumentException();
		}
		
		this.activities = activities;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 	 * Sets the trail maintenance 
	 	 * @param trailMaintenance true if the trail is under maintenance, false if otherwise
	 	 */
	public void setTrailMaintenance(boolean trailMaintenance) {
		this.closedForMaintenance = trailMaintenance;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	  * Sets the amount of snow on the trail
	 	 * @param snow the amount of snow on the trail
	 	 */
	public void setSnow(double snow) {
		this.snow = snow;
		if(snow < 0){
			this.snow = 0;
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 	 * Updates the snow field with the change in snow
	 	 * @param snowfall the amount of change in snow
	 	 * @return this.snow the updated amount of snow for the trail
	 	 */
	public double addSnow(double snowfall) {
		snow += snowfall;
		if(this.snow < 0) {
			this.snow = 0;
		}
		this.setChanged();
		this.notifyObservers(this);
		return this.snow;
			
	}

	/**
	 	 * Sets the distance for the trail
	 	 * @param distance the distance to set for the trail
	 	 */
	public void setDistance(double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException();
		}
		this.distance = distance;
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 	 * Gets the trail's ID
	 	 * @return trailID the trail's ID
	 	 */
	public String getTrailID() {
		return trailID;

	}

	/**
	 	 * Gets the trail's name
	 	 * @return trailName the name of the trail
	 	 */
	public String getTrailName() {
		return trailName;

	}

	/**
	 	 * Gets the activities assigned to the trail
	 	 * @return this.activities the activities assigned to the trail
	 	 */
	public SortedArrayList<Activity> getActivities() {
		return this.activities;

	}

	/**
	 	 * Tells if the trail is closed for maintenance or not
	 	 * @return true if the trail is closed for maintenance, false if otherwise
	 	 */
	public boolean closedForMaintenance() {
		return this.closedForMaintenance;

	}

	/**
	  * Gets the amount of snow on the trail
	  * @return snow the amount of snow on the trail
	  */
	public double getSnow() {
		return this.snow;

	}

	/**
	 	 * Gets the distance of the trail
	  * @return distance the distance of the trail
	 	 */
	public double getDistance() {
		return this.distance;

	}
	/**
	 * Gets the Difficulty of the trail
	 * @return this.difficulty the difficulty of the current trail
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;

	}

	/**
	 * Returns whether based on maintenance and snow level the trail is open for the
	 * given activity.
	* @param activity the activity to see if it is open given the conditions
 	 * @return true if the trail is open for the specific activity, false if otherwise
	 */
	public boolean trailOpen(Activity activity) {
		if(activity.getNeedSnow()) {
			if(this.snow > activity.getSnowBoundary() & closedForMaintenance) {
				return true;
			}
			return false;
		} else {
			return this.snow < activity.getSnowBoundary() && closedForMaintenance;
		}
	}

	/**
	 * Returns whether activity is allowed on this trail.
	 * @param activity the activity to see if it is allowed on the trail
 	 * @return true if the activity is allowed, false if otherwise
	 */
	public boolean allow(Activity activity) {
		return closedForMaintenance();
	}

	/**
	 * Two Trail objects are compared on their trailNames. Delegate to the String’s
	 * compareTo method.
	 * @param t the trail to compare this trail to
 	 * @return -1 if this trail is less than the passed in trail,
 	 * 0 if this trail is the same as the passed in trail, and 1
 	 * if this trail is greater than the passed in trail
	 */
	public int compareTo(Trail t) {
		return this.trailName.compareTo(t.getTrailName());

	}

	/**
	 * The string representation of an Activity is the trailName,
	 * closedForMaintenance, snow, distance, difficult and activities fields
	 * separated by tabs.
 	 * @return the string format of the trail
	 */
	public String toString() {
		String actv = "";
		for(int i = 0; i < activities.size() - 1; i++)
			actv += activities.get(i).getName() + "\t";
		actv += activities.get(activities.size() - 1).getName();
		return this.trailName + "\t" + this.closedForMaintenance + "\t" + this.snow +
				 			"\t" + this.distance + "\t" + this.difficulty + "\t" + actv;

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return trailName.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Trail))
			return false;
		Trail other = (Trail) obj;
		return other.trailName.equals(trailName);
	}

}
