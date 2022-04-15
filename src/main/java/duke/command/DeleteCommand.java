package duke.command;

import duke.common.Messages;
import duke.task.TaskManagerException;

/**
 * Delete command that handles deleting a specified task by its task number from list command.
 */
public class DeleteCommand extends Command {

    final public static String COMMAND_WORD = "delete";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_NUMBER;

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
        this.hasDataChange = true;
    }

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public CommandResult execute() throws TaskManagerException {
        String taskDescription = taskManager.deleteTask(this.getTaskIndex());
        String result = Messages.MESSAGE_COMMAND_DELETE + "\n";
        result += taskDescription + "\n";
        result += String.format(Messages.MESSAGE_TOTAL_TASK_NOW, taskManager.getTotalNumberOfTasks());
        return new CommandResult(result);
    }
}
