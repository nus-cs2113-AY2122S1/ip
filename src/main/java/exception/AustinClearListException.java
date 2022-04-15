package exception;

/** Thrown when the "clear" command is called when there are no tasks in the list. */
public class AustinClearListException extends AustinException {
    public AustinClearListException() {
        super("There are no tasks in the list to clear.");
    }
}
