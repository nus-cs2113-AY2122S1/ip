package kate.command;

import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public abstract class Command {
    protected String userInput;
    protected boolean isExit;

    public abstract void execute(KateUI ui, Storage storage, TaskList tasks);

    public boolean getExitStatus() {
        return isExit;
    }
}
