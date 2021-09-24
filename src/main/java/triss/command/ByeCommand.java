package triss.command;

import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) {
        storage.saveTasks();
        isExit = true;
        ui.printShutdownMessage();
    }
}
