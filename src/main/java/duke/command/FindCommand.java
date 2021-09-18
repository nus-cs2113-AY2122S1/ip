package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    protected String keyword;

    /**
     * Class find command constructor.
     *
     * @param keyword Task to be added to the list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs a command that prints matching tasks in the list
     *
     * @param tasks   List that stores all the tasks.
     * @param ui      User interface of duke.
     * @param storage Reference to the file where data is stored.
     * @throws DukeException If exception is thrown by Command subclasses.
     */
    public void runCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.printMatchingTask(keyword, ui);
    }
}
