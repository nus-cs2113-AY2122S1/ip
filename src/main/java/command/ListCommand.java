package command;

import task.TaskManager;
import ui.UI;

public class ListCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.printTasksList(taskManager.getTasks(), taskManager.getTaskCount());
    }
}
