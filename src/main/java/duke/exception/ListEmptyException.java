package duke.exception;

/**
 * Exception to be thrown when attempting to print tasks from an empty list.
 */
public class ListEmptyException extends Exception {
    private static String MESSAGE = "List is empty!";

    public ListEmptyException() {
        super(MESSAGE);
    }

}
