package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents the command to show the birthday message.
 */
public class BirthdayCommand extends Command{

    /**
     * Shows the birthday message.
     *
     * @param tasks task list that is included so birthday can be extended from command.
     * @param ui Access to messages.
     * @param storage storage access that is included so birthday can extend command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBirthdayMessage();
    }
}
