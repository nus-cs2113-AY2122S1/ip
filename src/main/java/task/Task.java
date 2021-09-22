package task;

public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Class constructor with specified task name.
     *
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Class constructor with the specified task name and isDone value.
     *
     * @param taskName The name of the task
     * @param isDone The boolean of whether task is done
     */
    public Task(String taskName, boolean isDone) {
        this(taskName);
        if (isDone) {
            setDone();
        }
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns whether the task is done.
     *
     * @return The boolean of whether task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the Status Icon based on whether task is done.
     *
     * @return The status icon based on isDone
     */
    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    /**
     * Returns a String-formatted task for printing.
     *
     * @return The string-formatted task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }
}
