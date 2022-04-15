package duke;

/** Signals that some error occurred specific to the functionality of Duke. */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
