package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a flag that indicates whether it is the exit command
     *
     * @return <code>false</code> by default.
     */
    public boolean isExit() {
        return false;
    }
}