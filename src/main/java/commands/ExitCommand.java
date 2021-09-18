package commands;

import task.TaskManager;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        return COMMAND_WORD;
    }
}
