package duke.exception;

public class DukeException extends Exception {

    /**
     * A general exception in Duke.
     *
     * @param message text to describe what kind of exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
