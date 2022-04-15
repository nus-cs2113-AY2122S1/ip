package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class UpcomingCommand extends Command {
    /**
     * Execute the list of all tasks that have yet to be completed and
     * have deadlines that are less than or equal to three days from current date.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleUpcomingComment();
        tasks.listUpcoming(ui);
    }
}
