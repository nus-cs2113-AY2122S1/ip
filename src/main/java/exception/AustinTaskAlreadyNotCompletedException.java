package exception;

/**
 * Thrown when user keys in the "undo" command if the user already
 * haven't finished the task.
 */
public class AustinTaskAlreadyNotCompletedException extends AustinException {
    public AustinTaskAlreadyNotCompletedException() {
        super("Oops. This task is still in progress.");
    }
}
