package duke.exception;
/**
 * Exception to be thrown when the command entered by a user is invalid.
 */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(String message){
        super(message);
    }

}