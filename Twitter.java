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
    private static boolean Compare(List<String> list, String item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(item)) {
                return true;
            }
        }
        return false;
    }
    
    
    private static void debugPrinter( List<List<Tweet>> allTweets, Timeline followedTimeline, List<String> allUsers, List<String> followedUsers) {
	
	System.out.println("ENTERING DEBUGGER");
	
	
	System.out.println("");
	System.out.printf("allUsers    size=%d\n",allUsers.size());
	for (int i = 0; i < allUsers.size(); i++) {
	    System.out.println(allUsers.get(i));
	}
	
	System.out.println("");
	System.out.printf("followedUsers    size=%d\n",followedUsers.size());
	for (int i = 0; i < followedUsers.size(); i++) {
	    System.out.println(followedUsers.get(i));
	}

	System.out.println("");
	System.out.printf("allTweets:    size=%d\n",allTweets.size());
	for (int i = 0; i < allTweets.size(); i++) {
	    System.out.printf("   current user: %s    size=%d\n",allTweets.get(i).get(0).getUser(),allTweets.get(i).size());
	    for (int j = 0; j < allTweets.get(i).size(); j++) {
		allTweets.get(i).get(j).print();
	    }
	}
	
	System.out.println("");
	System.out.println("followedTimeline");
	followedTimeline.print();
    }
    
    
    /**
     * The main method creates an instance of Timeline and
     * then prompts the user for options to follow or unfollow
     * users, search tweets, and print or list information.
     *
     * @param args[] The input files associated with all users.
     */
    public static void main(String[] args) throws FileNotFoundException, TweetTooLongException {
	
	// Check that at least one user file has been provided:
	if (args.length < 1) {
	    System.out.println("Usage: java Twitter FileName1 Filename2...");
	    System.exit(0);
	}
	
	// Create variables for continuously stored data:
	List<List<Tweet>> allTweets = new ArrayList<List<Tweet>>();// List of all tweets sort by user
	Timeline followedTimeline = new Timeline();//contains only tweets the user is following
	
	List<String> allUsers = new ArrayList<String>();  // Lists all users
	List<String> followedUsers = new ArrayList<String>();// Lists followed users
	
	// Variables that will be used in varying contexts:
	String user;           // current user of interest
	List<Tweet> userTweets;// contain tweets from user
	
	
	// Loop over input files (one per user):
	for (int i = 0; i < args.length; i++) {
	    
	    // Check whether input file exists and is readable: 
	    File dataFile = new File(args[i]);
	    if (!dataFile.exists()) {
		System.out.println("Error: cannot access input file");
		System.exit(0);
	    }
	    
	    // Add users to program once file loads:
	    user = args[i].replace(".txt","");
	    allUsers.add(user);
	    followedUsers.add(user);
	    
	    // Load the data from the input file:
	    Scanner inputFile = new Scanner(dataFile);
	    
	    // Loop through the current input file (1 tweet per line):
	    userTweets = new ArrayList<Tweet>();// Store a list of tweets
	    while (inputFile.hasNext()) {
		
		String currLine = inputFile.nextLine();
		
		// Split the line into two strings at the colon:
		String delims = ":";
		String[] splitLine = currLine.split(delims);
		try {
		    userTweets.add(new Tweet(Integer.parseInt(splitLine[0]), splitLine[1], allUsers.get(i)));
		}
		catch (TweetTooLongException ttle) {
		    System.out.println("caught TweetTooLongException in Twitter main");
		}
		
	    }
	    inputFile.close();
	    allTweets.add(userTweets);//list storing lists of tweets by user
	}
	
	// Separate loop to reduce add complexity
	for (int i = 0; i < allTweets.size(); i++) {
            followedTimeline.add(allTweets.get(i));
	}
	

	
	// AT THIS POINT, INPUTS SHOULD BE OK.
	//debugPrinter( allTweets, followedTimeline, allUsers, followedUsers);
	
	

	
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
		    if (commands.length != 2) {
			System.out.println("Invalid command");
			break;
		    }

		    switch (commands[1]) {
		    
		    case "users":// list users
			for (int i = 0; i < allUsers.size(); i++) {
			    System.out.println(allUsers.get(i));
			}
			break;
			
		    case "following": // list followers
			for (int i = 0; i < followedUsers.size(); i++) {
			    System.out.println(followedUsers.get(i));
			}
			break;
		    }
		    break;

		    
		    
		case "follow": // Follow an existing user
		    if (commands.length != 2) {
			System.out.println("Invalid command");
			break;
		    }
		    
		    user = commands[1];
		    
		    // First check that user exists:
		    if (Compare(allUsers, user)) {
			
			// If they are not yet followed, follow
			if (!Compare(followedUsers, user)) {
			    followedUsers.add(user);
			    
			    for (int i = 0; i < allTweets.size(); i++) {
				if (allTweets.get(i).get(0).getUser().equals(user)) {
				    followedTimeline.add(allTweets.get(i));
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
		    // DEBUGGING:
		    debugPrinter( allTweets, followedTimeline, allUsers, followedUsers);
		    break;
		    
		    
		    
		case "unfollow": // Unfollow an existing user:
		    if (commands.length != 2) {
			System.out.println("Invalid command");
			break;
		    }
		    
		    user = commands[1];
		    
		    // First check that user exists:
		    if (Compare(allUsers, user)) {
			
			// If they are already followed, unfollow:
			if (Compare(followedUsers, user)) {
			    followedUsers.remove(user);
			    followedTimeline.remove(user);//remove tweets from timeline
			}
			else {// If user already not followed:
			    System.out.println("Warning: User not followed");
			}
		    }
		    else {// If user does not exist:
			System.out.println("Invalid user");
		    }
		    // DEBUGGING:
		    debugPrinter( allTweets, followedTimeline, allUsers, followedUsers);
		    break;
		    
		    
		    
		case "search": // Search for tweets that contain a string
		    String string = commands[1];
		    followedTimeline.search(string).print();
		    
		    break;
		    
		    
		    
		case "print": // Prints the timeline
		    if (commands.length == 1) {// print entire timeline
			followedTimeline.print();
		    }
		    else if(commands.length == 2){//print after given time
			followedTimeline.print(Integer.parseInt(commands[1]));
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
