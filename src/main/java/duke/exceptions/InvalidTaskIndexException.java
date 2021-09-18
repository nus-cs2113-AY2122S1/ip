package duke.exceptions;


public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(int taskIndex) {
        message = "\tThere is no task with task index " + (taskIndex+1) + ".";
    }
}
