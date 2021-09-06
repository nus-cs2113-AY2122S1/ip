package duke;

public class InvalidTaskIdException extends Exception {

    private final String INVALID_TASK_ID_MSG = "\n[Duke]:\n"
            + "=> Yikes, the task id given is out of range of the tasklist!\n"
            + "=> Please enter another task id...";

    @Override
    public String toString() {
        return INVALID_TASK_ID_MSG;
    }

}
