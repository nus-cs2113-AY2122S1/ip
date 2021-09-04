package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    /** Whether the task has been completed */
    protected boolean isDone;

    /**
     * Creates a task with the specified description.
     * By default, task is not yet completed.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task (based on whether it is completed).
     * The icon is a string of length 1 ("X" if completed, or " " if not).
     *
     * @return Status icon for the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /** Marks task as completed */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task (consisting of its status icon and description).
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
