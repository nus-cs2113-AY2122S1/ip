package duke.TaskList.command;

import duke.TaskList.TaskManager;

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
