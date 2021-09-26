package duke.logic.commands.exceptions;

public class TaskListEmptyException extends Exception {
    private static final String ERROR_MESSAGE = "No tasks yet, add a task now!";

    public TaskListEmptyException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
