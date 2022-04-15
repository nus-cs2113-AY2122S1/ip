package duke.command;

import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to list all current tasks.
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the ListTasksCommand by printing all
     * current tasks.
     *
     * @param taskManager TaskManager object passed to the listTasks method.
     * @param ui          Ui object used to list (print) all tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.listTasks(taskManager);
    }
}
