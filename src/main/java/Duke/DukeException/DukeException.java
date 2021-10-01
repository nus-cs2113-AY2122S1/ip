package Duke.DukeException;

public class DukeException extends Exception {
    private final String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
