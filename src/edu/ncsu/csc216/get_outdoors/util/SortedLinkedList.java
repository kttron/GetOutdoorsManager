package edu.ncsu.csc216.get_outdoors.util;

import java.util.Iterator;

/**
 * Custom Sorted Linked List data structure
 * 
 * @author Jack Wasserman
 * @author kavitpatel
 *
 * @param <E>
 *            the generic object
 */
public class SortedLinkedList<E extends Comparable<E>> implements Iterable<E> {
	/** Reference to the first node in the chain */
	private Node<E> head;
	private int numOfEntries;

	/**
	 * Constructor for the Sorted Linked List
	 */
	public SortedLinkedList() {
		head = null;
	}

	// Inserts a new node at the beginning of this list.
	private void addFirst(E item) {
		head = new Node<E>(item, head);
	}

	/**
	 * Returns if the Sorted data structure contains the object or not
	 * 
	 * @param object
	 *            to check is in the Sorted data structure
	 * @return true if the object is in the Sorted data structure, false if
	 *         otherwise
	 */
	public boolean contains(E object) {
		for (E tmp : this) {
			if (tmp.equals(object)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the object at the specified index
	 * 
	 * @param index
	 *            to get the object at
	 * @return object at the specified index
	 */
	public E get(int index) {
		if (index < 0 || index >= this.numOfEntries) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> tmp = head;
		for (int k = 0; k < index; k++)
			tmp = tmp.next;

		if (tmp == null)
			throw new IllegalArgumentException();

		return tmp.value;
	}

	/**
	 * Converts the Sorted Linked List to a string
	 * 
	 * @return s the Sorted Linked List in string format
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		int count = 0;
		for (Object x : this) {
			count++;
			if (count == this.numOfEntries) {
				result.append(x);
			} else {
				result.append(x + " ");
			}	
		}
		return result.toString();
	}

	/**
	 * Removes the object at the specified index
	 * 
	 * @param index
	 *            the index to remove the object from
	 * @return object the was removed from the Sorted data structure
	 */
	public E remove(int index) {
		if (index < 0 || index >= this.numOfEntries) {
			throw new IndexOutOfBoundsException();
		}

		if (head == null)
			throw new NullPointerException("cannot remove");
		
		// if head then replace with the next value.
		if (index == 0) {
			E first = head.value;
			head = head.next;
			this.numOfEntries--;
			return first;
		}

		int i = 0;
		Node<E> cur = head;
		Node<E> prev = null;
		
		// this stops at previous.
		while (cur != null && i < index) {
			prev = cur; // prev is replaced by current.
			cur = cur.next; // current is replaced by next.
			i++;
		}
		if(cur == null)
			return null;
		// delete cur node (current node is the prev.next). we replace it with the next value.
		if (cur != null && cur.value != null) {
			prev.next = cur.next;
			this.numOfEntries--;
		}
		
		return cur.value;
	}

	/**
	 * Creates an iterator object for the SortedLinkedList
	 * @return SortedLinkedListIterator the iterator for the SortedLinkedList
	 */
	public Iterator<E> iterator() {
		return new SortedLinkedListIterator();
	}

	/**
	 * Returns the size of the Sorted data structure
	 * 
	 * @return size the size of the Sorted data structure
	 */
	public int size() {
		return numOfEntries;

	}

	/**
	 * Returns if the Sorted data structure is empty or not
	 * 
	 * @return true if the Sorted data structure is empty, false if otherwise
	 */
	public boolean isEmpty() {
		return head == null;

	}

	/**
	 * Adds the object to the Sorted data structure
	 * 
	 * @param object
	 *            to add to the Sorted data structure
	 * @return true if the object is added, false if otherwise
	 */
	public boolean add(E object) {
		if (object == null) {
			throw new NullPointerException();
		}
		if (head == null) {
			this.addFirst(object); // first item.
			this.numOfEntries++;
		}
		// if head is greater than object then object must be the first.
		if (head.value.compareTo(object) > 0) {
			this.addFirst(object);
			this.numOfEntries++;
			return true;
		}

		Node<E> cur = head;
		Node<E> prev = null;

		// for sorting keep comparing for sorting until current is greater than or equal to object.
		// when current value is greater than object then insert object before the
		// current.
		while (cur != null && cur.value.compareTo(object) < 0) {
			prev = cur;
			cur = cur.next;
		}
		
		// if cur is null then it is end of the list. add the object.
		if (cur == null) {
			prev.next = new Node<E>(object, cur);
			this.numOfEntries++;
			return true;
		}

		// now insert the object before the current value;
		// remove the if !=0 condition if duplicate value is allowed.
		if (cur != null && cur.value.compareTo(object) != 0) { 
			prev.next = new Node<E>(object, cur);
			this.numOfEntries++;
			return true;
		}

		return false;

	}

	/**
	 * Gets the index of the specified object in the Sorted data structure
	 * 
	 * @param object
	 *            to get the index of
	 * @return the index that the object is located in the Sorted data structure
	 */
	public int indexOf(E object) {
		int index = 0;
		for (E tmp : this) {
			if (tmp.equals(object)) {
				return index;
			}
			index++;
		}
		return -1;
	}



	/**
	 * Represents a list node
	 * 
	 * @author Jack Wasserman
	 *
	 * @param <E>
	 *            the generic object type
	 */
	@SuppressWarnings("hiding")
	private class Node<E> {
		private E value;
		private Node<E> next;

		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
	
	}
	
	/**
	 * Inner class that handles the iterator for the SortedLinkedList
	 * @author Jack Wasserman
	 *
	 */
	private class SortedLinkedListIterator implements Iterator<E> {
		private Node<E> nextNode;

		public SortedLinkedListIterator() {
			nextNode = head;
		}

		public boolean hasNext() {
			return nextNode != null;
		}

		public E next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			} else {
				E val = nextNode.value;
				nextNode = nextNode.next;
				return val;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result += prime * result;
		if(head == null) {
			result += 0;
		} else {
			result += head.hashCode();
		}
		result += prime * result + numOfEntries;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}

}
