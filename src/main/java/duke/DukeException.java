package duke;

/**
 * Class that encapsulates an exception in Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor for a Duke Exception
     *
     * @param message The message to be printed when the exception is thrown
     */
    DukeException(String message) {
        super(message);
    }

}
