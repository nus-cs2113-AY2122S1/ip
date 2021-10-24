package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;

/**
 * Prints all tasks
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for reading from/writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTasks(tasks.getTasks());
    }
}
