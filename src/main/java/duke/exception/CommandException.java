package duke.exception;

/**
 * This exception is raised when an error relating to command execution occurs.
 */
public class CommandException extends Exception{

    public CommandException(String message) {
        super(message);
    }
}
