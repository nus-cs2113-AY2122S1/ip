package duke.command;

import duke.task.TaskManager;
import duke.ui.Ui;

public class QuitCommand extends Command {
    public QuitCommand() {
        isBye = true;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.printFarewell();
    }
}
