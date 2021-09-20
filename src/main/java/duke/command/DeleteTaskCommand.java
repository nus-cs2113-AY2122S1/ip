package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    private final int taskId;

    public DeleteTaskCommand(int taskId) {
        this.taskId = taskId;
    }

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
