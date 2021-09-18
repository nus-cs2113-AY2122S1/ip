package commands;

import task.TaskManager;

public class Command {

    public static final String COMMAND_WORD = "general";

    protected TaskManager taskManager;
    protected String[] commandComponents;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void setCommandComponents(String[] commandComponents) {
        this.commandComponents = commandComponents;
    }

    public String executeCommand() {
        return COMMAND_WORD;
    }
}
