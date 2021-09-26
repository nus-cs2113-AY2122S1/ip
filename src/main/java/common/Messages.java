package common;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.MarkAsDoneCommand;
import commands.DeleteCommand;


public class Messages {

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Error. Task not found.";
    public static final String MESSAGE_LOADING_ERROR = "Error in loading file.";

    public static final String USER_GUIDE = "\n" + AddTodoCommand.MESSAGE_USAGE
            + "\n" + AddEventCommand.MESSAGE_USAGE
            + "\n" + AddDeadlineCommand.MESSAGE_USAGE
            + "\n" + MarkAsDoneCommand.MESSAGE_USAGE
            + "\n" + DeleteCommand.MESSAGE_USAGE;

}
