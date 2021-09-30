package Duke.commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class FindCommand extends Command {
    private String search;

    public FindCommand (String search) {
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showFilteredTaskList(search, ui);
    }
}
