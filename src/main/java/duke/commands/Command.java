package duke.commands;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a flag that indicates whether it exits the program or not.
     *
     * @return <code>false</code> by default.
     */
    public boolean isExit() {
        return false;
    }
}
