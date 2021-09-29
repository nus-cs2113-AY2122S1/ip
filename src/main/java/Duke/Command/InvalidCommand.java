package Duke.Command;

import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInvalidCommandMessage();
    }
}
