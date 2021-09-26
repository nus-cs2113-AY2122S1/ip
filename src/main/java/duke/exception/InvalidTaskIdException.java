package duke.exception;

/**
 * Represents an exception when the user executes the done or delete command
 * with a task ID that does not exist in the user's tasklist (negative or bigger than the tasklist size).
 */
public class InvalidTaskIdException extends Exception {

    private final String INVALID_TASK_ID_MSG = "Yikes, the task id given is out of range of the tasklist!\n"
            + "=> Please enter another task id...";

    @Override
    public String toString() {
        return INVALID_TASK_ID_MSG;
    }

}
