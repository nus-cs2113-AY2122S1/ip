package duke.logic.commands.exceptions;

import static duke.ui.Ui.LS;

public class TaskAlreadyDoneException extends Exception{
    private static final String ERROR_MESSAGE =  "Task has already been marked as done! Good job!" + LS +
            "Try marking another task as done! ^=^";

    public TaskAlreadyDoneException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }

}
