package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TasksList;
import duke.Ui;


public class DoneCommand extends Command {
    public int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Finds and mark a task as done.
     * @param taskList The <code>TaskList</code> whose task is to be marked as done.
     * @param ui The <code>Ui</code> to print out the task that has been marked as done to the user.
     * @param storage The <code>Storage</code> which helps to save the resultant tasks to the data storage.
     * @throws DukeException If the <code>taskIndex</code> given cannot be found in <code>taskList</code>.
     */
    public void execute(TasksList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.markTaskAsDone(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        ui.showTaskDone(taskList.getTask(taskIndex));
        storage.save(taskList);
    }
}