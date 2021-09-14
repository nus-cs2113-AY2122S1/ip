package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class DeleteTaskCommand extends Command{
    protected int taskIndex;

    public DeleteTaskCommand(int taskIndex) { this.taskIndex = taskIndex; }

    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException {
        taskManager.deleteTask(taskIndex);
    }
}
