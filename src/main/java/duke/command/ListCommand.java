package duke.command;

import duke.DataHandler;
import duke.TaskManager;

public class ListCommand extends Command{

    @Override
    public void executeCommand(TaskManager taskManager, DataHandler dataHandler) {
        taskManager.printTasks();
    }
}
