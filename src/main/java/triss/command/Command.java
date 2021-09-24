package triss.command;

import triss.Parser;
import triss.Storage;
import triss.TaskList;
import triss.Ui;
import triss.exception.TrissException;

public abstract class Command {

    boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {

    }
}
