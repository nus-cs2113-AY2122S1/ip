package duke.Exceptions;


/**
 * Thrown when a task that has already been marked as done is getting marked as done again.
 */
public class TaskHasBeenDoneException extends DukeException {
    public TaskHasBeenDoneException (int taskIndex) {
        message = "\tTask " + (taskIndex+1) + " has already been marked as done.";
    }
}
