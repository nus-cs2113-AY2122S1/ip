package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
