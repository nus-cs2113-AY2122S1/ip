package commands;

import task.TaskManager;

public class MarkDoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public MarkDoneCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.markTaskAsCompleted(commandComponents);
        return COMMAND_WORD;
    }
}
