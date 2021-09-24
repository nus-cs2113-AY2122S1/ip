package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

/**
 * Represents a <code>Command</code> object.
 */
public abstract class Command {
    /** Represents a todo command */
    protected static final String COMMAND_TODO = "todo";
    /** Represents a deadline command */
    protected static final String COMMAND_DEADLINE = "deadline";
    /** Represents an event command */
    protected static final String COMMAND_EVENT = "event";

    /** Represents a "by" separator */
    protected static final String SEPARATOR_BY = " /by ";
    /** Represents a "at" separator */
    protected static final String SEPARATOR_AT = " /at ";
    /** Represents a " " separator */
    protected static final String SEPARATOR_SPACE = " ";

    /** Represents a data-loading from file boolean */
    protected static final boolean FOR_LOADING = true;
    /** Represents a non data-loading from file boolean */
    protected static final boolean NOT_FOR_LOADING = false;

    /** Represents the length of "todo" */
    protected static final int LENGTH_TODO = 4;
    /** Represents the length of "deadline" */
    protected static final int LENGTH_DEADLINE = 8;
    /** Represents the length of "event" */
    protected static final int LENGTH_EVENT = 5;
    protected static final int LENGTH_FIND = 4;

    /** Represents a <code>Command</code> is an exit command*/
    protected static final boolean IS_EXIT = true;
    /** Represents a <code>Command</code> is not an exit command*/
    protected static final boolean IS_NOT_EXIT = false;

    private String description;
    private boolean isExitCommand;

    /**
     * Instantiates a <code>Command</code> object.
     *
     * @param description Description of the task.
     * @param isExit To indicate whether it is an exit command.
     */
    public Command(String description, boolean isExit) {
        this.description = description;
        this.isExitCommand = isExit;
    }

    /**
     * Returns the description of the <code>Command</code>.
     *
     * @return Description of the <code>Command</code>.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the <code>Command</code> is an exit command.
     *
     * @return Indication whether the <code>Command</code> is an exit command.
     */
    public boolean isExit() {
        return isExitCommand;
    }

    /**
     * Returns size of <code>tasks</code>.
     *
     * @param tasks Tasks to be considered.
     * @return size of <code>tasks</code>.
     */
    public int getTasksSize(ArrayList<Task> tasks) {
        return tasks.size();
    }

    /**
     * Executes a command.
     *
     * @param tasks Tasks to be executed on.
     * @param ui UI to interact with user.
     * @param storage Storage to save task changes.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
