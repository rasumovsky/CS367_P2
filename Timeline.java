///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  Twitter.java 
// File:             Timeline.java
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
 * The Timeline class uses SimpleLinkedList class to build a time ordered 
 * list of following tweets. Tweets with smaller Time fields should come 
 * earlier in the list.
 */
class Timeline{
    

    private ListADT<Tweet> tlList;// implement via SimpleLinkedList class
    //private List<Tweet> tlList;// implement via SimpleLinkedList class

    
    
    /**
     * Constructs an empty timeline
     */
    public Timeline() {

	tlList = new SimpleLinkedList<Tweet>();
	//tlList = new LinkedList<Tweet>();

    }
    

    /**
     * Adds a single tweet to the Timeline
     * 
     * @param tweet the tweet to add
     */
    public void add(Tweet tweet) {
	boolean wasAdded = false;
	int tlIndex = 0;

    //Compare time of tweet and add it into list
	while (!wasAdded && tlIndex < tlList.size()) {
	    if (tweet.getTime() < tlList.get(tlIndex).getTime()) {
		tlList.add(tlIndex,tweet);
		wasAdded = true;
	    }
	    tlIndex++;
	}
	// If tweet is not added yet, add it into the list:
	if (!wasAdded) {
	    tlList.add(tweet);
	}
    }
    
        
    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets) {
	for (int i = 0; i < tweets.size(); i++) {
	    this.add(tweets.get(i));
        }    
    }
    
    
    /**
     * Removes all tweets by user from the Timeline
     * 
     * @param user the user whose tweets should be removed
     */
    public void remove(String user) {
	
	if (user != null) {
	   
	    int tlIndex = 0;
	    // loop through list to check for and remove user tweets
	    while (tlIndex < tlList.size()) {
		
		if (tlList.get(tlIndex).getUser().equals(user)) {
		    tlList.remove(tlIndex);
		}
		else {
		    tlIndex++;// only move forward if nothing removed
		}
	    }
	}
    }
    
    
    /**
     * Create a new Timeline containing only the tweets containing keyword
     * 
     * @param keyword the keyword to search for
     * @return a Timeline of tweets containing keyword
     */
    public Timeline search(String keyword) {
	
	Timeline newTimeline = new Timeline(); 
	if (keyword != null) {
	    
        //search for tweets that contain keyword
	    for (int i = 0; i < tlList.size(); i++) {
		//case-sensitive keyword search:
		if (tlList.get(i).getMessage().contains(keyword)) {
		    newTimeline.add(tlList.get(i));
		}
	    }
	}
	return newTimeline;
    }
    
    
    /**
     * Print each tweet in the timeline
     */
    public void print() {
	for (int i = 0; i < tlList.size(); i++) {
	    tlList.get(i).print();
	}
    }
    

    /**
     * Print each tweet in the timeline since time
     * 
     * @param time the largest time to print tweets
     */
    public void print(int time) {
	if (tlList.size() > 0) {
	    int index = 0;
	    while (index < tlList.size() 
		   && tlList.get(index).getTime() < time) {
		tlList.get(index).print();
		index++;
		
	    }
	}
    }

}

