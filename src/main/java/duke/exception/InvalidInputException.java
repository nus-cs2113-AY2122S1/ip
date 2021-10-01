package duke.exception;

/**
 * A custom exception to catch user inputs which are invalid.
 */
public class InvalidInputException extends Exception {
    /**
     * Returns an InvalidInputException.
     *
     * @param errorMessage Message of the error.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
