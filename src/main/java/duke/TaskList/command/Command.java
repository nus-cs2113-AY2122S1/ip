package duke.TaskList.command;

import duke.TaskList.TaskManager;

/**
 * Base parent of all command classes.
 */
public class Command {
    protected TaskManager taskManager;

    /**
     * Creates a Command object and sets taskManager object.
     * @param taskManager
     */
    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {}
}
