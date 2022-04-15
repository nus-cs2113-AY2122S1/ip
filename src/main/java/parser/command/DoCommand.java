package parser.command;

import storage.Storage;
import task.Task;
import task.TaskManager;
import ui.Ui;

import java.util.HashMap;

/**
 * Represents a command for completing a task
 */
public class DoCommand extends Command{
    public DoCommand(HashMap<String, String> params) {
        super(params);
    }


    /**
     * Execute the command based on its type
     * @param storage Storage for updating data file
     * @param taskMgr TaskManager to manage internal task list
     * @param ui Ui to print output messages
     */
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
            Task completedTask = taskMgr.doTask(index);
            ui.printSuccessMessage("I've marked this task as done:");
            ui.printMessage(String.format("%s\n", completedTask));
        } catch (Exception e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
