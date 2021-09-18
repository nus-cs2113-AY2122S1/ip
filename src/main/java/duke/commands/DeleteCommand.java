package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TasksList;
import duke.Ui;
import duke.task.Task;


public class DeleteCommand extends Command {
    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task at the <code>taskIndex</code> of this instance.
     * @param taskList The <code>TaskList</code> which task will be deleted from.
     * @param ui The <code>Ui</code> to print out the task that has been deleted to the user.
     * @param storage The <code>Storage</code> which helps to save the resultant tasks to the data storage.
     * @throws DukeException If the <code>taskIndex</code> given cannot be found in <code>taskList</code>.
     */
    public void execute(TasksList taskList, Ui ui, Storage storage) throws DukeException {
        Task task;
        try {
            task = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        ui.showTaskDeleted(task, taskList.getSize());
        storage.save(taskList);
    }
}