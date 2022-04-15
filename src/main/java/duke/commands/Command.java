package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * Parent class of commands to be executed by user
 */
public abstract class Command {
    public abstract void execute(String input, TaskList tasks, Storage storage) throws DukeException;

    /**
     * Checks if "bye" command is called, returns false otherwise
     *
     * @param command Command to verify
     * @return True if ByeCommand is called
     */
    public boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }
}
