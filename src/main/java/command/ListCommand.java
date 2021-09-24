package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/** Prints all the tasks stored in the list */
public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException {
        tasks.printTaskList();
    }
}
