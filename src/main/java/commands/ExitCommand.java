package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ExitCommand extends Command{

    /**
     * Changes isExit attribute to true and shows the exit message.
     *
     * @param tasks task list that is included so exit command can be extended from command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
        isExit = true;
    }
}
