package duke.exception;

/**
 * Represents an exception where the user tries to mark an already completed task as done.
 */
public class TaskAlreadyDoneException extends Exception {

    private final String TASK_ALREADY_DONE_MSG = "Yikes, this task is already marked done!\n"
            + "=> Please enter another task to complete...";

    @Override
    public String toString() {
        return TASK_ALREADY_DONE_MSG;
    }

}
