package commands;

import task.Task;
import task.TaskManager;

import static ui.Ui.DISPLAYED_INDEX_OFFSET;

public abstract class Command {
    protected TaskManager taskManager;
    private int targetIndex = -1;

    public void setData(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    protected Command() {
    }

    public Command(int targetDisplayIndex) {
        this.setTargetIndex(targetDisplayIndex);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskManager.getTaskList().get(targetIndex);
    }

    public abstract CommandResult execute();
}
