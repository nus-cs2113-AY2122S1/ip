package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Prints all the tasks stored in the list
 */
public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    /**
     * Execute ListCommand by printing all the tasks stored in the list.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws AustinException If the list is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException {
        tasks.printTaskList();
    }
}
