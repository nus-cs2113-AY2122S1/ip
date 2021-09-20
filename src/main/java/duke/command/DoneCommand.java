package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    private final int taskId;

    /**
     * Constructs a DoneCommand with task ID.
     *
     * @param taskId The corresponding task ID.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the DoneCommand by marking the task
     * corresponding to taskId as done.
     *
     * @param taskManager Used to mark task as done.
     * @param ui          Used to print messages.
     * @throws DukeException If task ID is invalid.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        Task completedTask;
        try {
            completedTask = taskManager.markAsDone(taskId);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
            throw new DukeException("Please try again!");
        }
        ui.printMarkAsDone(completedTask);
    }
}
