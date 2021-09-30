package exceptions;

/**
 * Thrown to indicate that a user does not provide the required task index or index is not a number
 *
 */
public class TaskIndexException extends DukeException {
    @Override
    public String toString() {
        return "     ☹ OOPS!!! The task index is empty or is not a number\n";
    }

}
