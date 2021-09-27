package duke.exceptions;

/** Represents the exception thrown associated with the operations of Duke. */
public class DukeException extends Exception {

    /**
     * Constructs an exception associated with Duke with an exception message.
     *
     * @param message Message intended for the user which can be the exception description or the
     *                reason that it is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
