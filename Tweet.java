///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  Twitter.java 
// File:             Tweet.java
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
 * Stores the time, message and user of a Tweet
 *
 * @author Ming Chew
 * @author Andrew Hard
 * @author CS367 Personnel
 */
class Tweet{
    
    // Self explanatory private tweet variables:
    private int tweetTime;
    private String tweetMessage;
    private String tweetUser;
    
    
    /**
     * Constructs a Tweet for user with message at time. 
     * Throws a TweetTooLongException if message over 140 characters.
     * 
     * @param time time at which tweet occured
     * @param message message of the tweet, <=140 characters
     * @param user the person who tweeted the tweet 
     * @throws TweetTooLongException if message over 140 characters 
     */
    public Tweet(int time, String message, String user) throws TweetTooLongException {
	try {
	    // should we check for time == null and how?// time <0?
	    if (message == null || user == null || time < 0) {
		throw new IllegalArgumentException();
	    }
	    if (message.length() > 140) {
		throw new TweetTooLongException();
	    }
	    tweetTime = time;
	    tweetMessage = message;
	    tweetUser = user;
	}
	//catch (TweetTooLongException e1) {// Should we remove the catch to see if it crashes?
	//}
	catch (IllegalArgumentException e2) {// ditto
	}
    }
    
    
    /** 
     * Returns the stored message of the Tweet
     * @return the message
     */
    public String getMessage() {
	return tweetMessage;
    }
    
    
    /** 
     * Returns the stored time of the Tweet
     * @return the stored time
     */
    public int getTime() {
	return tweetTime;
    }

    
    /** 
     * Returns the user who tweeted the Tweet
     * @return the user
     */
    public String getUser() {
	return tweetUser;
    }
    
    
    /** 
     * Print the Tweet with the following format: <TIME> <USER>:<MESSAGE>
     */
    public void print() {
	if (tweetUser != null && tweetMessage != null) {
	    System.out.printf("%d %s:%s\n", tweetTime, tweetUser, tweetMessage);
	}
    }
}


