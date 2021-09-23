package commands;

import task.TaskManager;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Creates a new exit command.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public ExitCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Returns the exit command type.
     *
     * @return The exit command type.
     */
    @Override
    public String executeCommand() {
        return COMMAND_WORD;
    }
}
