package commands;

import common.Messages;
import task.Task;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD+": Deletes the task corresponding to the task number. \n"
            + "Example: delete {TASK_NUMBER}";
    public static final String MESSAGE_DELETE_SUCCESS = "I've deleted the task: %1$s";

    public DeleteCommand(int targetDisplayIndex) {
        super(targetDisplayIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task toDelete = getTargetTask();
            taskManager.deleteTask(toDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, toDelete.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_FOUND);
        }
    }
}
