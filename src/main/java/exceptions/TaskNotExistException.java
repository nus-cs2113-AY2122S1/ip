package exceptions;

/**
 * Thrown to indicate that the task index that users are referring to does not exist.
 *
 */
public class TaskNotExistException extends DukeException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task does not exist\n";
    }

}
