package commands;

import storage.Storage;
import task.type.TaskList;
import ui.UI;

public class ExecuteList extends Command {
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        UI.printListTasks(tasksList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
