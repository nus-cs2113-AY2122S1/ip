package duke.logic.commands.exceptions;

public class MissingTaskDescriptionException extends Exception {
    private static final String ERROR_MESSAGE = "Please specify a name for the task!";

    public MissingTaskDescriptionException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
