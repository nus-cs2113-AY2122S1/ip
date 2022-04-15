package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndex;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private final int selectedTaskIndex;

    /**
     * The DeleteTaskCommand constructor.
     * @param selectedTaskIndex the index of the task to be deleted
     */
    public DeleteTaskCommand(int selectedTaskIndex) {
        this.selectedTaskIndex = selectedTaskIndex;
    }

    /**
     * Delete the selected task from the list.
     * @param tasks   the TaskList object that deletes the selected task
     * @param ui      the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws DukeException when there is invalid index input or cannot save data
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ui.printDeleted(tasks.getTaskDetails(this.selectedTaskIndex));
            tasks.deleteTask(this.selectedTaskIndex);
            storage.writeData(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndex();
        } catch (IOException e) {
            throw new UnsavedFile();
        }

    }
}