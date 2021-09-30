package duke.TaskList.command;

import duke.TaskList.TaskManager;

/**
 * Class responsible to set tasks as done.
 */
public class SetDoneCommand extends Command{

    protected String component;

    /**
     * Creates a SetDoneCommand object, sets TaskManager object and command components.
     * @param taskManager TaskManager object used to perform operations.
     * @param component String containing indexes of tasks to be set as done.
     */
    public SetDoneCommand(TaskManager taskManager, String component) {
        super(taskManager);
        this.component = component;
    }

    /**
     * Set tasks listed as done.
     */
    public void execute() {
        taskManager.setAsDone(component);
    }
}
