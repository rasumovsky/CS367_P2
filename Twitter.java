// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class  Twitter{

    public static void main(String[] args) {
	
	// Check that at least one user file has been provided:
	if (args.length < 1) {
	    System.out.println("Usage: java Twitter FileName1 Filename2...");
	    System.exit(0);
	}
	
	// Create instances of important variables:
	Timeline twitTimeline = new Timeline();
	String currUser;
	
	// Loop over input files (users):
	for (int i = 0; i < args.length; i++) {
	    File dataFile = new File(args[i]);
	    
	    //currUser.args[i].replaceAll(".txt","");
	    currUser = args[i].replace(".txt","");
	    
	    // Check whether input file exists and is readable: 
	    if (!dataFile.exists()) {
		System.out.println("Error: cannot access input file");
		exit(0);
	    }
	    
	    // Load the data from the input file:
	    Scanner inputFile = new Scanner(dataFile);
	    
	    // Loop through the current input file:
	    // Each line is one tweet
	    while (inputFile.hasNext()) {
		
		String currLine = inputFile.nextLine();
		
		// Split the line into two strings at the colon:
		String delims = ":";
		String[] splitLine = currLine.split(delims);
		twitTimeline.add(new Tweet((int)splitLine[0], splitLine[1], currUser));
	    }
	    inputFile.close();
	}
	
	
	
        Scanner stdin = new Scanner(System.in);  //for console input
	
        boolean done = false;
        while (!done) {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();
	    
            //only do something if the user enters at least one character
            if (input.length() > 0) {
                String[] commands = input.split(" ");//split on space
               
		switch(commands[0]) {
		
		case "list":
		    break;
		
		case "follow":
		    break;

		case "unfollow":
		    break;

		case "search":
		    break;
		    
		case "print":
		    if (commands[1].length() > 0) {
			twitTimeline.print();
		    }
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
