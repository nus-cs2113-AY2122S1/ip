package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Marks a task as done in the TaskList and storage file
 */
public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final int indexOfTaskDone;

    /**
     * @param indexOfTaskDone 1-based index of the task that has been completed
     */
    public MarkAsDoneCommand(int indexOfTaskDone) {
        this.indexOfTaskDone = indexOfTaskDone;
    }

    /**
     * Executes command by setting the task as done in TaskList and refreshes the storage file to reflect the
     * changes
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for reading from/writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     * @throws DukeInvalidTaskIndex              if indexOfTaskDone is invalid
     *                                           (greater than no. of tasks or less than 0)
     * @throws DukeTaskAlreadyCompletedException if task at indexOfTaskDone has already been completed
     * @throws IOException                       if an I/O error occurs
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeInvalidTaskIndex,
            DukeTaskAlreadyCompletedException,
            IOException {
        tasks.setTaskAsDone(indexOfTaskDone);
        storage.refreshData(tasks.getTasks());
        ui.acknowledgeDoneCommand(tasks.getTasks().get(indexOfTaskDone - 1));
    }
}
