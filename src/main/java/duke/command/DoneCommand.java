package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class DoneCommand extends Command{
    protected int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException {
        taskManager.markTaskAsDone(taskIndex);
    }
}
