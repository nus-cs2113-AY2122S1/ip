package duke.task;

/**
 * Represents a task that was added to the TaskList.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Re-formats the task type, status and description into the proper format for the txt file.
     *
     * @return task details in format for txt file.
     */
    public abstract String getTaskDetailsInFileFormat();

    /**
     * Gets the description of the task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is done or not, and gets the corresponding icon.
     *
     * @return X if task is done, otherwise returns a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets a particular task as done.
     */
    public void setAsDone() {
        isDone = true;
    }

    /**
     * Overwrites the default method with a custom print message instead.
     *
     * @return the task status and details.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
