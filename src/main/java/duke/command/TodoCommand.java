package duke.command;

import duke.common.CommonFormat;
import duke.common.Messages;

/**
 * Todo command that will create a Todo task.
 */
public class TodoCommand extends Command {

    final public static String COMMAND_WORD = "todo";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_DESCRIPTION;

    private String arguments;

    public TodoCommand(String arguments) {
        super();
        this.arguments = arguments;
        this.hasDataChange = true;
    }

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public CommandResult execute() {
        taskManager.createToDoTask(this.arguments);
        return new CommandResult(super.getAddTaskResponseMessage());
    }
}
