package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Abstract class Command represents the generic command.
 * Inherited by all commands.
 */
public abstract class Command {
    String command;
    boolean isExit;

    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
