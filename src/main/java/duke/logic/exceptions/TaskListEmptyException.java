package duke.logic.exceptions;

import duke.ui.Ui;

/**
 * Represents an error where the user tries to access something in the task list but the list is currently empty.
 * Contains a fixed error message.
 */
public class TaskListEmptyException extends Exception {
    private static final String ERROR_MESSAGE = Ui.MESSAGE_NO_TASKS_YET;

    public TaskListEmptyException() {
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
