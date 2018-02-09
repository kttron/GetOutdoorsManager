package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
/**
 * A representation of an Activity in the GetOutdoorsManager application
 * @author Jack Wasserman
 *
 */
public class Activity extends Observable implements Comparable<Activity>  {
	/** The ID string of the Activity */
	private String activityID;
	/** The name of the Activity */
	private String name;
	/** The description of the Activity */
	private String description;
	/** Tells if the Activity needs snow or not */
	private boolean needSnow;
	/** The amount of snow that the Activity can/cannot happen in */
	private int snowBoundary;

	/**
	 * The behaviors defined for setDescription(), setNeedSnow(), and
	 * setSnowBoundary() apply when constructing an Activity with the given
	 * parameters. That means the constructor should throw an
	 * IllegalArgumentException if activityID, name, or description are null, the
	 * empty string, or string with whitespace only. Leading and trailing whitespace
	 * should be trimmed from activityID, name, and description prior to assigning
	 * to fields. An IllegalArgumentException should also be thrown if snowBoundary
	 * is negative. Observers of Activity are notified of the change.
	 * 
	 * @param activityID The ID string of the Activity
	 * @param name The name of the Activity
	 * @param description The description of the Activity
	 * @param needSnow Tells if the Activity needs snow or not 
	 * @param snowBoundary The amount of snow that the Activity can/cannot happen in
	 */
	public Activity(String activityID, String name, String description, boolean needSnow, int snowBoundary) {
		if(activityID == null || name == null || description == null || name.trim().isEmpty() 
						|| activityID.trim().isEmpty() || description.trim().isEmpty()
						|| snowBoundary < 0) {
			throw new IllegalArgumentException();
		}
		this.setDescription(description.trim());
		this.setNeedSnow(needSnow);
		this.setSnowBoundary(snowBoundary);
		
		this.activityID = activityID.trim();
		this.name = name.trim();
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Sets the description of the Activity
	 * @param description of the Activity
	 */
	public void setDescription(String description) {
		if(description == null || description.trim().isEmpty() ) {
			throw new IllegalArgumentException("Description can not be null" );
		}
		this.description = description.trim();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * The needSnow field is set and Observers of Activity.
	 * 
	 * @param needSnow tells if the Activity needs snow or not
	 */
	public void setNeedSnow(boolean needSnow) {
		this.needSnow = needSnow;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Gets the Activity's needSnow field
	 * 
	 * @return needSnow true if the Activity needs snow, false if otherwise
	 */
	public boolean getNeedSnow() {
		return this.needSnow;
	}

	/**
	 * Sets the Activity's snow boundary
	 * @param snowBoundary the amount of snow the activity can operate in or operate without
	 */
	public void setSnowBoundary(int snowBoundary) {
		if (snowBoundary < 0) {
			throw new IllegalArgumentException();
		}
		this.snowBoundary = snowBoundary;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Gets the Activity's ID
	 * @return activityID the Activity's ID
	 */
	public String getActivityID() {
		return activityID;

	}

	/**
	 * Gets the name of the Activity
	 * @return name of the Activity
	 */
	public String getName() {
		return name;

	}

	/**
	 * Gets the description of the Activity
	 * @return description of the Activity
	 */
	public String getDescription() {
		return description;

	}

	/**
	 * Tells if snow is needed for the Activity to happen 
	 * @return needSnow true if snow is needed for the Activity to happen, false if otherwise
	 */
	public boolean snowNeeded() {
		return needSnow;

	}

	/**
	 * Gets the Activity's snow boundary
	 * @return snowBoundary of the Activity
	 */
	public int getSnowBoundary() {
		return snowBoundary;

	}

	/**
	 * The string representation of an Activity is the name, description, needSnow,
	 * and snowBoundary fields separated by tabs.
	 * (name\tdescription\tneedSnow\tsnowBoundary)
	 * 
	 * @return the string format of the Activity
	 */
	public String toString() {
		return this.name + "\t" + this.description + "\t" + this.needSnow + "\t" + this.snowBoundary;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if   (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Two Activity objects are compared on their names. Delegate to the String's
	 * compareTo method.
	 * 
	 * @param o the activity to compare to
	 * @return true if they are equivalent, false if otherwise
	 */
	public int compareTo(Activity o) {
		if (this.name.compareTo(o.getName()) < 0) {
			return -1;
		} else if (this.name.compareTo(o.getName()) == 0) {
			return 0;
		} else if (this.name.compareTo(o.getName()) > 0) {
			return 1;
		}
		return this.name.compareTo(o.getName());
	}

}
