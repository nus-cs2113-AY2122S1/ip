package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class ListCommand extends Command {

    /**
     * Runs a command to print the entire task list.
     *
     * @throws DukeException If there are no tasks in the list.
     */
    @Override
    public void runCommand() throws DukeException {
        TaskManager.printList();
    }
}
