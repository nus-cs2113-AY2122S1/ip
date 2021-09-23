package exception;

/** Thrown when the "list" command is called when there are no tasks in the list. */
public class AustinEmptyListException extends AustinException {
    public AustinEmptyListException() {
        super("Sorry. There are no tasks in the list right now.");
    }
}
