package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    /**
     * Abstract method that runs user input commands.
     *
     * @param tasks   List that stores all the tasks.
     * @param ui      User interface of duke.
     * @param storage Reference to the file where data is stored.
     * @throws DukeException If exception is thrown by Command subclasses.
     */
    public abstract void runCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the exit command is being called.
     *
     * @return True if Command subclass is exitCommand.
     *         False if other Command subclasses.
     */
    public boolean isExit() {
        return false;
    };

}
