
// User defined exceptions should usually be checked, which means it is a subclass of Exception but not RunTimeException:

public class TweetTooLongException extends Exception {
 
    public TweetTooLongException() {
	super();
    }
    
    public TweetTooLongException(String message) {
	super(message);
    }
    
}
