package command;

import task.TaskManager;
import ui.UI;

public abstract class Command {

    public abstract void execute(TaskManager taskManager, UI ui);

}
