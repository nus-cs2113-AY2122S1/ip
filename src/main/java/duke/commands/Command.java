package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Abstract class representing a command
 */
public abstract class Command {
    /**
     * Executes the command
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for reading from/writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     * @throws DukeException Usually occurs in DeleteTaskCommand and MarkAsDoneCommand where either the
     *                       task index is invalid or the task is already completed
     * @throws IOException   if an I/O error occurs
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException, IOException;
}
