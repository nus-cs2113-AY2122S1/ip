package commands;

import task.TaskManager;
import ui.Display;

/**
 * Displays helper text to user whenever an invalid command is entered.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    /**
     * Creates a new help command.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
    public HelpCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Displays helper text to user.
     *
     * @return The help command type.
     */
    @Override
    public String executeCommand() {
        Display.displayHelperText();
        return COMMAND_WORD;
    }
}
