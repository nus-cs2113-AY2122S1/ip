package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Abstract class for the commands
 */
public abstract class Command {

    /**
     * Executes the command input by the user
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws AustinException Thrown either if the list is empty or if the already marked as
     *                         "done" or "not done"
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException,
            IOException, IndexOutOfBoundsException;
}
