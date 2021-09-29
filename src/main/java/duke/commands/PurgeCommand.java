package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Deletes all Tasks in TaskList
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
