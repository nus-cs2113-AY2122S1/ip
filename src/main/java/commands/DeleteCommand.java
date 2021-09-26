package commands;

import common.Messages;
import task.Task;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "delete: Deletes the task corresponding to the task index.\n"
            + "Parameters: TASK_INDEX\n"
            + "Example: delete 1";
    public static final String MESSAGE_SUCCESS = "Deleted task: %1$s";

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task toDelete = getTargetTask();
            taskManager.deleteTask(toDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_FOUND);
        }
    }
}
