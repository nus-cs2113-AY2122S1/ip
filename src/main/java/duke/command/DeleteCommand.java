package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Calls the deleteTask method in taskManager to delete the task at the specified index
     *
     * @param taskManager the taskManager that contains the task to be marked
     * @throws DukeException If taskIndex < 0 or if there is no task at the specified index
     */
    @Override
    public void execute(TaskManager taskManager) throws DukeException {
        taskManager.deleteTask(taskIndex);
    }
}
