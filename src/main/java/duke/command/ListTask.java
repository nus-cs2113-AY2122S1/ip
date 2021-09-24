package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class ListTask extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
