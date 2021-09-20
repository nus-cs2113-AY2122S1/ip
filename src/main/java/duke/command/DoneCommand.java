package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

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
