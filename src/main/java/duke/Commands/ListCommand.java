package duke.Commands;

import duke.Storage;
import duke.TasksList;
import duke.Ui;


public class ListCommand extends Command{
    public void execute(TasksList taskList, Ui ui, Storage storage) {
        ui.showAllTasks(taskList);
    }
}