package duke.command;

import duke.task.TaskManager;
import duke.ui.Ui;

public class QuitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        isBye = true;
        ui.printFarewell();
    }
}
