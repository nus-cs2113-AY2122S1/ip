package alfred.command;

import alfred.task.Task;
import alfred.ui.TextUi;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int index) {
        taskIndex = index;
    }

    public void execute() {
        Task removedTask = taskList.removeTask(taskIndex);
        int numberOfTasks = taskList.getSize();
        TextUi.deleteTaskMessage(removedTask, numberOfTasks);
    }
}
