package duke.task;

/**
 * Representing a task with a description and
 * a boolean variable isDone to indicate whether
 * the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status icon of the task based on whether
     * the current task is marked as done.
     *
     * @return "[X]" if the task is marked as done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Indicates whether the task is marked as done or not by returning
     * a boolean (true or false).
     *
     * @return A boolean indicating whether the task is marked as done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Provides a String representation of Task.
     *
     * @return A String representation of Task with its
     * status and description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
