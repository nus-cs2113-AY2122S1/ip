package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public abstract class Command {

    public abstract void execute(TaskList taskList, UI ui, Storage storage);

}
