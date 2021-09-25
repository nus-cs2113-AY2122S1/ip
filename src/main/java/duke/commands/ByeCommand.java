package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Deletes Task based on given user input of task number.
 * Removal of Task from ArrayList tasks.
 * Out of bounds error handling is implemented.
 */
public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveData(tasks);
        ui.displayByeMessage();
    }
}