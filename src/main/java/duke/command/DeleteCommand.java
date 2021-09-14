package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Calls the deleteTask method in taskList to delete the task at the specified index
     *
     * @param taskList the taskList that contains the task to be marked
     * @param ui The ui class instance that will print out the completion message
     * @throws DukeException If taskIndex < 0 or if there is no task at the specified index
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.deleteTask(taskIndex, ui);
        storage.saveData(taskList);
    }
}
