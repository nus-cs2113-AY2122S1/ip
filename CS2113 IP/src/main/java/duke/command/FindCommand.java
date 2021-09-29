package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String searchKey;

    /**
     * Class constructor for FindCommand.
     *
     * @param searchKey Key to search through for matching tasks.
     */
    public FindCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * Executes the finding of the key for matches within the task description for each task in the taskList.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(searchKey, ui);
    }
}
