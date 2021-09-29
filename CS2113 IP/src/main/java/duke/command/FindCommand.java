package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String searchKey;

    public FindCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(searchKey, ui);
    }
}
