package duke.commands;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

    public void printList(TaskList tasks){

    }
}
