package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private final int taskId;

    /**
     * Constructs a DeleteTaskCommand with task ID.
     *
     * @param taskId The corresponding task ID.
     */
    public DeleteTaskCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the DeleteTaskCommand by deleting the
     * task corresponding to the taskId.
     *
     * @param taskManager Used to delete task.
     * @param ui          Used to print messages.
     * @throws DukeException If task ID is invalid.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        Task deletedTask;
        try {
            deletedTask = taskManager.deleteTask(taskId);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumber();
            throw new DukeException("Please try again!");
        }
        ui.printDeleteTask(deletedTask, taskManager);
    }
}
