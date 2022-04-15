package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class DoneTaskCommand extends Command {
    private final String line;

    /**
     * Constructs a DoneTaskCommand with the user input.
     *
     * @param line The user input.
     */
    public DoneTaskCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the DoneTaskCommand by marking the task
     * corresponding to taskId as done.
     *
     * @param taskManager TaskManager object used to mark task as done.
     * @param ui          Ui object used to print messages.
     * @throws DukeException If taskId is invalid.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        int taskId = Parser.getTaskId(line);
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
