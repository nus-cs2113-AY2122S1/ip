package commands;

import tasks.TaskList;

public abstract class Command {
    protected TaskList taskList;

    public abstract void execute();

    /**
     * Supplies the data the command will operate on.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
