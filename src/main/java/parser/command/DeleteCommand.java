package parser.command;

import storage.Storage;
import task.Task;
import task.TaskManager;
import ui.Ui;

import java.io.IOException;
import java.util.HashMap;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager taskMgr, Ui ui) {
        String indexParam = params.get("main");
        int index;

        try {
            index = Integer.parseInt(indexParam);
        } catch(NumberFormatException e) {
            ui.printErrorMessage("Index is not a number");
            return;
        }

        try {
            Task completedTask = taskMgr.removeTask(index);
            storage.updateTaskFile(taskMgr.toFileString());
            ui.printSuccessMessage("I've removed this task:");
            ui.printMessage(String.format("%s\n", completedTask));
        }  catch(Exception e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
