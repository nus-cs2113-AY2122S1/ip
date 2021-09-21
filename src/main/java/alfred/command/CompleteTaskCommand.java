package alfred.command;

import alfred.ui.TextUi;

public class CompleteTaskCommand extends Command {
    private final int taskIndex;

    public CompleteTaskCommand(int index) {
        taskIndex = index;
    }

    public void execute() {
        taskList.setTaskDoneInList(taskIndex);
        String completedTaskDescription = taskList.getTask(taskIndex).getDescription();
        TextUi.completeTaskMessage(taskIndex, completedTaskDescription);
    }
}
