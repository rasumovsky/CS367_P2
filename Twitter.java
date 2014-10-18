////////////////////////////////////////////////////////////////////////////////
// 
// Title:            Twitter
// Files:            Twitter.java, Timeline.java, Tweet.java 
//                   SimpleLinkedList.java, TweetTooLongException.java, 
//                   ListADT.java, DblListnode.java
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
import java.io.File;
import java.io.FileNotFoundException;

/** 
 * The Twitter class reads user files as command-line inputs. All users are 
 * initially followed. The program creates a list of users that are followed,
 * and creates a Timeline object to represent the time line of tweets 
 * associated with the followed users. 
 */

public class Twitter{

    /** 
     * Search for a String in a list of Strings.
     *
     * @param list The list of Strings to search.
     * @param item The item to find in the list of Strings.
     * @return true iff item is found in list.
     */
    public static boolean Compare(List<String> list, String item){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(item)){
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * The main method creates an instance of Timeline and
     * then prompts the user for options to follow or unfollow
     * users, search tweets, and print or list information.
     *
     * @param args[] The input files associated with all users.
     */
    public static void main(String[] args) throws FileNotFoundException,TweetTooLongException{
	
	// Check that at least one user file has been provided:
	if (args.length < 1) {
	    System.out.println("Usage: java Twitter FileName1 Filename2...");
	    System.exit(0);
	}
	
	// Create instances of important variables:
	List<List<Tweet>> tweets = new ArrayList<List<Tweet>>();//contains list of tweets sort by user
	List<Tweet> userTweets;//contain tweets
	
	Timeline followTimeline = new Timeline();//contains only the tweet the user is following
	List<String> currUser = new ArrayList<String>();//contains all user
	List<String> followUser = new ArrayList<String>();//contains only followed user
	String user;
	
	
	// Loop over input files (one per user):
	for (int i = 0; i < args.length; i++) {
	    File dataFile = new File(args[i]);
	    
	    //currUser.args[i].replaceAll(".txt","");
	    user = args[i].replace(".txt","");
	    currUser.add(user);
	    followUser.add(user);
	    
	    // Check whether input file exists and is readable: 
	    if (!dataFile.exists()) {
		System.out.println("Error: cannot access input file");
		System.exit(0);
	    }
	    
	    // Load the data from the input file:
	    Scanner inputFile = new Scanner(dataFile);
	    
	    // Loop through the current input file:
	    // Each line is one tweet
	    userTweets = new ArrayList<Tweet>();//store a list of tweets
	    while (inputFile.hasNext()) {
		
		String currLine = inputFile.nextLine();
		
		// Split the line into two strings at the colon:
		String delims = ":";
		String[] splitLine = currLine.split(delims);
		userTweets.add(new Tweet(Integer.parseInt(splitLine[0]), splitLine[1], currUser.get(i)));
	    }
	    inputFile.close();
	    tweets.add(userTweets);//list storing lists of tweets by user
	}
	
	for (int i = 0; i < tweets.size(); i++) {
            followTimeline.add(tweets.get(i));
	}
	
	
	// Initialize console input:
        Scanner stdin = new Scanner(System.in);
	
        boolean done = false;
        while (!done) {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();
	    
            //only do something if the user enters at least one character
            if (input.length() > 0) {
                String[] commands = input.split("[ ]+");//split on space
		
		switch (commands[0]) {
		    
		    
		    
		case "list": // Case 1: list all or following users
		    if (commands.length == 1) {
			System.out.println("Invalid command");
			break;
		    }
		    switch (commands[1]) {
		    case "users":
			for (int i = 0; i < currUser.size(); i++) {
			    System.out.println(currUser.get(i));
			}
			break;//case users
			
		    case "following":
			for (int i = 0; i < followUser.size(); i++) {
			    System.out.println(followUser.get(i));
			}
			break;
		    }
		    break;

		    
		    
		case "follow": // Follow an existing user
		    user = commands[1];
		  
		    // First check that user exists:
		    if (Compare(currUser,user)) {

			// If they are not yet followed, follow
			if (!Compare(followUser, user)) {
			    followUser.add(user);
			    
			    for (int i = 0; i < tweets.size(); i++) {
				if (tweets.get(i).get(0).getUser().equals(user)) {
				    followTimeline.add(tweets.get(i));
				}
			    }
			}
			
			else {// If user already followed:
			    System.out.println("Warning: User already followed");
			}
		    }
		    else {// If user does not exist:
			System.out.println("Invalid user");
		    }
		    
		    break;
		    
		    
		    
		case "unfollow": // Unfollow an existing user
		    user = commands[1];
		    
		    // First check that user exists:
		    if (Compare(currUser,user)) {
			
			// If they are already followed, unfollow:
			if (Compare(followUser, user)) {
			    followUser.remove(user);
			    followTimeline.remove(user);//remove tweets from timeline
			}
			else {// If user already not followed:
			    System.out.println("Warning: User not followed");
			}
		    }
		    else {// If user does not exist:
			System.out.println("Invalid user");
		    }
		    break;
		    
		    
		    
		case "search": // Search for tweets that contain a string
		    String string = commands[1];
		    followTimeline.search(string).print();
		    
		    break;
		    
		    
		    
		case "print": // Prints the timeline
		    if (commands.length == 1) {// print entire timeline
			followTimeline.print();
		    }
		    else if(commands.length == 2){//print after given time
			followTimeline.print(Integer.parseInt(commands[1]));
		    }
		    
		    /*
		      if (commands[1].trim().length() > 0) {
		      twitTimeline.print();
		      }
		      else {
		      // this casting might throw some exception...
		      int currTime = valueOf(commands[1]);
		      if (currTime < 0) {
		      throw new IllegalArgumentException();
		      }
		      twitTimeline.print(currTime);
		      }
		    */
		    
		    break;
		    
		    
		case "quit": // Exit the program
		    done = true;
		    System.out.println("exit");
		    break;
		    
		    
		default: // A command with no argument
		    System.out.println("Invalid Command");
		    break;
                }
		
            } //end if
        } //end while
    } //end main
} //end class
