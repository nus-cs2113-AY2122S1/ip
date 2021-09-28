package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class DeleteCommand extends Command{

    /**
     * Creates a delete command object and sets TaskManager object.
     * @param taskManager TaskManager object used to perform operations.
     * @param components String containing indexes of tasks to be deleted.
     */
    public DeleteCommand(TaskManager taskManager, String components) {
        super(taskManager);
        this.components = components;
    }

    /**
     * Deletes tasks listed.
     */
    public void execute() {
        taskManager.deleteTask(components);
    }

}
