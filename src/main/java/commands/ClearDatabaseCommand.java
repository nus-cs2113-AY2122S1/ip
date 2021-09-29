package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ClearDatabaseCommand extends Command{

    /**
     * Removes all tasks in the task list and the database.
     *
     * @param tasks task list to be updated to clear all tasks.
     * @param ui Access to messages.
     * @param storage storage access.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Storage.clearDatabase();
        TaskList.clearAllTasksFromList();
        ui.showClearDatabaseMessage();
    }
}
