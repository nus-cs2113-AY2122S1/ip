package duke.exception;

/**
 * Used to handle exceptions that are specific to Duke.
 */
public class DukeException extends Exception {
    private final String message;

    /**
     * Constructs a DukeException with a message s.
     *
     * @param s The exception message.
     */
    public DukeException(String s) {
        this.message = s;
    }

    /**
     * Gets the exception message.
     *
     * @return Exception message as a String.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
