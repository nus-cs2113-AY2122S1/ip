package commands;

import task.TaskManager;

/**
 * The Command class is responsible for all different types of user commands.
 */
public class Command {

    public static final String COMMAND_WORD = "general";

    protected TaskManager taskManager;
    protected String[] commandComponents;

    /**
     * Creates a new Command object and set the TaskManager object to perform operations on.
     *
     * @param taskManager The TaskManager object to execute commands on.
     */
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
