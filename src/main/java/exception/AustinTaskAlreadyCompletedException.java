package exception;

/**
 * Thrown when user keys in the "done" command if the user already
 * completed the task.
 */
public class AustinTaskAlreadyCompletedException extends AustinException {
    public AustinTaskAlreadyCompletedException() {
        super("Oops. This task is already completed.");
    }
}
