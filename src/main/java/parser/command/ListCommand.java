package parser.command;

import storage.Storage;
import task.TaskManager;
import ui.Ui;

import java.util.HashMap;

public class ListCommand extends Command {
    public ListCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager tm, Ui ui) {
        String output = tm.listTasks();
        ui.printMessage(output);
    }
}
