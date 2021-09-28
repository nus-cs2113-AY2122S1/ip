package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

abstract public class Command {
    protected boolean isExit;
    protected String fullCommand;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    };

    public boolean isExit() {
        return this.isExit;
    }
}

