package duke.tasklist.command;

import duke.tasklist.TaskManager;

/**
 * Base parent of all command classes.
 */
public class Command {
    protected TaskManager taskManager;

    /**
     * Creates a Command object and sets taskManager object.
     * @param taskManager TaskManager object used for task operations.
     */
    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {

    }
}
