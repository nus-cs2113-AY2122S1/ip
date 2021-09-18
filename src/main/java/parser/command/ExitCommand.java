package parser.command;

import storage.Storage;
import task.TaskManager;
import ui.Ui;

import java.util.HashMap;

/**
 * Represents a command for user quitting the program
 */
public class ExitCommand extends Command{

    public ExitCommand(HashMap<String, String> params) {
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
        ui.printBye();
    }

    @Override
    public boolean isFinalCommand() {
        return true;
    }
}
