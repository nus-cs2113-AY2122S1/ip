package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    /**
     * Command to be overridden by child classes
     *
     * @param tasks task list to be used in overriden commands.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns boolean isExit which tells us if the command called was the ExitCommand.
     *
     * @return isExit boolean.
     */
    public boolean getIsExit() {
        return isExit;
    }

}
