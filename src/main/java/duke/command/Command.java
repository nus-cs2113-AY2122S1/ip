package duke.command;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;

public abstract class Command {
    public abstract void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) throws DukeException;

    public boolean isExit(){
        return false;
    }
}
