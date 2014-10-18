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
    boolean isAdd = false;
    if(tlList.isEmpty()){
        tlList.add(tweet);
        isAdd = true;

    }//If list is empty, add a new tweet
    else{
        for(int i = 0; i < tlList.size(); i++){
            if(!isAdd){
                if(tlList.get(i).getTime() > tweet.getTime()){
                    tlList.add(i,tweet);
                    isAdd = true;
                }
            }
        }
    }//Compare the time and add tweet into timeline
    if(!isAdd){
        tlList.add(tweet);
    }//If tweet is not added yet, add it into the list
    }
    
    
    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets) {
		for(int i = 0; i < tweets.size(); i++){
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
	    // careful when removing to not skip over a tweet...
        for(int i=1; i<tlList.size(); i++){
            if(tlList.get(i).getUser().equals(user)){
                Tweet test = tlList.remove(i);
                i--;

            }
        }//a for loop works too

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
	    for (int i = 1; i < tlList.size(); i++) {
		if (tlList.get(i).getMessage().contains(keyword)) {//shouldn't it be case-sensitive? There's a post on piazza that says everything in P2 is case-sensitive
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
	for (int i = 1; i < tlList.size(); i++) {
	    tlList.get(i).print();

	}   
    }
    

    /**
     * Print each tweet in the timeline since time
     * 
     * @param time the largest time to print tweets
     */
    public void print(int time){
	for (int i = 1; i < tlList.size(); i++) {
	    if (tlList.get(i).getTime() <= time) {
		tlList.get(i).print();
	    }
	}   
    }
}

