package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to display the help message.
 */
public class HelpCommand extends Command{

    /**
     * Shows the help message.
     *
     * @param tasks task list that is included so help command can be extended from command.
     * @param ui Access to messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }
}
