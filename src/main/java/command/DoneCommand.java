package command;

import task.TaskManager;
import ui.UI;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        taskManager.markAsDone(taskIndex);
        ui.printMarkedDoneMessage(taskManager.getTasks()[taskIndex - 1]);
    }
}
