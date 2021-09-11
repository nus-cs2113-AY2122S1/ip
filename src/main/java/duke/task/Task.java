package duke.task;

public class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected TaskType taskType = null;

    /**
     * Constructor for Task, task is marked as not completed.
     *
     * @param taskName the String containing the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
    }

    /**
     * Marks task as completed.
     */
    public void setCompleted() {
        isCompleted = true;
    }

    /**
     * Returns task name as String.
     *
     * @return the String of the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return the Boolean of whether the task is completed.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns the TaskType corresponding to the task's type.
     *
     * @return the enum TaskType specifying the task's type.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Creates a description of the task containing whether it has been completed and its name.
     *
     * @return a String describing the task.
     */
    public String toString() {
        String checkbox = isCompleted ? "[X]" : "[ ]";
        return checkbox + " " + taskName;
    }

    /**
     * Creates a String containing the Task information for storage in a .txt file.
     *
     * @return a String describing the task for storage.
     */
    public String getStorageString() {
        return "";
    }
}
