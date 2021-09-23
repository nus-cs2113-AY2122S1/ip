package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {
    
    public abstract void execute(TaskList list, Ui ui, Storage storage);

    public abstract boolean isExit();

}
