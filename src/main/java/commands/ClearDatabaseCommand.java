package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ClearDatabaseCommand extends Command{
    private static final Ui ui = new Ui();

    @Override
    public void execute(TaskList tasks) {
        Storage.clearDatabase();
        TaskList.clearAllTasksFromList();
        ui.showClearDatabaseMessage();
    }
}
