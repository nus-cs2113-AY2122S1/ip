package duke.exceptions;


/**
 * Thrown when a task with the input <code>taskIndex</code> cannot be found.
 */
public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(int taskIndex) {
        message = "\tThere is no task with task index " + (taskIndex+1) + ".";
    }
}
