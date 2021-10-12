package duke.exception;

/**
 * Represents an exception when the user executes the list command when their tasklist is empty.
 */
public class EmptyTasklistException extends Exception {

    private final String TASKLIST_EMPTY_MSG = "Yikes, there are currently no tasks in the tasklist!\n"
            + "=> Please kindly add a task (todo/deadline/event) first...";

    @Override
    public String toString() {
        return TASKLIST_EMPTY_MSG;
    }

}