package duke.exceptions;

/**
 * This exception is thrown when the description of the task is missing when adding a task
 */
public class DukeEmptyDescriptionException extends DukeException {
    public DukeEmptyDescriptionException() {
        errorMessage = "Please enter a description of the task";
    }

}
