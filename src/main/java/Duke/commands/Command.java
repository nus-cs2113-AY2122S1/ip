package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {
    /**
     * Abstract class for other commands to execute their operation
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    public abstract void execute (TaskList tasks, Ui ui, Storage storage);

    /**
     * Default isExit boolean is set to false, so that while loop will continue
     *
     * @return Default isExit boolean
     */
    public boolean isExit() {
        return false;
    }
}
