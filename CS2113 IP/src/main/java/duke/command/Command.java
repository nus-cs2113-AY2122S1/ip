package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    /**
     * Abstract method that executes different commands based on user's input.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a Boolean value to check when the program should exit.
     *
     * @return At instantiation of all commands other than ByeCommand, isExit should be set to false.
     */
    public boolean isExit() {
        return false;
    }
}
