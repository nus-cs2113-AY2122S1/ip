package commands;

import task.TaskManager;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.listTask();
        return COMMAND_WORD;
    }
}
