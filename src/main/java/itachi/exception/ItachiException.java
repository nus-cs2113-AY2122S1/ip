package itachi.exception;

/**
 * Prints the current error message provided during an exception
 */
public class ItachiException extends Exception {
    public ItachiException(String errorMessage) {
        super(errorMessage);
    }
}
