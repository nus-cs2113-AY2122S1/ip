package duke.command;

import duke.common.Messages;

/**
 * List command to print all task in the task list.
 */
public class ListCommand extends Command {

    final public static String COMMAND_WORD = "list";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_NO_FORMAT;

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public CommandResult execute() {
        String result = Messages.MESSAGE_COMMAND_LIST + "\n";
        result += taskManager.getAllTaskInfo();
        result += String.format(Messages.MESSAGE_TOTAL_TASK_NOW, taskManager.getTotalNumberOfTasks());
        return new CommandResult(result);
    }

}
