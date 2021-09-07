package command;

import task.TaskManager;
import ui.UI;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ui.printExitMessage();
        System.exit(0);
    }
}
