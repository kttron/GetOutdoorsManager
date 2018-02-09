package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
/**
 * Concrete class that handles park objects
 * @author Jack Wasserman
 *
 */
public class Park extends Observable implements Comparable<Park> {
	/** The Park's ID */
	private String parkID;
	/** The Park's name */
	private String name;
	/** The Park's description */
	private String description;
	/** The Park's change in snow */
	private double snowChange;

	/**
	 * Constructor for the Park object
	 * @param parkID the park's ID
	 * @param name the park's name
	 * @param description the park's description
	 */
	public Park(String parkID, String name, String description) {
		if(parkID == null || name == null || description == null || parkID.trim().isEmpty() || name.trim().isEmpty() || description.trim().isEmpty()) {
			throw new IllegalArgumentException("parameters can't be null");
		}
		this.parkID = parkID.trim();
		this.name = name.trim();
		this.description = description.trim();
		this.snowChange = 0;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * Constructor for the Park object with an added snowChange parameter
	 * @param parkID the park's ID
	 * @param name the park's name
	 * @param description the park's description
	 * @param snowChange the park's change in snow
	 */
	public Park(String parkID, String name, String description, double snowChange) {
		if(parkID == null || name == null || description == null || parkID.trim().isEmpty() || name.trim().isEmpty() || description.trim().isEmpty()) {
			throw new IllegalArgumentException("parameters can't be null");
		}
		this.parkID = parkID.trim();
		this.name = name.trim();
		this.description = description.trim();
		this.snowChange = snowChange;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Gets the Park's name
	 * @return name of the park
	 */
	public String getName() {
		return name;

	}
	
	/**
	 * Gets the Park's ID
	 * @return parkID the park's ID
	 */
	public String getParkID() {
		return this.parkID;

	}

	/**
	 * Gets the Park's description
	 * @return description the park's description
	 */
	public String getDescription() {
		return description;

	}

	/**
	 * Gets the Park's change in snow
	 * @return snowChange the park's change in snow
	 */
	public double getSnowChange() {
		return snowChange;

	}

	/**
	 * Sets the Park's change in snow
	 * @param snowChange the double to be set to the change park's change in snow
	 */
	public void setSnowChange(double snowChange) {
		this.snowChange = snowChange;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Two Park objects are compared on their names. Delegate to the String's
	 * compareTo method.
	 * 
	 * @param p the park to compare to the current park object
	 * @return true if parks have the same name, false if otherwise
	 */
	public int compareTo(Park p) {
		if (this.name.compareTo(p.getName()) < 0) {
			return -1;
		} else if (this.name.compareTo(p.getName()) == 0) {
			return 0;
		} else  {
			return 1;
		}
	}

	/**
	 * The string representation of a Park is the name, description, and snowChange
	 * fields separated by tabs. (name\tdescription\tsnowChange)
	 * 
	 * @return the park in string format
	 */
	public String toString() {
		return this.name + "\t" + this.description + "\t" + this.snowChange;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parkID == null) ? 0 : parkID.hashCode());
		long temp;
		temp = Double.doubleToLongBits(snowChange);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		if (!(obj instanceof Park))
			return false;
		Park other = (Park) obj;
		if (parkID == null || other.parkID == null) {
				return false;
		} else if (!parkID.equals(other.parkID))
			return false;
		if (Double.doubleToLongBits(snowChange) != Double.doubleToLongBits(other.snowChange))
			return false;
		return true;
	}

}
