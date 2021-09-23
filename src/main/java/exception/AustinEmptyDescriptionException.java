package exception;

/** Thrown when the user does not provide the description of the task while adding into the list. */
public class AustinEmptyDescriptionException extends AustinException {
    public AustinEmptyDescriptionException() {
        super("The description of the task is empty.");
    }
}
