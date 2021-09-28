package duke;

/**
 * Represents a <code>Task</code> object.
 */
public abstract class Task {
    private static final String DONE_ICON = "[X]";
    private static final String NOT_DONE_ICON = "[ ]";

    private String taskName;
    private boolean isDone;

    /**
     * Instantiates a <code>Task</code> object.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Description of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns a boolean indicating whether the task is done.
     *
     * @return a boolean indicating whether the task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the task status indicating whether the task is done.
     *
     * @return task status icon.
     */
    public String getStatusIcon() {
        return (getIsDone()? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Formats the <code>task</code> to <code>String</code>.
     *
     * @return Formatted task to user-friendly output.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + taskName;
    }
}
