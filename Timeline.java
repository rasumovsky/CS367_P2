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
    
    private ListADT<Tweet> tlList;//will implement via SimpleLinkedList class
        
    
    /**
     * Constructs an empty timeline
     */
    public Timeline() {
	tlList = new SimpleLinkedList<Tweet>();
    }
    

    /**
     * Adds a single tweet to the Timeline
     * 
     * @param tweet the tweet to add
     */
    public void add(Tweet tweet) {
	boolean wasAdded = false;
	
	// If list is empty, add a new tweet:
	if (tlList.isEmpty()) {
	    tlList.add(tweet);
	    wasAdded = true;
	}
	// Else compare the time and add tweet into timeline:
	else{
	    for (int i = 0; i < tlList.size(); i++) {
		if (!wasAdded) {
		    if (tweet.getTime() < tlList.get(i).getTime()) {
			tlList.add(i,tweet);
			wasAdded = true;
		    }
		}
	    }
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
	    
	    // careful when removing to not skip over a tweet...
	    // Shouldn't this start from zero? The zeroth element in the timeline should be nonzero. 
	    for (int i = 0; i < tlList.size(); i++ ) {// <------ should it be this?
	    //for (int i = 1; i < tlList.size(); i++ ) {// <------ I don't understand this...
		
		if (tlList.get(i).getUser().equals(user)) {
		    Tweet test = tlList.remove(i);
		    i--;
		}
	    }
	    
	    /*while (tlIndex < tlList.size()) {
	      
	      if (tlList.get(tlIndex).getUser().equals(user)) {
	      tlList.remove(tlIndex);
	      }
	      
	      else {
	      tlIndex++;
	      }
	      }*/
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
	    // must start from zero in loop over list elements:
	    for (int i = 0; i < tlList.size(); i++) {
		if (tlList.get(i).getMessage().contains(keyword)) {//case-sensitive
		    newTimeline.add(tlList.get(i));
		}
	    }
	}
	return newTimeline;
    }
    
    
    /**
     * Print each tweet in the timeline
     */
    public void print(){
	//for (int i = 1; i < tlList.size(); i++) {
	for (int i = 0; i < tlList.size(); i++) {
	    tlList.get(i).print();
	}   
    }
    

    /**
     * Print each tweet in the timeline since time
     * 
     * @param time the largest time to print tweets
     */
    public void print(int time){
	// start with index 0 in order to print 1st element
	int index = 0;
	while (tlList.get(index).getTime() < time) {
	    tlList.get(index).print();
	    index++;
	}
    }
}

