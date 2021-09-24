package duke.command;

/**
 * Done command to set a specified task to done by its task number from list command.
 */
public class DoneCommand extends Command {

    final public static String COMMAND_WORD = "done";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_NUMBER;

    public DoneCommand(int targetIndex) {
        super(targetIndex);
        this.hasDataChange = true;
    }

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public void execute() {
        taskManager.setTaskToDone(this.getTaskIndex());
    }
}
