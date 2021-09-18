package parser.command;

import storage.Storage;
import task.TaskManager;
import ui.Ui;

import java.util.HashMap;

public class ExitCommand extends Command{

    public ExitCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager tm, Ui ui) {
        ui.printBye();
    }

    @Override
    public boolean isFinalCommand() {
        return true;
    }
}
