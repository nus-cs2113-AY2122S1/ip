package exception;

/**
 * Thrown when the user does not provide date and time details while
 * creating a new event or deadline task.
 */
public class AustinEmptyTimeDetailsException extends AustinException {
    public AustinEmptyTimeDetailsException() {
        super("The date and time details are missing.");
    }
}
