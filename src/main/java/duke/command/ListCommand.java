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
        int tasksListSize = taskList.size();
        String message = "";
        if (tasksListSize == 0) {
            message = "Oh! You have no tasks left!";
        } else {
            message = "Total of " + tasksListSize + " task(s)\n";
            int counter = 1;
            for (Task task : taskList) {
                message += String.format("%d.%s\n", counter, task.toString());
                counter++;
            }
        }
        ui.printMessage(message);
    }

}
