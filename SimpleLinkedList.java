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
    private DblListnode<E> head; //Do you want to create a header node?
    private DblListnode<E> tail; // keep tail to make add() O(1)
    
    private DblListnode<E> newDblLn;    // to create new DblListnodes
    private DblListnode<E> curr;        // for traversing chain
    private int currIndex;              // track traversing position //Do we need this?
    
    
    /**
     * Constructs an empty list
     */
    public SimpleLinkedList() {
	head = new DblListnode<E>(null);
	tail = head;
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
	
	tail.setNext(new DblListnode<E>(item,tail,null));
	tail = tail.getNext();
		
	numItems++;
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
	
	else if (pos < 0 || pos > numItems) {
	    throw new IndexOutOfBoundsException();
	}
	
	if(pos == numItems){
		this.add(item);
	}
		
	else{
		this.get(pos);
		DblListnode<E> newDblLn = new DblListnode<E>(item,curr.getPrev(),curr);
		curr.getPrev().setNext(newDblLn);
		curr.setPrev(newDblLn);
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
    if(item == null){
		throw new IllegalArgumentException();
	}
			
	curr = head.getNext();

	while (curr.getNext() != null) {
	    if (curr.getData().equals(item)) {// should this be == or .equals? // should be .equals
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
		
	for(int i = 0; i <= pos; i++){
		curr = curr.getNext();
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
	
	E item = this.get(pos);
	curr.getPrev().setNext(curr.getNext());

	if(curr.getNext() != null){//Check whether its the last item in the list			
		curr.getNext().setPrev(curr.getPrev());
	}
	
	numItems--;

	return item;
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
