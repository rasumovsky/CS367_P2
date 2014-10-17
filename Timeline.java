import java.util.*;
/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
 */
class Timeline{

    //private SimpleLinkedList<Tweet> tweetList;
    private ListADT<Tweet> tweetList;
    
    /**
     * Constructs an empty timeline
     */
    public Timeline() {
	tweetList = new SimpleLinkedList<Tweet>;
    }
    
    /**
     * Adds a single tweet to the Timeline
     * 
     * @param tweet the tweet to add
     */
    public void add(Tweet tweet){
    }

    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets){
    }

    /**
     * Removes all tweets by user from the Timeline
     * 
     * @param user the user whose tweets should be removed
     */
    public void remove(String user){
    }

    
    /**
     * Create a new Timeline containing only the tweets containing keyword
     * 
     * @param keyword the keyword to search for
     * @return a Timeline of tweets containing keyword
     */
    public Timeline search(String keyword) {
	Timeline newTimeline = new Timeline(); 
	for (int i = 0; i < tweetList.size(); i++) {
	    if (tweetList.get(i).getMessage().toLowerCase().contains(keyword.toLowerCase())) {
		newTimeline.add(tweetList.get(i));
	    }
	}
	return newTimeline;
    }

    
    /**
     * Print each tweet in the timeline
     */
    public void print(){
	for (int i = 0; i < tweetList.size(); i++) {
	    tweetList.get(i).print();
	}   
    }
    
    /**
     * Print each tweet in the timeline since time
     * 
     * @param time the largest time to print tweets
     */
    public void print(int time){
	for (int i = 0; i < tweetList.size(); i++) {
	    if (tweetList.get(i).getTime() > time) {
		tweetList.get(i).print();
	    }
	}   
    }
}

