package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * Executes the listing of the all tasks within the task list.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTask(ui);
    }
}
