package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    /**
     * Runs a command that prints the entire task list
     *
     * @param tasks   List that stores all the tasks.
     * @param storage Not applicable.
     */
    @Override
    public void runCommand(TaskList tasks, Storage storage) {
        tasks.printList();
    }
}
