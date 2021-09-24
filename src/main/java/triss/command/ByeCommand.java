package triss.command;

import triss.Parser;
import triss.Storage;
import triss.TaskList;
import triss.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) {
        storage.saveTasks();
        isExit = true;
        ui.printShutdownMessage();
    }
}
