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
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
 */
class Timeline{

    // NB: It would be useful to construct an iterator class for SimpleLinkedList...
    // Would have to be an independent class, since we are not allowed to add public methods.

    //private SimpleLinkedList<Tweet> tlList;
    private ListADT<Tweet> tlList;
    private int currPos;

    
    /**
     * Constructs an empty timeline
     */
    public Timeline() {
	tlList = new SimpleLinkedList<Tweet>();
	currPos = 0;
    }
    

    /**
     * Adds a single tweet to the Timeline
     * 
     * @param tweet the tweet to add
     */
    public void add(Tweet tweet) {
	
	for (int i = 0; i < tlList.size(); i++) {
	    if (tweet.getTime() < tlList.get(currPos).getTime()) {
		tlList.add(i,tweet);
		break;
	    }
	}
    }
    
    
    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets) {
	
	if (tweets != null) {
	    
	    Iterator<Tweet> twtItr = tweets.iterator();
	    while (twtItr.hasNext()) {
		this.add(twtItr.next());
	    }
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
	    // careful when removing to not skip over a tweet...
	    while (tlIndex < tlList.size()) {
	
		if (tlList.get(tlIndex).getUser().equals(user)) {
		    tlList.remove(tlIndex);
		}
		
		else {
		    tlIndex++;
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
	    for (int i = 0; i < tlList.size(); i++) {
		if (tlList.get(i).getMessage().toLowerCase().contains(keyword.toLowerCase())) {
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
	for (int i = 0; i < tlList.size(); i++) {
	    if (tlList.get(i).getTime() > time) {
		tlList.get(i).print();
	    }
	}   
    }
}

