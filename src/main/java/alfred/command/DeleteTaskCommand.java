package alfred.command;

import alfred.task.Task;
import alfred.ui.TextUi;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int index) {
        taskIndex = index;
    }

    /**
     * This method deletes a Task from TaskList given an index.
     */
    public void execute() {
        try {
            Task removedTask = taskList.removeTask(taskIndex);
            int numberOfTasks = taskList.getSize();
            TextUi.deleteTaskMessage(removedTask, numberOfTasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            TextUi.uninitialisedTaskIndexMessage(taskList.getSize());
        }
    }
}
