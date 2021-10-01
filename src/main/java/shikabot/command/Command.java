package shikabot.command;

import shikabot.task.TaskList;

public abstract class Command {

    protected TaskList taskList;

    public Command() {
    }

    public abstract void execute();

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

}
