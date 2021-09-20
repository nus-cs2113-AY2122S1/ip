package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

public abstract class Command {
    protected boolean isBye;

    abstract public void execute(TaskManager taskManager, Ui ui) throws DukeException;

    public boolean getIsBye() {
        return isBye;
    }
}
