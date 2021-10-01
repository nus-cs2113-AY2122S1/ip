package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.DukeException.DukeException;
import Duke.Tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
