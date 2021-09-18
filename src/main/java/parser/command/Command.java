package parser.command;

import task.TaskManager;
import ui.Ui;
import storage.Storage;
import java.util.HashMap;

public abstract class Command {
    protected HashMap<String, String> params;

    public Command(HashMap<String, String> params) {
        this.params = params;
    }

    /***
     * Execute the command based on its type
     * @param storage Storage for updating data file
     * @param taskMgr TaskManager to manage internal task list
     * @param ui Ui to print output messages
     */
    public abstract void execute( Storage storage, TaskManager taskMgr, Ui ui);

    /***
     * Check if this command is the final one.
     * If it is not an ExitCommand, then this will always return false.
     * @return If the command is final
     */
    public boolean isFinalCommand() {
        return false;
    }
}
