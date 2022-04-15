package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import duke.task.TaskList;

public abstract class Command {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    protected static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number.";
    protected static final String MESSAGE_FORMAT_GENERIC_USAGE = "Usage: %s";

    protected String argument;
    protected boolean isExit;

    /**
     * Constructor for Command class.
     *
     * @param argument The command argument.
     */
    public Command(String argument) {
        this.argument = argument;
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
