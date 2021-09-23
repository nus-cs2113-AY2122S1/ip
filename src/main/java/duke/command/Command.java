package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;

public abstract class Command {

    /**
     * Abstract method that sub commands implement to execute user input commands.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    public abstract void executeCommand(TaskList tasks, LizUi ui);

    /**
     * Checks if exit command is called.
     * @return True if exit command is called. False otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
