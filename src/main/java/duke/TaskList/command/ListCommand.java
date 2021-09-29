package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class ListCommand extends Command{
    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    public void execute() {
        taskManager.getAndPrintTaskList();
    }
}
