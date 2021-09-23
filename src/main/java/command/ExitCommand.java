package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class ExitCommand extends Command {

    /**
     * Exits the programme after printing an exit message
     *
     * @param taskList the TaskList type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     * @param storage the object used for handling read/write operations to the taro.txt data file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printExitMessage();
        System.exit(0);
    }
}
