package duke.logic.exceptions;

/**
 * Represents an error where the user input did not specify a description/name for the task.
 * Contains a fixed error message.
 */
public class MissingTaskDescriptionException extends Exception {
    private static final String ERROR_MESSAGE = "Please specify a name for the task!";

    public MissingTaskDescriptionException() {
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
