package command;

import task.Task;
import task.TaskManager;
import ui.UI;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        Task deletedTask = taskManager.deleteTask(taskIndex);
        ui.printTaskDeletedMessage(deletedTask, taskManager.getTaskCount());
    }
}
