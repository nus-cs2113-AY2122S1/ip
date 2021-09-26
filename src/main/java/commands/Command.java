package commands;

import common.DukeException;
import task.Task;
import task.TaskManager;

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

    public CommandResult execute() throws DukeException {
        throw new DukeException("This method is to be implemented by child classes.");
    }
}
