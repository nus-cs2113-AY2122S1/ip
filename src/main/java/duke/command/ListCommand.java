package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    /**
     * Lists all tasks in tasklist.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}