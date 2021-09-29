package duke.logic.exceptions;

import static duke.ui.Ui.LS;

/**
 * Represents an error where the task at the given task number to be marked as done has already been marked as done.
 * Contains a fixed error message.
 */
public class TaskAlreadyDoneException extends Exception{
    private static final String ERROR_MESSAGE =  "Task has already been marked as done! Good job!" + LS +
            "Try marking another task as done! ^=^";

    public TaskAlreadyDoneException() {
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
