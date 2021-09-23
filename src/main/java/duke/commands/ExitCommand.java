package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList.getTaskList());
        } catch (IOException e) {
            ui.printError(e.toString());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
