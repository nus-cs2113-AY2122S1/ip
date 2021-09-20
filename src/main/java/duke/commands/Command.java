package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return CommandType.isBye(command);
    }
}
