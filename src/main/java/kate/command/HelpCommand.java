package kate.command;

import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public class HelpCommand extends Command {

    public HelpCommand() {
        this.isExit = false;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        ui.printHelpPage();
    }
}
