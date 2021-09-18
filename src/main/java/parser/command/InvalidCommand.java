package parser.command;

import storage.Storage;
import task.TaskManager;
import ui.Ui;

import java.util.HashMap;

public class InvalidCommand extends Command{

    public InvalidCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager taskMgr, Ui ui) {
        ui.printErrorMessage("Invalid command");
    }
}
