package duke.exception;
/**
 * Exception to be thrown when the command entered by a user is invalid
 * Accepts a <code>message</code> String to be passed to the handler.
 */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String message){
        super(message);
    }
}