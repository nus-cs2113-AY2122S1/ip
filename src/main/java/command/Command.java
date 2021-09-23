package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public abstract class Command {
    /** Executes the command input by the user */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException, IOException;
}
