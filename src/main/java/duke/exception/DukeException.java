package duke.exception;

/**
 * Prints the current error message provided during an exception
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
