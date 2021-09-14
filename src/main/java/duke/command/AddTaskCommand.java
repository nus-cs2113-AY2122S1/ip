package duke.command;

import duke.DataHandler;
import duke.DukeException;
import duke.TaskManager;
import duke.task.Task;

public class AddTaskCommand extends Command{
    protected Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskManager taskManager, DataHandler dataHandler) throws DukeException {
        taskManager.addTasks(task, true);
        dataHandler.saveData(taskManager);
    }
}
