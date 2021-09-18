package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.task.Task;


public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in the <code>TaskList</code> that contains the <code>keyword</code> in their description.
     * @param taskList The <code>TaskList</code> which tasks are to be searched.
     * @param ui The <code>Ui</code> to print out the tasks that has been found to the user.
     * @param storage The <code>Storage</code> which helps to save the resultant tasks to the data storage.
     */
    public void execute(TasksList taskList, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = taskList.findTask(keyword);
        ui.showFoundTasks(foundTasks);
    }
}