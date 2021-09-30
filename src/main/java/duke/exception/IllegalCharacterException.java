package duke.exception;

import duke.task.Task;

/**
 * Exception to be thrown when the {@link duke.task.Task#SAVE_FILE_SEPARATOR} is included in a Task arguments.
 */
public class IllegalCharacterException extends Exception {
    private static String MESSAGE = "Illegal character '" + Task.SAVE_FILE_SEPARATOR + "' in task description or time!";

    public IllegalCharacterException() {
        super(MESSAGE);
    }

}
