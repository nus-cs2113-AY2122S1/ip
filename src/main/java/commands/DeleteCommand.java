package commands;

import task.Task;

import static common.Messages.MESSAGE_TASK_NOT_FOUND;

/**
 * Deletes a task in the task list using the task index.
 */
public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "delete: Deletes the task corresponding to the task index.\n"
            + "\tParameters: TASK_INDEX\n"
            + "\tExample: delete 1\n";
    public static final String MESSAGE_SUCCESS = "Deleted task: %1$s";

    /**
     * Constructor that sets the target index of the task we want to delete.
     * @param taskIndex the index of the task we want to delete.
     */
    public DeleteCommand(int taskIndex) {
        super(taskIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task toDelete = getTargetTask();
            taskManager.deleteTask(toDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_FOUND, taskManager.getTaskList().size()));
        }
    }
}
