package duke.command;

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
    public void execute() {
        taskManager.printAllTasks();
    }

}
