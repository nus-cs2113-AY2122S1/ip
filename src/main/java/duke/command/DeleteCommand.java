package duke.command;

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
    public void execute() {
        taskManager.deleteTask(this.getTaskIndex());
    }
}
