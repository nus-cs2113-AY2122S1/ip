package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class ClearTasksCommand extends Command {
    public static final String COMMAND_KEYWORD = "clear";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException, IOException {
        tasks.clearTasks();
        storage.clearFileContents();
        ui.acknowledgeClear();
    }
}
