package commands;

import processors.TaskList;
import processors.Ui;

public class ListCommand extends Command {
    public Ui ui = new Ui();

    /**
     * Function prints out the tasks found in the list
     * @param taskList the list of tasks
     */
    public void execute(TaskList taskList) {
        ui.printListMessage(taskList.taskList);
    }
}
