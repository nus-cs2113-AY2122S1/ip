package kate.command;

import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        ui.printByeMessage();
    }
}
