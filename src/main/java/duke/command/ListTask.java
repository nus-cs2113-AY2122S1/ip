package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a command type of List Task.
 */
public class ListTask extends Command{

    /**
     * Lists all tasks in the ArrayList tasks.
     *
     * @param tasks TaskList including all tasks.
     * @param ui User Interface.
     * @param storage Storage to load and save the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    /**
     * Returns false to indicate the program continues running.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
