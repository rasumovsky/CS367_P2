// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class  Twitter{


    public static boolean Compare(List<String> list, String item){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(item)){
                return true;
            }
        }
        return false;
    }

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
	
	// Loop over input files (users):
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
        tweets.add(userTweets);//store list of tweets by user
	}
    
	   for(int i = 0; i < tweets.size(); i++){
            followTimeline.add(tweets.get(i));
       }
	
	
        Scanner stdin = new Scanner(System.in);  //for console input
	
        boolean done = false;
        while (!done) {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();
	    
            //only do something if the user enters at least one character
            if (input.length() > 0) {
                String[] commands = input.split("[ ]+");//split on space
               
		switch(commands[0]) {
		
		case "list":
            if(commands.length == 1){
                System.out.println("Invalid command");
                break;
            }
            switch(commands[1]){
                case "users":
                    for(int i = 0; i < currUser.size(); i++){
                        System.out.println(currUser.get(i));
                    }
                    break;//case users

                case "following":
                    for(int i = 0; i < followUser.size(); i++){
                        System.out.println(followUser.get(i));
                    }
                    break;
            }
            
		    break;
		case "follow":
            user = commands[1];
            if(Compare(currUser,user)){
                if(!Compare(followUser,user)){
                    followUser.add(user);
                    for(int i = 0; i < tweets.size(); i++){
                       if(tweets.get(i).get(0).getUser().equals(user)){
                            followTimeline.add(tweets.get(i));
                        }//add tweets to timeline
                    }
                }//check whether user already followed
                else{
                    System.out.println("Warning: User already followed");
                }
            }//check whether user exists
            else{
                System.out.println("Invalid user");
            }
            
		    break;

		case "unfollow":
            user = commands[1];
            if(Compare(currUser,user)){
                if(Compare(followUser,user)){
                    followUser.remove(user);
                    followTimeline.remove(user);//remove tweets from timeline
                }//check whether user already unfollowed
                else{
                    System.out.println("Warning: User not followed");
                }
            }//check whether user exists
            else{
                System.out.println("Invalid user");
            }
		    break;

		case "search":
            String string = commands[1];
            followTimeline.search(string).print();

		    break;
		    
		case "print":
            if(commands.length == 1){
                followTimeline.print();
            }
            else if(commands.length == 2){
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
		    
		case "quit":
		    done = true;
		    System.out.println("exit");
		    break;
		    
		default:  //a command with no argument
		    System.out.println("Invalid Command");
		    break;
                }
            } //end if
        } //end while
    } //end main
} //end class
