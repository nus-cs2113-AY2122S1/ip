package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class ListCommand extends Command {

    /**
     * Uses the UI object to print a list of all tasks stored by taro
     *
     * @param taskList the TaskList type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     * @param storage the object used for handling read/write operations to the taro.txt data file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printTasksList(taskList.getTasks(), taskList.getTaskCount());
    }
}
