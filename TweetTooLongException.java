///////////////////////////////////////////////////////////////////////////////
// 
// Main Class File:  Twitter.java 
// File:             TweetTooLongException.java
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

/**
 * The TweetTooLongException class extends the Exception class, which means
 * is is a checked exception. 
 * @author Ming Chew
 * @author Andrew Hard 
 */
public class TweetTooLongException extends Exception {
 
    public TweetTooLongException() {
	super();
    }
    
    public TweetTooLongException(String message) {
	super(message);
    }
    
}
