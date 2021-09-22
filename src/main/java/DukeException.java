/**
 * Custom exception to deal with incorrect input format from user
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
