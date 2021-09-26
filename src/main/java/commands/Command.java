package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

/**
 * The Command class is an abstract class representing teh underlying command object
 * It is inherited by all other command objects
 */
public abstract class Command {
    /**
     * Executes the command, performs required operations and updates data.txt.
     * This method is only meant to be called by child classes
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns whether stop command has been issued by user
     * @return Boolean true if stop command has been issued, false if other commands issued
     */
    public Boolean isStop() {
        return this instanceof ByeCommand;
    }
}
