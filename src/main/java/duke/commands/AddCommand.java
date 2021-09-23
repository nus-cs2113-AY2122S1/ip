package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to taskList
 */
public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to taskList and prints a message after adding
     * @param taskList the list to add task to
     * @param ui prints the message after adding
     * @param storage loading and saving taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = taskList.getListSize();

        taskList.add(task);
        ui.printToUser("    ", "added: ", taskList.getTask(index).toString());
    }
}
