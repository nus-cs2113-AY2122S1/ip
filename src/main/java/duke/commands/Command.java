package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, TextUi ui, Storage storage);

    public boolean getExit() {
        return isExit;
    }

    ;
}
