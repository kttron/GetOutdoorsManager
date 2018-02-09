package edu.ncsu.csc216.get_outdoors.util;


/**
 * Custom Sorted Array List data structure
 * @author Jack Wasserman
 *
 * @param <E> the generic object
 */
public class SortedArrayList<E extends Comparable<E>> {
	
	/** The number to multiply by when the capacity is reached to resize the Sorted Array List */
	private static final int RESIZE = 10;
	/** The array of generic objects */
	private E[] list;
	/** The amount of element Sorted Array List */
	private int size;
	/** The length of the Sorted Array List */
	public int length;

	/**
	 * Constructor for the Sorted Array List
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayList() {
		this.list = (E[]) new Comparable[RESIZE];
		this.size = 0;
		this.length = 0;
	}
	
	/**
	 * Constructor for the Sorted Array List with a size parameter
	 * @param sizeInput of the Sorted Array List to construct
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayList(int sizeInput) {
		if (sizeInput < 0) {
			throw new IllegalArgumentException();
		}
		this.list = (E[]) new Comparable[sizeInput];
		this.size = 0;
		this.length = sizeInput;
	}
	
	/**
	 * Returns the size of the Sorted data structure
	 * @return size the size of the Sorted data structure
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns if the Sorted data structure is empty or not
	 * @return true if the Sorted data structure is empty, false if otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns if the Sorted data structure contains the object or not
	 * @param object to check is in the Sorted data structure
	 * @return true if the object is in the Sorted data structure, false if otherwise
	 */
	public boolean contains(E object) {
		for (int i = 0; i < this.size; i++) {
			if(list[i].equals(object)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds the object to the Sorted data structure
	 * @param object to add to the Sorted data structure
	 * @return true if the object is added, false if otherwise
	 */
	public boolean add(E object) {
		if (object == null) {
			throw new NullPointerException();
		}
		
		int idx = getIndex(object); //Index to add the object to
		
		if (this.size == this.list.length) {
			growArray();
		}
		
		//Shifts all elements that are behind idx over to right
		for (int i = this.size; i > idx; i--) {
			this.list[i] = this.list[i - 1];
		}
		
		this.list[idx] = object;
		
		size++;
		
		return true;
		
	}
	
	/**
	 * Gets the object at the specified index
	 * @param index to get the object at
	 * @return object at the specified index
	 */
	public E get(int index) {
		if (index < 0 || index > this.size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	/**
	 * Removes the object at the specified index
	 * @param index the index to remove the object from
	 * @return object the was removed from the Sorted data structure
	 */
	public E remove(int index) {
		if (index < 0 || index > this.size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		E removed = this.list[index];
		this.list[index] = null;
		
		int i;
		for (i = ++index; i < this.list.length; i++) {
			this.list[i - 1] = this.list[i];
		}
		
		this.list[i - 1] = null;
		size--;
		
		return removed;
	}
	
	/**
	 * Gets the index of the specified object in the Sorted data structure
	 * @param object to get the index of
	 * @return the index that the object is located in the Sorted data structure
	 */
	public int indexOf(E object) {
		if (this.size == 0 || object == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < this.list.length; i++) {
			if (list[i].equals(object)) {
				return i;
			}
		}
		return -1; //Returns -1 if element is not in list
	}
	
	/**
	 * Converts the Sorted Array List to a string with a structure of
	 * [object1, object2, object3]
	 * @return s the Sorted Array List in string format
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < this.size; i++) {
			if (i != this.size - 1) {
				s += list[i].toString() + ", ";
			} else {
				s += list[i].toString();
			}
		}
		s += "";
		return s;
	}
	
	/**
	 * Resizes the array when capacity is reached
	 * Inner code copied from lab
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = list;
		list = (E[]) new Comparable[this.list.length + RESIZE];
		for (int i = 0; i < this.size; i++) {
			list[i] = temp[i];
		}
		this.length = list.length;
	}
	
	/**
	 * Gets the index where the object should be added
	 * @return number the index the object should be added to
	 */
	private int getIndex(E object) {
		if (isEmpty()) {
			return 0;
		}
		for (int i = 0; i < this.size; i++) {
			if (object.compareTo(list[i]) < 0) {
				return i;
			} else if (object.compareTo(list[i]) == 0) {
				return i;
			}
		}
		return this.size;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + size;
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
		if (!(obj instanceof SortedArrayList))
			return false;
		@SuppressWarnings("unchecked")
		SortedArrayList<E> other = (SortedArrayList<E>) obj;
		if (size != other.size)
			return false;
		return true;
	}
}




