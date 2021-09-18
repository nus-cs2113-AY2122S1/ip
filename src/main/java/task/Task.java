package task;

/**
 * Represents a task in the TaskManager.
 * It contains task basic information like name and completion status.
 */
public class Task {

    private String taskName;
    private Boolean isCompleted;

    /**
     * Creates a task and set its name and completion status.
     *
     * @param taskName Task name provided by user
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
    }

    public String getTask() {
        return taskName;
    }

    public void setTaskCompleted() {
        isCompleted = true;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
