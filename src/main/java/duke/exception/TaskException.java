package duke.exception;

/**
 * This exception is raised when an error relating to parsing of tasks occurs.
 */
public class TaskException extends Exception{

    public TaskException(String message) {
        super(message);
    }
}
