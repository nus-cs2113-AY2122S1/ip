package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Bye extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        storage.saveData(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
