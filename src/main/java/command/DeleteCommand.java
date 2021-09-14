package command;

import task.Task;
import task.TaskManager;
import ui.UI;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {
       try {
            Task deletedTask = taskManager.deleteTask(taskIndex);
            ui.printTaskDeletedMessage(deletedTask, taskManager.getTaskCount());
        } catch (IOException e) {
           ui.printString("there was an error while deleting that task in memory");
       }
    }
}
