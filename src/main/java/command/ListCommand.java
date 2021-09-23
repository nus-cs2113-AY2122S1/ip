package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException {
        tasks.printTaskList();
    }
}
