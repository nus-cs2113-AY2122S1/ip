package duke.commands;

import duke.task.Task;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public abstract class Command {
    public abstract void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }
}
