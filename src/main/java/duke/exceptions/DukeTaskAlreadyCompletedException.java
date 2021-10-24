package duke.exceptions;

/**
 * Thrown when an already completed task is being set as done again
 */
public class DukeTaskAlreadyCompletedException extends DukeException {
    public DukeTaskAlreadyCompletedException() {
        errorMessage = "This task is already completed";
    }
}
