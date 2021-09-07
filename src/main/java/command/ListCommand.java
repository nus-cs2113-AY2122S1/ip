package command;

import task.TaskManager;
import ui.UI;

public class ListCommand extends Command {

    /**
     * Uses the UI object to print a list of all tasks stored by taro
     *
     * @param taskManager the TaskManager type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.printTasksList(taskManager.getTasks(), taskManager.getTaskCount());
    }
}
