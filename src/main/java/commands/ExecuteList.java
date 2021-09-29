package commands;

import storage.Storage;
import task.type.TaskList;
import ui.UI;

public class ExecuteList extends Command {

    /**
     * Executes the displaying of current tasks in the list
     *
     * @param tasksList Object of TaskList.
     * @param storage Object of Storage.
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        UI.printListTasks(tasksList.getTasks());
    }

    /**
     * To check whether to exit the application
     *
     * @return false To take input from user again.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
