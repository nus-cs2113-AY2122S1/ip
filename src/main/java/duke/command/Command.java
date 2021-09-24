package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
