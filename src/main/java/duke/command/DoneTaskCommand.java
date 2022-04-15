package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndex;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;

import java.io.IOException;

/**
 * Represents a command that marks a task as done.
 */
public class DoneTaskCommand extends Command {
    private final int currentTaskIndex;

    /**
     * DoneTaskCommand constructor.
     * @param currentTaskIndex the index of the task to be marked as done
     */
    public DoneTaskCommand(int currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    /**
     * Mark a task from the list as "done" and show it to the user.
     * @param tasks   the TaskList object where the task is taken from
     * @param ui      the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws DukeException when there is invalid index input or cannot save data
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.markTaskDone(this.currentTaskIndex);
            ui.printMarked(tasks.getTaskDetails(this.currentTaskIndex));
            storage.writeData(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndex();
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}