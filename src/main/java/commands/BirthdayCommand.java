package commands;

import tasklist.TaskList;
import ui.Ui;

public class BirthdayCommand extends Command{
    private static final Ui ui = new Ui();

    /**
     * Shows the birthday message.
     *
     * @param tasks task list that is included so birthday can be extended from command.
     */
    @Override
    public void execute(TaskList tasks) {
        ui.showBirthdayMessage();
    }
}
