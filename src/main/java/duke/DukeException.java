package duke;

/**
 * Exception class created for the Duke program. An error message will be recorded whenever the error message is thrown
 * in the program
 */
public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}
