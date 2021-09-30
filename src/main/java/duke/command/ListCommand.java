package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    /**
     * Runs a command that prints the entire task list
     *
     * @param tasks   List that stores all the tasks.
     * @param ui      User interface of duke.
     * @param storage Not applicable.
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.printList(ui);
    }

}
