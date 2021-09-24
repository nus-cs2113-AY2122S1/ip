package commands;

import tasklist.TaskList;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks);

    public boolean getIsExit() {
        return isExit;
    }

}
