package alfred.command;

import alfred.task.TaskList;

public abstract class Command {
    protected TaskList taskList;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract void execute();

    public boolean isExit() {
        return this instanceof ExitAppCommand;
    }
}
