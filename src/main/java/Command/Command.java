package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public abstract class Command {
    protected static final String COMMAND_TODO = "todo";
    protected static final String COMMAND_DEADLINE = "deadline";
    protected static final String COMMAND_EVENT = "event";

    protected static final String SEPARATOR_BY = " /by ";
    protected static final String SEPARATOR_AT = " /at ";
    protected static final String SEPARATOR_SPACE = " ";

    protected static final boolean FOR_LOADING = true;
    protected static final boolean NOT_FOR_LOADING = false;

    protected static final int LENGTH_TODO = 4;
    protected static final int LENGTH_DEADLINE = 8;
    protected static final int LENGTH_EVENT = 5;
    protected static final int LENGTH_FIND = 4;

    protected static final boolean IS_EXIT = true;
    protected static final boolean IS_NOT_EXIT = false;

    private String description;
    private boolean isExitCommand;

    public Command(String description, boolean isExit) {
        this.description = description;
        this.isExitCommand = isExit;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExit() {
        return isExitCommand;
    }

    public int getTasksSize(ArrayList<Task> tasks) {
        return tasks.size();
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
