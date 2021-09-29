package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTask(ui);
    }
}
