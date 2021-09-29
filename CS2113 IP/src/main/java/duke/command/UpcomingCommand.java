package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class UpcomingCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleUpcomingComment();
        tasks.listUpcoming(ui);
    }
}
