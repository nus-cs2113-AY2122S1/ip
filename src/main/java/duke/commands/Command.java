package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class representing all possible commands from user.
 */
abstract public class Command {
    protected boolean isExit;
    protected String fullCommand;

    /**
     * Method for execution of command.
     * Overwritten by child classes.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    };

    /**
     * Returns exit status of a command.
     * False for all commands except ExitCommand.
     * @return exit status as a boolean
     */
    public boolean isExit() {
        return this.isExit;
    }
}

