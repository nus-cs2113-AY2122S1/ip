package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Display all Tasks in ArrayList tasks
 */
public class PurgeCommand extends Command {
    public PurgeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.purgeTaskList();
        Storage.saveData(tasks);
        ui.displayPurgeResponse();
    }
}
