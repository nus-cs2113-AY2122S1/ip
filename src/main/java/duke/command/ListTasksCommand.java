package duke.command;

import duke.task.TaskManager;
import duke.ui.Ui;

public class ListTasksCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.listTasks(taskManager);
    }
}
