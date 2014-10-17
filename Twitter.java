// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class  Twitter{

    public static void main(String[] args) {

        // TODO *** steps 1 - 3 of the main method ***
        SimpleLinkedList<String> testList = new SimpleLinkedList<String>();//to test SimpleLinkedList
        Scanner stdin = new Scanner(System.in);  //for console input

        boolean done = false;
        while (!done) {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();

            //only do something if the user enters at least one character
            if (input.length() > 0) {
                String[] commands = input.split(" ");//split on space
                switch(commands[0]){
                    case "list":
                        break;
                    case "follow":
                        break;
                    case "unfollow":
                        break;
                    case "search":
                        break;
                    case "print":
                        break;
                    case "quit":
                        done = true;
                        System.out.println("exit");
                        break;
                    //test        
                    case "add":
                        testList.add(commands[1]);
                        break;
                    case "addpos":
                        testList.add(Integer.parseInt(commands[1]),commands[2]);
                        break;
                    case "contains":
                        if(testList.contains(commands[1])) System.out.println("exist");
                        else System.out.println("not exist");
                        break;
                    case "get":
                        System.out.println(testList.get(Integer.parseInt(commands[1])));
                        break;
                    case "empty":
                        if(testList.isEmpty()) System.out.println("empty");
                        else System.out.println("not empty");
                        break;
                    case "size":
                        System.out.println(testList.size());
                        break;
                    case "remove":
                        System.out.println(testList.remove(Integer.parseInt(commands[1])));
                        break;
                    case "printall":
                        for(int i=0; i<testList.size(); i++){
                            System.out.println(testList.get(i));
                        }
                        break;
                    //end of test
                    default:  //a command with no argument
                        System.out.println("Invalid Command");
                        break;
                }
            } //end if
        } //end while
    } //end main
}
