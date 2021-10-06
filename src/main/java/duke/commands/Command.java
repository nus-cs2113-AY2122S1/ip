package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;

/**
 * An abstract representation of Command.
 */
public abstract class Command {
    /**
     * Executes command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException If there is an exception of type DukeException occurs.
     * @throws Exception If there is an exception of type other than DukeException occurs.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, Exception;
}
