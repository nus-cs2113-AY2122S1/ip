package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private final String description;
    /** Whether the task has been completed */
    private boolean isDone;

    /**
     * Creates a task with the specified description.
     * By default, task is not yet completed.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        isDone = false;
    }

    /**
     * Returns the status icon for the task (based on whether it is completed).
     * The icon is a string of length 1 ("X" if completed, or " " if not).
     *
     * @return Status icon for the task.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the icon for the type of task.
     *
     * @return Icon for the type of task.
     */
    public abstract String getTaskTypeIcon();

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return True if the task has been marked as completed; false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /** Marks task as completed */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task (consisting of the task type icon, status icon and description).
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskTypeIcon(), getStatusIcon(), getDescription());
    }
}
