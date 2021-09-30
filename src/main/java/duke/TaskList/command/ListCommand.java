package duke.TaskList.command;

import duke.TaskList.TaskManager;

/**
 * Class responsible to display the list of all tasks.
 */
public class ListCommand extends Command{

    /**
     * Creates a ListCommand object and sets the TaskManager object.
     * @param taskManager TaskManager object used to perform task operations.
     */
    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Prints the latest version of the task list.
     */
    public void execute() {
        taskManager.getAndPrintTaskList();
    }
}
