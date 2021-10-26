package commands;

import processors.TaskList;
import processors.UI;

public class ListCommand extends Command {
    public UI ui = new UI();

    /**
     * Function prints out the tasks found in the list
     * @param taskList the list of tasks
     */
    public void execute(TaskList taskList) {
        ui.printListMessage(taskList.taskList);
    }
}
