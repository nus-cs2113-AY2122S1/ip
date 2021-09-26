package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command{
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Finds tasks in tasklist.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.findTasks(searchTerm);
    }
}
