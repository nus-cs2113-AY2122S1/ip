package commands;

import task.TaskManager;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        taskManager.deleteTask(commandComponents);
        return COMMAND_WORD;
    }
}
