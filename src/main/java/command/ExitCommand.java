package command;

import task.TaskManager;
import ui.UI;

public class ExitCommand extends Command {

    /**
     * Exits the programme after printing an exit message
     *
     * @param taskManager the TaskManager type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.printExitMessage();
        System.exit(0);
    }
}
