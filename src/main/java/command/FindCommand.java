package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the find command given by user
 * to display list of tasks that match a keyword.
 */
public class FindCommand extends Command {
    private String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Displays a list of tasks that match the keyword
     * given by the user.
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        ArrayList<Task> filteredTasks = tasksList.findTasks(filter);
        UI.printFindTasksList(filteredTasks);
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