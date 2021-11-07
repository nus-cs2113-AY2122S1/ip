package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

/**
 * Represents the list command given by user
 * to list all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Lists all the current taks
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        UI.printTasksList(tasksList.getTasks());
    }

    /**
     * Returns a boolean to check whether to exit application or not.
     *
     * @return false To continue taking user commands
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
