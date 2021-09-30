package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ui.printListOfTaskMessage(taskList);
    }
}
