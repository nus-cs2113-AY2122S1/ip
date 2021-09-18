package parser.command;

import storage.Storage;
import task.TaskManager;
import ui.Ui;

import java.util.HashMap;

/**
 * Represents a command for invalid user input
 */
public class InvalidCommand extends Command{

    public InvalidCommand(HashMap<String, String> params) {
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
        ui.printErrorMessage("Invalid command");
    }
}
