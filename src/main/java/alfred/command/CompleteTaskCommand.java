package alfred.command;

import alfred.task.Task;
import alfred.ui.TextUi;

public class CompleteTaskCommand extends Command {
    private final int taskIndex;

    public CompleteTaskCommand(int index) {
        taskIndex = index;
    }

    /**
     * This method sets a Task as done based on its given index.
     */
    public void execute() {
        try {
            taskList.setTaskDoneInList(taskIndex);
            Task completedTask = taskList.getTask(taskIndex);
            TextUi.completeTaskMessage(taskIndex, completedTask);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            TextUi.uninitialisedTaskIndexMessage(taskList.getSize());
        }
    }
}
