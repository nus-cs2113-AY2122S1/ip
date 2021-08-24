/**
 * The Task class represents a task with a description
 * and a status (whether it's done or not).
 *
 * @author richwill28
 */
class Task {
    /** Description of the task */
    protected String description;

    /** Status of the task (whether it's done or not) */
    protected boolean isDone;

    /**
     * The constructor method. Assigns the task
     * description and set initial status to "not done".
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task, whether it's done
     * or not.
     *
     * @return "X" if task is done, otherwise returns " ".
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as "done".
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
