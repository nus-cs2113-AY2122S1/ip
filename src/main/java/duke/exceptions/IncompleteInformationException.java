package duke.exceptions;

import duke.commands.CommandType;

public class IncompleteInformationException extends DukeException{
    private String taskType;

    public IncompleteInformationException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String getMessage(){
        if (CommandType.isTodo(taskType)) {
            return String.format("☹ OOPS!!! The check that the description of %s is not empty", taskType);
        }
        return String.format("☹ OOPS!!! The check that the description and time fields of %s is not empty", taskType);
    }
}
