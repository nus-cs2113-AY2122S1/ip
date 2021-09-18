package commands;

import task.TaskManager;

/**
 * Lists down all the tasks in TaskManager.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Creates a new list command and sets the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Lists all tasks found in the TaskManager.
     *
     * @return The list command type.
     */
    @Override
    public String executeCommand() {
        taskManager.listTask();
        return COMMAND_WORD;
    }
}
