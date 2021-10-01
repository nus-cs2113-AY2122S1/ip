package Duke.commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class FindCommand extends Command {
    private String search;

    /**
     * Class constructor for FindCommand
     *
     * @param search Keyword to search for
     */
    public FindCommand (String search) {
        this.search = search;
    }

    /**
     * Find tasks that contain search in their description
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showFilteredTaskList(search, ui);
    }
}
