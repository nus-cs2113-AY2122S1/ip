package duke.commands;

import duke.Storage;
import duke.TasksList;
import duke.Ui;


public class ListCommand extends Command {
    /**
     * Shows a list of all the tasks in the <code>TaskList</code>.
     * @param taskList The <code>TaskList</code> whose tasks are to be displayed.
     * @param ui The <code>Ui</code> to print out the tasks in the <code>TaskList</code> to the user.
     * @param storage The <code>Storage</code> which helps to save the tasks to the data storage.
     */
    public void execute(TasksList taskList, Ui ui, Storage storage) {
        ui.showAllTasks(taskList);
    }
}