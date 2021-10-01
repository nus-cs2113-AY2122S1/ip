package duke.exceptions;

/**
 * An exception that is thrown when the command is invalid or not recognised.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }
}
