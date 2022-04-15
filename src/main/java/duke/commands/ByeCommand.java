package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Bye command saves data into data/duke.txt file
 * Sets isExit to true to signal for program to terminate
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