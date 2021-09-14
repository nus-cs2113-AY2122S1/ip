package duke.command;

import duke.DataHandler;
import duke.DukeException;
import duke.TaskManager;

public class DoneCommand extends Command{
    protected int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeCommand(TaskManager taskManager, DataHandler dataHandler) throws DukeException {
        taskManager.markTaskAsDone(taskIndex);
        dataHandler.saveData(taskManager);
    }
}
