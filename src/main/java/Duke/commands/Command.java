package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
