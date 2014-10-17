///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  Twitter.java 
// File:             SimpleLinkedList.java
// Semester:         CS367 Fall 2014
//
// Author:           Andrew Hard 
// Email:            hard@wisc.edu
// CS Login:         hard
// Lecturer's Name:  Jim Skrentny 
// Lab Section:      LEC-002 (77632)
//
///////////////////////////////////////////////////////////////////////////////
//
// Pair Partner:     Wayne Chew
// Email:            mchew2@wisc.edu
// CS Login:         mchew
// Lecturer's Name:  Jim Skrentny
// Lab Section:      LEC-001 (77631)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;

/**
 * The SimpleLinkedList class implements the ListADT using
 * a doubly linked chain of nodes.
 *
 * @author Ming Chew
 * @author Andrew Hard
 */
public class SimpleLinkedList<E> implements ListADT<E> {
    
    // The private objects:
    private int numItems;
    private DblListnode<E> head;
    private DblListnode<E> tail;// keep tail to make add() O(1)
    
    DblListnode<E> newDblLn;    // to create new DblListnodes
    DblListnode<E> curr;        // for traversing chain
    int currIndex;              // track traversing position
    
    
    /**
     * Constructs an empty list
     */
    public SimpleLinkedList() {
	head = null;
	tail = null;
	numItems = 0;
    }
    
    
    /**
     * Adds item to the end of the List.
     * 
     * @param item the item to add
     * @throws IllegalArgumentException if item is null 
     */
    public void add(E item) {
	
	if (item == null) {
	    throw new IllegalArgumentException();
	}
	
	else {
	    if (numItems == 0) {
		head = new DblListnode<E>(item);
		tail = head;
	    }
	    
	    else {
		tail.setNext(new DblListnode<E>(item));
		tail.getNext().setPrev(tail);
		tail = tail.getNext();
	    }
	    
	    numItems++;
	}
    }
    
    
    /**
     * Adds item at position pos in the List, moving the items originally in 
     * positions pos through size() - 1 one place to the right to make room.
     * 
     * @param pos the position at which to add the item
     * @param item the item to add
     * @throws IllegalArgumentException if item is null 
     * @throws IndexOutOfBoundsException if pos is less than 0 or greater 
     * than size()
     */
    public void add(int pos, E item) {
	
	if (item == null) {
	    throw new IllegalArgumentException();
	}
	
	else if (pos < 0 || pos >= numItems) {
	    throw new IndexOutOfBoundsException();
	}
	
	else {
	    // special case of size 0:
	    if (numItems == 0) {
		head = new DblListnode<E>(item);
		tail = head;
	    }
	    
	    // general case:
	    else {
		curr = head;
		currIndex = 0;
		while (curr.getNext() != null && currIndex < pos) {
		    curr = curr.getNext();
		    currIndex++;
		}
		// arrived at pos. Insert new node ahead of curr (which is at pos)
		curr.setPrev(new DblListnode<E>(item, curr.getPrev(), curr));
	    }
	    numItems++;
	}
    }
    
    
    /**
     * Returns true iff item is in the List (i.e., there is an item x in the List 
     * such that x.equals(item))
     * 
     * @param item the item to check
     * @return true if item is in the List, false otherwise
     */
    public boolean contains(E item) {
	curr = head;
	while (curr.getNext() != null) {
	    if (curr.getData() == item) {// should this be == or .equals?
		return true;
	    }
	    curr = curr.getNext();
	}
	return false;
    }
    
    
    /**
     * Returns the item at position pos in the List.
     * 
     * @param pos the position of the item to return
     * @return the item at position pos
     * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
     * or equal to size()
     */
    public E get(int pos) {

	if (pos < 0 || pos >= numItems) {
	    throw new IndexOutOfBoundsException();
	}
	
	curr = head;
	currIndex = 0;
	while (curr.getNext() != null && currIndex < pos) {
	    curr = curr.getNext();
	    currIndex++;
	}
	return curr.getData();
    }
    
    
    /**
     * Returns true iff the List is empty.
     * 
     * @return true if the List is empty, false otherwise
     */
    public boolean isEmpty() {
	return (numItems == 0);
    }
    
    
    /**
     * Removes and returns the item at position pos in the List, moving the items 
     * originally in positions pos+1 through size() - 1 one place to the left to 
     * fill in the gap.
     * 
     * @param pos the position at which to remove the item
     * @return the item at position pos
     * @throws IndexOutOfBoundsException if pos is less than 0 or greater than
     * or equal to size()
     */
    public E remove(int pos) {
	
	if (pos < 0 || pos >= numItems) {
	    throw new IndexOutOfBoundsException();
	}
	
	curr = head;
	currIndex = 0;
	while (curr.getNext() != null && currIndex < pos) {
	    curr = curr.getNext();
	    currIndex++;
	}
	
	// remove current node and redo links:
	curr.getPrev().setNext(curr.getNext());
	curr.getNext().setPrev(curr.getPrev());
	return curr.getData();
    }
    
    
    /**
     * Returns the number of items in the List.
     * 
     * @return the number of items in the List
     */
    public int size() {
	return numItems;
    }
    
}
