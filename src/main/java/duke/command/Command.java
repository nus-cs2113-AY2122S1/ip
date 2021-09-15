package duke.command;

import duke.util.TaskList;

/**
 * Represents a command that the user issues to Duke.
 */
public abstract class Command {
    /**
     * Executes the command.
     */
    public abstract void execute(TaskList tl);
}
