package tasks;

/**
 * Represents the interactions with a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns 'X' if the task is done and ' ' otherwise as the done status.
     *
     * @return Done status as String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
     * Marks a task as done by changing the isDone boolean.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the true if task is done and false otherwise.
     *
     * @return Done status as boolean.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns task information in String format. To be overridden by child classes.
     *
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
