package shikabot.command;

import shikabot.task.TaskList;
import shikabot.ui.TextUi;


public abstract class Command {

    protected final TextUi ui;
    protected TaskList taskList;

    public Command() {
        this.ui = new TextUi();
    }

    public abstract void execute();

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

}
