package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

public class ListCommand extends Command {

    /**
     * A list command execute method which will print all task in tasks using ui
     *
     * @param tasks   TaskList Class which stores all tasks related information such as task array and handle all task
     *                related operations
     * @param ui      UI class which handles all user interactions
     * @param storage Storage class which allows all loading and saving of task information
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        ui.printListOfTaskMessage(taskList);
    }
}
