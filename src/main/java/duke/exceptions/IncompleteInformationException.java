package duke.exceptions;

import duke.commands.CommandType;

/**
 * This class is thrown when the task information fields like time and description are missing. It
 * stores the message specific to the task type and the fields that are lacking
 */
public class IncompleteInformationException extends DukeException{
    private String taskType;

    /**
     * Creates a exception class that stores the task type it is related to
     *
     * @param taskType it is the name of the task that cause this exception
     */
    public IncompleteInformationException(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Depending on the task type, the error message that the exception will show is different.
     * For todo task, the error would be an empty description field while for events and deadlines
     * the error would be empty descriptions or timing fields
     *
     * @return a string of the error message that corresponds to the task
     */
    @Override
    public String getMessage(){
        if (CommandType.isTodo(taskType)) {
            return String.format("☹ OOPS!!! The check that the description of %s is not empty", taskType);
        }
        return String.format("☹ OOPS!!! The check that the description and time fields of %s is not empty", taskType);
    }
}
