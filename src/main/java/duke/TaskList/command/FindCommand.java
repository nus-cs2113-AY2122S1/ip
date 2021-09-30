package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class FindCommand extends Command{

    protected String component;

    /**
     * Creates a FindCommand object, sets TaskManager object and command component.
     * @param taskManager TaskManager object used to do task operations.
     * @param component String containing keyword for searching.
     */
    public FindCommand(TaskManager taskManager, String component) {
        super(taskManager);
        this.component = component;
    }

    /**
     * Finds and prints all the tasks that contain the keyword used.
     */
    public void execute() {
        taskManager.findTask(component);
    }

}
