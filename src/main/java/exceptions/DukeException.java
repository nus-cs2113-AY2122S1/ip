package exceptions;

/**
 * Represent exceptions specific to duke.
 */
public class DukeException extends Exception {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public DukeException(String message) {
        this.message = message;
    }
}
