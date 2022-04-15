package triss.command;

import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;
import triss.exception.TrissException;

public abstract class Command {

    boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {

    }
}
