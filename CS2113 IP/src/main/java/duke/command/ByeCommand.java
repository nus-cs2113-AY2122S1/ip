package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
