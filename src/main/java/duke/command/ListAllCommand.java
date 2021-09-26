package duke.command;

import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListAllCommand extends Command {

    /**
     * Print out all tasks in the list.
     * @param tasks   the TaskList object stores all the tasks
     * @param ui      the Ui object responsible for printing messages
     * @param storage is not used
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.getTaskList());
    }
}