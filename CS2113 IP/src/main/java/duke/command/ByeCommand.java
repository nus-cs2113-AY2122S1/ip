package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {
    /**
     * Executes exiting of Duke.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleBye();
    }

    /**
     * Returns a Boolean value to check when the program should exit.
     *
     * @return At instantiation of ByeCommand, isExit set to true to exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
