package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private final String line;

    /**
     * Constructs a DeleteTaskCommand with the user input.
     *
     * @param line The user input.
     */
    public DeleteTaskCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the DeleteTaskCommand by deleting the
     * task corresponding to the taskId.
     *
     * @param taskManager TaskManager object used to delete task.
     * @param ui          Ui object used to print messages.
     * @throws DukeException If taskId is invalid.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        int taskId = Parser.getTaskId(line);
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
