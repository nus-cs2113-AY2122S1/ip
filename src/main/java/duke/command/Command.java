package duke.command;

import duke.DukeException;
import duke.Util.Storage;
import duke.Util.Ui;
import duke.task.TaskList;

public abstract class Command {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    protected static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number.";

    protected String argument;
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public boolean isExit() {
        return isExit;
    }
}
