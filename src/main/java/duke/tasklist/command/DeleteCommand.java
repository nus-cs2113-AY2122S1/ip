package duke.tasklist.command;

import duke.tasklist.TaskManager;

/**
 * Class responsible for deleting tasks.
 */
public class DeleteCommand extends Command {

    protected String component;

    /**
     * Creates a delete command object and sets TaskManager object and command components.
     * @param taskManager TaskManager object used to perform operations.
     * @param component String containing indexes of tasks to be deleted.
     */
    public DeleteCommand(TaskManager taskManager, String component) {
        super(taskManager);
        this.component = component;
    }

    /**
     * Deletes tasks listed.
     */
    public void execute() {
        taskManager.deleteTask(component);
    }

}
