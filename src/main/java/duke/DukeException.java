package duke;

/**
 * Custom exception class
 * @author Mohamed Irfan
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}