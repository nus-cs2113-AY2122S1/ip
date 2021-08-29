package shikabot.command;

import shikabot.task.Task;
import shikabot.ui.TextUi;

import java.util.ArrayList;

public abstract class Command {

    protected final TextUi ui;
    protected ArrayList<Task> tasks;

    public Command() {
        this.ui = new TextUi();
    }

    public abstract void execute();

    public void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

}
