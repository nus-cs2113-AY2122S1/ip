package duke.data.task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor which sets the description and isDone value as the default false.
     *
     * @param description a string that contains the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representing the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a boolean representing whether a task is marked done or not.
     *
     * @return the isDone value of the task
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the task description.
     *
     * @param description the task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the isDone value of the task as true to mark it as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns a String representation of the task's isDone status.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the String representation of the task.
     *
     * @return the status icon of the task and its description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
