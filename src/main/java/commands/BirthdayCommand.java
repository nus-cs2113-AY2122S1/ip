package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class BirthdayCommand extends Command{

    /**
     * Shows the birthday message.
     *
     * @param tasks task list that is included so birthday can be extended from command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBirthdayMessage();
    }
}
