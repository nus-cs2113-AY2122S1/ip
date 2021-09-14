package duke.command;

import duke.DataHandler;
import duke.DukeException;
import duke.TaskManager;

import javax.xml.crypto.Data;

public class DeleteTaskCommand extends Command{
    protected int taskIndex;

    public DeleteTaskCommand(int taskIndex) { this.taskIndex = taskIndex; }

    @Override
    public void executeCommand(TaskManager taskManager, DataHandler dataHandler) throws DukeException {
        taskManager.deleteTask(taskIndex);
        dataHandler.saveData(taskManager);
    }
}
