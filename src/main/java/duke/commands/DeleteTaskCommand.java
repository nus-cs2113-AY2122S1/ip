package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.DukeInvalidTaskIndex;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Deletes a task
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int indexOfTask;

    /**
     * @param indexOfTask 1-based index of the task that is to be deleted
     */
    public DeleteTaskCommand(int indexOfTask) {
        this.indexOfTask = indexOfTask;
    }

    /**
     * Executes command by deleting task and refreshing storage file to reflect the changes
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for reading from/writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     * @throws DukeInvalidTaskIndex if indexOfTaskDone is invalid
     *                              (greater than no. of tasks or less than 0)
     * @throws IOException          if an I/O error occurs
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeInvalidTaskIndex,
            IOException {
        Task removedTask = tasks.removeTask(indexOfTask);
        storage.refreshData(tasks.getTasks());
        ui.acknowledgeRemoveCommand(removedTask, tasks.getNumberOfTasks());
    }
}
