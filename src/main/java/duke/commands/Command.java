package duke.commands;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.exceptions.DukeException;


abstract public class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TasksList taskList, Ui ui, Storage storage) throws DukeException;
}
