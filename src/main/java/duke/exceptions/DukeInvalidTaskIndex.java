package duke.exceptions;

/**
 * Thrown when the task index of a task being deleted/set-as-done is greater than the number of tasks or
 * less than 0
 */
public class DukeInvalidTaskIndex extends DukeException {
    public DukeInvalidTaskIndex() {
        errorMessage = "Please enter valid task index number";
    }
}
