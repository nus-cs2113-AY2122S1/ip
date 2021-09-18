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

    public abstract void execute( Storage storage, TaskManager taskMgr, Ui ui);

    public boolean isFinalCommand() {
        return false;
    }
}
