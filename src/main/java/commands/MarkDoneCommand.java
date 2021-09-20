package commands;

import task.TaskManager;

/**
 * Marks the task status as completed.
 */
public class MarkDoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    /**
     * Creates a new mark command and sets the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public MarkDoneCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Sets the task status as completed.
     *
     * @return The done command type.
     */
    @Override
    public String executeCommand() {
        taskManager.markTaskAsCompleted(commandComponents);
        return COMMAND_WORD;
    }
}
