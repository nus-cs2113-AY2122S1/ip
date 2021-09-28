package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class SetDoneCommand extends Command{

    /**
     * Creates a SetDoneCommand object, sets TaskManager object and command components.
     * @param taskManager TaskManager object used to perform operations.
     * @param component String containing indexes of tasks to be set as done.
     */
    public SetDoneCommand(TaskManager taskManager, String component) {
        super(taskManager, component);
    }

    /**
     * Set tasks listed as done.
     */
    public void execute() {
        taskManager.setAsDone(component);
    }
}
