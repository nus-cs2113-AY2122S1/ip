package commands;

import task.Task;
import task.TaskManager;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskManager taskManager;
    private int targetIndex = -1;

    /**
     * Supplies the data the command will operate on.
     * @param taskManager contains the task list.
     */
    public void setData(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    protected Command() {
    }

    /**
     * Constructor that sets the target index.
     * Used for MarkAsDoneCommand and DeleteCommand
     * @param targetIndex target index of the task that we want to perform an operation on
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Extracts the target task.
     * @return the target task.
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the task list.
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskManager.getTaskList().get(targetIndex);
    }

    /**
     * Executes the command and returns the result
     */
    public abstract CommandResult execute();
}
