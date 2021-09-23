package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from taskList
 */
public class DeleteCommand extends Command {
    protected int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex - 1;
    }

    /**
     * Deletes a tasks by the specified index from taskList and prints a message after deleting
     * @param taskList the list to delete task from
     * @param ui prints the message after deleting
     * @param storage loading and saving taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printDeletedTask();
        ui.printToUser("        ", "deleted: ", taskList.getTask(deleteIndex).toString());
        taskList.deleteTask(deleteIndex);
    }
}
