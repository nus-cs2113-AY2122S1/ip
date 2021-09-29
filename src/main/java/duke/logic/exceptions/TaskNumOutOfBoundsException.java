package duke.logic.exceptions;

/**
 * Represents an error where the user tries to access a task number that does not exist.
 * Contains a fixed error message.
 */
public class TaskNumOutOfBoundsException extends Exception {
    private static final String ERROR_MESSAGE =  "Please input a valid task number from 1 to %d!";

    public TaskNumOutOfBoundsException() {
        super(ERROR_MESSAGE);
    }

    /**
     * Returns the error message in String form.
     */
    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
