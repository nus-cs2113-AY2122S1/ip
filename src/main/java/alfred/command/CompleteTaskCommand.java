package alfred.command;

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
            String completedTaskDescription = taskList.getTask(taskIndex).getDescription();
            TextUi.completeTaskMessage(taskIndex, completedTaskDescription);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            TextUi.uninitialisedTaskIndexMessage(taskList.getSize());
        }
    }
}
