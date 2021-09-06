package duke.command;

import duke.TaskManager;

public class ListCommand extends Command{

    @Override
    public void executeCommand(TaskManager taskManager) {
        taskManager.printTasks();
    }
}
