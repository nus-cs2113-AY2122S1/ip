package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit() {
        return false;
    }

}
