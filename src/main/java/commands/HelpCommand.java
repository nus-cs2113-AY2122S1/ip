package commands;

import task.TaskManager;
import ui.Display;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public HelpCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        Display.displayHelperText();
        return COMMAND_WORD;
    }
}
