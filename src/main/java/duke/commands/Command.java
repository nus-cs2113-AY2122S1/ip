package duke.commands;

import duke.DukeException;
import duke.tasklist.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the appropriate actions.
     *
     * @param tasks Task list.
     * @return Feedback about what was executed.
     * @throws DukeException If there was a problem executing the command.
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return {@code true} if and only if the command is an exit command; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
