package duke.exceptions;


/**
 * Parent class of all Duke specific exceptions.
 */
public class DukeException extends Exception {
    protected String message;

    public String getMessage() {
        return message;
    }
}